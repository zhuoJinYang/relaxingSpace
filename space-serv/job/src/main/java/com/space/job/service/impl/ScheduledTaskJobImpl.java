package com.space.job.service.impl;

import com.space.job.service.ScheduledTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ScheduledTaskJobImpl implements ScheduledTaskJob {


    @Override
    public void run() {
        log.info("ScheduledTask => {} 每日一练题目生成成功! {}", Thread.currentThread().getName());
    }
}
