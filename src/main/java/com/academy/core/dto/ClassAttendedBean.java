package com.academy.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClassAttendedBean {

    private String id;
    private Date date;
    private List<MemberBean> members;

}
