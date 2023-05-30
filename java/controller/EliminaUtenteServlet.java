package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CarrelloDAO;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "EliminaUtenteServlet", value = "/EliminaUtenteServlet")
public class EliminaUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Utente u = UtenteDAO.doRetrieveByUsername(username);
        if (u != null) {
            UtenteDAO.doDeleteUser(username);
            CarrelloDAO.doDeleteCartbyCode(u.getCarrello().getCodice());
        }
        else
            response.sendError(400, "Utente inesistente");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
