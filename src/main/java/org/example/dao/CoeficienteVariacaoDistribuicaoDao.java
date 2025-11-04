package org.example.dao;
import org.example.model.CoeficienteVariacaoDistribuicaoModel;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementação concreta do GenericDao para a tabela "coeficiente_variacao_distribuicao".
 *
 * Herda toda a lógica do {@link GenericDao} e fornece
 * o nome da tabela e o construtor do modelo {@link CoeficienteVariacaoDistribuicaoModel}.
 */
public class CoeficienteVariacaoDistribuicaoDao extends GenericDao<CoeficienteVariacaoDistribuicaoModel> {

    /**
     * Retorna o nome exato da tabela no banco de dados.
     *
     * @return A string "coeficiente_variacao_distribuicao".
     */
    @Override
    public String getTableName() {
        return "coeficiente_variacao_distribuicao";
    }

    /**
     * Cria e retorna uma nova instância vazia do modelo correspondente.
     *
     * @return Uma nova instância de {@link CoeficienteVariacaoDistribuicaoModel}.
     */
    @Override
    public CoeficienteVariacaoDistribuicaoModel createNewModelInstance() {
        return new CoeficienteVariacaoDistribuicaoModel();
    }
}