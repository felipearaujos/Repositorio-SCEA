package scea.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;

import scea.core.aplicacao.Estoque;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.ProdutoDAO;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class ValidarLimiteEntrada {

	public Resultado processar(EntidadeDominio entidade)
	{

            Resultado resultado = new Resultado();
            Estoque entEntrada = new Estoque();
            Transacao transacao = (Transacao)entidade;
            ProdutoDAO produtoDAO = new ProdutoDAO();
            boolean flgExiste = false;

            Produto produtoBuscado = new Produto();
             resultado.setEntidades(produtoDAO.consultar(transacao.getProduto()));
            for(EntidadeDominio e : resultado.getEntidades()){
                Produto produto = (Produto)e;
                if(produto.getId().equals(transacao.getProduto().getId()) ){
                    produtoBuscado = produto;
                    flgExiste = true;
                    break;
                }  
            }
            if(!flgExiste){
               resultado.setMsg("PRODUTO NÃO CADASTRADO");
            }
            else{
                 //Verifica os valores da quantidade
                if(transacao.getQtdeDoTipo() <= 0){
                     resultado.setMsg("TRANSACAO NÃO RESPEITA OS VALORES PERMITIDOS");
                }else{
                    //Produto produtoBuscado = (Produto)produtoDAO.consultar(transacao.getProduto()).get(0);
                    entEntrada.setProduto(produtoBuscado);
                    entEntrada.setQtdeTentativa(transacao.getProduto().getQuantidade());
                    entEntrada.setQtdeDisponivel(produtoBuscado.getTipoDeProduto().getQtdeMax() - produtoBuscado.getQuantidade());
                    entEntrada.setQtdeFutura(produtoBuscado.getQuantidade() + transacao.getProduto().getQuantidade());


                    if((transacao.getQtdeDoTipo() + produtoBuscado.getQuantidade()) > produtoBuscado.getTipoDeProduto().getQtdeMax())
                    {
                            resultado.setMsg("TENTATIVA DE ENTRADA NÃO RESPEITA OS LIMITES PERMITIDOS");
                            return resultado;
                    }
                    else{
                            entEntrada.setFlgValida(true);
                            resultado.setMsg(null);
                    }
                }
            }
            ArrayList<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
            entidades.add(0, entEntrada);
            resultado.setEntidades(entidades);
            return resultado;
        }
	
}

