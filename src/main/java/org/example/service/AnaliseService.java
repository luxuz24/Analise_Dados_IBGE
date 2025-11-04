package org.example.service;

import org.example.dao.*;
import org.example.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Camada de Serviço (Business Logic Layer) da aplicação.
 *
 * Esta classe atua como o "cérebro" da aplicação, coordenando a lógica de
 * negócios e o acesso aos dados. Ela é responsável por:
 * 1. Mapear nomes amigáveis (usados na UI) para os DAOs e atributos de modelo corretos.
 * 2. Fornecer dados para popular os filtros da UI (ComboBoxes, ListViews).
 * 3. Executar as lógicas de análise (cálculos de crescimento, comparações)
 * solicitadas pelo {@link org.example.controller.AnaliseController}.
 */
public class AnaliseService {

    // --- REGISTRO DE SERVIÇOS (DAOs) E MÉTRICAS ---

    /**
     * Um mapa que atua como um "registro" de DAOs (Service Locator pattern).
     * Associa um nome amigável (ex: "Domicílios por Conexão") a uma
     * instância concreta do GenericDao correspondente.
     */
    private final Map<String, GenericDao<? extends DadosIbge>> daosMap;
    /**
     * Um mapa que "traduz" o nome amigável de uma métrica (ex: "B. Larga Fixa")
     * para o nome real do atributo na classe de modelo (ex: "bandaLargaFixa").
     * Isso é crucial para a seleção dinâmica de colunas e métricas na UI.
     */
    private final Map<String, String> metricasMap;

    /**
     * Construtor da classe de serviço.
     * Inicializa os mapas `daosMap` e `metricasMap`, registrando
     * todas as fontes de dados e métricas disponíveis na aplicação.
     */
    public AnaliseService() {

        // Inicializa o mapa de DAOs
        daosMap = new LinkedHashMap<>();
        daosMap.put("Domicílios por Conexão", new DomicilioInternetPorConexaoDao());
        daosMap.put("Moradores por Conexão", new MoradoresInternetPorConexaoDao());
        daosMap.put("Distribuição % Domicílios", new DistribuicaoPercentualInternetDao());
        daosMap.put("Distribuição % Moradores", new DistribuicaoPercentualMoradoresDao());
        daosMap.put("Coef. Variação Domicílios", new CoeficienteVariacaoInternetDao());
        daosMap.put("Coef. Variação Moradores", new CoeficienteVariacaoMoradoresDao());
        daosMap.put("Coef. Variação % Domicílios", new CoeficienteVariacaoDistribuicaoDao());
        daosMap.put("Coef. Variação % Moradores", new CoeficienteVariacaoDistribuicaoMoradoresDao());

        // Inicializa o mapa de Métricas
        metricasMap = new LinkedHashMap<>();
        metricasMap.put("Total", "total");
        metricasMap.put("Discada", "discada");
        metricasMap.put("Banda Larga", "bandaLarga");
        metricasMap.put("Somente B. Larga", "somenteBandaLarga");
        metricasMap.put("B. Larga Fixa", "bandaLargaFixa");
        metricasMap.put("Somente B. Larga Fixa", "somenteBandaLargaFixa");
        metricasMap.put("B. Larga Móvel", "bandaLargaMovel");
        metricasMap.put("Somente B. Larga Móvel", "somenteBandaLargaMovel");
        metricasMap.put("B. Larga Fixa e Móvel", "bandaLargaFixaMovel");
        metricasMap.put("Somente B. Larga Fixa e Móvel", "somenteBandaLargaFixaMovel");
    }

    // --- MÉTODOS DE SUPORTE PARA A UI (FILTROS) ---

    /**
     * Retorna a lista de nomes amigáveis das tabelas.
     * Usado para popular o ComboBox de seleção de Tabela/Fonte de Dados.
     * @return Lista de Strings com os nomes das tabelas.
     */
    public List<String> getTabelasNomes() {
        return new ArrayList<>(daosMap.keySet());
    }

    /**
     * Retorna a lista de regiões disponíveis no banco de dados.
     * Adiciona "Brasil" e "Todos" como opções padrão no topo.
     * @return Lista de Strings com as regiões.
     */
    public List<String> getRegioesDisponiveis() {
        // Busca as regiões distintas usando o primeiro DAO da lista
        List<String> regioes = daosMap.values().iterator().next().buscarPorRegioesDistintas();

        // Garante que "Brasil" esteja no topo, se existir
        regioes.removeIf(r -> r.equalsIgnoreCase("Brasil"));
        regioes.add(0, "Brasil");
        regioes.add(1, "Todos");
        return regioes;
    }

