<%@ page import="model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <link rel="stylesheet" type="text/css" href="Logo_and_CSS/stylesheet.css">
    <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="Logo_and_CSS/jquery.js"></script>
    <script src="Logo_and_CSS/resizing.js"></script>
</head>
<body>
<header>
    <a href="index.jsp"><img src="Logo_and_CSS/logo_vg_marketplace.png" class="logoImg"></a>
    <%
        Utente u = (Utente)session.getAttribute("user");
        if (u == null){
    %>
    <ul class="logRegList">
        <li class="profileItem"><a href="show_cart_servlet" class="cartprofileAnchor"><img src="Logo_and_CSS/cart.png" class="cartImg"></a>
        <li class="logRegItem" id="logButton"><a href="login.jsp" class="logRegAnchor"><img src="Logo_and_CSS/profile_icon2.png" class="loginIcon" id="noImg"> Login</a></li>
        <li class="logRegItem"><a href="register.jsp" class="logRegAnchor">Registrati</a></li>
    </ul>
    <ul class="logRegList2">
        <li class="logRegItem ReservedArea">Area Riservata</li>
        <ul class="logRegListVertical">
            <li class="profileItemVertical"><a href="show_cart_servlet" class="cartprofileAnchor cartAnchorWidth1000"><img src="Logo_and_CSS/cart.png" class="cartImg"> Carrello</a>
            <li class="logRegItemVertical" id="logButton2"><a href="login.jsp" class="logRegAnchor"><img src="Logo_and_CSS/profile_icon2.png" class="loginIcon" id="noImg2"> Login</a></li>
            <li class="logRegItemVertical"><a href="register.jsp" class="logRegAnchor">Registrati</a></li>
        </ul>
    </ul>
    <% }
    else if (u.isAdmin()){
    %>
    <ul class="logRegList">
        <li class="logRegItem"><a href="AccessoPannelloAmministratoreServlet" class="logRegAnchor">Pannello Amministratore</a></li>
        <li class="profileItem"><a href="show_profile_servlet" class="cartprofileAnchor">Ciao <%=u.getUsername()%></a></li>
        <li class="profileItem"><a href="show_cart_servlet" class="cartprofileAnchor"><img src="Logo_and_CSS/cart.png" class="cartImg"></a>
        <li class="logRegItem"><a href="exit" class="logRegAnchor">EXIT</a></li>
    </ul>
    <p class="profileItem profileName2"><a href="show_profile_servlet" class="cartprofileAnchor cartAnchorWidth1000">Ciao <%=u.getUsername()%></a></p>
    <ul class="logRegList2">
        <li class="logRegItem ReservedArea">Area Riservata</li>
        <ul class="logRegListVertical">
            <li class="logRegItemVertical profileName"><a href="show_profile_servlet" class="cartprofileAnchor cartAnchorWidth1000">Ciao <%=u.getUsername()%></a></li>
            <li class="logRegItemVertical"><a href="AccessoPannelloAmministratoreServlet" class="logRegAnchor">Pannello Amministratore</a></li>
            <li class="profileItemVertical"><a href="show_cart_servlet" class="cartprofileAnchor cartAnchorWidth1000"><img src="Logo_and_CSS/cart.png" class="cartImg"> Carrello</a>
            <li class="logRegItemVertical"><a href="exit" class="logRegAnchor">EXIT</a></li>
        </ul>
    </ul>
    <%
    }
    else {
    %>
    <ul class="logRegList">
        <li class="profileItem"><a href="show_cart_servlet" class="cartprofileAnchor"><img src="Logo_and_CSS/cart.png" class="cartImg"></a>
        <li class="profileItem"><a href="show_profile_servlet" class="cartprofileAnchor">Ciao <%=u.getUsername()%></a></li>
        <li class="logRegItem"><a href="exit" class="logRegAnchor">EXIT</a></li>
    </ul>
    <p class="profileItem profileName2"><a href="show_profile_servlet" class="cartprofileAnchor cartAnchorWidth1000">Ciao <%=u.getUsername()%></a></p>
    <ul class="logRegList2">
        <li class="logRegItem ReservedArea">Area Riservata</li>
        <ul class="logRegListVertical">
            <li class="logRegItemVertical profileName"><a href="show_profile_servlet" class="cartprofileAnchor cartAnchorWidth1000">Ciao <%=u.getUsername()%></a></li>
            <li class="profileItemVertical"><a href="show_cart_servlet" class="cartprofileAnchor cartAnchorWidth1000"><img src="Logo_and_CSS/cart.png" class="cartImg"> Carrello</a>
            <li class="logRegItemVertical"><a href="exit" class="logRegAnchor">EXIT</a></li>
        </ul>
    </ul>
    <%
        }
    %>
</header>
<h1 class="firsth1">VIDEOGAME MARKETPLACE</h1>
<hr>
</body>
</html>