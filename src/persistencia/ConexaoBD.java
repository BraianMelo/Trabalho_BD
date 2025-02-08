package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {
    private static String url;
    private static String usuario;
    private static String senha;

    static {
        carregarConfiguracoes();
    }

    private static void carregarConfiguracoes() {
        Properties propriedades = new Properties();

        try (FileInputStream fis = new FileInputStream("database/BD.properties")) {
            propriedades.load(fis);

            url = propriedades.getProperty("db.url");
            usuario = propriedades.getProperty("db.usuario");
            senha = propriedades.getProperty("db.senha");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo BD.properties", e);
        }
    }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}
