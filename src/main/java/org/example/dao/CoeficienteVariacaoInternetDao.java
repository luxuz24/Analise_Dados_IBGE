package org.example.dao;

import org.example.model.CoeficienteVariacaoInternetModel;

public class CoeficienteVariacaoInternetDao extends GenericDao<CoeficienteVariacaoInternetModel>{

    @Override
    public String getTableName() {
        return "coeficiente_variacao_internet";
    }

    @Override
    public CoeficienteVariacaoInternetModel createNewModelInstance() {
        return new CoeficienteVariacaoInternetModel();
    }
}
