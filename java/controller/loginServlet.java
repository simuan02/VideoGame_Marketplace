package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Utente u = new Utente();
        u.setUsername(username);
        u.setPassword(password);
        UtenteDAO uDAO = new UtenteDAO();
        HttpSession session = request.getSession();
        String address;
        Utente u2 = uDAO.doRetrieveByUsernameAndPassword(u);
        if (u2 != null) {
            u2.setCarrello(CarrelloDAO.doRetrieveByUsername(u2.getUsername()));
            if (u2.getCarrello().getNumeroArticoli() == 0) {
                String codiceCarrelloVecchio = u2.getCarrello().getCodice();
                u2.setCarrello((Carrello)session.getAttribute("carrello"));
                CarrelloDAO.doInsertCart(u2.getCarrello());
                UtenteDAO.doUpdateUserCartCode(u2.getCarrello().getCodice(), u2.getUsername());
                CarrelloDAO.doDeleteCartbyCode(codiceCarrelloVecchio);
                for (ProdottoCarrello pc : u2.getCarrello().getProdottiCarrello()){
                    ProdottoCarrelloDAO.doInsertProduct(pc);
                }
            }
            else {
                u2.getCarrello().setProdottiCarrello(ProdottoCarrelloDAO.doRetrieveByCartCode(u2.getCarrello().getCodice()));
                session.setAttribute("carrello", u2.getCarrello());
            }
            session.setAttribute("user", u2);
            address = "index.jsp";
        }
        else {
            address = "login.jsp";
            request.setAttribute("lf", false);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
