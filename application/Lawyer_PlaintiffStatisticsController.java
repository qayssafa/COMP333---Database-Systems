package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class Lawyer_PlaintiffStatisticsController implements Initializable{
	 @FXML
	 private StackedBarChart<String,Integer> barChart3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String SQL = "select Lawyer_Name, count(*) as Total_number_of_cases_with_Plaintiffs from \r\n"
				+ "Cases,Lawyer,Plaintiff,Plaintiff2Cases2lawyer\r\n"
				+ "where Plaintiff2Cases2lawyer.P_id = Plaintiff.P_id and Plaintiff2Cases2lawyer.Lawyer_ID = Lawyer.ID_number and Plaintiff2Cases2lawyer.Case_ID = Cases.Case_id\r\n"
				+ "GROUP BY Lawyer.ID_number;";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				XYChart.Series<String,Integer> s = new XYChart.Series<>();
				s.setName("Lawyer_Name: " + rs.getString(1));
				String name = rs.getString(1);
				int numOfItem = rs.getInt(2);
				s.getData().add(new XYChart.Data(name,numOfItem));
				barChart3.getData().addAll(s);
			}
			rs.close();
			state.close();
			Connector.a.connectDB().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
