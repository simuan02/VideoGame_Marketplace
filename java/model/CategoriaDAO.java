package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> doRetrieveAll (){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Categoria";
            ResultSet rs = stmt.executeQuery(query);
            List<Categoria> categories = new ArrayList<>();
            while (rs.next()){
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setNome(rs.getString(2));
                cat.setDescrizione(rs.getString(3));
                categories.add(cat);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Categoria doRetrieveByName (String nome){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Categoria where nome = '" + nome + "'";
            ResultSet rs = stmt.executeQuery(query);
            Categoria cat = new Categoria();
            while (rs.next()){
                cat.setId(rs.getInt(1));
                cat.setNome(rs.getString(2));
                cat.setDescrizione(rs.getString(3));
            }
            return cat;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Categoria doRetrieveById (int id){
        try {
            Connection conn = ConPool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Categoria where id = " + id + ";";
            ResultSet rs = stmt.executeQuery(query);
            Categoria cat = new Categoria();
            while (rs.next()){
                cat.setId(rs.getInt(1));
                cat.setNome(rs.getString(2));
                cat.setDescrizione(rs.getString(3));
            }
            return cat;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
