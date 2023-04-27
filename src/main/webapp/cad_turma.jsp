<%-- 
    Document   : cad_turma
    Created on : 30 de jan. de 2023, 17:02:49
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
                                <li><a href="#">Lançar Nota</a></li>
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
                        <!-- <td>Código da Turma</td> -->
                        <th>Código da Turma</th>
                        <th>Série</th>
                        <th>Turma</th>   
                        <th>Ação</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>        

                    <c:forEach var="turma" items="${listaDeTurma}">
                        <tr>
                            <!--<td>${turma.turm_idturma}</td> -->
                            <th>${turma.turm_codturma} </th>
                            <th>${turma.turm_serie}</th>
                            <th>${turma.turm_turma}</th>

                            <td width="5%">

                                <button class="btn btn-success" data-toggle="modal" data-target="#updateModal"
                                        onclick="onUpdate(${turma.turm_idturma}, '${turma.turm_codturma}', '${turma.turm_serie}', '${turma.turm_turma}')"><i class="fas fa-edit"></i> Editar </button>
                            </td>
                            <td>
                                <!-- comment 
                                <form action="excluirpessoa" method="post">
                                    <input type="hidden" name="pes_IdPessoa" value="${turma.turm_idturma}"/>
                                    <button type="submit" class="btn btn-danger"><i class="far fa-trash-alt"></i> Excluir </button>
                                </form>
                                -->
                                <!-- ELIMINAR USUARIOS -->
                                <input type="hidden" id="codigo" value="${turma.turm_idturma}">
                                <a id="deletarturma" href="<c:url value="TurmaServlet">
                                       <c:param name="action" value="eliminarTurma" />
                                       <c:param name="cod" value="${turma.turm_idturma}" />
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
                            <h5 class="modal-title">Cadastro de Turmas</h5>

                        </div>
                        <div class="modal-body">
                            <form action="Turma">        

                                <div class="form-group">
                                    <input class="form-control" id="Codigo" name="Codigo" placeholder="Adicionar Código" maxlength="4" pattern="[0-9]*">
                                </div>

                                <div class="form-group">
                                    <input id="Serie" name="Serie" placeholder="Série" class="form-control input-md" required="" type="text" maxlength="24">
                                </div>

                                <div class="form-group">
                                    <input class="form-control" id="Turma" name="Turma" placeholder="Adicionar Turma">
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
                        <h5 class="modal-title">Atualizar Turmas</h5>

                    </div>
                    <div class="modal-body">
                        <form action="Turma">
                            <div class="form-group">
                                <input class="form-control" id="Codigo" name="Codigo" placeholder="Adicionar Código" maxlength="4" pattern="[0-9]*">
                            </div>

                            <div class="form-group">
                                <input id="Serie" name="Serie" placeholder="Série" class="form-control input-md" required="" type="text" maxlength="24">
                            </div>

                            <div class="form-group">
                                <input class="form-control" id="Turma" name="Turma" placeholder="Adicionar Turma">
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
        function onUpdate(id, Codigo, Serie, Turma) {

            var titleID = document.querySelector("#updateModal input[name=id]");
            var titleCod = document.querySelector("#updateModal input[name=Codigo]");
            var titletSerie = document.querySelector("#updateModal input[name=Serie]");
            var titleturma = document.querySelector("#updateModal input[name=Turma]");


            titleID.value = id;
            titleCod.value = Codigo;
            titletSerie.value = Serie;
            titleturma.value = Turma;

        }

        function id(el) {
            return document.getElementById(el);
        }

        $(function () {
            $("#Codigo").maskMoney({
                precision: 3,
                decimal: ''
            });
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
    <script src="js/funcaoTurma.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js">
    </script>
</body>

</html>
