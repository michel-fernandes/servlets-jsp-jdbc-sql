<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<title>Projeto JSP</title>
<style type="text/css">
form{
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
}
h5{
	position: absolute;
	top: 35%;
	left: 33%;
	right: 33%;
}
.msg{
	position: absolute;
	top: 90%;
	left:2%;
	right: 33%;
	font-size: 15px;
	color: red;
}
</style>
</head>
<body>
	<h5>Bem vindo ao Projeto JSP</h5>
	<form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<div class="col-md-6">
			<input type="hidden" value=<%=request.getParameter("url")%>
				name="url"> 
			<label class="form-label">Login</label> 
			<input name="login"	type="text" class="form-control" required>
			<div class="invalid-feedback">
        	Informe o login.
      		</div>
		</div>
		<div class="col-md-6">
			<label class="form-label">Senha</label>
			<input name="senha" type="password" class="form-control" required>
			<div class="invalid-feedback">
        	Informe a senha.
      		</div>
		</div>
		<div class="mb-3">
			<input type="submit" value="Acessar" style="width: 100%" class="btn btn-primary mb-3">
	        <div class="alert alert-danger" role="alert">${msg}</div>
	        <h4 class="msg"></h4>
	    </div>
	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous">
	</script>
	<script type="text/javascript">
	(() => {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  const forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }

		      form.classList.add('was-validated')
		    }, false)
		  })
		})()
	</script>
</body>
</html>