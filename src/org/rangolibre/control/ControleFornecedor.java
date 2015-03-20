/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.FornecedorDAO;
import org.rangolibre.model.Fornecedor;

/**
 *
 * @author aleaguado
 */
public class ControleFornecedor {
    FornecedorDAO foDAO = new FornecedorDAO();
    
    public Fornecedor criar(Fornecedor forn) throws Exception { //Criação de fornecedor no banco
        if (this.fornecedorExiste(forn) == false) { // Verifica se fornecedor já existe
            return foDAO.criarDAO(forn);          //Caso não, executa a criação
        } else {
            throw new Exception("Usuario ja existe!"); //Caso sim, retorna exceção
        }
    }
    
    public boolean fornecedorExiste(Fornecedor forn) throws Exception { //Usuario existe?
        return foDAO.fornecedorExisteDAO(forn); //fala para o DAO perguntar no Banco!!!
    }
    
    public Fornecedor alterar(Fornecedor forn) throws Exception {
        return foDAO.alterarDAO(forn);
    }
    
    public ArrayList<Fornecedor> obterListaFornecedor()throws Exception {
        return foDAO.obterListaFornecedorDAO();
    }

    
}
