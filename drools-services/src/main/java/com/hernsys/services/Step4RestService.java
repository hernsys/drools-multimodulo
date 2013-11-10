package com.hernsys.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.hernsys.entities.Customer;

@Path("/step4Service")

@Produces("application/xml")
public interface Step4RestService {

	
	@GET
    @Path("/getCustomerByIdJson/{id}")
	@Produces("application/json")
	//http://localhost:8081/cxf/projectRest/step4Service/getCustomerByIdJson/1/
	public Customer getCustomerByIdJson(@PathParam("id") Long id);
	
	@GET
    @Path("/getCustomerByIdXml/{id}")
	@Produces("application/xml")
	//http://localhost:8081/cxf/projectRest/step4Service/getCustomerByIdXml/1/
	public Customer getCustomerByIdXml(@PathParam("id") Long id);
	
	
	
	
}
