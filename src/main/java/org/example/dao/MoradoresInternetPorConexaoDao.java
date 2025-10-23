package org.example.dao;

import org.example.model.MoradoresInternetPorConexaoModel;

public class MoradoresInternetPorConexaoDao extends GenericDao<MoradoresInternetPorConexaoModel>{

    @Override
    public String getTableName() {
        return "moradores_internet_por_conexao";
    }

    @Override
    public MoradoresInternetPorConexaoModel createNewModelInstance() {
        return new MoradoresInternetPorConexaoModel();
    }
}
