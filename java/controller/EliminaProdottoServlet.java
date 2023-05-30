package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProdottoCarrelloDAO;
import model.ProdottoDAO;
import model.ProdottoImgDAO;
import model.ProdottoOrdineDAO;

import java.io.IOException;

@WebServlet(name = "EliminaProdottoServlet", value = "/EliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (ProdottoDAO.doRetrieveByID(id) != null){
            ProdottoDAO.doDeleteProduct(id);
            ProdottoImgDAO.doDeleteByProdID(id);
            ProdottoCarrelloDAO.doDeleteByProdID(id);
        }
        else {
            response.sendError(400, "Prodotto Inesistente");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
