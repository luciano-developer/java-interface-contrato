package model.services;

public class PaypalService implements OnlinePaymentService {
	
	private static final double INTEREST_RATE = 0.01;
	private static final double FEE_PERCENTEGE = 0.02;
	
	@Override
	public double paymentFee(double amount) {
		return amount * FEE_PERCENTEGE;
	}

	@Override
	public double interest(double amount, int months) {
		return amount * months * INTEREST_RATE;
	}
}
