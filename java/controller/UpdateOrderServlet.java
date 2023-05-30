package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "UpdateOrderServlet", value = "/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ordine o = new Ordine();
        o.setNomeDestinatario(request.getParameter("name"));
        o.setCognomeDestinatario(request.getParameter("surname"));
        o.setIndirizzoSpedizione(request.getParameter("indirizzo"));
        o.setCittaSpedizione(request.getParameter("citta"));
        o.setNazioneSpedizione(request.getParameter("nazione"));
        o.setStato(request.getParameter("stato"));
        o.setId(request.getParameter("idOrdine"));
        OrdineDAO.doUpdateOrder(o);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
