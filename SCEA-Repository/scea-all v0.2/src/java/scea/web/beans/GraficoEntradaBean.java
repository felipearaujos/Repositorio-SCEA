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

@ManagedBean ( name = "graficoEntradaSaidaBean")
public class GraficoEntradaBean {
    private EntidadeRelatorio relatorio;
    private LineChartModel graficoRetornado;
    private boolean renderizar = false;
    private Date dtInicial, dtFinal;
    private Integer idTipo;
    private Integer idProduto;
 
    public boolean initGrafico()
    {
        EntidadeRelatorio rel = new EntidadeRelatorio();
        resultado = new Resultado();
        //rel.setDtInicial("01/01/2015");
        //rel.setDtFinal(("31/07/2015"));
        
        //setDtInicial("01/01/2015");
        //setDtFinal(("31/07/2015"));
        //rel.setDtInicial("01/01/2015");
        //rel.setDtFinal(("31/06/2016"));
        if(getDtInicial() == null)
            return false;
        rel.setDtInicial(getDtInicial());
        rel.setDtFinal((getDtFinal()));
        rel.getTransacao().getProduto().getTipoDeProduto().setId(getIdTipo());
        fachada = new Fachada();
      
        
        rel.setNome("RELATORIOTRANSACOES");
        
        resultado = fachada.consultar(rel);
        
        if(resultado.getEntidades() != null){
            setGraficoRetornado(gerarGrafico(resultado.getEntidades()));
            setRenderizar(true);
        }else{
            setRenderizar(false);
        }
        
        
  //      }
        return true;
    }
    public void teste(){
        initGrafico();
        
    }
    
    
    
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
             
             
             graficoLinha.setTitle(listRelatorios.get(0).getTituloRelatorio());
            graficoLinha.setAnimate(true);
            graficoLinha.setTitle("Total de Entradas e Saídas entre " + getDtInicial()
            + " á " + getDtFinal());
            
                    DateAxis axis = new DateAxis("Meses entre o período");
            DateAxis axis2 = new DateAxis("Quantidade de Entradas e Saídas");
            axis.setTickAngle(-50);
            axis.setTickFormat("%#d / %b / %y");
            graficoLinha.getAxes().put(AxisType.X, axis);
            graficoLinha.getAxis(AxisType.Y).setLabel("Meses entre o período");
        
        }
        
        return graficoLinha;
    }
    
    
    public String formatar(Date data)
    {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatter.format(data);
        return dataFormatada;
    }

    /**
     * @return the relatorio
     */
    public EntidadeRelatorio getRelatorio() {
        return relatorio;
    }

    /**
     * @param relatorio the relatorio to set
     */
    public void setRelatorio(EntidadeRelatorio relatorio) {
        this.relatorio = relatorio;
    }

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

    /**
     * @return the renderizar
     */
    public boolean isRenderizar() {
        return renderizar;
    }

    /**
     * @param renderizar the renderizar to set
     */
    public void setRenderizar(boolean renderizar) {
        this.renderizar = renderizar;
    }

    /**
     * @return the dtInicial
     */
    public Date getDtInicial() {
        return dtInicial;
    }

    /**
     * @param dtInicial the dtInicial to set
     */
    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }

    /**
     * @return the dtFinal
     */
    public Date getDtFinal() {
        return dtFinal;
    }

    /**
     * @param dtFinal the dtFinal to set
     */
    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

    /**
     * @return the grafico
     */

    
        public void setDtInicial(String dtInicial) {
        //this.dtInicial = dtInicial;
        Date dt;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dt = df.parse(dtInicial);
            //return dt;
            this.setDtInicial(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
        
        
            public void setDtFinal(String dtFinal){
     Date dt;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dt = df.parse(dtFinal);
            //return dt;
            this.setDtFinal(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the idTipo
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    
}
