package com.dxc.BloodBank;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import activedonorservices.ActiveDonorServices;

import model.ActiveDonor;

@Path("activedonor")
public class ActiveDonorResource {
	
	@POST
	@Path("activate/{mobile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static void activate(@PathParam("mobile")String mobile) //mobile no
	{
		ActiveDonorServices.activate(mobile);
	}
	
	@POST
	@Path("find")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static ArrayList<ActiveDonor> find(Document doc) //state,city,blood group
	{
		return ActiveDonorServices.find(doc);
	}

	@POST
	@Path("deactivate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static String delete(Document doc) //mobile no
	{
		if(ActiveDonorServices.deactivate(doc))
		{
			return "Deactivated successfully";
		}
		else
		return "Can't do... retry";
		
	}

}
