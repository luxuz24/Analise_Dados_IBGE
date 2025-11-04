package org.example.model;

import java.math.BigDecimal;

/**
 * Classe Modelo que representa os dados da tabela "coeficiente_variacao_internet".
 * Ela herda todos os seus atributos e métodos da superclasse {@link DadosIbge}.
 *
 * Esta classe existe para permitir que o {@link org.example.dao.GenericDao}
 * trabalhe de forma tipada (type-safe) com esta tabela específica.
 */
public class CoeficienteVariacaoInternetModel extends DadosIbge {

    /**
     * Construtor padrão.
     */
    public CoeficienteVariacaoInternetModel() {
        super();
    }

    /**
     * Construtor parametrizado completo, repassado para a superclasse.
     */
    public CoeficienteVariacaoInternetModel(Long id, String regioes, Integer ano, BigDecimal total, BigDecimal discada, BigDecimal bandaLarga, BigDecimal somenteBandaLarga, BigDecimal bandaLargaFixa, BigDecimal somenteBandaLargaFixa, BigDecimal bandaLargaMovel, BigDecimal somenteBandaLargaMovel, BigDecimal bandaLargaFixaMovel, BigDecimal somenteBandaLargaFixaMovel) {
        super(id, regioes, ano, total, discada, bandaLarga, somenteBandaLarga, bandaLargaFixa, somenteBandaLargaFixa, bandaLargaMovel, somenteBandaLargaMovel, bandaLargaFixaMovel, somenteBandaLargaFixaMovel);
    }
}