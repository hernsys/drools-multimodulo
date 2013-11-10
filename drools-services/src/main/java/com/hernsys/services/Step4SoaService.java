package com.hernsys.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hernsys.entities.Customer;

@WebService(targetNamespace = "http://www.hernsys.com.ar/services/project")
public interface Step4SoaService {

	
	@WebMethod(operationName = "getCustomerById")
	// @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, style =
	// Style.DOCUMENT, use = Use.LITERAL)
	@WebResult(name = "Proyecto")
	public String getCustomerById(@WebParam(name = "id") String id);

	@WebMethod(operationName = "getCustomerByName")
	@WebResult(name = "Proyecto")
	public Customer getCustomerByName(@WebParam(name = "name") String name);
	
}
