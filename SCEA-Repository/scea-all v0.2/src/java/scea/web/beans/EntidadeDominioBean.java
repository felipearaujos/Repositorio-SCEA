/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import javax.faces.bean.ManagedBean;
import scea.core.aplicacao.Resultado;
import scea.core.impl.controle.Fachada;
import scea.core.impl.controle.FachadaTransacao;

/**
 *
 * @author Main User
 */
@ManagedBean(name = "entidadeBean")
public class EntidadeDominioBean {
    public int id;
    Fachada fachada = new Fachada();
    FachadaTransacao fachadaTransacao = new FachadaTransacao();
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
