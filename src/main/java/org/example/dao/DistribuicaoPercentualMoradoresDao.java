package org.example.dao;

import org.example.model.DistribuicaoPercentualMoradoresModel;

public class DistribuicaoPercentualMoradoresDao extends GenericDao<DistribuicaoPercentualMoradoresModel>{


    @Override
    public String getTableName() {
        return "distribuicao_percentual_moradores";
    }

    @Override
    public DistribuicaoPercentualMoradoresModel createNewModelInstance() {
        return new DistribuicaoPercentualMoradoresModel();
    }
}
