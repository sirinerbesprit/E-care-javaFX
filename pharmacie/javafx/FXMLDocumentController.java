/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacie.javafx;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import ds.desktop.notify.DesktopNotify;
import java.io.IOException;
import javafx.scene.control.ListView;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtadresse;

    @FXML
    private TextField txtnum;
    
    @FXML
    private TextField txtsearch;

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
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;
    
    @FXML
    private Button btngo;
    

    
    
    

    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btninsert){
            insertRecord();
        }else if
            (event.getSource() == btnupdate) {
            updateRecord();
            }else if
            (event.getSource() == btndelete){
            deleteRecord();    
            }
    }
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
private void insertRecord(){
//      if (txtnom.getText().isEmpty() || txtadresse.getText().isEmpty() || txtnum.getText().isEmpty() ) {
//          insertRecord();
//            DesktopNotify.showDesktopMessage(
//            "ERROR !",
//                    "Votre ajout de pharmacie est refusé!",
//                    DesktopNotify.ERROR);
//     } 
//      else
//     {
   String query = "INSERT INTO `pharmacies`(`id`, `Nom`, `Adresse`, `Numtel`) VALUES('" + txtnom.getText() + "','" +txtadresse.getText()+ "','" +txtnum.getText() + "')";
   // System.out.println(txtnom.getText());
  // INSERT INTO `pharmacie` (`id`, `Nom`, `Adresse`, `Numtel`) VALUES (NULL, 'taha', 'taha', '12345678');
   executeQuery(query); 
//    insertRecord();
//            DesktopNotify.showDesktopMessage(
//            "success !",
//                    "Votre pharmacie est ajoutée avec succés !",
//                    DesktopNotify.SUCCESS);
   showPharmacies();
//     }
}

private void updateRecord(){
    String query = "UPDATE `pharmacie` SET Nom ='" +txtnom.getText() + "', Adresse= '" +txtadresse.getText() + " ', Numtel = "+txtnum.getText() + " WHERE ID =" +txtid.getText() +"";
    
     executeQuery(query); 
   showPharmacies();
}
private void deleteRecord(){
    String query = "DELETE FROM `pharmacie` WHERE id =" +txtid.getText() +"";
     executeQuery(query); 
   showPharmacies();
}


    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{ 
            st = conn.createStatement();
            st.executeUpdate(query);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void Insert(ActionEvent event) {
        if(event.getSource() == btninsert){
           
        }   
    }

    @FXML
    private void Update(ActionEvent event) {
            if(event.getSource() == btnupdate) {
        updateRecord();
              }
    }
    

    @FXML
    private void Delete(ActionEvent event) {
         if (event.getSource() == btndelete){
            deleteRecord();    
            }
    }
    
    
    @FXML
    private void handleMouseAction(MouseEvent event) {
       Pharmacies pharmacie = table.getSelectionModel().getSelectedItem();
      txtid.setText(""+ pharmacie.getID());
      txtnom.setText(pharmacie.getNom());
      txtadresse.setText(pharmacie.getAdresse());
      txtnum.setText(""+ pharmacie.getNumtel());
    }
  

    @FXML
    private void gogo(ActionEvent event) throws IOException{
        Parent d_page = FXMLLoader.load(getClass().getResource("PHARMACIEFRONT.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); 
    }
 
}
