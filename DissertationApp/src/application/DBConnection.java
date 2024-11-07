package application;
import java.sql.*;


public class DBConnection {
	
	
	public static Connection DatabaseConnection() { //returns the a connection object that can be used to interact with the database
		
		
		//defines values used for connecting to database
		String url = "jdbc:mysql://localhost:3306/apprenticedb"; //address for database
		String username = "root"; //database username
		String password = "Password"; //database password
		
		try { //try is necessary here as if the application is unable to connect to the database an error will be produced
			Connection connection = DriverManager.getConnection(url, username, password); //passes necessary information into function 
			return connection; //returns the database connection variable that can be used to interact with the database
			
		} catch (SQLException e) {
			System.out.println("Database Connection Failed"); // prints to console if connection to database failed
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}

}
