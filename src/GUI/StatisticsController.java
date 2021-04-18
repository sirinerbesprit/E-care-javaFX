/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Clinique;
import java.io.IOException;
/*
import service.ServiceClinique;*/
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServicePatient;

/**
 * FXML Controller class
 *
 * @author Siwar Boutaleb
 */
public class StatisticsController implements Initializable {

    @FXML
    private BarChart<?, ?> rt;
    @FXML
    private NumberAxis x;
    @FXML
    private CategoryAxis y;
    @FXML
    private TextField fd;
    @FXML
    private AnchorPane recpane;
    @FXML
    private Button clinique;

    /**
     * Initializes the controller class.
     */
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServicePatient o = new ServicePatient();
   
        BarChart.Series set1 = new BarChart.Series<>();
      
        fd.setText("vous avez "+o.getNbr()+"patient") ;
    
        set1.getData().add(new BarChart.Data("carthagene",o.getNbrclinique1()));
        
        set1.getData().add(new BarChart.Data("nour",o.getNbrclinique2()));
        set1.getData().add(new BarChart.Data("elissa",o.getNbrclinique3()));
        set1.getData().add(new BarChart.Data("abouloubeba",o.getNbrclinique4()));
        rt.getData().addAll(set1);
        // TODO
    }    

    @FXML
    private void clinique(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Clinique.fxml"));
           recpane.getChildren().setAll(pane);
    }
    
}
