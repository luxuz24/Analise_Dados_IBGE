package org.example.dao;

import org.example.model.DadosIbge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DadosIbgeDAO {

    public List<DadosIbge> buscarTodos() {

        String sql = "SELECT * FROM coeficiente_variacao_distribuicao ORDER BY \"Ano\" ASC, \"Cod.\" ASC;";
        List<DadosIbge> listaDados = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DadosIbge dados = new DadosIbge();
                dados.setId(rs.getLong("Cod."));
                dados.setRegioes(rs.getString("Grandes Regiões e Unidades da Federação"));
                dados.setAno(rs.getInt("Ano"));
                dados.setTotal(rs.getBigDecimal("Total"));
                dados.setDiscada(rs.getBigDecimal("Discada"));
                dados.setBandaLarga(rs.getBigDecimal("Banda larga"));
                dados.setSomenteBandaLarga(rs.getBigDecimal("Somente banda larga"));
                dados.setBandaLargaFixa(rs.getBigDecimal("Banda larga fixa"));
                dados.setSomenteBandaLargaFixa(rs.getBigDecimal("Somente banda larga fixa"));
                dados.setBandaLargaMovel(rs.getBigDecimal("Banda larga móvel"));
                dados.setSomenteBandaLargaMovel(rs.getBigDecimal("Somente banda larga móvel"));
                dados.setBandaLargaFixaMovel(rs.getBigDecimal("Banda larga fixa e móvel"));
                dados.setSomenteBandaLargaFixaMovel(rs.getBigDecimal("Somente banda larga fixa e móvel"));


                listaDados.add(dados);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar dados do IBGE: " + e.getMessage());
            throw new RuntimeException("Erro de SQL ao buscar dados.", e);
        }

        return listaDados;
    }
}