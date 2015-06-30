/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.handlers;

import java.util.List;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.impl.controle.Handler;
import scea.core.impl.dao.RelatoriosDAO;
import scea.dominio.modelo.EntidadeDominio;


public class TelaInicialHandler extends Handler{

    @Override
    public List<EntidadeDominio> processarRequisicao(EntidadeDominio entidade) {
        EntidadeRelatorio relatorio = (EntidadeRelatorio) entidade;
        RelatoriosDAO rlDAO = new RelatoriosDAO();
            if(relatorio.getNome().toUpperCase().equals("RELATORIOINICIAL")){
                return rlDAO.relatorioInicial();
            }
            else
            {
                return this.getSucessor().processarRequisicao(entidade);
            }
    }
    
}
