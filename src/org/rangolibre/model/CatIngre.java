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
public class CatIngre {
    private int idcat;         //Ident. da Cat. de Ingrediente
    private String descricao;    //Descrição da Categoria
    
    public CatIngre(int i, String d) { //Método construtor da Categoria de Ingredientes
        idcat = i;
        descricao = d;
    }
    
     
    public CatIngre() { //Método construtor 2

    }   
    

    //Métodos Set    
    public void setId(int i) {
        idcat = i;
    }
    
    public void setDesc(String d) {
        descricao = d;
    }  
    
    //Métodos Get
    public int getId() {
        return idcat;
    }
    
    public String getDesc() {
        return descricao;
    }   
}
