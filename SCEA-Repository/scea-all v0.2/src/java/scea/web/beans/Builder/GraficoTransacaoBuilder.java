/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans.Builder;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.dominio.modelo.EntidadeDominio;
import scea.web.beans.Builder.*;

public class GraficoTransacaoBuilder implements Serializable{
    
    private LineChartModel graficoLinha = new LineChartModel();
    private Resultado resultado;
    private EntidadeRelatorio rel;
    
    
    
    public GraficoTransacaoBuilder(Resultado r){
        resultado = r;
    }
    
     public GraficoTransacaoBuilder  initModelo() {
         
         
        
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Entrada");
        graficoLinha.setLegendPosition("e");
        
         for (EntidadeDominio e : resultado.getEntidades()) {
             rel = (EntidadeRelatorio)e;
             series1.set(rel.getMes(), rel.getTransacao().getQtdeDoTipo());
         }
         
        
        series1.set("2014-01-01", 51);
        //series1.set("2014-01-06", 22);
        //series1.set("2014-01-12", 65);
        //series1.set("2014-01-18", 74);
        //series1.set("2014-01-24", 24);
        //series1.set("2014-01-30", 51);
 
       /* LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Saída: Caneta Azul");
 
        series2.set("2014-01-01", 1);
        series2.set("2014-01-06", 73);
        series2.set("2014-01-12", 24);
        series2.set("2014-01-18", 12);
        series2.set("2014-01-24", 74);
        series2.set("2014-01-30", 62);
 */
        graficoLinha.addSeries(series1);
   //     graficoLinha.addSeries(series2);
        return this;
     }
         
    public GraficoTransacaoBuilder informacoesGrafico()
    {
    graficoLinha.setTitle("Entrada e Saída por Periodo ");
    graficoLinha.setZoom(true);
    graficoLinha.getAxis(AxisType.Y).setLabel("Número Total de Entradas e Saídas");
    return this;
    }

    public GraficoTransacaoBuilder alocarEixos()
    {
        DateAxis axis = new DateAxis("Período Selecionado");
        axis.setTickAngle(-50);
        axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");    
        graficoLinha.getAxes().put(AxisType.X, axis);
        return this;
    }
     
    
    /**
     * @return the graficoLinha
     */
    public LineChartModel getGraficoLinha() {
        return graficoLinha;
    }

    /**
     * @param graficoLinha the graficoLinha to set
     */
    public void setGraficoLinha(LineChartModel graficoLinha) {
        this.graficoLinha = graficoLinha;
    }

    /**
     * @return the resultado
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    
    
}
