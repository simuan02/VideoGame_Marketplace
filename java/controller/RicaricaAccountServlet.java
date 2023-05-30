package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "RicaricaAccountServlet", value = "/RicaricaAccountServlet")
public class RicaricaAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente)session.getAttribute("user");
        float credito = Float.parseFloat(request.getParameter("ricarica"));
        if (credito < 0)
            response.sendError(403, "Non cambiare lo script della pagina. Non è possibile effettuare una ricarica negativa");
        UtenteDAO.doUpdateUserCredit(credito, u);
        u.setCreditoUtente(((u.getCreditoUtente() * 100) + (credito * 100)) / 100);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
