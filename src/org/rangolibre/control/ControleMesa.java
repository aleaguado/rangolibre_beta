/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.control;

import java.util.ArrayList;
import org.rangolibre.dao.MesaDAO;
import org.rangolibre.model.Mesa;
import org.rangolibre.model.MesaStatus;

/**
 *
 * @author aleaguado
 */
public class ControleMesa {
     MesaDAO m = new MesaDAO();
    
    public ArrayList<MesaStatus> obterMesasGeral() throws Exception{
       
        return m.obterMesasGeralDAO(); 
    }
    
    public ArrayList<Mesa> obterMesasLivres() throws Exception{

        return m.obterMesasDAO("L");
    }

    public ArrayList<Mesa> obterMesas() throws Exception{

        return m.obterMesasDAO("");
    }
    
    public ArrayList<MesaStatus> obterOcupacao() throws Exception{

        return m.obterOcupacao();
    }
    
    public Mesa ObterMesa(String id) throws Exception{

        return m.ObterMesa(id);
    }
    
    public Mesa criar(Mesa me) throws Exception{
        if  (!m.mesaExisteDAO(me))
            return m.criarDAO(me);
        else
            throw new Exception("Mesa já existe!");
    }
    
    public Mesa atualizar(Mesa me) throws Exception{
        return m.atualizarDAO(me);
    }
    
    public boolean mesaVazia(Mesa me) throws Exception{
        return m.ObterMesa(me.getId()).getStatus().equals("L");
    }
    
}
