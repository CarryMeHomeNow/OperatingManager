package com.tcl.uf.email.service.impl;

import com.tcl.uf.email.dto.DemoDto;
import com.tcl.uf.email.repository.DemoRepository;
import com.tcl.uf.email.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);
    @Autowired
    private DemoRepository demoRepository;

    @Override
    public DemoDto findById(Long id) {
        // upgrade (hepeng)
        return demoRepository.getOne(id);
//        return demoRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)

    public Long save(DemoDto demoDto) {
        DemoDto save = demoRepository.save(demoDto);
        return save.getId();
    }
}
