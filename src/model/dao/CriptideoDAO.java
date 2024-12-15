package model.dao;

import model.Criptideo;
import model.enums.Tipo;
import model.enums.StatusCriptideo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriptideoDAO {

    // Inserir um novo criptídeo no banco de dados
    public void inserir(Criptideo criptideo) {
        String sql = "INSERT INTO Criptideo (Nome, Descricao, Tipo, Status_cr) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, criptideo.getNome());
            stmt.setString(2, criptideo.getDescricao());
            stmt.setString(3, criptideo.getTipo().name());
            stmt.setString(4, criptideo.getStatusCr().name());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        criptideo.setIdCriptideo(generatedKeys.getInt(1)); // Obtendo o ID gerado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualizar um criptídeo existente no banco de dados
    public void atualizar(Criptideo criptideo) {
        String sql = "UPDATE Criptideo SET Nome = ?, Descricao = ?, Tipo = ?, Status_cr = ? WHERE ID_Criptideo = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, criptideo.getNome());
            stmt.setString(2, criptideo.getDescricao());
            stmt.setString(3, criptideo.getTipo().name());
            stmt.setString(4, criptideo.getStatusCr().name());
            stmt.setInt(5, criptideo.getIdCriptideo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir um criptídeo do banco de dados
    public void excluir(int idCriptideo) {
        String sql = "DELETE FROM Criptideo WHERE ID_Criptideo = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCriptideo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar um criptídeo por ID
    public Criptideo consultarPorId(int idCriptideo) {
        String sql = "SELECT * FROM Criptideo WHERE ID_Criptideo = ?";
        Criptideo criptideo = null;

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCriptideo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                criptideo = new Criptideo(
                        rs.getInt("ID_Criptideo"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        Tipo.valueOf(rs.getString("Tipo")),
                        StatusCriptideo.valueOf(rs.getString("Status_cr"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return criptideo;
    }

    // Listar todos os criptídeos do banco de dados
    public List<Criptideo> listarTodos() {
        String sql = "SELECT * FROM Criptideo";
        List<Criptideo> criptideos = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Criptideo criptideo = new Criptideo(
                        rs.getInt("ID_Criptideo"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        Tipo.valueOf(rs.getString("Tipo")),
                        StatusCriptideo.valueOf(rs.getString("Status_cr"))
                );
                criptideos.add(criptideo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return criptideos;
    }
}
