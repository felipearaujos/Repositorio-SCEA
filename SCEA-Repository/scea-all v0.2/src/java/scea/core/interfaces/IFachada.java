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
	public Resultado visualizar(EntidadeDominio entidade);
	
        //Acesso
        public Resultado acessar(EntidadeDominio entidade);
	
	//Estoque
	public Resultado entrada(EntidadeDominio entidade); 
	public Resultado saida(EntidadeDominio entidade); 
	
	//Simulações
	public Resultado simular(EntidadeDominio entidade); 
        
       
        //Email
        public Resultado enviarEmail(EntidadeDominio entidade);
        
        //Relatorio
        public Resultado RelatorioInicial(EntidadeDominio entidade);
        public Resultado transacoesPeriodo(EntidadeDominio entidade);
        public Resultado transacoesProdPeriodo(EntidadeDominio entidade);
        public Resultado relatorioEstoque(EntidadeDominio entidade);
        
	
}
