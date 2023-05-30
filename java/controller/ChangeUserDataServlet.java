package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChangeUserDataServlet", value = "/ChangeUserDataServlet")
public class ChangeUserDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String username = request.getParameter("username");
        String indirizzo = request.getParameter("indirizzo");
        String vecchioUsername = request.getParameter("vecchioUsername");
        String citta = request.getParameter("citta");
        String nazione = request.getParameter("nazione");
        List<String> usernames = UtenteDAO.doRetrieveAllUsernames();
        boolean t = true;
        for (String x : usernames){
            if (x.equalsIgnoreCase(username) && !x.equalsIgnoreCase(vecchioUsername)){
                response.sendError(400);
                t = false;
            }
        }
        if (t) {
            HttpSession session = request.getSession();
            Utente u = (Utente) session.getAttribute("user");
            u.setNome(nome);
            u.setCognome(cognome);
            u.setUsername(username);
            u.setIndirizzo(indirizzo);
            u.setCitta(citta);
            u.setNazione(nazione);
            UtenteDAO.doUpdateUser(nome, cognome, username, indirizzo, citta, nazione, vecchioUsername);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
