package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "aggiungiAlCarrelloServlet", value = "/aggiungiAlCarrelloServlet")
public class aggiungiAlCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        int prodID = Integer.parseInt(request.getParameter("prodottoID"));
        HttpSession session = request.getSession();
        Carrello c = (Carrello) session.getAttribute("carrello");
        Prodotto p = ProdottoDAO.doRetrieveByID(prodID);
        ProdottoCarrello pc = new ProdottoCarrello();
        pc.setIdProdotto(p.getId());
        pc.setCodiceCarrello(c.getCodice());
        pc.setPrezzoCad(p.getPrezzoAttuale());
        pc.setQuantita(quantita);
        pc.setPrezzoTotale();
        Utente u = (Utente)session.getAttribute("user");
        c.setNumeroArticoli(c.getNumeroArticoli() + quantita);
        c.setSpesaTot(c.getSpesaTot() + (p.getPrezzoAttuale() * quantita));
        if (u != null) {
            if (ProdottoCarrelloDAO.doRetrieveProdottoCarrello(pc) != null) {
                ProdottoCarrelloDAO.doUpdateAmountOrPrice(pc);
            } else {
                ProdottoCarrelloDAO.doInsertProduct(pc);
            }
        }
        ProdottoCarrello p2 = c.cercaProdotto(pc.getIdProdotto());
        if (p2 == null)
            c.getProdottiCarrello().add(pc);
        else {
            c.getProdottiCarrello().remove(p2);
            pc.setQuantita(p2.getQuantita() + pc.getQuantita());
            pc.setPrezzoTotale();
            c.getProdottiCarrello().add(pc);
        }
        if (u != null) {
            CarrelloDAO.doUpdateCart(c);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
