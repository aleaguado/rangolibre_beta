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
public class ItemEmAberto {
    private String mesa;
    private String pedido;
    private  String cliente;
    private int idcliente;
    private int qty;
    private String item;
    private int idtem;
    private Double preco_unidade;
    private Double preco_total;
    private String status;
    
    public ItemEmAberto(){} 
    
    public ItemEmAberto(
        String m,
        String p,
        String c,
        int idc,
        int q,
        String it,
        int idt,
        Double pu,
        Double pt) {
    
     mesa = m;
     pedido = p;
     cliente = c;
     idcliente = idc;
     qty = q;
     item = it;
     idtem = idt;
     preco_unidade = pu;
     preco_total = pt;
    }

   public ItemEmAberto(
        String m,
        String p,
        String c,
        int idc,
        int q,
        String it,
        int idt,
        Double pu,
        Double pt,
        String sta) {
    
     mesa = m;
     pedido = p;
     cliente = c;
     idcliente = idc;
     qty = q;
     item = it;
     idtem = idt;
     preco_unidade = pu;
     preco_total = pt;
     status = sta;
    }    
    
    
    
    public String getMesa() { return mesa;}
    public String getPedido() { return pedido;}
    public String getCliente() { return cliente;}
    public int getIdCliente() { return idcliente;}
    public int getQty() { return qty;}
    public String getItem() { return item;}
    public int getIdItem() { return idtem;}
    public Double getPrecoUnidade() { return preco_unidade;}
    public Double getPrecoTotal() { return preco_total;} 
    public String getStatus() {return status;}
    public void setQty(int q) { qty = q;}
    public void setPrecoTotal(double p) { preco_total = p;}
    
}
