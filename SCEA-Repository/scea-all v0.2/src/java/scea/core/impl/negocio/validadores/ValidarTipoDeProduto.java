/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.negocio.validadores;

import scea.core.aplicacao.Resultado;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.TipoDeProduto;

/**
 *
 * @author Felipe
 */
public class ValidarTipoDeProduto implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        TipoDeProduto tipoProd = new TipoDeProduto();
        Resultado r = new Resultado();
        if(tipoProd.getTipo() == null || tipoProd.getDescricao()== null || tipoProd.getQtdeMax() <= 0 || tipoProd.getQtdeMin() < 0){
            r.setMsg("Digite todos campos validos");
        }
        return r;
    }
    
}
