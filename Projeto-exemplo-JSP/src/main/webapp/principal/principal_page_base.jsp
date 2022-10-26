<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
										<h1>Conteúdo das páginas do Projeto Java EE</h1>
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="jsfile.jsp" />
</body>
</html>