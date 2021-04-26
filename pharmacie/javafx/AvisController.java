/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacie.javafx;

import ds.desktop.notify.DesktopNotify;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author User
 */
 public class AvisController implements Initializable {

    @FXML
    private TextArea txtdesc;
    
    @FXML
    private ComboBox<Pharmacies> combph;
    
    
    @FXML
    private Button btninsert;
    
    @FXML
    private Button btnupdate;
    
    @FXML
    private Button btndelete;
    
    @FXML
    private TableView<Avis> table;
    
     @FXML
    private TableColumn<Avis, String> nomcolumn;

    @FXML
    private TableColumn<Avis, String> phcolumn;

    @FXML
    private TableColumn<Avis, String> desccolumn;

    @FXML
    private TableColumn<Avis, Integer> ratecolumn;

    @FXML
    private TableColumn<Avis, Integer> idcolumn;
    @FXML
    private Rating rate;
     int hopitalId = -1;
   @FXML
    private TextField txtid;
   
   ObservableList<Pharmacies> catlist;
   

   
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAvis();
        catlist = getPharmaciesList();
          combph.setItems(catlist);
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
     
     public ObservableList<Avis> getAvisList(){
    ObservableList<Avis> avisList = FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM avis";
    Statement st;
    ResultSet rs;
    try{
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Avis avis;
        while(rs.next()){
        avis = new Avis(rs.getInt("id"), rs.getInt("pharmacie_id"), rs.getString("note"), rs.getInt("rate") );   
        avisList.add(avis);
    }
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return avisList;
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
   
    
 public void showAvis(){
    ObservableList<Avis> list = getAvisList();
   
    idcolumn.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("id"));
  
    phcolumn.setCellValueFactory(new PropertyValueFactory<Avis, String>("pharmacie_id"));
    desccolumn.setCellValueFactory(new PropertyValueFactory<Avis, String>("note"));
    ratecolumn.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("rate"));
    table.setItems(list);
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
 
 private void insertRecord(){
    String query = "INSERT INTO `avis` ( pharmacie_id, note  , rate) VALUES('" + combph.getSelectionModel().getSelectedItem().getID() + "','" + txtdesc.getText() + "','" +rate.getRating()+ "')";
   executeQuery(query); 
showAvis();
}

private void updateRecord(){
    String query = "UPDATE `avis` SET   pharmacie_id='" + combph.getSelectionModel().getSelectedItem().getID() + "', rate= '" +rate.getRating()+"', note= '" +txtdesc.getText()+"' WHERE id= "+txtid.getText()+"";
     executeQuery(query); 
   showAvis();
}

    @FXML
    private void handleMouseAction(MouseEvent event) {
       Avis avis = table.getSelectionModel().getSelectedItem();
        txtid.setText(""+ avis.getId());
      txtdesc.setText(avis.getNote());
      rate.setRating(avis.getRate());
       combph.getSelectionModel().select(catlist.stream().filter((t) -> {
            if (t.getID() == avis.getPharmacie_id()) {
                return true;
            } else {
                return false;
            }
        }).findFirst().get());
      
    }
private void deleteRecord(){
    String query = "DELETE FROM `avis` WHERE id="+txtid.getText()+"";
     executeQuery(query); 
   showAvis();
}
    
     @FXML
    private void Insert(ActionEvent event) {
        if(event.getSource() == btninsert){
            insertRecord();
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

    
 }