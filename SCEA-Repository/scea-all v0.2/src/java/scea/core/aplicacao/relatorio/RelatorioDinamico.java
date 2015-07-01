/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.aplicacao.relatorio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Transacao;

/**
 *
 * @author Felipe
 */
public class RelatorioDinamico extends EntidadeRelatorio{
    
    
      
    private boolean minQuantidade;
    private boolean maxQuantidade;
    private boolean avgQuantidade;
    private boolean sumQuantidade;
    private boolean minValor;
    private boolean maxValor;
    private boolean avgValor;
    private boolean sumValor;
    
    private Transacao minTransacao;
    private Transacao maxTransacao;
    private Transacao avgTransacao;
    
    
    
    
    public RelatorioDinamico(){
        transacao = new Transacao();
        minTransacao = new Transacao();
        avgTransacao = new Transacao();
        maxTransacao = new Transacao();
        nome = "RELATORIODINAMICO";
    }
    
    
 


    /**
     * @return the minQuantidade
     */
    public boolean isMinQuantidade() {
        return minQuantidade;
    }

    /**
     * @param minQuantidade the minQuantidade to set
     */
    public void setMinQuantidade(boolean minQuantidade) {
        this.minQuantidade = minQuantidade;
    }

    /**
     * @return the maxQuantidade
     */
    public boolean isMaxQuantidade() {
        return maxQuantidade;
    }

    /**
     * @param maxQuantidade the maxQuantidade to set
     */
    public void setMaxQuantidade(boolean maxQuantidade) {
        this.maxQuantidade = maxQuantidade;
    }

    /**
     * @return the avgQuantidade
     */
    public boolean isAvgQuantidade() {
        return avgQuantidade;
    }

    /**
     * @param avgQuantidade the avgQuantidade to set
     */
    public void setAvgQuantidade(boolean avgQuantidade) {
        this.avgQuantidade = avgQuantidade;
    }

    /**
     * @return the minValor
     */
    public boolean isMinValor() {
        return minValor;
    }

    /**
     * @param minValor the minValor to set
     */
    public void setMinValor(boolean minValor) {
        this.minValor = minValor;
    }

    /**
     * @return the maxValor
     */
    public boolean isMaxValor() {
        return maxValor;
    }

    /**
     * @param maxValor the maxValor to set
     */
    public void setMaxValor(boolean maxValor) {
        this.maxValor = maxValor;
    }

    /**
     * @return the avgValor
     */
    public boolean isAvgValor() {
        return avgValor;
    }

    /**
     * @param avgValor the avgValor to set
     */
    public void setAvgValor(boolean avgValor) {
        this.avgValor = avgValor;
    }

    /**
     * @return the valor
     */
    public boolean isValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(boolean valor) {
        this.valor = valor;
    }

    /**
     * @return the sumQuantidade
     */
    public boolean isSumQuantidade() {
        return sumQuantidade;
    }

    /**
     * @param sumQuantidade the sumQuantidade to set
     */
    public void setSumQuantidade(boolean sumQuantidade) {
        this.sumQuantidade = sumQuantidade;
    }

    /**
     * @return the sumValor
     */
    public boolean isSumValor() {
        return sumValor;
    }

    /**
     * @param sumValor the sumValor to set
     */
    public void setSumValor(boolean sumValor) {
        this.sumValor = sumValor;
    }

    /**
     * @return the minTransacao
     */
    public Transacao getMinTransacao() {
        return minTransacao;
    }

    /**
     * @param minTransacao the minTransacao to set
     */
    public void setMinTransacao(Transacao minTransacao) {
        this.minTransacao = minTransacao;
    }

    /**
     * @return the maxTransacao
     */
    public Transacao getMaxTransacao() {
        return maxTransacao;
    }

    /**
     * @param maxTransacao the maxTransacao to set
     */
    public void setMaxTransacao(Transacao maxTransacao) {
        this.maxTransacao = maxTransacao;
    }

    /**
     * @return the avgTransacao
     */
    public Transacao getAvgTransacao() {
        return avgTransacao;
    }

    /**
     * @param avgTransacao the avgTransacao to set
     */
    public void setAvgTransacao(Transacao avgTransacao) {
        this.avgTransacao = avgTransacao;
    }
    
    
    
}
