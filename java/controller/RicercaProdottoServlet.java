package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RicercaProdottoServlet", value = "/ricerca_prodotto_servlet")
public class RicercaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeProdotto = request.getParameter("prodotto");
        String piattaforma;
        if (request.getParameter("Piattaforma").equalsIgnoreCase("Any"))
            piattaforma = "";
        else
            piattaforma = request.getParameter("Piattaforma");
        String nomeCategoria = request.getParameter("Categoria");
        int idCategoria;
        if (nomeCategoria.equalsIgnoreCase("Any"))
            idCategoria = -1;
        else
            idCategoria = CategoriaDAO.doRetrieveByName(nomeCategoria).getId();
        float minPrice, maxPrice;
        if (request.getParameter("MinPrice") == null)
        {
            minPrice = 0;
            maxPrice = 100000;
        }
        else {
            if (request.getParameter("MinPrice").equals(""))
                minPrice = 0;
            else
                minPrice = Float.parseFloat(request.getParameter("MinPrice"));
            if (request.getParameter("MaxPrice").equals(""))
                maxPrice = 100000;
            else
                maxPrice = Float.parseFloat(request.getParameter("MaxPrice"));
        }
        boolean offerta;
        if(request.getParameter("Offerta") == null)
            offerta = false;
        else
            offerta = true;
        ProdottoDAO pdDAO = new ProdottoDAO();
        List<Prodotto> prodotti = pdDAO.doRetrieveByConstraints(piattaforma, nomeProdotto, idCategoria, minPrice, maxPrice, offerta);
        String address;
        if (prodotti.isEmpty()){
            address = "/WEB-INF/results/emptyResearch.jsp";
        } else {
            request.setAttribute("prodotti", prodotti);
            address = "/WEB-INF/results/research.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
