package org.example.dao;

import org.example.model.DadosIbge;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação abstrata do padrão "Generic Data Access Object (DAO)".
 * Esta classe centraliza toda a lógica de acesso ao banco de dados (JDBC)
 * que é comum a todas as tabelas de dados do IBGE.
 *
 * @param <T> O tipo de modelo (Model) que este DAO gerencia,
 * restringido a ser uma subclasse de {@link DadosIbge}.
 */
public abstract class GenericDao<T extends DadosIbge> {

    // --- MÉTODOS ABSTRATOS (A SEREM IMPLEMENTADOS PELAS CLASSES FILHAS) ---

    /**
     * Retorna o nome exato da tabela no banco de dados que a classe filha gerencia.
     * Ex: "domicilios_internet_por_conexao"
     *
     * @return O nome da tabela.
     */
    public abstract String getTableName();

    /**
     * Método fábrica (Factory Method) para criar uma nova instância vazia do modelo (T).
     * É usado internamente pelo {@link #mapResultSetToObject(ResultSet)} para
     * popular um objeto do tipo correto.
     *
     * @return Uma nova instância de T (ex: new DomicilioInternetPorConexaoModel()).
     */
    public abstract T createNewModelInstance();


    // --- MÉTODOS CONCRETOS (LÓGICA COMUM) ---

    /**
     * Mapeia a linha atual de um ResultSet para um objeto de modelo (T).
     * Este método é o "tradutor" entre o banco de dados e os objetos Java.
     *
     * @param rs O ResultSet posicionado na linha a ser mapeada.
     * @return Um objeto T populado com os dados da linha.
     * @throws SQLException Se ocorrer um erro ao ler os dados do ResultSet.
     */
    private T mapResultSetToObject(ResultSet rs) throws SQLException {
        // Usa o método fábrica para criar a instância correta
        T obj = createNewModelInstance();

        // Mapeia cada coluna do banco para um atributo do objeto Java
        obj.setId(rs.getLong("Cod."));
        obj.setRegioes(rs.getString("Grandes Regiões e Unidades da Federação"));
        obj.setAno(rs.getInt("Ano"));
        obj.setTotal(rs.getBigDecimal("Total"));
        obj.setDiscada(rs.getBigDecimal("Discada"));
        obj.setBandaLarga(rs.getBigDecimal("Banda larga"));
        obj.setSomenteBandaLarga(rs.getBigDecimal("Somente banda larga"));
        obj.setBandaLargaFixa(rs.getBigDecimal("Banda larga fixa"));
        obj.setSomenteBandaLargaFixa(rs.getBigDecimal("Somente banda larga fixa"));
        obj.setBandaLargaMovel(rs.getBigDecimal("Banda larga móvel"));
        obj.setSomenteBandaLargaMovel(rs.getBigDecimal("Somente banda larga móvel"));
        obj.setBandaLargaFixaMovel(rs.getBigDecimal("Banda larga fixa e móvel"));
        obj.setSomenteBandaLargaFixaMovel(rs.getBigDecimal("Somente banda larga fixa e móvel"));

        return obj;
    }

