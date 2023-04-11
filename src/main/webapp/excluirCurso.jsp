<%-- 
    Document   : cad_curso
    Created on : 30 de ago. de 2022, 16:31:31
    Author     : Marcio
--%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

        <title>Cadastro de Cursos</title>
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
                        <li><a href="#">Aluno</a></li>
                        <li><a href="#">Professor</a></li>
                        <li><a href="#">Secretaria</a></li>
                        <li><a class="nav-link" href="Curso">Cursos <span class="sr-only">(current)</span></a><li>
                        <li><a href="register.jsp">Registrar Usuário</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sair</a></li>
                        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> <%= session.getAttribute("username")%></a></li>
                    </ul>
                </div>
            </nav> 

            <!-- Menu Provisório -->


            <p align="right">
                <button type="button" class="btn btn-success add-new " data-toggle="modal" data-target="#createmodal" a href="cadastrarCurso.jsp" ><i class="fa fa-plus" ></i>  Add New</button>
            </p>
            <table class="table">
                <thead>
                    <tr>
                        <!-- <td>Código do Curso</td> -->
                        <td>Nome do Curso</td>
                    </tr>
                </thead>
                <tbody>        

                    <c:forEach var="curso" items="${listaDeCursos}">
                        <tr>
                            <!-- <td>${curso.cur_idCurso}</td> -->
                            <td>${curso.cur_descricao}</td>
                            <td>
                                <form action="action">
                                    <button type="button" class="btn btn-success"><i class="fas fa-edit"></i></button>
                                </form>

                                <form action="excluircurso" method="post">
                                    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
                                    <input type="hidden" name="cur_idCurso" value="${curso.cur_idCurso}"/>
                                    <input type="submit" class="btn btn-warning" value="Excluir Curso"/>
                                </form>

                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p style="color: red"> ${erro} </p>
            <!-- Modal -->

            <div class="modal fade" id="createmodal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Adicionar Curso</h4>
                        </div>
                        <div class="modal-body">
                            <form action="Curso">
                                <div class="form-group">
                                    <label for="title">Cadastro de Cursos</label>
                                    <input class="form-control" id="title" placeholder="Curso">
                                </div> 
                                <button type="submit" class="btn btn-success" data-dismiss="modal">Salvar</button>
                            </form>
                        </div>

                    </div>
                </div>

            </div>
        </div>

    </div>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status === "Sucesso") {
            swal("Congrats", "Accounts Created Successfully!!", "success");
        }


    </script>
</div>
</div>
</div>
</body>

</html>
