package scea.core.testes.testesDAO;

import scea.core.impl.negocio.ValidarLimiteEntrada;
import scea.core.impl.negocio.ValidarLimiteSaida;
import scea.dominio.modelo.*;

public class testeValidadorLimiteSaida {

	public void testeDeveTestarLimiteDeSaidaSemDadosDoBanco()
	{
		Transacao t = new Transacao();
		t.setQtdeDoTipo(150);
		
		ValidarLimiteSaida validador = new ValidarLimiteSaida();
		
		System.out.println(validador.processar(t));
		
	}	
	
	public void testeDeveTestarLimiteDeSaidaCOmOBanco()
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
		
		ValidarLimiteSaida validador = new ValidarLimiteSaida();
		
		System.out.println(validador.processar(t));
	}
}
