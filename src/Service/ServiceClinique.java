/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entite.Clinique;
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
public class ServiceClinique {
Connection cnx = DataSource.getInstance().getCnx();

    private Statement ste;
    private PreparedStatement pst;
    
    public void ajouter(Clinique r) {
          try {
                       
            String req = "INSERT INTO `Clinique` (`nomcl`, `adressecl`, `numerocl`,"
                    + "`desccl`) values (?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, r.getNomcl());
            pre.setString(2, r.getAdressecl());
            pre.setInt(3, r.getNumerocl());
            pre.setString(4, r.getDesccl());
         
            pre.executeUpdate();
            System.out.println("clinique Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }

     public void supprimerr(int id) {
 try {
            String req = "DELETE FROM `Clinique` WHERE id="+id+"";
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.executeUpdate();
            System.out.println("Clinique Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
      /*  public BarChart loadChart() {
        int nbr = getNbrClinique();
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
         String sql="select id_rec, from Clinique";
         ResultSet rset=statement.executeQuery(sql);
         while(rset.next()){
           XYChart.Data<String, Number> datax =  new XYChart.Data<String, Number>(rset.getString(i),nbr);
             System.out.println("nbr="+nbr);
            double totale = getNbrFeedback4()+getNbrClinique();
         series1.getData().add(new XYChart.Data("Cliniques",getNbrClinique()));
         series1.getData().add(new XYChart.Data("% des techniciens", (getNbrClinique()/totale)*100));
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
 
    public int getNbrClinique() {
        String sql="SELECT COUNT(*) FROM Clinique";
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
    
     
   
    
    public void supprimer(Clinique r) {
 try {
            String req = "DELETE FROM Clinique WHERE id="+r.getId();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Clinique Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
 public int deleteclonique(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE  FROM `clinique` WHERE id="+id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePatient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
   
    public void modifier(Clinique r) {
      try {
            String req = "UPDATE `Clinique` SET `nomcl`=?, `adressecl`=?, `numerocl`=? , `desccl`=?  WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
             pre.setString(1, r.getNomcl());
            pre.setString(2, r.getAdressecl());
            pre.setInt(3, r.getNumerocl());
            pre.setString(4, r.getDesccl());
            pre.setInt(5, r.getId());
             
            pre.executeUpdate();
            System.out.println("Clinique Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

   
    public List<Clinique> afficher() {
        List<Clinique> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Clinique";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Clinique( rs.getInt("id"),rs.getString("nomcl"), rs.getString("adressecl"), rs.getInt("numerocl"), rs.getString("desccl")));             
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
      public List<Clinique> afficherdes() {
        List<Clinique> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Clinique order by id  desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Clinique( rs.getInt("id"),rs.getString("nomcl"), rs.getString("adressecl"), rs.getInt("numerocl"), rs.getString("desccl")));               
           
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
      } 
   
  
   
   
    
}
