/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.model;

import java.sql.Date;

/**
 *
 * @author aleaguado
 */
public class PedidoCardapio {
    private ItemCard itemCardapio;
    private Usuario criador;
    private Usuario preparador;
    private String status;
    private Date horaCriacao;
    private Date horaPreparo;
    private Cliente cli;
    private String pago;
    private Usuario caixa;
    private String observacao;
    
    public PedidoCardapio(){} //construtor vazio
    
    public PedidoCardapio(
            ItemCard ic,
            Usuario cria,
            Usuario prep,
            String sta,
            Date hC,
            Date hP,
            Cliente c,
            String pg,
            Usuario cx,
            String obs){
      itemCardapio = ic;
      criador = cria;
      preparador = prep;
      status = sta;
      horaCriacao = hC;
      horaPreparo = hP;
      cli = c;
      pago = pg;
      caixa = cx;
      observacao = obs;
    }

    public ItemCard getItemCard() { return itemCardapio;}
    public Usuario getCriador() { return criador;}
    public Usuario getPreparador() { return preparador;}
    public String getStatus() { return status;}
    public Date getHoraCriacao() { return horaCriacao;}
    public Date getHoraPreparo() { return horaPreparo;}
    public Cliente getCliente() { return cli;}
    public String getPago() { return pago;}
    public Usuario getCaixa() { return caixa;}
    public String getObservacao() { return observacao;}
    
    public void setItemCard(ItemCard ic) { itemCardapio = ic;}
    public void setCriador(Usuario cria) { criador = cria;}
    public void setPreparador(Usuario prep) { preparador=prep;}
    public void setStatus(String st) { status = st;}
    public void setHoraCriacao(Date hC) { horaCriacao = hC;}
    public void setHoraPreparo(Date hP) { horaPreparo=hP;}
    public void setCliente(Cliente c) { cli = c;}
    public void setPago(String pg) { pago = pg;}
    public void setCaixa(Usuario cx) { caixa = cx;}
    public void setObservacao(String obs) { observacao = obs;}
    
}
