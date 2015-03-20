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
public class Perfil {
    private int id;                 //Identificador de tipo de Perfil
    private String desc;            //Descricao do Perfil
    private ArrayList<Permissao> permissoes;    //Lista de Permissoes do perfil
 

    public Perfil(){} //Construtor 2
    
    public Perfil(int i, String d, ArrayList<Permissao> p) {    //Método Construtor
        id = i;
        desc = d;
        permissoes = p;
    }
    
    //Métodos Set    
    public void setId(int i) {
        id = i;
    }
    
    public void setDesc(String d) {
        desc = d;
    }
    
    public void setPerm(ArrayList<Permissao> p) {
        permissoes = p;
    }
    
    //Métodos Get
    public int getId() {
        return id;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public ArrayList<Permissao> getPerm() { //Retorna um ArrayList com as permissoes daquele perfil
        return permissoes;
    }
    
    //Outros métodos importantes
    
    public void addPerm(Permissao p) {
        permissoes.add(p);
    }
    
    public void dropPerm(Permissao p) {
        permissoes.remove(p);
    }
    
}
