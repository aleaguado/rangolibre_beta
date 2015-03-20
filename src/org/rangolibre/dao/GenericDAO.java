/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rangolibre.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.rangolibre.dao.jdbc.ConnectionFactory;

/**
 *
 * @author Rafael Wendel Pinheiro
 */
public class GenericDAO {
    
    protected Connection conn;
    protected PreparedStatement stmte;
    
    public GenericDAO(){
        this.conn = new ConnectionFactory().getConnection();
    }
    
    protected void prepareStmte(String sql) throws SQLException{
        try{
            this.stmte = this.conn.prepareStatement(sql);
            this.stmte.closeOnCompletion();
        }
        catch(SQLException e){
            throw new SQLException("Erro ao preparar estado", e);
        }
    }
    
    protected void executarProcedure(String sql) throws SQLException{
 
        CallableStatement cs = null;
        cs = (CallableStatement) conn.prepareCall(sql);
        cs.execute();
    }
    
    protected void closeAll() throws SQLException{
        try{
            this.stmte.close();
            this.conn.close();
        }
        catch(SQLException e){
           throw new SQLException("Erro ao fechar conexao", e);
        }
    }    
}
