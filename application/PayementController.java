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
import javafx.util.converter.DoubleStringConverter;
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

public class PayementController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<Payement> data;
	private ObservableList<Payement> dataList;
	
	@FXML
	private Label total;
	@FXML
	private TextField payment_ID_S;
	@FXML
	private TextField payment_ID_A;
	@FXML
	private TextField Payment_method_A;
	@FXML
	private TextField currency_A;
	@FXML
	private TextField payment_ID_U;
	@FXML
	private TextField Payment_method_U;
	@FXML
	private TextField currency_U;
	@FXML
	private TextField payment_ID_D;
	@FXML
	private Button add;
	@FXML
	private Button searsh;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TableView PayementTable;
	@FXML
	private TableColumn<Payement, Integer> payment_ID;
	@FXML
	private TableColumn<Payement, String> Payment_method;
	@FXML
	private TableColumn<Payement, String> currency;
	@FXML
	private TableColumn<Payement, Double> Amount;
	@FXML
	private Button back;
	@FXML
	private TextField Amount_A;
	@FXML
	private TextField Amount_U;
	@FXML
	private TextField old;

//----------------------------------------------------------------------

	@FXML
	public void add(ActionEvent event) {
		try {
			Payement rc;
			rc = new Payement(Integer.parseInt(payment_ID_A.getText()),
					currency_A.getText(), Payment_method_A.getText(),
					Double.parseDouble(Amount_A.getText()));

			dataList.add(rc);
			insertData(rc);
			

			payment_ID_A.clear();
			currency_A.clear();
			Payment_method_A.clear();
			Amount_A.clear();
			refresh();
		} catch (Exception e) {

		}
		totoal_number();
		
	}

//----------------------------------------------------------------------
	
	private void deleteRow(int pay_id) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Payement where Payement_ID =" + pay_id);
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
			if (payment_ID_D.getText() != null) {
				int pay_id = Integer.parseInt(payment_ID_D.getText());
				deleteRow(pay_id);
			}
			payment_ID_D.clear();
		} catch (Exception e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}
		initialize();
	}
	
//----------------------------------------------------------------------

	private void insertData(Payement rc) {
		try {
			
			Connector.a.connectDB();
			String sql = "Insert into Payement (Payement_ID, Currency, Payment_Method, Amount) values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			
			ps.setInt(1, rc.getPayement_ID());
			ps.setString(2, rc.getCurrency());			
			ps.setString(3, rc.getPayment_Method());
			ps.setDouble(4, rc.getAmount());
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
		PayementTable.getItems().clear();
		getData();
		totoal_number();
		PayementTable.setItems(dataList);
	}
	
//----------------------------------------------------------------------
	
	public void getData() {
		String SQL = "select * from Payement order by Payement_ID asc";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Payement it = new Payement(rs.getInt(1), rs.getString(2),
						              rs.getString(3), rs.getDouble(4));
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
				int pay_id = Integer.parseInt(old.getText());
				
				if (currency_U.getText().length() > 0) {
					updatecurrency(pay_id, currency_U.getText());
				}

				if (Payment_method_U.getText().length() > 0) {
					updatepaymethod(pay_id, Payment_method_U.getText());
				}

				if (Amount_U.getText().length() > 0) {
					updateAmount(pay_id, Double.parseDouble(Amount_U.getText()));
				}
				
				if (payment_ID_U.getText().length() > 0) {
					updatePayement_ID(pay_id,Integer.parseInt(payment_ID_U.getText()));
				}
				old.clear();
				currency_U.clear();
				Payment_method_U.clear();
				Amount_U.clear();
				payment_ID_U.clear();
				initialize();
			
			}
		} catch (Exception e) {
			
		}
		totoal_number();
	}
	
//----------------------------------------------------------------------
	
	public void updatePayement_ID(int OLD_ID,int pay_id) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Payement set Payement_ID = " + pay_id + " where Payement_ID = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updatecurrency(int OLD_ID , String Currency ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update Payement set Currency = '" + Currency + "' where Payement_ID = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}

//----------------------------------------------------------------------

	public void updatepaymethod(int OLD_ID , String paymethod ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Payement set Payment_Method = '" + paymethod + "' where Payement_ID = " + OLD_ID + ";");
			Connector.a.connectDB().close();
		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void updateAmount(int OLD_ID , double CASE_ID_U ) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Payement set Amount = " + CASE_ID_U + " where Payement_ID = " + OLD_ID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		} catch (ClassNotFoundException e) {
			Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
		}

	}
	
//----------------------------------------------------------------------

	public void searchItems() {
		FilteredList<Payement> filteredData = new FilteredList<>(dataList, b -> true);
		payment_ID_S.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(item -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(item.getPayement_ID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else
					return false; // Does not match.
			});
		});
		SortedList<Payement> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(PayementTable.comparatorProperty());
		PayementTable.setItems(sortedData);
	}
	
//----------------------------------------------------------------------
	
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		PayementTable.setEditable(true);
		
		
		payment_ID.setCellValueFactory(new PropertyValueFactory<Payement, Integer>("Payement_ID"));
		payment_ID.setCellFactory(TextFieldTableCell.<Payement, Integer>forTableColumn(new IntegerStringConverter()));
		payment_ID.setOnEditCommit((CellEditEvent<Payement, Integer> t) -> {
			((Payement) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPayement_ID(t.getNewValue()); 
			updatePayement_ID(t.getRowValue().getPayement_ID(), t.getNewValue());
		});

		currency.setCellValueFactory(new PropertyValueFactory<Payement, String>("Currency"));
		currency.setCellFactory(TextFieldTableCell.<Payement>forTableColumn());
		currency.setOnEditCommit((CellEditEvent<Payement, String> t) -> {
	                      ((Payement) t.getTableView().getItems().get(t.getTablePosition().getRow())
	                      ).setCurrency(t.getNewValue());
	                      updatecurrency(t.getRowValue().getPayement_ID(), t.getNewValue());
		});
		        
		Payment_method.setCellValueFactory(new PropertyValueFactory<Payement, String>("Payment_Method"));
		Payment_method.setCellFactory(TextFieldTableCell.<Payement>forTableColumn());
		Payment_method.setOnEditCommit((CellEditEvent<Payement, String> t) -> {
	    ((Payement) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPayment_Method(t.getNewValue());
	    updatepaymethod(t.getRowValue().getPayement_ID(), t.getNewValue());
		});
		
		
		Amount.setCellValueFactory(new PropertyValueFactory<Payement, Double>("Amount"));
		Amount.setCellFactory(TextFieldTableCell.<Payement,Double>forTableColumn(new DoubleStringConverter()));
		Amount.setOnEditCommit((CellEditEvent<Payement, Double> t) -> {        
		  ((Payement) t.getTableView().getItems().get(t.getTablePosition().getRow()) ).setAmount(t.getNewValue());     		
		  updateAmount(t.getRowValue().getPayement_ID(), t.getNewValue());
		});

        	                    	                                       
		getData();
		totoal_number();
		PayementTable.setItems(dataList);
		searchItems();
	}

//----------------------------------------------------------------------

	@FXML
	public void back(ActionEvent event) {
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
			Message.displayMassage("There is no table for the selected button, Try again","error");
		}
	}
	
//----------------------------------------------------------------------

	public void totoal_number() {

		try {
			int num = 0;
			Connector.a.connectDB();
			PreparedStatement st2;
			st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Payement;");
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

