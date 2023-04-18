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

public class Date2CasesController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Date2Cases> data;
	private ObservableList<Date2Cases> dataList;
	
	@FXML
	private Label total;
	@FXML
	private TextField Date_ID_S;
	@FXML
	private TextField Date_ID_A;
	@FXML
	private TextField Case_ID_A;
	@FXML
	private TextField Date_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button searshByDate_ID;
	@FXML
	private Button update;
	@FXML
	private Button delete_Date_ID;
	@FXML
	private TableView ReservationTable;
	@FXML
	private TableColumn<Date2Cases,Integer> Date_ID;
	@FXML
	private TableColumn<Date2Cases,Integer> Case_ID;
	@FXML
	private TextField Date_ID_U;
	@FXML
	private TextField Case_ID_U;
	@FXML
	private Button Back;
	@FXML
	private TextField Case_ID_S;
	@FXML
	private TextField old;
	
	@FXML
	private Button searshByCase_ID;
	

//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
			Date2Cases rc;
			rc = new Date2Cases(Integer.parseInt(Date_ID_A.getText()),
	              Integer.parseInt(Case_ID_A.getText()));

			dataList.add(rc);
			insertData(rc);

			Date_ID_A.clear();
			Case_ID_A.clear();
			refresh();

		} catch (Exception e) {

		}
		totoal_number();
		
	}
	
//----------------------------------------------------------------------
	
	public void refresh() {
		ReservationTable.getItems().clear();
		getData();
		ReservationTable.setItems(dataList);
	}
	
//----------------------------------------------------------------------

	private void insertData(Date2Cases rc) {
		try {
			Connector.a.connectDB();
			String sql = "Insert into Date2Cases (Date_id, Case_id) values(?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1, rc.getDate_id());
			ps.setInt(2, rc.getCase_id());
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
		String SQL = "select * from Date2Cases order by Date_id asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Date2Cases it = new Date2Cases(rs.getInt(1), rs.getInt(2));
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

	public void searchItems_byCASE() {
		FilteredList<Date2Cases> filteredData = new FilteredList<>(dataList, b -> true);
		Case_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
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
		SortedList<Date2Cases> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(ReservationTable.comparatorProperty());
		ReservationTable.setItems(sortedData);
	}

//----------------------------------------------------------------------
	
	@FXML
	public void update(ActionEvent event) {
		try {
			if (old.getText() != null) {
				int date_id = Integer.parseInt(old.getText());
				
				if (Case_ID_U.getText().length() > 0) {
					updateCASE_ID(date_id, Integer.parseInt(Case_ID_U.getText()));
				}
				
				if (Date_ID_U.getText().length() > 0) {
					updatedateID(date_id,Integer.parseInt(Date_ID_U.getText()));
				}
				old.clear();
				Case_ID_U.clear();
				Date_ID_U.clear();
				initialize();
			
			}
		} catch (Exception e) {
			
		}
		totoal_number();
	}
	
//----------------------------------------------------------------------

	public void updatedateID(int OLD_ID,int DATE_ID) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Date2Cases set Date_id = " + DATE_ID + " where Date_id = " + OLD_ID + ";");
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
			Connector.a.ExecuteStatement("update Date2Cases set Case_id = " + CASE_ID_U + " where Date_id = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------
	
	private void deleteRow(int Date_id) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Date2Cases where Date_id =" + Date_id);
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
			if (Date_ID_D.getText() != null) {
				int Date_ID = Integer.parseInt(Date_ID_D.getText());
				deleteRow(Date_ID);
			}
			Date_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
		totoal_number();
	}
	
//----------------------------------------------------------------------

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		ReservationTable.setEditable(true);
		
		
		Date_ID.setCellValueFactory(new PropertyValueFactory<Date2Cases, Integer>("Date_id"));
		Date_ID.setCellFactory(TextFieldTableCell.<Date2Cases, Integer>forTableColumn(new IntegerStringConverter()));
		Date_ID.setOnEditCommit((CellEditEvent<Date2Cases, Integer> t) -> {
			((Date2Cases) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDate_id(t.getNewValue()); 
			updatedateID(t.getRowValue().getDate_id(), t.getNewValue());
		
		});
		
			Case_ID.setCellValueFactory(new PropertyValueFactory<Date2Cases, Integer>("Case_id"));
			Case_ID.setCellFactory(TextFieldTableCell.<Date2Cases,Integer>forTableColumn(new IntegerStringConverter()));
			Case_ID.setOnEditCommit((CellEditEvent<Date2Cases, Integer> t) -> {        
			  ((Date2Cases) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setCase_id(t.getNewValue());     		
			   updateCASE_ID(t.getRowValue().getDate_id(), t.getNewValue());
			});

        	                    	                                       
		getData();
		totoal_number();
		ReservationTable.setItems(dataList);
		searchItems_byCASE();
		
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
			st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Date2Cases;");
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

