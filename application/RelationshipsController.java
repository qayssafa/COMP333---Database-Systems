package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class RelationshipsController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Button Defendant_Lawyer_Cases;
	@FXML
	private Button back;
	@FXML
	private Button Plaintiff_Cases_lawyer;
	@FXML
	private Button Paymant;
	
//----------------------------------------------------------------------
	
	@FXML
	public void Paymant(ActionEvent event) {
		try {
			stage = (Stage) back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("payement2cases2lawer.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Payment_cases_lawyer Page");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}
	}

//----------------------------------------------------------------------
	
	@FXML
	public void Defendant_Lawyer_Cases(ActionEvent event) {
		try {
			stage = (Stage) back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Defendant2Lawer2Cases.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Defendant_Lawyer_Cases Page");
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
	public void Plaintiff_Cases_lawyer(ActionEvent event) {
		try {
			stage = (Stage) back.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Plaintiff2Cases2lawyer.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Plaintiff_Cases_lawyer Page");
			stage.show();
		}
		catch (IOException e1) {
			Message.displayMassage("The button you clicked is wrong, Try again","error");
		}
	}
	
//----------------------------------------------------------------------
}
