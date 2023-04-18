package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class Plaintiff2Cases2lawyerController {
	
	private ArrayList<Plaintiff2Cases2lawyer> data;
	private ObservableList<Plaintiff2Cases2lawyer> dataList;
	
	@FXML
	private TextField Case_id_D;
	@FXML
	private TextField Plaintiff_ID_s;
	@FXML
	private TextField Plaintiff_ID_A;
	@FXML
	private TextField Case_ID_A;
	@FXML
	private TextField Plaintiff_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button update;
	@FXML
	private Button delete_Case_ID;
	@FXML
	private TableView NotificationTable;
	@FXML
	private TableColumn<Plaintiff2Cases2lawyer, Integer> Plaintiff_ID;
	@FXML
	private TableColumn<Plaintiff2Cases2lawyer, Integer> Case_ID;
	@FXML
	private TableColumn<Plaintiff2Cases2lawyer, Integer> Lawer_ID;
	@FXML
	private TextField Plaintiff_ID_U;
	@FXML
	private TextField Case_ID_U;
	@FXML
	private Button Back;
	@FXML
	private TextField Case_ID_S;
	@FXML
	private Button delete_Lawer_ID;
	@FXML
	private TextField Lawer_ID_S;
	@FXML
	private TextField Lawer_ID_D;
	@FXML
	private Button delete_Plaintiff_ID;
	@FXML
	private TextField Lawer_ID_A;
	@FXML
	private TextField Lawer_ID_U;
	@FXML
	private TextField old;

//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
			Plaintiff2Cases2lawyer rc;
			rc = new Plaintiff2Cases2lawyer(Integer.parseInt(Plaintiff_ID_A.getText()),
	              Integer.parseInt(Case_ID_A.getText()),Integer.parseInt(Lawer_ID_A.getText()));

			dataList.add(rc);
			insertData(rc);

			Plaintiff_ID_A.clear();
			Case_ID_A.clear();
			Lawer_ID_A.clear();
			refresh();

		} catch (Exception e) {

		}
		
	}
	
//----------------------------------------------------------------------

	public void refresh() {
		NotificationTable.getItems().clear();
		getData();
		NotificationTable.setItems(dataList);
	}
	
//----------------------------------------------------------------------

	private void insertData(Plaintiff2Cases2lawyer rc) {
		try {
			Connector.a.connectDB();
			String sql = "Insert into Plaintiff2Cases2lawyer (P_id,Case_id, Lawyer_ID) values(?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1, rc.getP_id());
			ps.setInt(2, rc.getCase_id());
			ps.setInt(3, rc.getLawyer_ID());
			ps.execute();
			
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
	}
	
//----------------------------------------------------------------------

	public void getData() {
		String SQL = "select * from Plaintiff2Cases2lawyer order by P_id asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Plaintiff2Cases2lawyer it = new Plaintiff2Cases2lawyer(rs.getInt(1), rs.getInt(2),rs.getInt(3));
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
	}
	
//----------------------------------------------------------------------
	
	@FXML
	public void update(ActionEvent event) {
		try {
			if (old.getText() != null) {
				int p_id = Integer.parseInt(old.getText());
				
				if (Case_ID_U.getText().length() > 0) {
					updateCASE_ID(p_id, Integer.parseInt(Case_ID_U.getText()));
				}
				
				if (Lawer_ID_U.getText().length() > 0) {
					updatelawid(p_id,Integer.parseInt(Lawer_ID_U.getText()));
				}
				
				if (Plaintiff_ID_U.getText().length() > 0) {
					updateplaintiffID(p_id,Integer.parseInt(Plaintiff_ID_U.getText()));
				}
				old.clear();
				Case_ID_U.clear();
				Lawer_ID_U.clear();
				Plaintiff_ID_U.clear();
				initialize();
			
			}
		} catch (Exception e) {
			
		}
	}
	
//----------------------------------------------------------------------

	public void updateplaintiffID(int OLD_ID,int Plaintiff_ID) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Plaintiff2Cases2lawyer set P_id = " + Plaintiff_ID_U + " where P_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatelawid(int OLD_ID , int Lawer_ID_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Plaintiff2Cases2lawyer set Lawyer_ID = " + Lawer_ID_U + " where P_id = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Plaintiff2Cases2lawyer set Case_id = " + CASE_ID_U + " where P_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}

//----------------------------------------------------------------------

	private void deleteRow(int def_id) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Plaintiff2Cases2lawyer where P_id =" + def_id);
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
	}
	
