package com.space.job.service.impl;

import com.nbport.answer.domain.service.PaperService;
import com.nbport.answer.domain.util.SpringContextUtil;
import com.nbport.answer.job.service.ScheduledTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ScheduledTaskJobImpl implements ScheduledTaskJob {

    private Long paperId;

    @Override
    public ScheduledTaskJob setPaperId(Long pageId) {
        this.paperId = pageId;
        return this;
    }
    @Override
    public void run() {
        PaperService paperService = (PaperService)SpringContextUtil.getBean(PaperService.class);
        paperService.setDailyPractice(paperId);
        log.info("ScheduledTask => {} 每日一练题目生成成功! {}", Thread.currentThread().getName(), paperId);
    }
}
