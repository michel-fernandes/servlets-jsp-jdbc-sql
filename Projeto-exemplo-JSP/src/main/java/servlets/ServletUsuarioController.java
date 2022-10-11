package servlets;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import dao.UsuarioDAORepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController  extends ServletGenericUtil{
	
	private static final long serialVersionUID = 1L;
	private UsuarioDAORepository usuarioDAO; 
       
    public ServletUsuarioController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String login = request.getParameter("login");
			
			usuarioDAO = new UsuarioDAORepository();
						
			if(acao.equals("buscarEditar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				ModelLogin user = usuarioDAO.consultarId(id, super.getUserLogado(request));					
				System.out.println(user.toString());				
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request));							
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", user);
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			} else if(acao.equals("listarUser")) {
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request));							
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuários carregados");
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			}else if(!login.isEmpty() && login!= null && acao.equals("deletarAjax")
						&& usuarioDAO.jaExisteLogin(login)) { //alternativa a solução acima
				usuarioDAO.deletar(login);					
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request));							
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuário excluido com sucesso");
			}else if(!login.isEmpty() && login!= null && acao.equals("consultarUsuarioAjax")) {
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(login, super.getUserLogado(request));					
				Gson gson = new GsonBuilder().setPrettyPrinting().create(); //bewutify Gson
				String json = gson.toJson(listUsers);
				response.getWriter().write(json);
			}else if(!login.isEmpty() && login!= null && login.equals("deletar") && usuarioDAO.jaExisteLogin(login)){
				usuarioDAO.deletar(login);					
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request));							
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuário excluido com sucesso");
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			}else {
				throw new Exception("Usuário não encontrado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			erro(e.getMessage(), request, response);
		}
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
					modelLogin = usuarioDAO.criar(modelLogin, super.getUserLogado(request));
					System.out.println(modelLogin.toString());				
					request.setAttribute("msg", "Usuário criado com sucesso");
					request.setAttribute("modelLogin", modelLogin);
				}else {
					modelLogin = usuarioDAO.atualizar(modelLogin, super.getUserLogado(request));
					System.out.println(modelLogin.toString());				
					request.setAttribute("msg", "Usuário atualizado com sucesso");
					request.setAttribute("modelLogin", modelLogin);
				}
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request));							
				request.setAttribute("listUsers", listUsers);
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
