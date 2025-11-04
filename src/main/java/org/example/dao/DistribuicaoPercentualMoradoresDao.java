package org.example.dao;

import org.example.model.DistribuicaoPercentualMoradoresModel;

/**
 * Implementação concreta do GenericDao para a tabela "distribuicao_percentual_moradores".
 *
 * Herda toda a lógica do {@link GenericDao} e fornece
 * o nome da tabela e o construtor do modelo {@link DistribuicaoPercentualMoradoresModel}.
 */
public class DistribuicaoPercentualMoradoresDao extends GenericDao<DistribuicaoPercentualMoradoresModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "distribuicao_percentual_moradores".
     */
    @Override
    public String getTableName() {
        return "distribuicao_percentual_moradores";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     *
     * @return Uma nova instância de {@link DistribuicaoPercentualMoradoresModel}.
     */
    @Override
    public DistribuicaoPercentualMoradoresModel createNewModelInstance() {
        return new DistribuicaoPercentualMoradoresModel();
    }
}