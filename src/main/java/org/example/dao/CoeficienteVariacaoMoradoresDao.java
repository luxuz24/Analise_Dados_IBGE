package org.example.dao;

import org.example.model.CoeficienteVariacaoMoradoresModel;

public class CoeficienteVariacaoMoradoresDao extends GenericDao<CoeficienteVariacaoMoradoresModel>{

    @Override
    public String getTableName() {
        return "coeficiente_variacao_moradores";
    }

    @Override
    public CoeficienteVariacaoMoradoresModel createNewModelInstance() {
        return new CoeficienteVariacaoMoradoresModel();
    }
}
