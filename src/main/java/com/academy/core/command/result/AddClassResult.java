package com.academy.core.command.result;

public class AddClassResult implements CommandResult {

	private Long id;

	public AddClassResult(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
}
