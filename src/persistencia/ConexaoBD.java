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
    private static Connection conexao;

    static {
        carregarConfiguracoes();
    }

    private ConexaoBD() {}

    private static void carregarConfiguracoes() {
        Properties propriedades = new Properties();

        try (FileInputStream fis = new FileInputStream("database/BD.properties")) {
            propriedades.load(fis);

            url = propriedades.getProperty("db.url");
            usuario = propriedades.getProperty("db.usuario");
            senha = propriedades.getProperty("db.senha");
            
            if (url == null || usuario == null || senha == null) {
            	throw new IOException("O arquivo BD.properties está imcompleto!");
            
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo BD.properties", e);
        
        }
    }

    public static Connection getConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            try {
                conexao = DriverManager.getConnection(url, usuario, senha);
                
            } catch (SQLException e) {
                throw new SQLException("Erro ao conectar ao banco de dados", e);
          
            }
        }
        
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null; 
                
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            
            }
        }
    }
}
