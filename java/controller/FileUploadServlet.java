package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Immagine;
import model.ImmagineDAO;
import model.ProdottoImgDAO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*1024*100,
        maxFileSize=1024*1024*200,
        maxRequestSize=1024*1024*500)
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static String SAVE_DIR ="C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Prodotti";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File fileSaveDir = new File(SAVE_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        if(idProdotto == 0)
            response.sendError(400, "Selezionare un prodotto dalla lista");
        else {
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.equals("")) {
                    part.write(SAVE_DIR + File.separator + fileName);
                    try {
                        ImmagineDAO.doInsertImage(SAVE_DIR + File.separator + fileName);
                        int idImmagine = ImmagineDAO.doRetrieveLastInsertedImageID();
                        ProdottoImgDAO.doInsertProdImg(idProdotto, idImmagine);
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
            }
            request.setAttribute("ImmagineInserita", 1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/results/adminPanel.jsp");
            dispatcher.forward(request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}