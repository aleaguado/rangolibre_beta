/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.model;

/**
 *
 * @author aleaguado
 */
public class CatCard {
    private int idCat;         //Ident. da Cat. de Cardapio
    private String descMaster;    //Campo importantíssimo p/ agrupar variaçõs de 
    private String tipo;            // mesmo prato
    
    public CatCard(int i, String d, String t) { //Método construtor da Categoria de Cardapio
        idCat = i;
        descMaster = d;
        tipo = t;
    }
    
    public CatCard() { //Método construtor 2

    }

    //Métodos Set    
    
    public void setTipo(String t) {
        tipo = t;
    }

    public void setIdCat(int i) {
        idCat = i;
    }
    
    public void setDescCat(String d) {
        descMaster = d;
    }  
    
    //Métodos Get
    public int getIdCat() {
        return idCat;
    }
    
    public String getDescCat() {
        return descMaster;
    }   

    public String getTipo() {
        return tipo;
    }   

}