    /**
     * Retorna a lista de anos distintos disponíveis no banco.
     * @return Lista de Integers (anos), ordenada do mais recente ao mais antigo.
     */
    public List<Integer> getAnosDisponiveis() {
        // Busca os anos distintos usando o primeiro DAO da lista
        List<Integer> anos = daosMap.values().iterator().next().buscarAnosDistintos();
        return anos;
    }

    /**
     * Retorna a lista de nomes amigáveis das métricas (colunas).
     * Usado para popular a ListView de CheckBoxes.
     * @return Lista de Strings com os nomes das métricas (ex: "Banda Larga Fixa").
     */
    public List<String> getNomesMetricas() {
        return new ArrayList<>(metricasMap.keySet());
    }

    /**
     * "Traduz" um nome amigável de métrica (da UI) para o nome do atributo Java.
     * @param nomeAmigavel O nome da UI (ex: "B. Larga Fixa").
     * @return O nome do atributo (ex: "bandaLargaFixa").
     */
    public String getAtributoMetrica(String nomeAmigavel) {
        return metricasMap.get(nomeAmigavel);
    }

    /**
     * Retorna uma instância do DAO com base no nome amigável da tabela.
     * Usado pelas abas de análise para saber qual DAO consultar.
     * @param nomeAmigavel O nome da tabela (ex: "Domicílios por Conexão").
     * @return A instância do GenericDao correspondente.
     * @throws IllegalArgumentException Se o nome da tabela não for encontrado no mapa.
     */
    public GenericDao<? extends DadosIbge> getDaoPeloNome(String nomeAmigavel) {
        GenericDao<? extends DadosIbge> dao = daosMap.get(nomeAmigavel);
        if (dao == null) {
            throw new IllegalArgumentException("Nome da tabela desconhecido: " + nomeAmigavel);
        }
        return dao;
    }


    // --- MÉTODOS DE LÓGICA DE NEGÓCIO E ANÁLISE ---

    /**
     * Busca dados brutos com base nos filtros da "Aba 1: Dados Brutos".
     *
     * @param nomeTabela O nome amigável da tabela a ser consultada.
     * @param regiao     A região a ser filtrada (ou "Todos" / null para nenhuma).
     * @param ano        O ano a ser filtrado (ou null para nenhum).
     * @return Uma lista de objetos {@link DadosIbge} filtrados.
     */
    public List<? extends DadosIbge> buscarDadosFiltrados(String nomeTabela, String regiao, Integer ano) {
        // Obtém o DAO correto usando o nome amigável
        GenericDao<? extends DadosIbge> daoSelecionado = getDaoPeloNome(nomeTabela);

        // Trata a opção "Todos" para o filtro de região
        String regiaoFiltro = (regiao != null && regiao.equalsIgnoreCase("Todos")) ? null : regiao;

        // Chama o método de busca dinâmico do GenericDao
        return daoSelecionado.buscarComFiltros(ano, regiaoFiltro);
    }

    /**
     * Calcula o crescimento percentual (ano a ano) para UMA ÚNICA métrica.
     * Usado para popular o Gráfico de Barras na "Aba 2: Análise Gráfica".
     *
     * @param regiao          A região para a qual calcular o crescimento (ex: "Brasil").
     * @param extratorMetrica Uma função Lambda (ex: DadosIbge::getTotal) que sabe
     * como extrair o valor da métrica do objeto DadosIbge.
     * @param daoEspecifico   O DAO da tabela a ser consultada.
     * @return Uma lista de {@link CrescimentoAnualDtoModel} contendo os pares (Ano, % Crescimento).
     */
    public List<CrescimentoAnualDtoModel> calcularCrescimentoAnualMetrica(
            String regiao,
            Function<DadosIbge, BigDecimal> extratorMetrica,
            GenericDao<? extends DadosIbge> daoEspecifico
    ) {
        // 1. Busca a série histórica completa para a região
        List<? extends DadosIbge> serieHistorica = daoEspecifico.buscarPorRegiao(regiao);
        List<CrescimentoAnualDtoModel> resultados = new ArrayList<>();

        // 2. Valida se há dados suficientes para comparar (precisa de pelo menos 2 anos)
        if (serieHistorica == null || serieHistorica.size() < 2) {
            System.err.println("Dados insuficientes (" + (serieHistorica == null ? 0 : serieHistorica.size()) + ") para calcular crescimento para a região: " + regiao + " na tabela: " + daoEspecifico.getTableName());
            return resultados;
        }

        // 3. Itera a partir do *segundo* item (i=1) para comparar com o anterior (i=0)
        for (int i = 1; i < serieHistorica.size(); i++) {
            DadosIbge dadosAtuais = serieHistorica.get(i);
            DadosIbge dadosAnteriores = serieHistorica.get(i - 1);

            // 4. Extrai os valores usando a função Lambda
            BigDecimal valorAtual = extratorMetrica.apply(dadosAtuais);
            BigDecimal valorAnterior = extratorMetrica.apply(dadosAnteriores);

            // 5. Calcula o percentual de forma segura
            double percentual = calcularPercentual(valorAtual, valorAnterior);

            // 6. Adiciona o resultado ao DTO
            resultados.add(new CrescimentoAnualDtoModel(dadosAtuais.getAno(), percentual));
        }
        return resultados;
    }

