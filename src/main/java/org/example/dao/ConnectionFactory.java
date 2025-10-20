package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // 1.URL do banco.
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/inclusao_digital";

    // 2.Usuário do PostgreSQL
    private static final String DATABASE_USER = "postgres";

    // 3.Senha do PostgreSQL
    private static final String DATABASE_PASSWORD = "7355";



    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw new RuntimeException("Falha ao obter conexão com o banco de dados.", e);
        }
    }
}