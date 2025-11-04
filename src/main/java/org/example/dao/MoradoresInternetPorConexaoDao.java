package org.example.dao;

import org.example.model.MoradoresInternetPorConexaoModel;

/**
 * Implementação concreta do GenericDao para a tabela "moradores_internet_por_conexao".
 *
 * Esta classe herda toda a lógica de acesso a dados (CRUD) do {@link GenericDao}
 * e apenas fornece as informações específicas deste modelo:
 * o nome da tabela e o construtor do modelo {@link MoradoresInternetPorConexaoModel}.
 */
public class MoradoresInternetPorConexaoDao extends GenericDao<MoradoresInternetPorConexaoModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "moradores_internet_por_conexao".
     */
    @Override
    public String getTableName() {
        return "moradores_internet_por_conexao";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     * Este método é chamado pelo {@link GenericDao} para popular os objetos
     * ao ler os dados do ResultSet.
     *
     * @return Uma nova instância de {@link MoradoresInternetPorConexaoModel}.
     */
    @Override
    public MoradoresInternetPorConexaoModel createNewModelInstance() {
        return new MoradoresInternetPorConexaoModel();
    }
}