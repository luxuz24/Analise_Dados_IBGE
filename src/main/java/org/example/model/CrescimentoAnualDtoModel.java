package org.example.model;

public class CrescimentoAnualDtoModel {

    private int ano;
    private double percentualCrescimento;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPercentualCrescimento() {
        return percentualCrescimento;
    }

    public void setPercentualCrescimento(double percentualCrescimento) {
        this.percentualCrescimento = percentualCrescimento;
    }

    public CrescimentoAnualDtoModel(int ano, double percentualCrescimento) {
        this.ano = ano;
        this.percentualCrescimento = percentualCrescimento;
    }

    public CrescimentoAnualDtoModel() {
    }

    @Override
    public String toString() {
        return "CrescimentoAnualDtoModel{" +
                "ano=" + ano +
                ", percentualCrescimento=" + percentualCrescimento +
                '}';
    }
}
