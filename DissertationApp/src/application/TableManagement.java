package application;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class TableManagement {

	
	public static void textWrapTableColumnEPA(TableColumn referencedColumn, int wrappingWidthValue) { // takes column that needs wrapping / takes value that it should be wrapped to width wise
		
		referencedColumn.setCellFactory(param -> {
	        return new TableCell<EPA, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);
	                
	                if (item == null || empty) {
	                    setText(null);
	                    setStyle("");
	                } else {
	                    Text text = new Text(item);
	                    text.setWrappingWidth(wrappingWidthValue);
	                    setPrefHeight(100);
	                    setGraphic(text);
	                }
	            }
	        };
	    });
	}
	
	public static void textWrapTableColumnOTJ(TableColumn referencedColumn, int wrappingWidthValue) { // takes column that needs wrapping / takes value that it should be wrapped to width wise
		
		referencedColumn.setCellFactory(param -> {
	        return new TableCell<OTJ, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);
	                if (item == null || empty) {
	                    setText(null);
	                    setStyle("");
	                } else {
	                    Text text = new Text(item);
	                    text.setWrappingWidth(wrappingWidthValue);
	                    setPrefHeight(100);
	                    setGraphic(text);
	                }
	            }
	        };
	    });
	}
	


}
	
	
	
	
	
	
	
	

