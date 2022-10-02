package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

/*controller, ServeletLoginController*/
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServeletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServeletLogin() {

    }
    
    /*Recebe os dados da url em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/*Recebe os dados enviados por um formulário*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String senha = request.getParameter("senha");
		String login = request.getParameter("login");
		String url = request.getParameter("url");
		
		if(!login.isEmpty() && login!= null && !senha.isEmpty() && senha!= null) {
			ModelLogin modelLogin = new ModelLogin(login, senha);
			
			if(modelLogin.getLogin().equalsIgnoreCase("admin") 
					&& modelLogin.getSenha().equalsIgnoreCase("admin")) {
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if(url==null || url.equals("null")) {
					url = "/principal/principal.jsp";
				}
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha");
				redirecionar.forward(request, response);
			}
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe o login e senha");
			redirecionar.forward(request, response);
		}
		
	}

}
