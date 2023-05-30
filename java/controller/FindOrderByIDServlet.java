package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Ordine;
import model.OrdineDAO;

import java.io.IOException;

@WebServlet(name = "FindOrderByIDServlet", value = "/FindOrderByIDServlet")
public class FindOrderByIDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idOrdine = request.getParameter("order");
        Ordine ord = OrdineDAO.doRetrieveByID(idOrdine);
        if (ord != null) {
            String json = new Gson().toJson(ord);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else
            response.sendError(400, "Ordine inesistente");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
