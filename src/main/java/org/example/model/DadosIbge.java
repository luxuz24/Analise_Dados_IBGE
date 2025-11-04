package org.example.model;

import org.example.controller.AnaliseController;
import java.math.BigDecimal;

/**
 * Classe Modelo (POJO - Plain Old Java Object) que representa a estrutura
 * de dados comum a todas as tabelas do IBGE neste projeto.
 *
 * Esta classe serve como a superclasse para todos os modelos de dados específicos
 * (ex: DomiciliosInternetPorConexaoModel) e contém os campos compartilhados
 * que são mapeados diretamente das colunas do banco de dados.
 */
public class DadosIbge {

    // --- ATRIBUTOS (FIELDS) ---
    // Estes atributos correspondem às colunas do banco de dados.

    /** O código de identificação da região (ex: 1 para Norte, 3 para Sudeste) */
    private Long id;
    /** O nome da região (ex: "Brasil", "Sudeste") */
    private String regioes;
    /** O ano da coleta de dados (ex: 2019) */
    private Integer ano;
    /** Valor total (em milhares de unidades ou percentual) */
    private BigDecimal total;
    /** Valor da métrica "Discada" */
    private BigDecimal discada;
    /** Valor da métrica "Banda Larga" (total) */
    private BigDecimal bandaLarga;
    /** Valor da métrica "Somente Banda Larga" */
    private BigDecimal somenteBandaLarga;
    /** Valor da métrica "Banda Larga Fixa" */
    private BigDecimal bandaLargaFixa;
    /** Valor da métrica "Somente Banda Larga Fixa" */
    private BigDecimal somenteBandaLargaFixa;
    /** Valor da métrica "Banda Larga Móvel" */
    private BigDecimal bandaLargaMovel;
    /** Valor da métrica "Somente Banda Larga Móvel" */
    private BigDecimal somenteBandaLargaMovel;
    /** Valor da métrica "Banda Larga Fixa e Móvel" */
    private BigDecimal bandaLargaFixaMovel;
    /** Valor da métrica "Somente Banda Larga Fixa e Móvel" */
    private BigDecimal somenteBandaLargaFixaMovel;

    // --- GETTERS E SETTERS ---
    // Métodos de acesso padrão (JavaBean) para todos os atributos.
    // Usados pelo GenericDao para popular o objeto e pelo JavaFX
    // (via PropertyValueFactory) para ler os dados.

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

    // --- CONSTRUTORES ---

    /**
     * Construtor padrão (vazio).
     * Necessário para que as subclasses (ex: DomiciliosInternetPorConexaoModel)
     * e o GenericDao (via createNewModelInstance) possam instanciar o objeto.
     */
    public DadosIbge() {
    }

    /**
     * Construtor parametrizado.
     * Permite a criação de um objeto de dados completo em uma única linha.
     * (Usado principalmente por subclasses)
     */
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

    // --- MÉTODOS UTILITÁRIOS ---

    /**
     * Sobrescreve o método toString() padrão para fornecer uma representação
     * textual clara do objeto, útil para depuração (debugging) e logs.
     *
     * @return Uma String formatada com todos os atributos e valores do objeto.
     */
    @Override
    public String toString() {
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

    /**
     * Método de acesso genérico (getter) que retorna o valor de um atributo
     * com base em seu nome (String).
     *
     * Este é um método crucial para a funcionalidade dinâmica da UI.
     * Ele permite que o {@link AnaliseController} extraia valores de métricas
     * (ex: para os gráficos) sem precisar saber qual getter específico (ex: getTotal())
     * deve ser chamado.
     *
     * @param attributeName O nome do atributo (ex: "total", "bandaLargaFixa").
     * @return O valor do atributo (como um Object, que pode ser BigDecimal, String ou Integer).
     * @throws IllegalArgumentException Se o nome do atributo for desconhecido.
     */
    public Object get(String attributeName) {
        switch (attributeName) {
            case "total": return total;
            case "discada": return discada;
            case "bandaLarga": return bandaLarga;
            case "somenteBandaLarga": return somenteBandaLarga;
            case "bandaLargaFixa": return bandaLargaFixa;
            case "somenteBandaLargaFixa": return somenteBandaLargaFixa;
            case "bandaLargaMovel": return bandaLargaMovel;
            case "somenteBandaLargaMovel": return somenteBandaLargaMovel;
            case "bandaLargaFixaMovel": return bandaLargaFixaMovel;
            case "somenteBandaLargaFixaMovel": return somenteBandaLargaFixaMovel;
            case "regioes": return regioes;
            case "ano": return ano;
            default:
                throw new IllegalArgumentException("Atributo desconhecido: " + attributeName);
        }
    }
}