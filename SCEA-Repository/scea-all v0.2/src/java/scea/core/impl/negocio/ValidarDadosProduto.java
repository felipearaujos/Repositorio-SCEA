package scea.core.impl.negocio;

import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.core.aplicacao.Resultado;
import scea.core.interfaces.IStrategy;

public class ValidarDadosProduto implements IStrategy {

    @Override
    public Resultado processar(EntidadeDominio entidade) {
		Produto produto = (Produto)entidade;
		StringBuilder sb = new StringBuilder();
		Resultado r = new Resultado();
		
		//Validando se nome,quantidade e valor foram preenchidos
		if((produto.getNome() == null || produto.getNome().equals("")) && 
		   (produto.getQuantidade() <= 0) && 
		   (produto.getValor() <= 0.0)){
			sb.append("Insira Nome, Quantidade e Valor validos");
		}
		
		
		else if((produto.getNome() == null || produto.getNome().equals("")) && 
				(produto.getQuantidade() <= 0)){
			sb.append("Insira o Nome do produto e uma quantidade Valida");
		}
				
		else if(produto.getNome() == null || produto.getNome().equals("")){
			sb.append("Insira o Nome do Produto");
		}
		
		else if(produto.getQuantidade() <= 0){
			sb.append("Insira uma quantidade v�lida");
		}
		
		else if(produto.getValor() <= 0.0){
			sb.append("Insira um valor valido");
		}
		/*else{
			return null;
                 //   r.setMsg(null);
		}*/
                String s = sb.toString();
                if (!s.equals("")) {
                    r.setMsg(sb.toString());
                }
                
		return r;
    }
}
