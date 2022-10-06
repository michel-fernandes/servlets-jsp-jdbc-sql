package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.ModelLogin;

public class LoginDAORepository {

	private Connection connection;

	public LoginDAORepository() {
		this.connection = SingleConnection.getConnection();
	}

	public boolean autenticar(ModelLogin modelLogin) throws SQLException {

		String sql = "select * from model_login where login=? and senha=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());

		ResultSet resultSet = statement.executeQuery();

		return resultSet.next() ? true : false;
	}

}
