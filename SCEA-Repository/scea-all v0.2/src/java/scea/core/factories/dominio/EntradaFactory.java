/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dominio;

import scea.core.interfaces.Factories.IEntidadeFactory;
import scea.dominio.modelo.Entrada;
import scea.dominio.modelo.Transacao;

/**
 *
 * @author Jonathan
 */
public class EntradaFactory implements IEntidadeFactory{
    
    @Override
    public Entrada createEntidade()
    {
        return new Entrada();
    }
}
