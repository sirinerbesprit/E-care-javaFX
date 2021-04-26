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
public class Avis {
    
  private int id;
  private int pharmacie_id;
  private String note;
  private int rate;

    public Avis(int id, int pharmacie_id, String note, int rate) {
        this.id = id;
        this.pharmacie_id = pharmacie_id;
        this.note = note;
        this.rate = rate;
    }

    public int getPharmacie_id() {
        return pharmacie_id;
    }

    public void setPharmacie_id(int pharmacie_id) {
        this.pharmacie_id = pharmacie_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Avis{" + "pharmacie_id=" + pharmacie_id + '}';
    }
    
}

   
