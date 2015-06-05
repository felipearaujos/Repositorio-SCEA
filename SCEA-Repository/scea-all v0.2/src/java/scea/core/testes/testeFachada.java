package scea.core.testes;

import java.sql.SQLException;

import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;
import scea.core.aplicacao.Resultado;
import scea.core.impl.controle.Fachada;
import scea.core.impl.negocio.ValidarLimiteEntrada;

public class testeFachada {

	public void testaMetodosEntradaESaidaDaFachadaParaOsValidadores() throws SQLException
	{
		Fachada f = new Fachada();
		//ValidarLimiteEntrada f = new ValidarLimiteEntrada();
		//Vindo da View, essa condicao passará no validador de acordo com os dados do banco
		//Transacao t = new Transacao();
		//Produto p = new Produto();
		 Acesso ac = new Acesso();
		//ac.setId(1);
		
		ac.setLogin("admins");
		ac.setSenha("1234");
		//t.setTipoDeTransacao("ENTRADA");
		//t.setProduto(new Produto());
		//t.getProduto().setId(1);
		
		
		
		//t.setAcesso(new Acesso());
		//t.getAcesso().setId(1);
		
		
		//TipoDeProduto tp = new TipoDeProduto();
		//tp.setTipo("");
		//t.getProduto().setTipoDeProduto(new TipoDeProduto());
		//t.getProduto().getTipoDeProduto().setTipo("");
		//t.setQtdeDoTipo(70);
		//t.getProduto().setQuantidade(10);
		
		
		//TransacaoDAO d = new TransacaoDAO();
		
		//
		Resultado r = new Resultado();
	//	d.entrada(t);
		//f.entrada(t);
		//d.entrar(t);
		r =	f.consultar(ac); 
		
		
		System.out.println(r.getMsg());
		
		/*if(r != null){
			System.out.println(((ResultadoEstoque)r).getQtdeAtual());
			System.out.println(((ResultadoEstoque)r).getQtdeMax());
			System.out.println(((ResultadoEstoque)r).getQtdeDisponivel());
			System.out.println(((ResultadoEstoque)r).isFlgEntradaValida());
		}
		else{
			System.out.println("nulo");
			
		}
		*/
		//Vindo da View, essa condicao não passará no validador de acordo com os dados do banco
		//t.setTipoDeTransacao("ENTRADA");
		//t.setQtdeDoTipo(350);
		
		//Instancia resultado apenas para mostrar a mensagem
		// r = new Resultado();
		//r =	f.entrada(t); 
		//System.out.println(r.getMsg());
		

		//r =	f.Saida(t); 
		//System.out.println(r.getMsg());
		
	}
}
