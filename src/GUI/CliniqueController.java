/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Clinique;
import Entite.Patient;
import Utils.DataSource;
import java.awt.HeadlessException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.ServiceClinique;
import service.ServicePatient;

/**
 * FXML Controller class
 *
 * @author Siwar Boutaleb
 */
public class CliniqueController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField sujet;
    @FXML
    private TextField desc;
    @FXML
    private TextField user;
    private TextField prod1;
    @FXML
    private ComboBox<?> prod;
    @FXML
    private Button ajout;
    @FXML
    private Button btnu;
    @FXML
    private TableView<Clinique> RECl;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> tt;
    @FXML
    private TableColumn<?, ?> oo;
    @FXML
    private TableColumn<?, ?> ss;
    @FXML
    private TableColumn<?, ?> ds;
    @FXML
    private Button supprimer;
    private TextField etat;

    /**
     * Initializes the controller class.
     */
     private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
     private ResultSet rsd = null ;
    ObservableList<Clinique> list = FXCollections.observableArrayList();
    @FXML
    private TextField type1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
  try {
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(CliniqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           afficher();    
    }    
 private void afficher(){
          dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          tt.setCellValueFactory(new PropertyValueFactory<>("nomcl"));
          oo.setCellValueFactory(new PropertyValueFactory<>("adressecl"));
          ss.setCellValueFactory(new PropertyValueFactory<>("numerocl"));
          ds.setCellValueFactory(new PropertyValueFactory<>("desccl"));
            
        RECl.setItems(list);
    }
    @FXML
    private void Ajout(ActionEvent event) throws SQLException {
        if (controleDeSaisi()) {
         ServiceClinique r = new ServiceClinique();
        int i=parseInt(desc.getText());
        r.ajouter(new Clinique(type.getText(),sujet.getText(),i,user.getText()));
        JOptionPane.showMessageDialog(null, "Votre clinique est bien ajoutée");
         list.clear();
                initialiserlist(); 
                afficher();
                list.clear();
                initialiserlist(); 
                afficher();
                RECl.refresh();
    }}
      public void initialiserlist() throws SQLException{
             try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM clinique");
            while(rs.next()){
             list.add(new Clinique( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(4)));
       }
            } catch (SQLException ex) {
            Logger.getLogger(CliniqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        }
       public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
 private boolean controleDeSaisi() {  

        if (type.getText().isEmpty() || user.getText().isEmpty()
                || sujet.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

           

           if (!Pattern.matches("[A-z]*", sujet.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'adresse ! ");
                sujet.requestFocus();
                sujet.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", type.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nom ! ");
                type.requestFocus();
                type.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", user.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la description!");
                user.requestFocus();
                user.selectEnd();
                return false;
            }
           
        }
        return true;
    }
    @FXML
    private void Edit(ActionEvent event) throws SQLException {
         
try {
            cnx = DataSource.ConnectDb();
            String value0 = type1.getText();
      
            
            String value3 = sujet.getText();
            
            String value1 = type.getText();
            String value4 = desc.getText();
            String value8 = user.getText();
            String sql = "update clinique set nomcl= '"+value1+"',adressecl= '"+
                    value3+"',desccl= '"+value3+"',numerocl= '"+value8+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
               
            JOptionPane.showMessageDialog(null, "Votre clinique est bien modifiée");
            type1.setText("");
            
    type.setText("");
    /*prod.setText("");*/
    sujet.setText("");
    desc.setText("");
    
    user.setText("");
       list.clear();
                initialiserlist(); 
                afficher();
                list.clear();
                initialiserlist(); 
                afficher();
                RECl.refresh();
    
     } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        initialiserlist(); 
                afficher();
                RECl.refresh();
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = RECl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    type1.setText(dd.getCellData(index).toString());
      type.setText(tt.getCellData(index).toString());
   
    desc.setText(ss.getCellData(index).toString());
    sujet.setText(oo.getCellData(index).toString());
    
   
    user.setText(ds.getCellData(index).toString());
    
    
    }
    public Clinique gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Clinique test = (Clinique) RECl.getSelectionModel().getSelectedItem();
        return test;
    }

    
    @FXML
   private void supprimerrc(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Clinique x = gettempReclamation(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceClinique cat = new ServiceClinique();

            int s = cat.deleteclonique(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Votre clinique est bien supprimée");
                alert.showAndWait();
             
                list.clear();
                initialiserlist(); 
                afficher();
                RECl.refresh();
    type.setText("");
    sujet.setText("");
    desc.setText("");
    
    user.setText("");
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }     
}
