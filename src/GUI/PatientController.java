/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import java.util.prefs.Preferences;
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
import service.ServicePatient;

/**
 * FXML Controller class
 *
 * @author Siwar Boutaleb
 */
public class PatientController implements Initializable {

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
    private ComboBox<Integer> prod;
    @FXML
    private Button ajout;
    @FXML
    private Button btnu;
    @FXML
    private TableView<Patient> RECl;
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
    private TableColumn<?, ?> dt;
    @FXML
    private TableColumn<?, ?> dtt;
    @FXML
    private TableColumn<?, ?> usr;
    @FXML
    private Button supprimer;
 private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    ObservableList<Patient> listM;
    /**
     * Initializes the controller class.
     */
    ObservableList<Patient> list = FXCollections.observableArrayList();
    @FXML
    private TextField iddd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try {
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
          afficher();
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
                || desc.getText().isEmpty() || desc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

           

           if (!Pattern.matches("[A-z]*", type.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nom ! ");
                type.requestFocus();
                type.selectEnd();
                return false;
            }if (!Pattern.matches("[A-z]*", user.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'adresse ! ");
                user.requestFocus();
                user.selectEnd();
                return false;
            }
            if (!Pattern.matches("[A-z]*", sujet.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'adresse mail ! ");
                sujet.requestFocus();
                sujet.selectEnd();
                return false;
            }
           
        }
        return true;
    }
    @FXML
    private void Ajout(ActionEvent event) throws SQLException {
         if (controleDeSaisi()) { 
        ServicePatient r = new ServicePatient();
        
        int i=parseInt(desc.getText());
        r.ajouter(new Patient(prod.getValue(),type.getText(),sujet.getText(),i,user.getText()));
        JOptionPane.showMessageDialog(null, "Bien ajouté");
         list.clear();
                initialiserlist(); 
                afficher();
                list.clear();
                initialiserlist(); 
                afficher();
                RECl.refresh();
    }}
 private void afficher(){
          dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          tt.setCellValueFactory(new PropertyValueFactory<>("clinique_id"));
          oo.setCellValueFactory(new PropertyValueFactory<>("name"));
          ss.setCellValueFactory(new PropertyValueFactory<>("email"));
          ds.setCellValueFactory(new PropertyValueFactory<>("phone"));
          dt.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            
            
        RECl.setItems(list);
    }
     
    @FXML
    private void Edit(ActionEvent event) throws SQLException {
        try {
            cnx = DataSource.ConnectDb();
            String value0 = iddd.getText();
            
            
            String value3 = sujet.getText();
            
            String value1 = type.getText();
            String value4 = desc.getText();
            String value8 = user.getText();
            String sql = "update patient set name= '"+value1+"',email= '"+
                    value3+"',adresse= '"+value8+"',phone= '"+value4+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
                list.clear();
              
            JOptionPane.showMessageDialog(null, "Bien modifié");
            iddd.setText("");
            
    type.setText("");
   
    sujet.setText("");
    desc.setText("");
    
   
    user.setText("");
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
    iddd.setText(dd.getCellData(index).toString());
    type.setText(oo.getCellData(index).toString());
    sujet.setText(ss.getCellData(index).toString());
    desc.setText(ds.getCellData(index).toString());
    
    user.setText(dt.getCellData(index).toString());
    
    
    }
    public Patient gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Patient test = (Patient) RECl.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    private void supprimerrc(ActionEvent event) throws SQLException {
         TableColumn.CellEditEvent edittedcell = null;
            Patient x = gettempReclamation(edittedcell);

            if (x != null) {

                int i = x.getId();
                ServicePatient cat = new ServicePatient();

                int s = cat.deletePatient(i);
                if (s == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Bien supprimé");
                    alert.showAndWait();
                    list.clear();
                    initialiserlist(); 
                    afficher();
                    RECl.refresh();
                      iddd.setText("");
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
  public void initialiserlist() throws SQLException{
             try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM patient");
            while(rs.next()){
               
            list.add(new Patient(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
        }
            } catch (SQLException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
              Connection cnx = DataSource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT id FROM clinique");
            // Now add the comboBox addAll statement
           while (rs.next()){
            prod.getItems().addAll(rs.getInt("id"));
           
           }
        }   
}
