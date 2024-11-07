package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public class OTJ {

	protected Float hoursCompleted;
	protected String dayType, actionPlan, achieveGoal, learningAchieved;
	protected Date activityDate;
	protected int OTJ_Entry_ID;

	
	public OTJ(int entryID, String Day, String Plan, String Goal, String Achieved, Date Date, Float Hours) {
		OTJ_Entry_ID = entryID;
		dayType = Day;
		actionPlan = Plan;
		achieveGoal = Goal;
		learningAchieved = Achieved;
		activityDate = Date;
		hoursCompleted = Hours;
	
	}
	
	public static void submitNewOTJEntry(int userID, int entryYear, String entryMonth, String entryDayType, String actionPlan, String hopingAchieve, String learningAchieved, LocalDate entryDate, Float hrsCompleted) {
		
		//finds last id
		int lastEntryID = 0; //will default to 0 if below query fails (failing because there are no entries)
		Connection connection = DBConnection.DatabaseConnection();
		String findLastEntryID = "select OTJ_Entry_ID from OTJ_entries order by OTJ_Entry_ID desc limit 1;";
		try {
	        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
	        ResultSet result = stmt.executeQuery(findLastEntryID);
	        result.next();
	        lastEntryID = result.getInt(1);
	        
	} catch (SQLException sqlE) {
		System.out.println(sqlE);
	}
		lastEntryID++; //adds one to latest entry ID
		
		//enter entry into database
		
		String newOTJEntryQuery = "insert into otj_entries values("+lastEntryID+","+userID+","+entryYear+",'"+entryMonth+"','"+entryDayType+"','"+actionPlan+"','"+hopingAchieve+"','"+learningAchieved+"','"+entryDate+"',"+hrsCompleted+");";
		
		try {
	        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
	        stmt.execute(newOTJEntryQuery);
	          
	} catch (SQLException sqlE) {
		System.out.println(sqlE);
	}
			
	}
	
	public int getOTJ_Entry_ID() {
		return OTJ_Entry_ID;
	}

	public void setOTJ_Entry_ID(int oTJ_Entry_ID) {
		OTJ_Entry_ID = oTJ_Entry_ID;
	}

	public static void editOTJEntry(int entryID, String entryDayType, String actionPlan, String hopingAchieve, String learningAchieved, LocalDate entryDate, Float hrsCompleted) {
		
		Connection connection = DBConnection.DatabaseConnection();

		String newOTJEntryQuery = "update otj_entries set OTJ_Entry_Day_Type = '"+entryDayType+"', OTJ_Entry_Action_Plan = '"+actionPlan+"',OTJ_Entry_Hoping_Achieve = '"+hopingAchieve+"',OTJ_Entry_Achieved = '"+learningAchieved+"',OTJ_Entry_Date = '"+entryDate+"',OTJ_Entry_Hours_Completed = "+hrsCompleted+"    where otj_entry_ID = "+entryID+";";
		
		try {
	        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
	        stmt.execute(newOTJEntryQuery);
	          
	} catch (SQLException sqlE) {
		System.out.println(sqlE);
	}
			
	}
	
	
	
	public Float getHoursCompleted() {
		return hoursCompleted;
	}

	public void setHoursCompleted(Float hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public String getAchieveGoal() {
		return achieveGoal;
	}

	public void setAchieveGoal(String achieveGoal) {
		this.achieveGoal = achieveGoal;
	}

	public String getLearningAchieved() {
		return learningAchieved;
	}

	public void setLearningAchieved(String learningAchieved) {
		this.learningAchieved = learningAchieved;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public static double getOTJHoursRemaining(User currentUser) { //returns how many OTJ hours the user has remaining to complete
		//placeholder to be replaced by calculation
		double totalHours = 967.5;
		return ((totalHours)-(getOTJHoursCompleted(currentUser)));
		
	}
	
	public static double getOTJHoursCompleted(User currentUser) {//returns how many hours have been completed by the current user
		Connection connection = DBConnection.DatabaseConnection();
		String query = "select round(SUM(OTJ_Entry_Hours_Completed),1) from otj_entries where OTJ_Entry_User_ID ="+currentUser.getUserID()+";";
		double hoursCompleted = 0;
		try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        ResultSet results = stmt.executeQuery(query);
		        
		        results.next();
		        	
		        hoursCompleted= results.getDouble(1);

			} catch (SQLException sqlE){
			
			System.out.println(sqlE);
			}
		return hoursCompleted;
		
	}
	
}
