package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class casesController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Cases> data;
	private ObservableList<Cases> dataList;
	
	@FXML
	private Button back;
	@FXML
	private TableView NotificationTable;
	@FXML
	private TableColumn<Cases, Integer> Case_id;
	@FXML
	private TableColumn<Cases, String> Case_number_in_the_court;
	@FXML
	private Button Right;
	@FXML
	private Button Resumption;
	@FXML
	private Button Penalty;
	@FXML
	private Button execution;
	@FXML
	private TextField Case_ID_s;
	@FXML
	private TextField Case_id_A;
	@FXML
	private TextField Case_number_in_the_court_A;
	@FXML
	private Button add;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TextField Case_id_U;
	@FXML
	private TextField Case_number_in_the_court_U;
	@FXML
	private TextField Case_id_D;
	@FXML
	private TextField Old_Case_ID;
	@FXML
    private Button statistic;
	
//----------------------------------------------------------------------		
	
	@FXML
	public void Right(ActionEvent event) {
			try {
				stage = (Stage) Right.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("Rights_cases.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Rights cases");
				stage.show();
			}
			catch (IOException e1) {
				Message.displayMassage("There is no table for the selected button, Try again","error");
			}
		}
	
//----------------------------------------------------------------------		
	
	@FXML	
	public void Resumption(ActionEvent event) {
			try {
				stage = (Stage) Resumption.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("Resumption_cases.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Resumption cases");
				stage.show();
			}
			catch (IOException e1) {
				Message.displayMassage("There is no table for the selected button, Try again","error");
			}
		}
	
//----------------------------------------------------------------------		
	
	@FXML
	public void Penalty(ActionEvent event) {
			try {
				stage = (Stage) Penalty.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("Penalty_cases.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Penalty cases");
				stage.show();
			}
			catch (IOException e1) {
				Message.displayMassage("There is no table for the selected button, Try again","error");
			}
	}
	
//----------------------------------------------------------------------		
	
	@FXML
	public void execution(ActionEvent event) {
			try {
				stage = (Stage) execution.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("ExecutionCases.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Execution cases");
				stage.show();
			}
			catch (IOException e1) {
				Message.displayMassage("There is no table for the selected button, Try again","error");
			}
	}
	
//----------------------------------------------------------------------		
		
	public void getData() {
			String SQL = "select * from Cases order by Case_id asc";
			try {
				Connector.a.connectDB();
				java.sql.Statement state = Connector.a.connectDB().createStatement();
				ResultSet rs = state.executeQuery(SQL);
				while (rs.next()) {
					Cases it = new Cases(rs.getInt(1), rs.getString(2));
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
		
	public void refresh() {
			NotificationTable.getItems().clear();
			getData();
			NotificationTable.setItems(dataList);
		}
		
//----------------------------------------------------------------------
			
	@FXML
	public void add(ActionEvent event) {
		try {
				Cases rc;
			rc = new Cases(Integer.parseInt(Case_id_A.getText()),Case_number_in_the_court_A.getText());			
			dataList.add(rc);
			insertData(rc);
			Case_id_A.clear();
			Case_number_in_the_court_A.clear();
			refresh();
			
			} catch (Exception e) {
				
			}
			
	}
		
//----------------------------------------------------------------------
			
	private void insertData(Cases rc) {
		try {
				Connector.a.connectDB();
				String sql = "Insert into Cases (Case_id, case_number_in_the_court) values(?,?)";
				PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
				
				ps.setInt(1, rc.getCase_id());
				ps.setString(2, rc.getCase_number_in_the_court());
				ps.execute();
				
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			}
	}
			
		
//----------------------------------------------------------------------			
	
	@FXML
	public void update(ActionEvent event) {
		try {
			if (Old_Case_ID.getText() != null) {
			int old_case = Integer.parseInt(Old_Case_ID.getText());
			
			if (Case_number_in_the_court_U.getText().length() > 0) {
			updatecasenumberincourt(old_case, Case_number_in_the_court_U.getText());
			}

			if (Case_id_U.getText().length() > 0) {
			updatecaseID(old_case,Integer.parseInt(Case_id_U.getText()));
			}
			Old_Case_ID.clear();
			Case_number_in_the_court_U.clear();
			Case_id_U.clear();
			initialize();
			
		}
			} catch (Exception e) {

			}
	}
	
//----------------------------------------------------------------------
			
	public void updatecaseID(int old_id,int Date_ID_U) {
	try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Cases set Case_id = " + Date_ID_U + " where Case_id = " + old_id + ";");
			Connector.a.connectDB().close();
			
		} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
			
	}
			
//----------------------------------------------------------------------
			
	public void updatecasenumberincourt(int old_id , String updatecasenumberincourt ) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Cases set case_number_in_the_court = '" + updatecasenumberincourt + "' where Case_id = " + old_id + ";");
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
			Connector.a.ExecuteStatement("delete from  Cases where Case_id =" + Case_id);
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
			if (Case_id_D.getText() != null) {
			int Case_ID = Integer.parseInt(Case_id_D.getText());
			deleteRow(Case_ID);
			}
			Case_id_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
			initialize();
	}
			
//----------------------------------------------------------------------
			
	public void searchItems() {
		FilteredList<Cases> filteredData = new FilteredList<>(dataList, b -> true);
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
		SortedList<Cases> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(NotificationTable.comparatorProperty());
		NotificationTable.setItems(sortedData);
	}
			
//----------------------------------------------------------------------
			
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		NotificationTable.setEditable(true);
				
				
		Case_id.setCellValueFactory(new PropertyValueFactory<Cases, Integer>("Case_id"));
		Case_id.setCellFactory(TextFieldTableCell.<Cases, Integer>forTableColumn(new IntegerStringConverter()));
		Case_id.setOnEditCommit((CellEditEvent<Cases, Integer> t) -> {
		((Cases) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCase_id(t.getNewValue()); 
		updatecaseID(t.getRowValue().getCase_id(), t.getNewValue());
		});
				
		Case_number_in_the_court.setCellValueFactory(new PropertyValueFactory<Cases, String>("case_number_in_the_court"));
		Case_number_in_the_court.setCellFactory(TextFieldTableCell.<Cases>forTableColumn());
		Case_number_in_the_court.setOnEditCommit((CellEditEvent<Cases, String> t) -> {
		((Cases) t.getTableView().getItems().get(t.getTablePosition().getRow())
		).setCase_number_in_the_court(t.getNewValue());
		updatecasenumberincourt(t.getRowValue().getCase_id(), t.getNewValue());
		});
					                    	                                       
		getData();
		NotificationTable.setItems(dataList);
		searchItems();
	}
	
//----------------------------------------------------------------------
			
	@FXML
	public void back(ActionEvent event) {
		if(mainpageController.isManger == 1){
			try {
				stage = (Stage) back.getScene().getWindow();
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
				stage = (Stage) back.getScene().getWindow();
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
	
	@FXML
	public void statistic(ActionEvent event) {
	    	try {
				Stage stage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("CasesStatistics.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Cases Statistics");
				stage.show();

			}catch (Exception e) {
				Message.displayMassage("The button you clicked is wrong, Try again","error");
			}
	    	
	    }
	 
//----------------------------------------------------------------------
}
