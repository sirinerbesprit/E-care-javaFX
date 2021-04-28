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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

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
    @FXML
    private Button back;
    Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public SignupController() {
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
    private void retour(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("bienvenue.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); 
    }
     
     
     
      public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
 public void signup (ActionEvent event){
               Connection conn= getConnection();
String sql = "insert into user (login,roles,password,cin,sexe,nom,prenom,adresse,num_tel,email) values (?,?,?,?,?,?,?,?,?,?)";
try {  
        if  ( ( (login.getText().isEmpty() || password.getText().isEmpty() )
                || cin.getText().isEmpty() || 
            nom.getText().isEmpty() || prenom.getText().isEmpty() || 
            email.getText().isEmpty() || sexe.getText().isEmpty() || 
            num.getText().isEmpty() ||adresse.getText().isEmpty() ) ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
           
        } else {

           

           if (!Pattern.matches("[A-z]*", nom.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez votre nom ! ");
                nom.requestFocus();
                nom.selectEnd();
                
            }
            if (!Pattern.matches("[A-z]*", prenom.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez votre prenom ! ");
                prenom.requestFocus();
                prenom.selectEnd();
                
            }
             if (!Pattern.matches("[A-z]*", adresse.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'adresse ! ");
                adresse.requestFocus();
                adresse.selectEnd();
                
            }
              if (!Pattern.matches("[A-z]*", sexe.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez vos données ! ");
                sexe.requestFocus();
                sexe.selectEnd();
                
            }if (!Pattern.matches("[0-9]*", cin.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez cin! ");
                cin.requestFocus();
                cin.selectEnd();
            }
           
        }  
    
    pst = conn.prepareStatement(sql);
    pst.setString(1, login.getText());
    pst.setString(2, "["+'"'+"ROLE_PAT"+'"'+"]");
        pst.setString(3, password.getText());
            pst.setString(4, cin.getText());
                pst.setString(5, sexe.getText());
                    pst.setString(6, nom.getText());
                        pst.setString(7, prenom.getText());
                            pst.setString(8, adresse.getText());
                                pst.setString(9, num.getText());
                                                                pst.setString(10, email.getText());

                                pst.execute();
                                
                                
        String to=email.getText();
        String fromemail="contact.ecare2021@gmail.com";
         String subject="Confirmation mail";
        String emailpassword="ecare2021";//your email password
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true" );
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromemail, emailpassword);
            }
});
        
        
        try{
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(fromemail));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress( to));
            message.setSubject(subject);
            message.setText("This is a confirmation email");
            Transport.send(message);
            
        
        
    }catch(Exception ex){
            System.out.println(""+ex);
        
    }
          
                                
                                
                                
                                                  JOptionPane.showMessageDialog(null, "Saved! you will receive an email.");


                            

             Parent d_page = FXMLLoader.load(getClass().getResource("bienvenue.fxml"));
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