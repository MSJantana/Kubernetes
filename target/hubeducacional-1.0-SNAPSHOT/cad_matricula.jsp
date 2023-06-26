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
        <scrip src="https://kit.fontawesome.com/26148a0259.js" crossorigin="anonymous"></script>
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
                
                <a id="read" href="<c:url value="Matriculas">
                       <c:param name="action" value="read" />

                   </c:url>"><button type="button" class="btn btn-outline-warning" data-toggle="tooltip"  title="Eliminar" data-original-title="Eliminar">
                        <i class="fas fa-sync-alt"></i> Atualizar Lista </button></a>



                <button type="button" class="btn btn-success add-new " data-toggle="modal" data-target="#createmodal" ><i class="fas fa-user-plus"></i>  Add New</button>
            </p>
            <table class="table">
                <thead>
                    <tr>                        
                        <th>Código da Turma</th>
                        <th>Nome do Aluno</th>
                        <th>Professor</th>
                        <th>Disciplina</th>
                        <th>Função</th>
                        <th>Escola</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="matricula" items="${matriculas}">
                        <tr> 
                            <!--<td>${matricula.mat_idMatricula}</td>-->
                            <td>${matricula.turma.turm_turma}</td>
                            <td>${matricula.pessoa.pes_nome}</td>
                            <td>${matricula.professor.prof_nome}</td>
                            <td width="8%">${matricula.curso.cur_descricao}</td>
                            <td>${matricula.funcao.func_nome}</td>
                            <td>${matricula.unidade.uni_siglaEscola}</td>

                            <td width="8%">

                                <!-- ELIMINAR USUARIOS -->
                                <input type="hidden" id="codigo" value="${matricula.mat_idMatricula}">
                                <a id="deletarMatricula" href="<c:url value="Matriculas">
                                       <c:param name="action" value="eliminarMatricula" />
                                       <c:param name="cod" value="${matricula.mat_idMatricula}" />
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
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h5 class="modal-title">Matricular Alunos</h5>
                        </div>
                        <div class="modal-body">
                            <form action="Matricula">      

                                <div class="form-group">
                                    <select class="form-control selectpicker" aria-label=".form-select-sm example" name="turm_turma" id="turma">
                                        <option selected>Selecione a Turma</option>
                                        <c:forEach items="${listaDeTurma}" var="turma">
                                            <option value="${turma.turm_idturma}">${turma.turm_turma}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select class="form-control selectpicker" aria-label=".form-select-sm example" name="pes_IdPessoa" id="pessoa">
                                        <option selected>Selecione nome do Aluno</option>
                                        <c:forEach items="${listaDePessoa}" var="pessoa">
                                            <option value="${pessoa.pes_IdPessoa}">${pessoa.pes_nome}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select class="form-control selectpicker" aria-label=".form-select-sm example" name="cur_idCurso" id="curso">
                                        <option selected>Selecione o Curso</option>
                                        <c:forEach items="${listaDeCursos}" var="curso">
                                            <option value="${curso.cur_idCurso}">${curso.cur_descricao}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select class="form-control selectpicker" aria-label=".form-select-sm example" name="func_idFuncao" id="funcao">
                                        <option selected>Selecione a Função</option>
                                        <c:forEach items="${listaDeFuncao}" var="funcao">
                                            <option value="${funcao.func_idFuncao}">${funcao.func_nome}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select class="form-control selectpicker" aria-label=".form-select-sm example" name="prof_idprofessor" id="professor">
                                        <option selected>Selecione o Professor</option>
                                        <c:forEach items="${listaDeProfessor}" var="professor">
                                            <option value="${professor.prof_idprofessor}">${professor.prof_nome}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <select class="form-control selectpicker" aria-label=".form-select-sm example" name="uni_idUnidade" id="unidade">
                                        <option selected>Selecione a Unidade</option>
                                        <c:forEach items="${listaDeUnidade}" var="unidade">
                                            <option value="${unidade.uni_idUnidade}">${unidade.uni_nomeUnidade}</option>
                                        </c:forEach>
                                    </select>
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

    <script src="js/sweetalert.js" type="text/javascript"></script>
    <script src="js/funcaoMatricula.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js">
    </script>
</body>

</html>
