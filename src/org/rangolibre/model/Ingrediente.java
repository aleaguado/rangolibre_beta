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
public class Ingrediente {
    private int idingre ;        //Identificador do ingrediente
    private String descricao;    //Descrição do ingrediente
    private int qty;        //Quantidade de ingredientes em estoque
    private CatIngre cat;   //Ref. p/ o objeto categoria, sobre a categoria do ingrediente.
    private Usuario usr;    //Ref. p/ o objeto usr, sobre o usuário que atualizou pela
    private String ativo;     //ultima vez a qty de ingredientes
    private ArrayList<Fornecedor> listaFornecedor;
    
    public Ingrediente(int i, String d, int q, CatIngre c, Usuario u, String a, ArrayList<Fornecedor> lf){ //Construtor
        idingre = i;
        descricao = d;
        qty = q;
        cat = c;
        usr = u;            
        ativo = a;
        listaFornecedor = lf;
    }
    
    public Ingrediente(){ //Construtor 2
         
    }
    
    public void setListaFornecedor(ArrayList<Fornecedor> lf) {
        listaFornecedor = lf;
    }    

    public ArrayList<Fornecedor> getListaFornecedor(){
        return listaFornecedor;
    }
    
    
    //Métodos set
    public void setId(int i) {
        idingre = i;
    }
    
    public void setDesc(String d) {
        descricao = d;
    } 
    
    public void setQty(int q) {
        qty = q;
    }        
    
    public void setCat(CatIngre c) {
        cat = c;
    }        

    public void setUsr(Usuario u) {
        usr = u;
    }     

    public void setAtivo(String a) {
        ativo = a;
    }
    
    //Métodos get

    public int getId() {
        return idingre;
    }
    
    public String getDesc() {
        return descricao;
    } 
    
    public int getQty() {
        return qty;
    }        
    
    public CatIngre getCat() {
        return cat;
    }        

    public Usuario getUsr() {
        return usr;
    }      
     
    public String getAtivo() {
        return ativo;
    } 
      
}
