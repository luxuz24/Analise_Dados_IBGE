package org.example;

// Importe o Serviço e os DAOs/Modelos necessários
import org.example.dao.*; // Importa todos os DAOs para referência
import org.example.model.*; // Importa a classe base e os DTOs
import org.example.service.AnaliseService; // Importa o Serviço!

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Classe de teste para validar os métodos da AnaliseService.
 * Esta classe usa o console (System.out.println) para verificar se os cálculos e
 * a lógica de negócio implementados no Service (que usam os DAOs) estão corretos.
 */
public class TesteDao {

    public static void main(String[] args) {


        AnaliseService service = new AnaliseService();


        DistribuicaoPercentualMoradoresDao daoDistPercentual = new DistribuicaoPercentualMoradoresDao();
        CoeficienteVariacaoInternetDao daoCoefInternet = new CoeficienteVariacaoInternetDao(); // Outro DAO para variar

        System.out.println("=========================================================");
        System.out.println("INICIANDO TESTES COMPLETOS PARA: AnaliseService");
        System.out.println("=========================================================");

        // --- Teste 1: calcularCrescimentoAnualMetrica ---
        System.out.println("\n--- Teste 1.1: Crescimento 'Banda Larga Fixa' (Brasil) - Tabela: " + daoDistPercentual.getTableName() + " ---");
        try {
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


    private static <E> void imprimirLista(List<E> lista, String prefixo) {
        if (lista == null || lista.isEmpty()) {
            System.out.println(prefixo + "Nenhum resultado encontrado.");
        } else {
            System.out.println(prefixo + "Resultados:");
            for (E item : lista) {
                System.out.println(prefixo + item);
            }
        }
    }

    private static void imprimirErro(String nomeMetodo, Exception e) {
        System.out.println("ERRO em " + nomeMetodo + ": " + e.getMessage());

    }
}