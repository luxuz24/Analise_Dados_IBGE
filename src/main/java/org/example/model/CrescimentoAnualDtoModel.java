package org.example.model;

/**
 * Um DTO (Data Transfer Object) que encapsula o resultado da análise de
 * "Crescimento Anual" para UMA ÚNICA métrica.
 *
 * Cada instância desta classe armazena um par de (Ano, ValorPercentual),
 * representando o crescimento de uma métrica em um ano específico.
 * É usado para preencher o Gráfico de Barras na "Análise Gráfica".
 */
public class CrescimentoAnualDtoModel {

    private int ano;
    private double percentualCrescimento;

    // --- Getters e Setters Padrão ---
    // Usados pelo AnaliseController para extrair os dados para o XYChart.Series

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

    /**
     * Construtor parametrizado.
     */
    public CrescimentoAnualDtoModel(int ano, double percentualCrescimento) {
        this.ano = ano;
        this.percentualCrescimento = percentualCrescimento;
    }

    /**
     * Construtor padrão.
     */
    public CrescimentoAnualDtoModel() {
    }

    /**
     * Representação textual do objeto, útil para depuração (logs).
     */
    @Override
    public String toString() {
        return "CrescimentoAnualDtoModel{" +
                "ano=" + ano +
                ", percentualCrescimento=" + percentualCrescimento +
                '}';
    }
}