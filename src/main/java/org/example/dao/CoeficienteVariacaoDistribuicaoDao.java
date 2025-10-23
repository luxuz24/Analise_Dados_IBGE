package org.example.dao;
import org.example.model.CoeficienteVariacaoDistribuicaoModel;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoeficienteVariacaoDistribuicaoDao extends GenericDao<CoeficienteVariacaoDistribuicaoModel> {

    @Override
    public String getTableName() {
        return "coeficiente_variacao_distribuicao";
    }

    @Override
    public CoeficienteVariacaoDistribuicaoModel createNewModelInstance() {
        return new CoeficienteVariacaoDistribuicaoModel();
    }


}


