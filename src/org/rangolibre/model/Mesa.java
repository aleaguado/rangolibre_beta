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
public class Mesa {
    private String id;
    private int capacidade;
    private String status;
    private String ativo;
    
    public Mesa(){} //Construtor 1
    
    public Mesa(String i, int c, String s, String at) {
        id = i;
        capacidade = c;
        status = s;     
        ativo = at;
    }
        
    //Métodos Set
    
    public void setAtivo(String at) {
        ativo = at;
    }
    
    public void setId(String i){
        id = i;
    }
    
    public void setCapacidade(int c){
        capacidade = c;
    }

    public void setStatus(String s){
        status = s;
    }

    //Métodos Get
    public String getAtivo(){
        return ativo;
    }
    
    public String getId(){
        return id;
    }
    
    public int getCapacidade(){
        return capacidade;
    }

    public String getStatus(){
        return status;
    }    
    
    
}
