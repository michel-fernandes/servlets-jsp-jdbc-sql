package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private boolean admin;
	private String perfil;
	private String sexo;
	
	public ModelLogin() {
		
	}
	
	public ModelLogin(Long id, String nome, String email, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public ModelLogin(String nome, String email, String login, String senha) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	
	public ModelLogin(String nome, String email, String login, String senha, String perfil, String sexo) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.sexo = sexo;
	}

	public ModelLogin(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public int hashCode() {
		return Objects.hash(login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelLogin other = (ModelLogin) obj;
		return Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		return "ModelLogin [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ", admin=" + admin + ", perfil=" + perfil + ", sexo=" + sexo + "]";
	}
	
}
