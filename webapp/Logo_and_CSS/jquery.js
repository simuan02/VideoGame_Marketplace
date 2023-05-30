var buttonPressed = 1;
var slidingAdvancedWindow = 1;

function activateChangeImg (){
        window.setInterval(function () {
            buttonPressed++;
            switch (buttonPressed % 3) {
                case 0:
                    $("#ImageDiv").css({"background-image": 'url("images/fifa22.jpeg")'});
                    $("#Description").html("BATTI IL CALCIO D'INIZIO CON FIFA 22. <br> DISPONIBILE PER PS5 - PS4 - XBOX SERIES - XBOX ONE");
                    break;
                case 1:
                    $("#ImageDiv").css("background-image", 'url("images/granTurismo7.jfif")');
                    $("#Description").html("SCOPRI IL MIGLIOR GIOCO AUTOMOBILISTICO PER PS4 - PS5! <br>GRAN TURISMO 7 DISPONIBILE ORA!");
                    break;
                case 2:
                    $("#ImageDiv").css({"background-image": 'url("images/horizon.jfif")'});
                    $("#Description").html("SCOPRI INSIEME AD ALOY L'OVEST PROIBITO. <br> HORIZON FORBIDDEN WEST DISPONIBILE ORA PER PS5 - PS4 - XBOX Series - XBOX One");
                    break;
            }
        }, 10000);
}

$(document).ready(activateChangeImg());


$(document).ready (function (){
    $("#logButton").mouseover(function() {
        $("#noImg").attr("src", "Logo_and_CSS/profile_icon.png");
    }
    )
});

$(document).ready (function (){
    $("#logButton").mouseleave(function() {
            $("#noImg").attr("src", "Logo_and_CSS/profile_icon2.png");
        }
    )
});

$(document).ready (function (){
    $("#prodotto").one('click', function() {
            $("#prodotto").attr("value", "");
        }
    )
});

$(document).ready(function(){
    $("#menu li").mouseover(function (){
        $(".submenu", this).slideDown(100);
    })
})

$(document).ready(function(){
    $("#menu li").mouseleave(function (){
        $(".submenu", this).slideUp(100);
    })
})

$(document).ready(function(){
    $("#imgBefore").click(function (){
        buttonPressed--;
        switch(buttonPressed % 3){
            case 0:
                $("#ImageDiv").css({"background-image": 'url("images/fifa22.jpeg")'});
                $("#Description").html("BATTI IL CALCIO D'INIZIO CON FIFA 22. <br> DISPONIBILE PER PS5 - PS4 - XBOX SERIES - XBOX ONE");
                break;
            case 1:
                $("#ImageDiv").css("background-image", 'url("images/granTurismo7.jfif")');
                $("#Description").html("SCOPRI IL MIGLIOR GIOCO AUTOMOBILISTICO PER PS4 - PS5! <br>GRAN TURISMO 7 DISPONIBILE ORA!");
                break;
            case 2:
                $("#ImageDiv").css({"background-image": 'url("images/horizon.jfif")', "color": "black"});
                $("#Description").html("SCOPRI INSIEME AD ALOY L'OVEST PROIBITO. <br> HORIZON FORBIDDEN WEST DISPONIBILE ORA PER PS5 - PS4 - XBOX Series - XBOX One");
                break;
        }
    })
})

$(document).ready(function(){
    $("#imgAfter").click(function (){
        buttonPressed++;
        switch(buttonPressed % 3){
            case 0:
                buttonPressed = 0;
                $("#ImageDiv").css({"background-image": 'url("images/fifa22.jpeg")'});
                $("#Description").html("BATTI IL CALCIO D'INIZIO CON FIFA 22. <br> DISPONIBILE PER PS5 - PS4 - XBOX SERIES - XBOX ONE");
                break;
            case 1:
                $("#ImageDiv").css("background-image", 'url("images/granTurismo7.jfif")');
                $("#Description").html("SCOPRI IL MIGLIOR GIOCO AUTOMOBILISTICO PER PS4 - PS5! <br>GRAN TURISMO 7 DISPONIBILE ORA!");
                break;
            case 2:
                $("#ImageDiv").css({"background-image": 'url("images/horizon.jfif")',  "color": "black"});
                $("#Description").html("SCOPRI INSIEME AD ALOY L'OVEST PROIBITO. <br> HORIZON FORBIDDEN WEST DISPONIBILE ORA PER PS5 - PS4 - XBOX Series - XBOX One");
                break;
        }
    })
})

$(document).ready(function () {
    $.get("RicercaCategoriaServlet", function (responseJson) {
        $.each(responseJson, function(index, cat) {
            $("<option value=''>").text(cat.nome).appendTo($("#Categoria")).attr("value", cat.nome);
        });
    })
})

