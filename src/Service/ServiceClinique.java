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
            System.out.println("Clinique Modfiée !");
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
