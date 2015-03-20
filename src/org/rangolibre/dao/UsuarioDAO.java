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
import org.rangolibre.model.Perfil;
import org.rangolibre.model.Permissao;
import org.rangolibre.model.Usuario;

/**
 *
 * @author aleaguado
 */
public class UsuarioDAO extends GenericDAO{
     
    public Usuario criarDAO(Usuario usr) throws Exception {
        
        //Executamos a procedure criar_usuario
        CallableStatement cs = conn.prepareCall("{call criar_usuario(?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idusuariop", usr.getId().trim());
        cs.setString("nomep", usr.getNome().trim());
        cs.setString("senhap", usr.getSenha().trim());
        cs.setInt("idperfilp", usr.getPerfil().getId());        
        //Registramos o parametro de retorno
        cs.registerOutParameter("idusuarior", Types.VARCHAR);

        boolean hadResults = cs.execute();
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getString("idusuarior").equals(usr.getId().trim()))
            return usr;
        else
            throw new Exception("Usuário não criado");
        
    }
    
    //Check se usuario existe ja no banco de dados
    public boolean usuarioExisteDAO(Usuario usr)  throws Exception {
        String sql = "select * from usuario where idusuario = '" + usr.getId().trim() + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
            return rs.next();
    }
    
    //Retorna uma lista com todos os perfis cadastrados no banco
    public ArrayList<Perfil> obterListaPerfilDAO()  throws Exception  {
            ArrayList<Perfil> perf = new ArrayList<Perfil>();
            String sql = "select * from perfil";
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                perf.add(new Perfil(rs.getInt("idperfil"),rs.getString("descricao"), null));   
            }
            return perf;        
    }
    
    //Retorna as permissoes existentes para determinado perfil
    public ArrayList<Permissao> obterListaPermissaoDAO(int idperfil)  throws Exception  {
            ArrayList<Permissao> perm = new ArrayList<Permissao>();
            String sql = "select * from permissoes perm, perfilpermi perf";
            sql = sql + " where perm.idpermissao = perf.idpermissao AND";
            sql = sql + " perf.idperfil = " + idperfil;

            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                perm.add(new Permissao(rs.getString("idpermissao"),rs.getString("descricao")));   
            }
            return perm;        
    }
    
        //Retorna uma lista com todos os usuarios cadastrados no banco
    public ArrayList<Usuario> obterListaUsuarioDAO()  throws Exception  {
            ArrayList<Usuario> listaUsr = new ArrayList<Usuario>();
            String sql = "select * from usuario where ativo = 'Y'";
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            while(rs.next()){
                listaUsr.add(new Usuario(rs.getString("idusuario"),rs.getString("nome"), rs.getString("senha"), null, rs.getString("ativo")));   
            }
            return listaUsr;        
    }
    
    public Perfil obterPerfilDAO(String idusuario)   throws Exception  {
            Perfil p;
            String sql = "select * from usuario u, perfil p";
            sql = sql + " where p.idperfil = u.idperfil and";
            sql = sql + " u.idusuario = '" + idusuario.trim() + "'";

            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            if(rs.next()){
                p = new Perfil(rs.getInt("idperfil"),rs.getString("descricao"), null);   
            } else
            {
                throw new Exception("Usuario sem perfil associado");
            }    
                
            return p;   
    
    }   
    
    public Usuario obterUsuarioDAO(String idusuario)   throws Exception {
        Usuario usr;
        String sql = "select * from usuario where idusuario = '" + idusuario.trim() + "'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
        if(rs.next()){
                usr = new Usuario(rs.getString("idusuario"),rs.getString("nome"), rs.getString("senha"), null, rs.getString("ativo"));   
            } else
            {
                throw new Exception("Usuario não existe!");
            }   
        return usr;
    }

    public Usuario loginDAO(String idusuario, String pass)   throws Exception {
        Usuario usr;
        String sql = "select * from usuario where idusuario = '" + idusuario.trim() + "'";
        sql = sql + " and senha = md5('" + pass.trim() + "') and ativo = 'Y'";
        this.prepareStmte(sql);
        ResultSet rs = this.stmte.executeQuery();
        if(rs.next()){
                usr = new Usuario(rs.getString("idusuario"),rs.getString("nome"), rs.getString("senha"), null,rs.getString("ativo") );   
            } else
            {
                throw new Exception("Usuário/Senha inválidos!!!");
            }   
        return usr;
    }
    
       public Usuario alterarDAO(Usuario usr) throws Exception {
        
        //Executamos a procedure criar_usuario
        CallableStatement cs = conn.prepareCall("{call atualizar_usuario(?, ?, ?, ?, ?, ?)}");
        //Preparamos os parametros
        cs.setString("idusuariop", usr.getId().trim());
        cs.setString("nomep", usr.getNome().trim());
        cs.setString("senhap", usr.getSenha().trim());
        cs.setInt("idperfilp", usr.getPerfil().getId());    
        cs.setString("ativop", usr.getAtivo()); 
        //Registramos o parametro de retorno
        cs.registerOutParameter("idusuarior", Types.VARCHAR);

        //Na duvida aqui ...
        cs.execute();
        
        //Checamos se foi realmente criado no banco o usuario
        if (cs.getString("idusuarior").equals(usr.getId().trim()))
            return usr;
        else
            throw new Exception("Não foi possível atualizar as informações");
        
    } 
    
}
