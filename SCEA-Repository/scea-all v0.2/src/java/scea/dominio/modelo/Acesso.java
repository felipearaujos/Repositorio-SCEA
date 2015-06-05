package scea.dominio.modelo;

public class Acesso extends EntidadeDominio{
	String login, senha;
	public boolean isAdmin = false;
    public boolean isLoginCorreto = false;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isLoginCorreto() {
		return isLoginCorreto;
	}

	public void setLoginCorreto(boolean isLoginCorreto) {
		this.isLoginCorreto = isLoginCorreto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
