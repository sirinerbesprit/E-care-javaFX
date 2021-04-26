/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import ecare2.Data;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author alaaa
 */
public class LoginController implements Initializable {
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Button log;
    
    private Connection cnx;
    @FXML
    private Button back;

  
Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;
    @FXML
    private Button google;
    @FXML
    private Button facebook;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        pane1.setStyle("-fx-background-image: url(\"/images/banner3.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/images/banner5.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/images/banner4.jpg\")");



        backgroundAnimation();

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

    private void backgroundAnimation() {

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(3),pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(3),pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2=new FadeTransition(Duration.seconds(3),pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                   FadeTransition fadeTransition0 =new FadeTransition(Duration.seconds(3),pane4);
                    fadeTransition0.setToValue(1);
                    fadeTransition0.setFromValue(0);
                    fadeTransition0.play();

                    fadeTransition0.setOnFinished(event3 -> {

                        FadeTransition fadeTransition11 =new FadeTransition(Duration.seconds(3),pane3);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.play();

                            });

                        });

                    });

                });
 
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
      private void Login (ActionEvent event) throws Exception {
          Connection conn= getConnection();
          String sql = "Select * from user where login = ? and password = ? ";
          try {
              pst= conn.prepareStatement(sql);
              pst.setString(1, login.getText());
              pst.setString(2, password.getText());
              rs= pst.executeQuery();
              user u = new user();
            if (rs.next()) {
            u.setId(rs.getInt("id"));
            u.setRoles(rs.getString("roles"));
                u.setLogin(login.getText());

        
                  Data.user=u;
                  JOptionPane.showMessageDialog(null, "Login Successfull");
System.out.print(Data.user.getRoles());
                  String r = Data.user.getRoles();
                  
                if (r.equals("["+'"'+"ROLE_ADMIN"+'"'+"]")) {
                       Parent d_page = FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show(); } 
                else  {Parent d_page = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
                    
                }
                  
              } else 
                                    JOptionPane.showMessageDialog(null, "Invalid");

                  
              
          } catch (Exception e) {
     
                            JOptionPane.showMessageDialog(null, e);

      }

      }
      
      
    @FXML
    private void passForgotten(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String email = "";
        String res ="";
        TextInputDialog dialog = new TextInputDialog(email);
        dialog.setTitle("mot de passe oublie");
        dialog.setHeaderText("votre mot de passe sera changer, confirmer");
        dialog.setContentText(" entrer votre email");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            res = result.get();
////            String rand = generate(7);
////            SendingMail sm = new SendingMail("Ceci est le code de votre compte " + rand, res, "");
////            SendingMail.sendMail();

String aemail=res;
        String fromemail="contact.ecare2021@gmail.com";
        System.out.println(res);
         String Subject=" mot de passe";
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
               
                return new PasswordAuthentication(fromemail,emailpassword);
                
            }
});
        
        
        try{
            
                            cnx = myconnection.getInstance().getConnection();
//    	Parent root;
        PreparedStatement pre;
     
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(fromemail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(aemail));
            message.setSubject(Subject);
            String req =  "select * from user where email = '"+res+"'" ;
	      
          pre = cnx.prepareStatement(req);
             ResultSet rs =pre.executeQuery();
             int i = 0;
             user u = new user();
             
	        while (rs.next()) {
                           u.setPassword(rs.getString("password"));
                     

	        	i++;	 
	        }
	         if (i==1) {
	        	Data.user = u;
                       
                        System.out.println( u.getPassword());
                 }
            message.setText("votre mot de passe est "+u.getPassword());
            Transport.send(message);
                    System.out.println(fromemail);
            
        
        
    }catch(MessagingException ex){
            System.out.println(""+ex);
        
    }
                
        }



    }

     
}
      
      
      
      

