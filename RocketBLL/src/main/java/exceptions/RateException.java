package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	RateDomainModel rdm;
	
	public RateException(RateDomainModel r){
		rdm = r;
	}
	
	public RateDomainModel getRDM(){
		return rdm;
	}
}
