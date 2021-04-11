/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentairecrud;

import java.util.Date;

/**
 *
 * @author Mohamed
 */



   public class reponse {
   private  Integer id;
   private  String pseudo;
   private  String rep;
   private  String date_rep;  
   private  Integer commentaire_id;

        public reponse(Integer id, String pseudo, String rep, String date_rep, Integer commentaire_id) {
            this.id = id;
            this.pseudo = pseudo;
            this.rep = rep;
            this.date_rep = date_rep;
            this.commentaire_id = commentaire_id;
        }

        public Integer getId() {
            return id;
        }

        public String getPseudo() {
            return pseudo;
        }

        public String getRep() {
            return rep;
        }

        public String getDate_rep() {
            return date_rep;
        }

        public Integer getCommentaire_id() {
            return commentaire_id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }

        public void setRep(String rep) {
            this.rep = rep;
        }

        public void setDate_rep(String date_rep) {
            this.date_rep = date_rep;
        }

        public void setCommentaire_id(Integer commentaire_id) {
            this.commentaire_id = commentaire_id;
        }

       
   
}
   

