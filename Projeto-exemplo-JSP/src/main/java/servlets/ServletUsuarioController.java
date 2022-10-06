package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;
import java.sql.SQLException;

import dao.UsuarioDAORepository;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAORepository usuarioDAO; 
       
    public ServletUsuarioController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			if(!senha.isEmpty() && senha!= null
					&& !nome.isEmpty() && nome!= null && !email.isEmpty() && email!= null) {
				
				ModelLogin modelLogin = new ModelLogin(nome, email, login, senha);
				modelLogin.setId((!id.isEmpty() && id!= null)? Long.parseLong(id): null);		
				usuarioDAO = new UsuarioDAORepository();
				
				if(!usuarioDAO.jaExisteLogin(modelLogin.getLogin())) {
					modelLogin = usuarioDAO.criar(modelLogin);
					System.out.println(modelLogin.toString());				
					request.setAttribute("msg", "Usuário criado com sucesso");
					request.setAttribute("modelLogin", modelLogin);
				}else {
					modelLogin = usuarioDAO.atualizar(modelLogin);
					System.out.println(modelLogin.toString());				
					request.setAttribute("msg", "Usuário atualizado com sucesso");
					request.setAttribute("modelLogin", modelLogin);
				}
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			erro(e.getMessage(), request, response);
		}
	}
	
	public void erro(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
	}

}
