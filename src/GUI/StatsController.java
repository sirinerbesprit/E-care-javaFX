/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Siwar Boutaleb
 */
public class StatsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    HBox root = new HBox();
    Scene scene = new Scene(root,450,330);
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("");
     NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("");
      LineChart<String,Number> linechart = new LineChart<String,Number>(xAxis,yAxis);
    
    
    XYChart.Series<String,Number> data = new XYChart.Series<>();
    data.getData().add(new XYChart.Data<String,Number>("ddd",5545));
    data.getData().add(new XYChart.Data<String,Number>("fdfd",452));
    data.getData().add(new XYChart.Data<String,Number>("qsd",5544));
    data.getData().add(new XYChart.Data<String,Number>("dsddd",5545));
    linechart.getData().add(data);
    root.getChildren().add(linechart);
   

    }    
    
    
}
