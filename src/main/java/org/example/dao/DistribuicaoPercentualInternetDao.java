package org.example.dao;

import org.example.model.DistribuicaoPercentualInternetModel;

/**
 * Implementação concreta do GenericDao para a tabela "distribuicao_percentual_internet".
 *
 * Herda toda a lógica do {@link GenericDao} e fornece
 * o nome da tabela e o construtor do modelo {@link DistribuicaoPercentualInternetModel}.
 */
public class DistribuicaoPercentualInternetDao extends GenericDao<DistribuicaoPercentualInternetModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "distribuicao_percentual_internet".
     */
    @Override
    public String getTableName() {
        return "distribuicao_percentual_internet";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     *
     * @return Uma nova instância de {@link DistribuicaoPercentualInternetModel}.
     */
    @Override
    public DistribuicaoPercentualInternetModel createNewModelInstance() {
        return new DistribuicaoPercentualInternetModel();
    }
}