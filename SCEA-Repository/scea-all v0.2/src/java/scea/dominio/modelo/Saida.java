/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scea.dominio.modelo;

/**
 *
 * @author Felipe
 */
public class Saida extends Transacao {

    private String tipoDeTransacao = "SAIDA";

    public Saida() {
        produto = new Produto();
    }

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
}
