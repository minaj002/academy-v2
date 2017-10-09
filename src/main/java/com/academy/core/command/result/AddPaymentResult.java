package com.academy.core.command.result;

public class AddPaymentResult implements CommandResult {

	private Long paymentId;

	public AddPaymentResult(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getPaymentId() {
		return paymentId;
	} 
	
}
