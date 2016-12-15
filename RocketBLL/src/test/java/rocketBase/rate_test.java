package rocketBase;

import static org.junit.Assert.*;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;

import exceptions.RateException;

public class rate_test {


	@Test
	public void test1() throws Exception{
		RateBLL x = new RateBLL();
		assertTrue(5 == x.getRate(600));
	}
	
	@Test
	public void test2() throws Exception{
		RateBLL y = new RateBLL();
		try{
			y.getRate(200);
		}
		catch (RateException e){
			assertTrue(1==1);
		}
	}
	
	@Test
	public void test3() throws Exception{
		RateBLL z = new RateBLL();
		assertTrue(z.getPayment(0,12,0,0,false) == 0);
	}
}