$(document).ready(function () {
    $("#advancedSearchHeader").click(function () {
        $("#advancedSearchParameter").slideToggle(400);
        if(slidingAdvancedWindow == 1){
            slidingAdvancedWindow = 0;
            $("#UpDownArrowLeft").attr("src", "images/downArrow.png");
            $("#UpDownArrowRight").attr("src", "images/downArrow.png");
        }
        else {
            slidingAdvancedWindow = 1;
            $("#UpDownArrowLeft").attr("src", "images/upArrow.png");
            $("#UpDownArrowRight").attr("src", "images/upArrow.png");
        }
    })
})

$(document).ready(function () {
    $.get("FindPlatformsServlet", function (responseJson) {
        $.each(responseJson, function(index, platform) {
            $("<option value=''>").text(platform).appendTo($("#Piattaforma")).attr("value", platform);
        });
    })
})

$(document).ready(function () {
    let prezzoCatalogo = $("#PrezzoCatalogoProd").text();
    let prezzoAttuale = $("#PrezzoAttualeProd").text();
    if (prezzoAttuale < prezzoCatalogo){
        $("#PrezzoCatalogoAndSymbol").css("text-decoration", "line-through");
    }
    else
        $("#PrezzoCatalogoAndSymbol").css("display", "none");
})

$(document).ready(function() {
    $("#AggiungiAlCarrello").click(function () {
        let amount = $("#Quantita").val();
        let prodID = $("#idProdotto").val();
        $.ajax({
            url: "aggiungiAlCarrelloServlet", data: {quantita: amount, prodottoID: prodID}, success: function () {
                alert("Prodotto inserito nel carrello");
            }
        });
    });
});

$(document).ready(function() {
    $(".croceRimozioneProdotto").click(function () {
        let prodID = $(this).attr("id");
        $.ajax({
            url: "rimuoviDalCarrelloServlet", data: {prodID: prodID}, success: function (){
                let idDivDaCancellare = "#Prodotto" + prodID;
                let prezzoProdotto = $("#PrezzoColumn" + prodID).text();
                $(idDivDaCancellare).remove();
                let totale = $("#TotalPriceProd").text();
                totale = totale - prezzoProdotto;
                $("#TotalPriceProd").text(totale);
                alert("Prodotto eliminato dal carrello ");
                let numeroProdottiDifferenti = $("#DifferentProductsNumber").val();
                numeroProdottiDifferenti--;
                if (numeroProdottiDifferenti == 0) {
                    $("#Carrello").remove();
                    $("#CarrelloTitle").remove();
                    $("#TotalPrice").remove();
                    $("#SvuotaCarrelloAndEffettuaOrdine").remove();
                    $("body").append('<div id="CarrelloVuoto2">' +
                        '<h2>Il carrello è vuoto</h2>' +
                        '<p><a href="index.jsp" class="AnchorCart">Torna agli acquisti</a></p>' +
                        '</div>');
                }
                else
                    $("#DifferentProductsNumber").val(numeroProdottiDifferenti);
            }
        , error: function(){
                alert("E' stato rilevato un errore. Prodotto inesistente. Ricaricare la pagina per continuare, grazie");
            }})
    })
})

$(document).ready(function () {
    $("#SvuotaCarrello").click(function () {
        $.ajax({url: "SvuotaCarrelloServlet", success: function (){
            alert("Carrello Svuotato");
            $("#Carrello").remove();
            $("#CarrelloTitle").remove();
            $("#TotalPrice").remove();
            $("#SvuotaCarrelloAndEffettuaOrdine").remove();
            $("body").append('<div id="CarrelloVuoto2">' +
                '<h2>Il carrello è vuoto</h2>' +
                '<p><a href="index.jsp" class="AnchorCart">Torna agli acquisti</a></p>' +
                '</div>');
            }
            , error: function(){
                alert("E' stato rilevato un errore. Prodotto inesistente. Ricaricare la pagina per continuare, grazie");
            }})
    })
})

$(document).ready (function () {
    $("#salvaDatiAnagraficiButton").click(function (){
        let nome = $("#NomeUserInput").val();
        let cognome = $("#CognomeUserInput").val();
        let username = $("#UsernameInput").val();
        let indirizzo = $("#IndirizzoUserInput").val();
        let citta = $("#CittaUserInput").val();
        let nazione = $("#NazioneUserInput").val();
        let vecchioUsername = $("#oldUsername").val();
        $.ajax({url: "ChangeUserDataServlet", data: {nome: nome, cognome: cognome, username: username, indirizzo: indirizzo,
            vecchioUsername: vecchioUsername, citta: citta, nazione: nazione},
        success: function () {
            $("#CognomeUser").text(cognome);
            $("#NomeUser").text(nome);
            $("#Username").text(username);
            $("#IndirizzoUser").text(indirizzo);
            $("#oldUsername").val(username);
            $("#CittaUser").text(citta);
            $("#NazioneUser").text(nazione);
            $("#profileLabel").text("Ciao " + username);
            $(".UserData").show();
            $(".UserCredit").show();
            $(".UserInput").hide();
            $("#ControlloUser").css("display", "none");
            $("#salvaDatiAnagraficiButton").hide();
            $(".ActiveSelector").removeClass("ActiveSelector");
            $("#DatiUtenteSelector").toggleClass("ActiveSelector");
            alert("Dati utente cambiati");
        }, error: function () {
                alert ("Errore nel cambiamento dei dati. Potrebbe essere stato inserito uno username già presente.");
            }})
    })
})

