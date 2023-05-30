package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;

@WebServlet(name = "FindProductByIDServlet", value = "/FindProductByIDServlet")
public class FindProductByIDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto p = ProdottoDAO.doRetrieveByID(Integer.parseInt(request.getParameter("product")));
        if (p == null){
            response.sendError(400, "Prodotto inesistente");
        }
        else {
            String json = new Gson().toJson(p);
            response.setContentType("applicatioon/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
