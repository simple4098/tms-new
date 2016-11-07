package com.yql.framework.schedule.support;

/**
 * For describing dto to pagination map, we have to invoke Pagination#describeDto()
 * so, create the aspect on all select* methods before invoking.
 * @author wangxiaohong
 */
public aspect DescribedObjectAj {
    pointcut dtoDescribe(): execution(public * com.funi.core..*Dao.select*(..)) ;

    before(): dtoDescribe(){
        Object[] args = thisJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Describable) {
                ((Describable) arg).describe();
            }
        }
    }
}
