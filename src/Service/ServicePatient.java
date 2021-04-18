/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entite.Patient;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.runtime.Debug.id;
/**
 *
 * @author Siwar Boutaleb
 */
public class ServicePatient {
Connection cnx = DataSource.getInstance().getCnx();

    private Statement ste;
    private PreparedStatement pst;
    
    public void ajouter(Patient r) {
          try {
                       
            String req = "INSERT INTO `Patient` (`clinique_id`, `name`, `email`,`phone`,"
                    + "`adresse`) values (?,?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setInt(1, r.getClinique_id());
            pre.setString(2, r.getName());
            pre.setString(3, r.getEmail());
            pre.setInt(4, r.getPhone());
            pre.setString(5, r.getAdresse());
       
         
            pre.executeUpdate();
            System.out.println("Patient Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
     public int getNbr() {
        String sql="SELECT COUNT(*) FROM `patient` ";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
       public int getNbrclinique1() {
        String sql="SELECT COUNT(*) FROM `patient` where clinique_id=19";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
           public int getNbrclinique2() {
        String sql="SELECT COUNT(*) FROM `patient` where clinique_id=26";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
               public int getNbrclinique3() {
        String sql="SELECT COUNT(*) FROM `patient` where clinique_id=27";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
                    public int getNbrclinique4() {
        String sql="SELECT COUNT(*) FROM `patient` where clinique_id=29";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }

     public void supprimerr(int id) {
 try {
            String req = "DELETE FROM `Patient` WHERE id="+id+"";
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.executeUpdate();
            System.out.println("Patient Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
      /*  public BarChart loadChart() {
        int nbr = getNbrPatient();
         NumberAxis xAxis = new NumberAxis();
     CategoryAxis yAxis = new CategoryAxis();
      String itemA = "Attendance";
      String itemB = "CT_Marks";
      String itemC = "Assignment";
      String itemD = "Others";
     Statement statement = null;
     int i=1;
     int j=2;
  XYChart.Data<String, Number> data =  new XYChart.Data<String, Number>();
  Pane pane=new Pane();
  pane.setPrefSize(600, 500);
  BarChart<String,Number> bchart=new BarChart<String,Number>(yAxis, xAxis);
  bchart.setPrefSize(550, 450);
  bchart.setTitle("Summary");
     xAxis.setLabel("Values");
     xAxis.setTickLabelRotation(45);
     yAxis.setTickLabelRotation(45);
     yAxis.setLabel("Menus");
     XYChart.Series series1 = new XYChart.Series();
     XYChart.Series series2 = new XYChart.Series();
        try{
         String sql="select id_rec, from Patient";
         ResultSet rset=statement.executeQuery(sql);
         while(rset.next()){
           XYChart.Data<String, Number> datax =  new XYChart.Data<String, Number>(rset.getString(i),nbr);
             System.out.println("nbr="+nbr);
            double totale = getNbrFeedback4()+getNbrPatient();
         series1.getData().add(new XYChart.Data("Patients",getNbrPatient()));
         series1.getData().add(new XYChart.Data("% des techniciens", (getNbrPatient()/totale)*100));
         series1.getData().add(new XYChart.Data("% des techniciens", (getNbrFeedback4()/totale)*100));

         DecimalFormat df = new DecimalFormat("0.00");
        
          
          
         } i++;j++;
      
        }catch(Exception e){
        
        }
     bchart.getData().addAll(series1);
     pane.getChildren().add(bchart);
    
   return bchart;
    }
*/
 
    public int getNbrPatient() {
        String sql="SELECT COUNT(*) FROM Patient";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
    
     
   
    
    public void supprimer(Patient r) {
 try {
            String req = "DELETE FROM Patient WHERE id="+r.getId();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Patient Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
  public int deletePatient(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE  FROM `Patient` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePatient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
   
    public void modifier(Patient r) {
      try {
            String req = "UPDATE `Patient` SET `clinique_id`=?, `name`=?, `email`=? , `phone`=? , `adresse`=?  WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
             pre.setInt(1, r.getClinique_id());
            pre.setString(2, r.getName());
            pre.setString(3, r.getEmail());
            pre.setInt(4, r.getPhone());
            pre.setString(5, r.getAdresse());
            pre.setInt(6, r.getId());
             
            pre.executeUpdate();
            System.out.println("Patient Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

   
    public List<Patient> afficher() {
        List<Patient> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Patient";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Patient( rs.getInt("id"), rs.getInt("clinique_id"),rs.getString("name"), rs.getString("email"), rs.getInt("phone"),rs.getString("adresse")));             
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
      public List<Patient> afficherdes() {
        List<Patient> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Patient order by id  desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Patient( rs.getInt("id"), rs.getInt("clinique_id"),rs.getString("name"), rs.getString("email"), rs.getInt("phone"),rs.getString("adresse")));               
           
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
      } 
   
  
   
   
    
}
