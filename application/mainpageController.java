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

public class mainpageController {
	static int isManger = 0;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button admin;
	@FXML
	private Button lawer;

//----------------------------------------------------------------------

	@FXML
	public void adminbutton(ActionEvent event)throws IOException {
		  isManger = 1;
		  Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Login page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void lawerbutton(ActionEvent event)throws IOException {
		  isManger = 0;
		  Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Login page");
		  stage.show();	
	}
	
//----------------------------------------------------------------------

}
