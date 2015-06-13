/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.factories.dominio;
import scea.core.interfaces.Factories.IEntidadeFactory;
import scea.dominio.modelo.Acesso;

/**
 *
 * @author Jonathan
 */
public class AcessoFactory implements IEntidadeFactory{
    
    @Override
    public Acesso createEntidade()
    {
        return new Acesso();
    }
}
