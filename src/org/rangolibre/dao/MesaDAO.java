/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.rangolibre.model.Mesa;
import org.rangolibre.model.MesaStatus;
/**
 *
 * @author aleaguado
 */
public class MesaDAO  extends GenericDAO {
    
    public MesaDAO(){
        super();
    }
    
    public Mesa criarDAO(Mesa me) throws Exception {
        
        //Executamos a procedure criar_usuario
        CallableStatement cs = conn.prepareCall("{call criar_mesa(?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idmesap", me.getId());
        cs.setInt("capacidadep", me.getCapacidade());
        cs.setString("idstatusp", "L");
        cs.setString("ativop", "Y");        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idmesar", Types.VARCHAR);

        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getString("idmesar").equals(me.getId()))
            return me;
        else
            throw new Exception("Mesa não criada");
        
    }    
    
    public Mesa atualizarDAO(Mesa me) throws Exception {
        
        //Executamos a procedure criar_usuario
        CallableStatement cs = conn.prepareCall("{call atualizar_mesa(?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idmesap", me.getId());
        cs.setInt("capacidadep", me.getCapacidade());
        cs.setString("idstatusp", "L");
        cs.setString("ativop", "Y");        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idmesar", Types.VARCHAR);

        cs.execute();
        
        //Checamos se foi realmente atualizado no banco o usuario
        if (cs.getString("idmesar").equals(me.getId()))
            return me;
        else
            throw new Exception("Mesa não atualizada");
        
    }       
    
    
    //Retorna as mesas cadastradas ... 
    public ArrayList<MesaStatus> obterMesasGeralDAO() throws Exception{ //Retorna as mesas Cadastradas
        
        int i=0;
        ArrayList<MesaStatus> m = new ArrayList<MesaStatus>();
        String sql = "select * from mesas_status";
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                MesaStatus me = new MesaStatus(rs.getString("IDMESA"),rs.getInt("CAPACIDADE"), rs.getString("STATUS"), rs.getString("PEDIDO") );
                m.add(i,me);
                i++;
            }           
            if (i == 0) {
                throw new Exception("Não existem mesas cadastradas");
            }            
            return m;           
    }

    //Retorna as mesas cadastradas ... 
    public ArrayList<MesaStatus> obterOcupacao() throws Exception{ //Retorna as mesas Cadastradas
        
        int i=0;
        ArrayList<MesaStatus> m = new ArrayList<MesaStatus>();
        String sql = "select * from mesas_status where STATUS = 'O'";
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                MesaStatus me = new MesaStatus(rs.getString("IDMESA"),rs.getInt("CAPACIDADE"), rs.getString("STATUS"), rs.getString("PEDIDO") );
                m.add(i,me);
                i++;
            }           
            if (i == 0) {
                throw new Exception("Não existem mesas cadastradas");
            }            
            return m;           
    }    
    
    
    public ArrayList<Mesa> obterMesasDAO(String status) throws Exception{ 
        int i=0;
            ArrayList<Mesa> m = new ArrayList<Mesa>();
            String sql;
            if (status.equals("L") || status.equals("O") || status.equals("I")) {
                sql = "select * from mesa where idstatus = '" + status.trim() + "' and ativo = 'Y'";
            } else {
                sql = "select * from mesa where ativo = 'Y'";
            }              
                
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                Mesa me = new Mesa(rs.getString("idmesa"),rs.getInt("capacidade"), rs.getString("idstatus"), rs.getString("ativo"));
                m.add(i,me);
                i++;
            }           
            if (i == 0) {
                throw new Exception("Não existem mesas com esse status");
            }            
            return m;                   
    }
    
    public Mesa ObterMesa(String id)  throws Exception {
        boolean retorno = false;
        String sql = "select * from mesa where idmesa = '" + id + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            if(rs.next()){
                retorno = true;
                Mesa me = new Mesa(rs.getString("idmesa"),rs.getInt("capacidade"), rs.getString("idstatus"),  rs.getString("ativo"));
                return me;
            }      
            if (!retorno) {
                throw new Exception("Mesa não existe!");
            }
            return null;
    }
   
    public boolean mesaExisteDAO(Mesa m)  throws Exception {
        String sql = "select * from mesa where idmesa = '" + m.getId()+ "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }
 
}
