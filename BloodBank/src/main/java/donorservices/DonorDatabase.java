package donorservices;


import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import databaseconnection.DatabaseConnection;

import model.Donor;


public class DonorDatabase {
	
	static MongoCollection<Document> dCollection;
	static {
		dCollection=DatabaseConnection.getDonorCollection();
	}
	
	public static void add(Donor donor)
	{
		Document toAdd=DonorAdapter.toDocument(donor);
		dCollection.insertOne(toAdd);
	}
	
	
	public static Donor getProfile(String mobile)
	{
		FindIterable<Document> fit=dCollection.find(Filters.eq("mobile", mobile));
		Donor donor=DonorAdapter.toDonor(fit.first());	
		return donor;
	}
	
	public static  boolean updateProfile(String mobile,Donor object)
	{
		Document updateFilter=new Document("mobile",mobile);
		//Document toUpdate= DonorAdapter.toDocument(object);
		//toUpdate.append("password", password);
		Document doc=new Document()
				.append("email",object.getEmail())
				.append("age", object.getAge())
				.append("city", object.getCity())
				.append("state", object.getState());
		Document toUpdate=new Document("$set",doc);
		UpdateResult result= dCollection.updateOne(updateFilter,toUpdate);
		return result.wasAcknowledged();	
	}
	
	
	public static  Boolean updatePassword(String mobile,String oldPass,String newPass)
	{

		Document set = new Document("$set", new Document("password", newPass));
		UpdateResult result= dCollection.updateOne((Filters.and(Filters.eq("password",oldPass),
				Filters.eq("mobile", mobile))),set);
		return result.wasAcknowledged();
	}




}
