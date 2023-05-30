<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <script src="Logo_and_CSS/scripts.js"></script>
    <title>VIDEOGAME MARKETPLACE - SCHERMATA DI REGISTRAZIONE</title>
</head>
<body>
<%@include file="header2.jsp" %>
<%
    if (request.getAttribute("rf") != null){ %>
<script>
    $(document).ready(function(){
        alert("Username già presente. Si prega di riprovare");
    });
</script>
<%
    }
%>
<%
    if (request.getAttribute("passerror") != null){ %>
<script>
    $(document).ready(function(){
        alert("Password inserita non sicura. Si prega di riprovare");
    });
</script>
<%
    }
%>
<h2 class="h2class">Modulo di Registrazione</h2>
<form action="RegisterServlet" method="post">
    <label for="nome" class="logLabel">Inserisci il nome</label>
    <input type="text" id="nome" name="nome" class="logInput"><br>
    <label for="cognome" class="logLabel">Inserisci il cognome</label>
    <input type="text" id="cognome" name="cognome" class="logInput"><br>
    <label for="username" class="logLabel">Inserisci l'username</label>
    <input type="text" id="username" name="username" class="logInput"><br>
    <label for="password3" class="logLabel">Inserisci la password (8+ caratteri, deve contenere almeno una lettera, un numero e un carattere speciale)</label>
    <div id="password2">
    <input type="password" id="password3" name="password" onchange="validate()">
        <label for="hidePSWD2">Mostra Password</label>
        <input type="checkbox" onchange="hidePSWD()" id="hidePSWD2">
    <span id="passCheck">NESSUNA PASSWORD INSERITA</span></div>
    <label for="indirizzo" class="logLabel">Inserisci l'indirizzo</label>
    <input type="text" id="indirizzo" name="indirizzo" class="logInput" size="35"><br>
    <label for="citta" class="logLabel">Inserisci la città</label>
    <input type="text" id="citta" name="citta" class="logInput"><br>
    <label for="nazione" class="logLabel">Inserisci la nazione</label>
    <input type="text" id="nazione" name="nazione" class="logInput"><br>
    <input type="submit" value="REGISTRATI" id="logSubmit2">
</form>
<p class="regP">Sei già registrato?<a href="login.jsp" class="RegAnchor">Accedi qui!</a></p>

</body>
</html>
