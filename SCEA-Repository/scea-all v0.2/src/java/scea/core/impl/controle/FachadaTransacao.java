package scea.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.*;

import scea.core.impl.negocio.ComplementarDtTransacao;
import scea.core.impl.negocio.RealizarEntrada;
import scea.core.impl.negocio.SimularEstoque;
import scea.core.impl.negocio.validadores.*;
import scea.core.interfaces.*;
import scea.dominio.modelo.*;

public class FachadaTransacao extends Fachada {

    private Map<String, IDAO> daos;

    //private Map<String, List<IStrategy>> rns;
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Resultado resultado = new Resultado();

    public FachadaTransacao() {
        daos = new HashMap<String, IDAO>();
        daos.put(Transacao.class.getName(), new TransacaoDAO());
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        List<IStrategy> RegrasTransacao = new ArrayList<IStrategy>();
        RegrasTransacao.add(new ValidarTransacao());
        Map<String, List<IStrategy>> rnsSalvarTransacao = new HashMap<String, List<IStrategy>>();
        rnsSalvarTransacao.put(Transacao.class.getName(), RegrasTransacao);
        rns.put("SALVAR", rnsSalvarTransacao);

    }//Fachada

    public Resultado entrada(EntidadeDominio entidade) {
        Resultado resultado = new Resultado();
        Transacao t = (Transacao) entidade;

        ValidarLimiteEntrada validador = new ValidarLimiteEntrada();

        //String msg = validador.processar(t);
        resultado = validador.processar(t);
        if (resultado.getMsg() == null) {
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
        if (resultado.getMsg() == null) {
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

    private String executarRegras(EntidadeDominio entidade, String operacao) {
        String nmClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(operacao);

        if (regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(nmClasse);
            Resultado r = new Resultado();
            if (regras != null) {
                for (IStrategy s : regras) {
                    Resultado re = s.processar(entidade);

                    if (re.getMsg() != null) {
                        msg.append(re.getMsg());
                        msg.append("\n");
                        r.setMsg(r.getMsg() + re.getMsg());
                    }
                }
            }

            if (msg.length() > 0) {
                return msg.toString();
            } else {
                return null;
            }
        }
        return null;

    }

    public Resultado salvar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "SALVAR");

        if (msg == null) {
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
        } else {
            resultado.setMsg(msg);

        }

        return resultado;
    }

}
