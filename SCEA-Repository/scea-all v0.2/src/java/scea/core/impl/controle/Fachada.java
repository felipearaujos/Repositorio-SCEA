package scea.core.impl.controle;


import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.mail.Email;
import scea.core.aplicacao.EmailAplicacao;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.impl.dao.AcessoDAO;
import scea.core.impl.dao.FornecedorDAO;
import scea.core.impl.dao.ProdutoDAO;
import scea.core.impl.dao.RelatoriosDAO;
import scea.core.impl.dao.SimulacaoDAO;
import scea.core.impl.dao.TransacaoDAO;
import scea.core.impl.negocio.EnviarEmail;
import scea.core.impl.negocio.SimularEstoque;
import scea.core.impl.negocio.ValidaCampos;
import scea.core.impl.negocio.ValidarAcesso;
import scea.core.impl.negocio.ValidarDadosProduto;
import scea.core.impl.negocio.ValidarExistenciaFornecedor;
import scea.core.impl.negocio.ValidarExistenciaTipoDeProduto;
import scea.core.impl.negocio.ValidarLimiteEntrada;
import scea.core.impl.negocio.ValidarLimiteSaida;
import scea.core.interfaces.IDAO;
import scea.core.interfaces.IFachada;
import scea.core.interfaces.IStrategy;
import scea.core.interfaces.ITransacao;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Simulacao;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;



public class Fachada implements IFachada {

private Map<String, IDAO> daos;
	
	//private Map<String, List<IStrategy>> rns;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado resultado = new Resultado();
	
	public Fachada(){
		daos = new HashMap<String, IDAO>();
		daos.put(Produto.class.getName(), new ProdutoDAO());
                daos.put(Simulacao.class.getName(), new SimulacaoDAO());
		daos.put(Transacao.class.getName(), new TransacaoDAO());
		daos.put(Fornecedor.class.getName(), new FornecedorDAO());
		daos.put(Acesso.class.getName(), new AcessoDAO());
		
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		List<IStrategy> regrasProduto = new ArrayList<IStrategy>();
		regrasProduto.add(new ValidarDadosProduto());	
                regrasProduto.add(new ValidarExistenciaFornecedor());
                regrasProduto.add(new ValidarExistenciaTipoDeProduto());
                
		List<IStrategy> regrasFornecedor = new ArrayList<IStrategy>();
		regrasFornecedor.add(new ValidaCampos());
                
                
		List<IStrategy> regrasSimulacao = new ArrayList<IStrategy>();
		regrasSimulacao.add(new ValidaCampos());
                
		
                List<IStrategy> regrasAcesso = new ArrayList<IStrategy>();
                regrasAcesso.add(new ValidaCampos());
                regrasAcesso.add(new ValidarAcesso());
		
		Map<String, List<IStrategy>> rnsSalvarAcesso = new HashMap<String, List<IStrategy>>();
		rnsSalvarAcesso.put(Acesso.class.getName(), regrasAcesso);
                
                
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
                
                
                
                
                /* ALTERA��O 
                List<IStrategy> regrasProduto = new ArrayList<IStrategy>();
		regrasProduto.add(new ValidarDadosProduto());
		//regrasProduto.add(new ValidadorCurso());
		//regrasProduto.add(new ValidarEndereco());
		
		
		Map<String, List<IStrategy>> rnsSalvarProduto = new HashMap<String, List<IStrategy>>();		
		rnsSalvarProduto.put(Produto.class.getName(), regrasProduto);				
		rns.put("SALVAR", rnsSalvarProduto);
		
		
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		//rnsAltAluno.put(Aluno.class.getName(), regrasAltAlunos);				
		rns.put("ALTERAR", rnsProduto);
		rns.put("CONSULTAR", rnsProduto);
		rns.put("EXCLUIR", rnsProduto);
		*/
			
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
        public Resultado RelatorioInicial(EntidadeDominio entidade) {

            resultado = new Resultado();
            Produto a = (Produto) entidade;

            ProdutoDAO produtoDAO = new ProdutoDAO();
            resultado.setEntidades(produtoDAO.consultarRelatorioInicial(entidade));

            return resultado;
        }
   
        
        @Override
        public Resultado enviarEmail(EntidadeDominio entidade) {

            EmailAplicacao emailEnviado = (EmailAplicacao) entidade;
            EnviarEmail validador = new EnviarEmail();
            //Resultado r = new Resultado();
            resultado = new Resultado();
            //r = validador.processar(emailEnviado);
            resultado = validador.processar(emailEnviado);
        //return r;
            return resultado;
        }
    
    
    
        @Override
        public Resultado transacoesPeriodo(EntidadeDominio entidade) {
           RelatoriosDAO dao = new RelatoriosDAO();
           EntidadeRelatorio r =  (EntidadeRelatorio)entidade;
           resultado = new Resultado();        
           resultado.setEntidades(dao.consultarRelTransacoesPeriodo(r));
           return resultado;
        }
        
        @Override
        public Resultado transacoesProdPeriodo(EntidadeDominio entidade) {
            RelatoriosDAO dao = new RelatoriosDAO();
            EntidadeRelatorio r =  (EntidadeRelatorio)entidade;
            resultado = new Resultado();        
            resultado.setEntidades(dao.consultarTransacoesProdPeriodo(r));
            return resultado;
        }
        
        @Override
        public Resultado relatorioEstoque(EntidadeDominio entidade) {
            RelatoriosDAO dao = new RelatoriosDAO();
            EntidadeRelatorio r =  (EntidadeRelatorio)entidade;
            resultado = new Resultado();        
            resultado.setEntidades(dao.consultarRelArmazenamentoEstoque(r));
            return resultado;
        }
        
        @Override
        public Resultado entrada(EntidadeDominio entidade) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Resultado saida(EntidadeDominio entidade) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }



   

    



}
