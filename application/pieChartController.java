package application;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class pieChartController {
	
	@FXML
	private PieChart Customars;
	
	public void initialize() throws SQLException {

		try {
			Connector.a.connectDB();
			int p=0;
			int d=0;
		
			java.sql.PreparedStatement sp1;
			sp1 = Connector.a.connectDB().prepareStatement("select count(*) from Plaintiff;");
			ResultSet re1 = sp1.executeQuery();
			if (re1.next()) {
				p = re1.getInt(1);
			}
			java.sql.PreparedStatement sp2;
			sp2 = Connector.a.connectDB().prepareStatement("select count(*) from Defendent;");
			ResultSet re2 = sp2.executeQuery();
			if (re2.next()) {
				d = re2.getInt(1);
			}
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("Plaintiff", d), new PieChart.Data("Defendent", p));
			Customars.setData(pieChartData);					
			Connector.a.connectDB().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
	}}

