$(document).ready (function () {
    let idActiveSection = $("#ProfileMenu").find(".ActiveSelector").attr("id");
    $("#ConfirmButtons1").hide();
    $("#ConfirmButtons2").hide();
    $("#ConfirmButtons3").hide();
    if (idActiveSection == "GestisciUtenti"){
        $("#UsersDiv").show();
    }
    else if (idActiveSection == "GestisciOrdini"){
        $("#OrderDiv").show();
    }
    else if (idActiveSection == "GestisciProdotti"){
        $("#ProductsDiv").show();
    }
})

$(document).ready(function () {
    $.get("UsernameServlet", function (responseJson) {
        $.each(responseJson, function(index, username) {
            $("<option value=''>").text(username).appendTo($("#SelezionaUtente")).attr("value", username);
        });
    });
    $.get("FindAllOrdersServlet", function(responseJson2) {
        $.each(responseJson2, function (index, order) {
            $("<option value=''>").text(order.id + " - Effettuato da: " + order.utente).appendTo($("#SelezionaOrdine")).attr("value", order.id);
        });
    });
    $.get("FindAllProductsServlet", function(responseJson3) {
        $.each(responseJson3, function (index, product) {
            $("<option value=''>").text(product.id + "- " + product.nome).appendTo($("#SelezionaProdotto")).attr("value", product.id);
        });
    });
})

$(document).ready (function () {
    $(".ProfileSelector").click(function () {
        let id = $(this).attr("id");
        if (id == "GestisciUtenti"){
            $("#OrderDiv").hide();
            $("#ProductsDiv").hide();
            $("#UsersDiv").show();
        }
        else if (id == "GestisciOrdini"){
            $("#UsersDiv").hide();
            $("#ProductsDiv").hide();
            $("#OrderDiv").show();
        }
        else if (id == "GestisciProdotti"){
            $("#OrderDiv").hide();
            $("#UsersDiv").hide();
            $("#ProductsDiv").show();
        }
        $(".ActiveSelector").removeClass("ActiveSelector");
        $("#" + id).addClass("ActiveSelector");
    })
})

$(document).ready(function(){
    $("#MostraInfoUser").click(function () {
        let username = $("#SelezionaUtente").val();
        if (username == "0"){
            alert ("Selezionare uno username dalla lista");
        }
        else {
            $("#SelezionaUtente").hide();
            $("#MostraInfoUser").hide();
            $("#UserInfo").show();
            $("#ConfirmButtons1").show();
            $.get("FindUserServlet?username=" + username, function (user) {
                $("#UserInfo").html("<h3 class='InputLabel'>Nome: </h3><input type='text' id='Nome' name='Nome' value='" + user.nome + "'><br>" +
                    "<h3 class='InputLabel'>Cognome: </h3><input type='text' id='Cognome' name='Cognome' value='" + user.cognome + "'><br>" +
                    "<h3 class='InputLabel'>Username: </h3><input type='text' id='Username' name='Username' value='" + user.username + "'><br>" +
                    "<h3 class='InputLabel'>Indirizzo: </h3><input type='text' id='Indirizzo' name='Indirizzo' value='" + user.indirizzo + "'><br>" +
                    "<h3 class='InputLabel'>Città: </h3><input type='text' id='Citta' name='Citta' value='" + user.citta + "'><br>" +
                    "<h3 class='InputLabel'>Nazione: </h3><input type='text' id='Nazione' name='Nazione' value='" + user.nazione + "'><br>" +
                    "<h3 class='InputLabel'>Credito: </h3><input type='text' id='Credito' name='Credito' value='" + user.creditoUtente + "'><br>" +
                    "<h3 class='InputLabel'>Admin: </h3><input type='checkbox' id='Admin' name='Admin'><br>" +
                    "<input type='hidden' id='OldUsername' name='OldUsername' value='" + user.username + "'><br>");
                if (user.admin){
                    $("#Admin").prop("checked", true);
                }
                else {
                    $("#Admin").prop("checked", false);
                }
            })
        }
    })
})

