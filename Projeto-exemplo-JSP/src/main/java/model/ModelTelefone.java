package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelTelefone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String telefone;
	private String tipo;
	private Long user_id;
	
	public ModelTelefone() {
	}
	
	public ModelTelefone(String telefone, String tipo, Long user_id) {
		this.telefone = telefone;
		this.tipo = tipo;
		this.user_id = user_id;
	}
	
	public ModelTelefone(Long id, String telefone, String tipo, Long user_id) {
		this.id = id;
		this.telefone = telefone;
		this.tipo = tipo;
		this.user_id = user_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, telefone, tipo, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		return Objects.equals(id, other.id) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(tipo, other.tipo) && Objects.equals(user_id, other.user_id);
	}

}
