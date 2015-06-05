package scea.core.testes.testesDAO;

import scea.core.impl.negocio.ValidarLimiteEntrada;
import scea.dominio.modelo.*;

public class testeValidadorLimiteEntrada {

	public void testeDeveTestarLimiteDeEntradaSemDadosDoBanco()
	{
		Transacao t = new Transacao();
		t.setQtdeDoTipo(150);
		
		ValidarLimiteEntrada validador = new ValidarLimiteEntrada();
		
		System.out.println(validador.processar(t));
		
	}
	
	public void testeDeveTestarLimiteDeEntradaComOBanco()
	{

		//Os valores aqui setados seriam os valores inseridos pelo usuario na View
		//O tipo por default ser� "" pois ser� necess�rio para fazer a busca apenas por ID
		Transacao t = new Transacao();
		Produto p = new Produto();
		p.setId(1);
		t.setProduto(p);
		TipoDeProduto tp = new TipoDeProduto();
		tp.setTipo("");
		t.getProduto().setTipoDeProduto(tp);
		t.setQtdeDoTipo(850);
		
		ValidarLimiteEntrada validador = new ValidarLimiteEntrada();
		
		System.out.println(validador.processar(t));
	}
}
