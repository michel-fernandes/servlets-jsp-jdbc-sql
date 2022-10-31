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

function buscarUserPagAjax(dados) {
	
	var urlAction = document.getElementById('form-user').action;
	var loginBusca = document.getElementById('loginPesquisar').value;
	
	$
			.ajax(
					{
						method : "get",
						url : urlAction,
						data : dados,
						success : function(response, textStatus, xhr) {
	
							var json = JSON.parse(response);
	
							$('#tabela-consulta-usuario > tbody > tr').remove();
							$('#ulPaginacaoUserAjax > li').remove();
	
							for (var p = 0; p < json.length; p++) {
								$('#tabela-consulta-usuario > tbody')
										.append(
												'<tr> <td>'
														+ json[p].id
														+ '</td> <td> '
														+ json[p].nome
														+ '</td> <td><button onclick="verEditar('
														+ json[p].id
														+ ')" type="button" class="btn btn-info">Ver</button></td></tr>');
							}
	
							document.getElementById('totalResponse').textContent = 'Resultados: '	+ json.length;
	
							var totalPagina = xhr.getResponseHeader("totalPaginas");
	
							for (var p = 0; p < totalPagina; p++) {
	
								var dados = 'login='
										+ loginBusca
										+ '&acao=consultarUsuarioAjaxPage&de='
										+ (p * 5);
	
								$("#ulPaginacaoUserAjax")
										.append(
												'<li class="page-item"><a class="page-link" href="#" onclick="buscarUserPagAjax(\''
														+ dados
														+ '\')">'
														+ (p + 1)
														+ '</a></li>');
	
							}
	
						}
	
					}).fail(
					function(xhr, status, errorThrown) {
						alert('Erro ao buscar usuário por nome: '
								+ xhr.responseText);
					});
	
}

function buscarUsuarioLogin() {
	
       alert(document.getElementById('loginPesquisar').value);
       var loginBusca = document.getElementById('loginPesquisar').value;
        
       if (loginBusca != null && loginBusca != '' && loginBusca.trim() != ''){ /*Validando que tem que ter valor pra buscar no banco*/
        
        var urlAction = document.getElementById('form-user').action;
         
        $.ajax({
              
              method: "get",
              url : urlAction,
              data : "login=" + loginBusca + '&acao=consultarUsuarioAjax',
              
              success: function (response, textStatus, xhr) {
              
              var json = JSON.parse(response);
              
              
              $('#tabela-consulta-usuario > tbody > tr').remove();
              $('#ulPaginacaoUserAjax  > li').remove();
              
               for(var p = 0; p < json.length; p++){
                   $('#tabela-consulta-usuario > tbody').append(
                                                                   '<tr> <td>'+json[p].id+'</td> <td> '
                                                                   +json[p].nome
                                                                   +'</td> <td><button onclick="verEditar('+json[p].id+')" type="button" class="btn btn-info">Ver</button></td></tr>');
               }
               
               document.getElementById('totalResponse').textContent = 'Resultados: ' + json.length;
               
               var totalPagina = xhr.getResponseHeader("totalPaginas");
                 
               for (var p = 0; p < totalPagina; p++){
                       
               	var dados = 'login=' 
                            + loginBusca
                            + '&acao=consultarUsuarioAjaxPage&de='
                            + (p * 5);
                    
               	$("#ulPaginacaoUserAjax").append(
						               			'<li class="page-item"><a class="page-link" href="#" onclick="buscarUserPagAjax(\''
												+ dados
												+ '\')">'
												+ (p + 1)
												+ '</a></li>');
                       
                }
              
              }
              
          }).fail(function(xhr, status, errorThrown){
             alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
          });
     	}
}

function verEditar(id) {
	var urlAction = document.getElementById('form-user').action;
	window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
}

function visualizarImg(imagemBase64, fileFoto) {
	var preview = document.getElementById(imagemBase64);
	var fileUser = document.getElementById(fileFoto).files[0];
	var reader = new FileReader();

	reader.onloadend = function() {
		preview.src = reader.result; /*carrega a foto na tela*/
	};

	if (fileUser) {
		reader.readAsDataURL(fileUser); /*preview da imagem*/
	} else {
		preview.src = '';
	}
}

$(document).ready(function() {

	function limpa_formulário_cep() {
		// Limpa valores do formulário de cep.
		$("#rua").val("");
		$("#bairro").val("");
		$("#cidade").val("");
		$("#uf").val("");
		$("#ibge").val("");
	}

	//Quando o campo cep perde o foco.
	$("#cep")
			.blur(
					function() {

						//Nova variável "cep" somente com dígitos.
						var cep = $(this).val()
								.replace(/\D/g, '');

						//Verifica se campo cep possui valor informado.
						if (cep != "") {

							//Expressão regular para validar o CEP.
							var validacep = /^[0-9]{8}$/;

							//Valida o formato do CEP.
							if (validacep.test(cep)) {

								//Preenche os campos com "..." enquanto consulta webservice.
								$("#logradouro").val(
										"...");
								$("#bairro").val("...");
								$("#localidade").val(
										"...");
								$("#uf").val("...");

								//Consulta o webservice viacep.com.br/
								$
										.getJSON(
												"https://viacep.com.br/ws/"
														+ cep
														+ "/json/?callback=?",
												function(
														dados) {

													if (!("erro" in dados)) {
														//Atualiza os campos com os valores da consulta.
														$(
																"#logradouro")
																.val(
																		dados.logradouro);
														$(
																"#bairro")
																.val(
																		dados.bairro);
														$(
																"#localidade")
																.val(
																		dados.localidade);
														$(
																"#uf")
																.val(
																		dados.uf);
													} //end if.
													else {
														//CEP pesquisado não foi encontrado.
														limpa_formulário_cep();
														alert("CEP não encontrado.");
													}
												});
							} //end if.
							else {
								//cep é inválido.
								limpa_formulário_cep();
								alert("Formato de CEP inválido.");
							}
						} //end if.
						else {
							//cep sem valor, limpa formulário.
							limpa_formulário_cep();
						}
					});
});
</script>