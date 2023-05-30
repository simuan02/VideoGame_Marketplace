package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categoria;
import model.CategoriaDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RicercaCategoriaServlet", value = "/RicercaCategoriaServlet")
public class RicercaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO cd = new CategoriaDAO();
        List<Categoria> categorie = cd.doRetrieveAll();
        String json = new Gson().toJson(categorie);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
