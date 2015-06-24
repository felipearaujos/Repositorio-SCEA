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
import scea.core.aplicacao.relatorio.RelatorioDetalheEstoque;
import scea.core.aplicacao.relatorio.RelatorioEstoque;
import static scea.core.testes.MainTestes.resultado;
import scea.dominio.modelo.EntidadeDominio;

/**
 *
 * @author Main User
 */
public class GraficoPizzaDetalheBuilder {
    
    private PieChartModel graficoPizza;

    public GraficoPizzaDetalheBuilder  initModelo(List<EntidadeDominio> entidades) {
         
        List<RelatorioDetalheEstoque> listRelatorios = new ArrayList<RelatorioDetalheEstoque>();
         
         for(EntidadeDominio e: entidades)
         {
             RelatorioDetalheEstoque relatorio = (RelatorioDetalheEstoque)e;
             listRelatorios.add(relatorio);
         }
         
        graficoPizza = new PieChartModel(); 
        graficoPizza.set("Produtos Zerados", listRelatorios.get(0).getQtdeZerado());
        graficoPizza.set("Produtos Disponíveis", listRelatorios.get(0).getQtdeDiponivel());
        graficoPizza.set("Produtos Criticos", listRelatorios.get(0).getQtdeCritico());
        

        
        return this;
   }

  public GraficoPizzaDetalheBuilder informacoesGrafico()
  {
        graficoPizza.setTitle("Situação estoque");
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
