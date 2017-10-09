package com.academy.core.command.result;

public class AddMemberResult implements CommandResult {

	private Long memberId;

	public AddMemberResult(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return memberId;
	}
	
}
