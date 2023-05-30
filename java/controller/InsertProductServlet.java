package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;

@WebServlet(name = "InsertProductServlet", value = "/InsertProductServlet")
public class InsertProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto p = new Prodotto();
        Categoria cat = CategoriaDAO.doRetrieveById(Integer.parseInt(request.getParameter("categoria")));
        if (cat.getNome() != null) {
            p.setNome(request.getParameter("name"));
            p.setDescrizione(request.getParameter("description"));
            p.setPrezzoCatalogo(Float.parseFloat(request.getParameter("PrezzoCatalogo")));
            p.setPrezzoAttuale(Float.parseFloat(request.getParameter("PrezzoAttuale")));
            p.setQuantita(Integer.parseInt(request.getParameter("quantita")));
            p.setCategoria(cat.getId());
            p.setPiattaforma(request.getParameter("piattaforma"));
            if (p.getPrezzoAttuale()<0 || p.getPrezzoCatalogo()<0 || p.getQuantita()<0){
                response.sendError(400, "Prezzi e quantità devono essere maggiori di 0");
            }
            else
                ProdottoDAO.doInsertProduct(p);
        }
        else{
            response.sendError(400, "Categoria inesistente");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
