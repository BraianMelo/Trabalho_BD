package persistencia;

import modelo.CriptideoConfirmado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriptideoConfirmadoDAO {

    public void inserir(CriptideoConfirmado criptideo) {
        String sql = "INSERT INTO Criptideo_Confirmado (ID_Criptideo, Nome_Cientifico, Data_Confirmacao, Fonte_Confirmacao, Observacoes) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, criptideo.getIdCriptideo());
            stmt.setString(2, criptideo.getNomeCientifico());
            stmt.setDate(3, Date.valueOf(criptideo.getDataConfirmacao()));
            stmt.setString(4, criptideo.getFonte());
            stmt.setString(5, criptideo.getObservacoes());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        criptideo.setIdConfirmado(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(CriptideoConfirmado criptideo) {
        String sql = "UPDATE Criptideo_Confirmado SET Nome_Cientifico = ?, Data_Confirmacao = ?, Fonte_Confirmacao = ?, Observacoes = ? WHERE ID_Confirmado = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, criptideo.getNomeCientifico());
            stmt.setDate(2, Date.valueOf(criptideo.getDataConfirmacao()));
            stmt.setString(3, criptideo.getFonte());
            stmt.setString(4, criptideo.getObservacoes());
            stmt.setInt(5, criptideo.getIdConfirmado());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idConfirmado) {
        String sql = "DELETE FROM Criptideo_Confirmado WHERE ID_Confirmado = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idConfirmado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CriptideoConfirmado consultarPorIdCriptideo(int idCriptideo) {
        String sql = "SELECT * FROM Criptideo_Confirmado WHERE ID_Criptideo = ?";
        CriptideoConfirmado criptideo = null;
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCriptideo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                criptideo = new CriptideoConfirmado(
                        rs.getInt("ID_Confirmado"),
                        rs.getInt("ID_Criptideo"),
                        rs.getString("Nome_Cientifico"),
                        rs.getDate("Data_Confirmacao").toLocalDate(),
                        rs.getString("Fonte_Confirmacao"),
                        rs.getString("Observacoes")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return criptideo;
    }

    public List<CriptideoConfirmado> listarTodos() {
        String sql = "SELECT * FROM Criptideo_Confirmado";
        List<CriptideoConfirmado> criptideos = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                CriptideoConfirmado criptideo = new CriptideoConfirmado(
                        rs.getInt("ID_Confirmado"),
                        rs.getInt("ID_Criptideo"),
                        rs.getString("Nome_Cientifico"),
                        rs.getDate("Data_Confirmacao").toLocalDate(),
                        rs.getString("Fonte_Confirmacao"),
                        rs.getString("Observacoes")
                );
                criptideos.add(criptideo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return criptideos;
    }
}
