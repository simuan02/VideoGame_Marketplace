
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>VideoGame Marketplace - Schermata di Login</title>
    <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="Logo_and_CSS/jquery.js"></script>
</head>
<body>
    <%@include file="header2.jsp" %>

    <form action="loginServlet" method="post">
        <label for="username" class="logLabel">Inserisci l'username</label>
         <input type="text" id="username" name="username" class="logInput"><br>
        <label for="password" class="logLabel">Inserisci la password</label>
        <input type="password" id="password" name="password" class="logInput"><br>
        <input type="submit" value="ACCEDI" id="logSubmit">
    </form>
    <p class="regP">Non sei registrato? <a href="register.jsp" class="RegAnchor">Registrati qui!</a></p>
    <%
        if (request.getAttribute("lf") != null){ %>
    <script>
        $(document).ready(function(){
            alert("Username o Password errati. Si prega di riprovare");
        });
    </script>
    <%
        }
    %>
    <%
        if (request.getAttribute("loginError") != null){ %>
    <script>
        $(document).ready(function(){
            alert("Per continuare con l'ordine, effettuare prima l'accesso.");
        });
    </script>
    <%
        }
    %>
</body>
</html>
