package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = new Utente();
        u.setNome(request.getParameter("name"));
        u.setCognome(request.getParameter("surname"));
        u.setUsername(request.getParameter("username"));
        u.setIndirizzo(request.getParameter("indirizzo"));
        u.setCitta(request.getParameter("citta"));
        u.setNazione(request.getParameter("nazione"));
        u.setCreditoUtente(Float.parseFloat(request.getParameter("credito")));
        u.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
        String oldUsername = request.getParameter("oldUsername");
        UtenteDAO.doUpdateUser(u.getNome(), u.getCognome(), u.getUsername(), u.getIndirizzo(), u.getCitta(), u.getNazione(), oldUsername);
        UtenteDAO.doUpdateAdminAndUserCredit(u.getUsername(), u.isAdmin(), u.getCreditoUtente());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
