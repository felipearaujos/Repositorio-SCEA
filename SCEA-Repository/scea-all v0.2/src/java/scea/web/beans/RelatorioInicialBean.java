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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.PieChartModel;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.impl.controle.Fachada;
import static scea.core.testes.MainTestes.fachada;
import static scea.core.testes.MainTestes.resultado;

import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.web.beans.Builder.GraficoPizzaDetalheBuilder;
import scea.web.beans.Builder.GraficoPizzaEstoqueBuilder;

/**
 *
 * @author Felipe
 */
@ManagedBean(name = "relatorioInicialBean")
public class RelatorioInicialBean extends EntidadeDominioBean {

    private List<Produto> produtosCriticos;
        private Produto produtoSelecionado;
        
        
               private PieChartModel graficoPizza;
        
        public void inicializarGrafico()
        {
        EntidadeRelatorio rel = new EntidadeRelatorio();
        rel.setNome("RELATORIODETALHEINICIAL");
        resultado = new Resultado();
        fachada = new Fachada();
        resultado = fachada.consultar(rel);
        
            GraficoPizzaDetalheBuilder builder = new GraficoPizzaDetalheBuilder()
                    .initModelo(resultado.getEntidades())
                    .informacoesGrafico();
            setGraficoPizza(builder.getGraficoPizza());
        }

        

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
    
        
        
        inicializarGrafico();
    }
    
    
    
    public void pegar(SelectEvent event)
    {
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true ); 
        session.setAttribute("produtoSelecionado", this.produtoSelecionado);
       
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
    
    
    public PieChartModel getGraficoPizza() {
        return graficoPizza;
    }

    /**
     * @param graficoPizza the graficoPizza to set
     */
    public void setGraficoPizza(PieChartModel graficoPizza) {
        this.graficoPizza = graficoPizza;
    }
}
