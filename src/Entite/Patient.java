/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Siwar Boutaleb
 */
public class Patient {
 
      public Patient(int id,int clinique_id ,String name, String email,int phone, String adresse) {
       this.id=id;
       this.clinique_id=clinique_id;
       this.name=name;
       this.email=email;
       this.phone=phone;
           this.adresse =adresse;
       
       
    }
      public Patient(int clinique_id ,String name, String email,int phone, String adresse) {
     
       this.clinique_id=clinique_id;
       this.name=name;
       this.email=email;
       this.phone=phone;
           this.adresse =adresse;
       
       
    }
      int id ;
      int clinique_id;
      int phone ;
      String name; 
      String email;
      String adresse;

    public String getAdresse() {
        return adresse;
    }

    public int getClinique_id() {
        return clinique_id;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClinique_id(int clinique_id) {
        this.clinique_id = clinique_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
      
     
}
