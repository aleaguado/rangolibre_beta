/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.dao;

import com.mysql.jdbc.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.rangolibre.control.ControleCardapio;
import org.rangolibre.control.ControleIngrediente;
import org.rangolibre.control.ControleMesa;
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
public class PedidoDAO extends GenericDAO{
    ControleMesa cm = new ControleMesa();
    
// CRIAÇÃO DE PEDIDO ##############################################################
     public Pedido criarDAO(Pedido ped) throws Exception{ 
        //Executamos a procedure criar_usuario
        java.sql.CallableStatement cs = conn.prepareCall("{call criar_pedido(?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idmesap", ped.getMesa().getId());
        cs.setString("idusuariop", ped.getUsr().getId());
        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idpedidoret", Types.VARCHAR);
        cs.registerOutParameter("idclientepret", Types.INTEGER);
        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado o pedido no banco o usuario
        if (cs.getString("idpedidoret") != null)
            ped.setId(cs.getString("idpedidoret"));
        else
            throw new Exception("Pedido não criado!");         
        
         
        return ped; 
         
    }
//######################################################################################
//Testar se cliente já existe ##########################################################
         //Check se usuario existe ja no banco de dados
    public boolean clienteExisteDAO(Cliente cli)  throws Exception {
        String sql = "select * from cliente where nome = '" + cli.getNomeCliente() + "' and idpedido = '" + cli.getPedido().getId() + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }
    
    //Vamos criar o cliente
    public Cliente criarClienteDAO(Cliente cli) throws Exception{
           //Executamos a procedure criar_usuario
        java.sql.CallableStatement cs = conn.prepareCall("{call adicionar_cliente(?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idmesap", cli.getPedido().getMesa().getId());
        cs.setString("nomep",cli.getNomeCliente());
        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idclienter", Types.INTEGER);
        cs.registerOutParameter("nomer", Types.VARCHAR);
        cs.registerOutParameter("idpedidovar", Types.VARCHAR);
        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado o cliente padrão no banco
        if (cs.getInt("idclienter") > 0) {
            cli.setIdCliente(cs.getInt("idclienter"));

                    }
        else
            throw new Exception("Cliente não criado!");           
         
        return cli;   
    }
     
    //Método que adiciona um item de cardápio ao pedido criando no banco o objeto PedidoCardapio
    
     public PedidoCardapio adicionarItemDAO(PedidoCardapio pc) throws Exception{ 
           //Executamos a procedure criar_usuario
        java.sql.CallableStatement cs = conn.prepareCall("{call adicionar_item(?, ?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idpedidop", pc.getCliente().getPedido().getId());
        cs.setInt("idclientep",pc.getCliente().getIdCliente());
        cs.setInt("idcardapiop",pc.getItemCard().getIdCard());
        cs.setString("idcriadorp",pc.getCriador().getId());
        cs.setString("obsp",pc.getObservacao());
        
        //Registramos o parametro de retorno
        cs.registerOutParameter("horacriar", Types.DATE);
        
        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado o cliente padrão no banco
        if (cs.getDate("horacriar") != null) {
            pc.setHoraCriacao(cs.getDate("horacriar"));
                    }
        else
            throw new Exception("Problemas ao adicionar este item no pedido!!!");           
         
        return pc;    
     }
     
