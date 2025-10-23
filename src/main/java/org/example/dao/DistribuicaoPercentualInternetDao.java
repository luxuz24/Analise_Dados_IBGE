package org.example.dao;

import org.example.model.DistribuicaoPercentualInternetModel;

public class DistribuicaoPercentualInternetDao extends GenericDao<DistribuicaoPercentualInternetModel>{


    @Override
    public String getTableName() {
        return "distribuicao_percentual_internet";
    }

    @Override
    public DistribuicaoPercentualInternetModel createNewModelInstance() {
        return new DistribuicaoPercentualInternetModel();
    }
}
