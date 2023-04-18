package application;

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
import javafx.util.converter.IntegerStringConverter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class LawyerController {
	private ArrayList<Lawyer> data;
	private ObservableList<Lawyer> dataList;
	
	@FXML
	private Label total_f;
	@FXML
	private Label total_m;
	@FXML
	private TextField ID_number_s;
	@FXML
	private TextField ID_number_A;
	@FXML
	private TextField Name_A;
	@FXML
	private TextField Address_A;
	@FXML
	private TextField Phone_number_A;
	@FXML
	private TextField ID_number_d;
	@FXML
	private Button add;
	@FXML
	private Button searsh;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TableView LawyerTable;
	@FXML
	private TableColumn<Lawyer, Integer> ID_number;
	@FXML
	private TableColumn<Lawyer, String> Name;
	@FXML
	private TableColumn<Lawyer, String> Address;
	@FXML
	private TableColumn<Lawyer, String> Phone_number;
	@FXML
	private TableColumn<Lawyer, String> Email;
	@FXML
	private TableColumn<Lawyer, String> password;
	@FXML
	private TableColumn<Lawyer, String> Gender;
	@FXML
	private TableColumn<Lawyer, Integer> Manger_ssn;
	@FXML
	private TextField Email_A;
	@FXML
	private TextField password_A;
	@FXML
	private TextField Gender_A;
	@FXML
	private TextField Manger_ssn_A;
	@FXML
	private TextField ID_number_U;
	@FXML
	private TextField Name_U;
	@FXML
	private TextField Address_U;
	@FXML
	private TextField Phone_number_U;
	@FXML
	private TextField Email_U;
	@FXML
	private TextField password_U;
	@FXML
	private TextField Gender_U;
	@FXML
	private TextField Manger_ssn_U;
	@FXML
	private TextField OldID_number;
	@FXML
	private Button back;
	@FXML
	private Button statics_pla;
	@FXML
	private Button statics_def;
	
//----------------------------------------------------------------------
	
	public void refresh() {
		LawyerTable.getItems().clear();
		getData();
		totoal_number_m();
		totoal_number_f();
		LawyerTable.setItems(dataList);

	};
	
//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
			Lawyer rc;
			rc = new Lawyer(Integer.parseInt(ID_number_A.getText()),
					Name_A.getText(), Address_A.getText(),Phone_number_A.getText(),Email_A.getText(),
					password_A.getText(),Gender_A.getText(),
					Integer.parseInt(Manger_ssn_A.getText()));

			dataList.add(rc);
			insertData(rc);
			
			ID_number_A.clear();
			Name_A.clear();
			Address_A.clear();
			Phone_number_A.clear();
			Email_A.clear();
			password_A.clear();
			Gender_A.clear();
			Manger_ssn_A.clear();
			refresh();
		} catch (Exception e) {
		}
		totoal_number_m();
		totoal_number_f();
		
	}
	
//----------------------------------------------------------------------

	@FXML
	public void update(ActionEvent event) {
		try {
			if (OldID_number.getText() != null) {
				int OLD = Integer.parseInt(OldID_number.getText());
				
				if (Name_U.getText().length() > 0) {
					updateLawyer_name(OLD, Name_U.getText());
				}

				if (Address_U.getText().length() > 0) {
					updateAddress(OLD, Address_U.getText());
				}
				
				if (Phone_number_U.getText().length() > 0) {
					updatephone(OLD, Phone_number_U.getText());
				}
				
				if (Email_U.getText().length() > 0) {
					updateemail(OLD, Email_U.getText());
				}
				
				if (password_U.getText().length() > 0) {
					updatepassword(OLD, password_U.getText());
				}
				
				if (Gender_U.getText().length() > 0) {
					updategender(OLD, Gender_U.getText());
				}

				if (Manger_ssn_U.getText().length() > 0) {
					updatemanager_number(OLD, Integer.parseInt(Manger_ssn_U.getText()));
				}
				
				if (ID_number_U.getText().length() > 0) {
					updateID_number(OLD,Integer.parseInt(ID_number_U.getText()));
				}
				
				OldID_number.clear();
				Name_U.clear();
				Address_U.clear();
				Phone_number_U.clear();
				Email_U.clear();
				password_U.clear();
				Gender_U.clear();
				Manger_ssn_U.clear();
				ID_number_U.clear();
				initialize();
			}
		} catch (Exception e) {
			
		}
		totoal_number_m();
		totoal_number_f();
	}
	
