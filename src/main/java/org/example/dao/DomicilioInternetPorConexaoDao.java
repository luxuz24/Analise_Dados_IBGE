package org.example.dao;

import org.example.model.DomiciliosInternetPorConexaoModel;

public class DomicilioInternetPorConexaoDao extends GenericDao<DomiciliosInternetPorConexaoModel>{


    @Override
    public String getTableName() {
        return "domicilios_internet_por_conexao";
    }

    @Override
    public DomiciliosInternetPorConexaoModel createNewModelInstance() {
        return new DomiciliosInternetPorConexaoModel();
    }
}
