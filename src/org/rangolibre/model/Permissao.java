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
public class Permissao {
    private String id; //identificador de permissão
    private String desc; //descricao
    
    
    public Permissao(){} //Construtor 2
    
    public Permissao(String i, String d){ //Método Construtor
        id = i;
        desc = d;
    }
    
    //Métodos Set
    public void setId(String i){
        id = i;
    }
    
    public void setDesc(String d){
        desc = d;
    }
    
    public void update(String i, String d){ //Método para atualizar uma permissao
        id = i;
        desc = d;
    }
    
    //Métodos Get
    
    public String getId(){
        return id;
    }
    
    public String getDesc(){
        return desc;
    }    
}
