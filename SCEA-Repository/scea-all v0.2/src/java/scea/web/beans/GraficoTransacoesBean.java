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
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.LineChartModel;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.impl.controle.Fachada;
import static scea.core.testes.MainTestes.fachada;
import static scea.core.testes.MainTestes.resultado;
import scea.web.beans.Builder.GraficoLinhaBuilder;
import scea.web.beans.Builder.GraficoTransacaoBuilder;


/**
 *
 * @author Main User
 */

@ManagedBean ( name = "graficoTransacoesBean")
public class GraficoTransacoesBean extends EntidadeDominioBean{
    private EntidadeRelatorio relatorio;
    private LineChartModel graficoRetornado;
    private boolean renderizar = false;
    private Date dtInicial, dtFinal;
    private int idprod;
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
        fachada = new Fachada();
        rel.setNome("RELATORIOTRANSACOESPRODUTO");
        resultado = fachada.consultar(rel);
        
        //if(resultado.getEntidades() != null){
        GraficoTransacaoBuilder grafico = new GraficoTransacaoBuilder()
                .initModelo(resultado.getEntidades())
                .informacoesGrafico(resultado.getEntidades(), formatar(getDtInicial()), formatar(getDtFinal()))
                .alocarEixos(resultado.getEntidades());
        setGraficoRetornado(grafico.getGraficoLinha());
        setRenderizar(true);
        //}
        return true;
    }
    public void teste(){
        //setRenderizar(false);
        initGrafico();
        
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
     * @return the idprod
     */
    public int getIdprod() {
        return idprod;
    }

    /**
     * @param idprod the idprod to set
     */
    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }
    
}
