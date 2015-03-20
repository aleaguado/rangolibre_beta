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
import javax.swing.JOptionPane;
import org.rangolibre.control.ControleUsuario;
import org.rangolibre.model.Ingrediente;
import org.rangolibre.model.IngredienteCardapio;
import org.rangolibre.model.ItemCard;

/**
 *
 * @author aleaguado
 */
public class CardapioDAO extends GenericDAO{
    
    public ItemCard criarPadraoDAO(ItemCard ic )throws Exception{
 
        
        
        //Procedure
        CallableStatement cs = conn.prepareCall("{call criar_cardapio_padrao(?, ?, ?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("descricaop", ic.getDescCard().trim());
        cs.setDouble("precop", ic.getpreco());
        cs.setString("usuariop", ic.getUsr().getId());
        cs.setString("tipop", ic.getTipo().trim());   
        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idcardapior", Types.INTEGER);
        cs.registerOutParameter("idcatcarr", Types.INTEGER);
        
        //Na duvida aqui ...
        cs.execute();
 
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getInt("idcardapior") > 0) {
            ic.setIdCard(cs.getInt("idcardapior"));
            ic.setIdCat(cs.getInt("idcatcarr"));
            return ic;
        }
        else {
            throw new Exception("Usuário não criado");    
        }
    }

