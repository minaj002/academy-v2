package com.academy.core.command.result;

public class EditMemberResult implements CommandResult {

	private Long memberId;

	public EditMemberResult(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return memberId;
	}
	
}
