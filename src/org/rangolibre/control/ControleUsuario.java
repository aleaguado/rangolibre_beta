/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.UsuarioDAO;
import org.rangolibre.model.Perfil;
import org.rangolibre.model.Permissao;
import org.rangolibre.model.Usuario;

/**
 *
 * @author aleaguado
 */
public class ControleUsuario { //Classe para manipulação de usuarios perfis e permissoes
    
    UsuarioDAO uDao = new UsuarioDAO();
    
    public Usuario criar(Usuario usr) throws Exception { //Criação de Usuario no Banco
        if (this.usuarioExiste(usr) == false) { // Verifica se usuario já existe
            return uDao.criarDAO(usr);          //Caso não, executa a criação
        } else {
            throw new Exception("Usuario ja existe!"); //Caso sim, retorna exceção
        }
    }
    
    public boolean usuarioExiste(Usuario usr) throws Exception { //Usuario existe?
        return uDao.usuarioExisteDAO(usr); //fala para o DAO perguntar no Banco!!!
    }
    
    //Retorna p/ classe usuaria uma lista de perfis ...
    public ArrayList<Perfil> obterListaPerfil()  throws Exception {
        
         ArrayList<Perfil> perf;
        
            //Obtemos atraves do DAO uma lista de perfis ...
            perf =  uDao.obterListaPerfilDAO();
            
            //Carregamos aqui as permissoes de cada perfil
            for (int i = 0; i < perf.size(); i++) {
                perf.get(i).setPerm(obterPermissoes(perf.get(i).getId()));
            }
            
            return perf;            
    }
    
    //Recorre ao DAO para pegar as permissoes de um determinado perfil
    public ArrayList<Permissao> obterPermissoes(int idperfil) throws Exception {
        return uDao.obterListaPermissaoDAO(idperfil);
    }
    
    //Retorna a lista de todos usuarios do sistema, POREM, sem carregar perfil e 
    //Permissoes para economizar espaço! Depois por demanda a classe Usuaria chama ...
    public ArrayList<Usuario> obterListaUsuario()throws Exception {
        return uDao.obterListaUsuarioDAO();
    }
    // ... esta metodo aqui!!!
    public Perfil obterPerfil(String idusuario) throws Exception {
        Perfil p;
        p = uDao.obterPerfilDAO(idusuario);
        p.setPerm(obterPermissoes(p.getId()));
        return p;
    }
    //Método para ter um usuario completo a partir do idusuario
    public Usuario obterUsuario(String id)  throws Exception {
        Usuario usr = uDao.obterUsuarioDAO(id);
        //usr.setPerfil(obterPerfil(usr.getId()));
        return usr;
    }

    //Método de login
    public Usuario login(String id, String pass)  throws Exception {
        Usuario usr = uDao.loginDAO(id, pass);
        usr.setPerfil(obterPerfil(usr.getId()));
        return usr;
    }
    
    //Atualização de usuário
    public Usuario alterar(Usuario usr) throws Exception {        
        Usuario usr2 = uDao.alterarDAO(usr);
        usr2.setPerfil(obterPerfil(usr2.getId()));
        return usr2;
    }

    
}
