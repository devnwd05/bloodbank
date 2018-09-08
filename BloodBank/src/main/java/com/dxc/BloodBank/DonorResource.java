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

import donorservices.DonorServices;
import model.Donor;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class DonorResource {

   

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	
        return "Got it!";
    }
    
   
    
    @GET
    @Path("getprofile/{mobile}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Donor getProfile(@PathParam("mobile")String mobile) //mobile
    {
    	
       
		return DonorServices.getProfile(mobile);
    }
  
    @PUT
    @Path("changepass/{mobile}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String  updateDonor(@PathParam("mobile")String mobile,Document doc) //old password,new password
    {
    	
       boolean state=DonorServices.updatePassword(mobile,doc);
       if(state)
       return "ok";
       else
    	   return "not updated.. try again";
    }
    
    @POST
    @Path("add") 
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addDonor(Donor obj) //object
    {
    	DonorServices.addDonor(obj);
        return "Got it!";
    }

    @PUT
    @Path("update/{mobile}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("mobile")String mobile,Donor obj) //object
    {
    	if(DonorServices.updateProfile(mobile,obj))
    		return "{Done}";
    	else
    		return "{Not done}";
    	
    }
}
