/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.care;

import entites.livraison;
import entites.panier;
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
 * @author achra
 */
public class LivraisonController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfmessage;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfcommande;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfadreesse;
    @FXML
    private TableView<livraison> tvlivraison;
    @FXML
    private TableColumn<livraison, Integer> coid;
    @FXML
    private TableColumn<livraison, String> conom;
    @FXML
    private TableColumn<livraison, String> coadresse;
    @FXML
    private TableColumn<livraison, String>conumero;
    @FXML
    private TableColumn<livraison, String> comail;
    @FXML
    private TableColumn<livraison, String>comessage;
    @FXML
    private TableColumn<livraison, Integer>cocommandeid;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField tsrechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Showlivraison();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
  if(event.getSource() == btninsert){
    InsertRecord();}
    else if(event.getSource()==btnupdate)
            updateRecord();
    else if (event.getSource()==btndelete)
        deleteButton();
    }
    
    
     public Connection getConnection(){
         Connection conn ;
try{
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/panier","root","");
return conn;
}
    catch(Exception ex){
System.out.println("Error:" +ex.getMessage());
return null;
}
    }
      public ObservableList<livraison> getlivraisonList(){
        ObservableList<livraison> livraisonList =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query ="SELECT * FROM livraison";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            livraison livraisons  ;
            while(rs.next()){
          
                livraisons = new livraison(rs.getInt("id"),rs.getString("nom"),rs.getString("adresse"),rs.getString("numero"),rs.getString("mail"),rs.getString("message"),rs.getInt("commande_id")); 
        livraisonList.add(livraisons);}
        }catch(Exception ex)
        {
        ex.printStackTrace();
        }
        return livraisonList;
    }
    
public void Showlivraison(){
     ObservableList<livraison> list =getlivraisonList();
     coid.setCellValueFactory(new PropertyValueFactory<livraison, Integer>("id"));
     conom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     coadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
     conumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
     comail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        comessage.setCellValueFactory(new PropertyValueFactory<>("message"));
           cocommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
      tvlivraison.setItems(list);
      
      
    
       FilteredList<livraison> filteredData = new FilteredList<>(list,b-> true);
       tsrechercher.textProperty().addListener((observable,oldvalue,newvalue) -> {
        filteredData.setPredicate((livraison livraison ) -> {
            if (newvalue==null || newvalue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newvalue.toLowerCase();
                
            if (livraison.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
            return true; 
            } if (String.valueOf(livraison.getAdresse()).contains(lowerCaseFilter)) {
            return true; 
            }
            if (String.valueOf(livraison.getNumero()).contains(lowerCaseFilter)) {
            return true; 
            }
            else  
             return false; 
            });
        tvlivraison.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
       tfid.setText(String.valueOf(newSelection.getId()));
       tfnom.setText(String.valueOf(newSelection.getNom()));
       tfadreesse.setText(String.valueOf(newSelection.getAdresse()));
       tfnumero.setText(String.valueOf(newSelection.getNumero()));;
       tfmessage.setText(String.valueOf(newSelection.getMessage()));
       tfmail.setText(String.valueOf(newSelection.getMail()));
       tfcommande.setText(String.valueOf(newSelection.getCommande_id()));
                  
    }
});
            
        SortedList<livraison> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvlivraison.comparatorProperty());
        tvlivraison.setItems(sortedData); 
        });

    }
      


private void InsertRecord(){
String query = "INSERT INTO livraison VALUES ('"+ tfid.getText()+"','"+tfnom.getText()+"','"+tfadreesse.getText()+"','"+tfnumero.getText()+"','"+tfmail.getText()+"','"+tfmessage.getText()+"','"+tfcommande.getText()+"')";
executeQuery(query);
Showlivraison();}
private void updateRecord(){
String query ="UPDATE panier SET nom = '"+tfnom.getText()+"', adresse ='" + tfadreesse.getText()+"',numero='"+tfnumero.getText()+"',mail='"+tfmail.getText()+"',mail='"+tfmessage.getText()+"',commande='"+tfcommande.getText()+"' WHERE id= '"+tfid.getText()+"'";

executeQuery(query);
Showlivraison();
}
private void deleteButton(){
String query = "DELETE FROM livraison WHERE id='" +tfid.getText()+"'";
executeQuery(query);
Showlivraison();
        }

    private void executeQuery(String query){
        Connection conn=getConnection();
        Statement st;
        try{
        st=conn.createStatement();
        st.executeUpdate(query);}catch(Exception ex){
        ex.printStackTrace();}

    }
    @FXML
private void handleMouseAction(MouseEvent event){
livraison livraison = tvlivraison.getSelectionModel().getSelectedItem();
tfid.setText(""+livraison.getId());
tfnom.setText(""+livraison.getNom());
tfadreesse.setText(""+livraison.getAdresse());
tfnumero.setText(""+livraison.getNumero());
tfmail.setText(""+livraison.getMail());
tfmessage.setText(""+livraison.getMessage());
tfcommande.setText(""+livraison.getCommande_id());
        }
 @FXML
    private void retour(MouseEvent event) throws IOException {
 Parent d_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
    }
}
