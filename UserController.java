/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;

import static ecare2.SignupController.showAlert;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author alaaa
 */
public class UserController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField logintext;
    @FXML
    private TextField passwordtext;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableView<user> tableview;
    @FXML
    private TableColumn<user, String> login;
    @FXML
    private TableColumn<user, String> password;
    @FXML
    private TableColumn<user, String> cin;
    @FXML
    private TableColumn<user, String> sexe;
    @FXML
    private TableColumn<user, String> nom;
    @FXML
    private TableColumn<user, String> prenom;
    @FXML
    private TableColumn<user, String> adresse;
    @FXML
    private TableColumn<user, String> num_tel;
    @FXML
    private TableColumn<user, String> email;
    @FXML
    private TableColumn<user, Boolean> is_verified;
    @FXML
    private TextField cintext;
    @FXML
    private TextField sexetext;
    @FXML
    private TextField nomtext;
    @FXML
    private TextField prenomtext;
    @FXML
    private TextField adressetext;
    @FXML
    private TextField numtext;
    @FXML
    private TextField emailtext; 
    @FXML
    private TableColumn<user, String> roles;
    
    @FXML
    private ChoiceBox<String> rolescb;
    
      ObservableList <String> rolesList =FXCollections.observableArrayList("["+'"'+"ROLE_PAT"+'"'+"]"
,"["+'"'+"ROLE_ADMIN"+'"'+"]","["+'"'+"ROLE_PARA"+'"'+"]","["+'"'+"ROLE_PHAR"+'"'+"]","["+'"'+"ROLE_MED"+'"'+"],");
    
  
     Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;
    @FXML
    private Button back;
   
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Showuser();
        rolescb.setItems(rolesList);
    }  
       private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == add){
            InsertRecord();
        }else if
            (event.getSource() == update) {
            updateRecord();
            }else if
            (event.getSource() == delete){
            delete();    
            }
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
    
    public ObservableList<user> getUserList(){
    ObservableList<user> UserList = FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM user";
    Statement st;
    ResultSet rs;
    try{
        st = conn.createStatement();
        rs = st.executeQuery(query);
        user user;
        while(rs.next()){
           user = new user(rs.getInt("id"),rs.getString("login"),rs.getString("roles"),rs.getString("password"),rs.getString("cin"),rs.getString("sexe"),
                        rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("num_tel"),
                        rs.getString("email"),rs.getBoolean("is_verified"));         
        UserList.add(user);
    }
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return UserList;
    }
    public void Showuser(){
     ObservableList<user> list =getUserList();
     login.setCellValueFactory(new PropertyValueFactory<user, String>("login"));
     password.setCellValueFactory(new PropertyValueFactory<user, String>("password"));
     roles.setCellValueFactory(new PropertyValueFactory<user, String>("roles"));
     cin.setCellValueFactory(new PropertyValueFactory<user, String>("cin"));
     sexe.setCellValueFactory(new PropertyValueFactory<user, String>("sexe"));
        nom.setCellValueFactory(new PropertyValueFactory<user, String>("nom"));
          prenom.setCellValueFactory(new PropertyValueFactory<user, String>("prenom"));
     adresse.setCellValueFactory(new PropertyValueFactory<user, String>("adresse"));
     num_tel.setCellValueFactory(new PropertyValueFactory<user, String>("num_tel"));
     email.setCellValueFactory(new PropertyValueFactory<user, String>("email"));
        is_verified.setCellValueFactory(new PropertyValueFactory<user, Boolean>("is_verified"));
      tableview.setItems(list);

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
    private void InsertRecord(){
                               Connection conn= getConnection();

    
try {  
        if  ( ( (login.getText().isEmpty() || password.getText().isEmpty() )
                || cin.getText().isEmpty() || 
            nom.getText().isEmpty() || prenom.getText().isEmpty() || 
            email.getText().isEmpty() || sexe.getText().isEmpty() || 
            num_tel.getText().isEmpty() ||adresse.getText().isEmpty() ) ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
           
       
        }  String sql = "insert into user (login,roles,password,cin,sexe,nom,prenom,adresse,num_tel,email) values (?,?,?,?,?,?,?,?,?,?)";

    
    pst = conn.prepareStatement(sql);
    pst.setString(1, login.getText());
    pst.setString(2, rolescb.getValue());
        pst.setString(3, password.getText());
            pst.setString(4, cin.getText());
                pst.setString(5, sexe.getText());
                    pst.setString(6, nom.getText());
                        pst.setString(7, prenom.getText());
                            pst.setString(8, adresse.getText());
                                pst.setString(9, num_tel.getText());
                                                                pst.setString(10, email.getText());

pst = conn.prepareStatement(sql);
                            executeQuery(sql);                                                
                            JOptionPane.showMessageDialog(null, "Saved !");


                          
    } 


 catch  (Exception e) {
                      JOptionPane.showMessageDialog(null, e);

}
Showuser();}
    

 

    private void updateRecord(){
                        user us = tableview.getSelectionModel().getSelectedItem();

    String query = "UPDATE `user` SET login = '" + logintext.getText() + "' , roles = '" + rolescb.getValue() + "', password = '" + passwordtext.getText() + "', cin = '" 
            +cintext.getText() + "' , sexe = '" + sexetext.getText() + "', nom = '" + nomtext.getText() + "', prenom = '" + prenomtext.getText() + "', adresse = '" + adressetext.getText() + "', num_tel = '" + numtext.getText() + "', email = '" + emailtext.getText() + "' WHERE email = '" + emailtext.getText() + "'";
                   Connection conn = getConnection();

    try {
            pst = conn.prepareStatement(query);
                            executeQuery(query);
JOptionPane.showMessageDialog(null, "Updated!");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    executeQuery(query);
    
    Showuser();
    }
    
    
    private void delete(){
                user us = tableview.getSelectionModel().getSelectedItem();

    String query = "DELETE FROM `user` WHERE cin ="+us.getCin() ;
               Connection conn = getConnection();

       
        try {
            pst = conn.prepareStatement(query);
                            executeQuery(query);
JOptionPane.showMessageDialog(null, "Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    executeQuery(query);
    
    Showuser();
}

    
    @FXML
    private void Insert(ActionEvent event) {
        if(event.getSource() == add){
            InsertRecord();
        }   
    }

    @FXML
    private void Update(ActionEvent event) {
            if(event.getSource() == update) {
        updateRecord();
              }
    }
    

    @FXML
    private void Delete(ActionEvent event) {
         if (event.getSource() == delete){
            delete();    
            }
    }
    
        @FXML
    private void handleMouseAction(MouseEvent event) {
       user u = tableview.getSelectionModel().getSelectedItem();
      logintext.setText(""+u.getLogin());
      passwordtext.setText(""+u.getPassword());
      cintext.setText(""+u.getCin());
      sexetext.setText(""+u.getSexe());
            nomtext.setText(""+u.getNom());
                  prenomtext.setText(""+u.getPrenom());
                        adressetext.setText(""+u.getAdresse());
                              numtext.setText(""+u.getNum_tel());
                                    emailtext.setText(""+u.getEmail());





      
    }
    
               
} 
    
  


