/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.negocio;

/**
 *
 * @author Felipe
 */
import com.oracle.jrockit.jfr.Producer;
import java.util.ArrayList;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.ProdutoDAO;
import scea.core.impl.dao.TipoDeProdutoDAO;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;
public class ValidarExistenciaTipoDeProduto implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        TipoDeProduto tipo = null; //= new TipoDeProduto();
        Produto produtoBanco = new Produto();
        TipoDeProdutoDAO daoTipoDeProduto = new TipoDeProdutoDAO();
        Resultado resultado = new Resultado();
        boolean flgexiste = false;
        
        if(entidade instanceof Produto){
             tipo = ((Produto)entidade).getTipoDeProduto();
        }else if(entidade instanceof TipoDeProduto){
            tipo = (TipoDeProduto) entidade;
        }
        resultado.setEntidades(daoTipoDeProduto.consultar(tipo));
        for(EntidadeDominio e : resultado.getEntidades()){
            TipoDeProduto t = (TipoDeProduto)e;
            if(t.getId().equals(tipo.getId()) ){
                flgexiste = true;
                break;
            }  
        }
        if(!flgexiste){
            resultado.setMsg("TIPO NÃO CADASTRADO");
        }
        else{
            resultado.setMsg(null);
        }
        
        
        return resultado;
    }
    
    
}
