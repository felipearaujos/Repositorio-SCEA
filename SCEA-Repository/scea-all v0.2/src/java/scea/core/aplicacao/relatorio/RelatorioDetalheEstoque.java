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
public class RelatorioDetalheEstoque extends EntidadeRelatorio{
    private int qtdeZerado;
    private int qtdeDiponivel;
    private int qtdeCritico;

    /**
     * @return the qtdeZerado
     */
    public int getQtdeZerado() {
        return qtdeZerado;
    }

    /**
     * @param qtdeZerado the qtdeZerado to set
     */
    public void setQtdeZerado(int qtdeZerado) {
        this.qtdeZerado = qtdeZerado;
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
     * @return the qtdeCritico
     */
    public int getQtdeCritico() {
        return qtdeCritico;
    }

    /**
     * @param qtdeCritico the qtdeCritico to set
     */
    public void setQtdeCritico(int qtdeCritico) {
        this.qtdeCritico = qtdeCritico;
    }

   
    
}
