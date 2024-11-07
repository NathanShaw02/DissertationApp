package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EPA {

	public String EpaArea, EPASectionDescription, AcademicEvidence, WorkplaceEvidence;

	
	public EPA(String area, String description, String aEvidence, String wEvidence) {//constructor
		EpaArea = area;
		EPASectionDescription = description;
		AcademicEvidence = aEvidence;
		WorkplaceEvidence = wEvidence;
		
	}
	
	public String getEpaArea() { //must capitalise letter after 'get' for propertyvaluefactory to find it
		return EpaArea;
	}
	public String getEPASectionDescription() {
		return EPASectionDescription;
	}
	public String getAcademicEvidence() {
		return AcademicEvidence;
	}
	public String getWorkplaceEvidence() {
		return WorkplaceEvidence;
	}
	
	
	public static void updateAcademicEvidence(String epaSection, String epaQuestionDescription, String AcademicEvidenceText, String WorkplaceEvidenceText, User CurrentUser) {
		
		int EPAQuestionSectionID = 0; //creates as a constant for this method
		String sectionQuery = "select epa_section_id from epa_section where EPA_Section_Name = '"+epaSection+"';"; 
		
		Connection connection = DBConnection.DatabaseConnection();
		
		
		//THE BELOW gets the epa section ID based upon the passed variable contiaing the section name
		//required as the epa entries table only contains the ID number - done rather than a fixed switch case as the sections may be updated within the database
		try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        ResultSet results = stmt.executeQuery(sectionQuery);
		        results.next();
		        EPAQuestionSectionID = results.getInt(1);
					
			} catch (SQLException sqlE){
			
			System.out.println(sqlE);
			}
		
		String questionIDQuery = "select epa_question_id from epa_questions where epa_QUESTION_section_id = "+EPAQuestionSectionID+" and epa_question_description = '"+epaQuestionDescription+"';";
		
		//THE BELOW gets the question ID based upon unique variables + using the seciton id identified from the last query
		int questionID = 0;//creates as a constant for this method
		
		try {
	        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
	        ResultSet results = stmt.executeQuery(questionIDQuery);
	        results.next();
	        questionID = results.getInt(1);
				
		} catch (SQLException sqlE){
		
		System.out.println(sqlE);
		}
		
		String updateEvidenceQuery = "UPDATE epa_ENTRIES SET EPA_ENTRY_academic_EVIDENCE = '"+ AcademicEvidenceText+"', EPA_ENTRY_workplace_EVIDENCE = '"+ WorkplaceEvidenceText+"' WHERE EPA_ENTRY_user_ID = "+CurrentUser.getUserID()+" and epa_entry_question_ID = "+questionID+";"; //USER ID HARDCODED FOR NOW
	
		try {
	        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
	        stmt.execute(updateEvidenceQuery);

				
		} catch (SQLException sqlE){
		
		System.out.println(sqlE);
		}
	}
	
	
}
