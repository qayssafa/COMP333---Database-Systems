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

public class Defendant2Lawyer2CasesController {
	
	private ArrayList<Defendant2Lawer2Cases> data;
	private ObservableList<Defendant2Lawer2Cases> dataList;
	
	@FXML
	private TextField Case_id_D;
	@FXML
	private TextField Defendant_ID_s;
	@FXML
	private TextField Defendant_ID_A;
	@FXML
	private TextField Case_ID_A;
	@FXML
	private TextField Defendant_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button searshByDefendant_ID;
	@FXML
	private Button update;
	@FXML
	private Button delete_Case_ID;
	@FXML
	private TableView DefendTable;
	@FXML
	private TableColumn<Defendant2Lawer2Cases, Integer> Defendant_ID;
	@FXML
	private TableColumn<Defendant2Lawer2Cases, Integer> Case_ID;
	@FXML
	private TableColumn<Defendant2Lawer2Cases, Integer> Lawer_ID;
	@FXML
	private TextField Defendant_ID_U;
	@FXML
	private TextField Case_ID_U;
	@FXML
	private TextField old;
	@FXML
	private Button Back;
	@FXML
	private TextField Case_ID_S;
	@FXML
	private Button searshByCase_ID;
	@FXML
	private Button delete_Lawer_ID;
	@FXML
	private TextField Lawer_ID_S;
	@FXML
	private Button searshByLawer_ID;
	@FXML
	private TextField Lawer_ID_D;
	@FXML
	private Button delete_Defendant_ID;
	@FXML
	private TextField Lawer_ID_A;
	@FXML
	private TextField Lawer_ID_U;

//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
			Defendant2Lawer2Cases rc;
			rc = new Defendant2Lawer2Cases(Integer.parseInt(Defendant_ID_A.getText()),
	              Integer.parseInt(Case_ID_A.getText()),Integer.parseInt(Lawer_ID_A.getText()));

			dataList.add(rc);
			insertData(rc);

			Defendant_ID_A.clear();
			Case_ID_A.clear();
			Lawer_ID_A.clear();
			refresh();

		} catch (Exception e) {

		}
		
	}
	
//----------------------------------------------------------------------

	public void refresh() {
		DefendTable.getItems().clear();
		getData();
		DefendTable.setItems(dataList);
	}
	
//----------------------------------------------------------------------

	private void insertData(Defendant2Lawer2Cases rc) {
		try {
			Connector.a.connectDB();
			String sql = "Insert into Defend (Defendent_ID_number,Case_ID, Lawyer_ID) values(?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1, rc.getDefendent_ID_number());
			ps.setInt(2, rc.getCase_ID());
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
		String SQL = "select * from Defend order by Defendent_ID_number asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Defendant2Lawer2Cases it = new Defendant2Lawer2Cases(rs.getInt(1), rs.getInt(2),rs.getInt(3));
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
				int def_id = Integer.parseInt(old.getText());
				
				if (Case_ID_U.getText().length() > 0) {
					updateCASE_ID(def_id, Integer.parseInt(Case_ID_U.getText()));
				}
				
				if (Lawer_ID_U.getText().length() > 0) {
					updatelawid(def_id,Integer.parseInt(Lawer_ID_U.getText()));
				}
				
				if (Defendant_ID_U.getText().length() > 0) {
					updatedefID(def_id,Integer.parseInt(Defendant_ID_U.getText()));
				}
				old.clear();
				Case_ID_U.clear();
				Lawer_ID_U.clear();
				Defendant_ID_U.clear();
				initialize();
			
			}
		} catch (Exception e) {
			
		}
	}
	
//----------------------------------------------------------------------

	public void updatedefID(int OLD_ID,int def_id) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Defend set Defendent_ID_number = " + def_id + " where Defendent_ID_number = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Defend set Lawyer_ID = " + Lawer_ID_U + " where Defendent_ID_number = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Defend set Case_ID = " + CASE_ID_U + " where Defendent_ID_number = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("delete from  Defend where Defendent_ID_number =" + def_id);
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
	public void delete_Defendant_ID(ActionEvent event) {
		try {
			if (Defendant_ID_D.getText() != null) {
				int Defendant_ID = Integer.parseInt(Defendant_ID_D.getText());
				deleteRow(Defendant_ID);
			}
			Defendant_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
	}
	
//----------------------------------------------------------------------
	
	public void searchItems_bylawyer() {
		FilteredList<Defendant2Lawer2Cases> filteredData = new FilteredList<>(dataList, b -> true);
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
		SortedList<Defendant2Lawer2Cases> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(DefendTable.comparatorProperty());
		DefendTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		DefendTable.setEditable(true);
		
		
		Defendant_ID.setCellValueFactory(new PropertyValueFactory<Defendant2Lawer2Cases, Integer>("Defendent_ID_number"));
		Defendant_ID.setCellFactory(TextFieldTableCell.<Defendant2Lawer2Cases, Integer>forTableColumn(new IntegerStringConverter()));
		Defendant_ID.setOnEditCommit((CellEditEvent<Defendant2Lawer2Cases, Integer> t) -> {
			((Defendant2Lawer2Cases) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDefendent_ID_number(t.getNewValue()); 
			updatedefID(t.getRowValue().getDefendent_ID_number(), t.getNewValue());
		
		});
		
			Case_ID.setCellValueFactory(new PropertyValueFactory<Defendant2Lawer2Cases, Integer>("Case_ID"));
			Case_ID.setCellFactory(TextFieldTableCell.<Defendant2Lawer2Cases,Integer>forTableColumn(new IntegerStringConverter()));
			Case_ID.setOnEditCommit((CellEditEvent<Defendant2Lawer2Cases, Integer> t) -> {        
			  ((Defendant2Lawer2Cases) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setCase_ID(t.getNewValue());     		
			  updateCASE_ID(t.getRowValue().getDefendent_ID_number(), t.getNewValue());
			});
			
		
			Lawer_ID.setCellValueFactory(new PropertyValueFactory<Defendant2Lawer2Cases, Integer>("Lawyer_ID"));
			Lawer_ID.setCellFactory(TextFieldTableCell.<Defendant2Lawer2Cases,Integer>forTableColumn(new IntegerStringConverter()));
			Lawer_ID.setOnEditCommit((CellEditEvent<Defendant2Lawer2Cases, Integer> t) -> {        
			  ((Defendant2Lawer2Cases) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setLawyer_ID(t.getNewValue());     		
			  updatelawid(t.getRowValue().getDefendent_ID_number(), t.getNewValue());
			});

        	                    	                                       
		getData();
		DefendTable.setItems(dataList);
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
