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
public class panier {
   private Integer id ;
private String   code_panier;
private Integer   quantite;
private Float   prix_tot;
private String   produits;

    public panier(Integer id, String code_panier, Integer quantite, Float prix_tot, String produits) {
        this.id = id;
        this.code_panier = code_panier;
        this.quantite = quantite;
        this.prix_tot = prix_tot;
        this.produits = produits;
    }

    public Integer getId() {
        return id;
    }

    public String getCode_panier() {
        return code_panier;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Float getPrix_tot() {
        return prix_tot;
    }

    public String getProduits() {
        return produits;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode_panier(String code_panier) {
        this.code_panier = code_panier;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setPrix_tot(Float prix_tot) {
        this.prix_tot = prix_tot;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }

  

   
    
}
