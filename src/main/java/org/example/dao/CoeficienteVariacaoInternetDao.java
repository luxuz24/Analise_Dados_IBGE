package org.example.dao;

import org.example.model.CoeficienteVariacaoInternetModel;

/**
 * Implementação concreta do GenericDao para a tabela "coeficiente_variacao_internet".
 *
 * Herda toda a lógica do {@link GenericDao} e fornece
 * o nome da tabela e o construtor do modelo {@link CoeficienteVariacaoInternetModel}.
 */
public class CoeficienteVariacaoInternetDao extends GenericDao<CoeficienteVariacaoInternetModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "coeficiente_variacao_internet".
     */
    @Override
    public String getTableName() {
        return "coeficiente_variacao_internet";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     *
     * @return Uma nova instância de {@link CoeficienteVariacaoInternetModel}.
     */
    @Override
    public CoeficienteVariacaoInternetModel createNewModelInstance() {
        return new CoeficienteVariacaoInternetModel();
    }
}