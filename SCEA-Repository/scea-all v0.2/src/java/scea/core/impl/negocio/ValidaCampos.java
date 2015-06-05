package scea.core.impl.negocio;

import scea.core.aplicacao.Resultado;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;

public class ValidaCampos implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        Resultado r = new Resultado();
        if(entidade instanceof Fornecedor)
        {
            Fornecedor f = (Fornecedor)entidade;
            if(f.getCNPJ().equals("") || f.getNome().equals("") || f.getNomeFantasia().equals("") || f.getRazaoSocial().equals("") 
                    || f.getEmail().equals("") || f.getTelefone().getNumero().equals(""))
            {
                 r.setMsg("Preencha todos os campos obrigatórios!");
                 return r;
            }
            else
            {
                return r;
            }
        }
        
        if(entidade instanceof Acesso)
        {
            Acesso a = (Acesso)entidade;
            if(a.getLogin().equals("") || a.getSenha().equals(""))
            {
                 r.setMsg("Preencha todos os campos obrigatórios!");
                 return r;
            }
            else
            {
                return r;
            }
        }
        
        if(entidade instanceof Produto)
        {
            Produto p = (Produto)entidade;
            if(p.getNome().equals("") || p.getTipoDeProduto().getTipo().equals(null) ||
                    p.getTipoDeProduto().getDescricao().equals(null))
            {
                 r.setMsg("Preencha todos os campos obrigatórios!");
                 return r;
            }
            else
            {
                return r;
            }
        }
        
        return null;
    }
}
