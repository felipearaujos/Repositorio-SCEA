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

@ManagedBean ( name = "graficoDinamicoBean")
public class GraficoDinamicoBean {
    private EntidadeRelatorio relatorio;
    private BarChartModel graficoRetornado;
    private boolean renderizar = false;
    private boolean avg = true;
    private boolean min = true; 
    private boolean max = true;
    private boolean sum = true;
    private RelatorioDinamico relD = new RelatorioDinamico();
    
    
    private Date dtInicial, dtFinal;
    //private Integer idTipo;
    //private Integer idProduto;
 
    public boolean initGrafico()
    {
        RelatorioDinamico rel = new RelatorioDinamico();
        resultado = new Resultado();
       // if(getDtInicial() == null)
       //     return false;
       // rel.setDtInicial(getDtInicial());
       // rel.setDtFinal((getDtFinal()));
        //rel.getTransacao().getProduto().getTipoDeProduto().setId(getIdTipo());
        fachada = new Fachada();
      
        
        rel.setNome("RELATORIODINAMICO");
        rel.setAvgQuantidade(avg);
        rel.setMaxQuantidade(max);
        rel.setSumQuantidade(sum);
        rel.setMinQuantidade(min);
        
        resultado = fachada.consultar(rel);
        
        if(resultado.getEntidades() != null){
            //setGraficoRetornado(gerarGrafico(resultado.getEntidades()));
             /*GraficoLinhaBuilder grafico = new GraficoLinhaBuilder()
                .initModelo(resultado.getEntidades())
                .informacoesGrafico(resultado.getEntidades(), formatar(getDtInicial()), formatar(getDtFinal()))
                .alocarEixos(resultado.getEntidades());
        setGraficoRetornado(grafico.getGraficoLinha());
       
            */
            setGraficoRetornado(gerarGrafico(resultado.getEntidades()));
            
            setRenderizar(true);
       // return true;
            
            //setRenderizar(true);
        }else{
            setRenderizar(false);
        }
        
        
  //      }
        return true;
    }
    public void teste(){
        initGrafico();
        
    }
    
    
    
    public BarChartModel gerarGrafico(List<EntidadeDominio> entidades){
        List<RelatorioDinamico> listRelatorios = new ArrayList<RelatorioDinamico>();
         BarChartModel graficoLinha = new BarChartModel();
         for(EntidadeDominio e: entidades)
         {
             RelatorioDinamico relatorio = (RelatorioDinamico)e;
             listRelatorios.add(relatorio);
         }
        
        if(listRelatorios.size() != 0)
        {
           ChartSeries entrada = new ChartSeries();
            entrada.setLabel("Entrada");
            
            ChartSeries saida = new ChartSeries();
            saida.setLabel("Saida");
            
            
            
          
            
            
            
           
            graficoLinha.setLegendPosition("se");
            for(int i=0; i < listRelatorios.size(); i++)
            {
                
               if(listRelatorios.get(i).getTransacao().getTipoDeTransacao().equals("ENTRADA"))
                {
                    if(isAvg()){
                        entrada.set("Media", listRelatorios.get(i).getAvgTransacao().getQtdeDoTipo());
                    }
                    
                    if(isMax()){
                        entrada.set("Max", listRelatorios.get(i).getMaxTransacao().getQtdeDoTipo());
                    }
                    
                    if(isMax()){
                        entrada.set("Min", listRelatorios.get(i).getMinTransacao().getQtdeDoTipo());
                    }
                    
                    if(isSum()){
                        entrada.set("Total", listRelatorios.get(i).getTransacao().getQtdeDoTipo());
                    }
                }
               else if(listRelatorios.get(i).getTransacao().getTipoDeTransacao().equals("SAIDA"))
                {   
                    if(isAvg()){
                        saida.set("Media", listRelatorios.get(i).getAvgTransacao().getQtdeDoTipo());
                    }
                    
                    if(isMax()){
                        saida.set("Max", listRelatorios.get(i).getMaxTransacao().getQtdeDoTipo());
                    }
                    
                    if(isMin()){
                        saida.set("Min", listRelatorios.get(i).getMinTransacao().getQtdeDoTipo());
                    }
                    if(isSum()){
                        saida.set("Total", listRelatorios.get(i).getTransacao().getQtdeDoTipo());
                    }
                }
               
               
            }
            
        
 /*           
            ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("a", 120);
        boys.set("b", 100);
        boys.set("c", 44);
        boys.set("d", 150);
        boys.set("e", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("a", 52);
        girls.set("b", 60);
        girls.set("c", 110);
        girls.set("d", 135);
        girls.set("e", 120);
 
        graficoLinha.addSeries(boys);
        graficoLinha.addSeries(girls);
        
 
               ChartSeries girls2 = new ChartSeries();
        girls2.setLabel("Girls");
        girls2.set("a", 52);
        girls2.set("b", 60);
        girls2.set("c", 110);
        girls2.set("d", 135);
        girls2.set("e", 120);
 
       
        graficoLinha.addSeries(girls2);
 */
        
        
           // graficoLinha.addSeries(maxEntradas);
            //graficoLinha.addSeries(maxSaidas);
           // graficoLinha.addSeries(avgEntradas);
            //graficoLinha.addSeries(avgSaidas);
             
             graficoLinha.addSeries(entrada);
             graficoLinha.addSeries(saida);
             graficoLinha.setTitle(listRelatorios.get(0).getTituloRelatorio());
            graficoLinha.setAnimate(true);
           
            
                  
        
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
    public BarChartModel getGraficoRetornado() {
        return graficoRetornado;
    }

    /**
     * @param graficoRetornado the graficoRetornado to set
     */
    public void setGraficoRetornado(BarChartModel graficoRetornado) {
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
     * @return the relD
     */
    public RelatorioDinamico getRelD() {
        return relD;
    }

    /**
     * @param relD the relD to set
     */
    public void setRelD(RelatorioDinamico relD) {
        this.relD = relD;
    }

    /**
     * @return the avg
     */
    public boolean isAvg() {
        return avg;
    }

    /**
     * @param avg the avg to set
     */
    public void setAvg(boolean avg) {
        this.avg = avg;
    }

    /**
     * @return the min
     */
    public boolean isMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(boolean min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public boolean isMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(boolean max) {
        this.max = max;
    }

    /**
     * @return the sum
     */
    public boolean isSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(boolean sum) {
        this.sum = sum;
    }

   

    
}
