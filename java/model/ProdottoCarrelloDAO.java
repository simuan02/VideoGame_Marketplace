package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoCarrelloDAO {

    public static void doInsertProduct (ProdottoCarrello pc){
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ProdottoCarrello values (?, ?, ?, ?, ?)");
            pstmt.setInt(1, pc.getIdProdotto());
            pstmt.setString(2, pc.getCodiceCarrello());
            pstmt.setInt(3, pc.getQuantita());
            pstmt.setFloat(4, pc.getPrezzoCad());
            pstmt.setFloat(5, pc.getPrezzoTotale());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doUpdateAmountOrPrice (ProdottoCarrello pc){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "UPDATE ProdottoCarrello SET quantita = quantita + " + pc.getQuantita() + ", costoCad = " +
                    pc.getPrezzoCad() + ", costoTotale = costoTotale + " + pc.getPrezzoTotale() +
                    " WHERE idProdotto = " + pc.getIdProdotto() + " and codiceCarrello = '" + pc.getCodiceCarrello() + "';";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ProdottoCarrello> doRetrieveByCartCode (String codice){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ProdottoCarrello WHERE codiceCarrello = '" + codice + "';";
            ResultSet rs = stmt.executeQuery(query);
            List<ProdottoCarrello> prodotti = new ArrayList<>();
            while (rs.next()){
                ProdottoCarrello pc = new ProdottoCarrello();
                pc.setIdProdotto(rs.getInt(1));
                pc.setCodiceCarrello(rs.getString(2));
                pc.setQuantita(rs.getInt(3));
                pc.setPrezzoCad(rs.getFloat(4));
                pc.setPrezzoTotale();
                prodotti.add(pc);
            }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doDeleteByCartCode (String codice){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM ProdottoCarrello WHERE codiceCarrello = '" + codice + "';";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProdottoCarrello doRetrieveProdottoCarrello (ProdottoCarrello pc){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ProdottoCarrello WHERE codiceCarrello = '" + pc.getCodiceCarrello() + "' and idProdotto = "
                    + pc.getIdProdotto() + ";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                return pc;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean doDeleteByProdIDAndCartCode(String codice, int prodID) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String update = "DELETE FROM ProdottoCarrello WHERE idProdotto = " + prodID + " and codiceCarrello = '" + codice + "';";
            stmt.executeUpdate(update);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doDeleteByProdID(int prodID) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String update = "DELETE FROM ProdottoCarrello WHERE idProdotto = " + prodID + ";";
            stmt.executeUpdate(update);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void doUpdatePriceByProdID (int prodID, float prezzo){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE ProdottoCarrello SET costoCad = " + prezzo + ", costoTotale = quantita * " + prezzo +
                    " WHERE idProdotto = " + prodID;
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