     public ArrayList<Pedido> obterPedidosDAO(String status)throws Exception{
         
            ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
            String sql;
            
            if (status == null)
                sql = "select * from pedido where idstatus = 'A' or idstatus = 'C'";
            else
                sql = "select * from pedido where idstatus = '" + status.trim() + "'";
            
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                listaPedido.add(new Pedido(rs.getString("idpedido"),rs.getDate("horain"), cm.ObterMesa(rs.getString("idmesa")), null, rs.getString("idstatus"), null, null ));
            }
            return listaPedido;          
     }

         public Pedido obterPedidoDAO(String idped)throws Exception{
         
            String sql = "select * from pedido where idpedido = '" + idped + "'";
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
             if(rs.next()){
                return new Pedido(rs.getString("idpedido"),rs.getDate("horain"), cm.ObterMesa(rs.getString("idmesa")), null, rs.getString("idstatus"), null, null );
            } else {
                 throw new Exception("Pedido não existe!!!");
             }          
     } 
         
        public Pedido obterPedidoMesaDAO(String mesa)throws Exception{
         
           String sql = "select * from pedido where idmesa = '" + mesa.trim() + "' and";
           sql = sql + " (idstatus = 'C' or idstatus = 'A')";
           
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
             if(rs.next()){
                return new Pedido(rs.getString("idpedido"),rs.getDate("horain"), cm.ObterMesa(rs.getString("idmesa")), null, rs.getString("idstatus"), null, null );
            } else {
                 throw new Exception("Não existe pedido aberto para essa mesa!!!");
             }          
     } 
         
         
         
     
         public ArrayList<Cliente> obterClientesDAO(Pedido ped)throws Exception{
            ArrayList<Cliente> acli = new ArrayList<Cliente>();
            String sql = "select * from cliente where idpedido = '" + ped.getId() + "'";
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
             while(rs.next()){
                 acli.add(new Cliente(rs.getInt("idcliente"),ped,rs.getString("nome"), null, null, null));
                 
            }
             return acli;
     } 

     //Aqui talves seja um dos poucos lugares em que preciso retornar o esquema completo
     public ArrayList<PedidoCardapio> obterListaPedidoCardapio(Cliente cli, Pedido ped) throws Exception{
        Pedido pedr = new Pedido();
        Usuario criador;
        Usuario preparo;
        Cliente clir;
        ItemCard ic;
         ArrayList<PedidoCardapio> lista = new ArrayList<>();
         ControleUsuario cu = new ControleUsuario();
         ControleCardapio cc = new ControleCardapio();
         String sql = "select * from pedicar where ";
         
         if (cli == null) {
             pedr = ped;
             sql = sql + " idpedido = '" + ped.getId() + "'";
         }
         if (ped == null) {
             pedr = cli.getPedido();
             sql = sql + " idpedido = '" + cli.getPedido().getId() + "' and idcliente = " + cli.getIdCliente();
         }
         
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
           
            
            
             while(rs.next()){
                //pedr = obterPedidoDAO(rs.getString("idpedido"));
                //criador = cu.obterUsuario(rs.getString("idcriador"));

                if (rs.getString("idpreparo") != null)
                    //preparo = cu.obterUsuario(rs.getString("idpreparo"));
                    preparo = new Usuario(rs.getString("idpreparo"),null,null,null,null);
                else
                    preparo = null;
                
                    clir = new Cliente(rs.getInt("idcliente"),pedr,null, null, null, null);
                    
                    ic = cc.obterItemCardapio(rs.getInt("idcardapio"));

                lista.add(new PedidoCardapio(ic, null, preparo,rs.getString("idstatus"),rs.getDate("horacria"), rs.getDate("horapreparo"), clir,rs.getString("pago"), null, rs.getString("observacao") ));
            }
             return lista;        
     }
//###############
    //Aqui talves seja um dos poucos lugares em que preciso retornar o esquema completo
     public ArrayList<PedidoCardapio> obterListaPedidoCardapioSimples(Cliente cli) throws Exception{
         Pedido pedr;
         Cliente clir;
         ItemCard ic;
         ArrayList<PedidoCardapio> lista = new ArrayList<>();
         //ControleUsuario cu = new ControleUsuario();
         ControleCardapio cc = new ControleCardapio();
         String sql = "select * from pedicar where ";
         sql = sql + " idpedido = '" + cli.getPedido().getId() + "' and idcliente = " + cli.getIdCliente();
         
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
           
            
             while(rs.next()){
                //pedr = obterPedidoDAO(rs.getString("idpedido"));
                //criador = cu.obterUsuario(rs.getString("idcriador"));

                 //clir = new Cliente(rs.getInt("idcliente"),pedr,null, null, null, null);
                 ic = cc.obterItemCardapio(rs.getInt("idcardapio"));

                lista.add(new PedidoCardapio(ic, null, null,rs.getString("idstatus"),rs.getDate("horacria"), rs.getDate("horapreparo"), cli,rs.getString("pago"), null, rs.getString("observacao") ));
            }
             return lista;        
     }     
//###########     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
  
     public Cliente obterClienteDAO(String idped, int idcliente) throws Exception{
 
            String sql = "select * from cliente where idpedido = '" + idped + "' and idcliente = " + idcliente;
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
             if(rs.next()){
                 return new Cliente(rs.getInt("idcliente"),obterPedidoDAO(rs.getString("idpedido")),rs.getString("nome"), null, null, null);                
             } else {
                 throw new Exception("Cliente não encontrado!!!");
             } 
             
    }
     
     public Cliente obterClienteNomeDAO(String idped, String nome) throws Exception{
 
            String sql = "select * from cliente where idpedido = '" + idped + "' and nome = '" + nome + "'";
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
             if(rs.next()){
                 return new Cliente(rs.getInt("idcliente"),obterPedidoDAO(rs.getString("idpedido")),rs.getString("nome"), null, null, null);                
             } else {
                 throw new Exception("Cliente não encontrado!!!");
             } 
             
    }     
}
