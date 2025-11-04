package org.example.model;

import java.math.BigDecimal;

/**
 * Classe Modelo que representa os dados da tabela "distribuicao_percentual_internet".
 * Ela herda todos os seus atributos e métodos da superclasse {@link DadosIbge}.
 *
 * Esta classe existe para permitir que o {@link org.example.dao.GenericDao}
 * trabalhe de forma tipada (type-safe) com esta tabela específica,
 * e o {@link org.example.dao.DistribuicaoPercentualInternetDao} a utiliza
 * para criar novas instâncias.
 */
public class DistribuicaoPercentualInternetModel extends DadosIbge {

    /**
     * Construtor padrão.
     * Chama o construtor da superclasse.
     */
    public DistribuicaoPercentualInternetModel() {
        super();
    }

    /**
     * Construtor parametrizado completo.
     * Repassa todos os parâmetros para o construtor da superclasse {@link DadosIbge}.
     */
    public DistribuicaoPercentualInternetModel(Long id, String regioes, Integer ano, BigDecimal total, BigDecimal discada, BigDecimal bandaLarga, BigDecimal somenteBandaLarga, BigDecimal bandaLargaFixa, BigDecimal somenteBandaLargaFixa, BigDecimal bandaLargaMovel, BigDecimal somenteBandaLargaMovel, BigDecimal bandaLargaFixaMovel, BigDecimal somenteBandaLargaFixaMovel) {
        super(id, regioes, ano, total, discada, bandaLarga, somenteBandaLarga, bandaLargaFixa, somenteBandaLargaFixa, bandaLargaMovel, somenteBandaLargaMovel, bandaLargaFixaMovel, somenteBandaLargaFixaMovel);
    }
}