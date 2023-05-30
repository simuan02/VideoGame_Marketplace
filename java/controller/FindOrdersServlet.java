package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindOrdersServlet", value = "/FindOrdersServlet")
public class FindOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente)session.getAttribute("user");
        List<Ordine> ordini = OrdineDAO.doRetrieveByUser(u.getUsername());
        String json = new Gson().toJson(ordini);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
