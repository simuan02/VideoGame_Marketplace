package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String address;
        if (UtenteDAO.doRetrieveByUsername(username) != null){
            address = "register.jsp";
            request.setAttribute("rf", true);
        }
        else {
            String password = request.getParameter("password");
            String indirizzo = request.getParameter("indirizzo");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter ("cognome");
            String citta = request.getParameter("citta");
            String nazione = request.getParameter("nazione");
            Utente u = new Utente();
            HttpSession session = request.getSession();
            if (u.setPassword(password)) {
                u.setUsername(username);
                u.setAdmin(false);
                u.setIndirizzo(indirizzo);
                u.setNome(nome);
                u.setCognome(cognome);
                u.setCitta(citta);
                u.setNazione(nazione);
                u.setCreditoUtente(0);
                CarrelloDAO.doInsertCart((Carrello) session.getAttribute("carrello"));
                Carrello c2 = (Carrello) session.getAttribute("carrello");
                for (ProdottoCarrello pc : c2.getProdottiCarrello()){
                    ProdottoCarrelloDAO.doInsertProduct(pc);
                }
                u.setCarrello((Carrello) session.getAttribute("carrello"));
                UtenteDAO.doInsertUser(u);
                session.setAttribute("user", u);
                address = "index.jsp";
            }
            else {
                address = "register.jsp";
                request.setAttribute("passerror", true);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
