package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

public class Notification {

	protected String notificationText;

	public Notification(String nType) {
		notificationText = nType;
	
	}

	
	public static void generateNotifications(TableView notificationTable,ObservableList dataForNotificationList, User currentUser) { //generates the users notifications
		
		
		dataForNotificationList.clear(); //removes all currently loaded notificaitons (avoids duplicates)
		LocalDate currentDate = LocalDate.now();
		LocalDate FiveDaysInFuture = currentDate.plusDays(5);
		LocalDate SevenDaysInFuture = currentDate.plusDays(7);
		String currentNumericMonth = String.valueOf(currentDate).substring(5,7);
		String monthInSevenDays = String.valueOf(SevenDaysInFuture).substring(5,7);
		
		if (currentNumericMonth.equals(monthInSevenDays)==false) {
			
			dataForNotificationList.add(new Notification("There are less than 7 days left of the month, please remember to complete your OTJ training"));
		}
		
		
		
		String findTasksDueSoonQuery = "SELECT Task_text, Task_Due_Date FROM tasks WHERE task_due_date BETWEEN '"+currentDate+"' AND '"+FiveDaysInFuture+"' and Task_User_ID_Assigned = "+currentUser.getUserID()+" order by Task_Due_Date asc;";
		Connection connection = DBConnection.DatabaseConnection();
		try {
		        Statement stmt = connection.createStatement();//creates statement for sending sql statements to database
				ResultSet resultSet = stmt.executeQuery(findTasksDueSoonQuery);
		
				while (resultSet.next()) {                                             
					
					dataForNotificationList.add(new Notification("The task '"+resultSet.getString(1)+"' is due soon on '"+resultSet.getString(2)));
				
				}
		} catch (SQLException sqlE){
		
        System.out.println(sqlE);
	
		}
		if (dataForNotificationList.isEmpty()) {
			dataForNotificationList.add(new Notification("You have no notifications"));
		}
		

		notificationTable.setItems(dataForNotificationList);
	}
	
	


	public String getNotificationText() {
		return notificationText;
	}

	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
		
		
}
