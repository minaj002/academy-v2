package com.academy.core.command.result;

public class DeleteMemberResult implements CommandResult {

	private Long memberId;

	public DeleteMemberResult(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return memberId;
	}
	
}
