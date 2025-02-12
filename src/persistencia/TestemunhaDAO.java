package persistencia;

import modelo.Testemunha;
import modelo.enums.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestemunhaDAO {

    public void inserir(Testemunha testemunha) {
        String sql = "INSERT INTO Testemunha (Nome, Sobrenome, Idade, Genero, Email, Telefone) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, testemunha.getNome());
            stmt.setString(2, testemunha.getSobrenome());
            stmt.setInt(3, testemunha.getIdade());
            stmt.setString(4, testemunha.getGenero().toString());
            stmt.setString(5, testemunha.getEmail());
            stmt.setString(6, testemunha.getTelefone());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    testemunha.setIdTestemunha(rs.getInt(1)); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Testemunha testemunha) {
        String sql = "UPDATE Testemunha SET Nome = ?, Sobrenome = ?, Idade = ?, Genero = ?, " +
                     "Email = ?, Telefone = ? WHERE ID_Testemunha = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, testemunha.getNome());
            stmt.setString(2, testemunha.getSobrenome());
            stmt.setInt(3, testemunha.getIdade());
            stmt.setString(4, testemunha.getGenero().toString());
            stmt.setString(5, testemunha.getEmail());
            stmt.setString(6, testemunha.getTelefone());
            stmt.setInt(7, testemunha.getIdTestemunha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idTestemunha) {
        String sql = "DELETE FROM Testemunha WHERE ID_Testemunha = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTestemunha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Testemunha buscarTestemunhaPorId(int idTestemunha) {
        String sql = "SELECT * FROM Testemunha WHERE ID_Testemunha = ?";
        Testemunha testemunha = null;

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTestemunha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                testemunha = new Testemunha(
                    rs.getInt("ID_Testemunha"),
                    rs.getString("Nome"),
                    rs.getString("Sobrenome"),
                    rs.getInt("Idade"),
                    Genero.valueOf(rs.getString("Genero")),  // Convertendo para o enum
                    rs.getString("Email"),
                    rs.getString("Telefone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return testemunha;
    }

    public List<Testemunha> listarTodos() {
        String sql = "SELECT * FROM Testemunha";
        List<Testemunha> testemunhas = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha(
                    rs.getInt("ID_Testemunha"),
                    rs.getString("Nome"),
                    rs.getString("Sobrenome"),
                    rs.getInt("Idade"),
                    Genero.valueOf(rs.getString("Genero")),
                    rs.getString("Email"),
                    rs.getString("Telefone")
                );
                testemunhas.add(testemunha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return testemunhas;
    }
}
