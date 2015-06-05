/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import scea.core.aplicacao.Estoque;
import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Transacao;

import scea.dominio.modelo.TipoDeProduto;

@ManagedBean(name = "transacaoBean")
public class TransacaoBean extends ProdutoBean{
    //private String nomeTipo;
    private int quantidade;
    //private int idProduto;
    private int idAcesso;
    private String operacao;

    public Transacao createTransacao()
    {
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
        setIdAcesso (Integer.parseInt(session.getAttribute("id_user").toString()));
        Transacao transacao = new Transacao();
        transacao.setQtdeDoTipo(getQuantidade());
        transacao.setProduto(new Produto());
        transacao.getProduto().setId(getId());
        
        transacao.setAcesso(new Acesso());
        transacao.getAcesso().setId(getIdAcesso());
         
        transacao.getProduto().setQuantidade(getQuantidade());
        transacao.setTipoDeTransacao(getOperacao());
        return transacao;
    }
    
   
        
    public void entrada()
    {
        setOperacao("ENTRADA");
        Transacao transacao = this.createTransacao();
        
        Resultado resultado = fachadaTransacao.entrada(transacao);
        if(resultado.getMsg() == null){
            resultado.setMsg("Entrada realizada COM SUCESSO");
        }
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", resultado.getMsg());
            context.addMessage(null, mensagem);
    }
    
    
    
    public void saida()
    {

        String obs = "";
        setOperacao("SAIDA");
        Transacao transacao = this.createTransacao();
        
        Resultado resultado = fachadaTransacao.saida(transacao);
        if(resultado.getMsg() == null){
            resultado.setMsg("SAIDA REALIZADA COM SUCESSO \n\n");
        }
        
        if(resultado.getEntidades() != null){
            if(((Estoque)resultado.getEntidades().get(0)).getObs() != null ){
              obs = ((Estoque)resultado.getEntidades().get(0)).getObs();
            }
        }
        
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", resultado.getMsg()+"\n"+ obs);
            context.addMessage(null, mensagem);
    }
     
   
    @Override
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the idProduto
     */
   /* public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }*/

    /**
     * @return the idAcesso
     */
    public int getIdAcesso() {
        return idAcesso;
    }

    /**
     * @param idAcesso the idAcesso to set
     */
    public void setIdAcesso(int idAcesso) {
        this.idAcesso = idAcesso;
    }

    /**
     * @return the operacao
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * @param operacao the operacao to set
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
 
}
