/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import scea.core.aplicacao.EmailAplicacao;
import scea.core.aplicacao.Resultado;

/**
 *
 * @author Jonathan
 */
@ManagedBean (name = "emailBean")
public class EmailBean extends EntidadeDominioBean{
    
    private String Assunto;
    private String Destinatario;
    private String Mensagem;

    /**
     * @return the Assunto
     */
    
    public EmailAplicacao createEmail()
    {
        EmailAplicacao umEmail = new EmailAplicacao();
        umEmail.setAssunto(getAssunto());
        umEmail.setDestinatario(getDestinatario());
        umEmail.setMensagem(getMensagem());
        
        return umEmail;
    }
    
    public void enviarEmail()
    {
        EmailAplicacao umEmail = createEmail();
        Resultado r = fachada.enviarEmail(umEmail);
        
        if(r.getMsg() == null)
            r.setMsg( "Email enviado ao fornecedor!" );
        limpaCampos();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", r.getMsg());
        context.addMessage(null, mensagem);

    }
    
    public void limpaCampos()
    {
        setAssunto("");
        setDestinatario("");
        setMensagem("");
    }
    
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
