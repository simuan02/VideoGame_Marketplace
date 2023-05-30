package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CancellaOrdineServlet", value = "/CancellaOrdineServlet")
public class CancellaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idOrdine = request.getParameter("idOrdine");
        Ordine ord = OrdineDAO.doRetrieveByID(idOrdine);
        if (ord == null){
            response.sendError(500, "Errore del server");
        }
        else if (ord.getId() == null) {
            response.sendError(400, "Ordine inesistente");
        }
        else if (!ord.getStato().equals("In lavorazione")){
            response.sendError(400, "Stato dell'ordine diverso da \'in lavorazione\'");
        }
        else {
            ord.setStato("Cancellato");
            OrdineDAO.doUpdateOrder(ord);
            List<ProdottoOrdine> prodsOrd = ProdottoOrdineDAO.doRetrieveByOrderId(idOrdine);
            for (ProdottoOrdine po : prodsOrd){
                Prodotto p = ProdottoDAO.doRetrieveByID(po.getCodiceProdotto());
                ProdottoDAO.doUpdateQuantita(po.getQuantita() + p.getQuantita(), p.getId());
            }
            Utente u = UtenteDAO.doRetrieveByUsername(ord.getUtente());
            UtenteDAO.doUpdateUserCredit(ord.getSpesaTotale(), u);
            HttpSession session = request.getSession();
            session.setAttribute("user", UtenteDAO.doRetrieveByUsername(u.getUsername()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
