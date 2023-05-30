package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoOrdineDAO {

    public static void doInsertProdottoOrdine (ProdottoOrdine po){
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT into ProdottoOrdine values (?, ?, ?, ?)");
            pstmt.setInt (1, po.getCodiceProdotto());
            pstmt.setString (2, po.getCodiceOrdine());
            pstmt.setFloat (3, po.getCostoAcquisto());
            pstmt.setInt (4, po.getQuantita());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<ProdottoOrdine> doRetrieveByOrderId(String id){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ProdottoOrdine WHERE codiceOrdine = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query);
            List<ProdottoOrdine> prodotti = new ArrayList<>();
            while(rs.next()){
                ProdottoOrdine po = new ProdottoOrdine();
                po.setCodiceProdotto(rs.getInt(1));
                po.setCodiceOrdine(rs.getString(2));
                po.setCostoAcquisto(rs.getFloat(3));
                po.setQuantita(rs.getInt(4));
                prodotti.add(po);
            }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void doDeleteByOrderID(String id) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM ProdottoOrdine WHERE codiceOrdine = '" + id + "';";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
