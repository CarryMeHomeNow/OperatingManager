package com.tcl.uf.management.schedule;

import com.tcl.uf.management.schedule.oasync.UserDepartmentSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class UserDepartmentSchedule {
    @Autowired
    private UserDepartmentSyncService userDepartmentSyncService;
    /**
     * 从sap同步员工信息的定时任务
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void syncOnlineUserList() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String date = sf.format(new Date());
        userDepartmentSyncService.userSync(date);
        userDepartmentSyncService.countDepartmentUserNum();
    }

    /**
     * 从sap同步员工信息的定时任务
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void syncOnlineUserList2() {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        userDepartmentSyncService.userSync(s.format(calendar.getTime()));
        userDepartmentSyncService.countDepartmentUserNum();
    }
    /**
     * 从sap同步部门信息的定时任务
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void syncOnlineDepartmentList() {
        userDepartmentSyncService.departmentSync();
    }
}
