package scea.core.testes.testesDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import scea.dominio.modelo.*;
import scea.core.impl.dao.AbstractJdbcDAO;
import scea.core.impl.dao.FornecedorDAO;

public class testeDAODFornecedor {

	public void testeConsultaTodosOsFornecedores()
	{
		Fornecedor fornecedor = new Fornecedor();
		Endereco e = new Endereco();
		Telefone t = new Telefone();
		fornecedor.setEndereco(e);
		fornecedor.setTelefone(t);
		fornecedor.setCNPJ("");
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		List<EntidadeDominio> entidadeDominioList = new ArrayList<EntidadeDominio>();
		List<Fornecedor> fornecedorList = new ArrayList<Fornecedor>();		
		
		entidadeDominioList = fornecedorDAO.consultar(fornecedor);

		for (EntidadeDominio fornecedor2 : entidadeDominioList) {
			fornecedor = ((Fornecedor)fornecedor2);
			
			System.out.println("\n\n\n< ---- LISTA TODOS FORNECEDORES ---- >");
			
			System.out.println("Nome:" + fornecedor.getNome());
			System.out.println("CNPJ:" + fornecedor.getCNPJ());
			System.out.println("Email:" + fornecedor.getEmail());
			System.out.println("Nome Fantasia:" + fornecedor.getNomeFantasia());
			System.out.println("Raz�o Social:" + fornecedor.getRazaoSocial());
			System.out.println("Telefone:" + fornecedor.getTelefone().getNumero());
			System.out.println("Rua:" + fornecedor.getEndereco().getRua());
			System.out.println("Numero:" + fornecedor.getEndereco().getNumero());
			System.out.println("Complemento:" + fornecedor.getEndereco().getComplemento());
			System.out.println("CEP:" + fornecedor.getEndereco().getCep());
			System.out.println("Bairro:" + fornecedor.getEndereco().getBairro());
			System.out.println("Cidade:" + fornecedor.getEndereco().getCidade().getNome());
			System.out.println("Estado:" + fornecedor.getEndereco().getEstado().getNome());			
		}
		
	}

	public void testeConsultaTodosOsFornecedoresPorID()
	{
		Fornecedor fornecedor = new Fornecedor();
		Endereco e = new Endereco();
		Telefone t = new Telefone();
		fornecedor.setEndereco(e);
		fornecedor.setTelefone(t);
		fornecedor.setId(13);
		fornecedor.setCNPJ("");
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		List<EntidadeDominio> entidadeDominioList = new ArrayList<EntidadeDominio>();
		List<Fornecedor> fornecedorList = new ArrayList<Fornecedor>();		
		
		entidadeDominioList = fornecedorDAO.consultar(fornecedor);

		for (EntidadeDominio fornecedor2 : entidadeDominioList) {
			fornecedor = ((Fornecedor)fornecedor2);
			
			System.out.println("\n\n\n< ---- LISTA TODOS FORNECEDORES POR ID ---- >");
			
			System.out.println("Nome:" + fornecedor.getNome());
			System.out.println("CNPJ:" + fornecedor.getCNPJ());
			System.out.println("Email:" + fornecedor.getEmail());
			System.out.println("Nome Fantasia:" + fornecedor.getNomeFantasia());
			System.out.println("Raz�o Social:" + fornecedor.getRazaoSocial());
			System.out.println("Telefone:" + fornecedor.getTelefone().getNumero());
			System.out.println("Rua:" + fornecedor.getEndereco().getRua());
			System.out.println("Numero:" + fornecedor.getEndereco().getNumero());
			System.out.println("Complemento:" + fornecedor.getEndereco().getComplemento());
			System.out.println("CEP:" + fornecedor.getEndereco().getCep());
			System.out.println("Bairro:" + fornecedor.getEndereco().getBairro());
			System.out.println("Cidade:" + fornecedor.getEndereco().getCidade().getNome());
			System.out.println("Estado:" + fornecedor.getEndereco().getEstado().getNome());			
		}
		
	}

	public void testeConsultaTodosOsFornecedoresPorCNPJ()

	{
		Fornecedor fornecedor = new Fornecedor();
		Endereco e = new Endereco();
		Telefone t = new Telefone();
		fornecedor.setEndereco(e);
		fornecedor.setTelefone(t);
		fornecedor.setCNPJ("123");
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		List<EntidadeDominio> entidadeDominioList = new ArrayList<EntidadeDominio>();
		List<Fornecedor> fornecedorList = new ArrayList<Fornecedor>();		
		
		entidadeDominioList = fornecedorDAO.consultar(fornecedor);

		for (EntidadeDominio fornecedor2 : entidadeDominioList) {
			fornecedor = ((Fornecedor)fornecedor2);
			
			System.out.println("\n\n\n< ---- LISTA TODOS FORNECEDORES POR CNPJ ---- >");
			
			System.out.println("Nome:" + fornecedor.getNome());
			System.out.println("CNPJ:" + fornecedor.getCNPJ());
			System.out.println("Email:" + fornecedor.getEmail());
			System.out.println("Nome Fantasia:" + fornecedor.getNomeFantasia());
			System.out.println("Raz�o Social:" + fornecedor.getRazaoSocial());
			System.out.println("Telefone:" + fornecedor.getTelefone().getNumero());
			System.out.println("Rua:" + fornecedor.getEndereco().getRua());
			System.out.println("Numero:" + fornecedor.getEndereco().getNumero());
			System.out.println("Complemento:" + fornecedor.getEndereco().getComplemento());
			System.out.println("CEP:" + fornecedor.getEndereco().getCep());
			System.out.println("Bairro:" + fornecedor.getEndereco().getBairro());
			System.out.println("Cidade:" + fornecedor.getEndereco().getCidade().getNome());
			System.out.println("Estado:" + fornecedor.getEndereco().getEstado().getNome());			
		}
		
	}

	public void testeRegistraFornecedor()
	{
		Fornecedor f = new Fornecedor();
		Endereco e = new Endereco();
		Telefone t = new Telefone();
		Estado est = new Estado();
		Cidade c = new Cidade();
		
		f.setCNPJ("12345123451234");
		f.setEmail("devilsoul@hotmail.com");
		f.setNome("TAKAO");
		f.setNomeFantasia("TAKAO MOTORS");
		f.setRazaoSocial("Especialistas em Motor");
		e.setBairro("Vila Maria");
		e.setCep("08130480");
		e.setComplemento("A");
		e.setNumero("44");
		e.setRua("Rua dos Ingleses");
		est.setNome("SP");
		t.setNumero("1129748273");
		c.setNome("Mogi das Cruzes");
		
		e.setCidade(c);
		e.setEstado(est);
		f.setEndereco(e);
		f.setTelefone(t);
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.salvar(f);
		
	}
	
	public void testeExcluiFornecedor()
	{
		Fornecedor f = new Fornecedor();
		f.setId(4);
		f.setCNPJ("");

		FornecedorDAO dao = new FornecedorDAO();
		dao.excluir(f);
		
	}
}
