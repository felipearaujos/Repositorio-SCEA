package scea.dominio.modelo;

public class TipoDeProduto extends EntidadeDominio{
	String tipo, descricao;
	int qtdeMax = 0, qtdeMin = 0;
	
	public int getQtdeMin() {
		return qtdeMin;
	}

	public void setQtdeMin(int qtdeMin) {
		this.qtdeMin = qtdeMin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQtdeMax() {
		return qtdeMax;
	}

	public void setQtdeMax(int qtdeMax) {
		this.qtdeMax = qtdeMax;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
