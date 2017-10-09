package com.academy.rest.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class Member {

	private Long id;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private String street;
	private String city;
	private String email;
	private String phone;
	private String joinDate;
	private List<Section> sections;

	}
