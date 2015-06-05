/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.aplicacao.relatorio;

/**
 *
 * @author Felipe
 */
public class RelatorioEstoque extends EntidadeRelatorio{
    private int qtdeEstoque;
    private int qtdeDiponivel;
    private double porcentagemOcupada;

    /**
     * @return the qtdeEstoque
     */
    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    /**
     * @param qtdeEstoque the qtdeEstoque to set
     */
    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    /**
     * @return the qtdeDiponivel
     */
    public int getQtdeDiponivel() {
        return qtdeDiponivel;
    }

    /**
     * @param qtdeDiponivel the qtdeDiponivel to set
     */
    public void setQtdeDiponivel(int qtdeDiponivel) {
        this.qtdeDiponivel = qtdeDiponivel;
    }

    /**
     * @return the porcentagemOcupada
     */
    public double getPorcentagemOcupada() {
        return porcentagemOcupada;
    }

    /**
     * @param porcentagemOcupada the porcentagemOcupada to set
     */
    public void setPorcentagemOcupada(double porcentagemOcupada) {
        this.porcentagemOcupada = porcentagemOcupada;
    }
    
}
