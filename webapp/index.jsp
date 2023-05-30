<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>VIDEOGAME MARKETPLACE</title>
    <link rel="icon" type="image/x-icon" href="Logo_and_CSS/logo_vg_marketplace2.png">
</head>
<body>
    <%@include file="header.jsp"%>
    <div id="corpo">
        <ul id="menu">
            <li> <a href="#">Sony</a>
                <ul class="submenu">
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=PS5&Categoria=Any">PS5</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=PS4&Categoria=Any">PS4</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=PS3&Categoria=Any">PS3</a></li>
                </ul>
            </li>
            <li><a href="#">Microsoft</a>
                <ul class="submenu">
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=XBOX+Series&Categoria=Any">XBOX Series</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=XBOX+One&Categoria=Any">XBOX One</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=XBOX+360&Categoria=Any">XBOX 360</a></li>
                </ul>
            </li>
            <li><a href="#">Nintendo</a>
                <ul class="submenu">
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=Nintendo+Switch&Categoria=Any">Nintendo Switch</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=Nintendo+DS&Categoria=Any">Nintendo DS</a></li>
                </ul>
            </li>
            <li><a href="#">Retrogaming platforms</a>
                <ul class="submenu">
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=PS2&Categoria=Any">PS2</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=PS1&Categoria=Any">PS1</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=XBOX&Categoria=Any">XBOX</a></li>
                    <li><a href="ricerca_prodotto_servlet?prodotto=&Piattaforma=Nintendo+Gamecube&Categoria=Any">Nintendo Gamecube</a></li>
                </ul>
            </li>
        </ul>
        <div id="ImageDiv"></div>
        <p id="ImageDescription">
            <img src="images/backArrow_white.png" id="imgBefore">
            <img src="images/forwardArrowWhite.png" id="imgAfter">
            <span id ="Description">SCOPRI IL MIGLIOR GIOCO AUTOMOBILISTICO PER PS4 - PS5! <br>GRAN TURISMO 7 DISPONIBILE ORA!</span>
        </p>
    </div>
    <span id="MenuSize1000"></span>
    <div id="ServicesDiv">
        <ul>
        <li class="Services BorderService">
            <img src="images/console-retrogaming.jpg" class="ServicesImage">
            <h2>SEI ALLA RICERCA DI UN GIOCO O DI UNA CONSOLE RETRO'? SEI NEL POSTO GIUSTO</h2>
            <p>Qui troverai un catalogo di giochi retrò molto vasto.<br>
                Intere aree dedicate a PS2, PS1, Nintendo GameCube, Commodore 64 e tanto altro.<br>
                Se invece sei alla ricerca di qualcosa di moderno, non disperare: <br>
                abbiamo tutto ciò di cui puoi avere bisogno per PS5, PS4, PS3, PSP, Xbox Series e Nintendo Switch,
                oltre al vasto assortimento di prodotti per PC.</p>
        </li>
        <li class = "Services BorderService">
            <img src="images/Green-Check-Mark.png" class="ServicesImage">
            <h2>PRODOTTI NUOVI  E USATI DI QUALITA' GARANTITI</h2>
            <p>Acquistando un nostro prodotto, anche usato, non dovrai preoccuparti delle sue condizioni. <br>Infatti, tutti i nostri
            prodotti, usati o nuovi che siano, vengono sottoposti a restrittivi controlli di qualità.<br>
            Inoltre, in caso di prodotto danneggiato o di guasto sopraggiunto entro un anno dalla data di acquisto,
                è possibile richiedere un rimborso totale con spedizione del
            prodotto a nostro carico.</p>
        </li>
        <li class = "Services">
            <img src="images/soddisfatti-rimborsati.png" class="ServicesImage">
            <h2>SU OGNI ACQUISTO, GARANZIA SODDISFATTI O RIMBORSATI</h2>
            <p>Entro un mese dalla data di acquisto, è possibile ricevere un rimborso per un acquisto per qualsiasi motivo.<br>
            (Categorie escluse dalla promo: Servizi, giochi in formato codice)<br>
            (Spese di spedizione a carico del cliente)</p>
        </li>
        </ul>
    </div>
</body>
</html>