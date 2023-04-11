<%-- 
    Document   : cad_unidade
    Created on : 30 de ago. de 2022, 16:31:31
    Author     : Marcio
--%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if(session.getAttribute("username")==null){
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
                                <li><a href="Pessoa">Aluno</a></li>
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
                        <th>Nome da Unidade</th>
                        <th>Telefone</th>
                        <th>Sigla da Escola</th>
                         <th>Ação</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>        

                    <c:forEach var="unidade" items="${listaDeUnidade}">
                        <tr>
                            <!--<td>${unidade.uni_idUnidade}</td> -->
                            <th>${unidade.uni_nomeUnidade} </th>
                            <th>${unidade.uni_telefone}</th>
                            <th>${unidade.uni_siglaEscola}</th>
                            <td width="5%">

                                <button class="btn btn-success" data-toggle="modal" data-target="#updateModal"
                                        onclick="onUpdate(${unidade.uni_idUnidade}, '${unidade.uni_nomeUnidade}', '${unidade.uni_telefone}', '${unidade.uni_siglaEscola}')"><i class="fas fa-edit"></i> Editar </button>
                            </td>
                            <td>
                                <form action="excluirUnidade" method="post">
                                    <input type="hidden" name="uni_idUnidade" value="${unidade.uni_idUnidade}"/>
                                    <button type="submit" class="btn btn-danger"><i class="far fa-trash-alt"></i> Excluir </button>
                                </form>
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
                            <h5 class="modal-title">Adicionar Unidade</h5>

                        </div>
                        <div class="modal-body">
                            <form action="Unidade">

                                <div class="form-group">
                                    <input class="form-control" id="title" name="title" placeholder="Adicionar Unidade">
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>

                                    <input id="telefone" name="telefone" class="form-control" placeholder="XX XXXXX-XXXX" required="" type="text" maxlength="15" />
                                </div>
                                <br>
                                <div class="form-group">
                                    <input class="form-control" id="sigla" name="sigla" placeholder="Adicionar Sigla">
                                </div>
                                <div>

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
                            <h5 class="modal-title">Atualizar Unidade</h5>

                        </div>
                        <div class="modal-body">
                            <form action="Unidade">

                                <div class="form-group">

                                    <input class="form-control" id="title" name="title">
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>

                                    <input id="telefone" name="telefone" class="form-control" placeholder="XX XXXXX-XXXX" required="" type="text" maxlength="15" />
                                </div>
                                <br>
                                <div class="form-group">
                                    <input class="form-control" id="sigla" name="sigla" placeholder="Adicionar Sigla">
                                </div>
                                <input type="hidden" name="id" value=""/>
                                <input type="hidden" name="action" value="update"/>
                                <button type="submit" class="btn btn-success">Atualizar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Fim do Modal UpDate -->

            <script>
                function onUpdate(id, title,telefone,sigla) {

                    var titleID = document.querySelector("#updateModal input[name=id]");
                    var titleDesc = document.querySelector("#updateModal input[name=title]");
                    var titletelefone = document.querySelector("#updateModal input[name=telefone]");
                    var titlesigla = document.querySelector("#updateModal input[name=sigla]");

                    titleID.value = id;
                    titleDesc.value = title;
                    titletelefone.value = telefone;
                    titlesigla.value = sigla;

                }
                
                /* Máscaras ER */
                function mascara(o, f) {
                    v_obj = o
                    v_fun = f
                    setTimeout("execmascara()", 1)
                }
                function execmascara() {
                    v_obj.value = v_fun(v_obj.value)
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
                    id('telefone').onkeyup = function () {
                        mascara(this, mtel);
                    }
                }

            </script>
    </body>

</html>