    /**
     * Método de busca principal e dinâmico, usado pela interface do usuário.
     * Constrói uma consulta SQL com base nos filtros fornecidos, que podem ser nulos.
     *
     * @param anoFiltro    O ano para filtrar (ex: 2019). Se for nulo ou 0, não é aplicado.
     * @param regiaoFiltro A região para filtrar (ex: "Sudeste"). Se for nulo ou "Todos", não é aplicado.
     * @return Uma lista de objetos T que correspondem aos filtros.
     */
    public List<T> buscarComFiltros(Integer anoFiltro, String regiaoFiltro) {

        // Usa StringBuilder para construir a query SQL dinamicamente
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
        sqlBuilder.append(getTableName());
        // "WHERE 1=1" é um truque comum para facilitar a adição de cláusulas AND
        sqlBuilder.append(" WHERE 1=1");

        // Lista para armazenar os parâmetros do PreparedStatement (para evitar SQL Injection)
        List<Object> parametros = new ArrayList<>();

        // Adiciona o filtro de Ano se ele for válido
        if (anoFiltro != null && anoFiltro > 0) {
            sqlBuilder.append(" AND \"Ano\" = ?");
            parametros.add(anoFiltro);
        }

        // Adiciona o filtro de Região se for válido
        String colunaRegioes = "\"Grandes Regiões e Unidades da Federação\"";
        if (regiaoFiltro != null && !regiaoFiltro.isEmpty() && !regiaoFiltro.equalsIgnoreCase("Todos")) {
            sqlBuilder.append(" AND ").append(colunaRegioes).append(" = ?");
            parametros.add(regiaoFiltro);
        }

        // Define a ordem padrão dos resultados
        sqlBuilder.append(" ORDER BY \"Ano\" ASC, \"Cod.\" ASC");

        String sqlFinal = sqlBuilder.toString();
        List<T> resultados = new ArrayList<>();

        // Usa o bloco try-with-resources para garantir que a conexão e o statement
        // sejam fechados automaticamente, mesmo se ocorrer um erro.
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFinal)) {

            // Seta dinamicamente os parâmetros na query
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i)); // setObject funciona para Integer e String
            }

            // Executa a consulta e mapeia os resultados
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(mapResultSetToObject(rs));
                }
            }
        } catch (SQLException e) {
            System.err.printf("Erro ao buscar com filtros (Ano: %s, Região: %s) em %s: %s\n",
                    anoFiltro, regiaoFiltro, getTableName(), e.getMessage());
            throw new RuntimeException("Erro de SQL ao buscar com filtros", e);
        }
        return resultados;
    }


    /**
     * Busca todos os registros da tabela.
     *
     * @return Uma lista com todos os objetos T da tabela.
     */
    public List<T> buscarTodos() {
        String sql = "SELECT * FROM " + getTableName() + " ORDER BY \"Ano\" ASC, \"Cod.\" ASC";
        List<T> resultados = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T objeto = mapResultSetToObject(rs);
                resultados.add(objeto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar  todos em " + getTableName() + ":" + e.getMessage());
            throw new RuntimeException("Erro de Sql", e);
        }
        return resultados;
    }

    /**
     * Busca todos os registros de um ano específico.
     *
     * @param ano O ano para filtrar.
     * @return Uma lista de objetos T daquele ano.
     */
    public List<T> buscarPorAno(int ano) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE \"Ano\" = ? ORDER BY \"Cod.\" ASC";
        List<T> resultados = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    T objeto = mapResultSetToObject(rs);
                    resultados.add(objeto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por ano em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro de SQL", e);
        }
        return resultados;
    }

    /**
     * Busca a lista de anos distintos (únicos) presentes na tabela.
     * Usado para popular os ComboBoxes de filtro.
     *
     * @return Uma lista de Integers (anos), ordenada do mais recente para o mais antigo.
     */
    public List<Integer> buscarAnosDistintos() {
        String sql = "SELECT DISTINCT \"Ano\" FROM " + getTableName() + " ORDER BY \"Ano\" DESC";
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

    /**
     * Busca a lista de regiões distintas (únicas) presentes na tabela.
     * Usado para popular os ComboBoxes de filtro.
     *
     * @return Uma lista de Strings (regiões), ordenada alfabeticamente.
     */
    public List<String> buscarPorRegioesDistintas() {
        String nomeColunaRegioes = "\"Grandes Regiões e Unidades da Federação\"";
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

    /**
     * Busca todos os registros (de todos os anos) para uma região específica.
     *
     * @param regiao O nome da região a ser filtrada (ex: "Brasil").
     * @return Uma lista de objetos T daquela região.
     */
    public List<T> buscarPorRegiao(String regiao) {
        String colunaRegiao = "\"Grandes Regiões e Unidades da Federação\"";
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + colunaRegiao + " = ? ORDER BY \"Ano\" ASC";
        List<T> resultados = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, regiao);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(mapResultSetToObject(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por regiao em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro SQL", e);
        }
        return resultados;
    }

    /**
     * Busca um único registro com base na região e no ano.
     *
     * @param regiao O nome da região.
     * @param ano    O ano.
     * @return O objeto T correspondente, ou null se não for encontrado.
     */
    public T buscarPorRegiaoEAno(String regiao, int ano) {
        String colunaRegioes = "\"Grandes Regiões e Unidades da Federação\"";
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + colunaRegioes + " = ? AND \"Ano\" = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, regiao);
            stmt.setInt(2, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToObject(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por região e ano em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro de SQL", e);
        }
        return null; // Retorna nulo se nenhum registro for encontrado
    }

    /**
     * Busca um único registro com base em seu ID (Cod.).
     *
     * @param id O código (PK) do registro.
     * @return O objeto T correspondente, ou null se não for encontrado.
     */
    public T buscarPorId(long id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE \"Cod.\" = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToObject(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar por ID em " + getTableName() + ": " + e.getMessage());
            throw new RuntimeException("Erro de SQL", e);
        }
        return null; // Retorna nulo se nenhum registro for encontrado
    }
}