$(document).ready(function () {
    $("#CancelModify1").click(function () {
        $("#UserInfo").hide();
        $("#SelezionaUtente").show();
        $("#MostraInfoUser").show();
        $("#ConfirmButtons1").hide();
    })
})

$(document).ready(function (){
    $("#SaveModify1").click(function () {
        let name = $("#Nome").val();
        let surname = $("#Cognome").val();
        let username = $("#Username").val();
        let indirizzo = $("#Indirizzo").val();
        let citta = $("#Citta").val();
        let nazione = $("#Nazione").val();
        let credito = $("#Credito").val();
        let admin = $("#Admin").prop("checked");
        let oldUsername = $("#OldUsername").val();
        $.ajax({url: "UpdateUserServlet", data: {name: name, surname: surname, username: username,
            indirizzo: indirizzo, citta: citta, nazione: nazione, credito: credito, admin: admin, oldUsername: oldUsername},
            success: function(){
                alert ("Utente aggiornato con successo");
                $("#UserInfo").hide();
                $("#SelezionaUtente").show();
                $("#MostraInfoUser").show();
                $("#ConfirmButtons1").hide();
            }, error: function (xhr) {
                alert ("Errore nell'aggiornamento. Codice errore: " + xhr.status);
                $("#UserInfo").hide();
                $("#SelezionaUtente").show();
                $("#MostraInfoUser").show();
                $("#ConfirmButtons1").hide();
            }})
    })
})


$(document).ready(function(){
    $("#EliminaUtente").click(function () {
        let usernameDaEliminare = $("#SelezionaUtente").val();
        if (confirm("Sei sicuro di voler eliminare l'utente " + usernameDaEliminare + "?")) {
            $.ajax({
                url: "EliminaUtenteServlet", data: {username: usernameDaEliminare}, success: function () {
                    alert("Utente " + usernameDaEliminare + " eliminato");
                    $("#UserInfo").hide();
                    $("#SelezionaUtente").show();
                    $("#MostraInfoUser").show();
                    $("#ConfirmButtons1").hide();
                }, error: function () {
                    alert("Impossibile eliminare l'utente");
                    $("#UserInfo").hide();
                    $("#SelezionaUtente").show();
                    $("#MostraInfoUser").show();
                    $("#ConfirmButtons1").hide();
                }
            })
        }
    })
})

$(document).ready(function(){
    $("#MostraInfoOrdine").click(function () {
        let ordine = $("#SelezionaOrdine").val();
        if (ordine == "0"){
            alert ("Selezionare un ordine dalla lista");
        }
        else {
            $("#SelezionaOrdine").hide();
            $("#MostraInfoOrdine").hide();
            $("#OrderSelectedInfo").show();
            $("#ConfirmButtons2").show();
            $.get("FindOrderByIDServlet?order=" + ordine, function (order) {
                $("#OrderSelectedInfo").html("<h3 class='InputLabel'>ID: </h3><span id='ID'>" + order.id + "</span><br>" +
                    "<h3 class='InputLabel'>Nome Destinatario: </h3><input type='text' id='Nome2' name='Nome2' value='" + order.nomeDestinatario + "'><br>" +
                    "<h3 class='InputLabel'>Cognome Destinatario: </h3><input type='text' id='Cognome2' name='Cognome2' value='" + order.cognomeDestinatario + "'><br>" +
                    "<h3 class='InputLabel'>Stato: </h3><input type='text' id='Stato' name='Stato' value='" + order.stato + "'><br>" +
                    "<h3 class='InputLabel'>Indirizzo: </h3><input type='text' id='Indirizzo2' name='Indirizzo2' value='" + order.indirizzoSpedizione + "'><br>" +
                    "<h3 class='InputLabel'>Città: </h3><input type='text' id='Citta2' name='Citta2' value='" + order.cittaSpedizione + "'><br>" +
                    "<h3 class='InputLabel'>Nazione: </h3><input type='text' id='Nazione2' name='Nazione2' value='" + order.nazioneSpedizione + "'><br>" +
                    "<h3 class='InputLabel'>Spesa Totale: </h3>" + order.spesaTotale + "<br>" +
                    "<h3 class='InputLabel'>Numero Articoli: </h3>" + order.numeroArticoli + "<br>" +
                    "<h3 class='InputLabel'>Data Ordine: </h3>" + order.data + "<br>" +
                    "<h3 class='InputLabel'>Utente: </h3>" + order.utente + "<br>");
            })
        }
    })
})

