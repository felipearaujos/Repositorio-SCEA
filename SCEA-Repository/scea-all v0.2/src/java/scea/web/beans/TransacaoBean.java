/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import scea.core.aplicacao.Estoque;
import scea.core.aplicacao.Resultado;
import scea.core.factories.dominio.EntradaFactory;
import scea.core.factories.dominio.SaidaFactory;
import scea.core.factories.dominio.TransacaoFactory;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.Entrada;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Saida;

import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

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
        entidadeFactory = new TransacaoFactory();
        Transacao transacao = (Transacao)entidadeFactory.createEntidade();
        transacao.setQtdeDoTipo(getQuantidade());
        transacao.setProduto(new Produto());
        transacao.getProduto().setId(getId());
        transacao.getProduto().setFornecedor(new Fornecedor());
        transacao.getProduto().getFornecedor().setId(0);
        transacao.getProduto().setNome(null);
        transacao.setAcesso(new Acesso());
        transacao.getAcesso().setId(getIdAcesso());
        transacao.getProduto().setQuantidade(getQuantidade());
        //transacao.setTipoDeTransacao(getOperacao());
        return transacao;
    }
    
    public Entrada createEntrada()
    {
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
        setIdAcesso (Integer.parseInt(session.getAttribute("id_user").toString()));
        entidadeFactory = new EntradaFactory();
        Entrada entrada = (Entrada)entidadeFactory.createEntidade();
        entrada.setQtdeDoTipo(getQuantidade());
        
        entrada.setProduto(new Produto());
        entrada.getProduto().setId(getId());
        entrada.getProduto().setFornecedor(new Fornecedor());
        entrada.getProduto().getFornecedor().setId(0);
        entrada.getProduto().setNome(null);
        
        entrada.setAcesso(new Acesso());
        entrada.getAcesso().setId(getIdAcesso());
         
        entrada.getProduto().setQuantidade(getQuantidade());
        //entrada.setTipoDeTransacao(getOperacao());
        return entrada;
    }
    
    public Saida createSaida()
    {
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
        setIdAcesso (Integer.parseInt(session.getAttribute("id_user").toString()));
        entidadeFactory = new SaidaFactory();
        Saida saida = (Saida)entidadeFactory.createEntidade();
        saida.setQtdeDoTipo(getQuantidade());
        saida.setProduto(new Produto());
        saida.getProduto().setId(getId());
        saida.getProduto().setFornecedor(new Fornecedor());
        saida.getProduto().getFornecedor().setId(0);
        saida.getProduto().setNome(null);
        
        saida.setAcesso(new Acesso());
        saida.getAcesso().setId(getIdAcesso());
         
        saida.getProduto().setQuantidade(getQuantidade());
        saida.setTipoDeTransacao(getOperacao());
        return saida;
    }
    
    
    
    
    @PostConstruct
     public void init(){
         setId(0);
         setQuantidade(0);
     }
        
    public void entrada()
    {
        //setOperacao("ENTRADA");
        Transacao transacao = this.createEntrada();
        
        Resultado resultado = fachada.salvar(transacao);
        //Resultado resultado = fachadaTransacao.entrada(transacao);
        if(resultado.getMsg() == null){
            resultado.setMsg("ENTRADA REALIZADA COM SUCESSO");
            zeraTransacao();
        }
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", resultado.getMsg());
            context.addMessage(null, mensagem);
            
            
           
            
            
    }
    
    
    
    public void saida()
    {

        String obs = "";
        //setOperacao("SAIDA");
        Transacao transacao = this.createSaida();
        
        //Resultado resultado = fachadaTransacao.saida(transacao);
        Resultado resultado = fachada.salvar(transacao);
        if(resultado.getMsg() == null){
            resultado.setMsg("SAIDA REALIZADA COM SUCESSO \n\n");
            zeraTransacao();
        }
        
/*        if(resultado.getEntidades() != null){
            if(((Estoque)resultado.getEntidades().get(0)).getObs() != null ){
              obs = ((Estoque)resultado.getEntidades().get(0)).getObs();
            }
        }
  */      
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", resultado.getMsg()+"\n"+ obs);
            context.addMessage(null, mensagem);
    }
     
   
    private void zeraTransacao(){
        setId(0);
            setQuantidade(0);
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
