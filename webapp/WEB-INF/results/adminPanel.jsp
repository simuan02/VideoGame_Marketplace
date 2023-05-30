<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>VideoGame MarketPlace - Pannello Amministratore</title>
    <link rel="icon" type="image/x-icon" href="Logo_and_CSS/logo_vg_marketplace2.png">
</head>
<body>
    <%@include file="../../header2.jsp"%>

    <%
        if (request.getAttribute("ImmagineInserita")!=null){
            %>
    <script>alert("Immagine inserita con successo")</script>
    <%
        }
    %>

    <script src="Logo_and_CSS/pannelloControllo.js"></script>
    <div id="ProfileMenuContainer">
        <ul id="ProfileMenu">
            <li id="GestisciUtenti" class="ActiveSelector ProfileSelector">Gestisci Utenti</li>
            <li id="GestisciOrdini" class="ProfileSelector">Gestisci Ordini</li>
            <li id="GestisciProdotti" class="ProfileSelector">Gestisci Prodotti</li>
        </ul>
    </div>
    <div id="ProfileInfo">
        <div id="UsersDiv" class="GestioneDiv">
            <h2 class="DivTitle">Gestione Utenti</h2>
            <select id="SelezionaUtente" class="SelectAdmin">
                <option value="0">--Seleziona--</option>
            </select>
            <div id="UserInfo">
            </div>
            <div id="ConfirmButtons1">
                <button class="ButtonCart ButtonAdmin" id="SaveModify1">Salva</button>
                <button class='ButtonCart ButtonAdmin' id='CancelModify1'>Annulla</button>
                <button class="ButtonCart ButtonAdmin" id="EliminaUtente">Elimina Utente</button>
            </div>
            <button id="MostraInfoUser" class="ButtonCart ButtonAdmin">Mostra Info - Modifica</button>
        </div>
        <div id="OrderDiv" class="GestioneDiv">
            <h2 class="DivTitle">Gestione Ordini</h2>
            <select id="SelezionaOrdine" class="SelectAdmin">
                <option value="0">--Seleziona--</option>
            </select>
            <div id="OrderSelectedInfo"></div>
            <div id="ConfirmButtons2">
                <button class="ButtonCart ButtonAdmin" id="SaveModify2">Salva</button>
                <button class='ButtonCart ButtonAdmin' id='CancelModify2'>Annulla</button>
                <button class="ButtonCart ButtonAdmin" id="EliminaOrdine">Elimina Ordine</button>
            </div>
            <button id="MostraInfoOrdine" class="ButtonCart ButtonAdmin">Mostra Info - Modifica</button>
        </div>
        <div id="ProductsDiv" class="GestioneDiv">
            <h2 class="DivTitle">Gestione Prodotti</h2>
            <select id="SelezionaProdotto" class="SelectAdmin">
                <option value="0">--Seleziona--</option>
            </select>
            <div id="ProductSelectedInfo"></div>
            <div id="GestioneProd">
                <button id="MostraInfoProdotto" class="ButtonCart ButtonAdmin">Mostra Info - Modifica</button>
                <button id="InsertNewProdotto" class="ButtonCart ButtonAdmin">Inserisci un nuovo prodotto</button>
                <button id="InsertNewImage" class="ButtonCart ButtonAdmin">Inserisci un'immagine associata</button>
            </div>
            <div id="ConfirmButtons3">
                <button class="ButtonCart ButtonAdmin" id="SaveModify3">Salva</button>
                <button class='ButtonCart ButtonAdmin' id='CancelModify3' onclick="annullaRichiesta3()">Annulla</button>
                <button class="ButtonCart ButtonAdmin" id="EliminaProdotto">Elimina Prodotto</button>
            </div>
        </div>
    </div>
</body>
</html>
