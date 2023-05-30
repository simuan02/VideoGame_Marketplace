package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoImgDAO {

    public static List<byte[]> doRetrieveByProdID (int id){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT fileImg\n" +
                    "FROM immagine join prodottoImg on prodottoImg.immagine = immagine.codice \n" +
                    "where prodottoImg.prodotto = " + id + ";";
            ResultSet rs = stmt.executeQuery(query);
            List<byte[]> immagini = new ArrayList<>();
            while(rs.next()){
                byte[] img = rs.getBytes(1);
                immagini.add(img);
            }
            return immagini;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doDeleteByProdID(int id) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM ProdottoImg WHERE prodotto = " + id + ";";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doInsertProdImg(int idProdotto, int idImmagine) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ProdottoImg values (?, ?);");
            pstmt.setInt(2, idImmagine);
            pstmt.setInt(1, idProdotto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
