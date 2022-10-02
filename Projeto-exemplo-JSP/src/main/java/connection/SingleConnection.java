package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String banco = "jdbc:postgresql://localhost:5432/exemplo-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {/*quando invocar a classe*/
		conectar();
	}
	
	public SingleConnection() {/*quando tiver uma instancia vai conectar*/
		conectar();
	}

	private static void conectar() {
		try {
			if(connection==null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);//não salvar automaticamente
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static Boolean isConnected() {
		return (connection==null)?true:false;
	}
}
