package com.tcl.uf.scheduler.content;

import com.tcl.commonservice.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleBrowseStatTask {

    @Autowired
    private ContentService contentService;

    @Scheduled(initialDelay = 60 * 1000, fixedDelay = 5 * 60 * 1000)
    public void callTask() {
        contentService.addBrowseNum();
    }
}
