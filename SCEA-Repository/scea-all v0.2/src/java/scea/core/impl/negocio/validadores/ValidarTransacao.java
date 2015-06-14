/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.negocio.validadores;

import scea.core.aplicacao.Resultado;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Transacao;

/**
 *
 * @author Felipe
 */
public class ValidarTransacao implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        Transacao t = (Transacao)entidade;
        if(t.getTipoDeTransacao().equals("ENTRADA")){
            ValidarLimiteEntrada v = new ValidarLimiteEntrada();
            return(v.processar(t));
            
            
        }else if(t.getTipoDeTransacao().equals("SAIDA")){
            ValidarLimiteSaida v = new ValidarLimiteSaida();
            return(v.processar(t));
        }
        
        
        return null;
        
        
        
    }
    
}
