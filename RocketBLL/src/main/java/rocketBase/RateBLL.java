package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws Exception
	{
		ArrayList<RateDomainModel> rates = _RateDAL.getAllRates();
		int y = 0;
		int z = 0;

		for (int x = 0; x < rates.size(); x++){
			if (rates.get(x).getiMinCreditScore() <= GivenCreditScore){
				if (rates.get(x).getiMinCreditScore() >= y){
					y = rates.get(x).getiMinCreditScore();
					z = x;
				}
			}
		}
		
	if (y==0){
		RateDomainModel r = new RateDomainModel();
		r.setiMinCreditScore(GivenCreditScore);
		throw new RateException(r);
	}
	else return rates.get(z).getdInterestRate();
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
