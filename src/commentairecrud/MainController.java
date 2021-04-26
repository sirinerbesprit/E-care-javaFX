/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentairecrud;
    
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.itextpdf.text.pdf.security.SecurityConstants.Id;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeJava.type;

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
    private ListView<Commentaire> tvCommentaire;
    private TableColumn<Commentaire, Integer> colId;
    private TableColumn<Commentaire, String> colPseudo;
    private TableColumn<Commentaire, String> colSujet;
    private TableColumn<Commentaire, String> colMedecin;
    private TableColumn<Commentaire, String> colQuestion;
    @FXML
    private TextField tsrechercher;
    @FXML
    private Button reply;
    
    
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
        conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1", "root","");
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

     
     
     tvCommentaire.setItems(list);
       FilteredList<Commentaire> filteredData = new FilteredList<>(list,b-> true);
       tsrechercher.textProperty().addListener((observable,oldvalue,newvalue) -> {
        filteredData.setPredicate((Commentaire Commentaire) -> {
            if (newvalue==null || newvalue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newvalue.toLowerCase();
                
            if (Commentaire.getPseudo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
            return true; 
            } if (String.valueOf(Commentaire.getSujet()).contains(lowerCaseFilter)) {
            return true; 
            }
            if (String.valueOf(Commentaire.getQuestion()).contains(lowerCaseFilter)) {
            return true; 
            }
            else  
             return false; 
            });
        tvCommentaire.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
       tfId.setText(String.valueOf(newSelection.getId()));
      tfPseudo.setText(String.valueOf(newSelection.getPseudo()));
       tfSujet.setText(String.valueOf(newSelection.getSujet()));
       tfMedecin.setText(String.valueOf(newSelection.getMedecin()));
       tfQuestion.setText(String.valueOf(newSelection.getQuestion()));
                  
    }
});
            
        SortedList<Commentaire> sortedData = new SortedList<>(filteredData);
      //  sortedData.comparatorProperty().bind(tvCommentaire.comparatorProperty());
        tvCommentaire.setItems(sortedData); 
        });

    }

     
     
    

private void InsertRecord(){
String query = "INSERT INTO commentaire VALUES ('"+ tfId.getText()+"','"+tfPseudo.getText()+"','"+tfSujet.getText()+"','"+tfMedecin.getText()+"','"+tfQuestion.getText()+"')";
executeQuery(query);
showCommentaire();}

private void updateRecord(){
    String query = "UPDATE commentaire SET pseudo = '" + tfId.getText() + tfPseudo.getText() + "' , medecin = '" + tfMedecin.getText() + "', sujet = '" + tfSujet.getText() + "', question = '" 
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
    @FXML
    private void Reply(ActionEvent event) throws IOException {
         Parent d_page = FXMLLoader.load(getClass().getResource("test.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
        
    }
    
    
    
    private boolean controleDeSaisie() {  

        if (tfId.getText().isEmpty() || tfPseudo.getText().isEmpty() || tfSujet.getText().isEmpty()
                || tfMedecin.getText().isEmpty() || tfQuestion.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

           

           if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfId.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez id ! ");
                tfId.requestFocus();
                tfId.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", tfPseudo.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le pseudo ! ");
                tfPseudo.requestFocus();
                tfPseudo.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", tfSujet.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le sujet!");
                tfSujet.requestFocus();
                tfSujet.selectEnd();
                return false;
            }
            if (!Pattern.matches("[A-z]*", tfMedecin.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le medecin ! ");
                tfMedecin.requestFocus();
                tfMedecin.selectEnd();
                return false;
            }
           if (!Pattern.matches("[A-z]*", tfQuestion.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la question ! ");
                tfQuestion.requestFocus();
                tfQuestion.selectEnd();
                return false;
            }
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String données_erronés, String verifier_les_données, String veuillez_bien_remplir_tous_les_champs_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    
    
    
    
    @FXML
    private void Imprimer(ActionEvent event) throws   ClassNotFoundException, SQLException, DocumentException, BadElementException, IOException, URISyntaxException {
        try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecare1", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from commentaire");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("La liste des questions.pdf"));
                       
                        my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("C:\image.png");
//                            my_pdf_report.add(img);
                             

                       my_pdf_report.add(new Paragraph("Liste des questions"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(4);
                             
                       //create a cell object
                       PdfPCell table_cell;
                                          
                                         
                                       table_cell=new PdfPCell(new Phrase(" Pseudo"));
                                      table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Sujet"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Medecin"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                               
                                      table_cell=new PdfPCell(new Phrase("Question"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       
                                       
                                       
                                       
                                      while(rs.next()){
                                       
                                       String pseudo= rs.getString("pseudo");
                                       table_cell=new PdfPCell(new Phrase(pseudo));
                                       my_report_table.addCell(table_cell);
                                       String sujet=rs.getString("sujet");
                                       table_cell=new PdfPCell(new Phrase(sujet));
                                       my_report_table.addCell(table_cell);
                                       String medecin=rs.getString("medecin");
                                       table_cell=new PdfPCell(new Phrase(medecin));
                                       my_report_table.addCell(table_cell);
                                       String question=rs.getString("question");
                                       table_cell=new PdfPCell(new Phrase(question));
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

    @FXML
    private void retour_accueil(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("first.fxml"));
        Scene s = new Scene(d_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 
                app_stage.hide(); //optional
                app_stage.setScene(s);
                app_stage.show();
    }
    
}