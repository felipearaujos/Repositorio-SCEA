/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.LineChartModel;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.web.beans.Builder.GraficoLinhaBuilder;
import scea.web.beans.Builder.GraficoTransacaoBuilder;

/**
 *
 * @author Main User
 */

@ManagedBean ( name = "graficoTransacoesBean")
public class GraficoTransacoesBean extends EntidadeDominioBean{
    private EntidadeRelatorio relatorio;

    public void atribuiDatas(){
        relatorio = new EntidadeRelatorio();
        Calendar c = Calendar.getInstance();  
        relatorio.setDtInicial(c.getTime());
        relatorio.setDtFinal(c.getTime());
        
    }
    
    
    public Resultado consultadadosRelatorio(){
        Resultado r = new Resultado();
        r = (fachada.transacoesPeriodo(createRelatorio()));
        return r;
    }
    
    
    
    public EntidadeRelatorio createRelatorio()
    {
        EntidadeRelatorio rel = new EntidadeRelatorio();
        //getRelatorio().setDtInicial(getRelatorio().getDtInicial());
        //getRelatorio().setDtFinal(getRelatorio().getDtFinal());   
        rel.setDtFinal(getRelatorio().getDtFinal());
        rel.setDtInicial(getRelatorio().getDtInicial());
        return rel;
    }
    
    public LineChartModel initGrafico()
    {
       EntidadeRelatorio rel = createRelatorio();
       
        
        GraficoTransacaoBuilder grafico;
        grafico = new GraficoTransacaoBuilder(consultadadosRelatorio())
                .initModelo()
                .informacoesGrafico()
                .alocarEixos();
        
        return grafico.getGraficoLinha();
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
    
}