//----------------------------------------------------------------------
	
	@FXML
	public void delete_Case_ID(ActionEvent event) {
		try {
			if (Case_id_D.getText() != null) {
				int Case_id = Integer.parseInt(Case_id_D.getText());
				deleteRow(Case_id);
			}
			Case_id_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
	}
	
//----------------------------------------------------------------------

	
	@FXML
	public void delete_Lawer_ID(ActionEvent event) {
		try {
			if (Lawer_ID_D.getText() != null) {
				int Lawer_ID = Integer.parseInt(Lawer_ID_D.getText());
				deleteRow(Lawer_ID);
			}
			Lawer_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void delete_Plaintiff_ID(ActionEvent event) {
		try {
			if (Plaintiff_ID_D.getText() != null) {
				int Plaintiff_ID = Integer.parseInt(Plaintiff_ID_D.getText());
				deleteRow(Plaintiff_ID);
			}
			Plaintiff_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
	}
	
//----------------------------------------------------------------------

	public void searchItems_bylawyer() {
		FilteredList<Plaintiff2Cases2lawyer> filteredData = new FilteredList<>(dataList, b -> true);
		Lawer_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getLawyer_ID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else
					return false; // Does not match.
			});
		});
		SortedList<Plaintiff2Cases2lawyer> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(NotificationTable.comparatorProperty());
		NotificationTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		NotificationTable.setEditable(true);
		
		
		Plaintiff_ID.setCellValueFactory(new PropertyValueFactory<Plaintiff2Cases2lawyer, Integer>("P_id"));
		Plaintiff_ID.setCellFactory(TextFieldTableCell.<Plaintiff2Cases2lawyer, Integer>forTableColumn(new IntegerStringConverter()));
		Plaintiff_ID.setOnEditCommit((CellEditEvent<Plaintiff2Cases2lawyer, Integer> t) -> {
			((Plaintiff2Cases2lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setP_id(t.getNewValue()); 
			updateplaintiffID(t.getRowValue().getP_id(), t.getNewValue());
		
		});
		
			Case_ID.setCellValueFactory(new PropertyValueFactory<Plaintiff2Cases2lawyer, Integer>("Case_id"));
			Case_ID.setCellFactory(TextFieldTableCell.<Plaintiff2Cases2lawyer,Integer>forTableColumn(new IntegerStringConverter()));
			Case_ID.setOnEditCommit((CellEditEvent<Plaintiff2Cases2lawyer, Integer> t) -> {        
			  ((Plaintiff2Cases2lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setCase_id(t.getNewValue());     		
			  updateCASE_ID(t.getRowValue().getP_id(), t.getNewValue());
			});
			
		
			Lawer_ID.setCellValueFactory(new PropertyValueFactory<Plaintiff2Cases2lawyer, Integer>("Lawyer_ID"));
			Lawer_ID.setCellFactory(TextFieldTableCell.<Plaintiff2Cases2lawyer,Integer>forTableColumn(new IntegerStringConverter()));
			Lawer_ID.setOnEditCommit((CellEditEvent<Plaintiff2Cases2lawyer, Integer> t) -> {        
			  ((Plaintiff2Cases2lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setLawyer_ID(t.getNewValue());     		
			  updatelawid(t.getRowValue().getP_id(), t.getNewValue());
			});

        	                    	                                       
		getData();
		NotificationTable.setItems(dataList);
		searchItems_bylawyer();
		
	}

//----------------------------------------------------------------------
	
@FXML
public void back(ActionEvent event) {
 	try { // open new stage
		Stage stage;
		Parent root;
		stage = (Stage) Back.getScene().getWindow();
		stage.close();
		root = FXMLLoader.load(getClass().getResource("Relationships.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Relationships Page");
		stage.show();
		
	} catch (IOException e1) {
		Message.displayMassage("The button you clicked is wrong, Try again","error");
	}
}
//----------------------------------------------------------------------
}
