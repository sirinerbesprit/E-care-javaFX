/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reponsecrud;

import commentairecrud.reponse;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class FXMLController implements Initializable {

    
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfPseudo;
    @FXML
    private TextField tfRep;
    @FXML
    private TextField tfDate_rep;
    @FXML
    private TextField tfCommentaire_id;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<reponse> tvReponse;
    @FXML
    private TableColumn<reponse, Integer> colId;
    @FXML
    private TableColumn<reponse, String> colPseudo;
    @FXML
    private TableColumn<reponse, String> colRep;
    @FXML
    private TableColumn<reponse, String> colDate_rep;
    @FXML
    private TableColumn<reponse, Integer> colCommentaire_id;
    
  @FXML
    private void handleButtonAction(ActionEvent event){  
        
                
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         showReponse();
    }    
    public Connection getConnection(){    
    Connection conn;
    try{
        conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare", "root","");
        return conn;     
    }catch(Exception ex){
        System.out.println("Error:  " + ex.getMessage() );
        return null;
    }
}
public ObservableList<reponse> getReponsesList(){
    ObservableList<reponse> reponsesList =FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM reponse";
    Statement st;
    ResultSet rs; 
    
    try {
        st = conn.createStatement();
        rs = st.executeQuery(query);
        reponse reponse;
        while (rs.next()){
            reponse = new reponse(rs.getInt("id"),rs.getString("pseudo"),rs.getString("rep"),rs.getString("date_rep"),rs.getInt("commentaire_id") );
            reponsesList.add(reponse);
            
        
        }
    }catch(Exception ex){
        ex.printStackTrace();
    } 
    return reponsesList;
}
public void showReponse(){
    
     ObservableList<reponse> list = getReponsesList();
     colId.setCellValueFactory(new PropertyValueFactory<>("id"));
     colPseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
     colRep.setCellValueFactory(new PropertyValueFactory<>("rep"));
     colDate_rep.setCellValueFactory(new PropertyValueFactory<>("date_rep"));
     colCommentaire_id.setCellValueFactory(new PropertyValueFactory<>("commentaire_id"));
     
     
     tvReponse.setItems(list);
     
     
    
}
}