module DissertationApp {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.base, javafx.graphics, javafx.fxml;


}
