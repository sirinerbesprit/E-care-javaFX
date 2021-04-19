/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author alaaa
 */
public class SignupController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField cin;
    @FXML
    private TextField sexe;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    @FXML
    private Button signup;
    @FXML
    private Button google;
    @FXML
    private Button facebook;

    
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
     
     
     
      public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
 private boolean controleDeSaisie() {  

        if (login.getText().isEmpty() || password.getText().isEmpty()
                || cin.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || sexe.getText().isEmpty() || num.getText().isEmpty() ||adresse.getText().isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

           

           if (!Pattern.matches("[A-z]*", nom.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez votre nom ! ");
                nom.requestFocus();
                nom.selectEnd();
                return false;
                
            }
            if (!Pattern.matches("[A-z]*", prenom.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez votre prenom ! ");
                prenom.requestFocus();
                prenom.selectEnd();
                return false;
                
            }
             if (!Pattern.matches("[A-z]*", adresse.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'adresse ! ");
                adresse.requestFocus();
                adresse.selectEnd();
                return false;
                
            }
              if (!Pattern.matches("[A-z]*", sexe.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez vos données ! ");
                sexe.requestFocus();
                sexe.selectEnd();
                return false;
                
            }if (!Pattern.matches("[0-9]*", cin.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez cin! ");
                cin.requestFocus();
                cin.selectEnd();
                return false;
            }
           
        }
        return true;
    }
 private void InsertRecord(){
     Statement st;
        ResultSet rs;
String query = "INSERT INTO user VALUES ('"+login.getText()+"','"+"'['ROLE_PAT']"+"','"+password.getText()+"','"+cin.getText()+"',"
        + "'"+sexe.getText()+"','"+nom.getText()+"','"+prenom.getText()+"','"
        + "'"+adresse.getText()+"','"+num.getText()+"','"+email.getText()+"',"
        + "'"+0+"')";
executeQuery(query);
    
 }
  private void executeQuery(String query) {
    Connection conn = getConnection();
    Statement st ;
    try {
        st = conn.createStatement();
        st.executeUpdate(query);
        
    }catch (Exception ex){
        ex.printStackTrace();
    }
}
}