    public ItemCard criarDerivadoDAO(ItemCard car)throws Exception{

        //Procedure
        java.sql.CallableStatement cs = conn.prepareCall("{call criar_cardapio_derivado(?, ?, ?, ?, ?, ?)}");
         //Preparamos os parametros
        cs.setString("descricaop", car.getDescCard());
        cs.setDouble("precop", car.getpreco());
        cs.setString("usuariop", car.getUsr().getId());
        cs.setInt("idcatcarp", car.getIdCat()); 
        cs.setString("pontocarne", car.getPonto());
        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idcardapior", Types.INTEGER);
        
        //Na duvida aqui ...
        cs.execute();

        
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getInt("idcardapior") > 0) {
            car.setIdCard(cs.getInt("idcardapior"));
            return car;
        }
        else {
            throw new Exception("Problemas ao criar novo item no Banco de Dados");    
        }
    }    
    
 
    public boolean adicionarIngredienteCardapioDAO(int idingre, int idcard, int qty)throws Exception{
       
        //Procedure
        CallableStatement cs = conn.prepareCall("{call adicionar_ingre_card(?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setInt("idingrep", idingre);
        cs.setInt("idcardp", idcard);
        cs.setInt("qtyp", qty); 
        
        cs.registerOutParameter("idingrer", Types.INTEGER);
        
        cs.execute();
        
        if (cs.getInt("idingrer") == idingre)
            return true;
        else
            throw new Exception("Um Ingrediente não adicionado ao cardápio!");

    }    

    public boolean removerIngredienteCardapioDAO(int idingre, int idcard, int qty)throws Exception{
       
        CallableStatement cs = conn.prepareCall("{call remover_ingre_card(?, ?, ?, ?)}");
        cs.setInt("idingrep", idingre);
        cs.setInt("idcardp", idcard);
        cs.setInt("qtyp", qty); 
        
        cs.registerOutParameter("idingrer", Types.INTEGER);
        
        cs.execute();
        
        if (cs.getInt("idingrer") != idingre)
            return true;
        else
            throw new Exception("Ingrediente não removido!");

    }     
    
    public void atualizarPrecoDAO(ItemCard ic)throws Exception{
       
        //Procedure
        CallableStatement cs = conn.prepareCall("{call atualizar_preco(?, ?, ?)}");
        cs.setInt("idcardapiop", ic.getIdCard());
        cs.setDouble("precop", ic.getpreco());
        
        cs.registerOutParameter("precor", Types.DOUBLE);
        
        cs.execute();
        
        if (cs.getDouble("precor") != ic.getpreco()) 
            throw new Exception("Preço não atualizado!");

    }      

        public void atualizarCardapioDAO(ItemCard ic)throws Exception{
       
        //Procedure
        CallableStatement cs = conn.prepareCall("{call atualizar_cardapio(?, ?, ?, ?, ?, ?)}");
        cs.setInt("idcardapiop", ic.getIdCard());
        cs.setString("descricaop", ic.getDescCard());
        cs.setString("idusuariop", ic.getUsr().getId());
        cs.setString("pontoCarnep", ic.getPonto());
        cs.setString("ativop", ic.getAtivo());

        cs.registerOutParameter("idcardapior", Types.INTEGER); 
        
        cs.execute();
        
        if (cs.getInt("idcardapior") != ic.getIdCard()) 
            throw new Exception("Problemas ao atualizar Item de Cardapio");

    }  

    public boolean verificarSemelhancaDAO(ItemCard car) throws Exception {
        String sql = "select * from cardapio where descricao = '" + car.getDescCard() + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }
    
    
    
//BUSCAS
    
    public ArrayList<String> obterTiposCardapioDAO()   throws Exception  {
        ArrayList<String> arr = new  ArrayList<>();
        String sql = "select distinct tipo from cat_car";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
        while(rs.next()){
            arr.add( rs.getString("tipo"));
        }
        return arr;       
    }
    
        public ArrayList<ItemCard> obterCardapioDAO(String TipoBusca, String tipoCardapio, ItemCard itemPadrao)  throws Exception  {
            ArrayList<ItemCard> cardapio = new ArrayList<ItemCard>();
            
            //São possíveis as seguintes buscas:
                   //BCT - Busca completa por tipo ( Se tipo for null são todos )
                   //BPT - Busca por tipo só de itens padrão ( Se tipo for null são todos )
                   //BCD - Busca completa de cardapio derivado
                String sql = "select  c.idcardapio as 'idcardapio', c.descricao as 'desccardapio', c.idusuario as 'usuario',";
                sql = sql + " c.padrao as 'padrao', c.pontoCarne as 'ponto', cc.idcatcar as 'idcatcar',";
                sql = sql + " c.ativo as 'ativo', cc.descricao as 'desccat', cc.tipo as 'tipo', p.preco as 'preco'";
                sql = sql + " from cardapio c, cat_car cc, cardpreco p where";
                sql = sql + " c.idcatcar = cc.idcatcar"; 
                sql = sql + " and c.idcardapio = p.idcardapio and idstatus = 'V'"; 
 
            if (TipoBusca.equals("BCT")) {
                if (tipoCardapio != null ) {
                   sql = sql + " and cc.tipo = '" + tipoCardapio.trim() + "'";  
                }
            }
            if (TipoBusca.equals("BPT")) {
                sql = sql + " and c.padrao = 1"; 
                
                if (tipoCardapio != null ) {
                   sql = sql + " and cc.tipo = '" + tipoCardapio.trim() + "'";  
                }               
            }      
            if (TipoBusca.equals("BCD")) {
                sql = sql + " and c.padrao = 0 and cc.idcatcar = " + itemPadrao.getIdCat(); 
            } 

            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                cardapio.add(new ItemCard(
                        rs.getInt("idcardapio"),
                        rs.getString("desccardapio"),
                        rs.getDouble("preco"),
                        null,
                        rs.getString("ativo"),
                        rs.getBoolean("padrao"),
                        null,
                        rs.getInt("idcatcar"),
                        rs.getString("desccat"),
                        rs.getString("tipo"),
                        rs.getString("ponto")
                ));   
            }
            return cardapio;   
        }

