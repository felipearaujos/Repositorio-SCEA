package scea.core.impl.controle;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.AcessoDAO;
import scea.core.impl.dao.FornecedorDAO;
import scea.core.impl.dao.TransacaoDAO;
import scea.core.impl.dao.ProdutoDAO;
import scea.core.impl.negocio.ComplementarDtTransacao;
import scea.core.impl.negocio.SimularEstoque;
import scea.core.impl.negocio.ValidarAcesso;
import scea.core.impl.negocio.ValidarLimiteEntrada;
import scea.core.impl.negocio.ValidarLimiteSaida;
import scea.core.interfaces.IDAO;
import scea.core.interfaces.IFachada;
import scea.core.interfaces.IStrategy;
import scea.core.interfaces.ITransacao;



public class FachadaTransacao extends Fachada {

private Map<String, IDAO> daos;
	
	//private Map<String, List<IStrategy>> rns;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado resultado = new Resultado();
	
	public FachadaTransacao(){
		daos = new HashMap<String, IDAO>();
		daos.put(Produto.class.getName(), new ProdutoDAO());
		
		daos.put(Transacao.class.getName(), new TransacaoDAO());
		daos.put(Fornecedor.class.getName(), new FornecedorDAO());
		daos.put(Acesso.class.getName(), new AcessoDAO());
		
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		List<IStrategy> regrasTransacao = new ArrayList<IStrategy>();
		//regrasTransacao.add(new ComplementarDtTransacao());
		//regrasProduto.add(new ValidadorCurso());
		//regrasProduto.add(new ValidarEndereco());
		
		//List<IStrategy> regrasAcesso = new ArrayList<IStrategy>();
		//regrasAcesso.add(new ValidarAcesso());
		
		//Map<String, List<IStrategy>> rnsConsultarAcesso = new HashMap<String, List<IStrategy>>();
		//rnsConsultarAcesso.put(Acesso.class.getName(), regrasAcesso);
		
		//rns.put("CONSULTAR", rnsConsultarAcesso);
		
		
		//Map<String, List<IStrategy>> rnsSalvarTransacao = new HashMap<String, List<IStrategy>>();		
		//rnsSalvarTransacao.put(Transacao.class.getName(), regrasTransacao);				
		//rns.put("SALVAR", rnsSalvarTransacao);
		
		
		Map<String, List<IStrategy>> rnsTransacao = new HashMap<String, List<IStrategy>>();
		//rnsAltAluno.put(Aluno.class.getName(), regrasAltAlunos);				
		rnsTransacao.put(Transacao.class.getName(), regrasTransacao);
		rns.put("ENTRADA", rnsTransacao);
		rns.put("SAIDA", rnsTransacao);
			
	}//Fachada
	
	
	public Resultado entrada(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		Transacao t = (Transacao) entidade;
		
		ValidarLimiteEntrada validador = new ValidarLimiteEntrada();
		
		//String msg = validador.processar(t);
		resultado = validador.processar(t);
		if(resultado.getMsg() == null)
		{
			// ira realizar a insercao na DAOTransacao
			TransacaoDAO trDAO = new TransacaoDAO();
			try {
				trDAO.entrar(entidade);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//resultado = new ResultadoEstoque();
			//resultado.setMsg(null);
		}
		/*else
		{
			resultado.setMsg("ERRO");
		}*/
		return resultado;
		
	}

	
	@Override
	public Resultado saida(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		Transacao t = (Transacao) entidade;
		
		
		ValidarLimiteSaida validador = new ValidarLimiteSaida();
		
		//String msg = validador.processar(t);
		
		//if(msg == null)
		//{
		resultado = validador.processar(t);
		if(resultado.getMsg() == null)
		{
			// ira realizar a insercao na DAOTransacao
			TransacaoDAO trDAO = new TransacaoDAO();
			try {
				trDAO.sair(entidade);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultado;
	}

	
	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(operacao);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(nmClasse);
			Resultado r = new Resultado();
			if(regras != null){
				for(IStrategy s: regras){			
					Resultado re = s.processar(entidade);			
					
					if(re.getMsg() != null){
						msg.append(re.getMsg());
						msg.append("\n");
                                                r.setMsg(r.getMsg()+ re.getMsg());
					}			
				}	
			}			
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
        	}
                return null;

        }
    }

