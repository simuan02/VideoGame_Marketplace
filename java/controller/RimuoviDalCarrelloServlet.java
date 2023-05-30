package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RimuoviDalCarrelloServlet", value = "/rimuoviDalCarrelloServlet")
public class RimuoviDalCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente)session.getAttribute("user");
        Carrello c = (Carrello) session.getAttribute("carrello");
        int prodID = Integer.parseInt(request.getParameter("prodID"));
        ProdottoCarrello pcMomentaneo = null;
        for (ProdottoCarrello pc : c.getProdottiCarrello()) {
            if (pc.getIdProdotto() == prodID) {
                pcMomentaneo = pc;
                c.setNumeroArticoli(c.getNumeroArticoli() - pc.getQuantita());
                c.setSpesaTot(c.getSpesaTot() - pc.getPrezzoTotale());
            }
        }
        c.getProdottiCarrello().remove(pcMomentaneo);
        if (u != null){
            if (ProdottoCarrelloDAO.doDeleteByProdIDAndCartCode(u.getCarrello().getCodice(), prodID)) {
                CarrelloDAO.doUpdateCart(c);
            }
            else
                response.sendError(406);
        }
        List<Prodotto> prodotti = (List<Prodotto>)session.getAttribute("contenutoCart");
        Prodotto tempProd = new Prodotto();
        for (Prodotto p : prodotti){
            if (p.getId() == prodID)
                tempProd = p;
        }
        prodotti.remove(tempProd);
        session.setAttribute("contenutoCart", prodotti);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
