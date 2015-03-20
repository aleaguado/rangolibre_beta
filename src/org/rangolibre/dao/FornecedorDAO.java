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
import org.rangolibre.model.Fornecedor;

/**
 *
 * @author aleaguado
 */
public class FornecedorDAO  extends GenericDAO{
    
        //Check se usuario existe ja no banco de dados
    public boolean fornecedorExisteDAO(Fornecedor forn)  throws Exception {
        String sql = "select * from fornecedor where cnpj = " + forn.getCnpj();
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }
    
    public Fornecedor criarDAO(Fornecedor forn) throws Exception {
        
        //Executamos a procedure inserir_fornecedor
        CallableStatement cs = conn.prepareCall("{call inserir_fornecedor(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("nomep", forn.getNome().trim());
        cs.setString("cnpjp", forn.getCnpj().trim());
        cs.setString("enderecop", forn.getEndereco().trim());      
        cs.setString("cidadep", forn.getCidade().trim());
        cs.setString("estadop", forn.getEstado().trim());
        cs.setString("paisp", forn.getPais().trim());
        cs.setString("fonep", forn.getTelefone().trim());
        cs.setString("usuariop", forn.getUsuario().getId());       
        //Registramos o parametro de retorno
        cs.registerOutParameter("idfornecedorr", Types.INTEGER);

        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getInt("idfornecedorr") > 0) {
            forn.setId(cs.getInt("idfornecedorr"));
            return forn;
        } else {
            throw new Exception("Fornecedor não criado");
        }
    }
    
    public Fornecedor alterarDAO(Fornecedor forn) throws Exception {
        
        //Executamos a procedure inserir_fornecedor
        CallableStatement cs = conn.prepareCall("{call atualizar_fornecedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setInt("idfornecedorp", forn.getId());
        cs.setString("nomep", forn.getNome().trim());
        cs.setString("cnpjp", forn.getCnpj().trim());
        cs.setString("enderecop", forn.getEndereco().trim());      
        cs.setString("cidadep", forn.getCidade().trim());
        cs.setString("estadop", forn.getEstado().trim());
        cs.setString("paisp", forn.getPais().trim());
        cs.setString("fonep", forn.getTelefone().trim());
        cs.setString("usuariop", forn.getUsuario().getId()); 
        cs.setString("ativop", forn.ativo().trim()); 
        //Registramos o parametro de retorno
        cs.registerOutParameter("idfornecedorr", Types.INTEGER);

        cs.execute();
        
        //Checamos se foi realmente atualizado no banco o usuario
        if (cs.getInt("idfornecedorr") > 0) {
            return forn;
        } else {
            throw new Exception("Fornecedor não atualizado");
        }
    }    
    
    //Retorna uma lista com todos os fornecedores cadastrados no banco
    public ArrayList<Fornecedor> obterListaFornecedorDAO()  throws Exception  {
            ArrayList<Fornecedor> listaForn = new ArrayList<Fornecedor>();
            String sql = "select * from fornecedor";
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                listaForn.add(new Fornecedor(
                        rs.getInt("idfornecedor"),
                        rs.getString("nome"), 
                        rs.getString("cnpj"),
                        rs.getString("endereco"), 
                        rs.getString("cidade"), 
                        rs.getString("estado"), 
                        rs.getString("pais"), 
                        rs.getString("telefone"), 
                        null, 
                        rs.getString("ativo")));   
            }
            return listaForn;        
    }    
    
}
