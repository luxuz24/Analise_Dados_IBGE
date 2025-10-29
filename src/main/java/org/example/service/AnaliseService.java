package org.example.service;

import org.example.dao.*;
import org.example.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnaliseService {


    private final Map<String, GenericDao<? extends DadosIbge>> daosMap;
    private final Map<String, String> metricasMap;


    public AnaliseService() {


        daosMap = new LinkedHashMap<>();
        daosMap.put("Domicílios por Conexão", new DomicilioInternetPorConexaoDao());
        daosMap.put("Moradores por Conexão", new MoradoresInternetPorConexaoDao());
        daosMap.put("Distribuição % Domicílios", new DistribuicaoPercentualInternetDao());
        daosMap.put("Distribuição % Moradores", new DistribuicaoPercentualMoradoresDao());
        daosMap.put("Coef. Variação Domicílios", new CoeficienteVariacaoInternetDao());
        daosMap.put("Coef. Variação Moradores", new CoeficienteVariacaoMoradoresDao());
        daosMap.put("Coef. Variação % Domicílios", new CoeficienteVariacaoDistribuicaoDao());
        daosMap.put("Coef. Variação % Moradores", new CoeficienteVariacaoDistribuicaoMoradoresDao());


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



    public List<String> getTabelasNomes() {
        return new ArrayList<>(daosMap.keySet());
    }

    public List<String> getRegioesDisponiveis() {

        List<String> regioes = daosMap.values().iterator().next().buscarPorRegioesDistintas();


        regioes.removeIf(r -> r.equalsIgnoreCase("Brasil"));


        regioes.add(0, "Brasil");
        regioes.add(1, "Todos");
        return regioes;
    }


    public List<Integer> getAnosDisponiveis() {

        List<Integer> anos = daosMap.values().iterator().next().buscarAnosDistintos();

        return anos;
    }


    public List<String> getNomesMetricas() {
        return new ArrayList<>(metricasMap.keySet());
    }


    public String getAtributoMetrica(String nomeAmigavel) {
        return metricasMap.get(nomeAmigavel);
    }



    public List<? extends DadosIbge> buscarDadosFiltrados(String nomeTabela, String regiao, Integer ano) {
        GenericDao<? extends DadosIbge> daoSelecionado = daosMap.get(nomeTabela);
        if (daoSelecionado == null) {
            throw new IllegalArgumentException("Nome da tabela desconhecido: " + nomeTabela);
        }


        String regiaoFiltro = (regiao != null && regiao.equalsIgnoreCase("Todos")) ? null : regiao;

        return daoSelecionado.buscarComFiltros(ano, regiaoFiltro);
    }


    public List<CrescimentoAnualDtoModel> calcularCrescimentoAnualMetrica(
            String regiao,
            Function<DadosIbge, BigDecimal> extratorMetrica,
            GenericDao<? extends DadosIbge> daoEspecifico
    ) {

        List<? extends DadosIbge> serieHistorica = daoEspecifico.buscarPorRegiao(regiao);
        List<CrescimentoAnualDtoModel> resultados = new ArrayList<>();

        if (serieHistorica == null || serieHistorica.size() < 2) {
            System.err.println("Dados insuficientes (" + (serieHistorica == null ? 0 : serieHistorica.size()) + ") para calcular crescimento para a região: " + regiao + " na tabela: " + daoEspecifico.getTableName());
            return resultados;
        }

        for (int i = 1; i < serieHistorica.size(); i++) {
            DadosIbge dadosAtuais = serieHistorica.get(i);
            DadosIbge dadosAnteriores = serieHistorica.get(i - 1);

            BigDecimal valorAtual = extratorMetrica.apply(dadosAtuais);
            BigDecimal valorAnterior = extratorMetrica.apply(dadosAnteriores);

            double percentual = calcularPercentual(valorAtual, valorAnterior);

            resultados.add(new CrescimentoAnualDtoModel(dadosAtuais.getAno(), percentual));
        }
        return resultados;
    }


    public List<CrescimentoMultiploDtoModel> calcularCrescimentoAnualMultiplo(
            String regiao,
            GenericDao<? extends DadosIbge> daoEspecifico
    ) {
        List<? extends DadosIbge> serieHistorica = daoEspecifico.buscarPorRegiao(regiao);
        List<CrescimentoMultiploDtoModel> resultados = new ArrayList<>();

        if (serieHistorica == null || serieHistorica.size() < 2) {
            System.err.println("Dados insuficientes (" + (serieHistorica == null ? 0 : serieHistorica.size()) + ") para calcular crescimento múltiplo para a região: " + regiao + " na tabela: " + daoEspecifico.getTableName());
            return resultados;
        }

        for (int i = 1; i < serieHistorica.size(); i++) {
            DadosIbge dadosAtuais = serieHistorica.get(i);
            DadosIbge dadosAnteriores = serieHistorica.get(i - 1);

            Integer anoAtualObj = dadosAtuais.getAno();
            int anoAtualPrimitivo = (anoAtualObj != null) ? anoAtualObj.intValue() : 0;

            CrescimentoMultiploDtoModel dtoAnoAtual = new CrescimentoMultiploDtoModel();
            dtoAnoAtual.setAno(anoAtualPrimitivo);

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



    public DadosIbge obterDadosDetalhadosConexao(String regiao, int ano, GenericDao<? extends DadosIbge> daoEspecifico) {
        return daoEspecifico.buscarPorRegiaoEAno(regiao, ano);
    }


    public List<ComparacaoRegionalDtoModel> compararMetricaEntreRegioes(
            int ano,
            Function<DadosIbge, BigDecimal> extratorMetrica,
            GenericDao<? extends DadosIbge> daoEspecifico,
            boolean ordemDescendente
    ) {

        List<? extends DadosIbge> dadosDoAno = daoEspecifico.buscarPorAno(ano);

        List<ComparacaoRegionalDtoModel> comparacao = dadosDoAno.stream()

                .filter(dado -> dado.getRegioes() != null && !dado.getRegioes().equalsIgnoreCase("Brasil"))
                .map(dado -> new ComparacaoRegionalDtoModel(
                        dado.getRegioes(),
                        extratorMetrica.apply(dado)
                ))
                .collect(Collectors.toList());


        Comparator<ComparacaoRegionalDtoModel> comparador = Comparator.comparing(
                ComparacaoRegionalDtoModel::getValorMetrica,
                Comparator.nullsLast(BigDecimal::compareTo)
        );

        if (ordemDescendente) {
            comparador = comparador.reversed();
        }

        comparacao.sort(comparador);

        return comparacao;
    }





    private double calcularPercentual(BigDecimal valorAtual, BigDecimal valorAnterior) {
        double percentual = 0.0; // Valor padrão


        if (valorAtual != null && valorAnterior != null && valorAnterior.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal diferenca = valorAtual.subtract(valorAnterior);

            BigDecimal crescimento = diferenca.divide(valorAnterior, 4, RoundingMode.HALF_UP);
            percentual = crescimento.doubleValue() * 100;
        }

        else if (valorAtual != null && valorAtual.compareTo(BigDecimal.ZERO) != 0 && (valorAnterior == null || valorAnterior.compareTo(BigDecimal.ZERO) == 0)) {
            percentual = 100.0;
        }
        else if (valorAnterior != null && valorAnterior.compareTo(BigDecimal.ZERO) != 0 && (valorAtual == null || valorAtual.compareTo(BigDecimal.ZERO) == 0)) {
            percentual = -100.0;
        }


        return percentual;
    }
}