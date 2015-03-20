/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.rangolibre.control.ControleCardapio;
import org.rangolibre.control.ControlePedido;
import org.rangolibre.control.ControleUsuario;
import org.rangolibre.model.Cliente;
import org.rangolibre.model.ItemCard;
import org.rangolibre.model.Mesa;
import org.rangolibre.model.Pedido;
import org.rangolibre.model.PedidoCardapio;
import org.rangolibre.model.Usuario;

/**
 *
 * @author aleaguado
 */
public class PreparacaoDAO extends GenericDAO{
         //Aqui talves seja um dos poucos lugares em que preciso retornar o esquema completo
     public ArrayList<PedidoCardapio> obterListaPedidoCardapioDAO(String tipo) throws Exception{
         ArrayList<PedidoCardapio> lista = new ArrayList<>();
    
         ControleUsuario cu = new ControleUsuario();
         ControlePedido cp = new ControlePedido();
         ControleCardapio cc = new ControleCardapio();

         String sql = "select * from preparosimples";
         if (tipo != null) {
            sql = sql + " where TIPO IN(" + tipo + ")";
         }
         
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
           
             while(rs.next()){
                Pedido pedr = new Pedido();
                pedr.setId(rs.getString("IDPEDIDO"));
                
                if (!rs.getString("MESA").isEmpty()) {
                    Mesa m = new Mesa(rs.getString("MESA"), 0, null, "Y");                
                    pedr.setMesa(m);
                }
                Cliente clir = new Cliente(rs.getInt("IDCLIENTE"),pedr,rs.getString("CLIENTE"), null, null, null);

                ItemCard ic = new ItemCard(rs.getInt("IDCARDAPIO"),rs.getString("DESCRICAO"),null,null,"Y", false,null,0,null,rs.getString("TIPO"), rs.getString("PONTOCARNE")  );

                Usuario criador = new Usuario(rs.getString("GARÇON"),null,null,null,null);
                
                lista.add(new PedidoCardapio(ic, criador, null,rs.getString("STATUS"),rs.getDate("HORA"), null, clir,null, null, rs.getString("OBSERVACAO") ));
            }
             return lista;        
     }
     
     
     public PedidoCardapio prepararItemDAO(PedidoCardapio pc) throws Exception{
        //Executamos a procedure abaixo
        java.sql.CallableStatement cs = conn.prepareCall("{call preparar_item(?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idpedidop", pc.getCliente().getPedido().getId());
        cs.setInt("idclientep",pc.getCliente().getIdCliente());
        cs.setInt("idcardapiop", pc.getItemCard().getIdCard());
        cs.setString("idpreparop", pc.getPreparador().getId());
        

        //Registramos o parametro de retorno
        cs.registerOutParameter("horapreparor", Types.DATE);
        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado o pedido no banco o usuario
        if (cs.getDate("horapreparor") != null) { 
            pc.setHoraPreparo(cs.getDate("horapreparor"));
            pc.setStatus("P");
        } else
            throw new Exception("Problemas pra preparar este item no sistema!!!");         
         
        return pc; 
     }
    
    
    
}
