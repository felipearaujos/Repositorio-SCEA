/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scea.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.SelectEvent;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;

import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;

/**
 *
 * @author Felipe
 */
@ManagedBean(name = "relatorioInicialBean")
public class RelatorioInicialBean extends EntidadeDominioBean {

    private List<Produto> produtosCriticos;
        private Produto produtoSelecionado;

 /*   public List<Produto> relatorioInicial() {
        Resultado r = new Resultado();
        //List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        rel.setNome("RELATORIOINICIAL");
        
        r = fachada.consultar(rel);
        //r = fachada.RelatorioInicial(new Produto());
        //entidades = r.getEntidades();
        List<Produto> produtos = new ArrayList<Produto>();
        for (EntidadeDominio e : r.getEntidades()) {
            Produto f = (Produto) e;
            produtos.add(f);
        }
        setProdutosCriticos(produtos);
        return getProdutosCriticos();
    }
*/
    @PostConstruct
    public void init(){
        Resultado r = new Resultado();
        //List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        rel.setNome("RELATORIOINICIAL");
        
        r = fachada.consultar(rel);
        //r = fachada.RelatorioInicial(new Produto());
        //entidades = r.getEntidades();
        List<Produto> produtos = new ArrayList<Produto>();
        for (EntidadeDominio e : r.getEntidades()) {
            Produto f = (Produto) e;
            produtos.add(f);
        }
        setProdutosCriticos(produtos);
    
    }
    
    
    
    public void pegar(SelectEvent event)
    {
       
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
