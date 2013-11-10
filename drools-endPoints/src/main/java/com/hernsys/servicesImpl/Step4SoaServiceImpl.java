package com.hernsys.servicesImpl;

import javax.jws.WebService;

import com.hernsys.entities.Customer;
import com.hernsys.services.Step4SoaService;

@WebService(endpointInterface = "com.hernsys.services.Step4SoaService")
public class Step4SoaServiceImpl implements Step4SoaService {

	private static final String PREFIX = "1/";
	private static final String SINGLE_NS = PREFIX + "user/s";


	@Override
	public String getCustomerById(String id) {
		return "Horacio Antar";
	}

	@Override
	public Customer getCustomerByName(String name) {
		return this.getById(name);
	}
	
	private Customer getById(String name){
		Customer cust = null;
		if(name!=null){
			cust = new Customer();
			cust.setId(1L);
			cust.setName("Horacio");
		}
		return cust;
	}

}
