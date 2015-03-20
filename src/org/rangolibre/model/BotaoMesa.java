/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.model;

import javax.swing.JButton;

/**
 *
 * @author aleaguado
 */
public class BotaoMesa {
    public JButton bt = new JButton("");
    public Mesa mesa = new Mesa();
    
    public BotaoMesa(JButton botao, Mesa me){
        bt = botao;
        mesa = me;
    }
}
