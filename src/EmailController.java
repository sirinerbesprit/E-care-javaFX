/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class EmailController implements Initializable {

    @FXML
    private TextField tfTo;
    @FXML
    private TextField tfSubject;
    @FXML
    private TextArea tfMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendmail(ActionEvent event) {
        String to=tfTo.getText();
        String fromemail="mohamed.amorri@esprit.tn";
         String subject=tfSubject.getText();
        String emailpassword="203JMT4596";//your email password
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true" );
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromemail, emailpassword);
            }
});
        
        
        try{
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(fromemail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress( to));
            message.setSubject(subject);
            message.setText(tfMessage.getText());
            Transport.send(message);
            
        
        
    }catch(Exception ex){
            System.out.println(""+ex);
        
    }
          }
    

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent d_page = FXMLLoader.load(getClass().getResource("Reponse.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
    }
    
}