//----------------------------------------------------------------------

	public void updateID_number(int OLD_ID , int ID_number ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Lawyer set ID_number = "+ ID_number +" where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatemanager_number(int OLD_ID , int manager_ssn ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set manager_ssn = " + manager_ssn + " where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updateLawyer_name(int OLD_ID , String Name_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set Lawyer_Name = '" + Name_U + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updateAddress(int OLD_ID , String Address_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set Address = '" + Address_U + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatephone(int OLD_ID , String phone_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set Phone_number = '" + phone_U + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updateemail(int OLD_ID , String email_u ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set Email_registered = '" + email_u + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatepassword(int OLD_ID , String pass_u ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set L_Password = '" + pass_u + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updategender(int OLD_ID , String gender_u ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Lawyer set Gender = '" + gender_u + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	private void deleteRow(int ID_number) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Lawyer where ID_number =" + ID_number);
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		totoal_number_m();
		totoal_number_f();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void delete(ActionEvent event) {

		try {
			if (ID_number_d.getText() != null) {
				int id_number = Integer.parseInt(ID_number_d.getText());
				deleteRow(id_number);
			}
			ID_number_d.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
		totoal_number_m();
		totoal_number_f();
	}
	
//----------------------------------------------------------------------
	
	@FXML
	public void back(ActionEvent event) {
	 	try { // open new stage
    		Stage stage;
    		Parent root;
    		stage = (Stage) back.getScene().getWindow();
    		stage.close();
    		root = FXMLLoader.load(getClass().getResource("MangerPage.fxml"));
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("Manger Page");
    		stage.show();
    		
    	} catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
    	}
	}

//----------------------------------------------------------------------

public void getData() {
	String SQL = "select * from Lawyer order by ID_number asc";
	try {
		Connector.a.connectDB();
		java.sql.Statement state = Connector.a.connectDB().createStatement();
		ResultSet rs = state.executeQuery(SQL);
		while (rs.next()) {
			Lawyer it = new Lawyer(rs.getInt(1), rs.getString(2),
					              rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),
					              rs.getString(7),rs.getInt(8));
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
	totoal_number_m();
	totoal_number_f();
}

//----------------------------------------------------------------------

private void insertData(Lawyer rc) {

	try {
			
		Connector.a.connectDB();
		String sql = "Insert into Lawyer (ID_number, Lawyer_Name, Address , Phone_number , Email_registered , "
				+ "L_Password, Gender, manager_ssn) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);

		ps.setInt(1, rc.getID_number());
		ps.setString(2, rc.getLawyer_Name());
		ps.setString(3, rc.getAddress());
		ps.setString(4, rc.getPhone_number());
		ps.setString(5, rc.getEmail_registered());
		ps.setString(6, rc.getL_Password());
		ps.setString(7, rc.getGender());
		ps.setInt(8, rc.getManager_ssn());
		ps.execute();
		
	} catch (SQLException e) {
		Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
	} catch (ClassNotFoundException e) {
		Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
	}
	totoal_number_m();
	totoal_number_f();
}

//----------------------------------------------------------------------

public void initialize() {
	data = new ArrayList<>();
	dataList = FXCollections.observableArrayList(data);
	LawyerTable.setEditable(true);
	
	
	ID_number.setCellValueFactory(new PropertyValueFactory<Lawyer, Integer>("ID_number"));
	ID_number.setCellFactory(TextFieldTableCell.<Lawyer, Integer>forTableColumn(new IntegerStringConverter()));
	ID_number.setOnEditCommit((CellEditEvent<Lawyer, Integer> t) -> {
		((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setID_number(t.getNewValue()); 
		
		updateID_number(t.getRowValue().getID_number(), t.getNewValue());
	});

	Name.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("Lawyer_Name"));
	Name.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
	Name.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
                      ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())
                      ).setLawyer_Name(t.getNewValue());   
              updateLawyer_name(t.getRowValue().getID_number(), t.getNewValue());});
	
	Address.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("Address")); 
	Address.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
	Address.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
   ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
             
   updateAddress( t.getRowValue().getID_number(),t.getNewValue());
    		});       
	Phone_number.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("Phone_number"));
	Phone_number.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
	Phone_number.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
                      ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())
                      ).setPhone_number(t.getNewValue());
                      updatephone(t.getRowValue().getID_number(), t.getNewValue());
	});
	
	Email.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("Email_registered"));
	Email.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
	Email.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
                      ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())
                      ).setEmail_registered(t.getNewValue());
                     
                      updateemail(t.getRowValue().getID_number(), t.getNewValue());
	});
	
	password.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("L_Password"));
	password.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
	password.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
                      ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())
                      ).setL_Password(t.getNewValue());
                     
                      updatepassword(t.getRowValue().getID_number(), t.getNewValue());
	});
	
	Gender.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("Gender"));
	Gender.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
	Gender.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
                      ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())
                      ).setGender(t.getNewValue());
                     
                      updategender(t.getRowValue().getID_number(), t.getNewValue());
	});
	
	
	Manger_ssn.setCellValueFactory(new PropertyValueFactory<Lawyer, Integer>("manager_ssn"));
	Manger_ssn.setCellFactory(TextFieldTableCell.<Lawyer,Integer>forTableColumn(new IntegerStringConverter()));
	Manger_ssn.setOnEditCommit((CellEditEvent<Lawyer, Integer> t) -> {        
	((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setManager_ssn(t.getNewValue());     		
	
	           updatemanager_number(t.getRowValue().getID_number(), t.getNewValue());
	           });
	  	                    	                                       
		getData();
		totoal_number_m();
		totoal_number_f();
		LawyerTable.setItems(dataList);
		searchItems();
	}

//----------------------------------------------------------------------

public void searchItems() {
	FilteredList<Lawyer> filteredData = new FilteredList<>(dataList, b -> true);
	ID_number_s.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(item -> {
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

			if (String.valueOf(item.getID_number()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
			}
			else if (item.getLawyer_Name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; // Filter matches password
			}
			else
				return false; // Does not match.
		});
	});
	SortedList<Lawyer> sortedData = new SortedList<>(filteredData);
	sortedData.comparatorProperty().bind(LawyerTable.comparatorProperty());
	LawyerTable.setItems(sortedData);
}
	
