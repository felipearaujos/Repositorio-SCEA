package scea.core.impl.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import scea.core.aplicacao.Resultado;

import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.core.impl.dao.AcessoDAO;
import scea.core.interfaces.IStrategy;

public class ValidarAcesso implements IStrategy{

	@Override
	public Resultado processar(EntidadeDominio entidade) {

		if(entidade instanceof Acesso)
		{
			Acesso usuario = (Acesso)entidade;
			Resultado r = new Resultado();
                        ArrayList<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                        //r.isAdmin = false;
                        //r.isLoginCorreto = false;
			if(usuario.getLogin() == null || usuario.getSenha() == null)
			{
                                r.setMsg("Preencha o usu�rio ou senha!");
				return r;
			}
			else
			{
				AcessoDAO DaoAcesso = new AcessoDAO();
				Acesso acessoBuscado = new Acesso();
				try {
					List<EntidadeDominio>listAcessoBuscado = DaoAcesso.consultar(usuario);
					if(listAcessoBuscado.size() == 0)
					{
                                            acessoBuscado.setId(0);
                                            r.setMsg("Login Incorreto!");
                                            entidades.add(0, acessoBuscado);
                                            r.setEntidades(entidades);

                                            return r;
					}
					
					acessoBuscado = (Acesso) listAcessoBuscado.get(0);
					
					if(usuario.getLogin().equals(acessoBuscado.getLogin()) && usuario.getSenha().equals(acessoBuscado.getSenha()))
					{
                                                
                                                
						
                                                if(usuario.getLogin().equals("admin"))
                                                {
                                                        acessoBuscado.setAdmin(true);
        						acessoBuscado.setLoginCorreto(true);
							//ArrayList<Acesso> ac = new ArrayList<Acesso>();
                                                        //ac.add(usuario);
                                        		entidades.add(0, acessoBuscado);
							r.setEntidades(entidades);
                                                        return r;                                                    
                                                }
                                                else
                                                {
                                                        acessoBuscado.setAdmin(false);
        						acessoBuscado.setLoginCorreto(true);
							//ArrayList<Acesso> ac = new ArrayList<Acesso>();
                                                        //ac.add(usuario);
                                        		entidades.add(0, acessoBuscado);
                                                        r.setEntidades(entidades);
                                                        return r;                                                        
                                                }
        					
					}
					else
					{
                                            if(usuario.getLogin().equals(acessoBuscado.getLogin()))
                                            {
                                                r.setMsg("Senha Incorreta!");
                                                entidades.add(0, acessoBuscado);
                                                r.setEntidades(entidades);						
                                                return r;
                                            }
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		return null;
	}

}
