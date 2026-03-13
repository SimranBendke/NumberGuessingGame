package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Game;
import util.DBConnection;

public class GameDAO {

   public boolean saveResult(String username, int attempts, String status) {

        boolean isSaved = false;

        String sql = "INSERT INTO game_results (username, attempts, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setInt(2, attempts);
            ps.setString(3, status);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                isSaved = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSaved;
    }

    public List<Game> getAllHistory() {

        List<Game> list = new ArrayList<>();

        String sql = "SELECT * FROM game_results ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Game g = new Game();

                g.setId(rs.getInt("id"));
                g.setUsername(rs.getString("username"));
                g.setAttempts(rs.getInt("attempts"));
                g.setStatus(rs.getString("status"));
                g.setPlayedAt(rs.getTimestamp("played_time"));

                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
 // Delete all game history from database
    public boolean deleteAllHistory() {

        boolean deleted = false;
        String sql = "DELETE FROM game_results";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.executeUpdate();
            deleted = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

}
