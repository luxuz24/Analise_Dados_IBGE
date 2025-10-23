package org.example.model;

import java.math.BigDecimal;

public class CoeficienteVariacaoDistribuicaoMoradoresModel extends DadosIbge{

    public CoeficienteVariacaoDistribuicaoMoradoresModel() {
    }

    public CoeficienteVariacaoDistribuicaoMoradoresModel(Long id, String regioes, Integer ano, BigDecimal total, BigDecimal discada, BigDecimal bandaLarga, BigDecimal somenteBandaLarga, BigDecimal bandaLargaFixa, BigDecimal somenteBandaLargaFixa, BigDecimal bandaLargaMovel, BigDecimal somenteBandaLargaMovel, BigDecimal bandaLargaFixaMovel, BigDecimal somenteBandaLargaFixaMovel) {
        super(id, regioes, ano, total, discada, bandaLarga, somenteBandaLarga, bandaLargaFixa, somenteBandaLargaFixa, bandaLargaMovel, somenteBandaLargaMovel, bandaLargaFixaMovel, somenteBandaLargaFixaMovel);
    }
}
