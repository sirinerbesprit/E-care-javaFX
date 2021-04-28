/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;

import static ecare2.SignupController.showAlert;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author alaaa
 */
public class MedecinAddController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button insert;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField specialite;
    @FXML
    private TextField sexe;
    @FXML
    private TextField num;
    @FXML
    private TextField cin;
    @FXML
    private TextField adresse;

         Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
     @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("Medecin.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); 
    }
         public Connection getConnection(){
         Connection conn ;
try{
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1","root","root");
return conn;
}
    catch(Exception ex){
System.out.println("Error:" +ex.getMessage());
return null;
}
    }
@FXML
    private void insert(ActionEvent event){
                       Connection conn= getConnection();

    
String sql = "insert into medecin (nom,prenom,specialite,adresse,sexe,num_tel,cin) values (?,?,?,?,?,?,?)";
try {  
        if  ( ( (nom.getText().isEmpty() || prenom.getText().isEmpty() )
                || cin.getText().isEmpty() || 
            sexe.getText().isEmpty() || adresse.getText().isEmpty() || 
            num.getText().isEmpty() || specialite.getText().isEmpty()  ) ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
           
       
        }  
    
    pst = conn.prepareStatement(sql);
    pst.setString(1, nom.getText());
    pst.setString(2, prenom.getText());
        pst.setString(3, specialite.getText());
            pst.setString(4, adresse.getText());
                pst.setString(5, sexe.getText());
                    pst.setString(6, num.getText());
                        pst.setString(7, cin.getText());
                         
                                pst.execute();
                                                  JOptionPane.showMessageDialog(null, "Saved !");
Parent d_page = FXMLLoader.load(getClass().getResource("Medecin.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();       

                          
    } 


 catch  (Exception e) {
                      JOptionPane.showMessageDialog(null, e);

}
}

    
}
