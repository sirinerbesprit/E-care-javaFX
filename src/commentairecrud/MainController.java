/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentairecrud;
    
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
 *
 * @author Mohamed
 */
public class MainController implements Initializable {
    
   
    @FXML
    private TextField tfPseudo;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfMedecin;
    @FXML
    private TextField tfQuestion;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfId;
    @FXML
    private TableView<Commentaire> tvCommentaire;
    @FXML
    private TableColumn<Commentaire, Integer> colId;
    @FXML
    private TableColumn<Commentaire, String> colPseudo;
    @FXML
    private TableColumn<Commentaire, String> colSujet;
    @FXML
    private TableColumn<Commentaire, String> colMedecin;
    @FXML
    private TableColumn<Commentaire, String> colQuestion;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event){  
        if(event.getSource()==btnInsert){
            InsertRecord();}
            else if(event.getSource()==btnUpdate)
                updateRecord();
            else if(event.getSource()==btnDelete)
                deleteButton();
                
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCommentaire();
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
public ObservableList<Commentaire> getCommentairesList(){
    ObservableList<Commentaire> commentairesList =FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM commentaire";
    Statement st;
    ResultSet rs; 
    
    try {
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Commentaire commentaire;
        while (rs.next()){
            commentaire = new Commentaire(rs.getInt("id"),rs.getString("pseudo"),rs.getString("sujet"),rs.getString("medecin"),rs.getString("question") );
            commentairesList.add(commentaire);
        }
    }catch(Exception ex){
        ex.printStackTrace();
    } 
    return commentairesList;
}
public void showCommentaire(){
    
     ObservableList<Commentaire> list = getCommentairesList();
     colId.setCellValueFactory(new PropertyValueFactory<>("id"));
     colPseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
     colSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
     colMedecin.setCellValueFactory(new PropertyValueFactory<>("medecin"));
     colQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
     
     
     tvCommentaire.setItems(list);
     
     
    
}
private void InsertRecord(){
String query = "INSERT INTO commentaire VALUES ('"+ tfId.getText()+"','"+tfPseudo.getText()+"','"+tfSujet.getText()+"','"+tfMedecin.getText()+"','"+tfQuestion.getText()+"')";
executeQuery(query);
showCommentaire();}

private void updateRecord(){
    String query = "UPDATE commentaire SET pseudo = '" + tfPseudo.getText() + "' , medecin = '" + tfMedecin.getText() + "', sujet = '" + tfSujet.getText() + "', question = '" 
            +tfQuestion.getText() + "'WHERE id = '" + tfId.getText() + "'";
    executeQuery(query);
    showCommentaire();
    
            
            }
private void deleteButton(){
    String query = "DELETE FROM commentaire WHERE id=" + tfId.getText()+ "";
    executeQuery(query);
    showCommentaire();
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

  

    @FXML
    private void handleMouseAction(MouseEvent event) {
         Commentaire commentaire = tvCommentaire.getSelectionModel().getSelectedItem();
          tfId.setText(""+commentaire.getId());
        tfPseudo.setText(""+commentaire.getPseudo());
        tfSujet.setText(""+commentaire.getSujet());
        tfMedecin.setText(""+commentaire.getMedecin());
        tfQuestion.setText(""+commentaire.getQuestion());
       
        
    }
}