$(document).ready(function () {
    $("#CancelModify2").click(function () {
        $("#OrderSelectedInfo").hide();
        $("#SelezionaOrdine").show();
        $("#MostraInfoOrdine").show();
        $("#ConfirmButtons2").hide();
    })
})

$(document).ready(function (){
    $("#SaveModify2").click(function () {
        let name = $("#Nome2").val();
        let surname = $("#Cognome2").val();
        let username = $("#Utente").val();
        let indirizzo = $("#Indirizzo2").val();
        let citta = $("#Citta2").val();
        let nazione = $("#Nazione2").val();
        let stato = $("#Stato").val();
        let idOrdine = $("#ID").text();
        let dataOrdine = $("#Data").val();
        $.ajax({url: "UpdateOrderServlet", data: {name: name, surname: surname, username: username,
                indirizzo: indirizzo, citta: citta, nazione: nazione, idOrdine: idOrdine, dataOrdine: dataOrdine, stato: stato},
            success: function(){
                alert ("Ordine aggiornato con successo");
                $("#OrderSelectedInfo").hide();
                $("#SelezionaOrdine").show();
                $("#MostraInfoOrdine").show();
                $("#ConfirmButtons2").hide();
            }, error: function (xhr) {
                alert ("Errore nell'aggiornamento. Codice errore: " + xhr.status);
                $("#OrderSelectedInfo").hide();
                $("#SelezionaOrdine").show();
                $("#MostraInfoOrdine").show();
                $("#ConfirmButtons2").hide();
            }})
    })
})

$(document).ready(function(){
    $("#EliminaOrdine").click(function () {
        let idOrdineDaEliminare = $("#SelezionaOrdine").val();
        if (confirm("Sei sicuro di voler eliminare l'ordine #" + idOrdineDaEliminare + "?")) {
            $.ajax({
                url: "EliminaOrdineServlet", data: {id: idOrdineDaEliminare}, success: function () {
                    alert("Ordine #" + idOrdineDaEliminare + " eliminato");
                    $("#OrderSelectedInfo").hide();
                    $("#SelezionaOrdine").show();
                    $("#MostraInfoOrdine").show();
                    $("#ConfirmButtons2").hide();
                }, error: function () {
                    alert("Impossibile eliminare l'ordine");
                    $("#OrderSelectedInfo").hide();
                    $("#SelezionaOrdine").show();
                    $("#MostraInfoOrdine").show();
                    $("#ConfirmButtons2").hide();
                }
            })
        }
    })
})

$(document).ready(function(){
    $("#MostraInfoProdotto").click(function () {
        let prodotto = $("#SelezionaProdotto").val();
        if (prodotto == "0"){
            alert ("Selezionare un prodotto dalla lista");
        }
        else {
            $("#SelezionaProdotto").hide();
            $("#MostraInfoProdotto").hide();
            $("#GestioneProd").hide();
            $("#ProductSelectedInfo").show();
            $("#ConfirmButtons3").show();
            $.get("FindProductByIDServlet?product=" + prodotto, function (product) {
                $("#ProductSelectedInfo").html("<h3 class='InputLabel'>ID: </h3><span id='IDProd'>" + product.id + "</span><br>" +
                    "<h3 class='InputLabel'>Nome: </h3><input type='text' id='Nome3' name='Nome3' value='" + product.nome + "'><br>" +
                    "<h3 class='InputLabel'>Descrizione: </h3><input type='text' size='120' id='Descrizione' name='Descrizione' value='" + product.descrizione + "'><br>" +
                    "<h3 class='InputLabel'>Prezzo Catalogo: </h3><input type='number' id='PrezzoCatalogo' name='PrezzoCatalogo' value='" + product.prezzoCatalogo + "'>€<br>" +
                    "<h3 class='InputLabel'>Prezzo Attuale: </h3><input type='number' id='PrezzoAttuale' name='PrezzoAttuale' value='" + product.prezzoAttuale + "'>€<br>" +
                    "<h3 class='InputLabel'>Quantità: </h3><input type='number' id='Quantita' name='Quantita' value='" + product.quantita + "'><br>" +
                    "<h3 class='InputLabel'>Categoria: </h3><input type='text' id='Categoria' name='Categoria' value='" + product.categoria + "'><br>" +
                    "<h3 class='InputLabel'>Piattaforma: </h3><input type='text' id='Piattaforma' name='Piattaforma' value='" + product.piattaforma + "'><br>");
            })
        }
    })
})

