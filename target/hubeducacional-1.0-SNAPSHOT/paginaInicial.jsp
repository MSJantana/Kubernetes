<%-- 
    Document   : paginaInicial
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

<title>Home</title>

<body classe="container mt-5">
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
                                <li><a href="#">Serviço</a></li>
                        </ul>
                    </li>
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Professor</a>
                        <ul class="dropdown-menu">
                            <li><a href="Matriculas">Matricula<span class="sr-only">(current)</span></a></li>
                            <li><a href="#">Lançar Nota</a></li>
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
                    <li>
                        <!-- comment   <a class="nav-link" href="Curso">Cursos <span class="sr-only">(current)</span></a><li>--> 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Registrar Usuário</a></li>
                    <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Olá, <%= session.getAttribute("username")%></a></li>
                </ul>
            </div>

    </div>


</body>

</html>
