package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Carrello;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "ControlloAccessoServlet", value = "/ControlloAccessoServlet")
public class ControlloAccessoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("user");
        String address;
        Carrello c = (Carrello) session.getAttribute("carrello");
        if (c.getNumeroArticoli() == 0)
            response.sendError(403, "Non è possibile effettuare l'ordine se il carrello è vuoto." +
                    " Si prega di non richiamare questa pagina in questo modo, al fine di evitare errori.");
        else {
            if (u == null) {
                address = "login.jsp";
                request.setAttribute("loginError", 1);
            } else if (u.getCreditoUtente() < c.getSpesaTot()){
                address = "WEB-INF/results/noProdOrder.jsp";
                request.setAttribute("noCredit", 1);
            }
            else {
                address = "WEB-INF/results/RiepilogoOrdine.jsp";
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
