package com.tcl.uf.email.repository;

import com.tcl.uf.email.dto.DemoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemoRepository extends JpaRepository<DemoDto, Long>, JpaSpecificationExecutor<DemoDto> {
}
