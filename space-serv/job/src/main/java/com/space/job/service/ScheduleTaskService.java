package com.space.job.service;


import com.space.db.entity.Schedule;

import java.util.Map;

/**
 * @ClassName ScheduledTaskService
 * @Description 定时任务接口
 */
public interface ScheduleTaskService {
    /**
     * 根据任务key 启动任务
     */
    Boolean start(Long taskId, Schedule scheduled);

    /**
     * 根据任务key 停止任务
     */
    Boolean stop(Long taskId);

    /**
     * 根据任务key 重启任务
     */
    Boolean restart(Long taskId, Schedule scheduled);

    /**
     * 初始化  ==> 启动所有正常状态的任务
     */
    void initAllTask();

    /**
     * 获取初始化内容
     */
    Map<Long, String> getScheduledCronMap();
}
