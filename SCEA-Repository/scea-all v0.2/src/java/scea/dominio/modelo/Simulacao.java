/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.dominio.modelo;

import java.util.Date;

/**
 *
 * @author Main User
 */
public class Simulacao extends EntidadeDominio{
    	private String tipoDeTransacao;
	private Date dtTransacaoFutura;
	private Produto produto;
	private int qtdeItens =0;

    /**
     * @return the tipoDeTransacao
     */
    public String getTipoDeTransacao() {
        return tipoDeTransacao;
    }

    /**
     * @param tipoDeTransacao the tipoDeTransacao to set
     */
    public void setTipoDeTransacao(String tipoDeTransacao) {
        this.tipoDeTransacao = tipoDeTransacao;
    }

    /**
     * @return the dtTransacaoFutura
     */

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the qtdeItens
     */
    public int getQtdeItens() {
        return qtdeItens;
    }

    /**
     * @param qtdeItens the qtdeItens to set
     */
    public void setQtdeItens(int qtdeItens) {
        this.qtdeItens = qtdeItens;
    }

    /**
     * @return the dtTransacaoFutura
     */
    public Date getDtTransacaoFutura() {
        return dtTransacaoFutura;
    }

    /**
     * @param dtTransacaoFutura the dtTransacaoFutura to set
     */
    public void setDtTransacaoFutura(Date dtTransacaoFutura) {
        this.dtTransacaoFutura = dtTransacaoFutura;
    }

}
