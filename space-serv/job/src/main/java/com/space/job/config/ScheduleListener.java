package com.space.job.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nbport.answer.db.entity.Schedule;
import com.nbport.answer.db.mapper.ScheduledMapper;
import com.nbport.answer.job.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ScheduleListener {
    @Resource
    private ScheduledMapper scheduledMapper;
    @Resource
    private ScheduleTaskService scheduleTaskService;

    @Scheduled(initialDelay= 5*1000,fixedRate = 5*60*1000)
    public void scheduleListening(){
        List<Schedule> schedules = scheduledMapper.selectList(Wrappers.emptyWrapper());
        log.info("查找数据完成-----------");
        Map<Long, String> scheduledCronMap = scheduleTaskService.getScheduledCronMap();
        for (Schedule schedule : schedules) {
            log.info("进入匹配环节-----------------");
            if (scheduledCronMap.containsKey(schedule.getId())){
                log.info("id匹配成功------------id:"+schedule.getId());
                if(scheduledCronMap.get(schedule.getId()).equals(schedule.getCron())){
                    log.info("cron匹配成功------------cron:"+schedule.getCron());
                    continue;
                }
            }
            log.info("匹配失败------重新初始化定时任务");
            scheduleTaskService.initAllTask();
        }
    }
}
