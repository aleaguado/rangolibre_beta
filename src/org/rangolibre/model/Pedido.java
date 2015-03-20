/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author aleaguado
 */
public class Pedido {
    
    private String id;
    private Date horain;
    private Date horaout;
    private Mesa mesa;
    private Usuario usr;
    private String status;
    //private ArrayList<PedidoCardapio> listaItens; //tentar não usar ... 
   // private ArrayList<Cliente> listaClientes;
    
    public Pedido(){} //Construtor 1
    
    public Pedido(String i, Date in, Mesa m, Usuario u, String s, ArrayList<PedidoCardapio> a, ArrayList<Cliente> lC) {
        id = i;
        horain = in;
        mesa = m;
        usr = u;
        status = s;
      //  listaItens = a;
      //   listaClientes = lC;
    }
    
    //Metodos Set
   //  public void setListaClientes(ArrayList<Cliente> lC){
     //   listaClientes = lC;
   // }
    
    
    public void setId(String i){
        id = i;
    }
    public void setHoraIn(Date in){
        horain = in;
    }
    public void setHoraOut(Date out){
        horaout = out;
    }
    public void setMesa(Mesa m) {
        mesa = m;
    }
    public void setUsr(Usuario u){
        usr = u;
    }
    
    public void setStatus(String s){
        status = s;
    }
  //  public void setListaItens(ArrayList<PedidoCardapio> a) {
  //      listaItens = a;
  //  }
    
    //Metodos Get
     public String getId(){
        return id;
    }
    public Date getHoraIn(){
       return horain;
    }
    public Date getHoraOut(){
        return horaout;
    }
    public Mesa getMesa() {
        return mesa;
    }
    public Usuario getUsr(){
        return usr;
    }
 //   public ArrayList<PedidoCardapio> getListaItens() {
 //       return listaItens;
 //   }   
    
    public String getStatus(){
        return status;
    }
    
    //Outros métodos
    
 //   public void addItem(PedidoCardapio i){
 //       listaItens.add(i);
 //   }
    
//    public void dropItem(PedidoCardapio i){
//        listaItens.remove(i);
//    }
    
 //   public ArrayList<Cliente> getListaClientes(){
 //       return listaClientes;
 //   }
}
