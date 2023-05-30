package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowCartServlet", value = "/show_cart_servlet")
public class ShowCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente)session.getAttribute("user");
        Carrello c = (Carrello)session.getAttribute("carrello");
        if(c.getProdottiCarrello().isEmpty()){
            request.setAttribute("CarrelloVuoto", true);
        }
        else {
            List<Prodotto> prodotti = new ArrayList<>();
            if (u != null) {
                prodotti = ProdottoDAO.doRetrieveProductsInUserCart(u.getCarrello().getCodice());
            } else {
                for (ProdottoCarrello pc : c.getProdottiCarrello()) {
                    prodotti.add(ProdottoDAO.doMergeProdottoCarrelloandProdotto(pc));
                }
            }
            session.setAttribute("contenutoCart", prodotti);
        }
        String address = "WEB-INF/results/cart.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
