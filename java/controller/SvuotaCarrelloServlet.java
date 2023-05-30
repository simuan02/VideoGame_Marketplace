package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Carrello;
import model.CarrelloDAO;
import model.ProdottoCarrelloDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SvuotaCarrelloServlet", value = "/SvuotaCarrelloServlet")
public class SvuotaCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente)session.getAttribute("user");
        Carrello c = (Carrello)session.getAttribute("carrello");
        c.setProdottiCarrello(new ArrayList<>());
        c.setSpesaTot(0.0F);
        c.setNumeroArticoli(0);
        session.setAttribute("carrello", c);
        if (u != null){
            ProdottoCarrelloDAO.doDeleteByCartCode(c.getCodice());
            CarrelloDAO.doUpdateCart(c);
        }
        session.removeAttribute("contenutoCart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
