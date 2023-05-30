package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.OrdineDAO;
import model.ProdottoOrdineDAO;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "EliminaOrdineServlet", value = "/EliminaOrdineServlet")
public class EliminaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (OrdineDAO.doRetrieveByID(id) != null) {
            OrdineDAO.doDeleteOrder(id);
            ProdottoOrdineDAO.doDeleteByOrderID(id);
        }
        else
            response.sendError(400, "Ordine inesistente");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
