/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;

@ManagedBean(name = "tipoDeprodutoBean")
public class TipodeProdutoBean extends EntidadeDominioBean{
    private TipoDeProduto tipoDeProduto = new TipoDeProduto();
    private List<TipoDeProduto> todosTiposDeProdutos;
    private int idtp;
    
    /**
     * @return the nomeTipo
     */
    public TipoDeProduto createTipoDeProduto()
    {
        TipoDeProduto ntp = new TipoDeProduto();
        ntp.setTipo(getTipoDeProduto().getTipo());
        ntp.setQtdeMax(getTipoDeProduto().getQtdeMax());
        ntp.setQtdeMin(getTipoDeProduto().getQtdeMin());
        ntp.setDescricao(getTipoDeProduto().getDescricao());
        ntp.setId(idtp);
        
        return ntp;
    }
  /*  
    public void Salvar()
    {
        Produto p = createProduto();
        Resultado re = f.salvar(p);
        if(re.getMsg() == null)
        {
            re.setMsg("Produto Salvo com sucesso!");
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
        
        
    }
*/
    public List<TipoDeProduto> consultar()
    {
        r = new Resultado();
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        r = fachada.consultar(this.createTipoDeProduto());
        entidades = r.getEntidades();
        List<TipoDeProduto> produtos = new ArrayList<TipoDeProduto>();
        for(EntidadeDominio e: entidades)
        {
            TipoDeProduto f = (TipoDeProduto)e;
            produtos.add(f);
        }
        setTodosTiposDeProdutos(produtos);
        return getTodosTiposDeProdutos();
    }
    
   
    
  
        
    public void Salvar()
    {
        TipoDeProduto tipoDeproduto = this.createTipoDeProduto();
        Resultado r = fachada.salvar(tipoDeproduto);
        if(r.getMsg() == null){
            r.setMsg("SALVO COM SUCESSO");
        }
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", r.getMsg());
            context.addMessage(null, mensagem);
    }
    

    public List<TipoDeProduto> getTodosTiposDeProdutos() {
        return todosTiposDeProdutos;
    }

    /**
     * @param todosProdutos the todosProdutos to set
     */
    public void setTodosTiposDeProdutos(List<TipoDeProduto> todosProdutos) {
        this.todosTiposDeProdutos = todosProdutos;
    }

    /**
     * @return the tipoDeProduto
     */
    public TipoDeProduto getTipoDeProduto() {
        return tipoDeProduto;
    }

    /**
     * @param tipoDeProduto the tipoDeProduto to set
     */
    public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
        this.tipoDeProduto = tipoDeProduto;
    }

    /**
     * @return the idtp
     */
    public int getIdtp() {
        return idtp;
    }

    /**
     * @param idtp the idtp to set
     */
    public void setIdtp(int idtp) {
        this.idtp = idtp;
    }

   


}
