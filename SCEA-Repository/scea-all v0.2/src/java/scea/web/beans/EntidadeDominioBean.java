/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import javax.faces.bean.ManagedBean;
import scea.core.aplicacao.Resultado;
import scea.core.factories.controle.FachadaFactory;
import scea.core.impl.controle.Fachada;

import scea.core.interfaces.Factories.IEntidadeDAOFactory;
import scea.core.interfaces.Factories.IEntidadeFactory;
import scea.core.interfaces.Factories.IFachadaFactory;
import scea.core.interfaces.IFachada;

/**
 *
 * @author Main User
 */
@ManagedBean(name = "entidadeBean")
public abstract class EntidadeDominioBean {
    public int id;
    IFachadaFactory fachadaFactory = new FachadaFactory();
    IEntidadeFactory entidadeFactory;
    IEntidadeDAOFactory entidadeDAOFactory;

    IFachada fachada = fachadaFactory.createFachada();
    
    public Resultado r = new Resultado();
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
