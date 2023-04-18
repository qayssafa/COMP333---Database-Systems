package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class CasesStatisticsController implements Initializable{

    @FXML
    private StackedBarChart<String,Integer> barChart;
    
    String name1 = "Resumption cases";
   	String name2 = "Rights cases";
   	String name3 = "Penalty cases";
   	String name4 = "Execution cases";
   	int num1 = 0;
   	int num2 = 0;
   	int num3 = 0;
   	int num4 = 0;	
   	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	totoal_number_Resumption();
    	totoal_number_Rights();
    	totoal_number_Penalty();
    	totoal_number_Execution();
    	
		int i = 1;
		while (i<5) {
			XYChart.Series<String,Integer> s = new XYChart.Series<>();
			if(i==1) {
				s.getData().add(new XYChart.Data(name1,num1));
				barChart.getData().addAll(s);
			}
			if(i==2) {
				s.getData().add(new XYChart.Data(name2,num2));
				barChart.getData().addAll(s);
			}
			if(i==3) {
				s.getData().add(new XYChart.Data(name3,num3));
				barChart.getData().addAll(s);
			}
			if(i==4) {
				s.getData().add(new XYChart.Data(name4,num4));
				barChart.getData().addAll(s);
			}
			i++;
		}	
	}

//----------------------------------------------------------------------
		public void totoal_number_Resumption() {
			try {
				Connector.a.connectDB();
				PreparedStatement st2;
				st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Resumption_cases;");
				ResultSet r2 = st2.executeQuery();
				if (r2.next()) {
					num1 = r2.getInt(1);
				}
				Connector.a.connectDB().close();
			}catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} 
		}
			
//----------------------------------------------------------------------

		public void totoal_number_Rights() {
			try {
				Connector.a.connectDB();
				PreparedStatement st2;
				st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Rights_cases;");
				ResultSet r2 = st2.executeQuery();
				if (r2.next()) {
					num2 = r2.getInt(1);
				}
				Connector.a.connectDB().close();
			}catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} 
		}
				
//----------------------------------------------------------------------
		
		public void totoal_number_Penalty() {

			try {
				Connector.a.connectDB();
				PreparedStatement st2;
				st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Penalty_cases;");
				ResultSet r2 = st2.executeQuery();
				if (r2.next()) {
					num3 = r2.getInt(1);
				}
				Connector.a.connectDB().close();
			}catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} 
		}
		
//----------------------------------------------------------------------
	
		public void totoal_number_Execution() {
			try {
				Connector.a.connectDB();
				PreparedStatement st2;
				st2 = Connector.a.connectDB().prepareStatement("SELECT COUNT(*) FROM Execution_cases;");
				ResultSet r2 = st2.executeQuery();
				if (r2.next()) {
					num4 = r2.getInt(1);
				}
				Connector.a.connectDB().close();
			}catch (ClassNotFoundException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} catch (SQLException e) {
				Message.displayMassage("Please confirm the values. Prevents repetition and false values","Warning");
			} 
		}
			
//----------------------------------------------------------------------		
}
