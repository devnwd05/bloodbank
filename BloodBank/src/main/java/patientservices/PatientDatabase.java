package patientservices;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import databaseconnection.DatabaseConnection;
import patientservices.PatientAdapter;


import model.Patient;


public class PatientDatabase {
	
	static MongoCollection<Document> pCollection;
	static {
		pCollection=DatabaseConnection.getPatientCollection();
	}
	
	public static void add(Patient Patient)
	{
		Document toAdd=PatientAdapter.toDocument(Patient);
		pCollection.insertOne(toAdd);
	}
	
	
	public static Patient getProfile(String mobile)
	{
		FindIterable<Document> fit=pCollection.find(Filters.eq("mobile", mobile));
		Patient Patient=PatientAdapter.toPatient(fit.first());	
		return Patient;
	}
	
	public static  boolean updateProfile(String mobile,Patient object)
	{
		Document updateFilter=new Document("mobile",mobile);
		//Document toUpdate= PatientAdapter.toDocument(object);
		//toUpdate.append("password", password);
		Document doc=new Document()
				.append("city", object.getCity())
				.append("state", object.getState());
		Document toUpdate=new Document("$set",doc);
		UpdateResult result= pCollection.updateOne(updateFilter,toUpdate);
		return result.wasAcknowledged();	
	}
	
	
	public static  Boolean updatePassword(String mobile,String oldPass,String newPass)
	{

		Document set = new Document("$set", new Document("password", newPass));
		UpdateResult result= pCollection.updateOne((Filters.and(Filters.eq("password",oldPass),
				Filters.eq("mobile", mobile))),set);
		return result.wasAcknowledged();
	}



}
