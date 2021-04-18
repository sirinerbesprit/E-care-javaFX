/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Clinique;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceClinique;

/**
 * FXML Controller class
 *
 * @author Siwar Boutaleb
 */
public class AffCliniqueController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
        private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
     private ResultSet rsd = null ;
    ObservableList<Clinique> list = FXCollections.observableArrayList();
    @FXML
   
    private TextField filterField;
    @FXML
    private AnchorPane recpane;
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
    @FXML
     public void recherche(){
    ServiceClinique re= new ServiceClinique() ;
    List<Clinique>results = new ArrayList<>();
    results = re.afficher();
     FilteredList<Clinique> filteredData = new FilteredList<>(list , b -> true);
		Clinique r = new Clinique();
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(clinique -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (clinique.getAdressecl().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (clinique.getDesccl().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                 else if (clinique.getNomcl().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(r.getDesccl()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Clinique> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(RECl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		RECl.setItems(sortedData);
               
        
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
    private void getSelected(MouseEvent event) {
    }
    public void initialiserlist() throws SQLException{
             try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM clinique");
            while(rs.next()){
             list.add(new Clinique( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
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

    @FXML
    private void patient(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Patient.fxml"));
           recpane.getChildren().setAll(pane);
    }
    
}
