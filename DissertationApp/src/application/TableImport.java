package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;



public class TableImport {
	
	
	public static void EPAImport(ObservableList EPAQuestionList, String EPASectionSelected, User currentUser){
		
		EPAQuestionList.clear();
		String query = ""; //defines query ahead of time so that it can be updated in below switch case
		
		switch (EPASectionSelected) {//based upon the list item currently selected, retrieve the corrisponding epa entries
		
		case "Core Skills":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 0 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;//break out of switch case after info has been updated
		case "Core Technical Knowledge":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 1 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "Core Behavioural Skills":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 2 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "Software Engineer":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 3 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "IT Consultant / Business Management":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 4 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "Business Analysis":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 5 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "Data Analyst":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 6 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "Cyber Security Specialist":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 7 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		case "Network Engineer Specialist":
			query = "Select EPA_Question_AREA_NAME, EPA_Question_description, EPA_entry_academic_evidence, EPA_entry_workplace_evidence From epa_questions join epa_entries on EPA_Entry_Question_ID = epa_question_id where EPA_Question_Section_ID = 8 and EPA_Entry_User_ID = "+currentUser.getUserID()+";";
			break;
		}
		
	
		
		Connection connection = DBConnection.DatabaseConnection();
		try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
				ResultSet resultSet = stmt.executeQuery(query);
		
				while (resultSet.next()) {                                             
				
					EPAQuestionList.add(new EPA((resultSet.getString(1)), (resultSet.getString(2)), (resultSet.getString(3)),(resultSet.getString(4))));
				
				}
	} catch (SQLException sqlE){
		
        System.out.println(sqlE);
	
	}
	
	}
	
	public static void OTJImport(ObservableList dataList, int UserID, String Month, int Year) {
		
		
		dataList.clear();
		String Query = "Select otj_entry_id, otj_entry_day_type,OTJ_Entry_Action_Plan,OTJ_Entry_Hoping_Achieve,OTJ_Entry_Achieved,OTJ_Entry_Date,OTJ_Entry_Hours_Completed  from otj_entries where OTJ_entry_user_id = "+UserID+" and OTJ_ENTRY_MONTH = '"+Month+"' and otj_entry_year = "+Year+" order by otj_entry_date asc;";
		Connection connection = DBConnection.DatabaseConnection();
		try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
				ResultSet resultSet = stmt.executeQuery(Query);
		
				while (resultSet.next()) {        
					
					
					dataList.add(new OTJ((resultSet.getInt(1)),(resultSet.getString(2)), (resultSet.getString(3)), (resultSet.getString(4)),(resultSet.getString(5)),(resultSet.getDate(6)),(resultSet.getFloat(7))));
			
					
				}
	} catch (SQLException sqlE){
		
        System.out.println(sqlE);
	
	}
		
	}
	
	public static void TaskListImport(ObservableList dataList, int UserID) {
		
		
		dataList.clear();
		String Query = "select Task_ID,Task_User_ID_Assigned,task_text, task_due_date from tasks where task_user_id_assigned = "+UserID+" order by Task_Due_Date asc;";
		Connection connection = DBConnection.DatabaseConnection();
		try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
				ResultSet resultSet = stmt.executeQuery(Query);
		
				while (resultSet.next()) {                                   
					
					
					dataList.add(new Task((resultSet.getInt(1)),(resultSet.getInt(2)), (resultSet.getString(3)),(resultSet.getDate(4))));
			
					
				}
	} catch (SQLException sqlE){
		
        System.out.println(sqlE);
	
	}
		
	}
	

}
