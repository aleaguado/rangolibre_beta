/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.PedidoDAO;
import org.rangolibre.model.Cliente;
import org.rangolibre.model.Pedido;
import org.rangolibre.model.PedidoCardapio;

/**
 *
 * @author aleaguado
 */
public class ControlePedido {
    
    PedidoDAO pd = new PedidoDAO();
    ControleMesa cm = new ControleMesa();
    
    
    //Método que criará um pedido e também o cliente PADRAO, numero 0 !!!
    public Pedido criar(Pedido ped) throws Exception{
        Pedido p;
        
        //Vamos fazer algumas verificações antes de criar um pedido
        if (cm.mesaVazia(ped.getMesa())) {
            p = pd.criarDAO(ped); //Pedimos para criar o pedido
            return p;
 
        } else
            throw new Exception("Esta mesa não esta vazia!!!");

        }
    
    //Cria um cliente para um pedido
    public Cliente criarCliente(Cliente cli) throws Exception{
        if (!pd.clienteExisteDAO(cli)) { // Se não existe um cliente com esse nome nesse pedido
            cli = pd.criarClienteDAO(cli);
        }
        return cli; //Testar depois se isso funciona ou se não se perdem nas referencias;
    }
    
    public PedidoCardapio adicionarItem(PedidoCardapio pc) throws Exception{
        return pd.adicionarItemDAO(pc);
    }
    
    public ArrayList<Pedido> obterPedidosAbertos()throws Exception{
        return pd.obterPedidosDAO(null);
    }
    
    public Pedido obterPedido(String idped) throws Exception{
        return pd.obterPedidoDAO(idped);
    }
    
    public Pedido obterPedidoMesa(String mesa) throws Exception{
        return pd.obterPedidoMesaDAO(mesa);
    }
    
    public ArrayList<Cliente> obterClientes(Pedido ped) throws Exception{
        return pd.obterClientesDAO(ped);
    }
    
    public ArrayList<PedidoCardapio> obterListaPedidoCardapio(Cliente cli)throws Exception{
        ArrayList<PedidoCardapio> lista; 
        lista = pd.obterListaPedidoCardapio(cli, null);
        
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).setCliente(obterCliente(lista.get(i).getCliente().getPedido().getId(),lista.get(i).getCliente().getIdCliente()));
        }
        
        return lista;
    }

    public ArrayList<PedidoCardapio> obterListaPedidoCardapioSimples(Cliente cli)throws Exception{

        return pd.obterListaPedidoCardapioSimples(cli);

    }
    
    
    
    
    public ArrayList<PedidoCardapio> obterListaPedidoCardapio(Pedido ped)throws Exception{
        ArrayList<PedidoCardapio> lista; 
        lista = pd.obterListaPedidoCardapio(null, ped);
        
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).setCliente(obterCliente(lista.get(i).getCliente().getPedido().getId(),lista.get(i).getCliente().getIdCliente()));
        }    
        
        return lista;
    }
    
 
    public Cliente obterCliente(String idped, int idcliente)throws Exception{
        Cliente cli;
        cli =  pd.obterClienteDAO(idped, idcliente);
        //Comentado por teste ...
        //cli.setListaItens(obterListaPedidoCardapio(cli));
        return cli;
    }
    
    public Cliente obterCliente(String pedido, String nome)throws Exception{
        Cliente cli;
        cli =  pd.obterClienteNomeDAO(pedido, nome);
        cli.setListaItens(obterListaPedidoCardapio(cli));
        return cli;
    }    
}