    /**
     * Calcula o crescimento percentual (ano a ano) para TODAS as métricas simultaneamente.
     * Usado para popular a Tabela na "Aba 3: Crescimento Múltiplo".
     *
     * @param regiao        A região para a qual calcular o crescimento.
     * @param daoEspecifico O DAO da tabela a ser consultada.
     * @return Uma lista de {@link CrescimentoMultiploDtoModel}, onde cada objeto representa
     * um ano e contém o % de crescimento de todas as 10 métricas.
     */
    public List<CrescimentoMultiploDtoModel> calcularCrescimentoAnualMultiplo(
            String regiao,
            GenericDao<? extends DadosIbge> daoEspecifico
    ) {
        // 1. Busca a série histórica
        List<? extends DadosIbge> serieHistorica = daoEspecifico.buscarPorRegiao(regiao);
        List<CrescimentoMultiploDtoModel> resultados = new ArrayList<>();

        // 2. Valida se há dados suficientes
        if (serieHistorica == null || serieHistorica.size() < 2) {
            System.err.println("Dados insuficientes (" + (serieHistorica == null ? 0 : serieHistorica.size()) + ") para calcular crescimento múltiplo para a região: " + regiao + " na tabela: " + daoEspecifico.getTableName());
            return resultados;
        }

        // 3. Itera a partir do segundo item
        for (int i = 1; i < serieHistorica.size(); i++) {
            DadosIbge dadosAtuais = serieHistorica.get(i);
            DadosIbge dadosAnteriores = serieHistorica.get(i - 1);

            Integer anoAtualObj = dadosAtuais.getAno();
            int anoAtualPrimitivo = (anoAtualObj != null) ? anoAtualObj.intValue() : 0;

            // 4. Cria o DTO para o ano atual
            CrescimentoMultiploDtoModel dtoAnoAtual = new CrescimentoMultiploDtoModel();
            dtoAnoAtual.setAno(anoAtualPrimitivo);

            // 5. Calcula e seta o percentual para CADA métrica
            dtoAnoAtual.setPercentualTotal(calcularPercentual(dadosAtuais.getTotal(), dadosAnteriores.getTotal()));
            dtoAnoAtual.setPercentualDiscada(calcularPercentual(dadosAtuais.getDiscada(), dadosAnteriores.getDiscada()));
            dtoAnoAtual.setPercentualBandaLarga(calcularPercentual(dadosAtuais.getBandaLarga(), dadosAnteriores.getBandaLarga()));
            dtoAnoAtual.setPercentualSomenteBandaLarga(calcularPercentual(dadosAtuais.getSomenteBandaLarga(), dadosAnteriores.getSomenteBandaLarga()));
            dtoAnoAtual.setPercentualBandaLargaFixa(calcularPercentual(dadosAtuais.getBandaLargaFixa(), dadosAnteriores.getBandaLargaFixa()));
            dtoAnoAtual.setPercentualSomenteBandaLargaFixa(calcularPercentual(dadosAtuais.getSomenteBandaLargaFixa(), dadosAnteriores.getSomenteBandaLargaFixa()));
            dtoAnoAtual.setPercentualBandaLargaMovel(calcularPercentual(dadosAtuais.getBandaLargaMovel(), dadosAnteriores.getBandaLargaMovel()));
            dtoAnoAtual.setPercentualSomenteBandaLargaMovel(calcularPercentual(dadosAtuais.getSomenteBandaLargaMovel(), dadosAnteriores.getSomenteBandaLargaMovel()));
            dtoAnoAtual.setPercentualBandaLargaFixaMovel(calcularPercentual(dadosAtuais.getBandaLargaFixaMovel(), dadosAnteriores.getBandaLargaFixaMovel()));
            dtoAnoAtual.setPercentualSomenteBandaLargaFixaMovel(calcularPercentual(dadosAtuais.getSomenteBandaLargaFixaMovel(), dadosAnteriores.getSomenteBandaLargaFixaMovel()));

            resultados.add(dtoAnoAtual);
        }
        return resultados;
    }


