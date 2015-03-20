/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.IngredienteDAO;
import org.rangolibre.model.CatIngre;
import org.rangolibre.model.Fornecedor;
import org.rangolibre.model.Ingrediente;

/**
 *
 * @author aleaguado
 */
public class ControleIngrediente {
    
    IngredienteDAO iDao = new IngredienteDAO();
    
    //Retorna uma lista de categorias de ingredientes
    public ArrayList<CatIngre> obterListaCategoria()  throws Exception {
        return iDao.obterListaCategoria();
    }
    
    //Retorna uma lista de ingredientes de ingredientes
    public ArrayList<Ingrediente> obterListaIngrediente(int idCat)  throws Exception {

        ArrayList<Ingrediente> lista;
            
            lista = iDao.obterListaIngredienteDAO(idCat);
            //Carregamos aqui a categoria de cada ingrediente
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).setCat(obterCategoria(lista.get(i).getId()));
            }

            return lista;
    }
    
    public CatIngre obterCategoria(int idIngre)  throws Exception {
        return iDao.obterCategoriaDAO(idIngre);
    }

    public ArrayList<Fornecedor> obterFornecedor(int idIngre)  throws Exception {
        return iDao.obterListaFornecedoresDAO(idIngre);
    }    

    //CRIAÇÕES E ATUALIZAÇÕES
    public Ingrediente criar(Ingrediente ingre) throws Exception { 
        if (this.ingredienteExiste(ingre) == false) { //Ingrediente já existe?
            return iDao.criarDAO(ingre); // Não? Vamos criar!!!
        } else {
            throw new Exception("Ingrediente já existe!!!"); //Caso sim, retorna exceção
        }
    }
    
    public Ingrediente atualizar(Ingrediente ingre) throws Exception {
        return iDao.alterarDAO(ingre);    
    }  
    
    public boolean ingredienteExiste(Ingrediente ingre) throws Exception { //Ingrediente existe?
        return iDao.ingredienteExisteDAO(ingre); //fala para o DAO perguntar no Banco!!!
    }
    
    public CatIngre criarCategoria(CatIngre ci) throws Exception  {
       if (this.categoriaExiste(ci) == false) { //Categoria já existe?
            return iDao.criarCategoriaDAO(ci); // Não? Vamos criar!!!
        } else {
            throw new Exception("Categoria já existe!!!"); //Caso sim, retorna exceção
        }            
    }

    
    public boolean categoriaExiste(CatIngre ci) throws Exception { //Categoria existe?
        return iDao.categoriaExisteDAO(ci); //fala para o DAO perguntar no Banco!!!
    }    
    
}
