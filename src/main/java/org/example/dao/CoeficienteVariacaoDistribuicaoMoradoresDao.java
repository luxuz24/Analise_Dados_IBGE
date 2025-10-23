package org.example.dao;

import org.example.model.CoeficienteVariacaoDistribuicaoMoradoresModel;

public class CoeficienteVariacaoDistribuicaoMoradoresDao extends GenericDao<CoeficienteVariacaoDistribuicaoMoradoresModel>{

    @Override
    public String getTableName() {
        return "coeficiente_variacao_distribuicao_moradores";
    }

    @Override
    public CoeficienteVariacaoDistribuicaoMoradoresModel createNewModelInstance() {
        return new CoeficienteVariacaoDistribuicaoMoradoresModel();
    }



}
