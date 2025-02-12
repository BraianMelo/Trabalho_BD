package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Avistamento;

public class AvistamentoDAO {

    public void inserir(Avistamento avistamento) {
        String sql = "INSERT INTO Avistamento (Local_Av, Pais, Data_Av) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, avistamento.getLocal());
            stmt.setString(2, avistamento.getPais());
            stmt.setDate(3, Date.valueOf(avistamento.getData()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        avistamento.setIdAvistamento(generatedKeys.getInt(1)); // Obtendo o ID gerado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Avistamento avistamento) {
        String sql = "UPDATE Avistamento SET Local_Av = ?, Pais = ?, Data_Av = ? WHERE ID_Avistamento = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, avistamento.getLocal());
            stmt.setString(2, avistamento.getPais());
            stmt.setDate(3, Date.valueOf(avistamento.getData()));
            stmt.setInt(4, avistamento.getIdAvistamento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idAvistamento) {
        String sql = "DELETE FROM Avistamento WHERE ID_Avistamento = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvistamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Avistamento consultarPorId(int idAvistamento) {
        String sql = "SELECT * FROM Avistamento WHERE ID_Avistamento = ?";
        Avistamento avistamento = null;

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvistamento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                avistamento = new Avistamento(
                        rs.getInt("ID_Avistamento"),
                        rs.getString("Local_Av"),
                        rs.getString("Pais"),
                        rs.getDate("Data_Av").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avistamento;
    }

    public List<Avistamento> listarTodos() {
        String sql = "SELECT * FROM Avistamento";
        List<Avistamento> avistamentos = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Avistamento avistamento = new Avistamento(
                        rs.getInt("ID_Avistamento"),
                        rs.getString("Local_Av"),
                        rs.getString("Pais"),
                        rs.getDate("Data_Av").toLocalDate()
                );
                avistamentos.add(avistamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avistamentos;
    }
}
