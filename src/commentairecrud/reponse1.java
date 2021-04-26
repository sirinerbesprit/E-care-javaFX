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
public class reponse1 {
    private  Integer id;
     private  Integer commentaire_id;
   private  String pseudo;
   private  String rep;
   private  String date_rep; 

    public reponse1(Integer id, Integer commentaire_id, String pseudo, String rep, String date_rep) {
        this.id = id;
        this.commentaire_id = commentaire_id;
        this.pseudo = pseudo;
        this.rep = rep;
        this.date_rep = date_rep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentaire_id() {
        return commentaire_id;
    }

    public void setCommentaire_id(Integer commentaire_id) {
        this.commentaire_id = commentaire_id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getDate_rep() {
        return date_rep;
    }

    public void setDate_rep(String date_rep) {
        this.date_rep = date_rep;
    }

    @Override
    public String toString() {
        return  "\n\n Commentaire id :" + commentaire_id + "\n\n Pseudo :" + pseudo + "\n\n Rep :" + rep + "\n\n Date rep :" + date_rep + "";
    }
   
    
}
