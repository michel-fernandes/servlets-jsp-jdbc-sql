package servlets;

import java.io.IOException;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.UsuarioDAORepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig // trabalha com enctype="multipart/form-data" para recebimento de dados do form
@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;
	private UsuarioDAORepository usuarioDAO;

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String login = request.getParameter("login");

			usuarioDAO = new UsuarioDAORepository();

			if (acao.equals("buscarEditar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				ModelLogin user = usuarioDAO.consultarId(id, super.getUserLogado(request));
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request), 0l);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", user);
				request.setAttribute("totalPaginas", usuarioDAO.totalPagina(super.getUserLogado(request)));
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			} else if (acao.equals("listarUser")) {
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request), 0l);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuários carregados");
				request.setAttribute("totalPaginas", usuarioDAO.totalPagina(super.getUserLogado(request)));
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			} else if (acao.equals("downloadImagem")) {
				Long id = Long.parseLong(request.getParameter("id"));
				ModelLogin user = usuarioDAO.consultarId(id, super.getUserLogado(request));
				// cadastro-usuario download da imagem do perfil
				if (user.getImagem() != "" && !user.getImagem().isEmpty()) {
					response.setHeader("Content-Disposition",
							"attachment;filename=download_image." + user.getFormatoImagem());
					response.getOutputStream().write(new Base64().decodeBase64(user.getImagem().split("\\,")[1]));
				}
			} else if (acao.equals("paginar")) {
				Long de = Long.parseLong(request.getParameter("de"));
				List<ModelLogin> listUsers = usuarioDAO.consultarUsersPaginado(super.getUserLogado(request), de);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("totalPaginas", usuarioDAO.totalPagina(super.getUserLogado(request)));
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			} else if (!login.isEmpty() && login != null && acao.equals("deletarAjax")
					&& usuarioDAO.jaExisteLogin(login)) { // alternativa a solução acima
				usuarioDAO.deletar(login);
				response.getWriter().write("Usuário excluido com sucesso");
			} else if (!login.isEmpty() && login != null && acao.equals("consultarUsuarioAjax")) {
				// Long de = Long.parseLong(request.getParameter("de"));
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(login, super.getUserLogado(request));
				Gson gson = new GsonBuilder().setPrettyPrinting().create(); // bewutify Gson
				String json = gson.toJson(listUsers);
				response.addHeader("totalPaginas",
						usuarioDAO.consultarUsersTotalPaginas(login, super.getUserLogado(request)).toString());
				response.getWriter().write(json);
			} else if (!login.isEmpty() && login != null && acao.equals("consultarUsuarioAjaxPage")) {
				Long de = Long.parseLong(request.getParameter("de"));
				List<ModelLogin> listUsers = usuarioDAO.consultarUsersPage(login, super.getUserLogado(request), de);
				Gson gson = new GsonBuilder().setPrettyPrinting().create(); // bewutify Gson
				String json = gson.toJson(listUsers);
				response.addHeader("totalPaginas",
						usuarioDAO.consultarUsersTotalPaginas(login, super.getUserLogado(request)).toString());
				response.getWriter().write(json);
			} else if (!login.isEmpty() && login != null && login.equals("deletar")
					&& usuarioDAO.jaExisteLogin(login)) {
				usuarioDAO.deletar(login);
				Long de = Long.parseLong(request.getParameter("de"));
				List<ModelLogin> listUsers = usuarioDAO.consultarUsersPaginado(super.getUserLogado(request), de);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("msg", "Usuário excluido com sucesso");
				request.setAttribute("totalPaginas", usuarioDAO.totalPagina(super.getUserLogado(request)));
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			} else {
				throw new Exception("Usuário não encontrado");
			}

		} catch (Exception e) {
			e.printStackTrace();
			erro(e.getMessage(), request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");

			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");

			if (!senha.isEmpty() && senha != null && !nome.isEmpty() && nome != null && !email.isEmpty()
					&& email != null && !login.isEmpty() && login != null && !perfil.isEmpty() && perfil != null
					&& !sexo.isEmpty() && sexo != null) {
				ModelLogin modelLogin = new ModelLogin(nome, email, login, senha, perfil, sexo);
				modelLogin.setEndereco(cep, logradouro, bairro, localidade, uf, numero);

				if (perfil.contains("ADMIN")) {
					modelLogin.setAdmin(true);
				} else {
					modelLogin.setAdmin(false);
				}
				modelLogin.setId((!id.isEmpty() && id != null) ? Long.parseLong(id) : null);

				if (ServletFileUpload.isMultipartContent(request)) {
					Part part = request.getPart("fileFoto"); /* captura a foto do form */
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); /* coverte imagem para byte */
					String imagemBase64 = "data:" + part.getContentType() + ";base64,"
							+ new Base64().encodeBase64String(foto);

					modelLogin.setImagem(imagemBase64);
					modelLogin.setFormatoImagem(part.getContentType().split("\\/")[1]);
				}

				usuarioDAO = new UsuarioDAORepository();

				if (!usuarioDAO.jaExisteLogin(modelLogin.getLogin())) {
					modelLogin = usuarioDAO.criar(modelLogin, super.getUserLogado(request));
					System.out.println(modelLogin.toString());
					request.setAttribute("msg", "Usuário criado com sucesso");
					request.setAttribute("modelLogin", modelLogin);
				} else {
					if (!id.isEmpty() && id != null) {
						modelLogin = usuarioDAO.atualizar(modelLogin, super.getUserLogado(request));
						System.out.println(modelLogin.toString());
						request.setAttribute("msg", "Usuário atualizado com sucesso");
						request.setAttribute("modelLogin", modelLogin);
					} else {
						erro("Login existente", request, response);
					}
				}
				List<ModelLogin> listUsers = usuarioDAO.consultarUsers(super.getUserLogado(request), 0l);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("totalPaginas", usuarioDAO.totalPagina(super.getUserLogado(request)));
				request.getRequestDispatcher("/principal/cadastro-usuario.jsp").forward(request, response);
			} else {
				erro("Obrigatório informar a senha", request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			erro(e.getMessage(), request, response);
		}
	}

	public void erro(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
	}

}
