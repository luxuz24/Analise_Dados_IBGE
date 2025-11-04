package org.example.dao;

import org.example.model.CoeficienteVariacaoDistribuicaoMoradoresModel;

/**
 * Implementação concreta do GenericDao para a tabela "coeficiente_variacao_distribuicao_moradores".
 *
 * Herda toda a lógica do {@link GenericDao} e fornece
 * o nome da tabela e o construtor do modelo {@link CoeficienteVariacaoDistribuicaoMoradoresModel}.
 */
public class CoeficienteVariacaoDistribuicaoMoradoresDao extends GenericDao<CoeficienteVariacaoDistribuicaoMoradoresModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "coeficiente_variacao_distribuicao_moradores".
     */
    @Override
    public String getTableName() {
        return "coeficiente_variacao_distribuicao_moradores";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     *
     * @return Uma nova instância de {@link CoeficienteVariacaoDistribuicaoMoradoresModel}.
     */
    @Override
    public CoeficienteVariacaoDistribuicaoMoradoresModel createNewModelInstance() {
        return new CoeficienteVariacaoDistribuicaoMoradoresModel();
    }
}