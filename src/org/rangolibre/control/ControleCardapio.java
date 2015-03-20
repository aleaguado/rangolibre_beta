/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import java.util.Objects;
import org.rangolibre.dao.CardapioDAO;
import org.rangolibre.model.IngredienteCardapio;
import org.rangolibre.model.ItemCard;

/**
 *
 * @author aleaguado
 */
public class ControleCardapio {
    
    CardapioDAO cDao = new CardapioDAO();
    
    public ItemCard criarPadrao(ItemCard car) throws Exception { 
        if (!verificarSemelhanca(car)) {
            ItemCard ic;
            ic = cDao.criarPadraoDAO(car); //Cria o ic que já esta completo no banco de dados
            
            //grava no banco os ingredientes deste item de cardapio
            for (int i = 0; i < ic.getIngre().size(); i++) {
                cDao.adicionarIngredienteCardapioDAO(ic.getIngre().get(i).getIngrediente().getId(), ic.getIdCard(), ic.getIngre().get(i).getQty());
            }            
            return ic;            
        } else {
            throw new Exception("Este item do cardápio já existe!");
        }    
    }
    
    public ItemCard criarDerivado(ItemCard car, ItemCard padrao) throws Exception { 
        ItemCard ic = car;       
       // ic.setDescCard(comporDescricaoDerivada(car, padrao));
        ic =  cDao.criarDerivadoDAO(ic);
        for (int i = 0; i < ic.getIngre().size(); i++) {
            cDao.adicionarIngredienteCardapioDAO(ic.getIngre().get(i).getIngrediente().getId(), ic.getIdCard(), ic.getIngre().get(i).getQty());
        }     
        return ic;
        
    }
    
    public String comporDescricaoDerivada(ItemCard car, ItemCard padrao) {
        boolean encontrei = false;
        String descricao = padrao.getDescCard();
        for (int i = 0; i < car.getIngre().size(); i++) {
            for (int j = 0; j < padrao.getIngre().size(); j++) {
                if ( car.getIngre().get(i).getIngrediente().getDesc().equalsIgnoreCase(padrao.getIngre().get(j).getIngrediente().getDesc() )) {
                    encontrei = true;
                }
  
            }
            if (encontrei == false) {
                descricao = descricao + " C/ " + car.getIngre().get(i).getIngrediente().getDesc().substring(0, 5) + ";";
            }                       
        }
        for (int i = 0; i < padrao.getIngre().size(); i++) {
            for (int j = 0; j < car.getIngre().size(); j++) {
                if ( padrao.getIngre().get(i).getIngrediente().getDesc().equals(car.getIngre().get(j).getIngrediente() )) {
                    encontrei = true;
                }
  
            }
            if (encontrei == false) {
                descricao = descricao + "[ S/ " + padrao.getIngre().get(i).getIngrediente().getDesc() + "] ";
            }                       
        }        
        
        if (car.getTipo() == "PRATOCARNE") {
            descricao = descricao + "[ PONTO DA CARNE: " + car.getPonto() + "] ";
        }
        
        return descricao;
    
    }
     
    
    public boolean verificarSemelhanca(ItemCard car) throws Exception {
        return cDao.verificarSemelhancaDAO(car);
    }
    
    public ItemCard atualizarItemCardapio(ItemCard car) throws Exception{
        boolean encontrei = false;
        ItemCard icAntigo = obterItemCardapio(car.getIdCard());
        cDao.atualizarCardapioDAO(car);
        
        
        // Se houve mudancas atualizamos o preco ...
        if (!Objects.equals(icAntigo.getpreco(), car.getpreco()))
            cDao.atualizarPrecoDAO(car);
        
        //Vamos atualizar a relação entre ingredientes e cardapio
        for (int i = 0; i < car.getIngre().size(); i++) {
            for (int j = 0; j < icAntigo.getIngre().size(); j++) {
                if ( car.getIngre().get(i).getIngrediente().getId() == icAntigo.getIngre().get(j).getIngrediente().getId() ) {
                    encontrei = true;
                }
  
            }
            if (encontrei = false) {
                cDao.adicionarIngredienteCardapioDAO(car.getIngre().get(i).getIngrediente().getId(), car.getIdCard(), car.getIngre().get(i).getIngrediente().getQty());
            }                       
        }
        for (int i = 0; i < icAntigo.getIngre().size(); i++) {
            for (int j = 0; j < car.getIngre().size(); j++) {
                if ( icAntigo.getIngre().get(i).getIngrediente().getId() == car.getIngre().get(j).getIngrediente().getId() ) {
                    encontrei = true;
                }
  
            }
            if (encontrei = false) {
                cDao.removerIngredienteCardapioDAO(icAntigo.getIngre().get(i).getIngrediente().getId(), car.getIdCard(), icAntigo.getIngre().get(i).getIngrediente().getQty());

            }                       
        }           
        return car;
    }
    

    public ArrayList<String> obterTiposCardapio()  throws Exception  {
        return cDao.obterTiposCardapioDAO();
        }

        //São possíveis as seguintes buscas:
                   //BCT - Busca completa por tipo ( Se tipo for null são todos )
                   //BPT - Busca por tipo só de itens padrão ( Se tipo for null são todos )
                   //BCD - Busca completa de itens derivados
    
    
    //Retorna p/ classe usuária uma lista de itens de cardapio completa de 
    //Determinado tipo ... se tipoC for null, retorna todos itens do cardapio
    public ArrayList<ItemCard> obterCardapioCompleto(String tipoC)  throws Exception  {
        return cDao.obterCardapioDAO("BCT", tipoC, null);
        }    
    //Retorna p/ classe usuária uma lista de itens de cardapio PADRAO de 
    //Determinado tipo ... se tipoC for null, retorna todos itens do cardapio
    public ArrayList<ItemCard> obterCardapioPadrao(String tipoC)  throws Exception  {
        return cDao.obterCardapioDAO("BPT", tipoC, null);
        }
    //Retorna p/ classe usuária uma lista de itens de cardapio derivados
    //De um outro padrão
    public ArrayList<ItemCard> obterCardapioDerivado(ItemCard iP)  throws Exception  {
        return cDao.obterCardapioDAO("BCD", null, iP);
        }        

    //Retorna um item de cardapio completo
    public ItemCard obterItemCardapio(int idCardapio)   throws Exception {
        ItemCard ic;
        ic = cDao.obterItemCardapioDAO(idCardapio);
        ic.setIngre(obterListaIngrediente(idCardapio));
        return ic;
    }

    //Retorna um item de cardapio completo
    public ItemCard obterItemPadrao(ItemCard derivado)   throws Exception {
        ItemCard ic;
        ic = cDao.obterItemPadraoDAO(derivado.getIdCat());
        ic.setIngre(obterListaIngrediente(ic.getIdCard()));
        return ic;
    }    
    
    
    public ArrayList<IngredienteCardapio> obterListaIngrediente(int idCardapio)  throws Exception{
        return cDao.obterListaIngredienteDAO(idCardapio);
    }

    
}