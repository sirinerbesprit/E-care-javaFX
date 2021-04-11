/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.care;

/**
 *
 * @author achra
 */
public class livraison {
    private Integer id ;
private String   nom;
private String   adresse;
private String   numero;
private String   mail;
private String   message;

private Integer   commande_id;

    public livraison(Integer id, String nom, String adresse, String numero, String mail, String message, Integer commande_id) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numero = numero;
        this.mail = mail;
        this.message = message;
        this.commande_id = commande_id;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumero() {
        return numero;
    }

    public String getMail() {
        return mail;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCommande_id() {
        return commande_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCommande_id(Integer commande_id) {
        this.commande_id = commande_id;
    }

   
    
}
