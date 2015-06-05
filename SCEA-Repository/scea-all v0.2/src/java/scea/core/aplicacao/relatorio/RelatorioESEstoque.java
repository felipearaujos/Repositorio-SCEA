/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.aplicacao.relatorio;

import java.util.Date;

/**
 *
 * @author Main User
 */
public class RelatorioESEstoque extends EntidadeRelatorio{
    private Date dataPeriodo;
    private double quantidade;

    /**
     * @return the dataPeriodo
     */
    public Date getDataPeriodo() {
        return dataPeriodo;
    }

    /**
     * @param dataPeriodo the dataPeriodo to set
     */
    public void setDataPeriodo(Date dataPeriodo) {
        this.dataPeriodo = dataPeriodo;
    }

    /**
     * @return the quantidade
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
}
