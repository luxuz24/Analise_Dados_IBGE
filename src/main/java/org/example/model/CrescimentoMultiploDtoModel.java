package org.example.model;

/**
 * Um DTO (Data Transfer Object) que encapsula os resultados da análise de
 * "Crescimento Múltiplo" (calculada no AnaliseService).
 *
 * Cada instância desta classe armazena a variação percentual (ano contra ano)
 * de TODAS as métricas de conexão para um único ano.
 * É usado para preencher a tabela na aba "Crescimento Múltiplo".
 */
public class CrescimentoMultiploDtoModel {

    private int ano;
    private double percentualTotal;
    private double percentualDiscada;
    private double percentualBandaLarga;
    private double percentualSomenteBandaLarga;
    private double percentualBandaLargaFixa;
    private double percentualSomenteBandaLargaFixa;
    private double percentualBandaLargaMovel;
    private double percentualSomenteBandaLargaMovel;
    private double percentualBandaLargaFixaMovel;
    private double percentualSomenteBandaLargaFixaMovel;

    // --- Getters e Setters Padrão ---
    // Usados pelo JavaFX (PropertyValueFactory) para ler os dados
    // e preencher as colunas da tabela.

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPercentualTotal() {
        return percentualTotal;
    }

    public void setPercentualTotal(double percentualTotal) {
        this.percentualTotal = percentualTotal;
    }

    public double getPercentualDiscada() {
        return percentualDiscada;
    }

    public void setPercentualDiscada(double percentualDiscada) {
        this.percentualDiscada = percentualDiscada;
    }

    public double getPercentualBandaLarga() {
        return percentualBandaLarga;
    }

    public void setPercentualBandaLarga(double percentualBandaLarga) {
        this.percentualBandaLarga = percentualBandaLarga;
    }

    public double getPercentualSomenteBandaLarga() {
        return percentualSomenteBandaLarga;
    }

    public void setPercentualSomenteBandaLarga(double percentualSomenteBandaLarga) {
        this.percentualSomenteBandaLarga = percentualSomenteBandaLarga;
    }

    public double getPercentualBandaLargaFixa() {
        return percentualBandaLargaFixa;
    }

    public void setPercentualBandaLargaFixa(double percentualBandaLargaFixa) {
        this.percentualBandaLargaFixa = percentualBandaLargaFixa;
    }

    public double getPercentualSomenteBandaLargaFixa() {
        return percentualSomenteBandaLargaFixa;
    }

    public void setPercentualSomenteBandaLargaFixa(double percentualSomenteBandaLargaFixa) {
        this.percentualSomenteBandaLargaFixa = percentualSomenteBandaLargaFixa;
    }

    public double getPercentualBandaLargaMovel() {
        return percentualBandaLargaMovel;
    }

    public void setPercentualBandaLargaMovel(double percentualBandaLargaMovel) {
        this.percentualBandaLargaMovel = percentualBandaLargaMovel;
    }

    public double getPercentualSomenteBandaLargaMovel() {
        return percentualSomenteBandaLargaMovel;
    }

    public void setPercentualSomenteBandaLargaMovel(double percentualSomenteBandaLargaMovel) {
        this.percentualSomenteBandaLargaMovel = percentualSomenteBandaLargaMovel;
    }

    public double getPercentualBandaLargaFixaMovel() {
        return percentualBandaLargaFixaMovel;
    }

    public void setPercentualBandaLargaFixaMovel(double percentualBandaLargaFixaMovel) {
        this.percentualBandaLargaFixaMovel = percentualBandaLargaFixaMovel;
    }

    public double getPercentualSomenteBandaLargaFixaMovel() {
        return percentualSomenteBandaLargaFixaMovel;
    }

    public void setPercentualSomenteBandaLargaFixaMovel(double percentualSomenteBandaLargaFixaMovel) {
        this.percentualSomenteBandaLargaFixaMovel = percentualSomenteBandaLargaFixaMovel;
    }

    /**
     * Construtor padrão.
     */
    public CrescimentoMultiploDtoModel() {
    }

    /**
     * Construtor parametrizado.
     */
    public CrescimentoMultiploDtoModel(int ano, double percentualTotal, double percentualDiscada, double percentualBandaLarga, double percentualSomenteBandaLarga, double percentualBandaLargaFixa, double percentualSomenteBandaLargaFixa, double percentualBandaLargaMovel, double percentualSomenteBandaLargaMovel, double percentualBandaLargaFixaMovel, double percentualSomenteBandaLargaFixaMovel) {
        this.ano = ano;
        this.percentualTotal = percentualTotal;
        this.percentualDiscada = percentualDiscada;
        this.percentualBandaLarga = percentualBandaLarga;
        this.percentualSomenteBandaLarga = percentualSomenteBandaLarga;
        this.percentualBandaLargaFixa = percentualBandaLargaFixa;
        this.percentualSomenteBandaLargaFixa = percentualSomenteBandaLargaFixa;
        this.percentualBandaLargaMovel = percentualBandaLargaMovel;
        this.percentualSomenteBandaLargaMovel = percentualSomenteBandaLargaMovel;
        this.percentualBandaLargaFixaMovel = percentualBandaLargaFixaMovel;
        this.percentualSomenteBandaLargaFixaMovel = percentualSomenteBandaLargaFixaMovel;
    }

    /**
     * Representação textual do objeto, útil para depuração (logs).
     */
    @Override
    public String toString() {
        return "CrescimentoMultiploDtoModel{" +
                "ano=" + ano +
                ", percentualTotal=" + percentualTotal +
                ", percentualDiscada=" + percentualDiscada +
                ", percentualBandaLarga=" + percentualBandaLarga +
                ", percentualSomenteBandaLarga=" + percentualSomenteBandaLarga +
                ", percentualBandaLargaFixa=" + percentualBandaLargaFixa +
                ", percentualSomenteBandaLargaFixa=" + percentualSomenteBandaLargaFixa +
                ", percentualBandaLargaMovel=" + percentualBandaLargaMovel +
                ", percentualSomenteBandaLargaMovel=" + percentualSomenteBandaLargaMovel +
                ", percentualBandaLargaFixaMovel=" + percentualBandaLargaFixaMovel +
                ", percentualSomenteBandaLargaFixaMovel=" + percentualSomenteBandaLargaFixaMovel +
                '}';
    }
}