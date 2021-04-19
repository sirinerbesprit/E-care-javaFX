/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentairecrud;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ReponseController implements Initializable {

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
    private Button btnEmail;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tsrechercher;
    
    private void handleButtonAction(ActionEvent event){  
        if(event.getSource()==btnInsert){
            InsertRecord();}
            else if(event.getSource()==btnUpdate)
                updateRecord();
            else if(event.getSource()==btnDelete)
                deleteButton();
                
    
                
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
   
   private void showReponse() {
 ObservableList<reponse> list = getReponsesList();
     colId.setCellValueFactory(new PropertyValueFactory<>("id"));
     colPseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
     colRep.setCellValueFactory(new PropertyValueFactory<>("rep"));
     colDate_rep.setCellValueFactory(new PropertyValueFactory<>("date_rep"));
     colCommentaire_id.setCellValueFactory(new PropertyValueFactory<>("commentaire_id"));
     
     
     tvReponse.setItems(list);
       FilteredList<reponse> filteredData = new FilteredList<>(list,b-> true);
       tsrechercher.textProperty().addListener((observable,oldvalue,newvalue) -> {
        filteredData.setPredicate((reponse reponse) -> {
            if (newvalue==null || newvalue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newvalue.toLowerCase();
                
            if (reponse.getPseudo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
            return true; 
            } if (String.valueOf(reponse.getDate_rep()).contains(lowerCaseFilter)) {
            return true; 
            }
            if (String.valueOf(reponse.getRep()).contains(lowerCaseFilter)) {
            return true; 
            }
            else  
             return false; 
            });
        tvReponse.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
       tfId.setText(String.valueOf(newSelection.getId()));
      tfPseudo.setText(String.valueOf(newSelection.getPseudo()));
       tfRep.setText(String.valueOf(newSelection.getRep()));
       tfDate_rep.setText(String.valueOf(newSelection.getDate_rep()));
       tfCommentaire_id.setText(String.valueOf(newSelection.getCommentaire_id()));
                  
    }
});
            
        SortedList<reponse> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvReponse.comparatorProperty());
        tvReponse.setItems(sortedData); 
        });
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) {
        reponse reponse =tvReponse.getSelectionModel().getSelectedItem();
          tfId.setText(""+reponse.getId());
        tfPseudo.setText(""+reponse.getPseudo());
        tfRep.setText(""+reponse.getRep());
        tfDate_rep.setText(""+reponse.getDate_rep());
        tfCommentaire_id.setText(""+reponse.getCommentaire_id());
    }

    @FXML
    private void email(ActionEvent event) throws IOException {
         Parent d_page = FXMLLoader.load(getClass().getResource("Email.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
    }

    

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent d_page = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
    }
    

    private void InsertRecord() {
     
        String query = "INSERT INTO reponse VALUES ('"+ tfId.getText()+"','"+tfPseudo.getText()+"','"+tfRep.getText()+"','"+tfDate_rep.getText()+"','"+tfCommentaire_id.getText()+"')";
executeQuery(query);
showReponse();
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

    private void updateRecord() {
        String query = "UPDATE reponse SET pseudo = '" + tfPseudo.getText() + "' , rep = '" + tfRep.getText() + "', date_rep = '" + tfDate_rep.getText() + "', commentaire_id = '" 
            +tfCommentaire_id.getText() + "'WHERE id = '" + tfId.getText() + "'";
    executeQuery(query);
    showReponse();
    }

    private void deleteButton() {
 String query = "DELETE FROM reponse WHERE id=" + tfId.getText()+ "";
    executeQuery(query);
    showReponse();    }

  

   
    
}
