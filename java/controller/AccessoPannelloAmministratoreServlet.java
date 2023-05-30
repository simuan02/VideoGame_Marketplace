package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "AccessoPannelloAmministratoreServlet", value = "/AccessoPannelloAmministratoreServlet")
public class AccessoPannelloAmministratoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("user");
        String address;
        if (u != null && u.isAdmin()) {
            address = "WEB-INF/results/adminPanel.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
        else
            response.sendError(403, "Richiesta non autorizzata. Solo gli admin possono accedere al pannello amministratore");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
