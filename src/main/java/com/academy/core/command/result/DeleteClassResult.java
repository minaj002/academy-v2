package com.academy.core.command.result;

public class DeleteClassResult implements CommandResult {

	private Long classId;

	public DeleteClassResult(Long classId) {
		this.classId = classId;
	}

	public Long getClassId() {
		return classId;
	}
	
}
