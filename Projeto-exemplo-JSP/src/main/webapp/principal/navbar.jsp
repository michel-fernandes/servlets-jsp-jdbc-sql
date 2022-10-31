<nav class="navbar header-navbar pcoded-header">
	<div class="navbar-wrapper">
		<div class="navbar-logo">
			<a class="mobile-menu waves-effect waves-light" id="mobile-collapse"
				href="#!"> <i class="ti-menu"></i>
			</a>
			<a href="<%= request.getContextPath() %>/principal/principal.jsp"> <img width= "40 px" class="img-fluid"
				src="<%= request.getContextPath() %>/assets/images/logo.png" alt="Projeto Java EE" />
			</a> <a class="mobile-options waves-effect waves-light"> <i
				class="ti-more"></i>
			</a>
		</div>

		<div class="navbar-container container-fluid">
			<ul class="nav-left">
				<li><a href="#!" onclick="javascript:toggleFullScreen()"
					class="waves-effect waves-light"> <i class="ti-fullscreen"></i>
				</a></li>
			</ul>
			<ul class="nav-right">
				<li class="user-profile header-notification"><a href="#!"
					class="waves-effect waves-light"> 
					<c:if test="${modelLogado.getImagem()!='' && modelLogado.getImagem()!=null}">
						<img class="img-80 img-radius" src="${modelLogado.getImagem()}">
						</c:if> 
						<c:if test="${modelLogado.getImagem()=='' || modelLogado.getImagem()==null}">
							<img class="img-80 img-radius" src="<%=request.getContextPath()%>/assets/images/bear.png">
						</c:if><span><%= session.getAttribute("usuario") %></span> <i
						class="ti-angle-down"></i>
				</a>
					<ul class="show-notification profile-notification">
						<li class="waves-effect waves-light"><a
							href="ServletLogin?acao=logout"> <i
								class="ti-layout-sidebar-left"></i> Sair
						</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>