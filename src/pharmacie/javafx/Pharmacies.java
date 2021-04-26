/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacie.javafx;

/**
 *
 * @author User
 */
public class Pharmacies {
    
  private int ID;
  private String Nom;
  private String Adresse;
  private int Numtel;

    public Pharmacies(int ID, String Nom, String Adresse, int Numtel) {
        this.ID = ID;
        this.Nom = Nom;
        this.Adresse = Adresse;
        this.Numtel = Numtel;
    }

    public int getID() {
        return ID;
    }

    public String getNom() {
        return Nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public int getNumtel() {
        return Numtel;
    }

    @Override
    public String toString() {
        return this.Nom;
    }
   
    
}
