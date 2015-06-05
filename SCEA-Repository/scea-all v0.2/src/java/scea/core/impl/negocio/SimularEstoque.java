package scea.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;
import scea.core.aplicacao.Estoque;




import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.ProdutoDAO;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Transacao;

public class SimularEstoque {
	
	public Resultado processar(EntidadeDominio entidade)
	{
			Resultado resultado = new Resultado();
			Estoque entSimulacao = new Estoque();
			Produto produto = (Produto)entidade;
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoBuscado = (Produto)produtoDAO.consultar(produto).get(0);
			
			
			entSimulacao.setProduto(produtoBuscado);
			//entSimulacao.setQtdeAtual(produtoBuscado.getQuantidade());
			entSimulacao.setQtdeTentativa(produto.getQuantidade());

			if((produtoBuscado.getQuantidade() + produto.getQuantidade()) <= produtoBuscado.getTipoDeProduto().getQtdeMax()){
				entSimulacao.setFlgValida(true);
				entSimulacao.setQtdeFutura((produtoBuscado.getQuantidade() + produto.getQuantidade()));
				entSimulacao.setQtdeDisponivel((produtoBuscado.getTipoDeProduto().getQtdeMax() - (produtoBuscado.getQuantidade() + produto.getQuantidade())));
                                
                        }
			else{
				entSimulacao.setFlgValida(false);
				entSimulacao.setQtdeDisponivel((produtoBuscado.getTipoDeProduto().getQtdeMax() - produtoBuscado.getQuantidade()));
			}
			
			
			ArrayList<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(0, entSimulacao);
			resultado.setEntidades(entidades);
		return resultado;
	}
}