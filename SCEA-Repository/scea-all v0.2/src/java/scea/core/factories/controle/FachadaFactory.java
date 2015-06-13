/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.controle;

import scea.core.impl.controle.Fachada;
import scea.core.interfaces.Factories.IFachadaFactory;

/**
 *
 * @author Jonathan
 */
public class FachadaFactory implements IFachadaFactory{
    @Override
    public Fachada createFachada()
    {
        return new Fachada();
    }
}
