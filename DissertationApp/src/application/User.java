package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {



		protected String Username, Password, Forename, Surname, CoursePathway, Organisation, LineManager;
		protected int UserID, StudentNumber;

		
		public User(int ID, String Uname, String Pword, String FName, String SName, int StuNumber, String Pathway, String Org, String Manger) {
			UserID = ID;
			Username = Uname;
			Password = Pword;
			Forename = FName;
			Surname = SName;
			StudentNumber = StuNumber;
			CoursePathway = Pathway;
			Organisation = Org;
			LineManager = Manger;
			
		}
		

		
		
		public static boolean loginValidationTest(String potentialUsername, String potentialPassword,User currentUser) {
			String loginValidationQuery = "select * from users where User_Username = '"+potentialUsername+"' and User_Password = '"+potentialPassword+"';";
			Connection connection = DBConnection.DatabaseConnection();

			try {
			        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
			        ResultSet results = stmt.executeQuery(loginValidationQuery);
			        results.next();
			        if (results.getString(1) != null){
			        	//-----initialises new user [start]
			        	currentUser.setUserID(results.getInt(1));
			        	currentUser.setUsername(results.getString(2));
			        	currentUser.setPassword(results.getString(3));
			        	currentUser.setForename(results.getString(4));
			        	currentUser.setSurname(results.getString(5));
			        	currentUser.setStudentNumber(results.getInt(6));
			        	currentUser.setCoursePathway(results.getString(7));
			        	currentUser.setOrganisation(results.getString(8));
			        	currentUser.setLineManager(results.getString(9));
			        	//-----initialises new user [end]
			        	return true;
			        }else {
			        	return false; 
			        }
			        
			        
			} catch (SQLException sqlE) {
				System.out.println(sqlE);
				return false;//here so that something is always returned regardless of outcome
			}
		}
		
		public static void createUserInDatabase(String Uname, String Pword, String FName, String SName, int StuNumber, String Pathway, String Org, String Manger) { 
			//finds last uid in table so that 1 can be added to it
			String findUserIDQuery = "select user_ID from users order by user_ID desc limit 1;";
			Connection connection = DBConnection.DatabaseConnection();
			int userID = 99999;

			try {
			        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
			        ResultSet searchResult = stmt.executeQuery(findUserIDQuery);
			        searchResult.next();
			        userID = searchResult.getInt(1);
			        
			} catch (SQLException sqlE) {
				System.out.println(sqlE);
			}
			
			userID++; //adds 1 to userID
			
			String newUserQuery = "insert into users values("+userID+",'"+Uname+"','"+Pword+"','"+FName+"','"+SName+"',"+StuNumber+",'"+Pathway+"','"+Org+"','"+Manger+"');"; 
			

			try {
			        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
			        stmt.execute(newUserQuery);
			        
			} catch (SQLException sqlE) {
				System.out.println(sqlE);
			}
		

		}
	
		public static void createEPASectionsInDatabase(int IDOfUser) {
			
			int numberOfEPAQuestions = 0;
			Connection connection = DBConnection.DatabaseConnection();
			
			String numEPAQsQuery = "select count(*) from epa_questions;";
			try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        ResultSet result = stmt.executeQuery(numEPAQsQuery);
		        result.next();
		        numberOfEPAQuestions = result.getInt(1);
		        
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
			int lastEntryID = 0; //will default to 0 if below query fails (failing because there are no entries)
			
			String findLastEntryID = "select EPA_Entry_ID from epa_entries order by EPA_Entry_ID desc limit 1;";
			try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        ResultSet result = stmt.executeQuery(findLastEntryID);
		        result.next();
		        lastEntryID = result.getInt(1);
		        
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
			
			
			try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        for (int i=0; i<numberOfEPAQuestions; i++) {; //for loop perameters: (executed 1 time before launch / condition for continued execution / executed each time)
		        	
		        	
		        	int sectionIDNumber = 0;
		        //using an if statement here instead of switch-case as switch case can NOT be used for logical expressions
		        //this ensures the correct section number is input
		        	if (i>=0 && i<7) { // section id 0
		        		sectionIDNumber = 0;
		        	}else if (i>=7 && i<17) { // section id 1	
		        		sectionIDNumber = 1;
		        	}else if (i>=17 && i<31) {// section id 2		        		
		        		sectionIDNumber = 2;
		        	}else if (i>=31 && i<43) {// section id 3		        		
		        		sectionIDNumber = 3;
		        	}else if (i>=43 && i<55) {// section id 4	        		
		        		sectionIDNumber = 4;
		        	}else if (i>=55 && i<67) {// section id 5	        		
		        		sectionIDNumber = 5;
		        	}else if (i>=67 && i<79) {// section id 6
		        		sectionIDNumber = 6;	        		
		        	}else if (i>=79 && i<91) {// section id 7
		        		sectionIDNumber = 7;       		
		        	}else if (i>=91 && i<103) {// section id 8
		        		sectionIDNumber = 8;
		        	}
		        	String updatingInsertionQuery = "insert into epa_entries values ("+(lastEntryID+1+i)+","+IDOfUser+","+sectionIDNumber+","+i+",'','');";
		        	stmt.execute(updatingInsertionQuery);
		        	
		        }
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
			
		}
		
		public static int findUserIDBasedOnUsernameAndPassword(String username, String password) {
			
			String query = "select user_ID from users where user_username = '"+username+"' and user_password = '"+password+"';"; //CONFIRMED WORKING
			Connection connection = DBConnection.DatabaseConnection();
			int userID = 99999;
			
			try {
			        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
			        ResultSet results = stmt.executeQuery(query);
			        results.next();
			        userID = results.getInt(1);
					return userID;
			        
				} catch (SQLException sqlE){
					//should never reach here as this is only called immediately after data has been validated previously, issue can only come from database side (EG if connection fails)
				System.out.println(sqlE);
				return userID;
				}
		}
		
		public static boolean isUsernameUnique(String passedUsername) {
			String query = "select User_Username from users where User_Username = '"+passedUsername+"';"; 
			Connection connection = DBConnection.DatabaseConnection();
			
			try {
			        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
			        ResultSet results = stmt.executeQuery(query);
			        results.next();
			        String foundName = results.getString(1);
			        if ((foundName.toLowerCase()).equals(passedUsername.toLowerCase())) { // used .equals here to compare strings
			        	return false;
			        }else {
			        	return true;
			        }
			        
				} catch (SQLException sqlE){
				System.out.println(sqlE);
				return true; 
				}
			
		}
		
		public String getUsername() {
			return Username;
		}

		public void setUsername(String username) {
			Username = username;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getForename() {
			return Forename;
		}

		public void setForename(String forename) {
			Forename = forename;
		}

		public String getSurname() {
			return Surname;
		}

		public void setSurname(String surname) {
			Surname = surname;
		}

		public String getCoursePathway() {
			return CoursePathway;
		}

		public void setCoursePathway(String coursePathway) {
			CoursePathway = coursePathway;
		}

		public String getOrganisation() {
			return Organisation;
		}

		public void setOrganisation(String organisation) {
			Organisation = organisation;
		}

		public String getLineManager() {
			return LineManager;
		}

		public void setLineManager(String lineManager) {
			LineManager = lineManager;
		}

		public int getUserID() {
			return UserID;
		}

		public void setUserID(int userID) {
			UserID = userID;
		}

		public int getStudentNumber() {
			return StudentNumber;
		}

		public void setStudentNumber(int studentNumber) {
			StudentNumber = studentNumber;
		}
}
