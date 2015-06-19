/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.impl.controle.Fachada;
import static scea.core.testes.MainTestes.fachada;
import static scea.core.testes.MainTestes.resultado;
import scea.web.beans.Builder.GraficoLinhaBuilder;
import scea.web.beans.Builder.GraficoPizzaEstoqueBuilder;

/**
 *
 * @author Jonathan
 */
@ManagedBean ( name = "estoquePizzaBean")
public class GraficoEstoquePizzaBean {
    
        private PieChartModel graficoPizza;
        
        public void inicializarGrafico()
        {
        EntidadeRelatorio rel = new EntidadeRelatorio();
        rel.setNome("RELATORIOSITUACAOESTOQUE");
        resultado = new Resultado();
        fachada = new Fachada();
        resultado = fachada.consultar(rel);
        
            GraficoPizzaEstoqueBuilder builder = new GraficoPizzaEstoqueBuilder()
                    .initModelo(resultado.getEntidades())
                    .informacoesGrafico();
            setGraficoPizza(builder.getGraficoPizza());
        }

    /**
     * @return the graficoPizza
     */
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
