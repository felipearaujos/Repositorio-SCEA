package scea.core.interfaces;

import java.sql.SQLException;

import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.EntidadeDominio;


public interface IFachada {
        //Crud
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	
        //Acesso
        public Resultado acessar(EntidadeDominio entidade);
	
	//Simulações
	public Resultado simular(EntidadeDominio entidade); 
               
        //Email
        public Resultado enviarEmail(EntidadeDominio entidade);
        

        
	
}
