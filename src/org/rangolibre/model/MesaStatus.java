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
public class MesaStatus extends Mesa {
   Pedido ped = new Pedido();
    
    public MesaStatus(){} //Construtor 1
    
    public MesaStatus(String i, int c, String s, String p) {
 
        this.setId(i);
        this.setCapacidade(c);
        this.setStatus(s); 
        ped.setId(p);
    }
    
    public String getPedido() {
        if (ped.getId() == null ) {
            return "";
        } else {
            return ped.getId();
        
        }
    }
    
}
