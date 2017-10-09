package com.academy.core.command.result;

public class DeletePaymentResult implements CommandResult {

	private Long paymentId;

	public DeletePaymentResult(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getPaymentId() {
		return paymentId;
	}
	
}
