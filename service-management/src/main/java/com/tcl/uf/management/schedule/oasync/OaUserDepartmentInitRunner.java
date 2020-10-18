package com.tcl.uf.management.schedule.oasync;

import com.tcl.commondb.management.repository.OaDepartmentRepository;
import com.tcl.commondb.management.repository.OaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OaUserDepartmentInitRunner implements CommandLineRunner {
    @Autowired
    private UserDepartmentSyncService userDepartmentSyncService;
    @Autowired
    private OaUserRepository oaUserRepository;
    @Autowired
    private OaDepartmentRepository oaDepartmentRepository;
    @Override
    public void run(String... strings) throws Exception {
//        long count = oaUserRepository.count();
//        if(count == 0){
//            userDepartmentSyncService.userSyncAll();
//        }
//        long count1 = oaDepartmentRepository.count();
//        if(count1 == 0){
//            userDepartmentSyncService.departmentSync();
//
//        }
    }
}
