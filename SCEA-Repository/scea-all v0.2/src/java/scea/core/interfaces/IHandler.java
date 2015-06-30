/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.interfaces;

import java.util.List;
import scea.core.impl.controle.Handler;
import scea.dominio.modelo.EntidadeDominio;

public interface IHandler {
     public abstract List<EntidadeDominio> processarRequisicao(EntidadeDominio entidade);
}
