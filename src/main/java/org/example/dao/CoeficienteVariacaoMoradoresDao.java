package org.example.dao;

import org.example.model.CoeficienteVariacaoMoradoresModel;

/**
 * Implementação concreta do GenericDao para a tabela "coeficiente_variacao_moradores".
 *
 * Herda toda a lógica do {@link GenericDao} e fornece
 * o nome da tabela e o construtor do modelo {@link CoeficienteVariacaoMoradoresModel}.
 */
public class CoeficienteVariacaoMoradoresDao extends GenericDao<CoeficienteVariacaoMoradoresModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "coeficiente_variacao_moradores".
     */
    @Override
    public String getTableName() {
        return "coeficiente_variacao_moradores";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     *
     * @return Uma nova instância de {@link CoeficienteVariacaoMoradoresModel}.
     */
    @Override
    public CoeficienteVariacaoMoradoresModel createNewModelInstance() {
        return new CoeficienteVariacaoMoradoresModel();
    }
}