package model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    public static void doInsertProduct(Prodotto p){
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO Prodotto (nome, descrizione, prezzoCatalogo, prezzoAttuale, quantita, categoria, piattaforma)" +
                            "values (?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getDescrizione());
            pstmt.setFloat(3, p.getPrezzoCatalogo());
            pstmt.setFloat(4, p.getPrezzoAttuale());
            pstmt.setInt(5, p.getQuantita());
            pstmt.setInt(6, p.getCategoria());
            pstmt.setString(7, p.getPiattaforma());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Prodotto doMergeProdottoCarrelloandProdotto(ProdottoCarrello pc) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT P.id, P.nome, P.descrizione, P.prezzoAttuale, P.categoria, P.piattaforma " +
                    "FROM Prodotto P WHERE P.id = " + pc.getIdProdotto();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCatalogo(pc.getPrezzoTotale()); //prezzoCatalogo contiene prezzoCaduno * numeroArticoli
                p.setQuantita(pc.getQuantita());//quantità contiene il numero di articoli di quel prodotto
                p.setPrezzoAttuale(rs.getFloat(4));
                p.setCategoria(rs.getInt(5));
                p.setPiattaforma(rs.getString(6));
                return p;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Prodotto> doRetrieveAll (){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Prodotto;";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            List<Prodotto> prodotti = new ArrayList<Prodotto>();
            while (rs.next()){
                Prodotto p = creaProdotto(rs);
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void doUpdateQuantita(int quantita, int id) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Prodotto SET quantita = " + quantita + " WHERE id = " + id;
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doDeleteProduct(int id) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM Prodotto WHERE id = " + id + ";";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doUpdateProduct(Prodotto p) {
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Prodotto SET nome = '" + p.getNome() + "', descrizione = '" + p.getDescrizione() +
                    "', prezzoCatalogo = " + p.getPrezzoCatalogo() + ", prezzoAttuale = " + p.getPrezzoAttuale() +
                    ", quantita = " + p.getQuantita() + ", categoria = " + p.getCategoria() +
                    ", piattaforma = '" + p.getPiattaforma() + "' WHERE id = " + p.getId() + " ;";
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prodotto> doRetrieveByName (String nome){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Prodotto WHERE nome LIKE '%" + nome + "%';";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            List<Prodotto> prodotti = new ArrayList<Prodotto>();
            while (rs.next()){
                Prodotto p = creaProdotto(rs);
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Prodotto doRetrieveByID (int id){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Prodotto WHERE id = " + id + ";";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                Prodotto p = creaProdotto(rs);
                return p;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Prodotto creaProdotto(ResultSet rs) throws SQLException {
        Prodotto p = new Prodotto();
        p.setNome(rs.getString(1));
        p.setId(rs.getInt(2));
        p.setDescrizione(rs.getString(3));
        p.setPrezzoCatalogo(rs.getFloat(4));
        p.setPrezzoAttuale(rs.getFloat(5));
        p.setQuantita(rs.getInt(6));
        p.setCategoria(rs.getInt(7));
        p.setPiattaforma(rs.getString(8));
        return p;
    }

    public List<String> doRetrievePlatforms (){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT DISTINCT Piattaforma FROM Prodotto";
            ResultSet rs = stmt.executeQuery(query);
            List<String> platforms = new ArrayList<>();
            while (rs.next()){
                platforms.add(rs.getString(1));
            }
            return platforms;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Prodotto>  doRetrieveByConstraints(String piattaforma, String nome, int categoria, float minPrice, float maxPrice, boolean scontato){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query2 = "SELECT * FROM Prodotto WHERE Nome LIKE '%" + nome + "%' and Piattaforma LIKE '%" + piattaforma + "%' and " +
                    "(Categoria LIKE '%" + categoria + "%' or " + categoria + "= -1) and PrezzoAttuale >= " + minPrice +
                    "and prezzoAttuale <= " + maxPrice;
            String query;
            if (scontato == true)
                query = query2.concat(" and prezzoCatalogo - prezzoAttuale > 0");
            else
                query = query2.concat(";");
            ResultSet rs = stmt.executeQuery(query);
            List<Prodotto> prodotti = new ArrayList<Prodotto>();
            while (rs.next()){
                Prodotto p = creaProdotto(rs);
                prodotti.add(p);
                }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<Prodotto> doRetrieveProductsInUserCart(String cartCode){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT P.id, P.nome, P.descrizione, PC.costoCad, P.categoria, P.piattaforma, PC.Quantita, PC.CostoTotale " +
                    "FROM Prodotto P JOIN ProdottoCarrello PC ON P.id = PC.idProdotto " +
                    "WHERE PC.codiceCarrello = '" + cartCode + "';";
            ResultSet rs = stmt.executeQuery(query);
            List<Prodotto> prodotti = new ArrayList<>();
            while (rs.next()){
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCatalogo(rs.getFloat(8)); //prezzoCatalogo contiene prezzoCaduno * numeroArticoli
                p.setQuantita(rs.getInt(7));//quantità contiene il numero di articoli di quel prodotto
                p.setPrezzoAttuale(rs.getFloat(4));
                p.setCategoria(rs.getInt(5));
                p.setPiattaforma(rs.getString(6));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Prodotto> doRetrieveByOrderID (String orderId){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ProdottoOrdine WHERE codiceOrdine = '" + orderId + "';";
            ResultSet rs =stmt.executeQuery(query);
            List<Prodotto> prodotti = new ArrayList<>();
            while (rs.next()){
                Prodotto p = ProdottoDAO.doRetrieveByID(rs.getInt(1));
                p.setPrezzoAttuale(rs.getFloat(3));
                p.setQuantita(rs.getInt(4));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
