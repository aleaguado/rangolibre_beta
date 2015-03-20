/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.PreparacaoDAO;
import org.rangolibre.model.PedidoCardapio;

/**
 *
 * @author aleaguado
 */
public class ControlePreparacao {
    
    PreparacaoDAO pp = new PreparacaoDAO();
    ControlePedido cp = new ControlePedido();
    
    
    //Retorna uma lista de cardapio por tipo ...
    public ArrayList<PedidoCardapio> obterListaCardapio(String tipo)throws Exception{
        return pp.obterListaPedidoCardapioDAO(tipo);

    }
    
    public PedidoCardapio prepararItem(PedidoCardapio pc) throws Exception{
            return pp.prepararItemDAO(pc);
    }
    
    
}
