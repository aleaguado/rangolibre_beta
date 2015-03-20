/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view;

import javax.swing.JButton;
import org.rangolibre.model.ItemCard;

/**
 *
 * @author aleaguado
 */
public class CardapioBotao {
    public ItemCard itemCardapio;
    public JButton bt;
    
    public CardapioBotao(ItemCard ic, JButton b){
        itemCardapio = ic;
        bt = b;
    }   
}
