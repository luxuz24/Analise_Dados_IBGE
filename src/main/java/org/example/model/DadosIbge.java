package org.example.model;

import java.math.BigDecimal;
import java.sql.Wrapper;

public class DadosIbge {

    private Long  id;
    private String regioes;
    private Integer ano;
    private BigDecimal total;
    private BigDecimal discada;
    private BigDecimal bandaLarga;
    private BigDecimal somenteBandaLarga;
    private BigDecimal bandaLargaFixa;
    private BigDecimal somenteBandaLargaFixa;
    private BigDecimal bandaLargaMovel;
    private BigDecimal somenteBandaLargaMovel;
    private BigDecimal bandaLargaFixaMovel;
    private BigDecimal somenteBandaLargaFixaMovel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegioes() {
        return regioes;
    }

    public void setRegioes(String regioes) {
        this.regioes = regioes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscada() {
        return discada;
    }

    public void setDiscada(BigDecimal discada) {
        this.discada = discada;
    }

    public BigDecimal getBandaLarga() {
        return bandaLarga;
    }

    public void setBandaLarga(BigDecimal bandaLarga) {
        this.bandaLarga = bandaLarga;
    }

    public BigDecimal getSomenteBandaLarga() {
        return somenteBandaLarga;
    }

    public void setSomenteBandaLarga(BigDecimal somenteBandaLarga) {
        this.somenteBandaLarga = somenteBandaLarga;
    }

    public BigDecimal getBandaLargaFixa() {
        return bandaLargaFixa;
    }

    public void setBandaLargaFixa(BigDecimal bandaLargaFixa) {
        this.bandaLargaFixa = bandaLargaFixa;
    }

    public BigDecimal getSomenteBandaLargaFixa() {
        return somenteBandaLargaFixa;
    }

    public void setSomenteBandaLargaFixa(BigDecimal somenteBandaLargaFixa) {
        this.somenteBandaLargaFixa = somenteBandaLargaFixa;
    }

    public BigDecimal getBandaLargaMovel() {
        return bandaLargaMovel;
    }

    public void setBandaLargaMovel(BigDecimal bandaLargaMovel) {
        this.bandaLargaMovel = bandaLargaMovel;
    }

    public BigDecimal getSomenteBandaLargaMovel() {
        return somenteBandaLargaMovel;
    }

    public void setSomenteBandaLargaMovel(BigDecimal somenteBandaLargaMovel) {
        this.somenteBandaLargaMovel = somenteBandaLargaMovel;
    }

    public BigDecimal getBandaLargaFixaMovel() {
        return bandaLargaFixaMovel;
    }

    public void setBandaLargaFixaMovel(BigDecimal bandaLargaFixaMovel) {
        this.bandaLargaFixaMovel = bandaLargaFixaMovel;
    }

    public BigDecimal getSomenteBandaLargaFixaMovel() {
        return somenteBandaLargaFixaMovel;
    }

    public void setSomenteBandaLargaFixaMovel(BigDecimal somenteBandaLargaFixaMovel) {
        this.somenteBandaLargaFixaMovel = somenteBandaLargaFixaMovel;
    }

    public DadosIbge() {
    }

    public DadosIbge(Long id, String regioes, Integer ano, BigDecimal total, BigDecimal discada, BigDecimal bandaLarga, BigDecimal somenteBandaLarga, BigDecimal bandaLargaFixa, BigDecimal somenteBandaLargaFixa, BigDecimal bandaLargaMovel, BigDecimal somenteBandaLargaMovel, BigDecimal bandaLargaFixaMovel, BigDecimal somenteBandaLargaFixaMovel) {
        this.id = id;
        this.regioes = regioes;
        this.ano = ano;
        this.total = total;
        this.discada = discada;
        this.bandaLarga = bandaLarga;
        this.somenteBandaLarga = somenteBandaLarga;
        this.bandaLargaFixa = bandaLargaFixa;
        this.somenteBandaLargaFixa = somenteBandaLargaFixa;
        this.bandaLargaMovel = bandaLargaMovel;
        this.somenteBandaLargaMovel = somenteBandaLargaMovel;
        this.bandaLargaFixaMovel = bandaLargaFixaMovel;
        this.somenteBandaLargaFixaMovel = somenteBandaLargaFixaMovel;
    }

    @Override
    public String  toString() {
        return "DadosIbge{" +
                "id=" + id +
                ", regioes='" + regioes + '\'' +
                ", ano=" + ano +
                ", total=" + total +
                ", discada=" + discada +
                ", bandaLarga=" + bandaLarga +
                ", somenteBandaLarga=" + somenteBandaLarga +
                ", bandaLargaFixa=" + bandaLargaFixa +
                ", somenteBandaLargaFixa=" + somenteBandaLargaFixa +
                ", bandaLargaMovel=" + bandaLargaMovel +
                ", somenteBandaLargaMovel=" + somenteBandaLargaMovel +
                ", bandaLargaFixaMovel=" + bandaLargaFixaMovel +
                ", somenteBandaLargaFixaMovel=" + somenteBandaLargaFixaMovel +
                '}';
    }
}
