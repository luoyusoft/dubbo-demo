package com.jinhaoxun.dubbo.framework.quartz.job;

import com.jinhaoxun.dubbo.exception.CustomRuntimeException;
import com.jinhaoxun.dubbo.constant.ResponseMsg;
import com.jinhaoxun.dubbo.quartz.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import javax.annotation.Resource;

/**
 * @version: 1.0
 * @author jinhaoxun
 * @Date 2019-08-09
 * @description Job测试类
 */
@Slf4j
public class JobTest implements Job {

    @Resource
    private TaskService taskService;

    /**
     * @author jinhaoxun
     * @description 重写任务内容
     * @param jobExecutionContext 设置的key
     * @throws CustomRuntimeException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //获取参数
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        String taskId = dataMap.getString("taskId");
        String id = dataMap.getString("id");
        String name = dataMap.getString("name");
        try {
            log.info("执行任务{},{}",id,name);
            taskService.updateExecutionStatus(Long.valueOf(taskId));
        } catch (Exception e) {
            throw new CustomRuntimeException(ResponseMsg.QUARTZ_EXECUTION_FAIL.getCode(),
                    ResponseMsg.QUARTZ_EXECUTION_FAIL.getMsg() + e.getMessage());
        }
    }
}
