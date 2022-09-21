package com.space.job.config;

import com.space.job.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName ScheduledTaskRunner
 * @Description 项目启动完毕后开启需要自启的任务
 */
@Slf4j
@Component
public class ScheduleTaskRunner implements ApplicationRunner {

    @Resource
    private ScheduleTaskService scheduledTaskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 开始!");
        scheduledTaskService.initAllTask();
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 结束！");
    }
}
