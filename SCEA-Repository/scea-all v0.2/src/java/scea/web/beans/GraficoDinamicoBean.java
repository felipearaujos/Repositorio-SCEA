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
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.aplicacao.relatorio.RelatorioDinamico;
import scea.core.impl.controle.Fachada;
import static scea.core.testes.MainTestes.fachada;
import static scea.core.testes.MainTestes.resultado;
import scea.dominio.modelo.EntidadeDominio;
import scea.web.beans.Builder.GraficoLinhaBuilder;

/**
 *
 * @author Main User
 */
@ManagedBean(name = "graficoDinamicoBean")
public class GraficoDinamicoBean extends GraficoBean {

    private BarChartModel graficoQuantidade;

    //private boolean renderizar = false;
    private boolean avgQtd = false;
    private boolean minQtd = false;
    private boolean maxQtd = false;
    private boolean sumQtd = false;
    
    //private Integer idTipo;
    //private Integer idFornecedor;

    //private Date dtInicial, dtFinal;
    //private Integer idTipo;
    //private Integer idProduto;

    public boolean initGrafico() {
        RelatorioDinamico rel = new RelatorioDinamico();
        resultado = new Resultado();
        if(getDtInicial() != null && getDtFinal() != null){
            rel.setDtInicial(getDtInicial());
            rel.setDtFinal((getDtFinal()));
        }
        fachada = new Fachada();

        rel.setNome("RELATORIODINAMICO");
        rel.setAvgQuantidade(isAvgQtd());
        rel.setMaxQuantidade(isMaxQtd());
        rel.setSumQuantidade(isSumQtd());
        rel.setMinQuantidade(isMinQtd());
        rel.getTransacao().getProduto().getFornecedor().setId(getIdFornecedor());
        rel.getTransacao().getProduto().getTipoDeProduto().setId(getIdTipo());
        

        resultado = fachada.consultar(rel);

        if (resultado.getEntidades() != null) {
            setGraficoQuantidade(gerarGraficoQuantidade(resultado.getEntidades()));
            setRenderizar(true);       
        } else {
            setRenderizar(false);
        }
        
        return true;
    }

   

    public BarChartModel gerarGraficoQuantidade(List<EntidadeDominio> entidades) {
        List<RelatorioDinamico> listRelatorios = new ArrayList<RelatorioDinamico>();
        BarChartModel graficoLinha = new BarChartModel();
        for (EntidadeDominio e : entidades) {
            RelatorioDinamico relatorio = (RelatorioDinamico) e;
            listRelatorios.add(relatorio);
        }
        ChartSeries entrada = new ChartSeries();
        entrada.setLabel("Entrada");

        ChartSeries saida = new ChartSeries();
        saida.setLabel("Saida");

        
        
        if((!isAvgQtd() && !isSumQtd() && !isMinQtd() && !isMaxQtd()) || listRelatorios.isEmpty()){
            entrada.set("Media", 0);
            entrada.set("Max",0);
            entrada.set("Min", 0);
            entrada.set("Total", 0);
        }
        
        for (RelatorioDinamico listRelatorio : listRelatorios) {
            if (listRelatorio.getTransacao().getTipoDeTransacao().equals("ENTRADA")) {
                if (isAvgQtd()) {
                    entrada.set("Media", listRelatorio.getAvgTransacao().getQtdeDoTipo());
                }
                if (isMaxQtd()) {
                    entrada.set("Max", listRelatorio.getMaxTransacao().getQtdeDoTipo());
                }
                if (isMinQtd()) {
                    entrada.set("Min", listRelatorio.getMinTransacao().getQtdeDoTipo());
                }
                if (isSumQtd()) {
                    entrada.set("Total", listRelatorio.getTransacao().getQtdeDoTipo());
                }
            } else if (listRelatorio.getTransacao().getTipoDeTransacao().equals("SAIDA")) {
                if (isAvgQtd()) {
                    saida.set("Media", listRelatorio.getAvgTransacao().getQtdeDoTipo());
                }
                if (isMaxQtd()) {
                    saida.set("Max", listRelatorio.getMaxTransacao().getQtdeDoTipo());
                }
                if (isMinQtd()) {
                    saida.set("Min", listRelatorio.getMinTransacao().getQtdeDoTipo());
                }
                if (isSumQtd()) {
                    saida.set("Total", listRelatorio.getTransacao().getQtdeDoTipo());
                }
            }
        } //For

        graficoLinha.setLegendPosition("ne");
        graficoLinha.addSeries(entrada);
        graficoLinha.addSeries(saida);
        graficoLinha.setTitle("Relatorio Visão Quantidade");
        graficoLinha.setAnimate(true);
        graficoLinha.setZoom(true);
        return graficoLinha;
    }
    
    
    
    
    
    
    



 
    /**
     * @return the graficoRetornado
     */
    public BarChartModel getGraficoQuantidade() {
        return graficoQuantidade;
    }

    /**
     * @param graficoRetornado the graficoRetornado to set
     */
    public void setGraficoQuantidade(BarChartModel graficoRetornado) {
        this.graficoQuantidade = graficoRetornado;
    }

   
   


    /**
     * @return the avg
     */
    public boolean isAvgQtd() {
        return avgQtd;
    }

    /**
     * @param avg the avg to set
     */
    public void setAvgQtd(boolean avg) {
        this.avgQtd = avg;
    }

    /**
     * @return the min
     */
    public boolean isMinQtd() {
        return minQtd;
    }

    /**
     * @param min the min to set
     */
    public void setMinQtd(boolean min) {
        this.minQtd = min;
    }

    /**
     * @return the max
     */
    public boolean isMaxQtd() {
        return maxQtd;
    }

    /**
     * @param max the max to set
     */
    public void setMaxQtd(boolean max) {
        this.maxQtd = max;
    }

    /**
     * @return the sum
     */
    public boolean isSumQtd() {
        return sumQtd;
    }

    /**
     * @param sum the sum to set
     */
    public void setSumQtd(boolean sum) {
        this.sumQtd = sum;
    }

}
