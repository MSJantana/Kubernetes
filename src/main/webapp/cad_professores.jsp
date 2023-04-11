<%-- 
    Document   : cad_professores
    Created on : 23 de jan. de 2023, 16:44:40
    Author     : Marcio Santana
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
                                <li><a href="#">Lançar Nota</a></li>
                                <li><a href="#">Something else here</a></li>
                             
                            </ul>
                        </li>
                        <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Professor</a>
                            <ul class="dropdown-menu">
                                <li><a href="Matriculas">Matricula<span class="sr-only">(current)</span></a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                              
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
                        <th>Titularidade</th>   
                        <th>Ação</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>        

                    <c:forEach var="professor" items="${listaDeProfessor}">
                        <tr>
                            <!--<td>${professor.prof_idprofessor}</td> -->
                            <th>${professor.prof_nome} </th>
                            <th>${professor.prof_cpf}</th>
                            <th>${professor.prof_titulacao}</th>

                            <td width="5%">

                                <button class="btn btn-success" data-toggle="modal" data-target="#updateModal"
                                        onclick="onUpdate(${professor.prof_idprofessor}, '${professor.prof_nome}', '${professor.prof_cpf}', '${professor.prof_titulacao}')"><i class="fas fa-edit"></i> Editar </button>
                            </td>
                            <td>
                                <!-- comment 
                                <form action="excluirpessoa" method="post">
                                    <input type="hidden" name="pes_IdPessoa" value="${professor.prof_idprofessor}"/>
                                    <button type="submit" class="btn btn-danger"><i class="far fa-trash-alt"></i> Excluir </button>
                                </form>
                                -->
                                <!-- ELIMINAR USUARIOS -->
                                <input type="hidden" id="codigo" value="${professor.prof_idprofessor}">
                                <a id="deleteProfessor" href="<c:url value="ProfessorServlet">
                                       <c:param name="action" value="eliminarProfessor" />
                                       <c:param name="cod" value="${professor.prof_idprofessor}" />
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
                            <h5 class="modal-title">Cadastro de Professores</h5>

                        </div>
                        <div class="modal-body">
                            <form action="Professor">        

                                <div class="form-group">
                                    <input class="form-control" id="Nome" name="Nome" placeholder="Adicionar Nome">
                                </div>

                                <div class="form-group">
                                    <input id="cpf" name="cpf" placeholder="CPF" class="form-control input-md" required="" type="text" maxlength="14">
                                </div>

                                <div class="form-group">
                                    <input class="form-control" id="titularidade" name="titularidade" placeholder="Adicionar Titulação">
                                </div>
                        </div>               


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
                        <h5 class="modal-title">Atualizar Professor</h5>

                    </div>
                    <div class="modal-body">
                        <form action="Professor">
                            <div class="form-group">
                                <input class="form-control" id="Nome" name="Nome" placeholder="Adicionar Nome">
                            </div>

                            <div class="form-group">
                                <input id="cpf" name="cpf" placeholder="CPF" class="form-control input-md" required="" type="text" maxlength="14">
                            </div>

                            <div class="form-group">
                                <input class="form-control" id="titularidade" name="titularidade" placeholder="Adicionar Titulação">
                            </div>
                    </div>                    
                    <div class="modal-footer">
                        <input type="hidden" name="id" value=""/>
                        <input type="hidden" name="action" value="update"/>
                        <button type="submit" class="btn btn-success">Atualizar</button>
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Fim do Modal UpDate -->

    <script>
        function onUpdate(id, Nome, cpf, titularidade) {

            var titleID = document.querySelector("#updateModal input[name=id]");
            var titleNome = document.querySelector("#updateModal input[name=Nome]");
            var titletcpf = document.querySelector("#updateModal input[name=cpf]");
            var titlettitularidade = document.querySelector("#updateModal input[name=titularidade]");


            titleID.value = id;
            titleNome.value = Nome;
            titletcpf.value = cpf;
            titlettitularidade.value = titularidade;

        }

        function id(el) {
            return document.getElementById(el);
        }

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


        function formatar(mascara, documento) {
            var i = documento.value.length;
            var saida = mascara.substring(0, 1);
            var texto = mascara.substring(i);

            if (texto.substring(0, 1) !== saida) {
                documento.value += texto.substring(0, 1);
            }

        }

    </script>
    <script src="js/sweetalert.js" type="text/javascript"></script>
    <script src="js/funcaoProfessores.js" type="text/javascript"></script>
</body>

</html>
