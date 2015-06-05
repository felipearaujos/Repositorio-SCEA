package scea.core.impl.negocio;

import java.util.Date;
import scea.core.aplicacao.Resultado;

import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Transacao;
import scea.core.interfaces.IStrategy;


public class ComplementarDtTransacao implements IStrategy {

	@Override
	public Resultado processar(EntidadeDominio entidade) {		
		Resultado r = new Resultado();
		Transacao transacao = (Transacao)entidade;
		if(transacao !=null){
			Date data = new Date();		
			transacao.setDtCadastro(data);
		}else{
                        r.setMsg("Entidade: "+entidade.getClass().getCanonicalName()+" nula!");
			return r;
		}
		
		
		
		return null;
	}

}
