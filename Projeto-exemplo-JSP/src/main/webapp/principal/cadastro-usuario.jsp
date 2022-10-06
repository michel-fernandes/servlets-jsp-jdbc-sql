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
															<form class="form-material"
																action="<%=request.getContextPath()%>/ServletUsuarioController"
																method="post" id="form-user">
																<div class="form-group form-default form-static-label">
																	<input type="text" name="id" id="id"
																		class="form-control" placeholder="ID automático"
																		readonly="readonly" value="${modelLogin.getId()}">
																	<span class="form-bar"></span> <label
																		class="float-label">ID</label>
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
																<button class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
																<button class="btn btn-success waves-effect waves-light">Salvar</button>
																<button class="btn btn-info waves-effect waves-light">Excluir</button>
															</form>
														</div>
													</div>
												</div>
											</div>

										</div>
										<span>${msg}</span>
									</div>
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
function limparForm(){
	var elementos = document.getElementById("form-user").elements;
	for (p=0; p< elementos.length;p++){
		elementos[p].value = '';
	}
}	
</script>
</body>
</html>