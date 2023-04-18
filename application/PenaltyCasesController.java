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

public class PenaltyCasesController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<PenaltyCases> data;
	private ObservableList<PenaltyCases> dataList;
	
	@FXML
	private Label total;
	@FXML
	private TextField Case_ID_s;
	@FXML
	private TextField case_id_A;
	@FXML
	private TextField Penalty_type_A;
	@FXML
	private TextField Court_type_A;
	@FXML
	private TextField Case_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TableView NotificationTable;
	@FXML
	private TableColumn<PenaltyCases , Integer> Case_ID;
	@FXML
	private TableColumn<PenaltyCases , String> Penalty_type;
	@FXML
	private TableColumn<PenaltyCases , String> Court_type;
	@FXML
	private TableColumn<PenaltyCases , String> Case_description;
	@FXML
	private TextField Case_description_A;
	@FXML
	private TextField CASE_ID_U;
	@FXML
	private TextField Penalty_type_U;
	@FXML
	private TextField Court_type_U;
	@FXML
	private TextField Case_description_U;
	@FXML
	private TextField OldCase_ID;
	@FXML
	private Button Back;

//----------------------------------------------------------------------

	public void refresh() {
		NotificationTable.getItems().clear();
		getData();
		NotificationTable.setItems(dataList);
		
		}
	
//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
				PenaltyCases rc;
			rc = new PenaltyCases(Integer.parseInt(case_id_A.getText()),
					Penalty_type_A.getText(), Court_type_A.getText(),Case_description_A.getText());
			
			dataList.add(rc);
			insertData(rc);
			
	
			case_id_A.clear();
			Penalty_type_A.clear();
			Court_type_A.clear();
			Case_description_A.clear();
			
			refresh();
			
			} catch (Exception e) {
			
			}
			totoal_number();
	}
		
//----------------------------------------------------------------------

	private void insertData(PenaltyCases rc) {
		try {
			Connector.a.connectDB();
			String sql = "Insert into Penalty_cases (Case_id, penalty_type,court_type, case_description) values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
				
			ps.setInt(1, rc.getCase_id());
			ps.setString(2, rc.getPenalty_type());
			ps.setString(3, rc.getCourt_type());
			ps.setString(4, rc.getCase_description());
			ps.execute();
				
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		totoal_number();
	}
		
//----------------------------------------------------------------------

		public void getData() {
			String SQL = "select * from Penalty_cases order by Case_id asc";
			try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				PenaltyCases it = new PenaltyCases(rs.getInt(1), rs.getString(2),
			rs.getString(3),rs.getString(4));
			dataList.add(it);
			}
			rs.close();
			state.close();
			Connector.a.connectDB().close();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} catch (SQLException e) {
			e.printStackTrace();
			}
			totoal_number();
		}
		
//----------------------------------------------------------------------

		@FXML
		public void update(ActionEvent event) {
			try {
				if (OldCase_ID.getText() != null) {
					int old_case = Integer.parseInt(OldCase_ID.getText());
					if (Case_description_U.getText().length() > 0) {
						updatecasedescription(old_case, Case_description_U.getText());
					}
					
					if (Court_type_U.getText().length() > 0) {
						updatecourttype(old_case, Court_type_U.getText());
					}
					
					if (Penalty_type_U.getText().length() > 0) {
						updatepenaltytype(old_case, Penalty_type_U.getText());
					}
					
					if (CASE_ID_U.getText().length() > 0) {
						updatecaseID(old_case,Integer.parseInt(CASE_ID_U.getText()));
					}
					OldCase_ID.clear();
					Case_description_U.clear();
					Court_type_U.clear();
					Penalty_type_U.clear();
					CASE_ID_U.clear();
					initialize();
	
				}
			} catch (Exception e) {
			
			}
				totoal_number();
	}
		
//----------------------------------------------------------------------

		public void updatecaseID(int old_id,int Date_ID_U) {
			try {
				Connector.a.connectDB();
				Connector.a.ExecuteStatement("update Penalty_cases set Case_id = " + Date_ID_U + " where Case_id = " + old_id + ";");
				Connector.a.connectDB().close();
				
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			}
		}
		
