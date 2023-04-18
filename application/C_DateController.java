package application;

import java.text.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class C_DateController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<C_Date> data;
	private ObservableList<C_Date> dataList;
	
	@FXML
	private TextField Date_ID_S;
	@FXML
	private TextField Date_ID_A;
	@FXML
	private TextField Time_A;
	@FXML
	private TextField Date_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button searsh;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TableColumn<C_Date, Integer> Date_ID;
	@FXML
	private TableColumn<C_Date, String> Date_of_session;
	@FXML
	private TableColumn<C_Date, String> Time;
	@FXML
	private TextField Date_ID_U;
	@FXML
	private TextField Time_U;
	@FXML
	private Button Back;
	@FXML
	private TextField Date_session_A;
	@FXML
	private TextField Date_session_U;
	@FXML
	private TextField old;
	@FXML
	private TableView DateTable;

//----------------------------------------------------------------------

	public void refresh() {
		DateTable.getItems().clear();
		getData();
		DateTable.setItems(dataList);
	}

//----------------------------------------------------------------------
	
	@FXML
	public void add(ActionEvent event) {
		try {
			C_Date rc;
			rc = new C_Date(Integer.parseInt(Date_ID_A.getText()), Date_session_A.getText(), Time_A.getText());
			dataList.add(rc);
			insertData(rc);
			Date_ID_A.clear();
			Date_session_A.clear();
			Time_A.clear();
			refresh();
		}catch (Exception e) {
			
		}
	}
	
//----------------------------------------------------------------------

	private void insertData(C_Date rc) {

		try {

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date myDate = null;
			java.sql.Date sqlDate;

			try {
				myDate = formatter.parse(rc.getDate_of_session());

			} catch (Exception e) {
				Message.displayMassage("Please confirm the values of DATE","Warning");
			}

			sqlDate = new java.sql.Date(myDate.getTime());
			Connector.a.connectDB();
			String sql = "Insert into C_Date (Date_id, Date_of_session, D_time) values(?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setInt(1, rc.getDate_id());
			ps.setTimestamp(2, new java.sql.Timestamp(myDate.getTime()));
			ps.setString(3, rc.getD_time());
			ps.execute();
			
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");

		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
	}

//----------------------------------------------------------------------
	
	public void getData() {
		String SQL = "select * from C_Date order by Date_id asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				C_Date it = new C_Date(rs.getInt(1), rs.getString(2), rs.getString(3));
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
	public void searsh(ActionEvent event) {
	}

//----------------------------------------------------------------------

	@FXML
	public void update(ActionEvent event) {
		try {
			if (old.getText() != null) {
				int old_date = Integer.parseInt(old.getText());

				if (Date_session_U.getText().length() > 0) {
					updatesessiondate(old_date, Date_session_U.getText());
				}

				if (Time_U.getText().length() > 0) {
					updatetime(old_date, Time_U.getText());
				}

				if (Date_ID_U.getText().length() > 0) {
					updatedateID(old_date, Integer.parseInt(Date_ID_U.getText()));
				}
				old.clear();
				Date_session_U.clear();
				Time_U.clear();
				Date_ID_U.clear();
				initialize();

			}
		} catch (Exception e) {
			
		}
	}

	
//----------------------------------------------------------------------

	public void updatedateID(int old_date, int Date_ID_U) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update C_Date set Date_id = " + Date_ID_U + " where Date_id = " + old_date + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatesessiondate(int old_date, String date_session_U) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update C_Date set Date_of_session = '" + date_session_U + "' where Date_id = " + old_date + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatetime(int old_date, String time_u) {

		try {
			Connector.a.connectDB();
			Connector.a
					.ExecuteStatement("update C_Date set D_time = '" + time_u + "' where Date_id = " + old_date + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	private void deleteRow(int Date_ID) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  C_Date where Date_id =" + Date_ID);
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
			if (Date_ID_D.getText() != null) {
				int Date_ID = Integer.parseInt(Date_ID_D.getText());
				deleteRow(Date_ID);
			}
			Date_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
	}

//----------------------------------------------------------------------
	
	public void searchItems() {
		FilteredList<C_Date> filteredData = new FilteredList<>(dataList, b -> true);
		Date_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getDate_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false; // Does not match.
			});
		});
		SortedList<C_Date> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(DateTable.comparatorProperty());
		DateTable.setItems(sortedData);
	}

//----------------------------------------------------------------------

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		DateTable.setEditable(true);

		Date_ID.setCellValueFactory(new PropertyValueFactory<C_Date, Integer>("Date_id"));
		Date_ID.setCellFactory(TextFieldTableCell.<C_Date, Integer>forTableColumn(new IntegerStringConverter()));
		Date_ID.setOnEditCommit((CellEditEvent<C_Date, Integer> t) -> {
			((C_Date) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDate_id(t.getNewValue());
			updatedateID(t.getRowValue().getDate_id(), t.getNewValue());
		});

		Date_of_session.setCellValueFactory(new PropertyValueFactory<C_Date, String>("Date_of_session"));
		Date_of_session.setCellFactory(TextFieldTableCell.<C_Date>forTableColumn());
		Date_of_session.setOnEditCommit((CellEditEvent<C_Date, String> t) -> {
			((C_Date) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setDate_of_session(t.getNewValue());
			updatesessiondate(t.getRowValue().getDate_id(), t.getNewValue());
		});

		Time.setCellValueFactory(new PropertyValueFactory<C_Date, String>("D_time"));
		Time.setCellFactory(TextFieldTableCell.<C_Date>forTableColumn());
		Time.setOnEditCommit((CellEditEvent<C_Date, String> t) -> {
			((C_Date) t.getTableView().getItems().get(t.getTablePosition().getRow())).setD_time(t.getNewValue());
			updatetime(t.getRowValue().getDate_id(), t.getNewValue());
		});
		getData();
		DateTable.setItems(dataList);
		searchItems();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void back(ActionEvent event) {
		if (mainpageController.isManger == 1) {
			try {
				stage = (Stage) Back.getScene().getWindow();
				stage.close();
				root = FXMLLoader.load(getClass().getResource("MangerPage.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Manger Page");
				stage.show();
			} catch (IOException e1) {
				Message.displayMassage("The button you clicked is wrong, Try again", "error");
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
			} catch (IOException e1) {
				Message.displayMassage("The button you clicked is wrong, Try again", "error");
			}
		}
	}
	
//----------------------------------------------------------------------
}
