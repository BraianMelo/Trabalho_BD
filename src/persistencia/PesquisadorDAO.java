package persistencia;

import modelo.Pesquisador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesquisadorDAO {

    public void inserir(Pesquisador pesquisador) {
        String sql = "INSERT INTO Pesquisador (ID_Testemunha, Area_Atuacao, Instituicao) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pesquisador.getIdTestemunha());
            stmt.setString(2, pesquisador.getAreaAtuacao());
            stmt.setString(3, pesquisador.getInstituicao());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pesquisador.setIdPesquisador(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Pesquisador pesquisador) {
        String sql = "UPDATE Pesquisador SET Area_Atuacao = ?, Instituicao = ? WHERE ID_Pesquisador = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pesquisador.getAreaAtuacao());
            stmt.setString(2, pesquisador.getInstituicao());
            stmt.setInt(3, pesquisador.getIdPesquisador());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idPesquisador) {
        String sql = "DELETE FROM Pesquisador WHERE ID_Pesquisador = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPesquisador);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pesquisador consultarPorIdTestemunha(int idTestemunha) {
        String sql = "SELECT * FROM Pesquisador WHERE ID_Testemunha = ?";
        Pesquisador pesquisador = null;

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTestemunha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pesquisador = new Pesquisador(
                        rs.getInt("ID_Pesquisador"),
                        rs.getInt("ID_Testemunha"),
                        rs.getString("Area_Atuacao"),
                        rs.getString("Instituicao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pesquisador;
    }

    public List<Pesquisador> listarTodos() {
        String sql = "SELECT * FROM Pesquisador";
        List<Pesquisador> pesquisadores = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pesquisador pesquisador = new Pesquisador(
                        rs.getInt("ID_Pesquisador"),
                        rs.getInt("ID_Testemunha"),
                        rs.getString("Area_Atuacao"),
                        rs.getString("Instituicao")
                );
                pesquisadores.add(pesquisador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pesquisadores;
    }
}