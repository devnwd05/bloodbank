package donorservices;



import org.bson.Document;

import model.Donor;



public class DonorServices {
	
	public static void addDonor(Donor obj)
	{
			DonorDatabase.add(obj);
	}
	
	
//	public static ArrayList<Donor> findDonor(Donor obj){
//		return DonorDatabase.findDonor(obj.getCity(),obj.getState(),obj.getBloodGroup());
//	} 
	
	public static Donor getProfile(String mobile){
		
		return DonorDatabase.getProfile(mobile);
	} 

	
	public static boolean updateProfile(String password, Donor obj)
	{
		return DonorDatabase.updateProfile(password,obj);
	}
	
	public static Boolean updatePassword(String mobile,Document doc){
		String oldPass=doc.getString("old password");
		String newPass=doc.getString("new password");
		return DonorDatabase.updatePassword(mobile,oldPass,newPass);
	} 
	
	
}
