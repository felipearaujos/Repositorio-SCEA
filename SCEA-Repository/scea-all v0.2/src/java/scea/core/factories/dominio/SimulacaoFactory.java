/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dominio;

import scea.core.interfaces.Factories.IEntidadeFactory;
import scea.dominio.modelo.Simulacao;

/**
 *
 * @author Jonathan
 */
public class SimulacaoFactory implements IEntidadeFactory{
    @Override
    public Simulacao createEntidade()
    {
        return new Simulacao();
    }
}
