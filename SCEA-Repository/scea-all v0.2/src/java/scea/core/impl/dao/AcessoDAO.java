package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.Cidade;
import scea.dominio.modelo.Endereco;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Estado;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Telefone;

public class AcessoDAO extends AbstractJdbcDAO{
	
	public AcessoDAO() {
		super("tb_acesso", "id_Acesso");		
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {

		PreparedStatement pst = null;
		
		Acesso usuario = (Acesso)entidade;
		
		String sql=null;
		
			sql = "INSERT INTO tb_acesso (usuario, senha)"
					+ " VALUES(?,?);";
						
	
	try {
		openConnection();
		
		//Inserindo Telefone	
		pst = connection.prepareStatement(sql);
		
		pst.setString(1, usuario.getLogin());
		pst.setString(2, usuario.getSenha());
		pst.executeUpdate();
		
		
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		String sql = null;
		Acesso usuario = (Acesso)entidade;
		

		openConnection();
		
		sql = "UPDATE tb_acesso SET senha = ? WHERE usuario = ?";
		pst = connection.prepareStatement(sql);
		pst.setString(1, usuario.getSenha());
		pst.setString(2, usuario.getLogin());

		pst.executeUpdate();
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		String sql = null;
		
		Acesso usuario = (Acesso)entidade;
		
		
		sql = "SELECT id_acesso, usuario FROM tb_acesso WHERE id_acesso = ?;";
		
		if(usuario.getId() == null && (!usuario.getLogin().equals(""))){
			sql = "SELECT id_acesso, usuario FROM tb_acesso WHERE usuario = ?;";
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(usuario.getId() != null){
				pst.setInt(1, usuario.getId());
			}else if(usuario.getId() == null && (!usuario.getLogin().equals(""))){
				pst.setString(1, usuario.getLogin());	
			}
			

			ResultSet rs = pst.executeQuery();

				Acesso acessoBuscado = new Acesso();
				
					while(rs.next()){
					acessoBuscado.setId(rs.getInt("id_acesso"));
					acessoBuscado.setLogin(rs.getString("usuario"));
					
					sql = "DELETE FROM tb_acesso WHERE id_acesso = ?";
					pst = connection.prepareStatement(sql);
					pst.setInt(1, acessoBuscado.getId());
	
					pst.executeUpdate();
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)
			throws SQLException {

		PreparedStatement pst = null;
		
		Acesso usuario = (Acesso)entidade;
		String sql=null;
		if(usuario.getLogin().equals(""))
                    sql = "SELECT * FROM tb_acesso";
                else
		sql = "SELECT * FROM tb_acesso WHERE usuario LIKE ?";
	
	
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		
		if(!usuario.getLogin().equals(""))
                    pst.setString(1, usuario.getLogin());
		ResultSet rs = pst.executeQuery();
		
		
		List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
			Acesso user = new Acesso();
                        user.setId(rs.getInt("id_acesso"));
			user.setLogin(rs.getString("usuario"));
			user.setSenha(rs.getString("senha"));
			
			
			usuarios.add(user);
		}
		return usuarios;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
		
	}

}
