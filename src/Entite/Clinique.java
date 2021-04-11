/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author Siwar Boutaleb
 */
    public class Clinique {
       
       public Clinique(int id,String nomcl, String adressecl,int numerocl, String desccl) {
       
        this.id = id;
        this.nomcl=nomcl;
        this.numerocl=numerocl;
        this.adressecl=adressecl;
        this.desccl=desccl;
       
    }    
       
       
       public Clinique(String nomcl, String adressecl,int numerocl, String desccl) {
       
      
        this.nomcl=nomcl;
        this.numerocl=numerocl;
        this.adressecl=adressecl;
        this.desccl=desccl;
       
    }    
        

    
    public int id;
    private String nomcl;
    private String desccl;
    private String adressecl;
    private int numerocl;
   

    public Clinique() {
    }
    

   

   

   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNomcl() {
        return nomcl;
    }

    public void setNomcl(String type) {
        this.nomcl = nomcl;
    }

    public String getDesccl() {
        return desccl;
    }

    public void setNumerocl(int numerocl) {
        this.numerocl = numerocl;
    }

    public Clinique(int id, String nomcl, String desccl, String adressecl, int numerocl) {
        this.id = id;
        this.nomcl = nomcl;
        this.desccl = desccl;
        this.adressecl = adressecl;
        this.numerocl = numerocl;
    }

    public void setAdressecl(String adressecl) {
        this.adressecl = adressecl;
    }

  

    

    public void setDesccl(String desccl) {
        this.desccl = desccl;
    }

    public int getNumerocl() {
        return numerocl;
    }

    public String getAdressecl() {
        return adressecl;
    }

    


}
