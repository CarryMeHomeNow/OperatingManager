package com.tcl.uf.email.service;

import com.tcl.uf.email.dto.DemoDto;

public interface DemoService {
    public DemoDto findById(Long id);

    public Long save(DemoDto demoDto);
}
