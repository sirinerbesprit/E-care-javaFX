/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author alaaa
 */
public class MedecinController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private TableView<medecin> tableview;
    @FXML
    private TableColumn<medecin, String> nom;
    @FXML
    private TableColumn<medecin, String> prenom;
    @FXML
    private TableColumn<medecin, String> specialite;
    @FXML
    private TableColumn<medecin, String> adresse;
    @FXML
    private TableColumn<medecin, String> sexe;
    @FXML
    private TableColumn<medecin, String> num;
    @FXML
    private TableColumn<medecin, String> cin;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
  
PreparedStatement pst=null;
    @FXML
    private TextField nomtext;
    @FXML
    private TextField specialitetext;
    @FXML
    private TextField numtext;
    @FXML
    private TextField adressetext;
    @FXML
    private TextField cintext;
    @FXML
    private TextField sexetext;
    @FXML
    private TextField prenomtext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMed();
    }    

    public Connection getConnection(){
       Connection conn;
       try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1","root","root");
        return conn;
       } catch (Exception ex) {
       System.out.println("Error: "+ ex.getMessage());
       return null;
       }
   }
    
    public ObservableList<medecin> getMedecinList(){
    ObservableList<medecin> MedecinList = FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM medecin";
    Statement st;
    ResultSet rs;
    try{
        st = conn.createStatement();
        rs = st.executeQuery(query);
        medecin medecin;
        while(rs.next()){
           medecin = new medecin(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("sexe"),
                        rs.getString("num_tel"),rs.getString("cin"),rs.getString("password"));         
        MedecinList.add(medecin);
    }
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return MedecinList;
    }
    
       
   public void showMed(){
     ObservableList<medecin> list =getMedecinList();
     nom.setCellValueFactory(new PropertyValueFactory<medecin, String>("nom"));
     prenom.setCellValueFactory(new PropertyValueFactory<medecin, String>("prenom"));
     specialite.setCellValueFactory(new PropertyValueFactory<medecin, String>("specialite"));
     adresse.setCellValueFactory(new PropertyValueFactory<medecin, String>("adresse"));
     sexe.setCellValueFactory(new PropertyValueFactory<medecin, String>("sexe"));
        num.setCellValueFactory(new PropertyValueFactory<medecin, String>("num_tel"));
          cin.setCellValueFactory(new PropertyValueFactory<medecin, String>("cin"));
  
      tableview.setItems(list);

}
    
    
       @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); 
    }
            @FXML
    private void add(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("MedecinAdd.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); 
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
    private void handleMouseAction(MouseEvent event) {
        try {
       medecin med = tableview.getSelectionModel().getSelectedItem();
               nomtext.setText(""+med.getNom());
       prenomtext.setText(""+med.getPrenom()); 
        specialitetext.setText(""+med.getSpecialite()); 
         adressetext.setText(""+med.getAdresse());
          sexetext.setText(""+med.getSexe()); 
           numtext.setText(""+med.getNum_tel()); 
            cintext.setText(""+med.getCin()); 

        } catch (Exception e) {
        }

    }     
    @FXML 
private void update(){
    String query = "UPDATE `medecin` SET `nom`= '" +nomtext.getText()+ "' ,`prenom`='" +prenomtext.getText()+"',`specialite`='"+specialitetext.getText()+"',`adresse`='"+adressetext.getText()+"',`sexe`='"+sexetext.getText()+"',`num_tel`='"+numtext.getText()+"',`cin`='"+cintext.getText()+
            "' WHERE cin='"+cintext.getText()+"' ";
               Connection conn = getConnection();
try { 
                pst = conn.prepareStatement(query);
     executeQuery(query); 
     JOptionPane.showMessageDialog(null, "Updated!");}
catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

   showMed();
   
}
    @FXML
    private void delete(ActionEvent event) {
    

        medecin medecin = tableview.getSelectionModel().getSelectedItem();
          
      
    String query = "DELETE FROM `medecin` WHERE cin ="+medecin.getCin();
           Connection conn = getConnection();

        try {
            pst = conn.prepareStatement(query);
                executeQuery(query);
                                                  JOptionPane.showMessageDialog(null, "Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    showMed();

    }  
}
