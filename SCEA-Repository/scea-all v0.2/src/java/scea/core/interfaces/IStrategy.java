package scea.core.interfaces;

import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.EntidadeDominio;




public interface IStrategy {

	public Resultado processar(EntidadeDominio entidade);
	
}
