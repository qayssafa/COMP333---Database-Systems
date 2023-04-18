package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class CustomrsController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Button Defendants;
	@FXML
	private Button Plaintiff;
	@FXML
	private Button back;
	@FXML
	private Button char_s;

//----------------------------------------------------------------------
	
	@FXML
	public void Defendants(ActionEvent event) {
		try {
			stage = (Stage) back.getScene().getWindow();
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
	
	@FXML
	public void Plaintiff(ActionEvent event) {
		try {
			stage = (Stage) back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Plaintiff.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Plaintiff Page");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}
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
	public void char_s(ActionEvent event) {
	    	try {
				Stage stage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("pieChart.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Chart");
				stage.show();

			}catch (Exception e) {
				Message.displayMassage("The button you clicked is wrong, Try again","error");
			}
	    	
	    }
	
//----------------------------------------------------------------------
}
