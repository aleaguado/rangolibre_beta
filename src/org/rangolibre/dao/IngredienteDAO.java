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
import org.rangolibre.model.CatIngre;
import org.rangolibre.model.Fornecedor;
import org.rangolibre.model.Ingrediente;

/**
 *
 * @author aleaguado
 */
public class IngredienteDAO  extends GenericDAO{
 
    //Retorna uma lista de categorias de ingredientes
    public ArrayList<CatIngre> obterListaCategoria()  throws Exception {
            ArrayList<CatIngre> listaCat = new ArrayList<CatIngre>();
            String sql = "select * from cat_ingre";
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                listaCat.add(new CatIngre(rs.getInt("idcat"),rs.getString("descricao")));   
            }
            return listaCat;  
    }

    public ArrayList<Ingrediente> obterListaIngredienteDAO(int idCat)  throws Exception {   
            ArrayList<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();

            String sql = "select * from ingrediente";       
            if ( idCat != 99999 )
                sql = sql + " where idcat = " + idCat;
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
               listaIngrediente.add(new Ingrediente(rs.getInt("idingre"), rs.getString("descricao"), rs.getInt("qty"), null, null, rs.getString("ativo"), null));   
            }
            return listaIngrediente;          
    
    }

    public ArrayList<Fornecedor> obterListaFornecedoresDAO(int idingre)  throws Exception {   
             ArrayList<Fornecedor> listaForn = new ArrayList<Fornecedor>();
            String sql = "select * from fornecedor where ";
            sql = sql + " idfornecedor in(select idfornecedor from ingreforne";
            sql = sql + " where idingre = " + idingre +  ")";

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
    
    //Retorna a categoria de um ingrediente
    public CatIngre obterCategoriaDAO(int idIngre)  throws Exception {
            CatIngre ci;
            String sql = "select idcat, descricao from cat_ingre";
            sql = sql + " where idcat = (select idcat from ingrediente";
            sql = sql + " where idingre = " + idIngre + ")";
            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            if(rs.next()){
                ci = new CatIngre(rs.getInt("idcat"),rs.getString("descricao"));   
            } else
            {
                throw new Exception("Item sem categoria!");
            } 

            return ci;
    }
    
        public Ingrediente criarDAO(Ingrediente ingre) throws Exception {
        
            //Executamos a procedure criar_usuario
            CallableStatement cs = conn.prepareCall("{call inserir_ingre(?, ?, ?, ?, ?, ?)}");
            //Preparamos os parametros
            cs.setString("descricaop", ingre.getDesc().trim());
            cs.setInt("qtyp", ingre.getQty());
            cs.setInt("catp", ingre.getCat().getId());
            cs.setString("usuariop", ingre.getUsr().getId());   
            cs.setInt("fornp", ingre.getListaFornecedor().get(0).getId()); 
            //Registramos o parametro de retorno
            cs.registerOutParameter("idingrer", Types.INTEGER);

            //Na duvida aqui ...
            cs.execute();

            //Checamos se foi realmente criado no banco o usuario
            if (cs.getInt("idingrer") > 0) {
                ingre.setId(cs.getInt("idingrer"));
                return ingre;
            } else {
                throw new Exception("Ingrediente não criado");
            }
    }
    
        public Ingrediente alterarDAO(Ingrediente ingre) throws Exception {
        
        //Executamos a procedure criar_usuario
        CallableStatement cs = conn.prepareCall("{call atualizar_ingre(?, ?, ?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setInt("idingrep", ingre.getId());
        cs.setString("descricaop", ingre.getDesc().trim());
        cs.setInt("qtyp", ingre.getQty());
        cs.setInt("catp", ingre.getCat().getId());
        cs.setString("usuariop", ingre.getUsr().getId()); 
        cs.setString("ativop", ingre.getAtivo());  
        //Registramos o parametro de retorno
        cs.registerOutParameter("idingrer", Types.INTEGER);

        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getInt("idingrer") > 0) {
            return ingre;
        } else {
            throw new Exception("Ingrediente não atualizado");
        }
    }
        
       //Check se ingrediente existe ja no banco de dados
    public boolean ingredienteExisteDAO(Ingrediente ingre)  throws Exception {
        String sql = "select * from ingrediente where descricao = '" + ingre.getDesc().trim() + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }     

   //Check se categoria existe ja no banco de dados
    public boolean categoriaExisteDAO(CatIngre ci)  throws Exception {
        String sql = "select * from cat_ingre where descricao = '" + ci.getDesc().trim() + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }      
    //Criar categoria
        public CatIngre criarCategoriaDAO(CatIngre ci) throws Exception {
        
            //Executamos a procedure criar_catingre
            CallableStatement cs = conn.prepareCall("{call criar_catingre(?, ?)}");
            //Preparamos os parametros
            cs.setString("descricaop", ci.getDesc().trim());
            //Registramos o parametro de retorno
            cs.registerOutParameter("idcatr", Types.INTEGER);

            //Na duvida aqui ...
            cs.execute();

            //Checamos se foi realmente criado no banco o usuario
            if (cs.getInt("idcatr") > 0) {
                ci.setId(cs.getInt("idcatr"));
                return ci;
            } else {
                throw new Exception("Categoria não criado");
            }
    }    
    
}
