package scea.core.impl.controle;


import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.mail.Email;
import scea.core.aplicacao.*;
import scea.core.aplicacao.relatorio.*;
import scea.core.factories.dao.*;
import scea.core.impl.dao.*;
import scea.core.impl.handlers.RelatorioDetalheInicialHandler;
import scea.core.impl.handlers.RelatorioSituacaoEstoqueHandler;
import scea.core.impl.handlers.RelatorioTransacoesHandler;
import scea.core.impl.handlers.RelatorioTransacoesProdutoHandler;
import scea.core.impl.handlers.TelaInicialHandler;
import scea.core.impl.negocio.*;
import scea.core.impl.negocio.validadores.*;
import scea.core.interfaces.Factories.*;
import scea.core.interfaces.*;
import scea.dominio.modelo.*;



public class Fachada implements IFachada {

private Map<String, IDAO> daos;
	
	//private Map<String, List<IStrategy>> rns;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado resultado = new Resultado();
        IEntidadeFactory entidadeFactory;
        IEntidadeDAOFactory entidadeDAOFactory;
	RelatorioDetalheInicialHandler handlerRelDetalheInicial;
        
        public Fachada(){
		daos = new HashMap<String, IDAO>();
		/*
                daos.put(Produto.class.getName(), new ProdutoDAO());
                daos.put(Simulacao.class.getName(), new SimulacaoDAO());
		daos.put(Transacao.class.getName(), new TransacaoDAO());
		daos.put(Fornecedor.class.getName(), new FornecedorDAO());
		daos.put(Acesso.class.getName(), new AcessoDAO());
		*/
                entidadeDAOFactory = new ProdutoDAOFactory();
		daos.put(Produto.class.getName(), entidadeDAOFactory.createDAO() );
                
                entidadeDAOFactory = new SimulacaoDAOFactory();
                daos.put(Simulacao.class.getName(), entidadeDAOFactory.createDAO());
		
                entidadeDAOFactory = new TransacaoDAOFactory();
                daos.put(Transacao.class.getName(), entidadeDAOFactory.createDAO());
                daos.put(Entrada.class.getName(), entidadeDAOFactory.createDAO());
                daos.put(Saida.class.getName(), entidadeDAOFactory.createDAO());
		
                entidadeDAOFactory = new FornecedorDAOFactory();
                daos.put(Fornecedor.class.getName(), entidadeDAOFactory.createDAO());
		
                entidadeDAOFactory = new AcessoDAOFactory();
                daos.put(Acesso.class.getName(),entidadeDAOFactory.createDAO());
                
                entidadeDAOFactory = new TipoDeProdutoDAOFactory();
                daos.put(TipoDeProduto.class.getName(), entidadeDAOFactory.createDAO());
                
                entidadeDAOFactory = new RelatorioDAOFactory();
                daos.put(EntidadeRelatorio.class.getName(), entidadeDAOFactory.createDAO());
                

                rns = new HashMap<String, Map<String, List<IStrategy>>>();
                
                // Produto
		List<IStrategy> regrasProduto = new ArrayList<IStrategy>();
		regrasProduto.add(new ValidarDadosProduto());	
                regrasProduto.add(new ValidarExistenciaFornecedor());
                regrasProduto.add(new ValidarExistenciaTipoDeProduto());
                
                //Fornecedor
		List<IStrategy> regrasFornecedor = new ArrayList<IStrategy>();
		regrasFornecedor.add(new ValidarDadosFornecedor());
                
                //TipoDeProduto
		List<IStrategy> regrasTipoDeProduto = new ArrayList<IStrategy>();
		regrasFornecedor.add(new ValidarTipoDeProduto());
                
                
                // Simulacao
		List<IStrategy> regrasSimulacao = new ArrayList<IStrategy>();
		regrasSimulacao.add(new ValidaCampos());
                
		//Acesso
                List<IStrategy> regrasAcesso = new ArrayList<IStrategy>();
                regrasAcesso.add(new ValidaAcesso());
                regrasAcesso.add(new ValidarAutenticacao());
		
		Map<String, List<IStrategy>> rnsSalvarAcesso = new HashMap<String, List<IStrategy>>();
		rnsSalvarAcesso.put(Acesso.class.getName(), regrasAcesso);
                
                //Simulacao
		Map<String, List<IStrategy>> rnsSalvarSimulacao = new HashMap<String, List<IStrategy>>();
		rnsSalvarSimulacao.put(Simulacao.class.getName(), regrasSimulacao);
                
                
		Map<String, List<IStrategy>> rnsConsultarAcesso = new HashMap<String, List<IStrategy>>();
		rnsConsultarAcesso.put(Acesso.class.getName(), regrasAcesso);
                
		rns.put("CONSULTAR", rnsConsultarAcesso);
                rns.put("SALVAR", rnsSalvarAcesso);
		
                Map<String, List<IStrategy>> rnsSalvarFornecedor = new HashMap<String, List<IStrategy>>();
		rnsSalvarFornecedor.put(Fornecedor.class.getName(), regrasFornecedor);
                rns.put("SALVAR", rnsSalvarFornecedor);
                
                
                Map<String, List<IStrategy>> rnsSalvarProduto = new HashMap<String, List<IStrategy>>();
		rnsSalvarProduto.put(Produto.class.getName(), regrasProduto);
                rns.put("SALVAR", rnsSalvarProduto);
		
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		//rnsAltAluno.put(Aluno.class.getName(), regrasAltAlunos);				
		rns.put("ALTERAR", rnsProduto);
		rns.put("CONSULTAR", rnsProduto);
		rns.put("EXCLUIR", rnsProduto);
                
                
                Map<String, List<IStrategy>> rnsSalvarTipoDeProduto = new HashMap<String, List<IStrategy>>();
		rnsSalvarTipoDeProduto.put(TipoDeProduto.class.getName(), regrasTipoDeProduto);
                rns.put("SALVAR", rnsSalvarTipoDeProduto);
                
                
                //List<IStrategy> RegrasTransacao = new ArrayList<IStrategy>();
              
                Map<String, List<IStrategy>> rnsSalvarTransacao = new HashMap<String, List<IStrategy>>();

                
                
                
                List<IStrategy> RegrasEntrada = new ArrayList<IStrategy>();
                RegrasEntrada.add(new ValidarLimiteEntrada());
                RegrasEntrada.add(new ComplementarDtTransacao());
                rnsSalvarTransacao.put(Entrada.class.getName(), RegrasEntrada);
                rns.put("SALVAR", rnsSalvarTransacao);
                
                List<IStrategy> RegrasSaida = new ArrayList<IStrategy>();
                RegrasSaida.add(new ValidarLimiteSaida());
                RegrasSaida.add(new ComplementarDtTransacao());
                rnsSalvarTransacao.put(Saida.class.getName(), RegrasSaida);
                rns.put("SALVAR", rnsSalvarTransacao);
                

                handlerRelDetalheInicial = new RelatorioDetalheInicialHandler();
                RelatorioSituacaoEstoqueHandler handlerRelSituacaoEstoque = new RelatorioSituacaoEstoqueHandler();
                RelatorioTransacoesHandler handlerRelTransacoes = new RelatorioTransacoesHandler();
                RelatorioTransacoesProdutoHandler handlerRelTransacoesProduto = new RelatorioTransacoesProdutoHandler();
                TelaInicialHandler handlerTelaInicial = new TelaInicialHandler();
                
                handlerRelDetalheInicial.setSucessor(handlerRelSituacaoEstoque);
                handlerRelSituacaoEstoque.setSucessor(handlerRelTransacoes);
                handlerRelTransacoes.setSucessor(handlerRelTransacoesProduto);
                handlerRelTransacoesProduto.setSucessor(handlerTelaInicial);
	}//Fachada
	
