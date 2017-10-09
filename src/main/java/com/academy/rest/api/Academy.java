package com.academy.rest.api;

import lombok.Data;

import java.util.List;

@Data
public class Academy {
	
	private String name;

	private String email;
	
	private String password;

	private List<Section> sections;

}
