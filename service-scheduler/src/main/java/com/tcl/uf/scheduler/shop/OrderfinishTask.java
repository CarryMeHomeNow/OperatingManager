package com.tcl.uf.scheduler.shop;

import com.tcl.commonservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderfinishTask {
    @Autowired
    private ShopService shopService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void callTask() {
        Integer ret=shopService.finishOrder();
    }
}
