package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do cotrato");
		System.out.print("Número:");
		int number = sc.nextInt();
		System.out.print("Data (DD/MM/AAAA): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();

		Contract contract = new Contract(number, date, totalValue);

		ContractService cs = new ContractService(new PaypalService());
		
		System.out.print("Entre com o número de parcelas: ");
		int numberMonths = sc.nextInt();
		
		cs.processContract(contract, numberMonths);
		
		System.out.println("Parcelas:");
		for (Installment installment : contract.getInstallments()) {
			System.out.println(installment.toString());
		}

		sc.close();
	}

}