    /**
     * Busca dados detalhados para uma região e ano específicos.
     * (Método auxiliar, usado pelo TesteDao)
     *
     * @param regiao        A região a ser buscada.
     * @param ano           O ano a ser buscado.
     * @param daoEspecifico O DAO da tabela a ser consultada.
     * @return Um único objeto {@link DadosIbge} ou null se não encontrado.
     */
    public DadosIbge obterDadosDetalhadosConexao(String regiao, int ano, GenericDao<? extends DadosIbge> daoEspecifico) {
        return daoEspecifico.buscarPorRegiaoEAno(regiao, ano);
    }


    /**
     * Compara o valor de UMA métrica entre todas as regiões em UM ano específico.
     * Usado para popular o Gráfico de Pizza na "Aba 2: Análise Gráfica".
     *
     * @param ano             O ano para o qual comparar (ex: 2022).
     * @param extratorMetrica A função Lambda que extrai o valor da métrica.
     * @param daoEspecifico   O DAO da tabela a ser consultada.
     * @param ordemDescendente true para ordenar do maior para o menor, false caso contrário.
     * @return Uma lista de {@link ComparacaoRegionalDtoModel} contendo os pares (Região, Valor).
     */
    public List<ComparacaoRegionalDtoModel> compararMetricaEntreRegioes(
            int ano,
            Function<DadosIbge, BigDecimal> extratorMetrica,
            GenericDao<? extends DadosIbge> daoEspecifico,
            boolean ordemDescendente
    ) {

        // 1. Busca todos os dados daquele ano
        List<? extends DadosIbge> dadosDoAno = daoEspecifico.buscarPorAno(ano);

        // 2. Transforma os dados brutos (DadosIbge) em DTOs (ComparacaoRegionalDtoModel)
        List<ComparacaoRegionalDtoModel> comparacao = dadosDoAno.stream()
                // Filtra para remover a linha "Brasil", pois queremos comparar apenas as regiões
                .filter(dado -> dado.getRegioes() != null && !dado.getRegioes().equalsIgnoreCase("Brasil"))
                // Mapeia para o DTO, extraindo a métrica
                .map(dado -> new ComparacaoRegionalDtoModel(
                        dado.getRegioes(),
                        extratorMetrica.apply(dado)
                ))
                .collect(Collectors.toList());

        // 3. Ordena os resultados
        Comparator<ComparacaoRegionalDtoModel> comparador = Comparator.comparing(
                ComparacaoRegionalDtoModel::getValorMetrica,
                Comparator.nullsLast(BigDecimal::compareTo) // Trata valores nulos
        );

        if (ordemDescendente) {
            comparador = comparador.reversed();
        }

        comparacao.sort(comparador);

        return comparacao;
    }


    /**
     * Método auxiliar privado para calcular o crescimento percentual de forma segura.
     * Trata casos de valores nulos e divisão por zero.
     *
     * @param valorAtual    O valor do ano atual (ex: 120).
     * @param valorAnterior O valor do ano anterior (ex: 100).
     * @return O crescimento percentual (ex: 20.0).
     */
    private double calcularPercentual(BigDecimal valorAtual, BigDecimal valorAnterior) {
        double percentual = 0.0; // Valor padrão

        // Caso normal: (Atual - Anterior) / Anterior * 100
        // Ex: (120 - 100) / 100 * 100 = 20.0%
        if (valorAtual != null && valorAnterior != null && valorAnterior.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal diferenca = valorAtual.subtract(valorAnterior);
            // Arredonda para 4 casas decimais durante a divisão para precisão
            BigDecimal crescimento = diferenca.divide(valorAnterior, 4, RoundingMode.HALF_UP);
            percentual = crescimento.doubleValue() * 100;
        }
        // Caso de crescimento "infinito": 0 -> X (onde X > 0)
        // Ex: (100 - 0) / 0 -> Tratado como 100% de crescimento
        else if (valorAtual != null && valorAtual.compareTo(BigDecimal.ZERO) != 0 && (valorAnterior == null || valorAnterior.compareTo(BigDecimal.ZERO) == 0)) {
            percentual = 100.0;
        }
        // Caso de queda total: X -> 0 (onde X > 0)
        // Ex: (0 - 100) / 100 * 100 = -100.0%
        else if (valorAnterior != null && valorAnterior.compareTo(BigDecimal.ZERO) != 0 && (valorAtual == null || valorAtual.compareTo(BigDecimal.ZERO) == 0)) {
            percentual = -100.0;
        }
        // Caso 0 -> 0 ou null -> null: permanece 0.0%

        return percentual;
    }
}