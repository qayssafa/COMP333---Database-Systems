package application;

import java.text.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class NotificationsController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Notification> data;
	private ObservableList<Notification> dataList;
	
	@FXML
	private Label total;
	@FXML
	private TextField Notification_ID_S;
	@FXML
	private TextField Notification_ID__A;
	@FXML
	private TextField Notification_Date_A;
	@FXML
	private TextField Notification_methode_A;
	@FXML
	private TextField case_id_A;
	@FXML
	private TextField Notification_ID_U;
	@FXML
	private TextField Notification_Date_U;
	@FXML
	private TextField Notification_method_U;
	@FXML
	private TextField CASE_ID_U;
	@FXML
	private TextField Notification_ID_D;
	@FXML
	private TextField OLD_ID;
	@FXML
	private Button add;
	@FXML
	private Button searsh;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private Button Back;
	@FXML
	private TableView NotificationTable;
	@FXML
	private TableColumn<Notification, Integer> Notification_ID;
	@FXML
	private TableColumn<Notification, String> Notification_Date;
	@FXML
	private TableColumn<Notification, String> Notification_Method;
	@FXML
	private TableColumn<Notification, Integer> Case_id;
	
//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
			Notification rc;
			rc = new Notification(Integer.parseInt(Notification_ID__A.getText()),
					Notification_Date_A.getText(), Notification_methode_A.getText(),
					Integer.parseInt(case_id_A.getText()));

			dataList.add(rc);
			insertData(rc);
			refresh();

			Notification_ID__A.clear();
			Notification_Date_A.clear();
			Notification_methode_A.clear();
			case_id_A.clear();

		} catch (Exception e) {

		}
		totoal_number();
		
	}
	
//----------------------------------------------------------------------

	private void deleteRow(int Notification_ID) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Notification where Notification_ID =" + Notification_ID);
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		totoal_number();
	}

//----------------------------------------------------------------------

	@FXML
	public void delete(ActionEvent event) {
		try {
			if (Notification_ID_D.getText() != null) {
				int Notification_ID = Integer.parseInt(Notification_ID_D.getText());
				deleteRow(Notification_ID);
			}
			Notification_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
		totoal_number();
	}
	
//----------------------------------------------------------------------

	private void insertData(Notification rc) {

		try {
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date myDate = null;
			java.sql.Date sqlDate;
			
			try {
				myDate = formatter.parse(rc.getNotification_date());

			} catch (Exception e) {
				Message.displayMassage("Please confirm the values of DATE","Warning");
			}

			sqlDate = new java.sql.Date(myDate.getTime());
			Connector.a.connectDB();
			String sql = "Insert into Notification (Notification_id, Notification_date, Notification_method, Case_id) values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1, rc.getNotification_id());
			ps.setTimestamp(2, new java.sql.Timestamp(myDate.getTime()));
			ps.setString(3, rc.getNotification_method());
			ps.setInt(4, rc.getCase_id());
			ps.execute();
			
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		totoal_number();
	}
	
//----------------------------------------------------------------------
	
	public void refresh() {
		NotificationTable.getItems().clear();
		getData();
		totoal_number();
		NotificationTable.setItems(dataList);

	}
	
//----------------------------------------------------------------------
	
	public void getData() {
		String SQL = "select * from Notification order by Notification_id asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Notification it = new Notification(rs.getInt(1), rs.getString(2),
						              rs.getString(3), rs.getInt(4));
				dataList.add(it);
			}
			rs.close();
			state.close();
			Connector.a.connectDB().close();
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		totoal_number();
	}
	
//----------------------------------------------------------------------

	public void updateNotification_ID(int OLD_ID,int Notification_ID_U) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Notification set Notification_id = " + Notification_ID_U + " where Notification_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updateNotification_Date(int OLD_ID , String Notification_Date_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Notification set Notification_date = '" + Notification_Date_U + "' where Notification_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}

