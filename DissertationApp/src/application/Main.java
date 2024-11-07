package application;
	
import java.util.Date;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	
	//defining variable to hold current user
	User currentUser = new User(0, null, null, null, null, 0, null, null, null);
	
	@Override
	public void start(Stage primaryStage) { //defines the stage as "primaryStage"

		//login page
		BorderPane loginPageBorder = new BorderPane(); //defines the border that will be added to the login scene
		Scene loginScene = new Scene(loginPageBorder,1600,900); //defines the first scene and assigns the border to it
		
		GridPane loginGrid = new GridPane(); //assigns new gridpane to variable named grid
		loginPageBorder.setCenter(loginGrid);

		loginGrid.setAlignment(Pos.CENTER); //alignment property changes default position of grid to centre
		loginGrid.setHgap(10); //space between columns in grid
		loginGrid.setVgap(10); // space between rows in grid
		loginGrid.setPadding(new Insets(25, 25, 25, 25)); //manages space around each grid pane

		Text scenetitle = new Text("Apprentice Hub Login"); //defines new text
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32)); //sets the font and size
		loginGrid.add(scenetitle, 0, 0, 2, 1); //specifies where defined text should be added (position 0,0 and column span of 2 and rowspan of 1)
		
		Label usernameText = new Label("Username:");
		usernameText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		loginGrid.add(usernameText, 0, 1); //(variable / column / row)
		
		TextField usernameEntry = new TextField();//entry box
		loginGrid.add(usernameEntry, 1, 1);
		
		Label passwordText = new Label("Password:");
		passwordText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		loginGrid.add(passwordText, 0, 2);
		
		PasswordField passwordEntry = new PasswordField(); //entry box that masks entered characters
		loginGrid.add(passwordEntry, 1, 2);
		
		Button logInButton = new Button("Sign in");
		logInButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		Button createAccountButton = new Button("Create Account");
		createAccountButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		loginGrid.add(createAccountButton, 0, 4);
		HBox hBoxlogInButton = new HBox(10); //sets an alignment for the button that is different from the alignment applied to the other controls in the grid pane. ||  positions all its child nodes (components) in a horizontal row
		hBoxlogInButton.setAlignment(Pos.BOTTOM_RIGHT);// defines position
		hBoxlogInButton.getChildren().add(logInButton);//adds button to hbox
		loginGrid.add(hBoxlogInButton, 1, 4);//adds hbox to grid
		
		final Text actiontarget = new Text(); //final keyword means its value cannot be modified
        loginGrid.add(actiontarget, 1, 3);
        
        //---------- Create Account Scene --------------
		
        BorderPane newAccountPageBorder = new BorderPane(); 
		Scene createAccountScene = new Scene(newAccountPageBorder,1600,900); 
		GridPane newAccountGrid = new GridPane();
		newAccountPageBorder.setCenter(newAccountGrid);
		
		//sets the percentage each grid column should take up
		ColumnConstraints newAccountColumn0 = new ColumnConstraints(); 
	    newAccountColumn0.setPercentWidth(25); 
	    ColumnConstraints newAccountColumn1 = new ColumnConstraints();
	    newAccountColumn1.setPercentWidth(10);
	    ColumnConstraints newAccountColumn2 = new ColumnConstraints(); 
	    newAccountColumn2.setPercentWidth(10); 
	    ColumnConstraints newAccountColumn3 = new ColumnConstraints(); 
	    newAccountColumn3.setPercentWidth(10); 
	    ColumnConstraints newAccountColumn4 = new ColumnConstraints();
	    newAccountColumn4.setPercentWidth(10);
	    ColumnConstraints newAccountColumn5 = new ColumnConstraints();
	    newAccountColumn5.setPercentWidth(10);
	    ColumnConstraints newAccountColumn6 = new ColumnConstraints();
	    newAccountColumn6.setPercentWidth(25);
	    newAccountGrid.getColumnConstraints().addAll(newAccountColumn0, newAccountColumn1, newAccountColumn2,newAccountColumn3,newAccountColumn4,newAccountColumn5,newAccountColumn6);
	    
	  //sets the percentage each grid row should take up
	    RowConstraints newAccountRow0 = new RowConstraints(); 
	    newAccountRow0.setPercentHeight(29); 
	    RowConstraints newAccountRow1 = new RowConstraints();
	    newAccountRow1.setPercentHeight(8);
	    RowConstraints newAccountRow2 = new RowConstraints(); 
	    newAccountRow2.setPercentHeight(8); 
	    RowConstraints newAccountRow3 = new RowConstraints();
	    newAccountRow3.setPercentHeight(8);
	    RowConstraints newAccountRow4 = new RowConstraints();
	    newAccountRow4.setPercentHeight(8);
	    RowConstraints newAccountRow5 = new RowConstraints();
	    newAccountRow5.setPercentHeight(5);
	    RowConstraints newAccountRow6 = new RowConstraints();
	    newAccountRow6.setPercentHeight(14);
	    RowConstraints newAccountRow7 = new RowConstraints();
	    newAccountRow7.setPercentHeight(25);
	    newAccountGrid.getRowConstraints().addAll(newAccountRow0,newAccountRow1, newAccountRow2,newAccountRow3,newAccountRow4,newAccountRow5,newAccountRow6,newAccountRow7);
		
	    
	    Label newAccountTitleLabel = new Label("Create Account");
	    newAccountTitleLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
		newAccountGrid.add(newAccountTitleLabel, 3, 0,3,1); //(variable / column / row)
		
	    
	    //left side entries
		Label newUsernameLabel = new Label("Username:");
		newUsernameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(newUsernameLabel, 1, 1); //(variable / column / row)
		
		TextField newUsernameEntry = new TextField();//entry box
		newAccountGrid.add(newUsernameEntry, 2, 1);
		
		Label newPasswordLabel = new Label("Password:");
		newPasswordLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(newPasswordLabel, 1, 2); //(variable / column / row)
		
		TextField newPasswordEntry = new TextField();//entry box
		newAccountGrid.add(newPasswordEntry, 2, 2);
		
		Label enterFirstNameLabel = new Label("First Name:");
		enterFirstNameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(enterFirstNameLabel, 1, 3); //(variable / column / row)
		
		TextField enterFirstNameEntry = new TextField();//entry box
		newAccountGrid.add(enterFirstNameEntry, 2, 3);
		
		Label enterSurnameLabel = new Label("Surname:");
		enterSurnameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(enterSurnameLabel, 1, 4); //(variable / column / row)
		
		TextField enterSurnameEntry = new TextField();//entry box
		newAccountGrid.add(enterSurnameEntry, 2, 4);
		
		//right side entries
		Label studentNumberLabel = new Label("Student Number:");
		studentNumberLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(studentNumberLabel, 4, 1); //(variable / column / row)
		
		TextField studentNumberEntry = new TextField();//entry box
		newAccountGrid.add(studentNumberEntry, 5, 1);

		
		Label coursePathwayLabel = new Label("Pathway:");
		coursePathwayLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(coursePathwayLabel, 4, 2); //(variable / column / row)
		
		ObservableList<String> PathwayList = FXCollections.observableArrayList("Software Engineer","IT Consultant / Business Management","Business Analysis","Data Analyst","Cyber Security Specialist","Network Engineer Specialist");
		ComboBox pathwayComboBox = new ComboBox(PathwayList);
		newAccountGrid.add(pathwayComboBox, 5, 2);
		
		Label enterCompanyLabel = new Label("Company:");
		enterCompanyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(enterCompanyLabel, 4, 3); //(variable / column / row)
		
		TextField enterCompanyEntry = new TextField();//entry box
		newAccountGrid.add(enterCompanyEntry, 5, 3);
		
		Label enterLineManagerLabel = new Label("Line Manager:");
		enterLineManagerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		newAccountGrid.add(enterLineManagerLabel, 4, 4); //(variable / column / row)
		
		TextField enterLineManagerEntry = new TextField();//entry box
		newAccountGrid.add(enterLineManagerEntry, 5, 4);
		
		//create acc buttons
		
		Button initialiseCreateAccountButton = new Button("Create Account");
		newAccountGrid.add(initialiseCreateAccountButton, 2, 6);
		
		Button cancelCreateAccountButton = new Button("Cancel");
		newAccountGrid.add(cancelCreateAccountButton, 4, 6);
		
		
		//---------- Main Page ----------
		
		
		BorderPane mainPageBorder = new BorderPane(); //defines the border that will be added to the main scene
		Scene mainScene = new Scene(mainPageBorder,1600,900); //defines the first scene and assigns the border to it
		
		ToolBar navigationToolBar = new ToolBar(); //defines the toolbar
		mainPageBorder.setTop(navigationToolBar);//adds the toolbar to the top of the border

		HBox toolbarButtonsHbox = new HBox(); //will contain all buttons and allow them to be evenly spaced
		toolbarButtonsHbox.setSpacing(9);//sets the space between buttons on the toolbar
		
	
		
		// toolbar buttons
		
		
		Button goToHomePageButton = new Button("Home Page");//defines toolbar button 1
		goToHomePageButton.setMinWidth(390); //sets minimum width of button, this is to space each evenly across the toolbar
		toolbarButtonsHbox.getChildren().add(goToHomePageButton);//adds the button to the hbox
		Button goToOTJPageButton = new Button("OTJ Timesheets");//defines toolbar button 2
		goToOTJPageButton.setMinWidth(390);
		toolbarButtonsHbox.getChildren().add(goToOTJPageButton);
		Button goToEPAPageButton = new Button("EPA Mapping Document");//defines toolbar button 3
		goToEPAPageButton.setMinWidth(390);
		toolbarButtonsHbox.getChildren().add(goToEPAPageButton);
		Button goToFAQPageButton = new Button("FAQ");//defines toolbar button 4
		goToFAQPageButton.setMinWidth(390);
		toolbarButtonsHbox.getChildren().add(goToFAQPageButton);
		navigationToolBar.getItems().add(toolbarButtonsHbox); //adds the hbox containing the buttons to the toolbar

		//--------------------------------------------
		//---------- Home Page ----------
		//--------------------------------------------
		
		GridPane mainPageGrid = new GridPane();  //initialises the GridPane for the main page which will be placed at the center of the border
		mainPageBorder.setCenter(mainPageGrid); //places the grid at the center of the border
		
		//the below divides the columns on the page up into different sizes. column 0 and 6 provide a buffer either side
	    ColumnConstraints mainPageColumn0 = new ColumnConstraints(); 
	    mainPageColumn0.setPercentWidth(3); 
	    ColumnConstraints mainPageColumn1 = new ColumnConstraints();
	    mainPageColumn1.setPercentWidth(28);
	    ColumnConstraints mainPageColumn2 = new ColumnConstraints(); 
	    mainPageColumn2.setPercentWidth(5); 
	    ColumnConstraints mainPageColumn3 = new ColumnConstraints(); 
	    mainPageColumn3.setPercentWidth(45); 
	    ColumnConstraints mainPageColumn4 = new ColumnConstraints();
	    mainPageColumn4.setPercentWidth(3);
	    ColumnConstraints mainPageColumn5 = new ColumnConstraints();
	    mainPageColumn5.setPercentWidth(13);
	    ColumnConstraints mainPageColumn6 = new ColumnConstraints();
	    mainPageColumn6.setPercentWidth(3);
	    mainPageGrid.getColumnConstraints().addAll(mainPageColumn0, mainPageColumn1, mainPageColumn2,mainPageColumn3,mainPageColumn4,mainPageColumn5,mainPageColumn6);
	    
	    //sets the percentage of the page that should be taken up by each row
	    RowConstraints mainPageRow1 = new RowConstraints(); 
	    mainPageRow1.setPercentHeight(5); 
	    RowConstraints mainPageRow2 = new RowConstraints();
	    mainPageRow2.setPercentHeight(10);
	    RowConstraints mainPageRow3 = new RowConstraints(); 
	    mainPageRow3.setPercentHeight(80); 
	    RowConstraints mainPageRow4 = new RowConstraints();
	    mainPageRow4.setPercentHeight(5);
	    mainPageGrid.getRowConstraints().addAll(mainPageRow1, mainPageRow2,mainPageRow3,mainPageRow4);
	    
	    
		Label notificationLabel = new Label("Notificaitons"); // Title above the notification list
		notificationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
		mainPageGrid.add(notificationLabel,1,1);
		mainPageGrid.setHalignment(notificationLabel, HPos.CENTER);//Within the grid position it is currently in, centers the label
		
		ObservableList<String> notificationList = FXCollections.observableArrayList();
		
		TableView<Notification> notificationTable = new TableView<>();
		TableColumn<Notification, String> notificationTextColumn = new TableColumn<>("Notification");
		notificationTextColumn.setCellValueFactory(new PropertyValueFactory<>("notificationText"));
		notificationTextColumn.setMinWidth(450);
		TableManagement.textWrapTableColumnOTJ(notificationTextColumn,440);
		notificationTable.getColumns().add(notificationTextColumn);
		mainPageGrid.add(notificationTable, 1, 2);
		
		
		Label taskListTitle = new Label("Task List"); // Title above the task list
		taskListTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
		mainPageGrid.add(taskListTitle,3,1);
		mainPageGrid.setHalignment(taskListTitle, HPos.CENTER); //Within the grid position it is currently in, centers the label
		
		//defines the table that will hold the tasks - TableView uses an object and the classes getter and setter methods
		TableView<Task> TaskTable = new TableView<>();
		
		TableColumn<Task, String> TaskTextColumn = new TableColumn<>("Task");
		TaskTextColumn.setCellValueFactory(new PropertyValueFactory<>("taskText")); //defines the attribute of the object that is searched for for the column
		TaskTextColumn.setMinWidth(600);
		TableManagement.textWrapTableColumnOTJ(TaskTextColumn,590);
		TableColumn<Task, Date> TaskDueDateColumn = new TableColumn<>("Due Date");
		TaskDueDateColumn.setCellValueFactory(new PropertyValueFactory<>("taskDueDate"));
		TaskDueDateColumn.setMinWidth(120);
		TaskTable.getColumns().addAll(TaskTextColumn,TaskDueDateColumn);
		mainPageGrid.add(TaskTable, 3, 2);
		ObservableList<Task> taskList = FXCollections.observableArrayList();
		TableImport.TaskListImport(taskList, currentUser.getUserID());
		TaskTable.setItems(taskList);
		
		VBox taskButtonContainer = new VBox();
		mainPageGrid.add(taskButtonContainer, 5, 2);
		taskButtonContainer.setSpacing(35);
		Button addTaskButton = new Button("Add Task");
		addTaskButton.setMinHeight(50);
		addTaskButton.setMinWidth(200);
		addTaskButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		Button editTaskButton = new Button("Edit Task");
		editTaskButton.setMinHeight(50);
		editTaskButton.setMinWidth(200);
		editTaskButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		Button completeTaskButton = new Button("Complete Task");
		completeTaskButton.setMinHeight(50);
		completeTaskButton.setMinWidth(200);
		completeTaskButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		taskButtonContainer.getChildren().addAll(addTaskButton,editTaskButton,completeTaskButton);
		
		//--------------------------------------------
		//---------- OTJ Timesheet Page ----------
		//--------------------------------------------
		
		GridPane OTJGrid = new GridPane(); //defines the grid pane that will contain the OTJ Timesheet screen
		
	    ColumnConstraints OTJPageColumn0 = new ColumnConstraints(); 
	    OTJPageColumn0.setPercentWidth(3); 
	    ColumnConstraints OTJPageColumn1 = new ColumnConstraints();
	    OTJPageColumn1.setPercentWidth(5);
	    ColumnConstraints OTJPageColumn2 = new ColumnConstraints(); 
	    OTJPageColumn2.setPercentWidth(76); 
	    ColumnConstraints OTJPageColumn3 = new ColumnConstraints(); 
	    OTJPageColumn3.setPercentWidth(3); 
	    ColumnConstraints OTJPageColumn4 = new ColumnConstraints();
	    OTJPageColumn4.setPercentWidth(10); 
	    ColumnConstraints OTJPageColumn5 = new ColumnConstraints();
	    OTJPageColumn5.setPercentWidth(3);
	    OTJGrid.getColumnConstraints().addAll(OTJPageColumn0, OTJPageColumn1, OTJPageColumn2,OTJPageColumn3,OTJPageColumn4,OTJPageColumn5);
	    
	    RowConstraints OTJPageRow1 = new RowConstraints();
	    OTJPageRow1.setPercentHeight(8);
	    RowConstraints OTJPageRow2 = new RowConstraints(); 
	    OTJPageRow2.setPercentHeight(87); 
	    RowConstraints OTJPageRow3 = new RowConstraints();
	    OTJPageRow3.setPercentHeight(5);
	    OTJGrid.getRowConstraints().addAll(OTJPageRow1, OTJPageRow2,OTJPageRow3);
		
		Label OTJIdentifier = new Label("OTJ Timesheets"); 
		OTJGrid.add(OTJIdentifier,0,0,5,1);
		OTJIdentifier.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		OTJGrid.setHalignment(OTJIdentifier, HPos.CENTER);
		
		VBox monthAndYearBox = new VBox();
		OTJGrid.add(monthAndYearBox, 1, 1);
		
		ObservableList<String> OTJYearList = FXCollections.observableArrayList("Year 1","Year 2","Year 3","Year 4");
		ComboBox OTJYearDropdown = new ComboBox(OTJYearList);
		OTJYearDropdown.getSelectionModel().selectFirst();
		OTJYearDropdown.minWidth(70);
		monthAndYearBox.getChildren().add(OTJYearDropdown);
		
		
		ObservableList<String> monthList = FXCollections.observableArrayList("September","October","November","December","January","Febuary","March","April","May","June","July","August");
		ListView<String> OTJMonthList = new ListView<String>(monthList);
		OTJMonthList.prefHeightProperty().bind(Bindings.size(monthList).multiply(23.1)); // reduces the size of the list to remove empty entries (without this the list would extend with empty items reducing UI appeal)// used as .maxheight() does not appear to effect ListView in this instance
		monthAndYearBox.getChildren().add(OTJMonthList);
		
		TableView<OTJ> OTJTimesheetTable = new TableView<>();
		
		TableColumn<OTJ, String> OTJDayType = new TableColumn<>("Type of Day");
		OTJDayType.setCellValueFactory(new PropertyValueFactory<>("dayType"));
		OTJDayType.setMinWidth(80);
		TableManagement.textWrapTableColumnOTJ(OTJDayType,70);
		TableColumn<OTJ, String> OTJKeyLearning = new TableColumn<>("Other development learning activity: Action Plan");
		OTJKeyLearning.setCellValueFactory(new PropertyValueFactory<>("actionPlan"));
		OTJKeyLearning.setMinWidth(300);
		TableManagement.textWrapTableColumnOTJ(OTJKeyLearning,290);//calls class method to wrap text in long answer areas (better for readability)
		TableColumn<OTJ, String> OTJAchieveGoal = new TableColumn<>("What hoping to achieve");
		OTJAchieveGoal.setCellValueFactory(new PropertyValueFactory<>("achieveGoal"));
		OTJAchieveGoal.setMinWidth(300);
		TableManagement.textWrapTableColumnOTJ(OTJAchieveGoal,290);//calls class method to wrap text in long answer areas (better for readability)
		TableColumn<OTJ, String> OTJLearningAchieved = new TableColumn<>("Actual learning achieved");
		OTJLearningAchieved.setCellValueFactory(new PropertyValueFactory<>("learningAchieved"));
		OTJLearningAchieved.setMinWidth(295);
		TableManagement.textWrapTableColumnOTJ(OTJLearningAchieved,285); //calls class method to wrap text in long answer areas (better for readability)
		TableColumn<OTJ, Date> OTJActivityDate = new TableColumn<>("Date of activity");
		OTJActivityDate.setCellValueFactory(new PropertyValueFactory<>("activityDate"));
		OTJActivityDate.setMinWidth(120);
		TableColumn<OTJ, Date> OTJHours = new TableColumn<>("Hours completed");
		OTJHours.setCellValueFactory(new PropertyValueFactory<>("hoursCompleted"));
		OTJHours.setMinWidth(120);
		
		OTJTimesheetTable.getColumns().addAll(OTJDayType,OTJKeyLearning,OTJAchieveGoal,OTJLearningAchieved,OTJActivityDate,OTJHours);
		OTJGrid.add(OTJTimesheetTable, 2, 1);
		
		//list that will contain OTJ data
		ObservableList<OTJ> OTJDataList = FXCollections.observableArrayList();
		
		//updates data when new month is selected
		OTJMonthList.getSelectionModel().selectedItemProperty().addListener(e->{
			String currentlySelectedYear = String.valueOf(OTJYearDropdown.getValue());
			int selectedYear = Integer.parseInt(currentlySelectedYear.replaceAll("[^\\d.]", ""));//convert to int->(removes all non int characters)
			OTJDataList.clear();
			TableImport.OTJImport(OTJDataList, currentUser.getUserID(), OTJMonthList.getSelectionModel().getSelectedItem(), selectedYear);
			OTJTimesheetTable.refresh(); //AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED
			OTJTimesheetTable.setItems(OTJDataList);
		});
		//updates data when new value from dropdown is detected (as opposed to a listener which would run before the new value can be obtained)
		OTJYearDropdown.valueProperty().addListener(e->{
			String currentlySelectedYear = String.valueOf(OTJYearDropdown.getValue());
			int selectedYear = Integer.parseInt(currentlySelectedYear.replaceAll("[^\\d.]", ""));//convert to int->(removes all non int characters)
			OTJDataList.clear();
			TableImport.OTJImport(OTJDataList, currentUser.getUserID(), OTJMonthList.getSelectionModel().getSelectedItem(), selectedYear);
			OTJTimesheetTable.refresh();//AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED
			OTJTimesheetTable.setItems(OTJDataList);
		});
		
		
		
		VBox OTJButtonBox = new VBox();
		OTJButtonBox.setSpacing(50);
		OTJGrid.add(OTJButtonBox,4, 1);
		Label OTJHoursCalculator = new Label("Hours Completed: " + OTJ.getOTJHoursCompleted(currentUser)+"\nHours Remaining: "+ OTJ.getOTJHoursRemaining(currentUser));
		OTJHoursCalculator.setFont(Font.font("Tahoma", FontWeight.NORMAL, 13));

		Button addOTJEntryButton = new Button("Add Entry");
		addOTJEntryButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		addOTJEntryButton.setMinHeight(50);
		addOTJEntryButton.setMinWidth(150);
		
		Button editOTJEntryButton = new Button("Edit Entry");
		editOTJEntryButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		editOTJEntryButton.setMinHeight(50);
		editOTJEntryButton.setMinWidth(150);
		
		OTJButtonBox.getChildren().addAll(addOTJEntryButton,OTJHoursCalculator,editOTJEntryButton);
		
		
		//--------------------------------------------
		//---------- EPA Document Page ----------
		//--------------------------------------------
		
		GridPane EPAGrid = new GridPane(); //defines the grid pane that will contain the EPA Mapping Document screen
			
		ColumnConstraints EPAPageColumn0 = new ColumnConstraints(); 
	    EPAPageColumn0.setPercentWidth(3); 
	    ColumnConstraints EPAPageColumn1 = new ColumnConstraints();
	    EPAPageColumn1.setPercentWidth(15);
	    ColumnConstraints EPAPageColumn2 = new ColumnConstraints(); 
	    EPAPageColumn2.setPercentWidth(70); 
	    ColumnConstraints EPAPageColumn3 = new ColumnConstraints(); 
	    EPAPageColumn3.setPercentWidth(2); 
	    ColumnConstraints EPAPageColumn4 = new ColumnConstraints();
	    EPAPageColumn4.setPercentWidth(8); 
	    ColumnConstraints EPAPageColumn5 = new ColumnConstraints();
	    EPAPageColumn5.setPercentWidth(2);
	    EPAGrid.getColumnConstraints().addAll(EPAPageColumn0, EPAPageColumn1, EPAPageColumn2,EPAPageColumn3,EPAPageColumn4,EPAPageColumn5);
	    
	    RowConstraints EPAPageRow0 = new RowConstraints();
	    EPAPageRow0.setPercentHeight(8);
	    RowConstraints EPAPageRow1 = new RowConstraints(); 
	    EPAPageRow1.setPercentHeight(87); 
	    RowConstraints EPAPageRow2 = new RowConstraints();
	    EPAPageRow2.setPercentHeight(5);
	    EPAGrid.getRowConstraints().addAll(EPAPageRow0,EPAPageRow1, EPAPageRow2);
	    
	    
		Label EPAIdentifier = new Label("EPA Page");
		EPAGrid.add(EPAIdentifier,0,0,6,1);
		EPAIdentifier.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		EPAGrid.setHalignment(EPAIdentifier, HPos.CENTER);
		
		ObservableList<String> EPASections = FXCollections.observableArrayList("Core Skills","Core Technical Knowledge","Core Behavioural Skills","Software Engineer","IT Consultant / Business Management","Business Analysis","Data Analyst","Cyber Security Specialist","Network Engineer Specialist");
		ListView<String> EPASectionList = new ListView<String>(EPASections);
		EPAGrid.add(EPASectionList, 1, 1);
		
		TableView<EPA> EPATimesheetTable = new TableView<>();
		EPATimesheetTable.setEditable(true);
		
		TableColumn<EPA, String> EPAAreaColumn = new TableColumn<EPA, String>("Area");
		EPAAreaColumn.setCellValueFactory(new PropertyValueFactory<EPA, String>("EpaArea"));
		EPAAreaColumn.setMinWidth(220);
		TableManagement.textWrapTableColumnEPA(EPAAreaColumn,210);
		TableColumn<EPA, String> EPADescriptionColumn = new TableColumn<EPA, String>("Description");
		EPADescriptionColumn.setCellValueFactory(new PropertyValueFactory<EPA, String>("EPASectionDescription"));
		EPADescriptionColumn.setMinWidth(300);
		TableManagement.textWrapTableColumnEPA(EPADescriptionColumn, 290);
		TableColumn<EPA, String> EPAAcademicEvidenceColumn = new TableColumn<EPA, String>("Evidence from Academic Studies");
		EPAAcademicEvidenceColumn.setCellValueFactory(new PropertyValueFactory<EPA, String>("AcademicEvidence"));
		EPAAcademicEvidenceColumn.setMinWidth(300);

		TableManagement.textWrapTableColumnEPA(EPAAcademicEvidenceColumn, 290);
		EPAAcademicEvidenceColumn.setCellFactory(TextFieldTableCell.<EPA>forTableColumn());
		TableColumn<EPA, String> EPAWorkplaceEvidenceColumn = new TableColumn<EPA, String>("Evidence from Workplace");
		EPAWorkplaceEvidenceColumn.setCellValueFactory(new PropertyValueFactory<EPA, String>("WorkplaceEvidence"));
		EPAWorkplaceEvidenceColumn.setMinWidth(300);
		TableManagement.textWrapTableColumnEPA(EPAWorkplaceEvidenceColumn, 290);
		
		EPATimesheetTable.getColumns().addAll(EPAAreaColumn,EPADescriptionColumn,EPAAcademicEvidenceColumn,EPAWorkplaceEvidenceColumn);
		EPAGrid.add(EPATimesheetTable, 2, 1);
		
		// ADDING DATA TO THE TABLE
		ObservableList<EPA> EPATestList = FXCollections.observableArrayList();
		EPASectionList.getSelectionModel().selectedItemProperty().addListener(e->{
			
			TableImport.EPAImport(EPATestList, EPASectionList.getSelectionModel().getSelectedItem(),currentUser);
			EPATimesheetTable.setItems(EPATestList);
		});
		
		
		//PAGE BUTTONS
		
		VBox EPAPageButtonVBox = new VBox();
		EPAPageButtonVBox.setSpacing(20);
		EPAGrid.add(EPAPageButtonVBox, 4, 1);
		
		Button JumpToEPAFAQButton = new Button ("EPA Help");
		JumpToEPAFAQButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		JumpToEPAFAQButton.setMinHeight(40);
		JumpToEPAFAQButton.setMinWidth(120);
		
		Button EPAEditEntryButton = new Button ("Edit Selected");
		EPAEditEntryButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		EPAEditEntryButton.setMinHeight(40);
		EPAEditEntryButton.setMinWidth(120);
		
		EPAPageButtonVBox.getChildren().addAll(JumpToEPAFAQButton,EPAEditEntryButton);
		
		
		//--------------------------------------------
		//---------- FAQ Page ----------
		//--------------------------------------------
		
		GridPane FAQGrid = new GridPane(); //defines the grid pane that will contain the FAQ screen
		
		ColumnConstraints FAQPageColumn0 = new ColumnConstraints(); 
	    FAQPageColumn0.setPercentWidth(3); 
	    ColumnConstraints FAQPageColumn1 = new ColumnConstraints();
	    FAQPageColumn1.setPercentWidth(30); 
	    ColumnConstraints FAQPageColumn2 = new ColumnConstraints(); 
	    FAQPageColumn2.setPercentWidth(64); 
	    ColumnConstraints FAQPageColumn3 = new ColumnConstraints(); 
	    FAQPageColumn3.setPercentWidth(3); 
	    FAQGrid.getColumnConstraints().addAll(FAQPageColumn0, FAQPageColumn1, FAQPageColumn2,FAQPageColumn3);
		
	    RowConstraints FAQPageRow1 = new RowConstraints(); 
	    FAQPageRow1.setPercentHeight(5); 
	    RowConstraints FAQPageRow2 = new RowConstraints();
	    FAQPageRow2.setPercentHeight(10);
	    RowConstraints FAQPageRow3 = new RowConstraints(); 
	    FAQPageRow3.setPercentHeight(80); 
	    RowConstraints FAQPageRow4 = new RowConstraints();
	    FAQPageRow4.setPercentHeight(5);
	    FAQGrid.getRowConstraints().addAll(FAQPageRow1, FAQPageRow2,FAQPageRow3,FAQPageRow4);
		
	    
		
		Label FAQIdentifier = new Label("FAQ Page");
		FAQIdentifier.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		FAQGrid.add(FAQIdentifier,0,0,4,2);
		FAQGrid.setHalignment(FAQIdentifier, HPos.CENTER);
		
		TextArea FAQIntroText = new TextArea("Welcome to the FAQ!\n\nPlease select a topic from the list to find out more."); 
		FAQIntroText.setEditable(false);//stops the text area from being typed in by the user // this would not edit the stored text but is a feature that is not desirable for the application
		FAQIntroText.setWrapText(true);
		FAQGrid.add(FAQIntroText,2,2);
		
		ObservableList<String> topicList = FXCollections.observableArrayList("EPA Mapping Document","Apprenticeship Structure","Dissertation Structure","OTJ Timesheets", "Key Contacts");
		ListView<String> FAQTopicList = new ListView<String>(topicList);
		FAQGrid.add(FAQTopicList, 1, 2);
		
		FAQTopicList.setOnMouseClicked(e->{ //event executes on mouse click of the list
			
			String currentFAQListSelection = FAQTopicList.getSelectionModel().getSelectedItem(); //gets the currently selected item from list and assigns it to a variable
			
			switch (currentFAQListSelection) {//based upon the list item currently selected, change the text to the relevant section // text is stored within the class InfoRepository
			
			case "EPA Mapping Document":
				FAQIntroText.setText(InfoRepository.getEPADetails());
				break;//break out of switch case after info has been updated
			case "Apprenticeship Structure":
				FAQIntroText.setText(InfoRepository.getApprenticeshipDetails());
				break;
			case "Dissertation Structure":
				FAQIntroText.setText(InfoRepository.getDissertationDetails());
				break;
			case "OTJ Timesheets":
				FAQIntroText.setText(InfoRepository.getOTJDetails());
				break;
			case "Key Contacts":
				FAQIntroText.setText(InfoRepository.getContactDetails());
				break;
			}
			
		});
		
		//--------------------------------------------------
		//----------  Button Functions ----------
		// placed here as the grids need to be defined first
		//--------------------------------------------------
		
		EventHandler<ActionEvent> goToHomePage = new EventHandler<ActionEvent>() { //defines new event variable
			
			public void handle(ActionEvent e) {
				mainPageBorder.setCenter(mainPageGrid); //sets the main page as the grid in the center of the screen replacing what is currently there. If it is the same page, the page will be reloaded
				taskList.clear();
				TableImport.TaskListImport(taskList, currentUser.getUserID());
				TaskTable.setItems(taskList);
				TaskTable.refresh();//AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED (issue originating from wrapping text)
				Notification.generateNotifications(notificationTable,notificationList, currentUser);
				notificationTable.refresh();
			}
			
		};		
		goToHomePageButton.setOnAction(goToHomePage); //assigns the event to occur when the button is pressed
		
		
		EventHandler<ActionEvent> goToOTJPage = new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				mainPageBorder.setCenter(OTJGrid); //sets the OTJ page as the grid in the center of the screen replacing what is currently there. If it is the same page, the page will be reloaded
				OTJMonthList.getSelectionModel().select(0);
			}
			
		};		
		goToOTJPageButton.setOnAction(goToOTJPage);//assigns the event to occur when the button is pressed
		
		
		EventHandler<ActionEvent> goToEPAPage = new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				mainPageBorder.setCenter(EPAGrid); //sets the EPA Page as the grid in the center of the screen replacing what is currently there. If it is the same page, the page will be reloaded
				EPASectionList.getSelectionModel().select(0); //loads the core skills section by default
				
			}
			
		};		
		goToEPAPageButton.setOnAction(goToEPAPage);//assigns the event to occur when the button is pressed
		
		EventHandler<ActionEvent> goToFAQPage = new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				mainPageBorder.setCenter(FAQGrid); //sets the FAQ page as the grid in the center of the screen replacing what is currently there. If it is the same page, the page will be reloaded
			}
			
		};		
		goToFAQPageButton.setOnAction(goToFAQPage);//assigns the event to occur when the button is pressed
		
		//-------------------EPA PAGE BUTTON EVENTS
		
		
		EventHandler<ActionEvent> jumpToEPAFAQ = new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				mainPageBorder.setCenter(FAQGrid); 
				FAQTopicList.getSelectionModel().select(0);//0 is the index of the EPA section
				FAQIntroText.setText(InfoRepository.getEPADetails()); 
			}
			
		};		
		JumpToEPAFAQButton.setOnAction(jumpToEPAFAQ);//assigns the event to occur when the button is pressed
		
		
		
		EventHandler<ActionEvent> editEPAEntry = new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				if (EPATimesheetTable.getSelectionModel().getSelectedItem() != null){
					
					String selectedQuestion = EPATimesheetTable.getSelectionModel().getSelectedItem().getEPASectionDescription();
					String selectedAcademicEvidence = EPATimesheetTable.getSelectionModel().getSelectedItem().getAcademicEvidence();
					String selectedWorkplaceEvidence = EPATimesheetTable.getSelectionModel().getSelectedItem().getWorkplaceEvidence();
					
					//defining the new window for editing EPA
					
					Stage editEPAStage = new Stage();
					editEPAStage.initOwner(primaryStage);//sets this new stage as a child of primaryStage - allows for moda-lity
					editEPAStage.initModality(Modality.APPLICATION_MODAL); //sets stage so that it must be closed before main window can be interacted with again
					
					BorderPane editEPABorder = new BorderPane();
					Scene editEPAScene = new Scene(editEPABorder, 1280,720);
					editEPAStage.setScene(editEPAScene);
					editEPAStage.setTitle("Edit EPA Document");
					editEPAStage.show();
					
					GridPane editEPAGrid = new GridPane(); //defines the grid pane that will contain the FAQ screen
					editEPABorder.setCenter(editEPAGrid);
					
					ColumnConstraints editEPAPageColumn0 = new ColumnConstraints(); 
				    editEPAPageColumn0.setPercentWidth(3); 
				    ColumnConstraints editEPAPageColumn1 = new ColumnConstraints();
				    editEPAPageColumn1.setPercentWidth(44); 
				    ColumnConstraints editEPAPageColumn2 = new ColumnConstraints(); 
				    editEPAPageColumn2.setPercentWidth(6); 
				    ColumnConstraints editEPAPageColumn3 = new ColumnConstraints(); 
				    editEPAPageColumn3.setPercentWidth(44); 
				    ColumnConstraints editEPAPageColumn4 = new ColumnConstraints(); 
				    editEPAPageColumn4.setPercentWidth(3); 
				    editEPAGrid.getColumnConstraints().addAll(editEPAPageColumn0, editEPAPageColumn1, editEPAPageColumn2,editEPAPageColumn3,editEPAPageColumn4);
					
				    RowConstraints editEPAPageRow0 = new RowConstraints(); 
				    editEPAPageRow0.setPercentHeight(15); 
				    RowConstraints editEPAPageRow1 = new RowConstraints(); 
				    editEPAPageRow1.setPercentHeight(10); 
				    RowConstraints editEPAPageRow2 = new RowConstraints();
				    editEPAPageRow2.setPercentHeight(60);
				    RowConstraints editEPAPageRow3 = new RowConstraints();
				    editEPAPageRow3.setPercentHeight(15);
	
				    editEPAGrid.getRowConstraints().addAll(editEPAPageRow0,editEPAPageRow1, editEPAPageRow2,editEPAPageRow3);
				    
				    Label questionHeading = new Label("Question Description: " + selectedQuestion);
				    questionHeading.setWrapText(true);
				    questionHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
				    editEPAGrid.add(questionHeading,1,0,3,1);
				    editEPAGrid.setHalignment(questionHeading, HPos.CENTER);
				    
				    Label academicEvidenceTextboxHeading = new Label("Academic Evidence:");
				    academicEvidenceTextboxHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
				    editEPAGrid.add(academicEvidenceTextboxHeading,1,1);
				    editEPAGrid.setHalignment(academicEvidenceTextboxHeading, HPos.CENTER);
				    
					TextArea academicEvidenceTextbox = new TextArea(selectedAcademicEvidence); 
					editEPAGrid.add(academicEvidenceTextbox,1,2);
					
				    Label workplaceEvidenceTextboxHeading = new Label("Workplace Evidence:");
				    workplaceEvidenceTextboxHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
				    editEPAGrid.add(workplaceEvidenceTextboxHeading,3,1);
				    editEPAGrid.setHalignment(workplaceEvidenceTextboxHeading, HPos.CENTER);
					
					TextArea workplaceEvidenceTextbox = new TextArea(selectedWorkplaceEvidence); 
					editEPAGrid.add(workplaceEvidenceTextbox,3,2);
					
					Button submitEPAChangesButton = new Button("Submit Changes");
					submitEPAChangesButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
					editEPAGrid.add(submitEPAChangesButton, 1, 3);
					editEPAGrid.setHalignment(submitEPAChangesButton, HPos.CENTER);
					Button cancelEPAChangesButton = new Button("Cancel Changes");
					cancelEPAChangesButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
					editEPAGrid.add(cancelEPAChangesButton, 3, 3);
					editEPAGrid.setHalignment(cancelEPAChangesButton, HPos.CENTER);
	
			
				EventHandler<ActionEvent> submitEPAChanges = new EventHandler<ActionEvent>() {
					
					public void handle(ActionEvent e) {
						String newAcademicEvidence = academicEvidenceTextbox.getText();
						String newWorkplaceEvidence = workplaceEvidenceTextbox.getText();
						
						EPA.updateAcademicEvidence((EPASectionList.getSelectionModel().getSelectedItem()),EPATimesheetTable.getSelectionModel().getSelectedItem().getEPASectionDescription() ,newAcademicEvidence , newWorkplaceEvidence, currentUser);
						
						//CLOSE WINDOW
						editEPAStage.close();
						
						//REFRESH TABLE
						TableImport.EPAImport(EPATestList, EPASectionList.getSelectionModel().getSelectedItem(),currentUser);
						EPATimesheetTable.setItems(EPATestList);
						EPATimesheetTable.refresh();
	
					}
				};		
				submitEPAChangesButton.setOnAction(submitEPAChanges);//assigns the event to occur when the button is pressed
					
					
				EventHandler<ActionEvent> cancelEPAChanges = new EventHandler<ActionEvent>() {
					
					public void handle(ActionEvent e) {
						editEPAStage.close(); //exits without saving
					}
					
				};		
				cancelEPAChangesButton.setOnAction(cancelEPAChanges);//assigns the event to occur when the button is pressed
				}else {
					
					Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
					noEPASelectedWarning.setTitle("WARNING");
					noEPASelectedWarning.setHeaderText("No EPA section has been selected");
					noEPASelectedWarning.setContentText("Please select a question from the table to begin editing");
					noEPASelectedWarning.showAndWait();
				}
				

				
		}
		};
			EventHandler<ActionEvent> login = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					if ((User.loginValidationTest(usernameEntry.getText(),passwordEntry.getText(),currentUser))==true) {
						OTJHoursCalculator.setText("Hours Completed: " + OTJ.getOTJHoursCompleted(currentUser)+"\nHours Remaining: "+ OTJ.getOTJHoursRemaining(currentUser)); //this updates the calculator on the OTJ Page - must run here as only after logging in is the current users attributes imported into the program
						primaryStage.setScene(mainScene);
						taskList.clear();
						TableImport.TaskListImport(taskList, currentUser.getUserID());
						TaskTable.setItems(taskList);
						TaskTable.refresh();//AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED (issue originating from wrapping text)
						Notification.generateNotifications(notificationTable,notificationList, currentUser);//generates notification, runs on login as this is where the currentUser is defined and main page is loaded
						notificationTable.refresh();
					}else {
						Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
						noEPASelectedWarning.setTitle("WARNING");
						noEPASelectedWarning.setHeaderText("Invalid Credentials");
						noEPASelectedWarning.setContentText("Please enter valid credentials to access the system");
						noEPASelectedWarning.showAndWait();
					}
				}
				
			};		
			logInButton.setOnAction(login);//assigns the event to occur when the button is pressed
				
			
			EventHandler<ActionEvent> goToNewAccountPage = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {

						primaryStage.setScene(createAccountScene);

					}
				

			};		
			createAccountButton.setOnAction(goToNewAccountPage);//assigns the event to occur when the button is pressed
			
			EventHandler<ActionEvent> setTaskAsComplete = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {

						//delete task from database
					if (TaskTable.getSelectionModel().getSelectedItem()== null) {
						Alert noTaskSelectedAlert = new Alert(AlertType.WARNING);
						noTaskSelectedAlert.setTitle("WARNING");
						noTaskSelectedAlert.setHeaderText("No task selected");
						noTaskSelectedAlert.setContentText("Please ensure that a task is selected for it to be completed");
						noTaskSelectedAlert.showAndWait();
					}else {
					
					Task.completeTask(TaskTable.getSelectionModel().getSelectedItem().getTaskID());
					taskList.clear();
					TableImport.TaskListImport(taskList, currentUser.getUserID());
					TaskTable.setItems(taskList);
					TaskTable.refresh();//AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED (issue originating from wrapping text)
					Notification.generateNotifications(notificationTable,notificationList, currentUser);
					notificationTable.refresh();
					}
					}
			};
			completeTaskButton.setOnAction(setTaskAsComplete);
			
			
			EventHandler<ActionEvent> createAccountButtonFunction = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
						
						
						if(User.isUsernameUnique(newUsernameEntry.getText())==false){
							Alert usernameTakenAlert = new Alert(AlertType.WARNING);
							usernameTakenAlert.setTitle("WARNING");
							usernameTakenAlert.setHeaderText("Username Taken");
							usernameTakenAlert.setContentText("The entered username is already taken. \nPlease choose a new username.");
							usernameTakenAlert.showAndWait();
					
						}else if (studentNumberEntry.getText().matches("^[0-9]*$") == true && newUsernameEntry.getText() != "" && newPasswordEntry.getText() != ""&& enterFirstNameEntry.getText() != ""&& enterSurnameEntry.getText() != ""&& studentNumberEntry.getText() != "" && pathwayComboBox.getValue()!= null && enterCompanyEntry.getText() != ""&& enterLineManagerEntry.getText() != "") {
							
							User.createUserInDatabase(newUsernameEntry.getText(), newPasswordEntry.getText(), enterFirstNameEntry.getText(), enterSurnameEntry.getText(),Integer.parseInt(studentNumberEntry.getText()), String.valueOf(pathwayComboBox.getValue()), enterCompanyEntry.getText(), enterLineManagerEntry.getText());
							int newUserID = User.findUserIDBasedOnUsernameAndPassword(newUsernameEntry.getText(), newPasswordEntry.getText());
							User.createEPASectionsInDatabase(newUserID);
							
							primaryStage.setScene(loginScene);
							
							
							
						}else if (studentNumberEntry.getText().matches("^[0-9]*$") == false) { //if student number entry is not an integer...
							Alert accCreationStuNumNotIntAlert = new Alert(AlertType.WARNING);
							accCreationStuNumNotIntAlert.setTitle("WARNING");
							accCreationStuNumNotIntAlert.setHeaderText("Invalid Entry");
							accCreationStuNumNotIntAlert.setContentText("Please ensure that the student number contains integers only");
							accCreationStuNumNotIntAlert.showAndWait();
							
						}else {
							Alert accCreationMissingFieldsAlert = new Alert(AlertType.WARNING);
							accCreationMissingFieldsAlert.setTitle("WARNING");
							accCreationMissingFieldsAlert.setHeaderText("Missing Credentials");
							accCreationMissingFieldsAlert.setContentText("Please ensure all entries are filled to create an account");
							accCreationMissingFieldsAlert.showAndWait();
						}
						
					
					}
	
			};		
			initialiseCreateAccountButton.setOnAction(createAccountButtonFunction);//assigns the event to occur when the button is pressed
			
			
			EventHandler<ActionEvent> cancelAccountCreation = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {

						primaryStage.setScene(loginScene);

					}
				
				
			};		
			cancelCreateAccountButton.setOnAction(cancelAccountCreation);//assigns the event to occur when the button is pressed
			

			
			EventHandler<ActionEvent> addTaskAction = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {

					Stage amendTaskStage = new Stage();
					amendTaskStage.initOwner(primaryStage);//sets this new stage as a child of primaryStage - allows for modelity
					amendTaskStage.initModality(Modality.APPLICATION_MODAL); //sets stage so that it must be closed before main window can be interacted with again
					
					BorderPane amendTaskBorder = new BorderPane();
					Scene amendTaskScene = new Scene(amendTaskBorder, 1280,400);
					amendTaskStage.setScene(amendTaskScene);
					amendTaskStage.setTitle("Update Task");
					amendTaskStage.show();
						
					GridPane amendTaskGrid = new GridPane(); //defines the grid pane that will contain the task amendment screen
					amendTaskBorder.setCenter(amendTaskGrid);
					
					ColumnConstraints amendTaskPageColumn0 = new ColumnConstraints(); 
				    amendTaskPageColumn0.setPercentWidth(3); 
				    ColumnConstraints amendTaskPageColumn1 = new ColumnConstraints();
				    amendTaskPageColumn1.setPercentWidth(60); 
				    ColumnConstraints amendTaskPageColumn2 = new ColumnConstraints(); 
				    amendTaskPageColumn2.setPercentWidth(4); 
				    ColumnConstraints amendTaskPageColumn3 = new ColumnConstraints(); 
				    amendTaskPageColumn3.setPercentWidth(30); 
				    ColumnConstraints amendTaskPageColumn4 = new ColumnConstraints(); 
				    amendTaskPageColumn4.setPercentWidth(3); 
				    amendTaskGrid.getColumnConstraints().addAll(amendTaskPageColumn0, amendTaskPageColumn1, amendTaskPageColumn2,amendTaskPageColumn3,amendTaskPageColumn4);
					
				    RowConstraints amendTaskPageRow0 = new RowConstraints(); 
				    amendTaskPageRow0.setPercentHeight(5); 
				    RowConstraints amendTaskPageRow1 = new RowConstraints(); 
				    amendTaskPageRow1.setPercentHeight(15); 
				    RowConstraints amendTaskPageRow2 = new RowConstraints();
				    amendTaskPageRow2.setPercentHeight(5);
				    RowConstraints amendTaskPageRow3 = new RowConstraints();
				    amendTaskPageRow3.setPercentHeight(45);
				    RowConstraints amendTaskPageRow4 = new RowConstraints();
				    amendTaskPageRow4.setPercentHeight(15);
				    RowConstraints amendTaskPageRow5 = new RowConstraints();
				    amendTaskPageRow5.setPercentHeight(15);
				    amendTaskGrid.getRowConstraints().addAll(amendTaskPageRow0,amendTaskPageRow1,amendTaskPageRow2,amendTaskPageRow3,amendTaskPageRow4,amendTaskPageRow5);
					
					Label taskTextLabel = new Label("Task Text:");
					taskTextLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
					amendTaskGrid.add(taskTextLabel, 1, 1);
					amendTaskGrid.setHalignment(taskTextLabel, HPos.CENTER);
					
					TextArea taskTextArea = new TextArea();
					taskTextArea.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
					taskTextArea.setWrapText(true);
					amendTaskGrid.add(taskTextArea, 1, 3,1,2);
					
					Label taskDateLabel = new Label("Task Due Date:");
					taskDateLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
					amendTaskGrid.add(taskDateLabel, 3, 1);
					
					DatePicker taskDueDateSeclection = new DatePicker();
					amendTaskGrid.add(taskDueDateSeclection, 3, 3);
					amendTaskGrid.setValignment(taskDueDateSeclection, VPos.TOP);
					
					HBox taskWindowButtonHBox = new HBox();
					amendTaskGrid.add(taskWindowButtonHBox,3,4);
					
					Button submitTaskChanges = new Button("Submit");
					submitTaskChanges.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
					Button cancelTaskChanges = new Button("Cancel");
					cancelTaskChanges.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
					taskWindowButtonHBox.getChildren().addAll(submitTaskChanges,cancelTaskChanges);
					
					EventHandler<ActionEvent> cancelTaskAmendment = new EventHandler<ActionEvent>() {
						
						public void handle(ActionEvent e) {

							amendTaskStage.close();

							}

					};		
					
					cancelTaskChanges.setOnAction(cancelTaskAmendment);
					
					EventHandler<ActionEvent> commitEditTaskToDB = new EventHandler<ActionEvent>() {
						
						public void handle(ActionEvent e) {

							Task.submitNewTask(currentUser.getUserID(),taskTextArea.getText(),taskDueDateSeclection.getValue());
							amendTaskStage.close();
							taskList.clear();
							TableImport.TaskListImport(taskList, currentUser.getUserID());
							TaskTable.setItems(taskList);
							TaskTable.refresh();//AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED (issue originating from wrapping text)
							Notification.generateNotifications(notificationTable,notificationList, currentUser);
							notificationTable.refresh();

							}

					};
					
					submitTaskChanges.setOnAction(commitEditTaskToDB);;

					}
			};		
			addTaskButton.setOnAction(addTaskAction);//assigns the event to occur when the button is pressed
			
			EventHandler<ActionEvent> editTaskAction = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					
					if (TaskTable.getSelectionModel().getSelectedItem()==null){//no task selected
						
						Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
						noEPASelectedWarning.setTitle("WARNING");
						noEPASelectedWarning.setHeaderText("No task has been selected to edit");
						noEPASelectedWarning.setContentText("Please select the task to edit");
						noEPASelectedWarning.showAndWait();
						
					}else {
					
						Stage amendTaskStage = new Stage();
						amendTaskStage.initOwner(primaryStage);//sets this new stage as a child of primaryStage - allows for modelity
						amendTaskStage.initModality(Modality.APPLICATION_MODAL); //sets stage so that it must be closed before main window can be interacted with again
						
						BorderPane amendTaskBorder = new BorderPane();
						Scene amendTaskScene = new Scene(amendTaskBorder, 1280,400);
						amendTaskStage.setScene(amendTaskScene);
						amendTaskStage.setTitle("Update Task");
						amendTaskStage.show();
							
						GridPane amendTaskGrid = new GridPane(); 
						amendTaskBorder.setCenter(amendTaskGrid);
						
						ColumnConstraints amendTaskPageColumn0 = new ColumnConstraints(); 
					    amendTaskPageColumn0.setPercentWidth(3); 
					    ColumnConstraints amendTaskPageColumn1 = new ColumnConstraints();
					    amendTaskPageColumn1.setPercentWidth(60); 
					    ColumnConstraints amendTaskPageColumn2 = new ColumnConstraints(); 
					    amendTaskPageColumn2.setPercentWidth(4); 
					    ColumnConstraints amendTaskPageColumn3 = new ColumnConstraints(); 
					    amendTaskPageColumn3.setPercentWidth(30); 
					    ColumnConstraints amendTaskPageColumn4 = new ColumnConstraints(); 
					    amendTaskPageColumn4.setPercentWidth(3); 
					    amendTaskGrid.getColumnConstraints().addAll(amendTaskPageColumn0, amendTaskPageColumn1, amendTaskPageColumn2,amendTaskPageColumn3,amendTaskPageColumn4);
						
					    RowConstraints amendTaskPageRow0 = new RowConstraints(); 
					    amendTaskPageRow0.setPercentHeight(5); 
					    RowConstraints amendTaskPageRow1 = new RowConstraints(); 
					    amendTaskPageRow1.setPercentHeight(15); 
					    RowConstraints amendTaskPageRow2 = new RowConstraints();
					    amendTaskPageRow2.setPercentHeight(5);
					    RowConstraints amendTaskPageRow3 = new RowConstraints();
					    amendTaskPageRow3.setPercentHeight(45);
					    RowConstraints amendTaskPageRow4 = new RowConstraints();
					    amendTaskPageRow4.setPercentHeight(15);
					    RowConstraints amendTaskPageRow5 = new RowConstraints();
					    amendTaskPageRow5.setPercentHeight(15);
					    amendTaskGrid.getRowConstraints().addAll(amendTaskPageRow0,amendTaskPageRow1,amendTaskPageRow2,amendTaskPageRow3,amendTaskPageRow4,amendTaskPageRow5);
						
						Label taskTextLabel = new Label("Task Text:");
						taskTextLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						amendTaskGrid.add(taskTextLabel, 1, 1);
						amendTaskGrid.setHalignment(taskTextLabel, HPos.CENTER);
						
						TextArea taskTextArea = new TextArea(TaskTable.getSelectionModel().getSelectedItem().getTaskText());//sets text to current task text 
						taskTextArea.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						taskTextArea.setWrapText(true);
						amendTaskGrid.add(taskTextArea, 1, 3,1,2);
						
						Label taskDateLabel = new Label("Task Due Date:");
						taskDateLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						amendTaskGrid.add(taskDateLabel, 3, 1);
						
						DatePicker taskDueDateSeclection = new DatePicker();
						
						//converts Date to local date:   Date-->String-->LocalDate
						String taskDateDateDatatype = String.valueOf(TaskTable.getSelectionModel().getSelectedItem().getTaskDueDate());
						LocalDate taskDate = LocalDate.parse(taskDateDateDatatype);
						//end of conversion
						
						taskDueDateSeclection.setValue(taskDate);//sets date to current task date 
						amendTaskGrid.add(taskDueDateSeclection, 3, 3);
						amendTaskGrid.setValignment(taskDueDateSeclection, VPos.TOP);
						
						HBox taskWindowButtonHBox = new HBox();
						amendTaskGrid.add(taskWindowButtonHBox,3,4);
						
						Button submitTaskChanges = new Button("Submit");
						submitTaskChanges.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						Button cancelTaskChanges = new Button("Cancel");
						cancelTaskChanges.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						taskWindowButtonHBox.getChildren().addAll(submitTaskChanges,cancelTaskChanges);
						
						EventHandler<ActionEvent> cancelTaskAmendment = new EventHandler<ActionEvent>() {
							
							public void handle(ActionEvent e) {

								amendTaskStage.close();

								}

						};		
						
						cancelTaskChanges.setOnAction(cancelTaskAmendment);
						
						EventHandler<ActionEvent> commitEditTaskToDB = new EventHandler<ActionEvent>() {
							
							public void handle(ActionEvent e) {
								Task.submitTaskEdit(TaskTable.getSelectionModel().getSelectedItem().getTaskID(),currentUser.getUserID(), taskTextArea.getText(), taskDueDateSeclection.getValue());
								amendTaskStage.close();
								taskList.clear();
								TableImport.TaskListImport(taskList, currentUser.getUserID());
								TaskTable.setItems(taskList);
								TaskTable.refresh();//AVOIDS ISSUE WITH TEXT WRAPPING RETAINING 1 ENTRY OF PREVIOUS DATA WHEN RELOADED (issue originating from wrapping text)
								Notification.generateNotifications(notificationTable,notificationList, currentUser);
								notificationTable.refresh();
								}

						};
						
						submitTaskChanges.setOnAction(commitEditTaskToDB);;
					}
				}		

			};		
			editTaskButton.setOnAction(editTaskAction);//assigns the event to occur when the button is pressed

			
			EventHandler<ActionEvent> addOTJEntry = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					
					if (OTJMonthList.getSelectionModel().getSelectedItem() != null) {
					
						Stage newOTJEntryStage = new Stage();
						newOTJEntryStage.initOwner(primaryStage);//sets this new stage as a child of primaryStage - allows for modelity
						newOTJEntryStage.initModality(Modality.APPLICATION_MODAL); //sets stage so that it must be closed before main window can be interacted with again
						
						BorderPane newOTJEntryBorder = new BorderPane();
						Scene newOTJEntryScene = new Scene(newOTJEntryBorder, 1280,720);
						newOTJEntryStage.setScene(newOTJEntryScene);
						newOTJEntryStage.setTitle("Add New OTJ Entry");
						newOTJEntryStage.show();
							
						GridPane newOTJEntryGrid = new GridPane(); //defines the grid pane that will contain the FAQ screen
						newOTJEntryBorder.setCenter(newOTJEntryGrid);
						
						ColumnConstraints newOTJEntryPageColumn0 = new ColumnConstraints(); 
					    newOTJEntryPageColumn0.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn1 = new ColumnConstraints();
					    newOTJEntryPageColumn1.setPercentWidth(10); 
					    ColumnConstraints newOTJEntryPageColumn2 = new ColumnConstraints(); 
					    newOTJEntryPageColumn2.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn3 = new ColumnConstraints(); 
					    newOTJEntryPageColumn3.setPercentWidth(25); 
					    ColumnConstraints newOTJEntryPageColumn4 = new ColumnConstraints(); 
					    newOTJEntryPageColumn4.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn5 = new ColumnConstraints(); 
					    newOTJEntryPageColumn5.setPercentWidth(25); 
					    ColumnConstraints newOTJEntryPageColumn6 = new ColumnConstraints(); 
					    newOTJEntryPageColumn6.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn7 = new ColumnConstraints(); 
					    newOTJEntryPageColumn7.setPercentWidth(25); 
					    ColumnConstraints newOTJEntryPageColumn8 = new ColumnConstraints(); 
					    newOTJEntryPageColumn8.setPercentWidth(3); 
					    newOTJEntryGrid.getColumnConstraints().addAll(newOTJEntryPageColumn0, newOTJEntryPageColumn1, newOTJEntryPageColumn2,newOTJEntryPageColumn3,newOTJEntryPageColumn4,newOTJEntryPageColumn5,newOTJEntryPageColumn6,newOTJEntryPageColumn7,newOTJEntryPageColumn8);
						
					    RowConstraints newOTJEntryPageRow0 = new RowConstraints(); 
					    newOTJEntryPageRow0.setPercentHeight(3); 
					    RowConstraints newOTJEntryPageRow1 = new RowConstraints(); 
					    newOTJEntryPageRow1.setPercentHeight(10); 
					    RowConstraints newOTJEntryPageRow2 = new RowConstraints();
					    newOTJEntryPageRow2.setPercentHeight(49);
					    RowConstraints newOTJEntryPageRow3 = new RowConstraints();
					    newOTJEntryPageRow3.setPercentHeight(5);
					    RowConstraints newOTJEntryPageRow4 = new RowConstraints();
					    newOTJEntryPageRow4.setPercentHeight(20);
					    RowConstraints newOTJEntryPageRow5 = new RowConstraints();
					    newOTJEntryPageRow5.setPercentHeight(13);
					    newOTJEntryGrid.getRowConstraints().addAll(newOTJEntryPageRow0,newOTJEntryPageRow1,newOTJEntryPageRow2,newOTJEntryPageRow3,newOTJEntryPageRow4,newOTJEntryPageRow5);
					    
					    VBox newEPAMiscDetailsContainer = new VBox();
					    newEPAMiscDetailsContainer.setSpacing(30);
					    newOTJEntryGrid.add(newEPAMiscDetailsContainer, 1, 2);
					    
					    
						Label OTJDayTypeLabel = new Label("Type of Day:       ");
						OTJDayTypeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						ObservableList<String> dayTypeList = FXCollections.observableArrayList("University","Study Day","Dissertation Leave");
						ComboBox dayTypeComboBox = new ComboBox(dayTypeList);
						
					    
						Label OTJHoursCompletedLabel = new Label("Hours Completed:");
						OTJHoursCompletedLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						TextField hoursCompeltedTextfield = new TextField();
						
					    HBox OTJDateContainer = new HBox();
					    
						Label OTJEntryDateLabel = new Label("Date of Training:");
						OTJEntryDateLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						
						DatePicker OTJEntryDatePicker = new DatePicker();
						
					
						String SelectedMonth = String.valueOf(OTJMonthList.getSelectionModel().getSelectedItem());
						String numericMonth = "00";
						switch(SelectedMonth){
							case "January":
								numericMonth = "01";
								break;
							case "Febuary":
								numericMonth = "02";
								break;
							case "March":
								numericMonth = "03";
								break;
							case "April":
								numericMonth = "04";
								break;
							case "May":
								numericMonth = "05";
								break;
							case "June":
								numericMonth = "06";
								break;
							case "July":
								numericMonth = "07";
								break;
							case "August":
								numericMonth = "08";
								break;
							case "September":
								numericMonth = "09";
								break;
							case "October":
								numericMonth = "10";
								break;
							case "November":
								numericMonth = "11";
								break;
							case "December":
								numericMonth = "12";
								break;
						}
						
						String monthHolder = numericMonth;
					
						OTJDateContainer.getChildren().addAll(OTJEntryDateLabel,OTJEntryDatePicker);

						newEPAMiscDetailsContainer.getChildren().addAll(OTJDayTypeLabel,dayTypeComboBox,OTJHoursCompletedLabel,hoursCompeltedTextfield,OTJEntryDateLabel,OTJDateContainer);
						
						Label OTJActionPlanLabel = new Label("Action Plan: ");
						newOTJEntryGrid.add(OTJActionPlanLabel, 3, 1);
						newOTJEntryGrid.setHalignment(OTJActionPlanLabel, HPos.CENTER);
						OTJActionPlanLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						TextArea OTJActionPlanEntry = new TextArea();
						OTJActionPlanEntry.setWrapText(true);
						newOTJEntryGrid.add(OTJActionPlanEntry, 3, 2);
						
						Label OTJHopeAchieveLabel = new Label("What hoping to achieve: ");
						newOTJEntryGrid.add(OTJHopeAchieveLabel, 5, 1);
						newOTJEntryGrid.setHalignment(OTJHopeAchieveLabel, HPos.CENTER);
						OTJHopeAchieveLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						TextArea OTJHopeAchieveEntry = new TextArea();
						OTJHopeAchieveEntry.setWrapText(true);
						newOTJEntryGrid.add(OTJHopeAchieveEntry, 5, 2);
						
						Label OTJLearningAchievedLabel = new Label("What learning has been achieved: ");
						newOTJEntryGrid.add(OTJLearningAchievedLabel, 7, 1);
						newOTJEntryGrid.setHalignment(OTJLearningAchievedLabel, HPos.CENTER);
						OTJLearningAchievedLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						TextArea OTJLearningAchievedEntry = new TextArea();
						OTJLearningAchievedEntry.setWrapText(true);
						newOTJEntryGrid.add(OTJLearningAchievedEntry, 7, 2);
						
						
						Button newOTJSubmissionButton = new Button ("Submit");
						newOTJSubmissionButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						newOTJSubmissionButton.setMinWidth(150);
						newOTJSubmissionButton.setMinHeight(50);
						newOTJEntryGrid.setHalignment(newOTJSubmissionButton, HPos.CENTER);
						newOTJEntryGrid.add(newOTJSubmissionButton,3,4);
						
						Button cancelNewOTJButton = new Button ("Cancel");
						cancelNewOTJButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						cancelNewOTJButton.setMinWidth(150);
						cancelNewOTJButton.setMinHeight(50);
						newOTJEntryGrid.setHalignment(cancelNewOTJButton, HPos.CENTER);
						newOTJEntryGrid.add(cancelNewOTJButton,7,4);
						
						
						EventHandler<ActionEvent> submitOTJEntry = new EventHandler<ActionEvent>() {
							
							public void handle(ActionEvent e) {
								
								//checks if hours entered is float/int then ensures all other mandatory entries are filled (date/hours completed/day type)
								String tempDateString = "";
								try {
									tempDateString = String.valueOf(OTJEntryDatePicker.getValue());
									tempDateString = tempDateString.substring(5,7);
								}catch(Exception z){
									Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
									noEPASelectedWarning.setTitle("WARNING");
									noEPASelectedWarning.setHeaderText("Not all requried fields have been filled");
									noEPASelectedWarning.setContentText("Please ensure data has been entered into the EPA Date, Hours completed and Day type");
									noEPASelectedWarning.showAndWait();
								};
								
								if ((tempDateString.equals(monthHolder)) == false) {
									
									Alert incorrectEPAMonthSelectedWarning = new Alert(AlertType.WARNING);
									incorrectEPAMonthSelectedWarning.setTitle("WARNING");
									incorrectEPAMonthSelectedWarning.setHeaderText("Please ensure the correct month is selected");
									incorrectEPAMonthSelectedWarning.setContentText("To add this entry to a different month, please open the month you would like to add to, then press the Add Entry button.");
									incorrectEPAMonthSelectedWarning.showAndWait();
								
								}else if (hoursCompeltedTextfield.getText().matches("[-+]?[0-9]*\\.?[0-9]+") == true && dayTypeComboBox.getValue() != null && hoursCompeltedTextfield.getText() != "" && OTJEntryDatePicker.getValue() != null) {//if entry validation is ok
									String currentlySelectedYear = String.valueOf(OTJYearDropdown.getValue());
									int selectedYear = Integer.parseInt(currentlySelectedYear.replaceAll("[^\\d.]", ""));//convert to int->(removes all non int characters)
									OTJ.submitNewOTJEntry(currentUser.getUserID(), selectedYear, OTJMonthList.getSelectionModel().getSelectedItem(), String.valueOf(dayTypeComboBox.getValue()), OTJActionPlanEntry.getText(), OTJHopeAchieveEntry.getText(), OTJLearningAchievedEntry.getText(), (OTJEntryDatePicker.getValue()), Float.parseFloat(hoursCompeltedTextfield.getText()));
									OTJHoursCalculator.setText("Hours Completed: " + OTJ.getOTJHoursCompleted(currentUser)+"\nHours Remaining: "+ OTJ.getOTJHoursRemaining(currentUser));
									newOTJEntryStage.close();
									
									TableImport.OTJImport(OTJDataList, currentUser.getUserID(), OTJMonthList.getSelectionModel().getSelectedItem(), selectedYear);
									OTJTimesheetTable.refresh();
									
								}else if (hoursCompeltedTextfield.getText().matches("[-+]?[0-9]*\\.?[0-9]+") == false) { //checks if num is not float or int
									Alert EPAHoursFormatIncorrectWarning = new Alert(AlertType.WARNING);
									EPAHoursFormatIncorrectWarning.setTitle("WARNING");
									EPAHoursFormatIncorrectWarning.setHeaderText("Please ensure the hours entered is formatted correctly");
									EPAHoursFormatIncorrectWarning.setContentText("The number of hours entered must be either a whole or decimal number");
									EPAHoursFormatIncorrectWarning.showAndWait();
									
								}else {
									Alert noEPASelectedWarning2 = new Alert(AlertType.WARNING);
									noEPASelectedWarning2.setTitle("WARNING");
									noEPASelectedWarning2.setHeaderText("Not all requried fields have been filled");
									noEPASelectedWarning2.setContentText("Please ensure data has been entered into the EPA Date, Hours completed and Day type");
									noEPASelectedWarning2.showAndWait();
									
								}

								}
							
							
						};		
						newOTJSubmissionButton.setOnAction(submitOTJEntry);
						
						EventHandler<ActionEvent> cancelOTJEntry = new EventHandler<ActionEvent>() {
							
							public void handle(ActionEvent e) {

								newOTJEntryStage.close();

								}

						};		
						cancelNewOTJButton.setOnAction(cancelOTJEntry);
						
						
					}else {
						Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
						noEPASelectedWarning.setTitle("WARNING");
						noEPASelectedWarning.setHeaderText("No Table Selected");
						noEPASelectedWarning.setContentText("Please select a table to add an entry to");
						noEPASelectedWarning.showAndWait();
					}
				
				
			}	
			
			};
			addOTJEntryButton.setOnAction(addOTJEntry);

			
			EventHandler<ActionEvent> editOTJEntry = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					
					if (OTJTimesheetTable.getSelectionModel().getSelectedItem() != null) {
					
					if (OTJMonthList.getSelectionModel().getSelectedItem() != null) {
						
					
					
						Stage newOTJEntryStage = new Stage();
						newOTJEntryStage.initOwner(primaryStage);//sets this new stage as a child of primaryStage - allows for modelity
						newOTJEntryStage.initModality(Modality.APPLICATION_MODAL); //sets stage so that it must be closed before main window can be interacted with again
						
						BorderPane newOTJEntryBorder = new BorderPane();
						Scene newOTJEntryScene = new Scene(newOTJEntryBorder, 1280,720);
						newOTJEntryStage.setScene(newOTJEntryScene);
						newOTJEntryStage.setTitle("Add New OTJ Entry");
						newOTJEntryStage.show();
							
						GridPane newOTJEntryGrid = new GridPane(); //defines the grid pane that will contain the FAQ screen
						newOTJEntryBorder.setCenter(newOTJEntryGrid);
						
						ColumnConstraints newOTJEntryPageColumn0 = new ColumnConstraints(); 
					    newOTJEntryPageColumn0.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn1 = new ColumnConstraints();
					    newOTJEntryPageColumn1.setPercentWidth(10); 
					    ColumnConstraints newOTJEntryPageColumn2 = new ColumnConstraints(); 
					    newOTJEntryPageColumn2.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn3 = new ColumnConstraints(); 
					    newOTJEntryPageColumn3.setPercentWidth(25); 
					    ColumnConstraints newOTJEntryPageColumn4 = new ColumnConstraints(); 
					    newOTJEntryPageColumn4.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn5 = new ColumnConstraints(); 
					    newOTJEntryPageColumn5.setPercentWidth(25); 
					    ColumnConstraints newOTJEntryPageColumn6 = new ColumnConstraints(); 
					    newOTJEntryPageColumn6.setPercentWidth(3); 
					    ColumnConstraints newOTJEntryPageColumn7 = new ColumnConstraints(); 
					    newOTJEntryPageColumn7.setPercentWidth(25); 
					    ColumnConstraints newOTJEntryPageColumn8 = new ColumnConstraints(); 
					    newOTJEntryPageColumn8.setPercentWidth(3); 
					    newOTJEntryGrid.getColumnConstraints().addAll(newOTJEntryPageColumn0, newOTJEntryPageColumn1, newOTJEntryPageColumn2,newOTJEntryPageColumn3,newOTJEntryPageColumn4,newOTJEntryPageColumn5,newOTJEntryPageColumn6,newOTJEntryPageColumn7,newOTJEntryPageColumn8);
						
					    RowConstraints newOTJEntryPageRow0 = new RowConstraints(); 
					    newOTJEntryPageRow0.setPercentHeight(3); 
					    RowConstraints newOTJEntryPageRow1 = new RowConstraints(); 
					    newOTJEntryPageRow1.setPercentHeight(10); 
					    RowConstraints newOTJEntryPageRow2 = new RowConstraints();
					    newOTJEntryPageRow2.setPercentHeight(49);
					    RowConstraints newOTJEntryPageRow3 = new RowConstraints();
					    newOTJEntryPageRow3.setPercentHeight(5);
					    RowConstraints newOTJEntryPageRow4 = new RowConstraints();
					    newOTJEntryPageRow4.setPercentHeight(20);
					    RowConstraints newOTJEntryPageRow5 = new RowConstraints();
					    newOTJEntryPageRow5.setPercentHeight(13);
					    newOTJEntryGrid.getRowConstraints().addAll(newOTJEntryPageRow0,newOTJEntryPageRow1,newOTJEntryPageRow2,newOTJEntryPageRow3,newOTJEntryPageRow4,newOTJEntryPageRow5);
					    
					    VBox newEPAMiscDetailsContainer = new VBox();
					    newEPAMiscDetailsContainer.setSpacing(30);
					    newOTJEntryGrid.add(newEPAMiscDetailsContainer, 1, 2);
					    
					    
						Label OTJDayTypeLabel = new Label("Type of Day:       ");
						OTJDayTypeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						ObservableList<String> dayTypeList = FXCollections.observableArrayList("University","Study Day","Dissertation Leave");
						ComboBox dayTypeComboBox = new ComboBox(dayTypeList);
						
						switch(OTJTimesheetTable.getSelectionModel().getSelectedItem().getDayType()) {
						case "University":
							dayTypeComboBox.getSelectionModel().select(0);
							break;
						case "Dissertation Leave":
							dayTypeComboBox.getSelectionModel().select(2);
							break;
						case "Study Day":
							dayTypeComboBox.getSelectionModel().select(1);
							break;
							
						}
						
					    
						Label OTJHoursCompletedLabel = new Label("Hours Completed:");
						OTJHoursCompletedLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						TextField hoursCompeltedTextfield = new TextField(String.valueOf(OTJTimesheetTable.getSelectionModel().getSelectedItem().getHoursCompleted()));

						
					    HBox OTJDateContainer = new HBox();
					    
						Label OTJEntryDateLabel = new Label("Date of Training:");
						OTJEntryDateLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
						
						DatePicker OTJEntryDatePicker = new DatePicker();
						String OTJEntryDateDateDatatype = String.valueOf(OTJTimesheetTable.getSelectionModel().getSelectedItem().getActivityDate());
						LocalDate OTJEntryDate = LocalDate.parse(OTJEntryDateDateDatatype);
						OTJEntryDatePicker.setValue(OTJEntryDate);//sets date to current task date (extra code from above converts date to local date)
						
					
						String SelectedMonth = String.valueOf(OTJMonthList.getSelectionModel().getSelectedItem());
						String numericMonth = "00";
						switch(SelectedMonth){
							case "January":
								numericMonth = "01";
								break;
							case "Febuary":
								numericMonth = "02";
								break;
							case "March":
								numericMonth = "03";
								break;
							case "April":
								numericMonth = "04";
								break;
							case "May":
								numericMonth = "05";
								break;
							case "June":
								numericMonth = "06";
								break;
							case "July":
								numericMonth = "07";
								break;
							case "August":
								numericMonth = "08";
								break;
							case "September":
								numericMonth = "09";
								break;
							case "October":
								numericMonth = "10";
								break;
							case "November":
								numericMonth = "11";
								break;
							case "December":
								numericMonth = "12";
								break;
						}
						
						String monthHolder = numericMonth;
					
						OTJDateContainer.getChildren().addAll(OTJEntryDateLabel,OTJEntryDatePicker);

						newEPAMiscDetailsContainer.getChildren().addAll(OTJDayTypeLabel,dayTypeComboBox,OTJHoursCompletedLabel,hoursCompeltedTextfield,OTJEntryDateLabel,OTJDateContainer);
						
						Label OTJActionPlanLabel = new Label("Action Plan: ");
						newOTJEntryGrid.add(OTJActionPlanLabel, 3, 1);
						newOTJEntryGrid.setHalignment(OTJActionPlanLabel, HPos.CENTER);
						OTJActionPlanLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						TextArea OTJActionPlanEntry = new TextArea(OTJTimesheetTable.getSelectionModel().getSelectedItem().getActionPlan());
						OTJActionPlanEntry.setWrapText(true);
						newOTJEntryGrid.add(OTJActionPlanEntry, 3, 2);
						
						Label OTJHopeAchieveLabel = new Label("What hoping to achieve: ");
						newOTJEntryGrid.add(OTJHopeAchieveLabel, 5, 1);
						newOTJEntryGrid.setHalignment(OTJHopeAchieveLabel, HPos.CENTER);
						OTJHopeAchieveLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						TextArea OTJHopeAchieveEntry = new TextArea(OTJTimesheetTable.getSelectionModel().getSelectedItem().getAchieveGoal());
						OTJHopeAchieveEntry.setWrapText(true);
						newOTJEntryGrid.add(OTJHopeAchieveEntry, 5, 2);
						
						Label OTJLearningAchievedLabel = new Label("What learning has been achieved: ");
						newOTJEntryGrid.add(OTJLearningAchievedLabel, 7, 1);
						newOTJEntryGrid.setHalignment(OTJLearningAchievedLabel, HPos.CENTER);
						OTJLearningAchievedLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						TextArea OTJLearningAchievedEntry = new TextArea(OTJTimesheetTable.getSelectionModel().getSelectedItem().getLearningAchieved());
						OTJLearningAchievedEntry.setWrapText(true);
						newOTJEntryGrid.add(OTJLearningAchievedEntry, 7, 2);
						
						
						Button newOTJSubmissionButton = new Button ("Submit");
						newOTJSubmissionButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						newOTJSubmissionButton.setMinWidth(150);
						newOTJSubmissionButton.setMinHeight(50);
						newOTJEntryGrid.setHalignment(newOTJSubmissionButton, HPos.CENTER);
						newOTJEntryGrid.add(newOTJSubmissionButton,3,4);
						
						Button cancelNewOTJButton = new Button ("Cancel");
						cancelNewOTJButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
						cancelNewOTJButton.setMinWidth(150);
						cancelNewOTJButton.setMinHeight(50);
						newOTJEntryGrid.setHalignment(cancelNewOTJButton, HPos.CENTER);
						newOTJEntryGrid.add(cancelNewOTJButton,7,4);
						
					
						
						EventHandler<ActionEvent> submitOTJEntry = new EventHandler<ActionEvent>() {
							
							public void handle(ActionEvent e) {
								
								String tempDateString = "";
								try {
									tempDateString = String.valueOf(OTJEntryDatePicker.getValue());
									tempDateString = tempDateString.substring(5,7);
								}catch(Exception z){
									Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
									noEPASelectedWarning.setTitle("WARNING");
									noEPASelectedWarning.setHeaderText("Not all requried fields have been filled");
									noEPASelectedWarning.setContentText("Please ensure data has been entered into the EPA Date, Hours completed and Day type");
									noEPASelectedWarning.showAndWait();
								};
								
								if ((tempDateString.equals(monthHolder)) == false) {
									
									Alert incorrectEPAMonthSelectedWarning = new Alert(AlertType.WARNING);
									incorrectEPAMonthSelectedWarning.setTitle("WARNING");
									incorrectEPAMonthSelectedWarning.setHeaderText("Please ensure the correct month is selected");
									incorrectEPAMonthSelectedWarning.setContentText("To add this entry to a different month, please open the month you would like to add to, then press the Add Entry button.");
									incorrectEPAMonthSelectedWarning.showAndWait();
								
								}else if (hoursCompeltedTextfield.getText().matches("[-+]?[0-9]*\\.?[0-9]+") == true && dayTypeComboBox.getValue() != null && hoursCompeltedTextfield.getText() != "" && OTJEntryDatePicker.getValue() != null) {//if entry validation is ok
									String currentlySelectedYear = String.valueOf(OTJYearDropdown.getValue());
									int selectedYear = Integer.parseInt(currentlySelectedYear.replaceAll("[^\\d.]", ""));//convert to int->(removes all non int characters)
									OTJ.editOTJEntry(OTJTimesheetTable.getSelectionModel().getSelectedItem().getOTJ_Entry_ID(), String.valueOf(dayTypeComboBox.getValue()), OTJActionPlanEntry.getText(), OTJHopeAchieveEntry.getText(), OTJLearningAchievedEntry.getText(), (OTJEntryDatePicker.getValue()), Float.parseFloat(hoursCompeltedTextfield.getText()));
									OTJHoursCalculator.setText("Hours Completed: " + OTJ.getOTJHoursCompleted(currentUser)+"\nHours Remaining: "+ OTJ.getOTJHoursRemaining(currentUser));
									newOTJEntryStage.close();
									
									TableImport.OTJImport(OTJDataList, currentUser.getUserID(), OTJMonthList.getSelectionModel().getSelectedItem(), selectedYear);
									OTJTimesheetTable.refresh();
									
								}else if (hoursCompeltedTextfield.getText().matches("[-+]?[0-9]*\\.?[0-9]+") == false) { //checks if num is not float or int
									Alert EPAHoursFormatIncorrectWarning = new Alert(AlertType.WARNING);
									EPAHoursFormatIncorrectWarning.setTitle("WARNING");
									EPAHoursFormatIncorrectWarning.setHeaderText("Please ensure the hours entered is formatted correctly");
									EPAHoursFormatIncorrectWarning.setContentText("The number of hours entered must be either a whole or decimal number");
									EPAHoursFormatIncorrectWarning.showAndWait();
									
								}else {
									Alert noEPASelectedWarning2 = new Alert(AlertType.WARNING);
									noEPASelectedWarning2.setTitle("WARNING");
									noEPASelectedWarning2.setHeaderText("Not all requried fields have been filled");
									noEPASelectedWarning2.setContentText("Please ensure data has been entered into the EPA Date, Hours completed and Day type");
									noEPASelectedWarning2.showAndWait();
									
								}

								}
						
							
						};		
						newOTJSubmissionButton.setOnAction(submitOTJEntry);
						
						EventHandler<ActionEvent> cancelOTJEntry = new EventHandler<ActionEvent>() {
							
							public void handle(ActionEvent e) {

								newOTJEntryStage.close();

								}

						};		
						cancelNewOTJButton.setOnAction(cancelOTJEntry);
						
						
						}else {
						Alert noEPASelectedWarning = new Alert(AlertType.WARNING);
						noEPASelectedWarning.setTitle("WARNING");
						noEPASelectedWarning.setHeaderText("No Table Selected");
						noEPASelectedWarning.setContentText("Please select a table to add an entry to");
						noEPASelectedWarning.showAndWait();
					}
					
				
			
				}else{
					Alert noEPAEntrySelectedWarning = new Alert(AlertType.WARNING);
					noEPAEntrySelectedWarning.setTitle("WARNING");
					noEPAEntrySelectedWarning.setHeaderText("No Entry Selected");
					noEPAEntrySelectedWarning.setContentText("Please select an entry to amend");
					noEPAEntrySelectedWarning.showAndWait();
				}
			}	
			};
			
			
			editOTJEntryButton.setOnAction(editOTJEntry);
			
		EPAEditEntryButton.setOnAction(editEPAEntry);
		
		//--------------------------------------------
		//---------- Window Launch Settings ----------
		//--------------------------------------------
		
		primaryStage.setScene(loginScene); //adds the scene to the stage (a stage is an instance of an app window)
		primaryStage.show(); //initialises the window
		primaryStage.setTitle("Apprentice Hub"); //sets the title of the window
		
	}
	
	public static void main(String[] args) { //main class
		
		launch(args); //runs the javaFX window
	}
}
