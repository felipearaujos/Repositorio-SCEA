package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;






import scea.dominio.modelo.Cidade;
import scea.dominio.modelo.Endereco;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Estado;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Telefone;



public class FornecedorDAO extends AbstractJdbcDAO{
	
	public FornecedorDAO() {
		super("tb_fornecedor", "id_fornecedor");		
	}
	public void salvar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		
		Fornecedor fornecedor = (Fornecedor)entidade;
		Telefone telefone = fornecedor.getTelefone();
		Endereco endereco = fornecedor.getEndereco();
		Cidade cidade = fornecedor.getEndereco().getCidade();
		Estado estado = fornecedor.getEndereco().getEstado();
		
		String sql=null, sql2 =null, sql3=null;
		
			sql2 = "INSERT INTO tb_endereco (rua, numero, complemento, cep, bairro, cidade, estado)"
					+ " VALUES(?,?,?,?,?,?,?);";
					
			sql = "INSERT INTO tb_telefone (numero)"
				+ " VALUES(?);";
												
			sql3 = "INSERT INTO tb_fornecedor (nome, email, nome_fantasia, rzsocial, cnpj, id_endereco, id_telefone)"
					+ " VALUES (?,?,?,?,?,?,?); ";
		
	
	
	try {
		openConnection();
		
		//Inserindo Telefone	
		pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		pst.setString(1, telefone.getNumero());
		pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		int idTel=0;
		if(rs.next())
			idTel = rs.getInt(1);
		
		
		//Inserindo Endereco
		pst2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
		pst2.setString(1, endereco.getRua());
		pst2.setString(2, endereco.getNumero());
		pst2.setString(3, endereco.getComplemento());
		pst2.setString(4, endereco.getCep());
		pst2.setString(5, endereco.getBairro());
		pst2.setString(6, cidade.getNome());
		pst2.setString(7, estado.getNome());
		pst2.executeUpdate();
		ResultSet rs2 = pst2.getGeneratedKeys();
		int idEnd=0;
		if(rs2.next())
			idEnd = rs.getInt(1);
		
		pst3 = connection.prepareStatement(sql3);
		pst3.setString(1, fornecedor.getNome());
		pst3.setString(2, fornecedor.getEmail());
		pst3.setString(3, fornecedor.getNomeFantasia());
		pst3.setString(4, fornecedor.getRazaoSocial());
		pst3.setString(5, fornecedor.getCNPJ());
		pst3.setInt(6, rs2.getInt(1));
		pst3.setInt(7, rs.getInt(1));
		pst3.executeUpdate();
		
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	
	}

	
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void excluir(EntidadeDominio entidade){
		PreparedStatement pst = null;
		String sql = null;
		
		Fornecedor fornecedor = (Fornecedor)entidade;
		
                
		if(fornecedor.getId() ==  null && !fornecedor.getCNPJ().equals("")){
			sql = "SELECT id_fornecedor, id_endereco, id_telefone FROM tb_fornecedor WHERE cnpj = ?;";
		}else if(fornecedor.getId() != null && fornecedor.getCNPJ().equals("")){
			sql = "SELECT id_fornecedor, id_endereco, id_telefone FROM tb_fornecedor WHERE id_fornecedor = ?;";
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(fornecedor.getId() != null && fornecedor.getCNPJ().equals("")){
				pst.setInt(1, fornecedor.getId());
			}else if(fornecedor.getId() == null && !fornecedor.getCNPJ().equals("")){
				pst.setString(1, fornecedor.getCNPJ());			
			}
			

			ResultSet rs = pst.executeQuery();

				Fornecedor f = new Fornecedor();
				Telefone t = new Telefone();
				Endereco e = new Endereco();
				PreparedStatement pst2 =  null;
				PreparedStatement pst3 =  null;
				
					while(rs.next()){
					f.setId(rs.getInt("id_fornecedor"));
					e.setId(rs.getInt("id_endereco"));
					t.setId(rs.getInt("id_telefone"));
					
					f.setEndereco(e);
					f.setTelefone(t);
				
					sql = "DELETE FROM tb_fornecedor WHERE id_fornecedor = ?";
					pst = connection.prepareStatement(sql);
					pst.setInt(1, f.getId());
	
					pst.executeUpdate();
	
					
					String sql2 = "DELETE FROM tb_endereco WHERE id_endereco = ?";
					pst2 = connection.prepareStatement(sql2);
					pst2.setInt(1, f.getEndereco().getId());
	
					pst2.executeUpdate();
	
					
					sql = "DELETE FROM tb_telefone WHERE id_telefone = ?";
					pst3 = connection.prepareStatement(sql);
					pst3.setInt(1, f.getTelefone().getId());
	
					pst3.executeUpdate();
					
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Fornecedor fornecedor = (Fornecedor)entidade;
		String sql = "SELECT * FROM tb_fornecedor f JOIN tb_endereco e using(id_endereco) "
					+ "JOIN tb_telefone t using(id_telefone)";;

		if(fornecedor.getId() ==  null && fornecedor.getCNPJ().equals("")){
			sql = "SELECT * FROM tb_fornecedor f JOIN tb_endereco e using(id_endereco) "
					+ "JOIN tb_telefone t using(id_telefone)";
		}else if(fornecedor.getId() != null && fornecedor.getCNPJ().equals("")){
			sql = "SELECT * FROM tb_fornecedor f JOIN tb_endereco e using(id_endereco) "
					+ "JOIN tb_telefone t using(id_telefone) WHERE id_fornecedor =?";
		}else if(fornecedor.getId() == null && !fornecedor.getCNPJ().equals("")){
			sql = "SELECT * FROM tb_fornecedor f JOIN tb_endereco e using(id_endereco) "
					+ "JOIN tb_telefone t using(id_telefone) WHERE cnpj LIKE ?";
		
		}
	
	
	
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		
		if(fornecedor.getId() != null && fornecedor.getCNPJ().equals("")){
			pst.setInt(1, fornecedor.getId());
		}else if(fornecedor.getId() == null && !fornecedor.getCNPJ().equals("")){
			pst.setString(1, fornecedor.getCNPJ());			
		}
		

		
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
			Fornecedor f = new Fornecedor();
			Endereco e = new Endereco();
			Telefone t = new Telefone();
			Cidade c = new Cidade();
			Estado est = new Estado();
			f.setId(rs.getInt("id_fornecedor"));
			f.setNome(rs.getString("nome"));
			f.setEmail(rs.getString("email"));
			f.setNomeFantasia(rs.getString("nome_fantasia"));
			f.setRazaoSocial(rs.getString("rzsocial"));
			f.setCNPJ(rs.getString("cnpj"));
			
			f.setEndereco(e);
			f.setTelefone(t);
			f.getEndereco().setCidade(c);
			f.getEndereco().setEstado(est);
			
			f.getTelefone().setNumero(rs.getString("numero"));
			f.getEndereco().setRua(rs.getString("rua"));
			f.getEndereco().setNumero(rs.getString("numero"));
			f.getEndereco().setComplemento(rs.getString("complemento"));
			f.getEndereco().setCep(rs.getString("cep"));
			f.getEndereco().setBairro(rs.getString("bairro"));
			f.getEndereco().getCidade().setNome(rs.getString("cidade"));
			f.getEndereco().getEstado().setNome(rs.getString("estado"));

			produtos.add(f);
		}
		return produtos;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;

	}

	
}
