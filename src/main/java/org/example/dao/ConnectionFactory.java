package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Uma classe utilitária (Factory) responsável por criar e gerenciar
 * conexões com o banco de dados PostgreSQL.
 *
 * Esta classe centraliza as credenciais e a URL de conexão em um único lugar,
 * facilitando a manutenção e a obtenção de novas conexões.
 */
public class ConnectionFactory {

    /**
     * A URL de conexão JDBC para o banco de dados PostgreSQL.
     * Formato: jdbc:postgresql://[host]:[porta]/[nome_do_banco]
     */
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/inclusao_digital";

    /**
     * O nome de usuário para acessar o banco de dados.
     */
    private static final String DATABASE_USER = "postgres";

    /**
     * A senha para acessar o banco de dados.
     */
    private static final String DATABASE_PASSWORD = "7355";


    /**
     * Obtém uma nova conexão ativa com o banco de dados.
     *
     * @return Um objeto {@link Connection} pronto para ser usado.
     * @throws RuntimeException Se uma {@link SQLException} ocorrer durante a tentativa
     * de conexão (encapsulando a exceção original).
     */
    public static Connection getConnection() {
        try {
            // Usa o DriverManager do JDBC para estabelecer a conexão
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            // Captura a exceção checada (SQLException) e a relança como uma
            // exceção não checada (RuntimeException) para simplificar o
            // tratamento de erros nas classes DAO.
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw new RuntimeException("Falha ao obter conexão com o banco de dados.", e);
        }
    }
}