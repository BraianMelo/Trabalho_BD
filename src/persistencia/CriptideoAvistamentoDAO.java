package persistencia;

import java.sql.*;
import java.util.List; 
import java.util.ArrayList;   

public class CriptideoAvistamentoDAO {

	public List<Integer> buscarIdsAvistamentosPorCriptideo(int idCriptideo) {
		String sql = "SELECT ID_Avistamento " +
					 "FROM Criptideo_Avistamento " +
					 "WHERE ID_Criptideo = ?";

		List<Integer> idsAvistamentos = new ArrayList<>();

		try (Connection conn = ConexaoBD.getConexao();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idCriptideo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				idsAvistamentos.add(rs.getInt("ID_Avistamento"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idsAvistamentos;
	}

    public void inserirRelacao(int idCriptideo, int idAvistamento) {
        String sql = "INSERT INTO Criptideo_Avistamento (ID_Criptideo, ID_Avistamento) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCriptideo);
            stmt.setInt(2, idAvistamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirRelacao(int idCriptideo, int idAvistamento) {
        String sql = "DELETE FROM Criptideo_Avistamento WHERE ID_Criptideo = ? AND ID_Avistamento = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCriptideo);
            stmt.setInt(2, idAvistamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
