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
import javafx.stage.Stage;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
	
public class DefendentController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Defendent> data;
	private ObservableList<Defendent> dataList;
	
	@FXML
	private Label total;
	@FXML
	private TextField Defendant_ID_S;
	@FXML
	private TextField Defendant_ID_A;
	@FXML
	private TextField Name_A;
	@FXML
	private TextField Address_A;
	@FXML
	private TextField phone_number_A;
	@FXML
	private TextField Defendant_ID_U;
	@FXML
	private TextField Name_U;
	@FXML
	private TextField Address_U;
	@FXML
	private TextField phone_number_U;
	@FXML
	private TextField Defendant_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button searsh;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TableView DefendentTable;
	@FXML
	private TableColumn<Defendent, Integer> Defendant_ID;
	@FXML
	private TableColumn<Defendent, String> Name;
	@FXML
	private TableColumn<Defendent, String> Address;
	@FXML
	private TableColumn<Defendent, String> phone_number;
	@FXML
	private Button Back;
	@FXML
	private TextField old;
	@FXML
	private Button more_information;
	
//----------------------------------------------------------------------

	@FXML
	public void add(ActionEvent event) {
		try {
			Defendent rc;
			rc = new Defendent(Integer.parseInt(Defendant_ID_A.getText()),
					Name_A.getText(), Address_A.getText(),phone_number_A.getText());

			dataList.add(rc);
			insertData(rc);
			

			Defendant_ID_A.clear();
			Name_A.clear();
			Address_A.clear();
			phone_number_A.clear();
			
			refresh();
			totoal_number();
		} catch (Exception e) {
		}
		
		
	}

//----------------------------------------------------------------------
	
	private void deleteRow(int def_id) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Defendent where ID_number =" + def_id);
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
	}
	
//----------------------------------------------------------------------

	@FXML
	public void delete(ActionEvent event) {

		try {
			if (Defendant_ID_D.getText() != null) {
				int def_id = Integer.parseInt(Defendant_ID_D.getText());
				deleteRow(def_id);
			}
			Defendant_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
		totoal_number();
	}
	
//----------------------------------------------------------------------
    
	private void insertData(Defendent rc) {

		try {
			
			Connector.a.connectDB();
			String sql = "Insert into Defendent (ID_number,Defendent_Name,Address,Phone_number) values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);

			ps.setInt(1, rc.getID_number());
			ps.setString(2, rc.getDefendent_Name());
			ps.setString(3, rc.getAddress());
			ps.setString(4, rc.getPhone_number());
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
		DefendentTable.getItems().clear();
		getData();
		totoal_number();
		DefendentTable.setItems(dataList);
	}

//----------------------------------------------------------------------
	
	public void getData() {
		String SQL = "select * from Defendent order by ID_number asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Defendent it = new Defendent(rs.getInt(1), rs.getString(2),
						              rs.getString(3), rs.getString(4));
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

	@FXML
	public void update(ActionEvent event) {
		try {
			if (old.getText() != null) {
				int def_id = Integer.parseInt(old.getText());
				
				if (phone_number_U.getText().length() > 0) {
					updatephone(def_id, phone_number_U.getText());
				}

				if (Address_U.getText().length() > 0) {
					updateaddress(def_id, Address_U.getText());
				}

				if (Name_U.getText().length() > 0) {
					updatename(def_id, Name_U.getText());
				}
				
				if (Defendant_ID_U.getText().length() > 0) {
					updatedef_id(def_id,Integer.parseInt(Defendant_ID_U.getText()));
				}
				old.clear();
				phone_number_U.clear();
				Address_U.clear();
				Name_U.clear();
				Defendant_ID_U.clear();
				initialize();
				totoal_number();
			
			}
		} catch (Exception e) {

		}
	}
	
//----------------------------------------------------------------------

	public void updatedef_id(int OLD_ID,int def_id) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Defendent set ID_number = " + def_id + " where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------
	
	public void updatename(int OLD_ID , String newname ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Defendent set Defendent_Name = '" + newname + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}

//----------------------------------------------------------------------
	
	public void updateaddress(int OLD_ID , String newadd ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Defendent set Address = '" + newadd + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatephone(int OLD_ID , String newphone ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Defendent set Phone_number = '" + newphone + "' where ID_number = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void searchItems() {
		FilteredList<Defendent> filteredData = new FilteredList<>(dataList, b -> true);
		Defendant_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getID_number()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (item.getDefendent_Name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				}
				else
					return false; // Does not match.
			});
		});
		SortedList<Defendent> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(DefendentTable.comparatorProperty());
		DefendentTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		DefendentTable.setEditable(true);
		
		
		Defendant_ID.setCellValueFactory(new PropertyValueFactory<Defendent, Integer>("ID_number"));
		Defendant_ID.setCellFactory(TextFieldTableCell.<Defendent, Integer>forTableColumn(new IntegerStringConverter()));
		Defendant_ID.setOnEditCommit((CellEditEvent<Defendent, Integer> t) -> {
			((Defendent) t.getTableView().getItems().get(t.getTablePosition().getRow())).setID_number(t.getNewValue()); 
			updatedef_id(t.getRowValue().getID_number(), t.getNewValue());
		});

		Name.setCellValueFactory(new PropertyValueFactory<Defendent, String>("Defendent_Name"));
		Name.setCellFactory(TextFieldTableCell.<Defendent>forTableColumn());
		Name.setOnEditCommit((CellEditEvent<Defendent, String> t) -> {
	                      ((Defendent) t.getTableView().getItems().get(t.getTablePosition().getRow())
	                      ).setDefendent_Name(t.getNewValue());
	                      updatename(t.getRowValue().getID_number(), t.getNewValue());
		});
		        
		Address.setCellValueFactory(new PropertyValueFactory<Defendent, String>("Address"));
		Address.setCellFactory(TextFieldTableCell.<Defendent>forTableColumn());
		Address.setOnEditCommit((CellEditEvent<Defendent, String> t) -> {
	    ((Defendent) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
	    updateaddress(t.getRowValue().getID_number(), t.getNewValue());
		});
		
		
		phone_number.setCellValueFactory(new PropertyValueFactory<Defendent, String>("Phone_number"));
		phone_number.setCellFactory(TextFieldTableCell.<Defendent>forTableColumn());
		phone_number.setOnEditCommit((CellEditEvent<Defendent, String> t) -> {
	    ((Defendent) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone_number(t.getNewValue());
	    updatephone(t.getRowValue().getID_number(), t.getNewValue());
		});

        	                    	                                       
		getData();
		totoal_number();
		DefendentTable.setItems(dataList);
		searchItems();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void back(ActionEvent event) {
		try {
			stage = (Stage) Back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Customrs.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Customrs Page");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}
	}
	
//----------------------------------------------------------------------

	public void totoal_number() {

		try {
			int num = 0;
			Connector.a.connectDB();
			PreparedStatement st2;
			st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Defendent;");
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
				num = r2.getInt(1);
			}
			total.setText(num + "");			
			Connector.a.connectDB().close();
		}catch (ClassNotFoundException e) {
			Message.displayMassage("Warning!!!","Warning");
			e.printStackTrace();
		} catch (SQLException e) {
			Message.displayMassage("Warning!!!","Warning");
			e.printStackTrace();
		} 
	}
	
//----------------------------------------------------------------------
	
	@FXML
	public void more_information(ActionEvent event) {
		try {
			stage = (Stage) Back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Defendant_case_information.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Defendant case information");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}
	}
	
//----------------------------------------------------------------------
}
