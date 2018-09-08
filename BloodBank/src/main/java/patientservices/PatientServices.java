package patientservices;



import org.bson.Document;

import patientservices.PatientDatabase;


import model.Patient;

public class PatientServices {
	public static void addPatient(Patient obj)
	{
			PatientDatabase.add(obj);
	}
	
	
//	public static ArrayList<Patient> findPatient(Patient obj){
//		return PatientDatabase.findPatient(obj.getCity(),obj.getState(),obj.getBloodGroup());
//	} 
	
	public static Patient getProfile(String mobile){
		
		return PatientDatabase.getProfile(mobile);
	} 

	
	public static boolean updateProfile(String password, Patient obj)
	{
		return PatientDatabase.updateProfile(password,obj);
	}
	
	public static Boolean updatePassword(String mobile,Document doc){
		String oldPass=doc.getString("old password");
		String newPass=doc.getString("new password");
		return PatientDatabase.updatePassword(mobile,oldPass,newPass);
	} 
	

	
//	{
//        "name": "Pratibha",
//        "mobile": "9189678906",
//        "city": "Warangal",
//        "state": "Telangana",
//		"password": "pratibhaa"
//        
//    }
}
