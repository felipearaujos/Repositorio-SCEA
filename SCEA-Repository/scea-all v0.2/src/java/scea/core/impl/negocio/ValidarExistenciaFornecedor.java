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
import scea.core.impl.dao.FornecedorDAO;
import scea.core.impl.dao.ProdutoDAO;
import scea.core.impl.dao.TipoDeProdutoDAO;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;
public class ValidarExistenciaFornecedor implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        //Produto produto; //= new TipoDeProduto();
        //Produto produtoBanco = new Produto();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = null;
        Resultado resultado = new Resultado();
        boolean flgexiste = false;
        
        if(entidade instanceof Produto){
             fornecedor = ((Produto)entidade).getFornecedor();
        }else if(entidade instanceof Fornecedor){
            fornecedor = ((Fornecedor)entidade);
        }
        resultado.setEntidades(fornecedorDAO.consultar(fornecedor));
       if(resultado.getEntidades() != null){
        for(EntidadeDominio e : resultado.getEntidades()){
            Fornecedor f = (Fornecedor)e;
            if(f.getId().equals(fornecedor.getId()) ){
                flgexiste = true;
                break;
            }  
        }
       }
        if(!flgexiste){
            resultado.setMsg("Fornecedor NÃO CADASTRADO");
        }
          else{
            resultado.setMsg(null);
        }
        
        return resultado;
    }
    
    
}
