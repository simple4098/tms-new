package com.yql.framework.schedule.support.job;


import com.yql.framework.schedule.domain.JobType;
import com.yql.framework.schedule.support.task.TaskJobContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Http 方法 远程 访问
 * @author wangxiaohong
 */
@Component
public class HttpTaskJobHandler implements TaskJobHandler {
    private static Logger logger = LoggerFactory.getLogger(HttpTaskJobHandler.class);

    @Override
    public boolean supports(TaskJobContext meta) {
        return meta.getType() != null &&
                meta.getType().equals(JobType.HTTP);
    }

    @Override
    public void handle(JobExecutionContext context, TaskJobContext meta) throws Exception {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        HttpGet httpGet = new HttpGet(meta.getUri());

        HttpResponse response = httpClient.execute(httpGet);
        logger.info("开始请求资源:" + meta.getUri());
        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("服务器返回状态码:" + statusCode);
        if (statusCode != 200) {
            throw new JobExecutionException("服务器内部错误");
        }
    }
}