//----------------------------------------------------------------------

	public void updatecasedescription(int old_id , String case_description ) {
		try {
		Connector.a.connectDB();
		Connector.a.ExecuteStatement("update Penalty_cases set case_description = '" + case_description + "' where Case_id = " + old_id + ";");
		Connector.a.connectDB().close();
		
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		
	}
//----------------------------------------------------------------------

	public void updatecourttype(int old_id , String court_type ) {
		try {
		Connector.a.connectDB();
		Connector.a.ExecuteStatement("update Penalty_cases set court_type = '" + court_type + "' where Case_id = " + old_id + ";");
		Connector.a.connectDB().close();
		
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		
	}
//----------------------------------------------------------------------

		public void updatepenaltytype(int old_id , String penalty_type ) {
			try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Penalty_cases set penalty_type = '" + penalty_type + "' where Case_id = " + old_id + ";");
			Connector.a.connectDB().close();
			
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			}
			
		}
		
//----------------------------------------------------------------------

		private void deleteRow(int Case_id) {
			try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Penalty_cases where Case_id =" + Case_id);
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
				if (Case_ID_D.getText() != null) {
				int Case_ID = Integer.parseInt(Case_ID_D.getText());
				deleteRow(Case_ID);
				}
				Case_ID_D.clear();
			} catch (Exception e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			}
			initialize();
		}
		
//----------------------------------------------------------------------
		
		public void searchItems() {
			FilteredList<PenaltyCases> filteredData = new FilteredList<>(dataList, b -> true);
			Case_ID_s.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();
			
			if (String.valueOf(item.getCase_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
			} else
					return false; // Does not match.
			});
			});
			SortedList<PenaltyCases> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(NotificationTable.comparatorProperty());
			NotificationTable.setItems(sortedData);
			totoal_number();
		}
		
//----------------------------------------------------------------------
			
		public void initialize() {
			data = new ArrayList<>();
			dataList = FXCollections.observableArrayList(data);
			NotificationTable.setEditable(true);
			
			
			Case_ID.setCellValueFactory(new PropertyValueFactory<PenaltyCases, Integer>("Case_id"));
			Case_ID.setCellFactory(TextFieldTableCell.<PenaltyCases, Integer>forTableColumn(new IntegerStringConverter()));
			Case_ID.setOnEditCommit((CellEditEvent<PenaltyCases, Integer> t) -> {
			((PenaltyCases) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCase_id(t.getNewValue()); 
			updatecaseID(t.getRowValue().getCase_id(), t.getNewValue());
			});
			
			Penalty_type.setCellValueFactory(new PropertyValueFactory<PenaltyCases, String>("penalty_type"));
			Penalty_type.setCellFactory(TextFieldTableCell.<PenaltyCases>forTableColumn());
			Penalty_type.setOnEditCommit((CellEditEvent<PenaltyCases, String> t) -> {
			((PenaltyCases) t.getTableView().getItems().get(t.getTablePosition().getRow())
			).setPenalty_type(t.getNewValue());
			updatepenaltytype(t.getRowValue().getCase_id(), t.getNewValue());
			});
			
			Court_type.setCellValueFactory(new PropertyValueFactory<PenaltyCases, String>("court_type"));
			Court_type.setCellFactory(TextFieldTableCell.<PenaltyCases>forTableColumn());
			Court_type.setOnEditCommit((CellEditEvent<PenaltyCases, String> t) -> {
			((PenaltyCases) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCourt_type(t.getNewValue());
			updatecourttype(t.getRowValue().getCase_id(), t.getNewValue());
			});	 
			
			Case_description.setCellValueFactory(new PropertyValueFactory<PenaltyCases, String>("case_description"));
			Case_description.setCellFactory(TextFieldTableCell.<PenaltyCases>forTableColumn());
			Case_description.setOnEditCommit((CellEditEvent<PenaltyCases, String> t) -> {
			((PenaltyCases) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCase_description(t.getNewValue());
			updatecasedescription(t.getRowValue().getCase_id(), t.getNewValue());
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
				root = FXMLLoader.load(getClass().getResource("cases.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Cases Page");
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
				st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Penalty_cases;");
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
