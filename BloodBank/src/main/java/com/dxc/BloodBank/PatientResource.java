package com.dxc.BloodBank;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import patientservices.PatientServices;
import model.Patient;


@Path("patient")
public class PatientResource {
	
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	
        return "Got it!";
    }
    
		@GET
	    @Path("getprofile/{mobile}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Patient getProfile(@PathParam("mobile")String mobile) //mobile
	    {
	    	
	       
			return PatientServices.getProfile(mobile);
	    }
	  
	    @PUT
	    @Path("changepass/{mobile}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String  updatePatient(@PathParam("mobile")String mobile,Document doc) //old password,new password
	    {
	    	
	       boolean state=PatientServices.updatePassword(mobile,doc);
	       if(state)
	       return "ok";
	       else
	    	   return "not updated.. try again";
	    }
	    
	    @POST
	    @Path("add") 
	    @Produces(MediaType.TEXT_PLAIN)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String addPatient(Patient obj) //object
	    {
	    	PatientServices.addPatient(obj);
	        return "Got it!";
	    }

	    @PUT
	    @Path("update/{mobile}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String update(@PathParam("mobile")String mobile,Patient obj) //object
	    {
	    	if(PatientServices.updateProfile(mobile,obj))
	    		return "{Done}";
	    	else
	    		return "{Not done}";
	    	
	    }
}
