package model;

import java.sql.*;

public class CarrelloDAO {

    public static void doInsertCart (Carrello c){
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Carrello values (?, ?, ?);");
            pstmt.setString(1, c.getCodice());
            pstmt.setFloat(2, c.getSpesaTot());
            pstmt.setInt(3, c.getNumeroArticoli());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doDeleteCartbyCode(String code){
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Carrello WHERE Codice = ?");
            pstmt.setString(1, code);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doUpdateCart (Carrello c){
        Connection conn = null;
        try {
            conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Carrello SET numeroArticoli = ?, spesaTot = ? WHERE codice = ?");
            pstmt.setInt(1, c.getNumeroArticoli());
            pstmt.setFloat(2, c.getSpesaTot());
            pstmt.setString(3, c.getCodice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Carrello doRetrieveByUsername (String username){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT c.codice, c.spesaTot, c.numeroArticoli FROM Carrello c JOIN Utente u ON c.codice = u.carrello " +
                    "WHERE u.username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);
            Carrello c = new Carrello();
            while(rs.next()){
                c.setCodice(rs.getString(1));
                c.setSpesaTot(rs.getFloat(2));
                c.setNumeroArticoli(rs.getInt(3));
                c.setProdottiCarrello(ProdottoCarrelloDAO.doRetrieveByCartCode(c.getCodice()));
                return c;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
