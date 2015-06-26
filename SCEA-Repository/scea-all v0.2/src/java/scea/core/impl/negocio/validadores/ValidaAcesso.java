package scea.core.impl.negocio.validadores;

import scea.core.aplicacao.Resultado;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;

public class ValidaAcesso implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        Resultado r = new Resultado();
        
        
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
        
        return null;
    }
}
