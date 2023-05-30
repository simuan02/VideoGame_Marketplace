package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;

import java.io.IOException;

@WebServlet(name = "ModificaOrdineServlet", value = "/ModificaOrdineServlet")
public class ModificaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("NomeDestinatarioMod");
        String cognome = request.getParameter("CognomeDestinatarioMod");
        String idOrder = request.getParameter("idOrder");
        String statoOrder = request.getParameter("statoOrder");
        String indirizzo = request.getParameter("IndirizzoMod");
        String citta = request.getParameter("CittaMod");
        String nazione = request.getParameter("NazioneMod");
        Ordine ord = new Ordine();
        ord.setNomeDestinatario(nome);
        ord.setCognomeDestinatario(cognome);
        ord.setId(idOrder);
        ord.setStato(statoOrder);
        ord.setIndirizzoSpedizione(indirizzo);
        ord.setCittaSpedizione(citta);
        ord.setNazioneSpedizione(nazione);
        OrdineDAO.doUpdateOrder(ord);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/results/profile.jsp");
        request.setAttribute("OrdineModificato", 1);
        dispatcher.forward(request, response);
    }
}
