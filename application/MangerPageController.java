package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import javafx.event.ActionEvent;

public class MangerPageController {
	private Stage stage;
	private Scene scene;
	@FXML
	private Button Customers;
	@FXML
	private Button Logout;
	@FXML
	private Button Lawyer;
	@FXML
	private Button Reservations;
	@FXML
	private Button Cases;
	@FXML
	private Button Notifications;
	@FXML
	private Button Payment;
	@FXML
	private Button date;
	@FXML
	private Button relation;
	
//----------------------------------------------------------------------

	@FXML
	public void Customers(ActionEvent event)throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Customrs.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Customrs Page");
		  stage.show();
	}

//----------------------------------------------------------------------

	@FXML
	public void LogOut(ActionEvent event) throws IOException{
		
		 Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Main Page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void Lawyer(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(getClass().getResource("Lawyer.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Employees page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void Reservations(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Reservations.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Reservations page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void Cases(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Cases.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Cases page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void Notifications(ActionEvent event) throws IOException{

		Parent root = FXMLLoader.load(getClass().getResource("Notifications.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Notifications page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void Payment(ActionEvent event) throws IOException{

		Parent root = FXMLLoader.load(getClass().getResource("Payement.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Payment page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void date(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Date.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Dates page");
		  stage.show();
	}
	
//----------------------------------------------------------------------
	
	@FXML
	public void relation(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Relationships.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Dates page");
		  stage.show();
	}
	
//----------------------------------------------------------------------
}
