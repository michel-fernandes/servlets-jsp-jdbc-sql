<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.ModelLogin" %>

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
	                                                                    <c:if test="${modelLogin.getImagem()!='' && modelLogin.getImagem()!=null}">
	                                                                    	<a href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadImagem&id=${modelLogin.getId()}">
	                                                                    		<img alt="Imagem do perfil" id="imagemBase64" 
	                                                                    		src="${modelLogin.getImagem()}" width="70px">
	                                                                    	</a>
	                                                                    </c:if>
	                                                                    <c:if test="${modelLogin.getImagem()=='' || modelLogin.getImagem()==null}">
	                                                                    	<img alt="Imagem do perfil" id="imagemBase64" 
	                                                                    	src="<%= request.getContextPath() %>/assets/images/bear.png" width="70px">
	                                                                    </c:if>
                                                                    </div>
                                                                    <input type="file" accept="images/*" id="fileFoto" name="fileFoto" 
                                                                    	onchange="visualizarImg('imagemBase64', 'fileFoto');" class="form-control-file" 
                                                                    	style="margin-top: 15px; margin-left: 5px;"/>
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
																  <input type="radio" name="sexo" value="MASCULINO" <% 
																		ModelLogin ml = (ModelLogin) request.getAttribute("modelLogin");
																		if (ml !=null && ml.getSexo().equals("MASCULINO")){
																			out.print(" ");
																			out.print("checked=\"checked\"");
																			out.print(" ");
																		} %>>Masculino</>
																  <input type="radio" name="sexo" value="FEMININO" <% 
																		if (ml !=null && ml.getSexo().equals("FEMININO")){
																			out.print(" ");
																			out.print("checked=\"checked\"");
																			out.print(" ");
																		} %>>Feminino</>
																</div>
																<div class="form-group form-default form-static-label">
																	<select name="perfil" id="perfil" class="form-control">
																		<option disabled="disabled">Selecione o perfil de usuário</option>
																		<option value="ADMIN"<% 
																		if (ml !=null && ml.getPerfil().contains("ADMIN")){
																			out.print(" ");
																			out.print("selected=\"selected\"");
																			out.print(" ");
																		} %>>Administrador</option>
																		<option value="DEVELOPER" <% 
																		if (ml !=null && ml.getPerfil().contains("DEVELOPER")){
																			out.print(" ");
																			out.print("selected=\"selected\"");
																			out.print(" ");
																		} %>>Developer</option>
																	</select><span
																		class="form-bar"></span> <label class="float-label">Perfil de usuário</label>
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
																						<button type="button"
																							class="btn btn-primary waves-effect waves-light"
																							onclick="buscarUsuarioLogin();">Pesquisar</button>
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
											<span>Abaixo todos os <code>usuários</code> do sistema
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
											<div class="table-responsive">
												<table class="table">
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
																<td><a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
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
	<script type="text/javascript">
		function deleteAjax() {
			if (confirm('Deseja excluir este usuário?')) {
				var urlAction = document.getElementById('form-user').action;
				var loginUser = document.getElementById('login').value;
				$.ajax({

					method : "get",
					url : urlAction,
					data : "&login=" + loginUser + "&acao=deletarAjax",
					success : function(response) {
						limparForm();
						document.getElementById("msg").textContent = response;
						buscarUsuarioLogin();
					}

				}).fail(
						function(xhr, status, errorThrow) {
							alert('Erro ao excluir o usuário por Login: '
									+ xhr.responseText);
						});
			}
		}
		function deleteJS() {
			if (confirm('Deseja excluir este usuário?')) {
				document.getElementById("form-user").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("form-user").submit();
			}
		}
		function limparForm() {
			var elementos = document.getElementById("form-user").elements;
			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
		function buscarUsuarioLogin() {
			var loginBusca = document.getElementById('loginPesquisar').value;
			var urlAction = document.getElementById('form-user').action;

			if (loginBusca != null && loginBusca != ''
					&& loginBusca.trim() != '') {
				$
						.ajax(
								{

									method : "get",
									url : urlAction,
									data : "&login=" + loginBusca
											+ "&acao=consultarUsuarioAjax",
									success : function(response) {
										const json = JSON.parse(response);
										console.info(response);
										console.info(json);
										$(
												'#tabela-consulta-usuario > tbody > tr')
												.remove();

										for (var p = 0; p < json.length; p++) {
											$(
													'#tabela-consulta-usuario > tbody')
													.append(
															'<tr><td>'
																	+ json[p].id
																	+ '</td><td>'
																	+ json[p].nome
																	+ '</td><td><button type="button" class="btn btn-link" onclick="verEditar('
																	+ json[p].id
																	+ ')">Ver</button></td></tr>');
										}

										document.getElementById('totalResponse').textContent = 'Resultados: '+ json.length;
									}

								})
						.fail(
								function(xhr, status, errorThrow) {
									alert('Erro ao consultar o usuário por Login: '
											+ xhr.responseText);
								});
			}
		}
		function verEditar(id) {
			var urlAction = document.getElementById('form-user').action;
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}
		function visualizarImg(imagemBase64, fileFoto){
			var preview = document.getElementById(imagemBase64);
			var fileUser = document.getElementById(fileFoto).files[0];
			var reader = new FileReader();
			
			reader.onloadend = function (){
				preview.src = reader.result; /*carrega a foto na tela*/
			};
			
			if(fileUser){
				reader.readAsDataURL(fileUser); /*preview da imagem*/
			}else{
				preview.src = '';
			}
		}
	</script>
</body>
</html>