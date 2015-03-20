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
public class IngredienteCardapio {
    private int qty;
    private Ingrediente ingre;
            
    public IngredienteCardapio(){
        
    }

    public IngredienteCardapio(int q, Ingrediente i){
        qty = q;
        ingre = i;
    }    
    
    public void setQty(int q){
        qty = q;
    }
     
    public void setIngrediente(Ingrediente i){
        ingre = i;
    } 

    public int getQty(){
        return qty;
    }
    public Ingrediente getIngrediente(){
        return ingre;
    }    
    
}
