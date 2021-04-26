/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacie.javafx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PHARMACIEFRONTController implements Initializable {

     @FXML
    private TableView<Pharmacies> table;

    @FXML
    private TableColumn<Pharmacies, Integer> idcolumn;

    @FXML
    private TableColumn<Pharmacies, String> nomcolumn;

    @FXML
    private TableColumn<Pharmacies, String> adressecolumn;

    @FXML
    private TableColumn<Pharmacies, Integer> numcolumn; 
    
     @FXML
    private TextField txtsearch;
     
    @FXML
    private Button btnrate;
    
    @FXML
    private Button btnloc;
    @FXML
    private TextField mapadress;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showPharmacies();
    }    

   public Connection getConnection(){
       Connection conn;
       try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1","root","");
        return conn;
       } catch (Exception ex) {
       System.out.println("Error: "+ ex.getMessage());
       return null;
       }
   }
   
   public ObservableList<Pharmacies> getPharmaciesList(){
    ObservableList<Pharmacies> pharmacieList = FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM pharmacie";
    Statement st;
    ResultSet rs;
    try{
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Pharmacies pharmacies;
        while(rs.next()){
        pharmacies = new Pharmacies(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Adresse"), rs.getInt("Numtel") );   
        pharmacieList.add(pharmacies);
    }
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return pharmacieList;
    }
   

public void showPharmacies(){
    ObservableList<Pharmacies> list = getPharmaciesList();
    
    idcolumn.setCellValueFactory(new PropertyValueFactory<Pharmacies, Integer>("ID"));
    nomcolumn.setCellValueFactory(new PropertyValueFactory<Pharmacies, String>("Nom"));
    adressecolumn.setCellValueFactory(new PropertyValueFactory<Pharmacies, String>("Adresse"));
    numcolumn.setCellValueFactory(new PropertyValueFactory<Pharmacies, Integer>("Numtel"));
    table.setItems(list);
    
   FilteredList<Pharmacies> filteredData = new FilteredList<>(list, b -> true);
       
        txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Pharmacies prop) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (prop.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (prop.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                }
                else {
                    return false; 
                }
            });
        });
        SortedList<Pharmacies> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
}
    
    @FXML
    private void handleMouseAction(MouseEvent event) {

    }

    @FXML
    private void rate(ActionEvent event) throws IOException{
        Parent d_page = FXMLLoader.load(getClass().getResource("Avis.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); 
    }

    @FXML
    private void Location(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
           mapadress.setText(adressecolumn.getCellData(index).toString());
           String S = mapadress.getText();
           String[] splitString = S.split(",");
                
                Double d = Double.parseDouble(splitString[0]);
                Double d1 = Double.parseDouble(splitString[1]);
                Maptet test = new Maptet(d, d1);
    }
}
