package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categoria;
import model.CategoriaDAO;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TrovaPiattaformeServlet", value = "/FindPlatformsServlet")
public class TrovaPiattaformeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO prod = new ProdottoDAO();
        List<String> piattaforme = prod.doRetrievePlatforms();
        String json = new Gson().toJson(piattaforme);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
