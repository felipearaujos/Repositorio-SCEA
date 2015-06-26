/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import scea.core.aplicacao.EmailAplicacao;
import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.Produto;

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
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
        Produto produtoSelecionado = ((Produto)session.getAttribute("produtoSelecionado"));
        
        EmailAplicacao umEmail = new EmailAplicacao();
        if(produtoSelecionado != null){
            umEmail.setDestinatario(produtoSelecionado.getFornecedor().getEmail());
        }
        else{
            umEmail.setDestinatario(getDestinatario());
        }
        umEmail.setAssunto(getAssunto());
        
        umEmail.setMensagem(getMensagem());
        
        return umEmail;
    }
    
    @PostConstruct
    public void init(){
    
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
        Produto produtoSelecionado = ((Produto)session.getAttribute("produtoSelecionado"));
        
        
        if(produtoSelecionado != null){
            setDestinatario(produtoSelecionado.getFornecedor().getEmail());
        }
        else{
            setDestinatario(getDestinatario());
        }
        
        
    }
    
    public void enviarEmail()
    {
        //(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession();
        
        
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

    public void limparCampos()
    {
        setAssunto("");
        setDestinatario("");
        setMensagem("");
    }
}
