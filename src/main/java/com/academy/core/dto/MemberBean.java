package com.academy.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MemberBean {

    private Long Id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String street;
    private String city;
    private String email;
    private String phone;
    private Date dueDate;
    private Date joinDate;
    private List<SectionBean> sections;

}