$(document).ready(function (){
    $("#SaveModify3").click(function () {
        let name = $("#Nome3").val();
        let description = $("#Descrizione").val();
        let id = $("#IDProd").text();
        let PrezzoCatalogo = $("#PrezzoCatalogo").val();
        let PrezzoAttuale = $("#PrezzoAttuale").val();
        let quantita = $("#Quantita").val();
        let categoria = $("#Categoria").val();
        let piattaforma = $("#Piattaforma").val();
        if (PrezzoCatalogo<0 || PrezzoAttuale < 0 || quantita < 0){
            alert("Prezzi e quantità devono essere maggiori di 0");
        }
        else {
            $.ajax({
                url: "UpdateProductServlet", data: {
                    name: name,
                    description: description,
                    id: id,
                    quantita: quantita,
                    PrezzoCatalogo: PrezzoCatalogo,
                    PrezzoAttuale: PrezzoAttuale,
                    categoria: categoria,
                    piattaforma: piattaforma
                },
                success: function () {
                    alert("Prodotto aggiornato con successo");
                    $("#ProductSelectedInfo").hide();
                    $("#SelezionaProdotto").show();
                    $("#MostraInfoProdotto").show();
                    $("#ConfirmButtons3").hide();
                    $("#GestioneProd").show();
                }, error: function (xhr) {
                    alert("Errore nell'aggiornamento. Codice errore: " + xhr.status);
                    $("#ProductSelectedInfo").hide();
                    $("#SelezionaProdotto").show();
                    $("#MostraInfoProdotto").show();
                    $("#ConfirmButtons3").hide();
                    $("#GestioneProd").show();
                }
            })
        }
    })
})

function annullaRichiesta3() {
    $("#ProductSelectedInfo").hide();
    $("#SelezionaProdotto").show();
    $("#MostraInfoProdotto").show();
    $("#ConfirmButtons3").hide();
    $("#GestioneProd").show();
}

$(document).ready(function(){
    $("#EliminaProdotto").click(function () {
        let idProdottoDaEliminare = $("#SelezionaProdotto").val();
        if (confirm("Sei sicuro di voler eliminare il prodotto #" + idProdottoDaEliminare + "?")) {
            $.ajax({
                url: "EliminaProdottoServlet", data: {id: idProdottoDaEliminare}, success: function () {
                    alert("Prodotto #" + idProdottoDaEliminare + " eliminato");
                    $("#ProductSelectedInfo").hide();
                    $("#SelezionaProdotto").show();
                    $("#MostraInfoProdotto").show();
                    $("#ConfirmButtons3").hide();
                    $("#GestioneProd").show();
                }, error: function (xhr) {
                    alert("Impossibile eliminare il prodotto: " + xhr.statusText);
                    $("#ProductSelectedInfo").hide();
                    $("#SelezionaProdotto").show();
                    $("#MostraInfoProdotto").show();
                    $("#ConfirmButtons3").hide();
                    $("#GestioneProd").show();

                }
            })
        }
    })
})

