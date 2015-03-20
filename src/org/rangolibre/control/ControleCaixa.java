/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.CaixaDAO;
import org.rangolibre.model.Cliente;
import org.rangolibre.model.ItemEmAberto;
import org.rangolibre.model.Pedido;
import org.rangolibre.model.PedidoCardapio;
import org.rangolibre.model.Usuario;

/**
 *
 * @author aleaguado
 */
public class ControleCaixa {
    CaixaDAO cx = new CaixaDAO();
    ControlePedido cp = new ControlePedido();
    
    public ArrayList<ItemEmAberto> obterTotais(Pedido p, Cliente cli) throws Exception {
        if (cli == null) 
            return cx.obterTotaisDAO(p, null);
        else
            return cx.obterTotaisDAO(null, cli);
    }
    
    public Pedido pagarMesa(Pedido ped, Usuario usr) throws Exception{
        cx.pagarMesaDAO(ped, usr);
        return cp.obterPedido(ped.getId());
    }
    
    public Cliente pagarCliente(Cliente cli, Usuario usr) throws Exception{
        cx.pagarClienteDAO(cli, usr);
        return cp.obterCliente(cli.getPedido().getId(), cli.getIdCliente());
    }
    
    public void pagarItem(String pedido, int cliente, int cardapio, Usuario usr) throws Exception {

        cx.pagarItemDAO(pedido, cliente, cardapio, usr);
    }
    
}
