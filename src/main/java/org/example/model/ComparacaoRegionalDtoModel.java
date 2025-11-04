package org.example.model;

import java.math.BigDecimal;

/**
 * Um DTO (Data Transfer Object) que encapsula o resultado da análise de
 * "Comparação Regional" para UMA ÚNICA métrica em UM ÚNICO ano.
 *
 * Cada instância armazena um par (Região, ValorDaMetrica), representando
 * o valor de uma métrica (ex: "Banda Larga") em uma região (ex: "Sudeste").
 * É usado para preencher o Gráfico de Pizza na "Análise Gráfica".
 */
public class ComparacaoRegionalDtoModel {

    private String regiao;
    private BigDecimal valorMetrica;

    // --- Getters e Setters Padrão ---
    // Usados pelo AnaliseController para extrair os dados para o PieChart.Data

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

    /**
     * Construtor parametrizado.
     */
    public ComparacaoRegionalDtoModel(String regiao, BigDecimal valorMetrica) {
        this.regiao = regiao;
        this.valorMetrica = valorMetrica;
    }

    /**
     * Construtor padrão.
     */
    public ComparacaoRegionalDtoModel() {
    }

    /**
     * Representação textual do objeto, útil para depuração (logs).
     */
    @Override
    public String toString() {
        return "ComparacaoRegionalDtoModel{" +
                "regiao='" + regiao + '\'' +
                ", valorMetrica=" + valorMetrica +
                '}';
    }
}