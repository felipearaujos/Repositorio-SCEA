/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dao;

import scea.core.impl.dao.AcessoDAO;
import scea.core.impl.dao.RelatoriosDAO;
import scea.core.impl.dao.TipoDeProdutoDAO;
import scea.core.interfaces.Factories.IEntidadeDAOFactory;
import scea.core.interfaces.IDAO;

/**
 *
 * @author Felipe
 */
public class RelatorioDAOFactory implements IEntidadeDAOFactory{


    @Override
    public RelatoriosDAO createDAO() {
        return new RelatoriosDAO();
    }
    
}
