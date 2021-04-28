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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alaaa
 */
public class MedecinFrontController implements Initializable {

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
  
      tableview.setItems(list);

}
    
    
       @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
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
}
