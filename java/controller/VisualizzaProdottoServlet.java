package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisualizzaProdottoServlet", value = "/visualizzaProdotto")
public class VisualizzaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodottoID = Integer.parseInt(request.getParameter("id"));
        String address;
        if (request.getParameter("id") == null) {
            address = "index.jsp";
            request.setAttribute("idNotFound", "X");
        }
        else {
            Prodotto p = ProdottoDAO.doRetrieveByID(prodottoID);
            Categoria cat = CategoriaDAO.doRetrieveById(p.getCategoria());
            request.setAttribute("categoria", cat);
            request.setAttribute("prodotto", p);
            address = "WEB-INF/results/visualizzaProdotto.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
