/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.rangolibre.model.Cliente;
import org.rangolibre.model.ItemEmAberto;
import org.rangolibre.model.Pedido;
import org.rangolibre.model.PedidoCardapio;
import org.rangolibre.model.Usuario;

/**
 *
 * @author aleaguado
 */
public class CaixaDAO extends GenericDAO{
    
    
    public ArrayList<ItemEmAberto> obterTotaisDAO(Pedido p, Cliente cli) throws Exception {
                 
            ArrayList<ItemEmAberto> listaPedido = new ArrayList<ItemEmAberto>();
            String sql;
            
            if (cli == null)
                sql = "select * from itens_em_aberto where PEDIDO = '" + p.getId() + "'";
            else
                sql = "select * from itens_em_aberto where PEDIDO = '" + cli.getPedido().getId() + "' and IDCLIENTE = " + cli.getIdCliente();
            
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                listaPedido.add(new ItemEmAberto(
                        rs.getString("MESA"),
                        rs.getString("PEDIDO"),
                        rs.getString("CLIENTE"),
                        rs.getInt("IDCLIENTE"),
                        rs.getInt("QTY"),
                        rs.getString("ITEM"),
                        rs.getInt("IDTEM"),
                        rs.getDouble("PRECO UNIDADE"),
                        rs.getDouble("PREÇO TOTAL")));
            }
            return listaPedido;   
    }
    
            public void pagarMesaDAO(Pedido ped, Usuario usr) throws Exception {
        
            CallableStatement cs = conn.prepareCall("{call pagar_mesa(?, ?, ?)}");
            cs.setString("pedidop", ped.getId());
            cs.setString("idusuariop", usr.getId());
            
            cs.registerOutParameter("pedidor", Types.VARCHAR);

            cs.execute();

            if (!cs.getString("pedidor").equals(ped.getId())) {
                throw new Exception("Problemas ao fechar pedido");
            }
    }

            public void pagarClienteDAO(Cliente cli, Usuario usr) throws Exception {
        
            CallableStatement cs = conn.prepareCall("{call pagar_cliente(?, ?, ?, ?)}");

            cs.setString("pedidop", cli.getPedido().getId());
            cs.setInt("clientep", cli.getIdCliente());
            cs.setString("idusuariop",usr.getId());
            
            cs.registerOutParameter("pedidor", Types.VARCHAR);

            cs.execute();

            if (!cs.getString("pedidor").equals(cli.getPedido().getId())) {
                throw new Exception("Problemas ao fechar pedido");
            }
    }
            
            public void pagarItemDAO(String pedido, int cliente, int cardapio, Usuario usr) throws Exception {
        
            CallableStatement cs = conn.prepareCall("{call pagar_item(?, ?, ?, ?, ?)}");
            cs.setString("pedidop", pedido);
            cs.setInt("clientep", cliente);
            cs.setInt("idcardapiop", cardapio);
            cs.setString("idusuariop", usr.getId());
            
            cs.registerOutParameter("pedidor", Types.VARCHAR);

            cs.execute();

            if (!cs.getString("pedidor").equals(pedido)) {
                throw new Exception("Problemas ao fechar pedido");
            }
    }            
            
    
}
