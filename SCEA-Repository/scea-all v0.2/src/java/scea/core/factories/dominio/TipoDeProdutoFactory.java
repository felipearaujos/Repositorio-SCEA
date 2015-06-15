/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dominio;

import scea.core.interfaces.Factories.IEntidadeFactory;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;

/**
 *
 * @author Jonathan
 */
public class TipoDeProdutoFactory implements IEntidadeFactory{
   
    @Override
    public TipoDeProduto createEntidade() 
    {
        return new TipoDeProduto();
    }
}
