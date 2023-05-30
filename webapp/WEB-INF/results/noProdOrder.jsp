<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>VideoGame Marketplace - Errore nell'ordine</title>
    <link rel="icon" type="image/x-icon" href="Logo_and_CSS/logo_vg_marketplace2.png">
</head>
<body>
    <%@include file="../../header2.jsp"%>
    <h2 id="CarrelloTitle"><img src="images/xrossa.png" id="ImageOrder"> Errore nell'ordine</h2>
    <div style="margin-left: 3%">
    <%
        if (request.getAttribute("ProdError") != null) {
    %>
    <p>Non è presente nessun articolo nel carrello, quindi non è possibile effettuare l'ordine.</p>
    <%
        ;}
        if (request.getAttribute("noCredit") != null){
    %>
        <p>Credito utente non sufficiente. Effettuare una ricarica per proseguire con l'ordine</p>
    <% ;
        }
        if (request.getAttribute("prodottiNonDisponibili") != null){
            %>
            <script>alert(<%=request.getAttribute("prodottiNonDisponibili").toString()%>)</script>
        <%
            String prodottiNoDisp = request.getAttribute("prodottiNonDisponibili").toString();
        %>
            <p>Alcuni prodotti nel carrello non sono disponibili nelle quantità indicate.<br>Essi sono: <%=prodottiNoDisp%></p>
        <% ;} %>
    </div>
    <p class="IndexP"><a href="index.jsp" class="AnchorCart">Torna alla home</a></p>
</body>
</html>
