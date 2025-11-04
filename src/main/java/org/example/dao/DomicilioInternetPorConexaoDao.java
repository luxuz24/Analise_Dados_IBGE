package org.example.dao;

import org.example.model.DomiciliosInternetPorConexaoModel;

/**
 * Implementação concreta do GenericDao para a tabela "domicilios_internet_por_conexao".
 *
 * Esta classe herda toda a lógica de CRUD (Create, Read, Update, Delete)
 * do {@link GenericDao} e apenas fornece as informações específicas
 * deste modelo: o nome da tabela e o construtor do modelo.
 */
public class DomicilioInternetPorConexaoDao extends GenericDao<DomiciliosInternetPorConexaoModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "domicilios_internet_por_conexao".
     */
    @Override
    public String getTableName() {
        return "domicilios_internet_por_conexao";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     * Usado pelo GenericDao para popular os objetos a partir do ResultSet.
     *
     * @return Uma nova instância de {@link DomiciliosInternetPorConexaoModel}.
     */
    @Override
    public DomiciliosInternetPorConexaoModel createNewModelInstance() {
        return new DomiciliosInternetPorConexaoModel();
    }
}