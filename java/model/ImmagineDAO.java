package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class ImmagineDAO {
    public synchronized static void doInsertImage(String photo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConPool.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO Immagine (fileImg) values (?)");
            File file = new File(photo);
            try {
                FileInputStream fis = new FileInputStream(file);
                pstmt.setBinaryStream(1, fis, fis.available());
                pstmt.executeUpdate();
                conn.commit();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int doRetrieveLastInsertedImageID() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = ConPool.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT max(codice) FROM Immagine";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id2 = rs.getInt(1);
                return id2;
            }
            return -1;
        }
        catch(SQLException e){
            e.printStackTrace();
            return -2;
        }
    }
}
