package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EffettuaOrdineServlet", value = "/EffettuaOrdine")
public class EffettuaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente userSession = (Utente) session.getAttribute("user");
        if (userSession == null){
            response.sendError(400, "Sessione scaduta. Rieffettuare l'accesso");
        }
        else {
            Utente u = UtenteDAO.doRetrieveByUsername(userSession.getUsername());
            Carrello c = CarrelloDAO.doRetrieveByUsername(u.getUsername());
            List<Prodotto> prodotti = new ArrayList<>();
            for (ProdottoCarrello pc : c.getProdottiCarrello()) {
                Prodotto p = ProdottoDAO.doMergeProdottoCarrelloandProdotto(pc);
                prodotti.add(p);
            }
            String prodottiNonDisponibili = "";
            String address;
            if (prodotti.isEmpty()) {
                request.setAttribute("ProdError", 1);
                address = "WEB-INF/results/noProdOrder.jsp";
            } else {
                for (Prodotto p : prodotti) {
                    Prodotto pCatalogo = ProdottoDAO.doRetrieveByID(p.getId());
                    if (p.getQuantita() > pCatalogo.getQuantita())
                        prodottiNonDisponibili = prodottiNonDisponibili.concat(p.getNome()).concat(";");
                }
                if (prodottiNonDisponibili != "") {
                    request.setAttribute("prodottiNonDisponibili", prodottiNonDisponibili);
                    address = "WEB-INF/results/noProdOrder.jsp";
                } else {
                    if (c.getSpesaTot() > u.getCreditoUtente()) {
                        request.setAttribute("noCredit", 1);
                        address = "WEB-INF/results/noProdOrder.jsp";
                    } else {
                        String nomeDestinatario = request.getParameter("NomeDestinatario");
                        String cognomeDestinatario = request.getParameter("CognomeDestinatario");
                        String indirizzo = request.getParameter("IndirizzoConsegna");
                        String citta = request.getParameter("Citta");
                        String nazione = request.getParameter("Nazione");
                        String username = u.getUsername();
                        float spesaTotale = c.getSpesaTot();
                        int numeroArticoli = c.getNumeroArticoli();
                        Ordine o = new Ordine();
                        o.setNomeDestinatario(nomeDestinatario);
                        o.setCognomeDestinatario(cognomeDestinatario);
                        o.setIndirizzoSpedizione(indirizzo);
                        o.setCittaSpedizione(citta);
                        o.setNazioneSpedizione(nazione);
                        o.setUtente(username);
                        o.setSpesaTotale(spesaTotale);
                        o.setNumeroArticoli(numeroArticoli);
                        o.setStato("In lavorazione");
                        o.setMetodoPagamento("Credito Utente");
                        OrdineDAO.doInsertOrdine(o);
                        for (Prodotto p : prodotti) {
                            ProdottoOrdine p2 = new ProdottoOrdine();
                            p2.setCodiceProdotto(p.getId());
                            p2.setCodiceOrdine(o.getId());
                            p2.setQuantita(p.getQuantita());
                            p2.setCostoAcquisto(p.getPrezzoAttuale());
                            ProdottoOrdineDAO.doInsertProdottoOrdine(p2);
                            ProdottoDAO.doUpdateQuantita(ProdottoDAO.doRetrieveByID(p.getId()).getQuantita() - p2.getQuantita(), p.getId());
                        }
                        c.setSpesaTot(0);
                        c.setNumeroArticoli(0);
                        c.setProdottiCarrello(new ArrayList<>());
                        ProdottoCarrelloDAO.doDeleteByCartCode(c.getCodice());
                        CarrelloDAO.doUpdateCart(c);
                        request.setAttribute("contenutoOrdine", prodotti);
                        request.setAttribute("ordine", o);
                        session.removeAttribute("contenutoCart");
                        session.setAttribute("carrello", c);
                        u.setCreditoUtente(u.getCreditoUtente() - o.getSpesaTotale());
                        UtenteDAO.doUpdateUserCredit(-o.getSpesaTotale(), u);
                        session.setAttribute("user", u);
                        address = "WEB-INF/results/ordineEffettuato.jsp";
                    }
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
