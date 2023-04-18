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


public class PlaintiffController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Plaintiff> data;
	private ObservableList<Plaintiff> dataList;
	
	@FXML
	private Label total;
	@FXML
	private TextField Plaintiff_ID_S;
	@FXML
	private TextField Plaintiff_ID_A;
	@FXML
	private TextField Name_A;
	@FXML
	private TextField Address_A;
	@FXML
	private TextField phone_number_A;
	@FXML
	private TextField Plaintiff_ID_U;
	@FXML
	private TextField Name_U;
	@FXML
	private TextField Address_U;
	@FXML
	private TextField phone_number_U;
	@FXML
	private TextField Plaintiff_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button searsh;
	@FXML
	private Button update;
	@FXML
	private Button Back;
	@FXML
	private Button delete;
	@FXML
	private TableView NotificationTable;
	@FXML
	private TableColumn<Plaintiff, Integer> Plaintiff_ID;
	@FXML
	private TableColumn<Plaintiff, String> Name;
	@FXML
	private TableColumn<Plaintiff, String> Address;
	@FXML
	private TableColumn<Plaintiff, String> phone_number;
	@FXML
	private TextField old;
	@FXML
	private Button more_information;

//----------------------------------------------------------------------

	@FXML
	public void add(ActionEvent event) {
		try {
			Plaintiff rc;
			rc = new Plaintiff(Integer.parseInt(Plaintiff_ID_A.getText()),
					Name_A.getText(), Address_A.getText(),phone_number_A.getText());

			dataList.add(rc);
			insertData(rc);
			

			Plaintiff_ID_A.clear();
			Name_A.clear();
			Address_A.clear();
			phone_number_A.clear();
			refresh();
		} catch (Exception e) {

		}
		totoal_number();
		
	}

//----------------------------------------------------------------------

	private void deleteRow(int def_id) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Plaintiff where P_id =" + def_id);
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
			if (Plaintiff_ID_D.getText() != null) {
				int def_id = Integer.parseInt(Plaintiff_ID_D.getText());
				deleteRow(def_id);
			}
			Plaintiff_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");

		}
		initialize();
		totoal_number();
	}
	
//----------------------------------------------------------------------

	private void insertData(Plaintiff rc) {
		try {
			
			Connector.a.connectDB();
			String sql = "Insert into Plaintiff (P_id,P_name,P_address,P_phone_number) values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);

			ps.setInt(1, rc.getP_id());
			ps.setString(2, rc.getP_name());
			ps.setString(3, rc.getP_address());
			ps.setString(4, rc.getP_phone_number());
			ps.execute();
			
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
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
		String SQL = "select * from Plaintiff order by P_id asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Plaintiff it = new Plaintiff(rs.getInt(1), rs.getString(2),
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
				
				if (Plaintiff_ID_U.getText().length() > 0) {
					updatep_id(def_id,Integer.parseInt(Plaintiff_ID_U.getText()));
				}
				old.clear();
				phone_number_U.clear();
				Address_U.clear();
				Name_U.clear();
				Plaintiff_ID_U.clear();
				initialize();
			
			}
		} catch (Exception e) {
			
		}
		totoal_number();
	}
	
//----------------------------------------------------------------------

	public void updatep_id(int OLD_ID,int p_id) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Plaintiff set P_id = " + p_id + " where P_id = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Plaintiff set P_name = '" + newname + "' where P_id = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Plaintiff set P_address = '" + newadd + "' where P_id = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Plaintiff set P_phone_number = '" + newphone + "' where P_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void searchItems() {
		FilteredList<Plaintiff> filteredData = new FilteredList<>(dataList, b -> true);
		Plaintiff_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getP_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if (item.getP_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				}
				else
					return false; // Does not match.
			});
		});
		SortedList<Plaintiff> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(NotificationTable.comparatorProperty());
		NotificationTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------
	
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		NotificationTable.setEditable(true);
		
		
		Plaintiff_ID.setCellValueFactory(new PropertyValueFactory<Plaintiff, Integer>("P_id"));
		Plaintiff_ID.setCellFactory(TextFieldTableCell.<Plaintiff, Integer>forTableColumn(new IntegerStringConverter()));
		Plaintiff_ID.setOnEditCommit((CellEditEvent<Plaintiff, Integer> t) -> {
			((Plaintiff) t.getTableView().getItems().get(t.getTablePosition().getRow())).setP_id(t.getNewValue()); 
			updatep_id(t.getRowValue().getP_id(), t.getNewValue());
		});

		Name.setCellValueFactory(new PropertyValueFactory<Plaintiff, String>("P_name"));
		Name.setCellFactory(TextFieldTableCell.<Plaintiff>forTableColumn());
		Name.setOnEditCommit((CellEditEvent<Plaintiff, String> t) -> {
	                      ((Plaintiff) t.getTableView().getItems().get(t.getTablePosition().getRow())
	                      ).setP_name(t.getNewValue());
	                      updatename(t.getRowValue().getP_id(), t.getNewValue());
		});
		        
		Address.setCellValueFactory(new PropertyValueFactory<Plaintiff, String>("P_address"));
		Address.setCellFactory(TextFieldTableCell.<Plaintiff>forTableColumn());
		Address.setOnEditCommit((CellEditEvent<Plaintiff, String> t) -> {
	    ((Plaintiff) t.getTableView().getItems().get(t.getTablePosition().getRow())).setP_address(t.getNewValue());
	    updateaddress(t.getRowValue().getP_id(), t.getNewValue());
		});
		
		
		phone_number.setCellValueFactory(new PropertyValueFactory<Plaintiff, String>("P_phone_number"));
		phone_number.setCellFactory(TextFieldTableCell.<Plaintiff>forTableColumn());
		phone_number.setOnEditCommit((CellEditEvent<Plaintiff, String> t) -> {
	    ((Plaintiff) t.getTableView().getItems().get(t.getTablePosition().getRow())).setP_phone_number(t.getNewValue());
	    updatephone(t.getRowValue().getP_id(), t.getNewValue());
		});

        	                    	                                       
		getData();
		totoal_number();
		NotificationTable.setItems(dataList);
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
			st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Plaintiff;");
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
	@FXML
	public void more_information(ActionEvent event) {
		try {
			stage = (Stage) Back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Plaintiff_case_information.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Plaintiff case information");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}
	}
	
//----------------------------------------------------------------------
}
