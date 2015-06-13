/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dao;

import scea.core.impl.dao.FornecedorDAO;
import scea.core.interfaces.Factories.IEntidadeDAOFactory;

/**
 *
 * @author Jonathan
 */
public class FornecedorDAOFactory implements IEntidadeDAOFactory{
    @Override
    public FornecedorDAO createDAO()
    {
        return new FornecedorDAO();
    }
}
