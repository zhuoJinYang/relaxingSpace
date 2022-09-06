package com.space.job.service;

/**
 * @ClassName ScheduledTaskJob
 * @Description 创建调度任务公共父接口
 */
public interface ScheduledTaskJob extends Runnable{
    public ScheduledTaskJob setPaperId(Long pageId);
}