//Retorna um item de cardapio especifico porem ainda incompleto
        public ItemCard obterItemCardapioDAO(int idItemCard)  throws Exception  {
           ItemCard cardapio;
           ControleUsuario cu = new ControleUsuario();
            
            //São possíveis as seguintes buscas:
                   //BCT - Busca completa por tipo ( Se tipo for null são todos )
                   //BPT - Busca por tipo só de itens padrão ( Se tipo for null são todos )
                   //BCD - Busca completa de cardapio derivado
                String sql = "select  c.idcardapio as 'idcardapio', c.descricao as 'desccardapio', c.idusuario as 'usuario',";
                sql = sql + " c.padrao as 'padrao', c.pontoCarne as 'ponto', cc.idcatcar as 'idcatcar',";
                sql = sql + " c.ativo as 'ativo', cc.descricao as 'desccat', cc.tipo as 'tipo', p.preco as 'preco'";
                sql = sql + " from cardapio c, cat_car cc, cardpreco p where";
                sql = sql + " c.idcatcar = cc.idcatcar"; 
                sql = sql + " and c.idcardapio = p.idcardapio and idstatus = 'V'"; 
                sql = sql + " and c.idcardapio = " +  idItemCard;
 

            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            if (rs.next()){
                cardapio = new ItemCard(
                        rs.getInt("idcardapio"),
                        rs.getString("desccardapio"),
                        rs.getDouble("preco"),
                        cu.obterUsuario(rs.getString("usuario")),
                        rs.getString("ativo"),
                        rs.getBoolean("padrao"),
                        null,
                        rs.getInt("idcatcar"),
                        rs.getString("desccat"),
                        rs.getString("tipo"),
                        rs.getString("ponto")
                );   
            } else {            
             throw new Exception("Item de Cardápio inexistente!!!");
            }
   
            return cardapio;   
        }        
        
        public ArrayList<IngredienteCardapio> obterListaIngredienteDAO(int idItemCard)   throws Exception  {
            ArrayList<IngredienteCardapio> listaIngrediente = new ArrayList<IngredienteCardapio>();

            String sql = "select ic.qty as 'qtyConsumida', i.idingre as 'idingre', i.descricao as 'descricao',";     
            sql = sql + " i.qty as 'qtyEstoque', i.idcat as 'idcat', i.idusuario as 'usuario', i.ativo as 'ativo'";
            sql = sql + " from ingrecar ic, ingrediente i where ic.idingre = i.idingre";        
            sql = sql + " and ic.idcardapio = " + idItemCard;

            
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                Ingrediente in = new Ingrediente(rs.getInt("idingre"),rs.getString("descricao"),rs.getInt("qtyEstoque"), null, null,rs.getString("ativo"), null);
                listaIngrediente.add(new IngredienteCardapio(rs.getInt("qtyConsumida"), in));   
            }
            return listaIngrediente;          
    
    }
        
        
//Retorna um item de cardapio especifico porem ainda incompleto
        public ItemCard obterItemPadraoDAO(int catCar)  throws Exception  {
           ItemCard cardapio;
           //ControleUsuario cu = new ControleUsuario();
            
            //São possíveis as seguintes buscas:
                   //BCT - Busca completa por tipo ( Se tipo for null são todos )
                   //BPT - Busca por tipo só de itens padrão ( Se tipo for null são todos )
                   //BCD - Busca completa de cardapio derivado
                String sql = "select  c.idcardapio as 'idcardapio', c.descricao as 'desccardapio', c.idusuario as 'usuario',";
                sql = sql + " c.padrao as 'padrao', c.pontoCarne as 'ponto', cc.idcatcar as 'idcatcar',";
                sql = sql + " c.ativo as 'ativo', cc.descricao as 'desccat', cc.tipo as 'tipo', p.preco as 'preco'";
                sql = sql + " from cardapio c, cat_car cc, cardpreco p where";
                sql = sql + " c.idcatcar = cc.idcatcar"; 
                sql = sql + " and c.idcardapio = p.idcardapio and p.idstatus = 'V'"; 
                sql = sql + " and cc.idcatcar = " + catCar + " and c.padrao = 1";
 

            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            if (rs.next()){
                cardapio = new ItemCard(
                        rs.getInt("idcardapio"),
                        rs.getString("desccardapio"),
                        rs.getDouble("preco"),
                        null,
                        rs.getString("ativo"),
                        rs.getBoolean("padrao"),
                        null,
                        rs.getInt("idcatcar"),
                        rs.getString("desccat"),
                        rs.getString("tipo"),
                        rs.getString("ponto")
                );   
            } else {            
             throw new Exception("Item de Cardápio inexistente!!!");
            }
   
            return cardapio;   
        }                
        
        
        
        
        
        
        
                
        }