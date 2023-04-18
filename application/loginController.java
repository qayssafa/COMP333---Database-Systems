package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;

public class loginController {
	public static String AEmail;
	public static String APassword;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Button login;
	@FXML
	private Button back;
	@FXML
	private TextField password;
	@FXML
	private TextField email;
	
//----------------------------------------------------------------------

	@FXML
	public void back(ActionEvent event)throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Main page");
		  stage.show();
	}
	
//----------------------------------------------------------------------

	@FXML
	public void login(ActionEvent event)throws IOException {
		try {
			PreparedStatement re = Connector.a.connectDB().prepareStatement("select * from Lawyer where Email_registered = ? AND L_Password = ? ");
			re.setString(1, email.getText());
			re.setString(2, password.getText());
			ResultSet result = re.executeQuery();
			if (email.getText().isEmpty()) {
				Message.displayMassage("Please enter the Email","Warning");
				return;
			}
			if (password.getText().isEmpty()) {
				Message.displayMassage("Please enter the Password","Warning");
				return;
			}
			
				
			if(mainpageController.isManger == 1){
				if (email.getText().equals("Fakhri Abu Bishara") && password.getText().equals("1122")) {
					try {
						stage = (Stage) login.getScene().getWindow();
						stage.close();
						root = FXMLLoader.load(getClass().getResource("MangerPage.fxml"));
						scene = new Scene(root, 901, 649);
						stage.setScene(scene);
						stage.setTitle("Manger Page");
						stage.show();
					}
					catch (IOException e1) {
						Message.displayMassage("There is no account at this Email and Password, Try again","error");
					}
				}
				else{
					Message.displayMassage( "There is no account at this Eamil and Password, Try again","error");
				}
			}
			else {
				if (result.next()) {
						if (result.getString(5).toLowerCase().equals(email.getText().toLowerCase()) && (result.getString(6).toLowerCase().equals(password.getText().toLowerCase()))){
							AEmail = email.getText().toLowerCase();
							APassword = password.getText().toLowerCase();
							Manegar.mang.setName(AEmail);
							Manegar.mang.setPassword(APassword);
							try {
								stage = (Stage) login.getScene().getWindow();
								stage.close();
								root = FXMLLoader.load(getClass().getResource("lawyerPage.fxml"));
								scene = new Scene(root, 901, 649);
								stage.setScene(scene);
								stage.setTitle("Lawyer Page");
								stage.show();
							}
							catch (IOException e1) {
								Message.displayMassage("There is no account at this Email and Password, Try again","error");
							}
						}			
				} else{
					Message.displayMassage( "There is no account at this Eamil and Password, Try again","error");
				}
			}
		}
		catch (ClassNotFoundException e1){
			e1.printStackTrace();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
//----------------------------------------------------------------------
}
