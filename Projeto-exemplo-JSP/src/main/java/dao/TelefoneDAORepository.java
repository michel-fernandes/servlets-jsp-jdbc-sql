package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;
import model.ModelTelefone;

public class TelefoneDAORepository {
	
	private Connection connection;

	public TelefoneDAORepository() {
		this.connection = SingleConnection.getConnection();
	}
	
	public void criar(ModelTelefone modelTelefone) throws SQLException {
		
		String sql = "INSERT INTO model_telefone(telefone, tipo, usuario_id) VALUES (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelTelefone.getTelefone());
		statement.setString(2, modelTelefone.getTipo());
		statement.setLong(3, modelTelefone.getUser_id());
		
		statement.execute();
		connection.commit();
	}
	
	public boolean jaExisteTelefone(Long user_id, String telefone) throws SQLException {

		String sql = "select * from model_telefone where user_id=? and telefone=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, user_id);
		statement.setString(2, telefone);
		ResultSet resultSet = statement.executeQuery();
		
		return (resultSet.next())?true:false;		
	}
	
	public List<ModelTelefone> consultar(Long user_id) throws SQLException {

		String sql = "select * from model_telefone where user_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, user_id);
		ResultSet resultSet = statement.executeQuery();
		
		List<ModelTelefone> listTelefone = new ArrayList<ModelTelefone>();
		
		while(resultSet.next())  {
			ModelTelefone modelTelefone = new ModelTelefone();
			modelTelefone.setId(resultSet.getLong("id"));
			modelTelefone.setTelefone(resultSet.getString("telefone"));
			modelTelefone.setTipo(resultSet.getString("tipo"));
			modelTelefone.setUser_id(resultSet.getLong("usuario_id"));
		}
		
		return listTelefone;		
	}
	
	public void atualizar(ModelTelefone modeltelefone) throws SQLException {

		String sql = "UPDATE model_telefone SET telefone=?, tipo=? WHERE id=? and usuario_id=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modeltelefone.getTelefone());
		statement.setString(2, modeltelefone.getTipo());
		
		statement.setLong(3, modeltelefone.getId());
		statement.setLong(4, modeltelefone.getUser_id());
		
		statement.executeUpdate();
		connection.commit();
			
	}

	public void deletar(Long id) throws SQLException{
		String sql = "DELETE FROM model_telefone WHERE id=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		statement.executeUpdate();
		connection.commit();
	}
	
}