$(document).ready(function(){
    $("#InsertNewProdotto").click(function () {
        $("#SelezionaProdotto").hide();
        $("#MostraInfoProdotto").hide();
        $("#GestioneProd").hide();
        $("#ProductSelectedInfo").show();
        $("#ConfirmButtons3").hide();
        $("#ProductSelectedInfo").html(
            "<h3 class='InputLabel'>Nome: </h3><input type='text' id='NomeInsert' name='NomeInsert'><br>" +
            "<h3 class='InputLabel'>Descrizione: </h3><input type='text' size='60' id='DescrizioneInsert' name='DescrizioneInsert'><br>" +
            "<h3 class='InputLabel'>Prezzo Catalogo: </h3><input type='number' id='PrezzoCatalogoInsert' name='PrezzoCatalogoInsert'>€<br>" +
            "<h3 class='InputLabel'>Prezzo Attuale: </h3><input type='number' id='PrezzoAttualeInsert' name='PrezzoAttualeInsert'>€<br>" +
            "<h3 class='InputLabel'>Quantità: </h3><input type='number' id='QuantitaInsert' name='QuantitaInsert'><br>" +
            "<h3 class='InputLabel'>Categoria: </h3><input type='text' id='CategoriaInsert' name='CategoriaInsert'><br>" +
            "<h3 class='InputLabel'>Piattaforma: </h3><input type='text' id='PiattaformaInsert' name='PiattaformaInsert'><br>" +
            "<button class='ButtonCart ButtonAdmin' id='SaveModify4' onclick='insertProduct()'>Salva</button>" +
            "<button class='ButtonCart ButtonAdmin' id='CancelModify4' onclick='annullaRichiesta3()'>Annulla</button>"
        );
    })
})

function insertProduct() {
    let nome = $("#NomeInsert").val();
    let descrizione = $("#DescrizioneInsert").val();
    let prezzoCatalogo = $("#PrezzoCatalogoInsert").val();
    let prezzoAttuale = $("#PrezzoAttualeInsert").val();
    let quantita = $("#QuantitaInsert").val();
    let categoria = $("#CategoriaInsert").val();
    let piattaforma = $("#PiattaformaInsert").val();
    if (prezzoCatalogo<0 || prezzoAttuale < 0 || quantita < 0){
        alert("Prezzi e quantità devono essere maggiori di 0");
    }
    else {
        $.ajax({
            url: "InsertProductServlet", data: {
                name: nome,
                description: descrizione,
                quantita: quantita,
                PrezzoCatalogo: prezzoCatalogo,
                PrezzoAttuale: prezzoAttuale,
                categoria: categoria,
                piattaforma: piattaforma
            },
            success: function () {
                alert("Prodotto inserito con successo");
                $("#ProductSelectedInfo").hide();
                $("#SelezionaProdotto").show();
                $("#MostraInfoProdotto").show();
                $("#ConfirmButtons3").hide();
                $("#GestioneProd").show();
            }, error: function (xhr) {
                alert("Errore nell'inserimento. Codice errore: " + xhr.status);
                $("#ProductSelectedInfo").hide();
                $("#SelezionaProdotto").show();
                $("#MostraInfoProdotto").show();
                $("#ConfirmButtons3").hide();
                $("#GestioneProd").show();
            }
        })
    }
}

$(document).ready(function () {
    $("#InsertNewImage").click(function(){
        if ($("#SelezionaProdotto").val() == "0")
            alert("Seleziona un prodotto a cui associare un'immagine");
        else {
            $("#SelezionaProdotto").hide();
            $("#MostraInfoProdotto").hide();
            $("#GestioneProd").hide();
            $("#ProductSelectedInfo").show();
            $("#ConfirmButtons3").hide();
            $("#ProductSelectedInfo").html("<form action='FileUploadServlet' method='post' enctype='multipart/form-data'>" +
                "<h3 class='InputLabel'>Scegli l'immagine da caricare: </h3>" +
                "<input type='file' class='ButtonCart ButtonAdmin' id='FileSelector' name='FileSelector'><br>" +
                "<input type='hidden' id='idProdotto' name='idProdotto' value='" + $('#SelezionaProdotto').val() + "'>" +
                "<input type='submit' class='ButtonCart ButtonAdmin' value='Inserisci immagine'></form>" +
                "<button class='ButtonCart ButtonAdmin' onclick='annullaRichiesta3()'>Annulla</button></form>");
        }
    })
})