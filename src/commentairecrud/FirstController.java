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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class FirstController implements Initializable {

    @FXML
    private ListView<Commentaire> tvCommentaire;
    private TableColumn<Commentaire, Integer> colId;
    private TableColumn<Commentaire, String> colPseudo;
    private TableColumn<Commentaire, String> colSujet;
    private TableColumn<Commentaire, String> colMedecin;
    private TableColumn<Commentaire, String> colQuestion;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
      
         }

                           
    @FXML
    private void posez_question(ActionEvent event) throws IOException {
        Parent d_page = FXMLLoader.load(getClass().getResource("Main.fxml"));
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
    
}
