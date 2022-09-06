package com.space.job.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nbport.answer.db.entity.Schedule;
import com.nbport.answer.db.mapper.ScheduledMapper;
import com.nbport.answer.job.service.ScheduleTaskService;
import com.nbport.answer.job.service.ScheduledTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ScheduledTaskServiceImpl
 * @Description 定时任务实现
 */
@Slf4j
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    @Value("${task.enabled}")
    private Boolean taskEnable;

    /**
     * 可重入锁
     */
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 定时任务线程池
     */
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 初始化监听任务调度
     */
    private Map<Long,String> scheduledCronMap = new ConcurrentHashMap<>();

    /**
     * 存放已经启动的任务map
     */
    private Map<Long, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();

    @Resource
    private ScheduledMapper scheduledMapper;

    /**
     * 描述: 根据任务id 启动任务
     *
     * @param taskId
     * @param scheduled
     * @return java.lang.Boolean
     * @author lv617
     * @date 2020/9/24 11:16
     */
    @Override
    public Boolean start(Long taskId, Schedule scheduled) {
        log.info(">>>>>> 启动任务 {} 开始 >>>>>>", taskId);
        //添加锁放一个线程启动，防止多人启动多次
        lock.lock();
        log.info(">>>>>> 添加任务启动锁完毕");
        try {
            //校验是否已经启动
            if (this.isStart(taskId)) {
                log.info(">>>>>> 当前任务已经启动，无需重复启动！");
                return false;
            }
            //查询配置
            if(scheduled == null)
                scheduled = this.getByTaskKey(taskId);
            if(scheduled == null)
                return false;
            //启动任务
            this.doStartTask(scheduled);
        } finally {
            // 释放锁
            lock.unlock();
            log.info(">>>>>> 释放任务启动锁完毕");
        }
        log.info(">>>>>> 启动任务 {} 结束 >>>>>>", taskId);
        return true;
    }

    /**
     * 描述: 查询定时任务配置参数
     *
     * @param taskId
     * @return com.yihaocard.main.module.scheduled.model.Scheduled
     * @author lv617
     * @date 2020/9/24 11:14
     */
    private Schedule getByTaskKey(Long taskId) {
        Schedule scheduled = scheduledMapper.selectById(taskId);
        if(scheduled == null)
            return null;
        return scheduled;
    }

    /**
     * 描述: 根据 id 停止任务
     *
     * @param taskId
     * @return java.lang.Boolean
     * @author lv617
     * @date 2020/9/24 11:17
     */
    @Override
    public Boolean stop(Long taskId) {
        log.info(">>>>>> 进入停止任务 {}  >>>>>>", taskId);
        //当前任务实例是否存在
        boolean taskStartFlag = scheduledFutureMap.containsKey(taskId);
        log.info(">>>>>> 当前任务实例是否存在 {}", taskStartFlag);
        if (taskStartFlag) {
            //获取任务实例
            ScheduledFuture scheduledFuture = scheduledFutureMap.get(taskId);
            //关闭实例
            boolean cancel = scheduledFuture.cancel(true);
            log.info("cancel:{}", cancel);
            //删除关闭的任务实例
            scheduledFutureMap.remove(taskId);
        }
        log.info(">>>>>> 结束停止任务 {}  >>>>>>", taskId);
        return taskStartFlag;
    }

    /**
     * 描述: 根据任务id 重启任务
     *
     * @param taskId
     * @param scheduled
     * @return java.lang.Boolean
     * @author lv617
     * @date 2020/9/24 11:18
     */
    @Override
    public Boolean restart(Long taskId, Schedule scheduled) {
        log.info(">>>>>> 进入重启任务 {}  >>>>>>", taskId);
        //先停止
        this.stop(taskId);
        //查询配置
        if(scheduled == null)
            scheduled = this.getByTaskKey(taskId);
        if(scheduled == null)
            return false;
        //再启动
        return this.start(taskId,scheduled);
    }

    /**
     * 初始化  ==> 启动所有正常状态的任务
     */
    @Override
    public void initAllTask() {
        if(!taskEnable){
            log.info("配置文件禁用了定时任务----");
            return;
        }
        List<Schedule> scheduledList = scheduledMapper.selectList(Wrappers.emptyWrapper());
        log.info("初始化  ==> 启动所有正常状态的任务开始 ！size={}", scheduledList == null ? 0 : scheduledList.size());
        // 先清除Map中所有内容
        scheduledFutureMap.clear();
        scheduledCronMap.clear();
        if (scheduledList == null || scheduledList.size() < 1) {
            return;
        }
        for (Schedule scheduled : scheduledList) {
            //任务 key
            Long taskId = scheduled.getId();
            //校验是否已经启动
            if (this.isStart(taskId)) {
                // 重启任务
                this.restart(taskId,scheduled);
            } else {
                // 启动任务
                this.doStartTask(scheduled);
            }
        }
        log.info("初始化  ==> 启动所有正常状态的任务结束 ！");
    }

    @Override
    public Map<Long, String> getScheduledCronMap() {
        return scheduledCronMap;
    }
    /**
     * 执行启动任务
     */
    private void doStartTask(Schedule scheduled) {
        if (scheduled == null)
            return;
        //任务id
        Long taskId = scheduled.getId();
        //定时表达式
        String taskCron = scheduled.getCron();
        //新增需要定时调度的接口
        ScheduledTaskJob scheduledTaskJob = new ScheduledTaskJobImpl();
        log.info(">>>>>> 任务 [ {} ] ,cron={}", scheduled.getName(), taskCron);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(scheduledTaskJob.setPaperId(scheduled.getPaperId()), (TriggerContext triggerContext) -> new CronTrigger(taskCron).nextExecutionTime(triggerContext));
        //将启动的任务放入 map
        scheduledFutureMap.put(taskId, scheduledFuture);
        scheduledCronMap.put(taskId,taskCron);
    }

    /**
     * 任务是否已经启动
     */
    private Boolean isStart(Long taskId) {
        //校验是否已经启动
        if (scheduledFutureMap.containsKey(taskId)) {
            if (!scheduledFutureMap.get(taskId).isCancelled()) {
                return true;
            }
        }
        return false;
    }
}
