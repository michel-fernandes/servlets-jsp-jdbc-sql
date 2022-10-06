package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.ModelLogin;

public class UsuarioDAORepository {

	private Connection connection;

	public UsuarioDAORepository() {
		this.connection = SingleConnection.getConnection();
	}

	public ModelLogin criar(ModelLogin modelLogin) throws SQLException {

		String sql = "INSERT INTO model_login(login, senha, email, nome) VALUES (?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		statement.setString(3, modelLogin.getEmail());
		statement.setString(4, modelLogin.getNome());
		statement.execute();
		connection.commit();
		
		return this.consultar(modelLogin.getLogin());
	}
	
	public ModelLogin consultar(String login) throws SQLException {

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
			modelLogin.setSenha(resultSet.getString("senha"));
		}
		
		return modelLogin;
		
	}
	
	public ModelLogin atualizar(ModelLogin modelLogin) throws SQLException {

		String sql = "UPDATE model_login SET login=?, senha=?, email=?, nome=? WHERE id=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		statement.setString(3, modelLogin.getEmail());
		statement.setString(4, modelLogin.getNome());
		statement.setLong(5, modelLogin.getId());
		statement.executeUpdate();
		connection.commit();
		
		return this.consultar(modelLogin.getLogin());
	}
	
	public boolean jaExisteLogin(String login) throws SQLException {

		String sql = "select * from model_login where login=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultSet = statement.executeQuery();
		
		return (resultSet.next())?true:false;		
	}

}