$(document).ready(function () {
    $("#UsernameInput").change(function () {
        let usernameLowerCase = $("#UsernameInput").val().toLowerCase();
        let vecchioUsername = $("#oldUsername").val().toLowerCase();
        $("#ControlloUser").text("Username disponibile o uguale al precedente");
        $("#ControlloUser").css({"color": "green"});
        $.get("UsernameServlet", function (responseJson) {
            $.each(responseJson, function (index, user) {
                let userLowerCase = user.toLowerCase();
                if ((userLowerCase == usernameLowerCase) && (userLowerCase != vecchioUsername)) {
                    $("#ControlloUser").text("Username già presente");
                    $("#ControlloUser").css({"color": "red"});
                    return false;
                }
            })
        })
    })
})

$(document).ready(function(){
    $(".ProfileSelector").click(function () {
        $("#OrdineDiv").hide();
        $(".Ordine").hide();
        $("#salvaDatiAnagraficiButton").hide();
        $("#RicaricaDiv").hide();
        $("#PrelevaDiv").hide();
        $(".UserCredit").show();
        let selectorID = $(this).attr("id");
        switch(selectorID){
            case "DatiUtenteSelector":
                $("#DatiUtente").show();
                $(".DatoUtente").show();
                $(".UserInput").hide();
                $(".UserData").show();
                break;
            case "CambiaDatiSelector":
                $("#DatiUtente").show();
                $(".DatoUtente").show();
                $(".UserData").hide();
                $(".UserInput").show();
                $(".UserCredit").hide();
                $("#salvaDatiAnagraficiButton").show();
                break;
            case "DepositSelector":
                $("#DatiUtente").hide();
                $("#RicaricaDiv").show();
                break;
            case "WithdrawSelector":
                $("#DatiUtente").hide();
                $("#PrelevaDiv").show();
                break;
            case "OrderSelector":
                $("#DatiUtente").hide();
                $(".UserCredit").hide();
                $(".Ordine").show();
                $("#OrdineDiv").show();
                break;
        }
        $(".ActiveSelector").removeClass("ActiveSelector");
        $("#" + selectorID).addClass("ActiveSelector");
    })
})

function successDepositWithdraw(n) {
    alert("Operazione effettuata con successo");
    $("#RicaricaDiv").hide();
    $("#PrelevaDiv").hide();
    let oldCredit = Number($(".CreditoUser:first").text());
    let n2 = Number(n);
    let newCredit =  oldCredit + n2;
    $(".CreditoUser").text(newCredit);
    $("#DatiUtente").show();
    $(".DatoUtente").show();
    $(".UserInput").hide();
    $(".UserData").show();
    $(".UserData2").hide();
    $(".ActiveSelector").removeClass("ActiveSelector");
    $("#DatiUtenteSelector").toggleClass("ActiveSelector");
}

$(document).ready(function () {
    $("#RicaricaButton2").click(function () {
        let n = $("#RicaricaInput").val();
        if (n < 0) {
            alert ("Non è possibile effettuare una ricarica negativa. Riprovare");
            return false;
        }
        $.ajax({url: "RicaricaAccountServlet", data: {ricarica: n}, success: successDepositWithdraw(n), error: function (xhr) {
                alert ("Errore " + xhr.status + ": " + xhr.statusText + ". Si prega di riprovare.");
            }})
    })
})

$(document).ready(function () {
    $("#PrelevaButton2").click(function () {
        let n = $("#PrelevaInput").val();
        if (n < 0) {
            alert ("Non è possibile effettuare un prelievo negativo. Riprovare");
            return false;
        }
        let oldCredit = $(".CreditoUser").text();
        if (n > oldCredit) {
            alert ("Non è possibile effettuare un prelievo maggiore del saldo attuale. Riprovare");
            return false;
        }
        $.ajax({url: "PrelievoAccountServlet", data: {prelievo: n}, success: successDepositWithdraw(-n), error: function (xhr) {
                alert ("Errore " + xhr.status + ": " + xhr.statusText + ". Si prega di riprovare.");
            }})
    })
})

$(document).ready(function () {
    $("#Numero1").click(function (){
      $("#DettagliSpedizione").hide(1000, function () {
          $("#Carrello").show();
          $("#TotalPrice").show();
          $("#Numero1").css({"background-color": "white", "color": "red", "border-style": "outset"});
          $("#Numero2").css({"border": "1px solid black", "color": "white", "background-color": "black"});
      });
    })
})

$(document).ready(function () {
    $("#Numero2").click(function (){
        $("#TotalPrice").hide(1000);
        $("#Carrello").hide(1000, function () {
                $("#DettagliSpedizione").show(500);
        });
        $("#Numero2").css({"background-color": "white", "color": "red", "border-style": "outset"});
        $("#Numero1").css({"border": "1px solid black", "color": "white", "background-color": "black"});
    })
})