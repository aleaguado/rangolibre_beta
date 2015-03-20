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
public class Cliente {
    private int id; //Identificador do cliente que deve ser usado unto com pedido
    private Pedido ped;
    private String nomeCliente; //nome do cliente
    private String CPF;
    private String RG;
    private ArrayList<PedidoCardapio> listaItens;
    
    public Cliente(){} //©onstrutor vazio
    
    public Cliente(
            int idCliente,
            Pedido pedidoCliente,
            String nome,
            String CPFCliente,
            String RGCliente,
            ArrayList<PedidoCardapio> li
    ){
            id = idCliente;
            ped = pedidoCliente;
         //   ped.getListaClientes().add(this);
            nomeCliente = nome;
            CPF = CPFCliente;
            RG = RGCliente;
            listaItens = li;
    }
    
    public int getIdCliente() { return id;} 
    public Pedido getPedido() { return ped;}
    public String getNomeCliente() { return nomeCliente;}
    public String getCPF() { return CPF;}
    public String getRG() { return RG;}   
    public ArrayList<PedidoCardapio> getListaItens() { return listaItens;}

    public void setIdCliente(int i) { id = i;} 
    public void setPedido(Pedido p) { ped = p; }
    public void setNomeCliente(String nc) { nomeCliente = nc;}
    public void setCPF(String cp) { CPF = cp;}
    public void setRG(String r) { RG = r;}   
    public void setListaItens(ArrayList<PedidoCardapio> li) { listaItens = li;}
}
