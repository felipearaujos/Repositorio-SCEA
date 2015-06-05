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

@ManagedBean(name = "produtoBean")
public class ProdutoBean extends EntidadeDominioBean{
    private int quantidade;
    private double valor;
    private String nome;
    private int idTipo;
    private List<Produto> todosProdutos;
    private List<Produto> produtosCriticos;
    private int idFornecedor;
    private Produto produtoSelecionado;
    /**
     * @return the nomeTipo
     */
    public Produto createProduto()
    {
        Produto p = new Produto();
        p.setId(getId());
        p.setNome(getNome());
        p.setQuantidade(getQuantidade());
        p.setValor(getValor());
        p.getTipoDeProduto().setId(getIdTipo());
        p.getFornecedor().setId(getIdFornecedor());
        
        return p;
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
    public List<Produto> consultar()
    {
        Resultado r = new Resultado();
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        r = fachada.consultar(this.createProduto());
        entidades = r.getEntidades();
        List<Produto> produtos = new ArrayList<Produto>();
        for(EntidadeDominio e: entidades)
        {
            Produto f = (Produto)e;
            produtos.add(f);
        }
        setTodosProdutos(produtos);
        return getTodosProdutos();
    }
    
    public List<Produto> relatorioInicial()
    {
        Resultado r = new Resultado();
        //List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        r = fachada.RelatorioInicial(new Produto());
        //entidades = r.getEntidades();
        List<Produto> produtos = new ArrayList<Produto>();
        for(EntidadeDominio e: r.getEntidades())
        {
            Produto f = (Produto)e;
            produtos.add(f);
        }
        setProdutosCriticos(produtos);
        return getProdutosCriticos();
    }
    
    public void pegar(SelectEvent event)
    {
        
    }
        
    public void Salvar()
    {
        Produto produto = this.createProduto();
        Resultado r = fachada.salvar(produto);
        if(r.getMsg() == null){
            r.setMsg("SALVO COM SUCESSO");
        }
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", r.getMsg());
            context.addMessage(null, mensagem);
    }
    
    public void Excluir()
    {
        Produto p = createProduto();
        Resultado re = fachada.excluir(p);
        if(re.getMsg() == null)
        {
            re.setMsg("Acesso Salvo com sucesso!");
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
    }
        



    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

   
    /**
     * @return the todosProdutos
     */
    public List<Produto> getTodosProdutos() {
        return todosProdutos;
    }

    /**
     * @param todosProdutos the todosProdutos to set
     */
    public void setTodosProdutos(List<Produto> todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    /**
     * @return the idFornecedor
     */
    public int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * @param idFornecedor the idFornecedor to set
     */
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    /**
     * @return the idTipo
     */
    public int getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the produtosCriticos
     */
    public List<Produto> getProdutosCriticos() {
        return produtosCriticos;
    }

    /**
     * @param produtosCriticos the produtosCriticos to set
     */
    public void setProdutosCriticos(List<Produto> produtosCriticos) {
        this.produtosCriticos = produtosCriticos;
    }

    /**
     * @return the produtoSelecionado
     */
    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    /**
     * @param produtoSelecionado the produtoSelecionado to set
     */
    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }


}
