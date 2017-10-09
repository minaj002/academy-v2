package com.academy.core.command.result;

public class EditPaymentResult implements CommandResult {

	private Long paymentId;

	public EditPaymentResult(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

}
