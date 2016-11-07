package com.yql.framework.schedule.support;


import com.yql.framework.schedule.utils.ReflectUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author Simon Wang
 */
@Intercepts({
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})
})
public final class PaginationInterceptor implements Interceptor {


    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        Connection connection = (Connection) invocation.getArgs()[0];
        StatementHandler delegate = (StatementHandler) ReflectUtils.getFieldValue(handler, "delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object paramObject = boundSql.getParameterObject();
        if (paramObject instanceof Pagination<?>) {
            Pagination<?> pagination = (Pagination<?>) paramObject;
            pagination.describeDto();
            MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getFieldValue(delegate, "mappedStatement");
            countTotalRecord(connection, mappedStatement, pagination, boundSql);
            String pageSql = getPageSql(pagination, boundSql.getSql());
            ReflectUtils.setFieldValue(boundSql, "sql", pageSql);
        }

        /*else {
//            Pagination pagination = new Pagination(1000, 1);
//            String pageSql = getPageSql(pagination, boundSql.getSql());
//            ReflectUtils.setFieldValue(boundSql, "sql", pageSql);
            //TODO:实现参数不是pagination类型和其他数据库分页支持
        }*/
        return invocation.proceed();
    }

    //postgreSQL分页查询
    private String getPageSql(Pagination<?> page, String sql) {
        return sql + " limit " + page.getPageSize() +" offset " + page.getOffset();
    }

    private String getCountSql(String sql) {
        String lowerCaseSql = sql.toLowerCase();
        boolean containsGroup = lowerCaseSql.contains("group by");
        boolean containsDistinct = lowerCaseSql.contains("distinct ");
        if (containsGroup || containsDistinct) {
            return "select count(1) from (" + sql + ") t";
        }
        int index = lowerCaseSql.indexOf("from");
        return "select count(1) " + sql.substring(index);
    }

    private void countTotalRecord(Connection connection, MappedStatement mappedStatement, Pagination<?> pagination, BoundSql boundSql) {
        String countSql = getCountSql(boundSql.getSql());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object additionalParameters = ReflectUtils.getFieldValue(boundSql, "additionalParameters");
        Object metaParameters = ReflectUtils.getFieldValue(boundSql, "metaParameters");
        BoundSql sql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, pagination);
        ReflectUtils.setFieldValue(sql, "additionalParameters", additionalParameters);
        ReflectUtils.setFieldValue(sql, "metaParameters", metaParameters);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, pagination, sql);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = connection.prepareStatement(sql.getSql());
            parameterHandler.setParameters(pstmt);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                pagination.setTotalCount(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
