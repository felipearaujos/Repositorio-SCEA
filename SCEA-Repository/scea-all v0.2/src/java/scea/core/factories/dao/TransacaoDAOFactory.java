/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dao;

import scea.core.impl.dao.TransacaoDAO;
import scea.core.interfaces.Factories.IEntidadeDAOFactory;

/**
 *
 * @author Jonathan
 */
public class TransacaoDAOFactory implements IEntidadeDAOFactory{
    @Override
    public TransacaoDAO createDAO()
    {
        return new TransacaoDAO();
    }
}
