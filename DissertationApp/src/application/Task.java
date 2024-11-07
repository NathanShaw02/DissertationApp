package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Task {
		
		protected int taskID, userID;
		protected String taskText;
		protected Date taskDueDate;
	
		
		public Task(int tskID,int uID, String tText,Date tDueDate) {
			taskID = tskID;
			userID = uID;
			taskText = tText;
			taskDueDate = tDueDate;
		
	}
		
		public static void submitNewTask(int userID, String tText, LocalDate tDueDate) {
			//finds last id
			int lastEntryID = 0; //will default to 0 if below query fails (failing because there are no entries)
			Connection connection = DBConnection.DatabaseConnection();
			String findLastEntryID = "select task_ID from tasks order by task_ID desc limit 1;";
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
			
			String Query = "insert into tasks values("+lastEntryID+","+userID+",'"+tDueDate+"','"+tText+"');";
			
			try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        stmt.execute(Query);
		          
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
				
		}
		
		public static void submitTaskEdit(int taskID,int userID, String newTaskText, LocalDate NewTaskDueDate) {
			Connection connection = DBConnection.DatabaseConnection();
			String taskEditQuery = "update tasks set Task_Due_Date = '"+NewTaskDueDate+"', task_text = '"+newTaskText+"' where task_ID = "+taskID+";";
			
			try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        stmt.execute(taskEditQuery);
		          
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
				
		}
		
		public static void completeTask(int taskID) {
			Connection connection = DBConnection.DatabaseConnection();
			String taskDeleteQuery = "delete from tasks where task_id = "+taskID+";";
			
			try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
		        stmt.execute(taskDeleteQuery);
		          
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
				
		}
		
		


		public int getTaskID() {
			return taskID;
		}


		public void setTaskID(int taskID) {
			this.taskID = taskID;
		}


		public int getUserID() {
			return userID;
		}


		public void setUserID(int userID) {
			this.userID = userID;
		}


		public String getTaskText() {
			return taskText;
		}


		public void setTaskText(String taskText) {
			this.taskText = taskText;
		}


		public Date getTaskDueDate() {
			return taskDueDate;
		}


		public void setTaskDueDate(Date dueDate) {
			this.taskDueDate = dueDate;
		}

		
		
		
}
