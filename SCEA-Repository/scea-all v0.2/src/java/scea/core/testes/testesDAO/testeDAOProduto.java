package scea.core.testes.testesDAO;

import java.util.ArrayList;
import java.util.List;

import scea.core.impl.dao.FornecedorDAO;
import scea.core.impl.dao.ProdutoDAO;
import scea.dominio.modelo.*;

public class testeDAOProduto {

	public void testeConsultaTodosOsProdutos()
	{

		System.out.println("<---- LISTA TODOS PRODUTOS  ---- >");
		
		Produto  produto = new Produto();
		produto.setTipoDeProduto(new TipoDeProduto());
		produto.getTipoDeProduto().setTipo("");
		//produto.setId(1);
		//fornecedor.setCNPJ("00011133322213");
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<EntidadeDominio> entidadeDominioList = new ArrayList<EntidadeDominio>();
		List<Produto> produtoList = new ArrayList<Produto>();		
		
		entidadeDominioList = produtoDAO.consultar(produto);

		for (EntidadeDominio p: entidadeDominioList) {
			produto = ((Produto)p);
			
			System.out.println("ID PRODUTO: " + produto.getId());
			System.out.println("NOME: " + produto.getNome());
			System.out.println("QUANTIDADE: " + produto.getQuantidade());
			System.out.println("VALOR: " + produto.getValor());
			System.out.println("ID TIPO: " + produto.getTipoDeProduto().getId());
			System.out.println("TIPO: " + produto.getTipoDeProduto().getTipo());
			System.out.println("DESCRICAO: " + produto.getTipoDeProduto().getDescricao());
			
			
			System.out.println("-------------------------");
		}
		
	}
	
	
	
	
	public void testeConsultaLikeTipo()
	{
		
		System.out.println("<---- LISTA PRODUTO POR TIPO  ---- >");
		
		Produto  produto = new Produto();
		produto.setTipoDeProduto(new TipoDeProduto());
		produto.getTipoDeProduto().setTipo("C");
		//produto.setId(1);
		//fornecedor.setCNPJ("00011133322213");
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<EntidadeDominio> entidadeDominioList = new ArrayList<EntidadeDominio>();
		List<Produto> produtoList = new ArrayList<Produto>();		
		
		entidadeDominioList = produtoDAO.consultar(produto);

		for (EntidadeDominio p: entidadeDominioList) {
			produto = ((Produto)p);
			
			System.out.println("ID PRODUTO: " + produto.getId());
			System.out.println("NOME: " + produto.getNome());
			System.out.println("QUANTIDADE: " + produto.getQuantidade());
			System.out.println("VALOR: " + produto.getValor());
			System.out.println("ID TIPO: " + produto.getTipoDeProduto().getId());
			System.out.println("TIPO: " + produto.getTipoDeProduto().getTipo());
			System.out.println("DESCRICAO: " + produto.getTipoDeProduto().getDescricao());
			
			
			System.out.println("-------------------------");
		}
		
	}
	
	
	public void testeConsultaId()
	{
		

		System.out.println("<---- LISTA PRODUTO POR ID  ---- >");
		
		Produto  produto = new Produto();
		produto.setTipoDeProduto(new TipoDeProduto());
		produto.setTipoDeProduto(new TipoDeProduto());
		produto.getTipoDeProduto().setTipo("");
		//produto.getTipoDeProduto().setTipo("Caneta A");
		produto.setId(1);
		//fornecedor.setCNPJ("00011133322213");
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<EntidadeDominio> entidadeDominioList = new ArrayList<EntidadeDominio>();
		List<Produto> produtoList = new ArrayList<Produto>();		
		
		entidadeDominioList = produtoDAO.consultar(produto);

		for (EntidadeDominio p: entidadeDominioList) {
			produto = ((Produto)p);
			
			System.out.println("ID PRODUTO: " + produto.getId());
			System.out.println("NOME: " + produto.getNome());
			System.out.println("QUANTIDADE: " + produto.getQuantidade());
			System.out.println("VALOR: " + produto.getValor());
			System.out.println("ID TIPO: " + produto.getTipoDeProduto().getId());
			System.out.println("TIPO: " + produto.getTipoDeProduto().getTipo());
			System.out.println("DESCRICAO: " + produto.getTipoDeProduto().getDescricao());
			
			
			System.out.println("-------------------------");
		}
	}
	
}
