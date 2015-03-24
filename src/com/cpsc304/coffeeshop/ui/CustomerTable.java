package com.cpsc304.coffeeshop.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
 
public class CustomerTable extends Application {
 
    public static final String Column1MapKey = "A";
    public static final String Column2MapKey = "B";
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(440);
        stage.setHeight(700);
        
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
 
        final VBox vbox = new VBox();
 
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, tableView);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
 
        stage.show();
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