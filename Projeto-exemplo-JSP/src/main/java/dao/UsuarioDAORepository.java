package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;

public class UsuarioDAORepository {

	private Connection connection;

	public UsuarioDAORepository() {
		this.connection = SingleConnection.getConnection();
	}

	public ModelLogin criar(ModelLogin modelLogin, Long userLogado) throws SQLException {
		
		String sql = "INSERT INTO model_login(login, senha, email, nome, usuario_id, useradmin, perfil, sexo) VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		statement.setString(3, modelLogin.getEmail());
		statement.setString(4, modelLogin.getNome());
		statement.setLong(5, userLogado);
		statement.setBoolean(6, modelLogin.isAdmin());
		statement.setString(7, modelLogin.getPerfil());
		statement.setString(8, modelLogin.getSexo());
		statement.execute();
		connection.commit();
		
		if(modelLogin.getFormatoImagem() != null && !modelLogin.getFormatoImagem().isEmpty()) {
			atualizarFoto(modelLogin);
		}
		
		return this.consultar(modelLogin.getLogin(), userLogado);
	}
	
	public ModelLogin consultarLogado(String login) throws SQLException {

		String sql = "select * from model_login where login=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultSet = statement.executeQuery();
		
		ModelLogin modelLogin = new ModelLogin();
		
		if(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagem(resultSet.getString("imagem"));
			modelLogin.setFormatoImagem(resultSet.getString("formato_imagem"));
			modelLogin.setSenha(null);
		}
		
		return modelLogin;		
	}
	
	public ModelLogin consultar(String login, Long usuarioLogado) throws SQLException {

		String sql = "select * from model_login where login=? and userAdmin is false and usuario_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		statement.setLong(2, usuarioLogado);
		ResultSet resultSet = statement.executeQuery();
		
		ModelLogin modelLogin = new ModelLogin();
		
		if(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagem(resultSet.getString("imagem"));
			modelLogin.setFormatoImagem(resultSet.getString("formato_imagem"));
			modelLogin.setSenha(null);
		}
		
		return modelLogin;
		
	}
	
	public ModelLogin consultarId(Long id, Long userLogado) throws SQLException {

		String sql = "select * from model_login where id=? and userAdmin is false and usuario_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		statement.setLong(2, userLogado);
		ResultSet resultSet = statement.executeQuery();
		
		ModelLogin modelLogin = new ModelLogin();
		
		if(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagem(resultSet.getString("imagem"));
			modelLogin.setFormatoImagem(resultSet.getString("formato_imagem"));
			modelLogin.setSenha(null);
		}
		
		return modelLogin;
		
	}
	
	public List<ModelLogin> consultarUsers(String login , Long userLogado) throws SQLException {

		String sql = "select * from public.model_login where upper(login) like upper(?) and userAdmin is false and usuario_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + login + "%");
		statement.setLong(2, userLogado);
		ResultSet resultSet = statement.executeQuery();
		
		List<ModelLogin> listLogin = new ArrayList<ModelLogin>();
		
		while(resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagem(resultSet.getString("imagem"));
			modelLogin.setFormatoImagem(resultSet.getString("formato_imagem"));
			modelLogin.setSenha(null);
			listLogin.add(modelLogin);
		}
		
		return listLogin;
		
	}
	
	public List<ModelLogin> consultarUsers(Long userLogado) throws SQLException {

		String sql = "select * from public.model_login where userAdmin is false and usuario_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, userLogado);
		ResultSet resultSet = statement.executeQuery();
		
		List<ModelLogin> listLogin = new ArrayList<ModelLogin>();
		
		while(resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagem(resultSet.getString("imagem"));
			modelLogin.setFormatoImagem(resultSet.getString("formato_imagem"));
			modelLogin.setSenha(null);
			listLogin.add(modelLogin);
		}
		
		return listLogin;
		
	}
	public ModelLogin atualizar(ModelLogin modelLogin, Long userLogado) throws SQLException {

		String sql = "UPDATE model_login SET login=?, senha=?, email=?, nome=?, useradmin=?, perfil=?, sexo=? WHERE id=? and userAdmin is false and usuario_id=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		statement.setString(3, modelLogin.getEmail());
		statement.setString(4, modelLogin.getNome());
		statement.setBoolean(5, modelLogin.isAdmin());
		statement.setString(6, modelLogin.getPerfil());
		statement.setString(7, modelLogin.getSexo());
		statement.setLong(8, modelLogin.getId());
		statement.setLong(9, userLogado);
		statement.executeUpdate();
		connection.commit();
		
		if(modelLogin.getFormatoImagem() != null && !modelLogin.getFormatoImagem().isEmpty()) {
			atualizarFoto(modelLogin);
		}
		
		return this.consultar(modelLogin.getLogin(), userLogado);
	}
	
	private void atualizarFoto(ModelLogin modelLogin) throws SQLException {

		String sql = "UPDATE model_login SET imagem=?, formato_imagem=?  WHERE login=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getImagem());
		statement.setString(2, modelLogin.getFormatoImagem());
		statement.setString(3, modelLogin.getLogin());
		statement.executeUpdate();
		connection.commit();
	}
	
	public boolean jaExisteLogin(String login) throws SQLException {

		String sql = "select * from model_login where login=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultSet = statement.executeQuery();
		
		return (resultSet.next())?true:false;		
	}

	public void deletar(String login) throws SQLException{
		String sql = "DELETE FROM model_login WHERE login=? and userAdmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		statement.executeUpdate();
		connection.commit();
		
	}

}
