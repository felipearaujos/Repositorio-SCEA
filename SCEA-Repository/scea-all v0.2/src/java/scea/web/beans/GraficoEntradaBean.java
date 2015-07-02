/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scea.web.beans;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.impl.controle.Fachada;
import static scea.core.testes.MainTestes.fachada;
import static scea.core.testes.MainTestes.resultado;
import scea.dominio.modelo.EntidadeDominio;
import scea.web.beans.Builder.GraficoLinhaBuilder;

/**
 *
 * @author Main User
 */
@ManagedBean(name = "graficoEntradaSaidaBean")
public class GraficoEntradaBean extends GraficoBean {

    private LineChartModel graficoRetornado;

    public boolean initGrafico() {
        EntidadeRelatorio rel = new EntidadeRelatorio();
        resultado = new Resultado();
        if (getDtInicial() == null) {
            return false;
        }
        rel.setDtInicial(getDtInicial());
        rel.setDtFinal((getDtFinal()));
        rel.getTransacao().getProduto().getTipoDeProduto().setId(getIdTipo());
        rel.getTransacao().getProduto().getFornecedor().setId(getIdFornecedor());
        rel.getTransacao().getProduto().setId(getIdProduto());
        fachada = new Fachada();

        rel.setNome("RELATORIOTRANSACOES");

        resultado = fachada.consultar(rel);

        if (resultado.getEntidades() != null) {
            GraficoLinhaBuilder grafico = new GraficoLinhaBuilder()
                    .initModelo(resultado.getEntidades())
                    .informacoesGrafico(resultado.getEntidades(), formatar(getDtFinal()), formatar(getDtFinal()))
                    .alocarEixos(resultado.getEntidades());

            setGraficoRetornado(grafico.getGraficoLinha());
            setRenderizar(true);

        } else {
            setRenderizar(false);
        }

        return true;
    }

    public void att() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (getIdProduto() != null) {
            context.addMessage(null, new FacesMessage("Aviso", "Campos: FORNECEDOR e TIPO serão desconsiderados"));
        }
    }

    /*
     public LineChartModel gerarGrafico(List<EntidadeDominio> entidades){
     List<EntidadeRelatorio> listRelatorios = new ArrayList<EntidadeRelatorio>();
     LineChartModel graficoLinha = new LineChartModel();
     for(EntidadeDominio e: entidades)
     {
     EntidadeRelatorio relatorio = (EntidadeRelatorio)e;
     listRelatorios.add(relatorio);
     }
        
     if(listRelatorios.size() != 0)
     {
     ChartSeries entradas = new ChartSeries();
     ChartSeries saidas = new ChartSeries();
     entradas.setLabel("Total de Entradas");
     saidas.setLabel("Total de Saídas");    
     graficoLinha.setLegendPosition("se");
     for(int i=0; i < listRelatorios.size(); i++)
     {
                
     if(listRelatorios.get(i).getTransacao().getTipoDeTransacao().equals("ENTRADA"))
     {
     entradas.set(listRelatorios.get(i).getMes(), listRelatorios.get(i).getTransacao().getQtdeDoTipo());
     //e//ntradas.set(i+2, i);
     }else
     {
     saidas.set(listRelatorios.get(i).getMes(), listRelatorios.get(i).getTransacao().getQtdeDoTipo());
                    
     }
     }
     graficoLinha.addSeries(entradas);
     graficoLinha.addSeries(saidas);
             
             
     graficoLinha.setAnimate(true);
     graficoLinha.setZoom(true);
            
     graficoLinha.setTitle("Total de Entradas e Saídas entre " + getDtInicial()
     + " á " + getDtFinal());
            
     DateAxis xAxis = new DateAxis("Meses entre o período");
     xAxis.setTickAngle(-50);
     xAxis.setTickFormat("%#d.%b.%y");
     graficoLinha.getAxes().put(AxisType.X, xAxis);
     //graficoLinha.getAxis(AxisType.Y).setLabel("Quantidade de Entradas e Saídas");
            
            
     Axis yAxis = graficoLinha.getAxis(AxisType.Y);
     yAxis.setLabel("Quantidade de Entradas e Saídas");
     yAxis.setMin(0);
            
            
        
     }
        
     return graficoLinha;
     }
    
    
     */
    /**
     * @return the graficoRetornado
     */
    public LineChartModel getGraficoRetornado() {
        return graficoRetornado;
    }

    /**
     * @param graficoRetornado the graficoRetornado to set
     */
    public void setGraficoRetornado(LineChartModel graficoRetornado) {
        this.graficoRetornado = graficoRetornado;
    }

}
