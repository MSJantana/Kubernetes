<%-- 
    Document   : cad_unidade
    Created on : 30 de ago. de 2022, 16:31:31
    Author     : Marcio
--%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- JavaScript Bundle with Popper--> 
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>    
        <script src="https://kit.fontawesome.com/26148a0259.js" crossorigin="anonymous"></script>
        <link rel="shortcut icon" href="images/favicon.ico">
        <!-- Main css -->
        <link rel="stylesheet" href="fonts/material-icon/css/style_curso.css">
        <link href="js/sweetalert.css" rel="stylesheet" type="text/css"/>


    </head>
    <body classe="containercorpo mt-5">
        <div>
            <!-- Menu Provisório -->
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <img src="images/hublogo.png" alt="Avatar Logo" style="width:70px;" class="rounded-pill"> 
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="paginaInicial.jsp">Home</a></li>
                        <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Aluno</a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Disciplina<span class="sr-only">(current)</span></a></li>
                                <li><a href="#">Boletim</a></li>
                                <li><a href="#">Serviços</a></li>
                                
                            </ul>
                        </li>
                        <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Professor</a>
                            <ul class="dropdown-menu">
                                <li><a href="Matriculas">Matricula<span class="sr-only">(current)</span></a></li>
                                <li><a href="#">Lançar Notas</a></li>
                                <li><a href="#">Consultar Diário</a></li>
                                
                            </ul>
                        </li>
                        <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Secretaria</a>
                            <ul class="dropdown-menu">
                                <li><a href="Curso">Disciplina<span class="sr-only">(current)</span></a></li>
                                <li><a href="Pessoa">Alunos</a></li>
                                <li><a href="Turma">Turmas</a></li>
                                <li><a href="Professor">Orientador</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="Unidade">Unidade Escolar</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="Funcao">Função</a></li>
                            </ul>
                        </li>
                        <!-- comment <li><a class="nav-link" href="Curso">Cursos <span class="sr-only">(current)</span></a><li>-->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Registrar Usuário</a></li>
                        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Olá, <%= session.getAttribute("username")%></a></li>
                    </ul>
                </div>
            </nav> 

            <!-- Menu Provisório -->

            <p align="right">
                <button type="button" class="btn btn-success add-new " data-toggle="modal" data-target="#createmodal" ><i class="fa fa-plus" ></i>  Add New</button>
            </p>
            <table class="table">
                <thead>
                    <tr>
                        <!-- <td>Código do Curso</td> -->
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>R.A</th>
                        <th>E-mail</th>
                        <th>Ação</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>        

                    <c:forEach var="pessoa" items="${listaDePessoa}">
                        <tr>
                            <!--<td>${pessoa.pes_IdPessoa}</td> -->
                            <th>${pessoa.pes_nome} </th>
                            <th>${pessoa.pes_cpf}</th>
                            <th>${pessoa.pes_Ra}</th>
                            <th>${pessoa.pes_email}</th>

                            <td width="5%">

                                <button class="btn btn-success" data-toggle="modal" data-target="#updateModal"
                                        onclick="onUpdate(${pessoa.pes_IdPessoa}, '${pessoa.pes_Ra}', '${pessoa.pes_nome}', '${pessoa.pes_cpf}', '${pessoa.pes_datanascimento}', '${pessoa.pes_sexo}', '${pessoa.pes_telefone}', '${pessoa.pes_email}', '${pessoa.pes_cep}', '${pessoa.pes_endereco}', '${pessoa.pes_numero}', '${pessoa.pes_bairro}', '${pessoa.pes_estado}', '${pessoa.pes_cidade}')"><i class="fas fa-edit"></i> Editar </button>
                            </td>
                            <td>
                                <!-- comment 
                                <form action="excluirpessoa" method="post">
                                    <input type="hidden" name="pes_IdPessoa" value="${pessoa.pes_IdPessoa}"/>
                                    <button type="submit" class="btn btn-danger"><i class="far fa-trash-alt"></i> Excluir </button>
                                </form>
                                -->
                                <!-- ELIMINAR USUARIOS -->
                                <input type="hidden" id="codigo" value="${pessoa.pes_IdPessoa}">
                                <a id="deletePessoa" href="<c:url value="PessoaServlet">
                                       <c:param name="action" value="eliminarPessoa" />
                                       <c:param name="cod" value="${pessoa.pes_IdPessoa}" />
                                   </c:url>"><button type="button" class="btn btn-danger" data-toggle="tooltip"  title="Eliminar" data-original-title="Eliminar">
                                        <i class="far fa-trash-alt"></i> Excluir </button></a>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p style="color: red"> ${erro} </p>
            <!-- Modal Create -->
            <div class="modal fade" id="createmodal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Cadastro de Aluno</h5>

                        </div>
                        <div class="modal-body">
                            <form action="Pessoa">

                                <div class="form-group">
                                    <input id="RA" name="RA" placeholder="Adicionar RA" class="form-control input-md" required="" type="text" maxlength="8" pattern="[0-9]+$">
                                </div>

                                <div class="form-group">
                                    <input class="form-control" id="Nome" name="Nome" placeholder="Adicionar Nome">
                                </div>

                                <div class="form-group">
                                    <input id="cpf" name="cpf" placeholder="CPF" class="form-control input-md" required="" type="text" maxlength="14">
                                </div>

                                <div class="form-group">
                                    <input id="dtnasc" name="dtnasc" placeholder="DD/MM/AAAA" class="form-control input-md" required="" type="text" maxlength="10" OnKeyPress="formatar('##/##/####', this)" onBlur="showhide()">
                                </div>

                                <label class="col-md-1 control-label" for="radios">Sexo</label>
                                <div class="col-md-5"> 
                                    <label required="" class="radio-inline" for="radios-0" >
                                        <input name="sexo" id="sexo" value="Feminino" type="radio" required>
                                        Feminino
                                    </label> 
                                    <label class="radio-inline" for="radios-1">
                                        <input name="sexo" id="sexo" value="Masculino" type="radio">
                                        Masculino
                                    </label>
                                </div>                    

                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>

                                    <input id="telefone1" name="telefone1" class="form-control" placeholder="XX XXXXX-XXXX" required="" type="text" maxlength="15" />
                                </div>  
                                <br>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>

                                    <input id="email" name="email" class="form-control" placeholder="Adicionar Email" required="" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" />
                                </div>
                                <br>

                                <label class="col-md-1 control-label" for="CEP">CEP</label>
                                <div class="col-md-4">
                                    <input id="cep" name="cep" placeholder="Apenas números" class="form-control input-md" required="" value="" type="search" maxlength="8" pattern="[0-9]+$">
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-primary" onclick="pesquisacep(cep.value)">Buscar</button>
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon">Rua</span>
                                    <input id="rua" name="rua" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>
                                <br>

                                <label class="col-md-1 control-label" for="Nº">Nº</label>
                                <div class="col-md-1"style="width: 15%">

                                    <input id="numero" name="numero" class="form-control" placeholder="" required=""  type="text">
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon">Bairro</span>
                                    <input id="bairro" name="bairro" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>
                                <br>

                                <label class="col-md-2 control-label" for="Estado">Estado</label>
                                <div class="col-md-3">

                                    <input id="estado" name="estado" class="form-control" placeholder="" required=""  type="text">
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon">Cidade</span>
                                    <input id="cidade" name="cidade" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>
                                <br>


                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="hidden" name="action" value="create"/>
                                    <button type="submit" class="btn btn-success">Salvar</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Fim do Modal Create -->

            <!-- Modal Update -->
            <div class="modal fade" id="updateModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Atualizar Aluno</h5>

                        </div>
                        <div class="modal-body">
                            <form action="Pessoa">

                                <div class="form-group">
                                    <input id="RA" name="RA" placeholder="Adicionar RA" class="form-control input-md" required="" type="text" maxlength="8" pattern="[0-9]+$">
                                </div>

                                <div class="form-group">
                                    <input class="form-control" id="Nome" name="Nome" placeholder="Adicionar Nome">
                                </div>

                                <div class="form-group">
                                    <input id="cpf" name="cpf" placeholder="CPF" class="form-control input-md" required="" type="text" inputmode="numeric" maxlength="15">
                                </div>

                                <div class="form-group">
                                    <input id="dtnasc" name="dtnasc" placeholder="DD/MM/AAAA" class="form-control input-md" required="" type="text" maxlength="10" OnKeyPress="formatar('##/##/####', this)" onBlur="showhide()">
                                </div>

                                <label class="col-md-1 control-label" for="radios">Sexo</label>
                                <div class="col-md-5"> 
                                    <label required="" class="radio-inline" for="radios-0" >
                                        <input name="sexo" id="sexo" value="Feminino" type="radio" required>
                                        Feminino
                                    </label> 
                                    <label class="radio-inline" for="radios-1">
                                        <input name="sexo" id="sexo" value="Masculino" type="radio">
                                        Masculino
                                    </label>
                                </div>                    

                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>

                                    <input id="telefone1" name="telefone1" class="form-control" placeholder="XX XXXXX-XXXX" required="" type="text" maxlength="15" />
                                </div>  
                                <br>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>

                                    <input id="email" name="email" class="form-control" placeholder="Adicionar Email" required="" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" />
                                </div>
                                <br>

                                <label class="col-md-1 control-label" for="CEP">CEP</label>
                                <div class="col-md-4">
                                    <input id="cep" name="cep" placeholder="Apenas números" class="form-control input-md" required="" value="" type="search" maxlength="8" pattern="[0-9]+$">
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-primary" onclick="pesquisacep(cep.value)">Buscar</button>
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon">Rua</span>
                                    <input id="rua" name="rua" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>
                                <br>

                                <label class="col-md-1 control-label" for="Nº">Nº</label>
                                <div class="col-md-1"style="width: 15%">

                                    <input id="numero" name="numero" class="form-control" placeholder="" required=""  type="text">
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon">Bairro</span>
                                    <input id="bairro" name="bairro" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>
                                <br>

                                <label class="col-md-2 control-label" for="Estado">Estado</label>
                                <div class="col-md-3">

                                    <input id="estado" name="estado" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon">Cidade</span>
                                    <input id="cidade" name="cidade" class="form-control" placeholder="" required="" readonly="readonly" type="text">
                                </div>
                                <br>
                                <input type="hidden" name="id" value=""/>
                                <input type="hidden" name="action" value="update"/>
                                <button type="submit" class="btn btn-success">Atualizar</button>
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Fim do Modal UpDate -->

            <script>
                function onUpdate(id, RA, Nome, cpf, dtnasc, sexo, telefone1, email, cep, rua, numero, bairro, estado, cidade) {

                    var titleID = document.querySelector("#updateModal input[name=id]");
                    var titleRA = document.querySelector("#updateModal input[name=RA]");
                    var titleNome = document.querySelector("#updateModal input[name=Nome]");
                    var titletcpf = document.querySelector("#updateModal input[name=cpf]");
                    var titletdtnasc = document.querySelector("#updateModal input[name=dtnasc]");
                    var titletsexo = document.querySelector("#updateModal input[name=sexo]");
                    var titlettelefone = document.querySelector("#updateModal input[name=telefone1]");
                    var titletemail = document.querySelector("#updateModal input[name=email]");
                    var titletcep = document.querySelector("#updateModal input[name=cep]");
                    var titletendereco = document.querySelector("#updateModal input[name=rua]");
                    var titletenumero = document.querySelector("#updateModal input[name=numero]");
                    var titletbairro = document.querySelector("#updateModal input[name=bairro]");
                    var titleteestado = document.querySelector("#updateModal input[name=estado]");
                    var titletcidade = document.querySelector("#updateModal input[name=cidade]");

                    titleID.value = id;
                    titleRA.value = RA;
                    titleNome.value = Nome;
                    titletcpf.value = cpf;
                    titletdtnasc.value = dtnasc;
                    titletsexo.value = sexo;
                    titlettelefone.value = telefone1;
                    titletemail.value = email;
                    titletcep.value = cep;
                    titletendereco.value = rua;
                    titletenumero.value = numero;
                    titletbairro.value = bairro;
                    titleteestado.value = estado;
                    titletcidade.value = cidade;
                }

                /* Máscaras ER */
                function mascara(o, f) {
                    v_obj = o;
                    v_fun = f;
                    setTimeout("execmascara()", 1);
                }
                function execmascara() {
                    v_obj.value = v_fun(v_obj.value);
                }
                function mtel(v) {
                    v = v.replace(/\D/g, ""); //Remove tudo o que não é dígito
                    v = v.replace(/^(\d{2})(\d)/g, "($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
                    v = v.replace(/(\d)(\d{4})$/, "$1-$2"); //Coloca hífen entre o quarto e o quinto dígitos
                    return v;
                }
                function id(el) {
                    return document.getElementById(el);
                }
                window.onload = function () {
                    id('telefone1').onkeyup = function () {
                        mascara(this, mtel);
                    };
                };
                document.getElementById("cpf").addEventListener("input", function () {
                    var i = document.getElementById("cpf").value.length;
                    var str = document.getElementById("cpf").value;
                    if (isNaN(Number(str.charAt(i - 1)))) {
                        document.getElementById("cpf").value = str.substr(0, i - 1);
                    }
                });
                document.addEventListener('keydown', function (event) {
                    if (event.keyCode !== 46 && event.keyCode !== 8) {
                        var i = document.getElementById("cpf").value.length;
                        if (i === 3 || i === 7)
                            document.getElementById("cpf").value = document.getElementById("cpf").value + ".";
                        else if (i === 11)
                            document.getElementById("cpf").value = document.getElementById("cpf").value + "-";
                    }
                });


                function limpa_formulario_cep() {
                    //Limpa valores do formulário de cep.
                    document.getElementById('rua').value = ("");
                    document.getElementById('bairro').value = ("");
                    document.getElementById('cidade').value = ("");
                    document.getElementById('estado').value = ("");

                }

                function meu_callback(conteudo) {
                    if (!("erro" in conteudo)) {
                        //Atualiza os campos com os valores.
                        document.getElementById('rua').value = (conteudo.logradouro);
                        document.getElementById('bairro').value = (conteudo.bairro);
                        document.getElementById('cidade').value = (conteudo.localidade);
                        document.getElementById('estado').value = (conteudo.uf);
                    } //end if.
                    else {
                        //CEP não Encontrado.
                        limpa_formulario_cep();
                        alert("CEP não encontrado.");
                        document.getElementById('cep').value = ("");
                    }
                }

                function pesquisacep(valor) {

                    //Nova variável "cep" somente com dígitos.
                    var cep = valor.replace(/\D/g, '');

                    //Verifica se campo cep possui valor informado.
                    if (cep !== "") {

                        //Expressão regular para validar o CEP.
                        var validacep = /^[0-9]{8}$/;

                        //Valida o formato do CEP.
                        if (validacep.test(cep)) {

                            //Preenche os campos com "..." enquanto consulta webservice.
                            document.getElementById('rua').value = "...";
                            document.getElementById('bairro').value = "...";
                            document.getElementById('cidade').value = "...";
                            document.getElementById('estado').value = "...";

                            //Cria um elemento javascript.
                            var script = document.createElement('script');

                            //Sincroniza com o callback.
                            script.src = '//viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

                            //Insere script no documento e carrega o conteúdo.
                            document.body.appendChild(script);

                        } //end if.
                        else {
                            //cep é inválido.
                            limpa_formulario_cep();
                            alert("Formato de CEP inválido.");
                        }
                    } //end if.
                    else {
                        //cep sem valor, limpa formulário.
                        limpa_formulario_cep();
                    }
                }

                function formatar(mascara, documento) {
                    var i = documento.value.length;
                    var saida = mascara.substring(0, 1);
                    var texto = mascara.substring(i);

                    if (texto.substring(0, 1) !== saida) {
                        documento.value += texto.substring(0, 1);
                    }

                }

                function idade() {
                    var data = document.getElementById("dtnasc").value;
                    var dia = data.substr(0, 2);
                    var mes = data.substr(3, 2);
                    var ano = data.substr(6, 4);
                    var d = new Date();
                    var ano_atual = d.getFullYear(),
                            mes_atual = d.getMonth() + 1,
                            dia_atual = d.getDate();

                    ano = +ano,
                            mes = +mes,
                            dia = +dia;

                    var idade = ano_atual - ano;

                    if (mes_atual < mes || mes_atual === mes_aniversario && dia_atual < dia) {
                        idade--;
                    }
                    return idade;
                }

                function exibe(i) {

                    document.getElementById(i).readOnly = true;

                }

                function desabilita(i) {

                    document.getElementById(i).disabled = true;
                }
                function habilita(i)
                {
                    document.getElementById(i).disabled = false;
                }


            </script>
            <script src="js/sweetalert.js" type="text/javascript"></script>
            <script src="js/funcaoPessoa.js" type="text/javascript"></script>
    </body>

</html>