//----------------------------------------------------------------------

	public void totoal_number_m() {
	
		try {
			int num= 0;
			Connector.a.connectDB();
			PreparedStatement st2;
			st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Lawyer where Lawyer.Gender = 'male' group by Gender;");
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
				num = r2.getInt(1);
			}
			total_m.setText(num + "");
			Connector.a.connectDB().close();
		}catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} 
	}
	
//----------------------------------------------------------------------

	public void totoal_number_f() {
		
		try {
			int num= 0;
			Connector.a.connectDB();
			PreparedStatement st2;
			st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Lawyer where Lawyer.Gender = 'female' group by Gender;");
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
				num = r2.getInt(1);
			}
			total_f.setText(num + "");
			Connector.a.connectDB().close();
		}catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} 
	}
//----------------------------------------------------------------------
	@FXML
	public void statics_def(ActionEvent event) {
	    	try {
				Stage stage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("Lawyer_DefendentStatistics.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Lawyer Defendents Statistics");
				stage.show();

			}catch (Exception e) {
				Message.displayMassage("The button you clicked is wrong, Try again","error");
			}
	    	
	    }
	
//----------------------------------------------------------------------

	@FXML
	public void statics_pla(ActionEvent event) {
	    	try {
				Stage stage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("Lawyer_PlaintiffStatistics.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Lawyer Plaintiff Statistics");
				stage.show();

			}catch (Exception e) {
				Message.displayMassage("The button you clicked is wrong, Try again","error");
			}
	    	
	    }
	
//----------------------------------------------------------------------
}
