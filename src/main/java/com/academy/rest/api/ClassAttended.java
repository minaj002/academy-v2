package com.academy.rest.api;

import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class ClassAttended {

	private String topic;
	private Date date;
	private String id;
	private List<Member> members= Collections.emptyList();

}
