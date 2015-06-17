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
    private List<Produto> todosTiposDeProdutos;
    
    /**
     * @return the nomeTipo
     */
    public TipoDeProduto createTipoDeProduto()
    {
        TipoDeProduto ntp = new TipoDeProduto();
        ntp.setTipo(tipoDeProduto.getTipo());
        ntp.setQtdeMax(tipoDeProduto.getQtdeMax());
        ntp.setQtdeMin(tipoDeProduto.getQtdeMin());
        ntp.setDescricao(tipoDeProduto.getDescricao());
        
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
   /* public List<Produto> consultar()
    {
        Resultado r = new Resultado();
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        r = fachada.consultar(this.createTipoDeProduto());
        entidades = r.getEntidades();
        List<Produto> produtos = new ArrayList<Produto>();
        for(EntidadeDominio e: entidades)
        {
            Produto f = (Produto)e;
            produtos.add(f);
        }
        setTodosTiposDeProdutos(produtos);
        return getTodosTiposDeProdutos();
    }
    
   */
    
  
        
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
    

    public List<Produto> getTodosTiposDeProdutos() {
        return todosTiposDeProdutos;
    }

    /**
     * @param todosProdutos the todosProdutos to set
     */
    public void setTodosTiposDeProdutos(List<Produto> todosProdutos) {
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

   


}
