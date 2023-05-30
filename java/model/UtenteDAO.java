package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.ConPool.getConnection;

public class UtenteDAO {

    public Utente doRetrieveByUsernameAndPassword (Utente user){
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utente WHERE username = ? and password = ?", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            ResultSet rs = pstmt.executeQuery();
            Utente u = new Utente();
            int count = 0;
            while (rs.next()) {
                Carrello c = new Carrello();
                u.setNome(rs.getString(1));
                u.setCognome(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setIndirizzo(rs.getString(5));
                u.setCitta(rs.getString(6));
                u.setNazione(rs.getString(7));
                c.setCodice(rs.getString(8));
                u.setCarrello(c);
                u.setAdmin(rs.getBoolean(9));
                u.setCreditoUtente(rs.getFloat(10));
                count++;
            }
            if (count < 1)
                return null;
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Utente doRetrieveByUsername (String username){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Utente WHERE Username = '" + username +"';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Carrello c = new Carrello();
                Utente u = new Utente();
                u.setNome(rs.getString(1));
                u.setCognome(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setIndirizzo(rs.getString(5));
                u.setCitta(rs.getString(6));
                u.setNazione(rs.getString(7));
                c.setCodice(rs.getString(8));
                u.setCarrello(c);
                u.setAdmin(rs.getBoolean(9));
                u.setCreditoUtente(rs.getFloat(10));
                return u;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doInsertUser (Utente u){
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Utente values (?, ?, ?, ?, ?, ?, ?, ?, ?, 0)");
            pstmt.setString(1, u.getNome());
            pstmt.setString(2, u.getCognome());
            pstmt.setString(3, u.getUsername());
            pstmt.setString(4, u.getPassword());
            pstmt.setString(5, u.getIndirizzo());
            pstmt.setString(6, u.getCitta());
            pstmt.setString(7, u.getNazione());
            pstmt.setString(8, u.getCarrello().getCodice());
            pstmt.setBoolean(9, u.isAdmin());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doUpdateUserCartCode (String code, String username){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "UPDATE Utente SET carrello = '" + code + "' WHERE username = '" + username + "';";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> doRetrieveAllUsernames(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT username FROM utente";
            ResultSet rs = stmt.executeQuery(query);
            List<String> usernames = new ArrayList<>();
            while(rs.next()){
                String username = rs.getString(1);
                usernames.add(username);
            }
            return usernames;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doUpdateUser (String nome, String cognome, String username, String indirizzo, String citta, String nazione, String oldUsername){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Utente SET nome = '" + nome + "', cognome = '" + cognome +
                    "', username = '" + username + "', indirizzo = '" + indirizzo +
                    "', citta = '" + citta + "', nazione = '" + nazione + "' WHERE username = '" + oldUsername + "';";
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void doUpdateUserCredit(float credito, Utente user) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Utente SET creditoUtente = creditoUtente + " + credito + " WHERE username = '" + user.getUsername()
                    + "';";
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doUpdateAdminAndUserCredit(String username, boolean admin, float credito) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Utente SET admin = " + admin + ", creditoUtente = " + credito + " WHERE username = '" + username + "';";
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doDeleteUser(String username){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM Utente WHERE username = '" + username + "';";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