	public Resultado salvar(EntidadeDominio entidade) {
            resultado = new Resultado();
            String nmClasse = entidade.getClass().getName();	

            String msg = executarRegras(entidade, "SALVAR");

            if(msg == null){
                IDAO dao = daos.get(nmClasse);
                try {
                    dao.salvar(entidade);
                    List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                    entidades.add(entidade);
                    resultado.setEntidades(entidades);
                } catch (SQLException e) {
                    e.printStackTrace();
                    resultado.setMsg("Nao foi possivel realizar o registro!");				
                }
            }else{
                resultado.setMsg(msg);


            }

            return resultado;
	}

	
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");

		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Nao foi possovel alterar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}//Alterar

	
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Nao foi possivel realizar o registro!");
						}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}//EXCLUIR


	
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		StringBuilder msg = new StringBuilder();
		
			if(msg.length() == 0){
			IDAO dao = daos.get(nmClasse);
			try {
				if(dao.getClass().getName() == RelatoriosDAO.class.getName())
                                    resultado.setEntidades(handlerRelDetalheInicial.processarRequisicao(entidade));
                                else
                                    resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Nao foi possivel realizar  a consulta!");
				
			}
		}else{
			resultado.setMsg(msg.toString());		
		}
		
		return resultado;

	}
	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		Resultado r = new Resultado();
		Map<String, List<IStrategy>> regrasOperacao = rns.get(operacao);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(nmClasse);
			
			if(regras != null){
				for(IStrategy s: regras){
                                        
					Resultado re = s.processar(entidade);			
					
					if(re.getMsg() != null){
						msg.append(re.getMsg());
						msg.append("\n");
                                                r.setMsg(r.getMsg() + re.getMsg());
					}			
				}	
			}			
			
		}
		
		if(msg.length()>0)
                {
                        r.setMsg(msg.toString());
                        return r.getMsg();
                }
                else
                {
                    return null;
                }
	}


	@Override
	public Resultado simular(EntidadeDominio entidade) {
		//Resultado resultado = new Resultado();
                 resultado = new Resultado();
		Transacao t = (Transacao) entidade;
		Produto p = t.getProduto();
		
		SimularEstoque validador = new SimularEstoque();
		
		resultado = validador.processar(p);
		//resultado.setMsg(msg);
		
		return resultado;
		
	}


	@Override
	public Resultado acessar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
		Acesso a = (Acesso) entidade;
		ValidarAutenticacao validador = new ValidarAutenticacao();
		//Resultado r = validador.processar(a);
		resultado = validador.processar(a);
                return resultado;
		}

        
 
        
        @Override
        public Resultado enviarEmail(EntidadeDominio entidade) {

            EmailAplicacao emailEnviado = (EmailAplicacao) entidade;
            EnviarEmail validador = new EnviarEmail();
            resultado = new Resultado();            
            resultado = validador.processar(emailEnviado);

            return resultado;
        }

}
