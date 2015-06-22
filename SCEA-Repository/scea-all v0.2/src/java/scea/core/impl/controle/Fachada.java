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
	
	/*public Fachada(){
            
		daos = new HashMap<String, IDAO>();
                rns = new HashMap<String, Map<String, List<IStrategy>>>();
                List<IStrategy> regrasProduto = new ArrayList<IStrategy>();
                
                
                
                
                entidadeFactory = new ProdutoFactory();
                entidadeDAOFactory = new ProdutoDAOFactory();
		daos.put(entidadeFactory.createEntidade().getClass().getName(), entidadeDAOFactory.createDAO());
                regrasProduto.add(new ValidarDadosProduto());	
                regrasProduto.add(new ValidarExistenciaFornecedor());
                regrasProduto.add(new ValidarExistenciaTipoDeProduto());
                Map<String, List<IStrategy>> rnsSalvarProduto = new HashMap<String, List<IStrategy>>();
		rnsSalvarProduto.put(entidadeFactory.createEntidade().getClass().getName(), regrasProduto);
                rns.put("SALVAR", rnsSalvarProduto);
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();			
		rns.put("ALTERAR", rnsProduto);
		rns.put("CONSULTAR", rnsProduto);
		rns.put("EXCLUIR", rnsProduto);
                
                
                
                
                entidadeFactory = new SimulacaoFactory();
                entidadeDAOFactory = new SimulacaoDAOFactory();
                daos.put(entidadeFactory.createEntidade().getClass().getName(), entidadeDAOFactory.createDAO());
                
                
                
                
                entidadeFactory = new TransacaoFactory();
                entidadeDAOFactory = new TransacaoDAOFactory();
		daos.put(entidadeFactory.createEntidade().getClass().getName(), entidadeDAOFactory.createDAO());
                List<IStrategy> regrasSimulacao = new ArrayList<IStrategy>();
		regrasSimulacao.add(new ValidaCampos());
                Map<String, List<IStrategy>> rnsSalvarSimulacao = new HashMap<String, List<IStrategy>>();
		rnsSalvarSimulacao.put(entidadeFactory.createEntidade().getClass().getName(), regrasSimulacao);
                
                
                
                entidadeFactory = new FornecedorFactory();
                entidadeDAOFactory = new FornecedorDAOFactory();
		daos.put(entidadeFactory.createEntidade().getClass().getName(), entidadeDAOFactory.createDAO());
                List<IStrategy> regrasFornecedor = new ArrayList<IStrategy>();
		regrasFornecedor.add(new ValidaCampos());
                Map<String, List<IStrategy>> rnsSalvarFornecedor = new HashMap<String, List<IStrategy>>();
		rnsSalvarFornecedor.put(entidadeFactory.createEntidade().getClass().getName(), regrasFornecedor);
                rns.put("SALVAR", rnsSalvarFornecedor);
                
                
                
                
                
                entidadeFactory = new AcessoFactory();
                entidadeDAOFactory = new AcessoDAOFactory();
		daos.put(entidadeFactory.createEntidade().getClass().getName(), entidadeDAOFactory.createDAO());
                List<IStrategy> regrasAcesso = new ArrayList<IStrategy>();
                regrasAcesso.add(new ValidaCampos());
                regrasAcesso.add(new ValidarAcesso());
		Map<String, List<IStrategy>> rnsSalvarAcesso = new HashMap<String, List<IStrategy>>();
		rnsSalvarAcesso.put(entidadeFactory.createEntidade().getClass().getName(), regrasAcesso);
		Map<String, List<IStrategy>> rnsConsultarAcesso = new HashMap<String, List<IStrategy>>();
		rnsConsultarAcesso.put(entidadeFactory.createEntidade().getClass().getName(), regrasAcesso);
		rns.put("CONSULTAR", rnsConsultarAcesso);
                rns.put("SALVAR", rnsSalvarAcesso);

       
			
	}//Fachada
	*/
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
                daos.put(Simulacao.class.getName(), new SimulacaoDAO());
		
                entidadeDAOFactory = new TransacaoDAOFactory();
                daos.put(Transacao.class.getName(), new TransacaoDAO());
		
                entidadeDAOFactory = new FornecedorDAOFactory();
                daos.put(Fornecedor.class.getName(), new FornecedorDAO());
		
                entidadeDAOFactory = new AcessoDAOFactory();
                daos.put(Acesso.class.getName(), new AcessoDAO());
                
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
		regrasFornecedor.add(new ValidaCampos());
                
                //TipoDeProduto
		List<IStrategy> regrasTipoDeProduto = new ArrayList<IStrategy>();
		regrasFornecedor.add(new ValidarTipoDeProduto());
                
                
                // Simulacao
		List<IStrategy> regrasSimulacao = new ArrayList<IStrategy>();
		regrasSimulacao.add(new ValidaCampos());
                
		//Acesso
                List<IStrategy> regrasAcesso = new ArrayList<IStrategy>();
                regrasAcesso.add(new ValidaCampos());
                regrasAcesso.add(new ValidarAcesso());
		
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
                
                
                List<IStrategy> RegrasTransacao = new ArrayList<IStrategy>();
                RegrasTransacao.add(new ValidarTransacao());
                Map<String, List<IStrategy>> rnsSalvarTransacao = new HashMap<String, List<IStrategy>>();
                rnsSalvarTransacao.put(Transacao.class.getName(), RegrasTransacao);
                rns.put("SALVAR", rnsSalvarTransacao);
                
      
			
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
	public Resultado visualizar(EntidadeDominio entidade) {

		Resultado resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);		
		return resultado;
		
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
		ValidarAcesso validador = new ValidarAcesso();
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
    
    
    /*
        @Override
        public Resultado transacoesPeriodo(EntidadeDominio entidade) {
           RelatoriosDAO dao = new RelatoriosDAO();
           EntidadeRelatorio r =  (EntidadeRelatorio)entidade;
           resultado = new Resultado();        
           resultado.setEntidades(dao.relatorioTransacoes(r));
           return resultado;
        }
        
        @Override
        public Resultado transacoesProdPeriodo(EntidadeDominio entidade) {
            RelatoriosDAO dao = new RelatoriosDAO();
            EntidadeRelatorio r =  (EntidadeRelatorio)entidade;
            resultado = new Resultado();        
            resultado.setEntidades(dao.relatorioTransacoesProduto(r));
            return resultado;
        }
        
        @Override
        public Resultado relatorioEstoque(EntidadeDominio entidade) {
            RelatoriosDAO dao = new RelatoriosDAO();
            EntidadeRelatorio r =  (EntidadeRelatorio)entidade;
            resultado = new Resultado();        
            resultado.setEntidades(dao.relatorioSituacaoEstoque(r));
            return resultado;
        }
        
       @Override
        public Resultado RelatorioInicial(EntidadeDominio entidade) {

            resultado = new Resultado();
            Produto a = (Produto) entidade;

            RelatoriosDAO relatorioDAO = new RelatoriosDAO();
            resultado.setEntidades(relatorioDAO.relatorioInicial(entidade));

            return resultado;
        }
   */



   

    



}
