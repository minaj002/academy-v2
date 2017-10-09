package com.academy.core.command.result;

public class AddSectionResult implements CommandResult{

	private Long id;

	public AddSectionResult(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}	
}
