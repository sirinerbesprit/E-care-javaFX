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
        String sql="SELECT COUNT(*) FROM `patient` where clinique_id=30";
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
        String sql="SELECT COUNT(*) FROM `patient` where clinique_id=32";
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
            System.out.println("Patient Supprimé !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
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
            System.out.println("Patient Supprimé !");
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
