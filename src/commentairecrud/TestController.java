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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class TestController implements Initializable {

    @FXML
    private TextField tfPseudo;
    @FXML
    private TextField tfRep;
    @FXML
    private TextField tfDate_rep;
    @FXML
    private TextField tfCommentaire_id;
    @FXML
    private TextField tfId;
    @FXML
    private ListView<reponse1> tvReponse;
    private TableColumn<reponse1, String> colPseudo;
    private TableColumn<reponse1, String> colRep;
    private TableColumn<reponse1, String> colDate_rep;
    private TableColumn<reponse1, Integer> colCommentaire_id;
    private TableColumn<reponse1, Integer> colId;
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

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            InsertRecord();
        } else if (event.getSource() == btnUpdate) {
            updateRecord();
        } else if (event.getSource() == btnDelete) {
            deleteButton();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showReponse();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error:  " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<reponse1> getReponsesList() {
        ObservableList<reponse1> commentairesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM reponse1";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            reponse1 commentaire;
            while (rs.next()) {
                commentaire = new reponse1(rs.getInt("id"), rs.getInt("commentaire_id"), rs.getString("pseudo"), rs.getString("rep"), rs.getString("date_rep"));
                commentairesList.add(commentaire);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return commentairesList;
    }

    public void showReponse() {

        ObservableList<reponse1> list = getReponsesList();
     

        tvReponse.setItems(list);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        reponse1 reponse = tvReponse.getSelectionModel().getSelectedItem();
        tfId.setText("" + reponse.getId());
        tfCommentaire_id.setText("" + reponse.getCommentaire_id());
        tfPseudo.setText("" + reponse.getPseudo());
        tfRep.setText("" + reponse.getRep());
        tfDate_rep.setText("" + reponse.getDate_rep());
        
    }

    private void InsertRecord() {
        String query = "INSERT INTO reponse1 VALUES ('" + tfId.getText() + "','" + tfCommentaire_id.getText() +"','" + tfPseudo.getText() + "','" + tfRep.getText() + "','" + tfDate_rep.getText()  + "')";

        executeQuery(query);
        showReponse();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteButton() {
        String query = "DELETE FROM reponse1 WHERE id=" + tfId.getText() + "";
        executeQuery(query);
        showReponse();
    }

    private void updateRecord() {
        String query = "UPDATE reponse1 SET pseudo = '"+ tfId.getText()  + tfPseudo.getText() + "' , rep = '" + tfRep.getText() + "', date_rep = '" + tfDate_rep.getText() + "', commentaire_id = '"
                + tfCommentaire_id.getText() + "'WHERE id = '" + tfId.getText() + "'";
        executeQuery(query);
        showReponse();
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

    @FXML
    private void email(MouseEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("Email.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide(); //optional
        app_stage.setScene(s);
        app_stage.show();
    }

    @FXML
    private void Imprimer(ActionEvent event) throws   ClassNotFoundException, SQLException, DocumentException, BadElementException, IOException, URISyntaxException {
        try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from reponse1");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("La liste des réponses.pdf"));
                       
                        my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("C:\image.png");
//                            my_pdf_report.add(img);
                             

                       my_pdf_report.add(new Paragraph("Liste des réponses"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(4);
                             
                       //create a cell object
                       PdfPCell table_cell;
                                          
                                         table_cell=new PdfPCell(new Phrase("Commentaire_id"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase(" Pseudo"));
                                      table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Rep"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                               
                                      table_cell=new PdfPCell(new Phrase("Date_rep"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       
                                       
                                       
                                       
                                      while(rs.next()){
                                       String commentaire_id=rs.getString("commentaire_id");
                                       table_cell=new PdfPCell(new Phrase(commentaire_id));
                                       my_report_table.addCell(table_cell);
                                       String pseudo= rs.getString("pseudo");
                                       table_cell=new PdfPCell(new Phrase(pseudo));
                                       my_report_table.addCell(table_cell);
                                       String rep=rs.getString("rep");
                                       table_cell=new PdfPCell(new Phrase(rep));
                                       my_report_table.addCell(table_cell);
                                       String date_rep=rs.getString("date_rep");
                                       table_cell=new PdfPCell(new Phrase(date_rep));
                                       my_report_table.addCell(table_cell);
                                        
                                       
                                       
                                               
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "Imprimer avec succès");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
        

    }
    
    
    private boolean controleDeSaisie() {  

        if (tfId.getText().isEmpty() || tfCommentaire_id.getText().isEmpty() || tfPseudo.getText().isEmpty()
                || tfRep.getText().isEmpty() || tfDate_rep.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

           

           if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfId.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez id ! ");
                tfId.requestFocus();
                tfId.selectEnd();
                return false;
                
            }if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfCommentaire_id.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le commentaire id ! ");
                tfCommentaire_id.requestFocus();
                tfCommentaire_id.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", tfPseudo.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le pseudo!");
                tfPseudo.requestFocus();
                tfPseudo.selectEnd();
                return false;
            }
            if (!Pattern.matches("[A-z]*", tfRep.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la réponse ! ");
                tfRep.requestFocus();
                tfRep.selectEnd();
                return false;
            }
           if (!Pattern.matches("[A-z]*", tfDate_rep.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la date ! ");
                tfDate_rep.requestFocus();
                tfDate_rep.selectEnd();
                return false;
            }
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String données_erronés, String verifier_les_données, String veuillez_bien_remplir_tous_les_champs_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }
    

}
