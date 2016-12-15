package rocketServer;

import java.io.IOException;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.hibernate.sql.Update;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message){
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			try{
				lq.setdRate(_RateBLL.getRate(lq.getiCreditScore()));
				lq.setdPayment(_RateBLL.getPayment(lq.getdRate(), lq.getiTerm(), lq.getiDownPayment(), lq.getdAmount(), false));
				}
			catch (Exception e) {
				System.out.println("Rate Error");
				System.exit(0);
			}
			
			sendToAll(lq);
		}
	}
}
