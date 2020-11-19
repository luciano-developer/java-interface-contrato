package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
		
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public ContractService() {
		// TODO Auto-generated constructor stub
	}

	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue()/months;
		for (int i = 1; i <= months; i++) {
			double updateQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
			Date dueDate = addMonth(contract.getDate(),i);
			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}

	private Date addMonth(Date date, int N) {
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);		
		return calendar.getTime();
	}

}
