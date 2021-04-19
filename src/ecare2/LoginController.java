/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author alaaa
 */
public class LoginController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField login;
    @FXML
    private Button google;
    @FXML
    private Button facebook;
    @FXML
    private Button signup;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
      public Connection getConnection(){
         Connection conn ;
try{
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare","root","root");
return conn;
}
    catch(Exception ex){
System.out.println("Error:" +ex.getMessage());
return null;
}
    }

    @FXML
    private void login(ActionEvent event) {
        
      
        
        if (login.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,  "Form Error!",
                "Please enter your email id");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                "Please enter a password");
        }
    }
        
          private static void showAlert (AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
        
    }
      
      
      
      
      

