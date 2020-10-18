package com.tcl.uf.email.dto;

import javax.persistence.*;

@Entity
@Table(name = "test_demo")
public class DemoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(32)  DEFAULT NULL COMMENT '姓名'")
    private String name;
    @Column(name = "age", columnDefinition = "int(11)  DEFAULT NULL COMMENT '年龄'")
    private Integer age;
    @Column(name = "city", columnDefinition = "varchar(32)  DEFAULT NULL COMMENT '城市'")
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