//----------------------------------------------------------------------

	public void updateNotification_method(int OLD_ID , String Notification_method_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Notification set Notification_method = '" + Notification_method_U + "' where Notification_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updateCASE_ID(int OLD_ID , int CASE_ID_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Notification set Case_id = " + CASE_ID_U + " where Notification_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void searchItems() {
		FilteredList<Notification> filteredData = new FilteredList<>(dataList, b -> true);
		Notification_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getNotification_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else
					return false; // Does not match.
			});
		});
		SortedList<Notification> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(NotificationTable.comparatorProperty());
		NotificationTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------
	
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		NotificationTable.setEditable(true);
		
		
		Notification_ID.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("Notification_id"));
		Notification_ID.setCellFactory(TextFieldTableCell.<Notification, Integer>forTableColumn(new IntegerStringConverter()));
		Notification_ID.setOnEditCommit((CellEditEvent<Notification, Integer> t) -> {
			((Notification) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNotification_id(t.getNewValue()); 
			updateNotification_ID(t.getRowValue().getNotification_id(), t.getNewValue());
		});

		Notification_Date.setCellValueFactory(new PropertyValueFactory<Notification, String>("Notification_date"));
		Notification_Date.setCellFactory(TextFieldTableCell.<Notification>forTableColumn());
		Notification_Date.setOnEditCommit((CellEditEvent<Notification, String> t) -> {
	                      ((Notification) t.getTableView().getItems().get(t.getTablePosition().getRow())
	                      ).setNotification_date(t.getNewValue());
	                      updateNotification_Date(t.getRowValue().getNotification_id(), t.getNewValue());
		});
		        
		Notification_Method.setCellValueFactory(new PropertyValueFactory<Notification, String>("Notification_method"));
		Notification_Method.setCellFactory(TextFieldTableCell.<Notification>forTableColumn());
		Notification_Method.setOnEditCommit((CellEditEvent<Notification, String> t) -> {
	    ((Notification) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNotification_method(t.getNewValue());
		updateNotification_method(t.getRowValue().getNotification_id(), t.getNewValue());
		});
		
		
		Case_id.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("Case_id"));
		Case_id.setCellFactory(TextFieldTableCell.<Notification,Integer>forTableColumn(new IntegerStringConverter()));
		Case_id.setOnEditCommit((CellEditEvent<Notification, Integer> t) -> {        
		  ((Notification) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setCase_id(t.getNewValue());     		
		   updateCASE_ID(t.getRowValue().getNotification_id(), t.getNewValue());
		});

        	                    	                                       
		getData();
		totoal_number();
		NotificationTable.setItems(dataList);
		searchItems();
	}

//----------------------------------------------------------------------


	@FXML
	public void update(ActionEvent event) {
		try {
			if (OLD_ID.getText() != null) {
				int Notification_ID = Integer.parseInt(OLD_ID.getText());
				
				if (Notification_Date_U.getText().length() > 0) {
					updateNotification_Date(Notification_ID, Notification_Date_U.getText());
				}
	
				if (Notification_method_U.getText().length() > 0) {
					updateNotification_method(Notification_ID, Notification_method_U.getText());
				}
	
				if (CASE_ID_U.getText().length() > 0) {
					updateCASE_ID(Notification_ID, Integer.parseInt(CASE_ID_U.getText()));
				}
				
				if (Notification_ID_U.getText().length() > 0) {
					updateNotification_ID(Notification_ID,Integer.parseInt(Notification_ID_U.getText()));
				}
				OLD_ID.clear();
				Notification_Date_U.clear();
				Notification_method_U.clear();
				CASE_ID_U.clear();
				Notification_ID_U.clear();
				initialize();
				totoal_number();
			
			}
		} catch (Exception e) {
			
		}
	}

//----------------------------------------------------------------------
    @FXML
	public void back(ActionEvent event) {
		if(mainpageController.isManger == 1){
			try {
				stage = (Stage) Back.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("MangerPage.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Manger Page");
				stage.show();
			}
			catch (IOException e1) {
				Message.displayMassage("The button you clicked is wrong, Try again","error");
			}
		}
		
		else {
				try {
					stage = (Stage) Back.getScene().getWindow();
					stage.close();
					root = FXMLLoader.load(getClass().getResource("lawyerPage.fxml"));
					scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Lawyer Page");
					stage.show();
				}
				catch (IOException e1) {
					Message.displayMassage("The button you clicked is wrong, Try again","error");
				}
		}
    }
    
  //----------------------------------------------------------------------

     public void totoal_number() {

			try {
				int num = 0;
				Connector.a.connectDB();
				PreparedStatement st2;
				st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Notification;");
				ResultSet r2 = st2.executeQuery();
				if (r2.next()) {
					num = r2.getInt(1);
				}
				total.setText(num + "");			
				Connector.a.connectDB().close();
			}catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} 
		}
     
//----------------------------------------------------------------------
}
