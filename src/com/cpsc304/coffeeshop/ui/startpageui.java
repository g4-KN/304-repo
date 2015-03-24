package com.cpsc304.coffeeshop.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
 
public class StartPageUI extends Application {
	
	public static final String Column1MapKey = "A";
	public static final String Column2MapKey = "B";
	private Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	 public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		stage = primaryStage;
		primaryStage.setTitle("Table View Sample");
		primaryStage.setWidth(440);
		primaryStage.setHeight(700);
	    Scene scene = logInScene();
	    primaryStage.setScene(scene);
	    primaryStage.show();
	
	}
	
	public Scene logInScene(){
        VBox menuButtons = new VBox();
        menuButtons.setSpacing(10);
        menuButtons.setPadding(new Insets(10, 0, 0, 10));
        menuButtons.setAlignment(Pos.CENTER);
        
        final Label title = new Label("Coffee Shop Application");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        
        Button customerButton = new Button("Customer");
        Button memberButton = new Button(" Member ");
        Button employeeButton = new Button("Employee");
        Button managerButton = new Button(" Manager ");
        Button adminButton = new Button("   Admin   ");
        final Label testLabel = new Label();
		
		customerButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				 stage.setScene(CustomerScene());
			}
		});
		
		memberButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent stage) {
				testLabel.setText("tegaerg");
			}
		});
		
		employeeButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent stage) {
				testLabel.setText("fghrth");
			}
		});
		
		managerButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent stage) {
				testLabel.setText("ppp");
			}
		});
	     
        adminButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent stage) {
				testLabel.setText("something");
			}
		});
		
		
		menuButtons.getChildren().addAll(title, customerButton, memberButton, employeeButton, managerButton, adminButton);
		return new Scene(menuButtons);   
    }
	
	
    protected Scene CustomerScene() {
    	final Label label = new Label("Customer Information");
        label.setFont(new Font("Arial", 20));
 
        TableColumn<Map, String> firstDataColumn = new TableColumn<>("CustomerName");
        TableColumn<Map, String> secondDataColumn = new TableColumn<>("CustomerAddress");
 
        firstDataColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));
        firstDataColumn.setMinWidth(200);
        secondDataColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
        secondDataColumn.setMinWidth(200);
 
        TableView tableView = new TableView<>(generateDataInMap());
 
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getColumns().setAll(firstDataColumn, secondDataColumn);
        Callback<TableColumn<Map, String>, TableCell<Map, String>>
        cellFactoryForMap = new Callback<TableColumn<Map, String>,
            TableCell<Map, String>>() {
                @Override
                public TableCell call(TableColumn p) {
                    return new TextFieldTableCell(new StringConverter() {
                        @Override
                        public String toString(Object t) {
                            return t.toString();
                        }
                        @Override
                        public Object fromString(String string) {
                            return string;
                        }                                    
                    });
                }
    };
        firstDataColumn.setCellFactory(cellFactoryForMap);
        secondDataColumn.setCellFactory(cellFactoryForMap);
        
        Button backButton = new Button("Back");
		
        backButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				 stage.setScene(logInScene());
			}
		});
 
    	
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, backButton, tableView);
        return new Scene(vbox);
    }
	
	
    private ObservableList<Map> generateDataInMap() {
        int max = 10;
        ObservableList<Map> allData = FXCollections.observableArrayList();
        
        String[] mockA = {"Jason", "Mayson", "Daniel", "David", "Mark" , "god"};
        String[] mockB = {"2122 something street", "9999 come@street","1234 easy street", "64356 idk street", "420 yoloswag street", "1337 legend street" };
        
        for (int i = 0; i < 6; i++) {
            Map<String, String> dataRow = new HashMap<>();
 
            String value1 = mockA[i];
            String value2 = mockB[i];
 
            dataRow.put(Column1MapKey, value1);
            dataRow.put(Column2MapKey, value2);
 
            allData.add(dataRow);
        }
        return allData;
    }

}
