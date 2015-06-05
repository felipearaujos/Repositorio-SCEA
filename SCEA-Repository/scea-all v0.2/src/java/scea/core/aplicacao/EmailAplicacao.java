/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.aplicacao;

import scea.dominio.modelo.EntidadeDominio;

/**
 *
 * @author Jonathan
 */
public class EmailAplicacao extends EntidadeDominio{
    private String Assunto;
    private String Destinatario;
    private String Mensagem;

    /**
     * @return the Assunto
     */
    public String getAssunto() {
        return Assunto;
    }

    /**
     * @param Assunto the Assunto to set
     */
    public void setAssunto(String Assunto) {
        this.Assunto = Assunto;
    }

    /**
     * @return the Destinatario
     */
    public String getDestinatario() {
        return Destinatario;
    }

    /**
     * @param Destinatario the Destinatario to set
     */
    public void setDestinatario(String Destinatario) {
        this.Destinatario = Destinatario;
    }

    /**
     * @return the Mensagem
     */
    public String getMensagem() {
        return Mensagem;
    }

    /**
     * @param Mensagem the Mensagem to set
     */
    public void setMensagem(String Mensagem) {
        this.Mensagem = Mensagem;
    }
}
