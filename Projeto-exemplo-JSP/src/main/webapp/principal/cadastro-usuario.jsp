<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.ModelLogin"%>

<jsp:include page="head.jsp" />

<body>
	<jsp:include page="pre-loader.jsp" />
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp" />
			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbar_mainmenu.jsp" />
					<div class="pcoded-content">
						<jsp:include page="page_header.jsp" />
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<div class="col-md-12">
													<div class="card">
														<div class="card-header">
															<h5>Cadastro de usuário</h5>
															<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
														</div>
														<div class="card-block">
															<!--enctype="multipart/form-data" para enviar conteúdo com arquivo-->
															<form class="form-material" enctype="multipart/form-data"
																action="<%=request.getContextPath()%>/ServletUsuarioController"
																method="post" id="form-user">
																<input type="hidden" name="acao" id="acao" value=""></input>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="id" id="id"
																		class="form-control" placeholder="ID automático"
																		readonly="readonly" value="${modelLogin.getId()}">
																	<span class="form-bar"></span> <label
																		class="float-label">ID</label>
																</div>
																<div class="form-group form-default input-group mb-4">
																	<div class="input-group-prepend">
																		<c:if
																			test="${modelLogin.getImagem()!='' && modelLogin.getImagem()!=null}">
																			<a
																				href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadImagem&id=${modelLogin.getId()}">
																				<img alt="Imagem do perfil" id="imagemBase64"
																				src="${modelLogin.getImagem()}" width="70px">
																			</a>
																		</c:if>
																		<c:if
																			test="${modelLogin.getImagem()=='' || modelLogin.getImagem()==null}">
																			<img alt="Imagem do perfil" id="imagemBase64"
																				src="<%=request.getContextPath()%>/assets/images/bear.png"
																				width="70px">
																		</c:if>
																	</div>
																	<input type="file" accept="images/*" id="fileFoto"
																		name="fileFoto"
																		onchange="visualizarImg('imagemBase64', 'fileFoto');"
																		class="form-control-file"
																		style="margin-top: 15px; margin-left: 5px;" />
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="nome" id="nome"
																		class="form-control" placeholder="Informe o nome"
																		required value="${modelLogin.getNome()}"> <span
																		class="form-bar"></span> <label class="float-label">Nome</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="email" name="email" id="email"
																		class="form-control" placeholder="Informe o Email"
																		required value="${modelLogin.getEmail()}"> <span
																		class="form-bar"></span> <label class="float-label">Email
																		(email@gmail.com)</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="radio" name="sexo" value="MASCULINO"
																		<%ModelLogin ml = (ModelLogin) request.getAttribute("modelLogin");
																		if (ml != null && ml.getSexo().equals("MASCULINO")) {
																			out.print(" ");
																			out.print("checked=\"checked\"");
																			out.print(" ");
																		}%>>Masculino</input>
																	<input type="radio" name="sexo" value="FEMININO"
																		<%if (ml != null && ml.getSexo().equals("FEMININO")) {
																			out.print(" ");
																			out.print("checked=\"checked\"");
																			out.print(" ");
																		}%>>Feminino</input>
																</div>
																<div class="form-group form-default form-static-label">
																	<select name="perfil" id="perfil" class="form-control">
																		<option disabled="disabled">Selecione o
																			perfil de usuário</option>
																		<option value="ADMIN"
																			<%if (ml != null && ml.getPerfil().contains("ADMIN")) {
																				out.print(" ");
																				out.print("selected=\"selected\"");
																				out.print(" ");
																			}%>>Administrador</option>
																		<option value="DEVELOPER"
																			<%if (ml != null && ml.getPerfil().contains("DEVELOPER")) {
																				out.print(" ");
																				out.print("selected=\"selected\"");
																				out.print(" ");
																			}%>>Developer</option>
																	</select><span class="form-bar"></span> <label
																		class="float-label">Perfil de usuário</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="cep" id="cep"
																		class="form-control"
																		placeholder="Informe o CEP com 8 digitos"
																		required="required" autocomplete="off"
																		value="${modelLogin.getCep()}"> <span
																		class="form-bar"></span> <label class="float-label">CEP</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="logradouro" id="logradouro"
																		class="form-control" placeholder="Logradouro"
																		required="required" autocomplete="off"
																		value="${modelLogin.getLogradouro()}"> <span
																		class="form-bar"></span> <label class="float-label">Logradouro</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="numero" id="numero"
																		class="form-control" placeholder="Informe o numero"
																		required="required" autocomplete="off"
																		value="${modelLogin.getNumero()}"> <span
																		class="form-bar"></span> <label class="float-label">Número</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="bairro" id="bairro"
																		class="form-control" placeholder="Bairro"
																		required="required" autocomplete="off"
																		value="${modelLogin.getBairro()}"> <span
																		class="form-bar"></span> <label class="float-label">Bairro</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="localidade" id="localidade"
																		class="form-control" placeholder="Cidade"
																		required="required" autocomplete="off"
																		value="${modelLogin.getLocalidade()}"> <span
																		class="form-bar"></span> <label class="float-label">Cidade</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="uf" id="uf"
																		class="form-control" placeholder="UF" required
																		autocomplete="off" value="${modelLogin.getUf()}">
																	<span class="form-bar"></span> <label
																		class="float-label">UF</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="login" id="login"
																		class="form-control" placeholder="Informe o login"
																		required autocomplete="off"
																		value="${modelLogin.getLogin()}"> <span
																		class="form-bar"></span> <label class="float-label">Login</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="password" name="senha" id="senha"
																		class="form-control" placeholder="Enter Password"
																		required autocomplete="off"
																		value="${modelLogin.getSenha()}"> <span
																		class="form-bar"></span> <label class="float-label">Senha</label>
																</div>
																<button type="button"
																	class="btn btn-primary waves-effect waves-light"
																	onclick="limparForm();">Novo</button>
																<button type="submit"
																	class="btn btn-success waves-effect waves-light">Salvar</button>
																<button type="button"
																	class="btn btn-info waves-effect waves-light"
																	onclick="deleteAjax();">Excluir</button>
																<!-- Button trigger modal -->
																<button type="button" class="btn btn-secondary"
																	data-toggle="modal" data-target="#consultaUsuario">
																	Consultar</button>

																<!-- Modal -->
																<div class="modal fade bd-example-modal-lg"
																	id="consultaUsuario" tabindex="-1" role="dialog"
																	aria-labelledby="consultaUsuarioLabel"
																	aria-hidden="true">
																	<div class="modal-dialog modal-lg" role="document">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h5 class="modal-title" id="consultaUsuarioLabel">Consulta
																					usuário</h5>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="input-group mb-3">
																					<input type="text" id="loginPesquisar"
																						class="form-control" placeholder="Login"
																						aria-label="Recipient's username"
																						aria-describedby="basic-addon2">
																					<div class="input-group-append">
																						<button class="btn btn-success" type="button" onclick="buscarUsuarioLogin();">Buscar</button>
																					</div>
																				</div>
																			</div>
																			<div style="overflow: scroll; height: 300px;">
																				<table id="tabela-consulta-usuario" class="table">
																					<thead>
																						<tr>
																							<th scope="col">Id</th>
																							<th scope="col">Nome</th>
																							<th scope="col">Mais informações</th>
																						</tr>
																					</thead>
																					<tbody>
																					</tbody>
																				</table>
																			</div>
																			<nav aria-label="Page navigation example">
																				<ul class="pagination" id="ulPaginacaoUserAjax"></ul>
																			</nav>

																			<span id="totalResponse"></span>
																			<div class="modal-footer">
																				<button type="button" class="btn btn-secondary"
																					data-dismiss="modal">Cancelar</button>
																			</div>
																		</div>
																	</div>
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>

										</div>
										<span id="msg">${msg}</span>
									</div>
									<br>
									<!-- Basic table card start -->
									<div class="card">
										<div class="card-header">
											<h5>Usuários</h5>
											<span>Abaixo todos os <code>usuários</code> gerenciados
											</span>
											<div class="card-header-right">
												<ul class="list-unstyled card-option">
													<li><i class="fa fa fa-wrench open-card-option"></i></li>
													<li><i class="fa fa-window-maximize full-card"></i></li>
													<li><i class="fa fa-minus minimize-card"></i></li>
													<li><i class="fa fa-refresh reload-card"></i></li>
													<li><i class="fa fa-trash close-card"></i></li>
												</ul>
											</div>
										</div>
										<div class="card-block table-border-style">
											<div class="table-responsive" style="overflow: scroll;">
												<table class="table" id="tabelaResultadosView">
													<thead>
														<tr>
															<th>Id</th>
															<th>Nome</th>
															<th>E-mail</th>
															<th>Login</th>
															<th>Mais informações</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listUsers}" var="ml">
															<tr>
																<td><c:out value="${ml.id}"></c:out></td>
																<td><c:out value="${ml.nome}"></c:out></td>
																<td><c:out value="${ml.email}"></c:out></td>
																<td><c:out value="${ml.login}"></c:out></td>
																<td><a
																	href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>

											<nav aria-label="Page navigation example">
												<ul class="pagination">
													<%
													int totalPagina = (int) request.getAttribute("totalPaginas");

													for (int i = 0; i < totalPagina; i++) {
														String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&de=" + (i * 5); //a partir do registro "de" mais 5 do offset, tb definido no DAORepository
														out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\">" + (i + 1) + "</a></li>");
													}
													%>
												</ul>
											</nav>
										</div>
									</div>
									<!-- Basic table card end -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="jsfile.jsp" />
	<jsp:include page="myjs_functions.jsp" />
</body>
</html>