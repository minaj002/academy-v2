package com.academy.core.command.result;

public class AddAcademyResult implements CommandResult{
	
	private Long id;

	public AddAcademyResult(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}	
}
