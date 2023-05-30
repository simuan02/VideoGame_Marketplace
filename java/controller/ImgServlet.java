package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoImgDAO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImgServlet", value = "/ImgServlet")
public class ImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
        int imgNumber = Integer.parseInt(request.getParameter("imgNumber"));
        List<byte[]> images = ProdottoImgDAO.doRetrieveByProdID(id);
        ServletOutputStream out = response.getOutputStream();
        if(images.size() > 0)
        {
            out.write(images.get(imgNumber));
            response.setContentType("image/jpeg");
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
