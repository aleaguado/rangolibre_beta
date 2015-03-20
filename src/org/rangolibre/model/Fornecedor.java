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
public class Fornecedor {
    private int idfornecedor;
    private String nome;
    private String cnpj;
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
    private String telefone;
    private Usuario usr;
    private String ativo;
    
    public Fornecedor(){} //construtor vazio
    
    public Fornecedor(
            int id,String n, String cn, String end,
            String cid, String est, String pa,
            String tel, Usuario u, String at
    ){ //construtor completo
        idfornecedor = id;
        nome = n;
        cnpj = cn;
        endereco = end;       
        cidade = cid;
        estado = est;
        pais = pa;
        telefone = tel;        
        usr = u; 
        ativo = at;        
    }
    
    public int getId(){
        return idfornecedor;
    }
    public String getNome(){
        return nome;
    }    
    public String getCnpj(){
        return cnpj;
    }        
    public String getEndereco(){
        return endereco;
    }        
    public String getCidade(){
        return cidade;
    }       
    public String getEstado(){
        return estado;
    }       
    public String getPais(){
        return pais;
    }        
    public String getTelefone(){
        return telefone;
    }        
    public Usuario getUsuario(){
        return usr;
    }        
    public String ativo(){
        return ativo;
    }        
    public void setUsuario(Usuario u){
        usr = u;
    } 
    
     public void setId(int i){
        idfornecedor = i;
    }  
}
