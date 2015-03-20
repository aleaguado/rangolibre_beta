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
public class Usuario {
    private String id;
    private String nome;
    private String senha;
    private Perfil perf;   
    private String ativo;    
    
    public Usuario(){} //construtor 2
    
    
    public Usuario(String i, String n, String s, Perfil p, String at){ // Método Construtor
        id = i;
        nome = n;
        senha = s;
        perf = p;
        ativo = at;
    }
    
   public void setAtivo(String at) {
        ativo = at;
    }
    
    public String getAtivo() {
        return ativo;
    }
   
    
    //Métodos Set  
    public void setId(String i) {
        id = i;
    }
    
    public void setNome(String n) {
        nome = n;
    }    

    public void setSenha(String s) {
        senha = s;
    }
    
    public void setPerfil(Perfil p) {
        perf = p;
    }    

    public void update(String i, String n, String s, Perfil p){ // Método p/ atualizar objeto
        id = i;
        nome = n;
        senha = s;
        perf = p;
    }    
    
    //Métodos Get
    
    public String getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }    

    public String getSenha() {
        return senha;
    }
    
    public Perfil getPerfil() {
        return perf;
    } 
    
    //Outros Métodos
        
}
