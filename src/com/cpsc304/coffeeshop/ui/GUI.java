package com.cpsc304.coffeeshop.ui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.cpsc304.coffeeshop.objects.Store;
import com.cpsc304.coffeeshop.objects.Transaction;
import com.cpsc304.coffeeshop.service.MemberServiceImpl;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
 
public class GUI extends Application {
	
	public static final int SPLASH_FONT_SIZE = 28;
	public static final int TITLE_FONT_SIZE = 20;
	public static final int LABEL_FONT_SIZE = 16;
	
	public static final int COLUMN_MIN_WIDTH = 100;
	public static final int COLUMN_MAX_WIDTH = 200;
	
	public static final int MAIN_VBOX_SPACING = 20;
	public static final int BAR_HBOX_SPACING = 10;
	
	public static final String Column1MapKey = "A";
	public static final String Column2MapKey = "B";
	private Stage stage;
	
	public MemberServiceImpl memberService = new MemberServiceImpl();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setUpViewStage() {
		stage.setWidth(1200);
		stage.setHeight(700);
	}
	
	public void errorPopup(String msg) {
		// Adapted from: http://stackoverflow.com/questions/8309981/how-to-create-and-show-common-dialog-error-warning-confirmation-in-javafx-2
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create().children(new Text(msg)).alignment(Pos.CENTER).padding(new Insets(10)).build()));
		dialogStage.show();
	}

	@Override
	 public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setTitle("Table View Sample");
		primaryStage.setWidth(440);
		primaryStage.setHeight(700);
	    Scene scene = logInScene();
	    primaryStage.setScene(scene);
	    primaryStage.show();
	
	}
	
	public Scene logInScene() {
		stage.setWidth(440);
		stage.setHeight(440);
		
        VBox menuButtons = new VBox();
        menuButtons.setSpacing(MAIN_VBOX_SPACING);
        menuButtons.setPadding(new Insets(10, 0, 0, 10));
        menuButtons.setAlignment(Pos.CENTER);
        
        final Label title = new Label("Coffee Shop Application");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, SPLASH_FONT_SIZE));
        
        Button customerButton = new Button("Customer");
        Button memberButton = new Button(" Member ");
        Button managerButton = new Button(" Manager ");
		
		customerButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				 stage.setScene(customerScene());
			}
		});
		
		memberButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				stage.setScene(memberScene());
			}
		});
		
		managerButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				stage.setScene(managerScene());
			}
		});
		
		
		menuButtons.getChildren().addAll(title, customerButton, memberButton, managerButton);
		return new Scene(menuButtons);
    }
	
	
    protected Scene customerScene() {
    	setUpViewStage();
    	HBox toolBar = new HBox();
    	toolBar.setSpacing(BAR_HBOX_SPACING);    	
        
        final TableView<Store> storeTable = new TableView<Store>();
        storeTable.setEditable(false);        
        TableColumn<Store, String> storeIdCol = new TableColumn<Store, String>("Store ID");
        storeIdCol.setMinWidth(COLUMN_MIN_WIDTH);
        storeIdCol.setMaxWidth(COLUMN_MAX_WIDTH);
        storeIdCol.setCellValueFactory(new PropertyValueFactory<Store, String>("storeId"));
        TableColumn<Store, String> houseNoCol = new TableColumn<Store, String>("Address Number");
        houseNoCol.setMinWidth(COLUMN_MIN_WIDTH);
        houseNoCol.setMaxWidth(COLUMN_MAX_WIDTH);
        houseNoCol.setCellValueFactory(new PropertyValueFactory<Store, String>("houseNo"));
        TableColumn<Store, String> streetCol = new TableColumn<Store, String>("Street");
        streetCol.setMinWidth(COLUMN_MIN_WIDTH);
        streetCol.setMaxWidth(COLUMN_MAX_WIDTH);
        streetCol.setCellValueFactory(new PropertyValueFactory<Store, String>("street"));
        TableColumn<Store, String> postalCodeCol = new TableColumn<Store, String>("Postal Code");
        postalCodeCol.setMinWidth(COLUMN_MIN_WIDTH);
        postalCodeCol.setMaxWidth(COLUMN_MAX_WIDTH);
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<Store, String>("postalCode"));
        TableColumn<Store, String> cityCol = new TableColumn<Store, String>("City");
        cityCol.setMinWidth(COLUMN_MIN_WIDTH);
        cityCol.setMaxWidth(COLUMN_MAX_WIDTH);
        cityCol.setCellValueFactory(new PropertyValueFactory<Store, String>("city"));
        TableColumn<Store, String> provCol = new TableColumn<Store, String>("Province");
        provCol.setMinWidth(COLUMN_MIN_WIDTH);
        provCol.setMaxWidth(COLUMN_MAX_WIDTH);
        provCol.setCellValueFactory(new PropertyValueFactory<Store, String>("province"));
        storeTable.getColumns().addAll(storeIdCol, houseNoCol, streetCol, postalCodeCol, cityCol, provCol);    	
    	
		final Label title = new Label("Customer View");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, TITLE_FONT_SIZE));
        
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				 stage.setScene(logInScene());
			}
		});
        
        toolBar.getChildren().addAll(title, backButton);
        
        HBox optionBar = new HBox();
        optionBar.setSpacing(BAR_HBOX_SPACING);
        
        Button findStores = new Button("Find Stores");
        findStores.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
		        // TODO: PUT DATABASE RETURN HERE
				List<Store> stores = new ArrayList<Store>();
				stores.add(new Store(new Double(Math.random() * 10).toString(), "1234", "Test Street", "V1AB2C", "Vancouver", "BC"));
				ObservableList<Store> data = FXCollections.observableList(stores);
				storeTable.setItems(data);
			}
		});
        final Separator optionBarSeparator1 = new Separator();
        optionBarSeparator1.setOrientation(Orientation.VERTICAL);
        final Label storeId = new Label("Store ID:");
        storeId.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField productStoreId = new TextField();
        Button findProducts = new Button("Find Products");
        findProducts.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
		        // TODO: Add action handler to put stuff into the table
				String input = productStoreId.getText();
				System.out.println(input);
				productStoreId.clear();
			}
		});
        final Separator optionBarSeparator2 = new Separator();
        optionBarSeparator2.setOrientation(Orientation.VERTICAL);
        Button findAllProducts = new Button("Find All Products");
        // TODO: write a button handler to display all the products
        
        optionBar.getChildren().addAll(findStores, optionBarSeparator1, storeId, productStoreId, findProducts, optionBarSeparator2, findAllProducts);
    	
        final VBox vbox = new VBox();
        vbox.setSpacing(MAIN_VBOX_SPACING);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(toolBar, optionBar, storeTable);
        return new Scene(vbox);
    }
	
    protected Scene memberScene() {
    	setUpViewStage();
    	HBox toolBar = new HBox();
    	toolBar.setSpacing(BAR_HBOX_SPACING);
    	
		final Label title = new Label("Member View");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, TITLE_FONT_SIZE));
        
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				 stage.setScene(logInScene());
			}
		});
        
        toolBar.getChildren().addAll(title, backButton);
        
        HBox optionBar = new HBox();
        optionBar.setSpacing(BAR_HBOX_SPACING);
        
        final Label memberId = new Label("Member ID:");
        memberId.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField memberField = new TextField();
        Button profileButton = new Button("Get Profile");
        final VBox profileForm = new VBox();
        profileForm.setSpacing(10);
        
        GridPane formGrid = new GridPane();
        
        final Label memberName = new Label("Name:");
        memberName.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField nameField = new TextField();
        formGrid.add(memberName, 0, 0);
        formGrid.add(nameField, 1, 0);
        
        final Label memberPhone = new Label("Phone:");
        memberPhone.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField phoneField = new TextField();
        formGrid.add(memberPhone, 0, 1);
        formGrid.add(phoneField, 1, 1);
        
        final Label memberHouse = new Label("House #:");
        memberHouse.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField houseField = new TextField();
        formGrid.add(memberHouse, 0, 2);
        formGrid.add(houseField, 1, 2);
        
        final Label memberStreet = new Label("Street:");
        memberStreet.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField streetField = new TextField();
        formGrid.add(memberStreet, 0, 3);
        formGrid.add(streetField, 1, 3);
        
        final Label memberPostalCode = new Label("Postal Code:");
        memberPostalCode.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField postalCodeField = new TextField();
        formGrid.add(memberPostalCode, 0, 4);
        formGrid.add(postalCodeField, 1, 4);
        
        final Label memberCity = new Label("City:");
        memberCity.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField cityField = new TextField();
        formGrid.add(memberCity, 0, 5);
        formGrid.add(cityField, 1, 5);
        
        final Label memberProv = new Label("Province:");
        memberProv.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField provField = new TextField();
        formGrid.add(memberProv, 0, 6);
        formGrid.add(provField, 1, 6);
        
        final Label memberPoints = new Label("Points:");
        memberPoints.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final Text points = new Text("0");
        points.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        formGrid.add(memberPoints, 0, 7);
        formGrid.add(points, 1, 7);        

        Button changeNameButton = new Button("Change Name");
        changeNameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				String nameInput = nameField.getText();
				String memberInput = memberField.getText();
				if (nameInput.isEmpty()) {
					errorPopup("Please input a phone number!");
				} else if (memberInput.isEmpty()) {
					errorPopup("Please input a member ID!");
				} else {
					try {
						int memberId = Integer.parseInt(memberInput);
						memberService.updateName(memberId, nameInput);
					} catch (NumberFormatException nfe) {
						errorPopup("Please input a valid member ID or phone number!");
						memberField.clear();
					} catch (SQLException e) {
						errorPopup("Update Failed");
					}
				}
			}
		});

        Button changePhoneButton = new Button("Change Phone");
        changePhoneButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				String phoneNoInput = phoneField.getText();
				String memberInput = memberField.getText();
				if (phoneNoInput.isEmpty()) {
					errorPopup("Please input a phone number!");
				} else if (memberInput.isEmpty()) {
					errorPopup("Please input a member ID!");
				} else {
					try {
						int memberId = Integer.parseInt(memberInput);
						int phoneNo = Integer.parseInt(phoneNoInput);
						memberService.updatePhoneNumber(memberId, phoneNo);
					} catch (NumberFormatException nfe) {
						errorPopup("Please input a valid member ID or phone number!");
						memberField.clear();
					} catch (SQLException e) {
						errorPopup("Update Failed");
					}
				}
			}
		});
        
        Button changeAddress = new Button("Change Address");
        changeAddress.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				String houseInput = houseField.getText();
				String streetInput = streetField.getText();
				String postalCodeInput = postalCodeField.getText();
				String cityInput = cityField.getText();
				String provInput = provField.getText();
				if (houseInput.isEmpty() || streetInput.isEmpty() || postalCodeInput.isEmpty() || cityInput.isEmpty() || provInput.isEmpty()) {
					errorPopup("Please input a FULL address!");
				} else {
					String input = memberField.getText();
					if (input.isEmpty()) {
						errorPopup("Please input a valid member ID!");
						memberField.clear();
					} else {
						try {
							int memberId = Integer.parseInt(input);
							int houseNumber = Integer.parseInt(houseInput);
							memberService.updateAddress(memberId, houseNumber, streetInput, postalCodeInput, cityInput, provInput);
						} catch (NumberFormatException nfe) {
							errorPopup("Please input a valid member ID or house number!");
							memberField.clear();
						} catch (SQLException e) {
							errorPopup("Update Failed");
						}
					}			
				}
			}
		});
        Button deleteMember = new Button("Delete Membership");
        profileForm.getChildren().addAll(formGrid, changeNameButton, changePhoneButton, changeAddress, deleteMember);
        
        profileButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				String input = memberField.getText();
				if (input.isEmpty()) {
					errorPopup("Please input a valid member ID!");
					memberField.clear();
				} else {
					try {
						int memberId = Integer.parseInt(input);
						List<Map<String, String>> member = memberService.getMemberById(memberId);
						if (member.isEmpty()) {
							errorPopup("This member does not exist!");
						} else {
							nameField.setText(member.get(0).get("Name"));
							phoneField.setText(member.get(0).get("Phone"));
							houseField.setText(member.get(0).get("HouseNo"));
							streetField.setText(member.get(0).get("Street"));
							postalCodeField.setText(member.get(0).get("PostalCode"));
							cityField.setText(member.get(0).get("City"));
							provField.setText(member.get(0).get("Province"));
							points.setText(member.get(0).get("PointBalance"));
						}
					} catch (NumberFormatException nfe) {
						errorPopup("Please input a valid member ID!");
						memberField.clear();
					} catch (SQLException e) {
						errorPopup("Member does not exist!");
					}
				}
			}
		});
        
        optionBar.getChildren().addAll(memberId, memberField, profileButton);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(MAIN_VBOX_SPACING);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(toolBar, optionBar, profileForm);
        return new Scene(vbox);
    }
    
    protected Scene managerScene() {
    	setUpViewStage();
    	HBox toolBar = new HBox();
    	toolBar.setSpacing(BAR_HBOX_SPACING);
    	
    	final TableView<Transaction> transactionTable = new TableView<Transaction>();
    	transactionTable.setEditable(false);        
        TableColumn<Transaction, String> transNoCol = new TableColumn<Transaction, String>("Transaction #");
        transNoCol.setMinWidth(COLUMN_MIN_WIDTH);
        transNoCol.setMaxWidth(COLUMN_MAX_WIDTH);
        transNoCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionNo"));
        TableColumn<Transaction, String> ptsGenCol = new TableColumn<Transaction, String>("Points Generated");
        ptsGenCol.setMinWidth(COLUMN_MIN_WIDTH);
        ptsGenCol.setMaxWidth(COLUMN_MAX_WIDTH);
        ptsGenCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("pointsGenerated"));
        TableColumn<Transaction, String> dateCol = new TableColumn<Transaction, String>("Date");
        dateCol.setMinWidth(COLUMN_MIN_WIDTH);
        dateCol.setMaxWidth(COLUMN_MAX_WIDTH);
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
        TableColumn<Transaction, String> storeIdCol = new TableColumn<Transaction, String>("Store ID");
        storeIdCol.setMinWidth(COLUMN_MIN_WIDTH);
        storeIdCol.setMaxWidth(COLUMN_MAX_WIDTH);
        storeIdCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("storeId"));
        TableColumn<Transaction, String> memberIdCol = new TableColumn<Transaction, String>("Member ID");
        memberIdCol.setMinWidth(COLUMN_MIN_WIDTH);
        memberIdCol.setMaxWidth(COLUMN_MAX_WIDTH);
        memberIdCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("memberId"));
        TableColumn<Transaction, String> sinNoCol = new TableColumn<Transaction, String>("Employee SIN");
        sinNoCol.setMinWidth(COLUMN_MIN_WIDTH);
        sinNoCol.setMaxWidth(COLUMN_MAX_WIDTH);
        sinNoCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("sinNo"));
        TableColumn<Transaction, String> pointCostCol = new TableColumn<Transaction, String>("Point Cost");
        pointCostCol.setMinWidth(COLUMN_MIN_WIDTH);
        pointCostCol.setMaxWidth(COLUMN_MAX_WIDTH);
        pointCostCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("pointCost"));
        TableColumn<Transaction, String> moneyCostCol = new TableColumn<Transaction, String>("Money Cost");
        moneyCostCol.setMinWidth(COLUMN_MIN_WIDTH);
        moneyCostCol.setMaxWidth(COLUMN_MAX_WIDTH);
        moneyCostCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("moneyCost"));
        transactionTable.getColumns().addAll(transNoCol, ptsGenCol, dateCol, storeIdCol, memberIdCol, sinNoCol, pointCostCol, moneyCostCol);
    	
		final Label title = new Label("Manager View");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, TITLE_FONT_SIZE));
        
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent event) {
				 stage.setScene(logInScene());
			}
		});
        
        toolBar.getChildren().addAll(title, backButton);
        
        HBox managerBar = new HBox();
        managerBar.setSpacing(BAR_HBOX_SPACING);
        final Label managerId = new Label("Manager ID:");
        managerId.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField managerField = new TextField();
        Button managerButton = new Button("Get Manager");
        managerBar.getChildren().addAll(managerId, managerField, managerButton);
        
        HBox optionBar = new HBox();
        optionBar.setSpacing(BAR_HBOX_SPACING);
        final Label employeeSin = new Label("Employee SIN:");
        employeeSin.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField empSinField = new TextField();
        Button findTransactionsForEmployee = new Button("Get Transactions");
        // TODO: Add action handler to put stuff into the table
        final Separator optionBarSeparator1 = new Separator();
        optionBarSeparator1.setOrientation(Orientation.VERTICAL);
        Button storeTransactionButton = new Button("Get Store Transactions");
        // TODO: Add action handler to put stuff into the table
        final Separator optionBarSeparator2 = new Separator();
        optionBarSeparator2.setOrientation(Orientation.VERTICAL);
        final Label memberId = new Label("Member ID:");
        memberId.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final TextField memberIdField = new TextField();
        Button findTransactionsForMember = new Button("Get Transactions");
        // TODO: Add action handler to put stuff into the table (put sum into popup?)
        final Separator optionBarSeparator3 = new Separator();
        optionBarSeparator3.setOrientation(Orientation.VERTICAL);
        Button summaryButton = new Button("Get Summary");
        // TODO: Make popup of summary
        optionBar.getChildren().addAll(employeeSin, empSinField, findTransactionsForEmployee, optionBarSeparator1, storeTransactionButton, optionBarSeparator2, memberId, memberIdField, findTransactionsForMember, optionBarSeparator3, summaryButton);
        
        HBox dateBar = new HBox();
        dateBar.setSpacing(BAR_HBOX_SPACING);
        final Label startDateLabel = new Label("Start Date:");
        startDateLabel.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final DatePicker startDateField = new DatePicker(Locale.ENGLISH);
        startDateField.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        startDateField.getCalendarView().setShowWeeks(false);
        // TODO: Implement datePicker
        final Separator dateBarSeparator = new Separator();
        dateBarSeparator.setOrientation(Orientation.VERTICAL);
        final Label endDateLabel = new Label("End Date:");
        endDateLabel.setFont(Font.font("Arial", LABEL_FONT_SIZE));
        final DatePicker endDateField = new DatePicker(Locale.ENGLISH);
        endDateField.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        endDateField.getCalendarView().setShowWeeks(false);
        // TODO: Implement datePicker
        dateBar.getChildren().addAll(startDateLabel, startDateField, dateBarSeparator, endDateLabel, endDateField);       
    	
        final VBox vbox = new VBox();
        vbox.setSpacing(MAIN_VBOX_SPACING);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(toolBar, managerBar, optionBar, dateBar, transactionTable);
        return new Scene(vbox);
    }
}
