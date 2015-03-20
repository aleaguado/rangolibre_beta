/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.model;

import java.util.ArrayList;

/**
 *
 * @author aleaguado
 */
public class ItemCard extends CatCard {
    private int idCardapio;
    private String descCardapio;
    private Double preco;
    private Usuario usr;
    private String ativo;
    private String ponto;
    private boolean ehPadrao;
    
    private ArrayList<IngredienteCardapio> ingre;
    
 
    public ItemCard(){ // Construtor 1
        descCardapio = null;
        preco = null;
        usr = null;
        ehPadrao = false;
        ingre = null;
    }
 
    //Construtor 2
    public ItemCard(int i, String d, Double p, Usuario u, String at, boolean pd, ArrayList<IngredienteCardapio> in, int idCat, String dMaster, String tipoC, String pt){
        idCardapio = i;
        descCardapio = d;
        preco = p;
        usr = u;
        ehPadrao = pd;
        ingre = in;
        ativo = at;
        ponto = pt;
        super.setIdCat(idCat);
        super.setDescCat(dMaster);
        super.setTipo(tipoC);
    }
    
    
    //Métodos Set
    
    public void setPonto(String pt) {
        ponto = pt;
    }
    
    public void setAtivo(String at) {
        ativo = at;
    }
    
    
    public void setIdCard(int i){
        idCardapio = i;
    }
    
    public void setDescCard(String d){
        descCardapio = d;
    }
    
    public void setpreco(Double p){
        preco = p;
    }

    public void setUsr(Usuario u){
        usr = u;
    }
    
    public void setPadrao(boolean b){
        ehPadrao = b;
    }
    
    public void setIngre(ArrayList<IngredienteCardapio> in){
        ingre = in;
    }
    

  //Métodos Get
     public String getAtivo(){
        return ativo;
    }   
    
    public int getIdCard(){
        return idCardapio;
    }
    
    public String getDescCard(){
        return descCardapio;
    }
    
    public Double getpreco(){
        return preco;
    }
    
    public Usuario getUsr(){
        return usr;
    }
    
    public boolean getPadrao(){
        return ehPadrao;
    }
    
    public ArrayList<IngredienteCardapio> getIngre( ){
        return ingre;
    }   
    
    public String getPonto(){
        return ponto;
    }
    
    
    //Outros Métodos
    
    public void addIngre(IngredienteCardapio i){
        ingre.add(i);
    }
 
    public void dropIngre(IngredienteCardapio i){
        ingre.remove(i);
    }
    
}
