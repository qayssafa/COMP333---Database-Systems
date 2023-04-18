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

public class Defendant_case_informationController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Defendent_case_information> data;
	private ObservableList<Defendent_case_information> dataList;
	
	@FXML
	private TextField Defendant_ID_name__S;
	@FXML
	private TableView NotificationTable;
	@FXML
	private TableColumn<Defendent , Integer> Defendant_ID;
	@FXML
	private TableColumn<Defendent , String> P_name;
	@FXML
	private TableColumn<Lawyer , String> Lawyer_Name;
	@FXML
	private TableColumn<C_Date , String> Date_of_session;
	@FXML
	private TableColumn<C_Date , String> D_time;
	@FXML
	private TableColumn<Cases , String> case_number_in_the_court;
	@FXML
	private Button Back;

//----------------------------------------------------------------------
	
	public void getData() {
		
		String SQL = "select Defendent.ID_number,Defendent.Defendent_Name,Lawyer.Lawyer_Name,C_Date.Date_of_session,C_Date.D_time,Cases.case_number_in_the_court "
				     	+ "from C_Date,Date2Cases,Cases,Lawyer, Defend,Defendent "
				     	+ "where Date2Cases.Case_id = Cases.Case_id and Date2Cases.Date_id = C_Date.Date_id and Defend.Defendent_ID_number = Defendent.ID_number and \r\n"
				     	+ "Defend.Lawyer_ID = Lawyer.ID_number and Defend.Case_ID = Cases.Case_id "
				     	+ "order by Defendent.ID_number asc "; 
	
					try {
				Connector.a.connectDB();
				java.sql.Statement state = Connector.a.connectDB().createStatement();
				ResultSet rs = state.executeQuery(SQL);
				while (rs.next()) {
					Defendent_case_information it = new Defendent_case_information(rs.getInt(1), rs.getString(2),
							              rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
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
	}
	
//----------------------------------------------------------------------

	public void searchItems() {
		FilteredList<Defendent_case_information> filteredData = new FilteredList<>(dataList, b -> true);
		Defendant_ID_name__S.textProperty().addListener((observable, oldValue, newValue) -> {
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
		SortedList<Defendent_case_information> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(NotificationTable.comparatorProperty());
		NotificationTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		NotificationTable.setEditable(true);
		
		
		Defendant_ID.setCellValueFactory(new PropertyValueFactory<Defendent, Integer>("ID_number"));
		Defendant_ID.setCellFactory(TextFieldTableCell.<Defendent, Integer>forTableColumn(new IntegerStringConverter()));
		Defendant_ID.setOnEditCommit((CellEditEvent<Defendent, Integer> t) -> {
			((Defendent) t.getTableView().getItems().get(t.getTablePosition().getRow())).setID_number(t.getNewValue());
		});

		P_name.setCellValueFactory(new PropertyValueFactory<Defendent, String>("Defendent_Name"));
		P_name.setCellFactory(TextFieldTableCell.<Defendent>forTableColumn());
		P_name.setOnEditCommit((CellEditEvent<Defendent, String> t) -> {
	                      ((Defendent) t.getTableView().getItems().get(t.getTablePosition().getRow())
	                      ).setDefendent_Name(t.getNewValue());});
		        
		Lawyer_Name.setCellValueFactory(new PropertyValueFactory<Lawyer, String>("Lawyer_Name"));
		Lawyer_Name.setCellFactory(TextFieldTableCell.<Lawyer>forTableColumn());
		Lawyer_Name.setOnEditCommit((CellEditEvent<Lawyer, String> t) -> {
	                      ((Lawyer) t.getTableView().getItems().get(t.getTablePosition().getRow())
	                      ).setLawyer_Name(t.getNewValue());});
		
		
		Date_of_session.setCellValueFactory(new PropertyValueFactory<C_Date, String>("Date_of_session"));
		Date_of_session.setCellFactory(TextFieldTableCell.<C_Date>forTableColumn());
		Date_of_session.setOnEditCommit((CellEditEvent<C_Date, String> t) -> {
			((C_Date) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setDate_of_session(t.getNewValue());
		});

		D_time.setCellValueFactory(new PropertyValueFactory<C_Date, String>("D_time"));
		D_time.setCellFactory(TextFieldTableCell.<C_Date>forTableColumn());
		D_time.setOnEditCommit((CellEditEvent<C_Date, String> t) -> {
			((C_Date) t.getTableView().getItems().get(t.getTablePosition().getRow())).setD_time(t.getNewValue());
		});
		case_number_in_the_court.setCellValueFactory(new PropertyValueFactory<Cases, String>("case_number_in_the_court"));
		case_number_in_the_court.setCellFactory(TextFieldTableCell.<Cases>forTableColumn());
		case_number_in_the_court.setOnEditCommit((CellEditEvent<Cases, String> t) -> {
		((Cases) t.getTableView().getItems().get(t.getTablePosition().getRow())
		).setCase_number_in_the_court(t.getNewValue());		});
		getData();
		NotificationTable.setItems(dataList);
		searchItems();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void back(ActionEvent event) {
		try {
			stage = (Stage) Back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Defendent.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Defendent Page");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}	
	}
	
//----------------------------------------------------------------------

}
