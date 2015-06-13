/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans.Builder;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.PieChartModel;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.aplicacao.relatorio.RelatorioEstoque;
import static scea.core.testes.MainTestes.resultado;
import scea.dominio.modelo.EntidadeDominio;

/**
 *
 * @author Main User
 */
public class GraficoPizzaEstoqueProdutoBuilder {
    
    private PieChartModel graficoPizza;

    public GraficoPizzaEstoqueProdutoBuilder  initModelo(List<EntidadeDominio> entidades) {
         
        List<RelatorioEstoque> listRelatorios = new ArrayList<RelatorioEstoque>();
         
         for(EntidadeDominio e: entidades)
         {
             RelatorioEstoque relatorio = (RelatorioEstoque)e;
             listRelatorios.add(relatorio);
         }
         
        graficoPizza = new PieChartModel(); 
        graficoPizza.set("Quantidade Ocupada", listRelatorios.get(0).getQtdeEstoque());
        graficoPizza.set("Quantidade Disponível", listRelatorios.get(0).getQtdeDiponivel());
         
        return this;
   }

  public GraficoPizzaEstoqueProdutoBuilder informacoesGrafico()
  {
        graficoPizza.setTitle("Análise de Armazenamento de Estoque Físico do Produto ");
        graficoPizza.setLegendPosition("w");
        graficoPizza.setShowDataLabels(true);
        
        return this;
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
