package org.example;

// Importe o Serviço e os DAOs/Modelos necessários
import org.example.dao.*; // Importa todos os DAOs para referência
import org.example.model.*; // Importa a classe base e os DTOs
import org.example.service.AnaliseService; // Importa o Serviço!

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Classe de teste de console (driver) para validar os métodos da {@link AnaliseService}.
 *
 * Esta classe usa o {@code System.out.println} para verificar se os cálculos e
 * a lógica de negócio implementados no Service (que por sua vez usam os DAOs)
 * estão retornando os resultados esperados.
 *
 * (Nota: Em um ambiente de produção, isso seria substituído por testes de unidade
 * com JUnit/AssertJ).
 */
public class TesteDao {

    /**
     * Ponto de entrada para a execução dos testes de console.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {

        // 1. Instancia o "cérebro" da aplicação
        AnaliseService service = new AnaliseService();

        // 2. Instancia DAOs específicos para usar nos testes
        // (Isso valida que o Service consegue trabalhar com qualquer DAO genérico)
        DistribuicaoPercentualMoradoresDao daoDistPercentual = new DistribuicaoPercentualMoradoresDao();
        CoeficienteVariacaoInternetDao daoCoefInternet = new CoeficienteVariacaoInternetDao(); // Outro DAO para variar

        System.out.println("=========================================================");
        System.out.println("INICIANDO TESTES COMPLETOS PARA: AnaliseService");
        System.out.println("=========================================================");

        // --- Teste 1: calcularCrescimentoAnualMetrica ---
        // Valida o cálculo de crescimento de uma única métrica ao longo do tempo.
        System.out.println("\n--- Teste 1.1: Crescimento 'Banda Larga Fixa' (Brasil) - Tabela: " + daoDistPercentual.getTableName() + " ---");
        try {
            // Define o "extrator" da métrica usando Method Reference
            Function<DadosIbge, BigDecimal> extratorBLFixa = DadosIbge::getBandaLargaFixa;
            List<CrescimentoAnualDtoModel> crescimento = service.calcularCrescimentoAnualMetrica(
                    "Brasil", extratorBLFixa, daoDistPercentual
            );
            imprimirLista(crescimento, "  ");
        } catch (Exception e) { imprimirErro("calcularCrescimentoAnualMetrica (BL Fixa)", e); }

        System.out.println("\n--- Teste 1.2: Crescimento 'Discada' (Região Norte) - Tabela: " + daoCoefInternet.getTableName() + " ---");
        try {
            Function<DadosIbge, BigDecimal> extratorDiscada = DadosIbge::getDiscada;
            List<CrescimentoAnualDtoModel> crescimentoDiscada = service.calcularCrescimentoAnualMetrica(
                    "Região Norte", extratorDiscada, daoCoefInternet
            );
            imprimirLista(crescimentoDiscada, "  ");
        } catch (Exception e) { imprimirErro("calcularCrescimentoAnualMetrica (Discada)", e); }


        // --- Teste 2: obterDadosDetalhadosConexao ---
        // Valida a busca de um único registro por região e ano.
        System.out.println("\n--- Teste 2: obterDadosDetalhadosConexao('Região Sul', 2023) - Tabela: " + daoDistPercentual.getTableName() + " ---");
        try {
            DadosIbge dadosDetalhe = service.obterDadosDetalhadosConexao("Região Sul", 2023, daoDistPercentual);
            if (dadosDetalhe == null) {
                System.out.println("  Resultado: Nenhum dado detalhado encontrado.");
            } else {
                System.out.println("  Resultado: Dados detalhados encontrados:\n  " + dadosDetalhe);
            }
        } catch (Exception e) { imprimirErro("obterDadosDetalhadosConexao", e); }


        // --- Teste 3: compararMetricaEntreRegioes ---
        // Valida a comparação de uma métrica entre regiões em um ano (ranking).
        System.out.println("\n--- Teste 3.1: Ranking 'Somente Banda Larga Móvel' (2024, DESC) - Tabela: " + daoDistPercentual.getTableName() + " ---");
        try {
            Function<DadosIbge, BigDecimal> extratorSomenteBLMovel = DadosIbge::getSomenteBandaLargaMovel;
            List<ComparacaoRegionalDtoModel> ranking = service.compararMetricaEntreRegioes(
                    2024, extratorSomenteBLMovel, daoDistPercentual, true
            );
            imprimirLista(ranking, "  ");
        } catch (Exception e) { imprimirErro("compararMetricaEntreRegioes (Somente BL Movel)", e); }

        System.out.println("\n--- Teste 3.2: Ranking 'Discada' (2019, ASC) - Tabela: " + daoCoefInternet.getTableName() + " ---");
        try {
            Function<DadosIbge, BigDecimal> extratorDiscada = DadosIbge::getDiscada;
            List<ComparacaoRegionalDtoModel> rankingDiscadaAsc = service.compararMetricaEntreRegioes(
                    2019, extratorDiscada, daoCoefInternet, false
            );
            imprimirLista(rankingDiscadaAsc, "  ");
        } catch (Exception e) { imprimirErro("compararMetricaEntreRegioes (Discada ASC)", e); }


        // --- Teste 4: calcularCrescimentoAnualMultiplo ---
        // Valida o cálculo de crescimento de *todas* as métricas simultaneamente.
        System.out.println("\n--- Teste 4: calcularCrescimentoAnualMultiplo('Brasil') - Tabela: " + daoDistPercentual.getTableName() + " ---");
        try {
            List<CrescimentoMultiploDtoModel> crescimentoMultiplo = service.calcularCrescimentoAnualMultiplo(
                    "Brasil", daoDistPercentual
            );
            imprimirLista(crescimentoMultiplo, "  ");
        } catch (Exception e) {
            imprimirErro("calcularCrescimentoAnualMultiplo", e);
        }


        System.out.println("\n=========================================================");
        System.out.println("FIM DOS TESTES DO SERVICE.");
        System.out.println("=========================================================");
    }

    /**
     * Método utilitário para imprimir uma lista de resultados no console.
     *
     * @param lista   A lista de DTOs ou Modelos a ser impressa.
     * @param prefixo Uma string de prefixo (ex: "  ") para indentação.
     * @param <E>     O tipo genérico dos itens na lista.
     */
    private static <E> void imprimirLista(List<E> lista, String prefixo) {
        if (lista == null || lista.isEmpty()) {
            System.out.println(prefixo + "Nenhum resultado encontrado.");
        } else {
            System.out.println(prefixo + "Resultados:");
            for (E item : lista) {
                System.out.println(prefixo + item); // Usa o método .toString() do item
            }
        }
    }

    /**
     * Método utilitário para imprimir mensagens de erro de forma padronizada.
     *
     * @param nomeMetodo O nome do método que falhou.
     * @param e          A exceção capturada.
     */
    private static void imprimirErro(String nomeMetodo, Exception e) {
        System.out.println("ERRO em " + nomeMetodo + ": " + e.getMessage());
    }
}