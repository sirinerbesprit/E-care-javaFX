/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentairecrud;

/**
 *
 * @author Mohamed
 */
public class Commentaire {
   private  Integer id;
   private  String pseudo;
   private  String sujet;
   private  String medecin;  
   private  String question;

    public Commentaire(Integer id, String pseudo, String sujet, String medecin, String question) {
        this.id = id;
        this.pseudo = pseudo;
        this.sujet = sujet;
        this.medecin = medecin;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
   

  
}
