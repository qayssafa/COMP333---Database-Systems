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

public class LawyerPageController {
	@FXML
	private Button Customers;
	@FXML
	private Button Logout;
	@FXML
	private Button Reservations;
	@FXML
	private Button Cases;
	@FXML
	private Button Notifications;
	@FXML
	private Button date;
	
	private Stage stage;
	private Scene scene;

//----------------------------------------------------------------------

	@FXML
	public void Customers(ActionEvent event)throws IOException {
		
		 Parent root = FXMLLoader.load(getClass().getResource("Customrs.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Customers page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void LogOut(ActionEvent event)throws IOException {
		
		 Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Main Page");
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
		
		 Parent root = FXMLLoader.load(getClass().getResource("cases.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Cases page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void Notifications(ActionEvent event) throws IOException {
		
		 Parent root = FXMLLoader.load(getClass().getResource("Notifications.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Notifications page");
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
}

