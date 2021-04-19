/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;

import java.awt.event.MouseEvent;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author alaaa
 */
public class MainController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableView<user> tableview;
    @FXML
    private TextField logintext;
    @FXML
    private TextField passwordtext;
    @FXML
    private TextField roletext;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField logintext1;
    @FXML
    private TextField logintext2;
    @FXML
    private TextField logintext3;
    @FXML
    private TextField logintext4;
    @FXML
    private TextField logintext5;
    @FXML
    private TextField logintext6;
    @FXML
    private TextField logintext7;
    @FXML
    private TableColumn<user, String> login;
    @FXML
    private TableColumn<user, String> password;
    @FXML
    private TableColumn<user, String> roles;
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
    private void handleButtonAction(ActionEvent event){  
        if(event.getSource()==add){
            InsertRecord();}
            else if(event.getSource()==update)
                updateRecord();
            else if(event.getSource()==delete)
                deleteButton();
                
    }
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Showuser();
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
     
      public ObservableList<user> getUserList(){
        ObservableList<user> userList =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query ="SELECT * FROM user";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            user user  ;
            while(rs.next()){
          
                user = new user(rs.getString("login"),rs.getString("roles"),rs.getString("password"),rs.getString("cin"),rs.getString("sexe"),
                        rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("num_tel"),
                        rs.getString("email"),rs.getBoolean("is_verified")); 
        userList.add(user);}
        }catch(Exception ex)
        {
        }
        return userList;
    }
    
public void Showuser(){
     ObservableList<user> list =getUserList();
     login.setCellValueFactory(new PropertyValueFactory<>("login"));
     password.setCellValueFactory(new PropertyValueFactory<>("password"));
     roles.setCellValueFactory(new PropertyValueFactory<>("role"));
     cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
     sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
     adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
     num_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
     email.setCellValueFactory(new PropertyValueFactory<>("email"));
        is_verified.setCellValueFactory(new PropertyValueFactory<>("is_verified"));
      tableview.setItems(list);

}
private void InsertRecord(){
     Statement st;
        ResultSet rs;
String query = "INSERT INTO user VALUES ('"+login.getText()+"','"+roles.getText()+"','"+password.getText()+"','"+cin.getText()+"',"
        + "'"+sexe.getText()+"','"+nom.getText()+"','"+prenom.getText()+"','"
        + "'"+adresse.getText()+"','"+num_tel.getText()+"','"+email.getText()+"',"
        + "'"+is_verified.getText()+"')";
executeQuery(query);
Showuser();}
     
private void updateRecord(){
    String query = "UPDATE user SET login = '" + login.getText() + "' , roles = '" + roles.getText() + "', password = '" + password.getText() + "', cin = '" 
            +cin.getText() + "' , sexe = '" + sexe.getText() + "', nom = '" + nom.getText() + "', prenom = '" + prenom.getText() + "', adresse = '" + adresse.getText() + "', num_tel = '" + num_tel.getText() + "', email = '" + email.getText() + "', is_verified = '" + is_verified.getText() + "',WHERE email = '" + email.getText() + "'";
    executeQuery(query);
    Showuser();
   }

private void deleteButton(){
    String query = "DELETE FROM user WHERE login=" + login.getText()+ "";
    executeQuery(query);
    Showuser();
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
