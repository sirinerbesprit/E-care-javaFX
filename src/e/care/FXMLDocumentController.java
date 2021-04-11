/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.care;


import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author achra
 */
public class FXMLDocumentController implements Initializable {
    
  
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcodepanier;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfprixtotal;
    @FXML
    private TextField tfproduit;
    @FXML
    private TableView<panier> tvlist;
    @FXML
    private TableColumn<panier, Integer> colid;
    @FXML
    private TableColumn<panier, String> colcodepanier;
    @FXML
    private TableColumn<panier, Integer> colquantite;
    @FXML
    private TableColumn<panier, Float>colprixtotal;
    @FXML
    private TableColumn<panier, String> colproduit;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
      @FXML
    private Button btnretour;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    if(event.getSource() == btninsert){
    InsertRecord();}
    else if(event.getSource()==btnupdate)
            updateRecord();
    else if (event.getSource()==btndelete)
        deleteButton();
 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Showpanier();
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
    public ObservableList<panier> getPanierList(){
        ObservableList<panier> PanierList =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query ="SELECT * FROM panier";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            panier paniers  ;
            while(rs.next()){
          
                paniers = new panier(rs.getInt("id"),rs.getString("code_panier"),rs.getInt("quantite"),rs.getFloat("prix_tot"),rs.getString("produits")); 
        PanierList.add(paniers);}
        }catch(Exception ex)
        {
        ex.printStackTrace();
        }
        return PanierList;
    }
public void Showpanier(){
     ObservableList<panier> list =getPanierList();
     colid.setCellValueFactory(new PropertyValueFactory<>("id"));
     colcodepanier.setCellValueFactory(new PropertyValueFactory<>("code_panier"));
     colquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     colprixtotal.setCellValueFactory(new PropertyValueFactory<>("prix_tot"));
     colproduit.setCellValueFactory(new PropertyValueFactory<>("produits"));
      tvlist.setItems(list);

}
private void InsertRecord(){
String query = "INSERT INTO panier VALUES ('"+ tfid.getText()+"','"+tfcodepanier.getText()+"','"+tfquantite.getText()+"','"+tfprixtotal.getText()+"','"+tfproduit.getText()+"')";
executeQuery(query);
Showpanier();}







private void updateRecord(){
String query ="UPDATE panier SET code_panier = '"+tfcodepanier.getText()+"', quantite ='" + tfquantite.getText()+"',prix_tot='"+tfprixtotal.getText()+"',produits='"+tfproduit.getText()+"' WHERE id= '"+tfid.getText()+"'";

executeQuery(query);
Showpanier();
}
private void deleteButton(){
String query = "DELETE FROM panier WHERE id='" +tfid.getText()+"'";
executeQuery(query);
Showpanier();
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
panier paniers = tvlist.getSelectionModel().getSelectedItem();
tfid.setText(""+paniers.getId());
tfcodepanier.setText(""+paniers.getCode_panier());
tfquantite.setText(""+paniers.getQuantite());
tfprixtotal.setText(""+paniers.getPrix_tot());
tfproduit.setText(""+paniers.getProduits());
        }


    @FXML
    private void retour(MouseEvent event) throws IOException {
 Parent d_page = FXMLLoader.load(getClass().getResource("livraison.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
    }
}
