$(document).ready(function(){
    $.get("FindOrdersServlet", function (responseJson) {
        $.each(responseJson, function(index, ord) {
                $("<div class='Ordine'>").
                html("<div class='OrdineHeader' " +
                    "onclick='let idOrd = $(this).attr(\"id\"); visualizzaDettagli(idOrd)' id='Header" + ord.id + "'>" +
                    "Ordine #" + ord.id + "</div>").appendTo($("#OrdineDiv")).attr("id", ord.id);
                $("<div class='DettagliOrdine2'>").html("<span class='DettagliModificabili'>" +
                    "Nome Destinatario: <span class='NomeDestinatarioInfo'>" + ord.nomeDestinatario + "</span><br>" +
                "Cognome Destinatario: <span class='CognomeDestinatarioInfo'>" + ord.cognomeDestinatario + "</span><br>" +
                "Indirizzo di spedizione: <span class='IndirizzoInfo'>" + ord.indirizzoSpedizione + "</span>, <span class='CittaInfo'>" +
                ord.cittaSpedizione + "</span>, <span class='NazioneInfo'>" + ord.nazioneSpedizione + "</span><br>" +
                "Stato spedizione: <span class='StatoOrdineInfo'>" + ord.stato + "</span><br>" +
                "Metodo di pagamento: <span class='MetodoPagamentoInfo'>" + ord.metodoPagamento + "</span><br></span>" +
                "Data ordine: " + ord.data + "<br>" +
                "Spesa Totale: " + ord.spesaTotale + " €<br>" +
                "Numero articoli: " + ord.numeroArticoli + "<br>").appendTo($("#" + ord.id)).append(
                    "<table id=\"Carrello\" class='ProdottiOrdinati'>" +
                    "    <tr id=\"CarrelloHeader\">" +
                        "    <th class=\"ImgColumn\"></th>" +
                        "    <th class=\"DescriptionColumn\"></th>" +
                        "    <th class=\"InformationColumn SinglePriceColumn\">Prezzo Singolo</th>" +
                        "    <th class=\"InformationColumn ArticlesColumn\"> Numero Articoli</th>" +
                        "    <th class=\"InformationColumn\">PREZZO TOTALE</th>" +
                    "    </tr>");
                if (ord.stato == "In lavorazione") {
                    $("#" + ord.id).find(".DettagliOrdine2").prepend("<img src='images/rimuoviProdottoX.png' class='cancellaOrdine'" +
                        "title='Cancella ordine' id='Cancella" + ord.id + "' onclick='cancellaOrdine($(this).attr(\"id\"))'>" +
                        "<img src='images/matitaIcon.png' class='modificaOrdine' title='Modifica Ordine' id='Modifica" + ord.id + "'" +
                        "onclick='modificaOrdine($(this).attr(\"id\"))'>");
                }
                $.get("FindOrderProductsServlet?orderID=" + ord.id, function(responseJson2){
                    $.each(responseJson2, function(index, prodOrder){
                        $("#" + ord.id).find(".DettagliOrdine2").find('.ProdottiOrdinati').append(
                                "<tr class='ProdottoOrdine' id='Prodotto " + prodOrder.id + "'>" +
                                "<td class='ImgColumn'>" +
                                "<img src='./ImgServlet?id=" + prodOrder.id + "&imgNumber=0' class='ImmagineProdotto'>" +
                                "</td>" +
                                "<td class='DescriptionColumn'>" +
                                "<a href='visualizzaProdotto?id=" + prodOrder.id + "' class='AnchorCart'>" + prodOrder.nome  + "</a></td>" +
                                "<td class='InformationColumn SinglePriceColumn'>" + prodOrder.prezzoAttuale + " €</td>" +
                                "<td class='InformationColumn ArticlesColumn'>" + prodOrder.quantita + "</td>" +
                                "<td class='InformationColumn'><span id ='PrezzoColumn" + prodOrder.id + "'>"
                                    + (prodOrder.prezzoAttuale * 100.0)*prodOrder.quantita/100.0 + "</span> €</td> </tr>"
                        )
                        let heightDiv = Number($("#" + ord.id).find(".DettagliOrdine2").height());
                        let imgHeight = Number($("#" + ord.id).find(".DettagliOrdine2").find(".ProdottoOrdine").
                        find(".ImgColumn").find(".ImmagineProdotto").height());
                        $("#" + ord.id).find(".DettagliOrdine2").css("height", heightDiv + imgHeight);
                    })
                    $("#" + ord.id).find(".DettagliOrdine2").find(".ProdottiOrdinati").append("</table>");
                })
        })
    })
})

function visualizzaDettagli (idOrdine){
    let id2 = idOrdine.slice(6);
     $("#" + id2).find(".DettagliOrdine2").slideToggle(300);
}

function cancellaOrdine(id) {
    let ordineID = id.slice(8);
    if(confirm("Sei sicuro di voler cancellare l'ordine #" + ordineID + "?"))
        $.ajax({url: "CancellaOrdineServlet", data: {idOrdine: ordineID}, success: function () {
            alert("Ordine " + ordineID + "cancellato con successo");
            }, error: function(xhr){
            alert("Impossibile cancellare l'ordine. Errore " + xhr.status + ": " + xhr.statusText);
        }});
}

function modificaOrdine(id) {
    let ordineID = id.slice(8);
    $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").html(
        "<form action='ModificaOrdineServlet' method='post'>" +
        "<input type='hidden' name='idOrder' value='"+ ordineID +"'>" +
        "<input type='hidden' name='statoOrder' value='"+ $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").find(".StatoOrdineInfo").text() +"'>" +
        "Nome Destinatario: <input type='text' name='NomeDestinatarioMod' value='" + $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").find(".NomeDestinatarioInfo").text() + "'><br>" +
        "Cognome Destinatario: <input type='text' name='CognomeDestinatarioMod' value='" + $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").find(".CognomeDestinatarioInfo").text() + "'><br>" +
        "Indirizzo di spedizione: <input type='text' name='IndirizzoMod' value='" + $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").find(".IndirizzoInfo").text() + "'><br>" +
        "Città destinazione: <input type='text' name='CittaMod' value='" + $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").find(".CittaInfo").text() +
        "'><br> " + "Nazione: <input type='text' name='NazioneMod' value='" + $("#" + ordineID).find(".DettagliOrdine2").find(".DettagliModificabili").find(".NazioneInfo").text() + "'><br>" +
        "<br><input type='submit' value='Modifica Ordine'>"
    );
    $("#" + ordineID).find(".DettagliOrdine2").css("height", $("#" + ordineID).find(".DettagliOrdine2").height() + 65);
}