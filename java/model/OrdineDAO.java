package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {

    public static void doInsertOrdine (Ordine o){
        try {
            Connection conn = ConPool.getConnection();
            LocalDate date = LocalDate.now();
            String dateString;
            if (date.getDayOfMonth() < 10 && date.getMonthValue() < 10){
                dateString = date.getYear() + "-0" + date.getMonthValue() + "-0" + date.getDayOfMonth();
            }
            else if (date.getDayOfMonth() < 10){
                dateString = date.getYear() + "-" + date.getMonthValue() + "-0" + date.getDayOfMonth();
            }
            else if (date.getMonthValue() < 10){
                dateString = date.getYear() + "-0" + date.getMonthValue() + "-" + date.getDayOfMonth();
            }
            else {
                dateString = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
            }
            PreparedStatement pstmt =
                    conn.prepareStatement("INSERT INTO Ordine(id, stato, metodoPagamento, indirizzoSpedizione, cittaSpedizione, " +
                            "nazioneSpedizione, spesaTotale, numeroArticoli, nomeDestinatario, cognomeDestinatario, data, utente) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '" + dateString + "', ?)");
            pstmt.setString(1, o.getId());
            pstmt.setString(2, o.getStato());
            pstmt.setString(3, o.getMetodoPagamento());
            pstmt.setString(4, o.getIndirizzoSpedizione());
            pstmt.setString(5, o.getCittaSpedizione());
            pstmt.setString(6, o.getNazioneSpedizione());
            pstmt.setFloat(7, o.getSpesaTotale());
            pstmt.setInt(8, o.getNumeroArticoli());
            pstmt.setString(9, o.getNomeDestinatario());
            pstmt.setString(10, o.getCognomeDestinatario());
            pstmt.setString(11, o.getUtente());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Ordine> doRetrieveByUser(String username) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Ordine WHERE utente = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);
            List<Ordine> ordini = new ArrayList<>();
            while(rs.next()){
                Ordine ord = new Ordine();
                ord.setId(rs.getString(1));
                ord.setStato(rs.getString(2));
                ord.setMetodoPagamento(rs.getString(3));
                ord.setIndirizzoSpedizione(rs.getString(4));
                ord.setCittaSpedizione(rs.getString(5));
                ord.setNazioneSpedizione(rs.getString(6));
                ord.setSpesaTotale(rs.getFloat(7));
                ord.setNumeroArticoli(rs.getInt(8));
                ord.setNomeDestinatario(rs.getString(9));
                ord.setCognomeDestinatario(rs.getString(10));
                ord.setData(rs.getString(11));
                ord.setUtente(rs.getString(12));
                ordini.add(ord);
            }
            return ordini;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Ordine doRetrieveByID(String idOrdine) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Ordine WHERE id = '" + idOrdine + "';";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Ordine ord = new Ordine();
                ord.setId(rs.getString(1));
                ord.setStato(rs.getString(2));
                ord.setMetodoPagamento(rs.getString(3));
                ord.setIndirizzoSpedizione(rs.getString(4));
                ord.setCittaSpedizione(rs.getString(5));
                ord.setNazioneSpedizione(rs.getString(6));
                ord.setSpesaTotale(rs.getFloat(7));
                ord.setNumeroArticoli(rs.getInt(8));
                ord.setNomeDestinatario(rs.getString(9));
                ord.setCognomeDestinatario(rs.getString(10));
                ord.setData(rs.getString(11));
                ord.setUtente(rs.getString(12));
                return ord;
            }
            return new Ordine();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doUpdateOrder(Ordine ord) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Ordine SET stato = '" + ord.getStato() + "', indirizzoSpedizione = '" + ord.getIndirizzoSpedizione()
                    + "', cittaSpedizione = '" + ord.getCittaSpedizione() + "', nazioneSpedizione = '" + ord.getNazioneSpedizione() +
                    "', nomeDestinatario = '" + ord.getNomeDestinatario() +
                    "', cognomeDestinatario = '" + ord.getCognomeDestinatario() + "' WHERE id = '" + ord.getId() + "';";
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Ordine> doRetrieveAll() {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Ordine;";
            ResultSet rs = stmt.executeQuery(query);
            List<Ordine> orders = new ArrayList<>();
            while(rs.next()){
                Ordine ord = new Ordine();
                ord.setId(rs.getString(1));
                ord.setStato(rs.getString(2));
                ord.setMetodoPagamento(rs.getString(3));
                ord.setIndirizzoSpedizione(rs.getString(4));
                ord.setCittaSpedizione(rs.getString(5));
                ord.setNazioneSpedizione(rs.getString(6));
                ord.setSpesaTotale(rs.getFloat(7));
                ord.setNumeroArticoli(rs.getInt(8));
                ord.setNomeDestinatario(rs.getString(9));
                ord.setCognomeDestinatario(rs.getString(10));
                ord.setData(rs.getString(11));
                ord.setUtente(rs.getString(12));
                orders.add(ord);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void doDeleteOrder(String id) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM Ordine WHERE id = '" + id + "';";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
