/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.controle;

import java.util.List;
import scea.core.interfaces.IHandler;
import scea.dominio.modelo.EntidadeDominio;

public abstract class Handler implements IHandler{
    private Handler Sucessor;

    public Handler getSucessor() {
        return Sucessor;
    }

    public void setSucessor(Handler handlerRequest) {
        this.Sucessor = handlerRequest;
    }
    
    public abstract List<EntidadeDominio> processarRequisicao(EntidadeDominio entidade);
}
