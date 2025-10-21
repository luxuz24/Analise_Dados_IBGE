package org.example.dao;

import org.example.model.DadosIbge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao <T extends DadosIbge> {

    public abstract String getTableName();

    public abstract T createFromResultSet(ResultSet rs) throws SQLException;

    public List<T> buscarTodos() {


        String sql = "SELECT * FROM " + getTableName() + " ORDER BY \"Ano\" ASC, \"Cod.\" ASC";
        List<T> resultados = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                T objeto = createFromResultSet(rs);
                resultados.add(objeto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos em " + getTableName() + ":" + e.getMessage());
            throw new RuntimeException("Erro de SQL",e);
        }
        return resultados;
    }
    public List<T> buscarPorAno(int ano) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE \"Ano\" = ? ORDER BY \"Cod.\" ASC";
        List<T> resultados = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    T objeto = createFromResultSet(rs);
                    resultados.add(objeto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por ano em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro de SQL", e);
        }
        return resultados;
    }

    public List<Integer> buscarAnosDistintos() {
        String sql = "SELECT DISTINCT \"Ano \" FROM " + getTableName() + " ORDER BY \"Ano.\" DESC";
        List<Integer> anos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                anos.add(rs.getInt("Ano"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ano em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro SQL", e);
        }
        return anos;
    }

    public List<String> buscarPorRegioesDistintas() {
        String nomeColunaRegioes = "Grandes Regiões e Unidades da Federação";
        String sql = " SELECT DISTINCT " + nomeColunaRegioes + " FROM " + getTableName() + " ORDER BY " + nomeColunaRegioes + " ASC";
        List<String> regioes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                regioes.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por regioes em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro SQL", e);
        }
        return regioes;
    }

    public List<T> buscarPorRegiao(String regiao) {
        String colunaRegiao = "\"Grandes Regiões e Unidades da Federação\"";
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + colunaRegiao + " = ? ORDER BY \"Ano\" ASC";
        List<T> resultados = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,regiao);
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    resultados.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por regiao em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro SQL", e);
        }
        return resultados;
    }

    public T buscarPorRegiaoEAno(String regiao, int ano) {
        String colunaRegioes = "\"Grandes Regiões e Unidades da Federação\"";
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + colunaRegioes + " = ? AND \"Ano\" = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, regiao);
            stmt.setInt(2, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por região e ano em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro de SQL", e);
        }
        return null;
    }

    public T buscarPorId(long id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE \"Cod.\" = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por ID em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro de SQL", e);
        }
        return null;
    }






}






