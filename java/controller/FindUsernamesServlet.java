package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UtenteDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindUsernamesServlet", value = "/FindUsernamesServlet")
public class FindUsernamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> usernames = UtenteDAO.doRetrieveAllUsernames();
        String json = new Gson().toJson(usernames);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
