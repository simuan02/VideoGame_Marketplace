package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "PrelievoAccountServlet", value = "/PrelievoAccountServlet")
public class PrelievoAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente)session.getAttribute("user");
        float credito = Float.parseFloat(request.getParameter("prelievo"));
        float oldCredit = u.getCreditoUtente();
        if (credito < 0 || credito > oldCredit)
            response.sendError(403, "Non cambiare lo script della pagina. Non è possibile effettuare un prelievo negativo o " +
                    "maggiore del saldo");
        UtenteDAO.doUpdateUserCredit(-credito, u);
        u.setCreditoUtente(((u.getCreditoUtente() * 100) - (credito * 100)) / 100);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
