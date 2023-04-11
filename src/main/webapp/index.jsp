<%-- 
    Document   : index
    Created on : 24 de ago. de 2022, 09:43:14
    Author     : Marcio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/26148a0259.js" crossorigin="anonymous"></script>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

        <title>Login</title>
         <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="stylesheet" href="fonts/material-icon/css/style.css">
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>   
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" integrity="sha512-rRQtF4V2wtAvXsou4iUAs2kXHi3Lj9NE7xJR77DE7GHsxgY9RTWy93dzMXgDIG8ToiRTD45VsDNdTiUagOFeZA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.css" integrity="sha512-9j3GcomYXBnTpYYxgqV2U8wxkJ8tWkzz55b/LDhX1RYQ4DF36slQJQt+OXj9W28KEsZlJYUTck0X6IeE+IiP8Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
        <div class="container">
            <div class="content first-content">
                <div class="first-column">
                    <h2 class="title title-primary">EDUCAÇÃO EAD</h2>
                    <p class="description description-primary">PowerBy MSOFT</p>

                </div>
                <div class="second-column">
                    <h2 class="title title-second">HUB EDUCACIONAL</h2>      
                    <p class="description description-second">iniciar a sessão</p>
                    <div class="signin-form">
                        <form method="post" action="login" class="register-form"
                              id="login-form">
                            <div class="form-group">
                                <label for="username"><i
                                        class="zmdi zmdi-account material-icons-name"></i></label> <input
                                    type="text" name="username" id="username"
                                    placeholder="Your Name" />
                            </div>
                            <div class="form-group">
                                <label for="password"><i class="zmdi zmdi-lock"></i></label> <input
                                    type="password" name="password" id="password"
                                    placeholder="Password" />
                            </div>
                            
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin"
                                       class="form-submit" value="Log in"/>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- JS -->              
                <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

                <script type="text/javascript">
                    var status = document.getElementById("status").value;
                    if (status === "Failed") {
                        swal("Sorry", "Wrong Username or Password!!", "error");
                    }


                </script>
                </body>
            </div>
</html>
