package org.example.model;

import java.math.BigDecimal;

public class ComparacaoRegionalDtoModel {
    private String regiao;
    private BigDecimal valorMetrica;

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public BigDecimal getValorMetrica() {
        return valorMetrica;
    }

    public void setValorMetrica(BigDecimal valorMetrica) {
        this.valorMetrica = valorMetrica;
    }

    public ComparacaoRegionalDtoModel(String regiao, BigDecimal valorMetrica) {
        this.regiao = regiao;
        this.valorMetrica = valorMetrica;
    }

    public ComparacaoRegionalDtoModel() {
    }

    @Override
    public String toString() {
        return "ComparacaoRegionalDtoModel{" +
                "regiao='" + regiao + '\'' +
                ", valorMetrica=" + valorMetrica +
                '}';
    }
}
