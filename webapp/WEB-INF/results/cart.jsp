<%@ page import="model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Carrello" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>VideoGame MarketPlace - Carrello</title>
    <script src="Logo_and_CSS/scripts.js"></script>
    <link rel="icon" type="image/x-icon" href="Logo_and_CSS/logo_vg_marketplace2.png">
</head>
<body>
    <%@include file="../../header2.jsp"%>

    <%
        List<Prodotto> prodotti = (List<Prodotto>) session.getAttribute("contenutoCart");
        if (request.getAttribute("CarrelloVuoto") == null){
    %>
            <h1 id="CarrelloTitle">IL TUO CARRELLO</h1>
            <table id="Carrello">
            <tr id="CarrelloHeader">
                <th class="ImgColumn"></th>
                <th class="DescriptionColumn"></th>
                <th class="InformationColumn SinglePriceColumn">Prezzo Singolo </th>
                <th class="InformationColumn ArticlesColumn"> Numero Articoli </th>
                <th class="InformationColumn">PREZZO TOTALE</th>
            </tr>
        <%
            int i=0;
            for (Prodotto p: prodotti){
        %>
            <tr class="ProdottoCarrello" id="Prodotto<%=p.getId()%>">
                <td class="ImgColumn">
                    <img src="images/rimuoviProdottoX.png" class="croceRimozioneProdotto" title="Rimuovi Prodotto dal carrello" id="<%=p.getId()%>">
                    <img src="./ImgServlet?id=<%=p.getId()%>&imgNumber=0" class="ImmagineProdotto">
                </td>
                <td class="DescriptionColumn"><a href="visualizzaProdotto?id=<%=p.getId()%>" class="AnchorCart"><%=p.getNome().toUpperCase()%></a></td>
                <td class="InformationColumn SinglePriceColumn"><%=p.getPrezzoAttuale()%> €</td>
                <td class="InformationColumn ArticlesColumn"><%=p.getQuantita()%></td>
                <td class="InformationColumn"><span id ="PrezzoColumn<%=p.getId()%>"><%=Math.round(p.getPrezzoCatalogo() * 100.0) / 100.0%></span> €</td>
            </tr>
        <%
            i++;
        }
        %>
        <input type="hidden" id="DifferentProductsNumber" value="<%=i%>">
    </table>
    <p id="TotalPrice">TOTALE:
        <span id="TotalPriceProd"><%=Math.round(prodotti.stream().map(x->x.getPrezzoCatalogo()).reduce(0.0F, (a, b) -> a + b) * 100.0)/100.0%></span> €</p>
    <div id="SvuotaCarrelloAndEffettuaOrdine">
        <button class="ButtonCart" id="SvuotaCarrello">
            Svuota Carrello</button>
        <a href="ControlloAccessoServlet" id="RiepilogoOrdine"><button class="ButtonCart" id="EffettuaOrdine">Effettua Ordine</button></a>
    </div>
    <%
        }

        else {
    %>
            <div id="CarrelloVuoto2">
                <h2>Il carrello è vuoto</h2>
                <p><a href="index.jsp" class="AnchorCart">Torna agli acquisti</a></p>
            </div>
    <% } %>
</div>
</body>
</html>