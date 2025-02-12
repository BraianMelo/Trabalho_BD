package persistencia;

import java.sql.*;
import java.util.List; 
import java.util.ArrayList;

public class AvistamentoTestemunhaDAO {

    public List<Integer> buscarIdsTestemunhasPorAvistamento(int idAvistamento) {
        String sql = "SELECT ID_Testemunha " +
                     "FROM Avistamento_Testemunha " +
                     "WHERE ID_Avistamento = ?";

        List<Integer> idsTestemunhas = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvistamento);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                idsTestemunhas.add(rs.getInt("ID_Testemunha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idsTestemunhas;
    }

    public void inserirRelacao(int idAvistamento, int idTestemunha) {
        String sql = "INSERT INTO Avistamento_Testemunha (ID_Avistamento, ID_Testemunha) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvistamento);
            stmt.setInt(2, idTestemunha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirRelacao(int idAvistamento, int idTestemunha) {
        String sql = "DELETE FROM Avistamento_Testemunha WHERE ID_Avistamento = ? AND ID_Testemunha = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvistamento);
            stmt.setInt(2, idTestemunha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
