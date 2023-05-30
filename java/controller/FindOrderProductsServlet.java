package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindOrderProductsServlet", value = "/FindOrderProductsServlet")
public class FindOrderProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderID");
        List<Prodotto> prodottiOrdine = ProdottoDAO.doRetrieveByOrderID(orderId);
        String json = new Gson().toJson(prodottiOrdine);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
