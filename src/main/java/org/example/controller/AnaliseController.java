package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.ComparacaoRegionalDtoModel;
import org.example.model.CrescimentoAnualDtoModel;
import org.example.model.CrescimentoMultiploDtoModel;
import org.example.model.DadosIbge;
import org.example.service.AnaliseService;
import org.example.dao.GenericDao;

import javafx.scene.control.TableCell;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Controller principal da aplicação de Análise de Dados do IBGE.
 * Gerencia todas as interações do usuário nas três abas principais:
 * 1. Dados Brutos (Tabela dinâmica)
 * 2. Análise Gráfica (Gráficos de Barra e Pizza)
 * 3. Crescimento Múltiplo (Tabela de % de crescimento ano-a-ano)
 */
public class AnaliseController {

    // --- Componentes Compartilhados ---
    /**
     * O "cérebro" da aplicação. Fornece todos os dados e lógicas de negócio.
     */
    private AnaliseService analiseService;
    @FXML
    private TabPane tabPanePrincipal;

    // --- Componentes da Aba 1 (Dados Brutos) ---
    @FXML
    private ComboBox<String> comboTabela;
    @FXML
    private ComboBox<String> comboRegiao;
    @FXML
    private ComboBox<Integer> comboAno;
    @FXML
    private CheckBox checkSelecionarTodos;
    @FXML
    private ListView<CheckBox> listTiposConexao;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<DadosIbge> tabelaPrincipal;

    // --- Componentes da Aba 2 (Análise Gráfica) ---
    @FXML
    private ComboBox<String> comboGraficoTabela;
    @FXML
    private ComboBox<String> comboGraficoMetrica;
    @FXML
    private ComboBox<String> comboGraficoRegiao;
    @FXML
    private ComboBox<Integer> comboGraficoAno;
    @FXML
    private Button btnGerarGraficos;
    @FXML
    private BarChart<String, Number> graficoBarrasCrescimento;
    @FXML
    private CategoryAxis eixoXBarras;
    @FXML
    private NumberAxis eixoYBarras;
    @FXML
    private PieChart graficoPizzaComparacao;

    // --- Componentes da Aba 3 (Crescimento Múltiplo) ---
    @FXML
    private ComboBox<String> comboCrescimentoTabela;
    @FXML
    private ComboBox<String> comboCrescimentoRegiao;
    @FXML
    private Button btnGerarCrescimento;
    @FXML
    private TableView<CrescimentoMultiploDtoModel> tabelaCrescimento;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Integer> colCrescAno;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescTotal;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescDiscada;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescBandaLarga;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescSomenteBandaLarga;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescBandaLargaFixa;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescSomenteBandaLargaFixa;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescBandaLargaMovel;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescSomenteBandaLargaMovel;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescBandaLargaFixaMovel;
    @FXML
    private TableColumn<CrescimentoMultiploDtoModel, Double> colCrescSomenteBandaLargaFixaMovel;


    /**
     * Método de inicialização do JavaFX.
     * Chamado automaticamente após o FXML ser carregado.
     * Instancia o serviço e configura os filtros e tabelas iniciais.
     */
    @FXML
    public void initialize() {
        this.analiseService = new AnaliseService();

        // Carrega os filtros para cada aba
        carregarFiltrosTabela();
        carregarFiltrosGraficos();
        carregarFiltrosCrescimento();

        // Configura as colunas da tabela de crescimento (formatação, etc.)
        configurarTabelaCrescimento();
    }

    /**
     * Carrega os filtros do painel esquerdo (Aba 1: Dados Brutos).
     * Popula os ComboBoxes e a ListView de CheckBoxes.
     */
    private void carregarFiltrosTabela() {
        try {
            // Popula os ComboBoxes
            comboTabela.setItems(FXCollections.observableArrayList(analiseService.getTabelasNomes()));
            comboRegiao.setItems(FXCollections.observableArrayList(analiseService.getRegioesDisponiveis()));
            comboAno.setItems(FXCollections.observableArrayList(analiseService.getAnosDisponiveis()));
            comboAno.getItems().add(0, null); // Adiciona a opção "Todos os Anos"

            // Popula a ListView com CheckBoxes
            listTiposConexao.getItems().clear();
            List<String> metricas = analiseService.getNomesMetricas();
            List<CheckBox> checkBoxes = new ArrayList<>();
            for (String metrica : metricas) {
                checkBoxes.add(new CheckBox(metrica));
            }
            listTiposConexao.setItems(FXCollections.observableArrayList(checkBoxes));
            // Impede que a *linha* da ListView seja selecionada, apenas o CheckBox
            listTiposConexao.setFocusTraversable(false);

            // Define filtros padrão
            comboTabela.getSelectionModel().selectFirst();
            comboRegiao.getSelectionModel().select("Brasil");

        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar filtros (Aba 1)", "Não foi possível conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carrega os filtros da Aba 2: Análise Gráfica.
     * Garante que esta aba tenha seus próprios controles independentes.
     */
    private void carregarFiltrosGraficos() {
        try {
            comboGraficoTabela.setItems(FXCollections.observableArrayList(analiseService.getTabelasNomes()));
            comboGraficoTabela.getSelectionModel().selectFirst();

            comboGraficoMetrica.setItems(FXCollections.observableArrayList(analiseService.getNomesMetricas()));
            comboGraficoMetrica.getSelectionModel().selectFirst();

            // Remove a opção "Todos" pois não faz sentido em gráficos comparativos
            List<String> regioesGrafico = new ArrayList<>(analiseService.getRegioesDisponiveis());
            regioesGrafico.remove("Todos");
            comboGraficoRegiao.setItems(FXCollections.observableArrayList(regioesGrafico));
            comboGraficoRegiao.getSelectionModel().select("Brasil");

            comboGraficoAno.setItems(FXCollections.observableArrayList(analiseService.getAnosDisponiveis()));
            comboGraficoAno.getSelectionModel().selectFirst(); // Padrão para o ano mais recente

        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar filtros (Aba 2)", "Não foi possível conectar ao banco de dados: " + e.getMessage());
        }
    }

    /**
     * Carrega os filtros da Aba 3: Crescimento Múltiplo.
     */
    private void carregarFiltrosCrescimento() {
        try {
            comboCrescimentoTabela.setItems(FXCollections.observableArrayList(analiseService.getTabelasNomes()));
            comboCrescimentoTabela.getSelectionModel().selectFirst();

            // Remove "Todos", pois a análise é por região
            List<String> regioesCrescimento = new ArrayList<>(analiseService.getRegioesDisponiveis());
            regioesCrescimento.remove("Todos");
            comboCrescimentoRegiao.setItems(FXCollections.observableArrayList(regioesCrescimento));
            comboCrescimentoRegiao.getSelectionModel().select("Brasil");

        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar filtros (Aba 3)", "Não foi possível conectar ao banco de dados: " + e.getMessage());
        }
    }


    /**
     * Configura as colunas da Tabela de Crescimento Múltiplo (Aba 3).
     * Esta função "linka" as colunas do FXML aos atributos do DTO {@link CrescimentoMultiploDtoModel}
     * e aplica a formatação de duas casas decimais.
     */
    private void configurarTabelaCrescimento() {
        // 1. Linka os dados usando PropertyValueFactory.
        // Os nomes (ex: "ano", "percentualTotal") devem ser idênticos aos atributos da classe DTO.
        colCrescAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colCrescTotal.setCellValueFactory(new PropertyValueFactory<>("percentualTotal"));
        colCrescDiscada.setCellValueFactory(new PropertyValueFactory<>("percentualDiscada"));
        colCrescBandaLarga.setCellValueFactory(new PropertyValueFactory<>("percentualBandaLarga"));
        colCrescSomenteBandaLarga.setCellValueFactory(new PropertyValueFactory<>("percentualSomenteBandaLarga"));
        colCrescBandaLargaFixa.setCellValueFactory(new PropertyValueFactory<>("percentualBandaLargaFixa"));
        colCrescSomenteBandaLargaFixa.setCellValueFactory(new PropertyValueFactory<>("percentualSomenteBandaLargaFixa"));
        colCrescBandaLargaMovel.setCellValueFactory(new PropertyValueFactory<>("percentualBandaLargaMovel"));
        colCrescSomenteBandaLargaMovel.setCellValueFactory(new PropertyValueFactory<>("percentualSomenteBandaLargaMovel"));
        colCrescBandaLargaFixaMovel.setCellValueFactory(new PropertyValueFactory<>("percentualBandaLargaFixaMovel"));
        colCrescSomenteBandaLargaFixaMovel.setCellValueFactory(new PropertyValueFactory<>("percentualSomenteBandaLargaFixaMovel"));

        // 2. Aplica a formatação de 2 casas decimais.
        // Cria uma "fábrica de células" reutilizável
        Callback<TableColumn<CrescimentoMultiploDtoModel, Double>, TableCell<CrescimentoMultiploDtoModel, Double>> factory = getDoubleCellFactory();

        // Aplica a formatação a todas as colunas de porcentagem
        colCrescTotal.setCellFactory(factory);
        colCrescDiscada.setCellFactory(factory);
        colCrescBandaLarga.setCellFactory(factory);
        colCrescSomenteBandaLarga.setCellFactory(factory);
        colCrescBandaLargaFixa.setCellFactory(factory);
        colCrescSomenteBandaLargaFixa.setCellFactory(factory);
        colCrescBandaLargaMovel.setCellFactory(factory);
        colCrescSomenteBandaLargaMovel.setCellFactory(factory);
        colCrescBandaLargaFixaMovel.setCellFactory(factory);
        colCrescSomenteBandaLargaFixaMovel.setCellFactory(factory);
    }


    // --- MÉTODOS DE AÇÃO (HANDLERS) ---

    // --- ABA 1: DADOS BRUTOS ---

    /**
     * Chamado ao clicar no CheckBox "Selecionar Todos" (Aba 1).
     * Marca ou desmarca todos os CheckBoxes na ListView de colunas.
     */
    @FXML
    private void onCheckSelecionarTodosClick() {
        boolean isSelecionado = checkSelecionarTodos.isSelected();
        for (CheckBox cb : listTiposConexao.getItems()) {
            cb.setSelected(isSelecionado);
        }
    }

    /**
     * Chamado ao clicar no botão "Analisar Tabela" (Aba 1).
     * Busca os dados brutos filtrados e reconstrói a tabela dinamicamente.
     */
    @FXML
    private void onBtnBuscarClick() {
        try {
            // 1. Coleta os filtros da Aba 1
            String tabelaSelecionada = comboTabela.getValue();
            String regiaoSelecionada = comboRegiao.getValue();
            Integer anoSelecionado = comboAno.getValue();

            // 2. Coleta as colunas selecionadas (lendo os CheckBoxes)
            List<String> colunasSelecionadas = new ArrayList<>();
            for (CheckBox cb : listTiposConexao.getItems()) {
                if (cb.isSelected()) {
                    colunasSelecionadas.add(cb.getText()); // Pega o nome amigável
                }
            }

            // 3. Validação
            if (tabelaSelecionada == null || regiaoSelecionada == null || colunasSelecionadas.isEmpty()) {
                exibirAlertaErro("Filtros Incompletos", "Por favor, selecione Tabela, Região e ao menos um Tipo de Conexão.");
                return;
            }

            // 4. Busca os dados no serviço
            List<? extends DadosIbge> resultados = analiseService.buscarDadosFiltrados(
                    tabelaSelecionada,
                    regiaoSelecionada,
                    anoSelecionado
            );

            // 5. Constrói a tabela dinamicamente com as colunas pedidas
            atualizarColunasTabela(colunasSelecionadas);
            // 6. Popula a tabela
            tabelaPrincipal.setItems(FXCollections.observableArrayList(resultados));

        } catch (Exception e) {
            exibirAlertaErro("Erro ao buscar dados", "Ocorreu um erro na busca: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reconstrói as colunas da Tabela de Dados Brutos (Aba 1) dinamicamente.
     * Esta função permite que o usuário escolha quais colunas exibir.
     *
     * @param colunasSelecionadas Lista de nomes amigáveis das colunas (ex: "Banda Larga Fixa").
     */
    private void atualizarColunasTabela(List<String> colunasSelecionadas) {
        // Limpa a tabela de colunas antigas
        tabelaPrincipal.getColumns().clear();

        // Adiciona as colunas estáticas (sempre presentes)
        TableColumn<DadosIbge, String> colRegiao = new TableColumn<>("Região");
        colRegiao.setCellValueFactory(new PropertyValueFactory<>("regioes"));
        colRegiao.setMinWidth(120);

        TableColumn<DadosIbge, Integer> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colAno.setMinWidth(60);

        tabelaPrincipal.getColumns().addAll(colRegiao, colAno);

        // Adiciona as colunas dinâmicas (selecionadas pelo usuário)
        for (String nomeAmigavel : colunasSelecionadas) {
            // Pega o nome real do atributo (ex: "bandaLargaFixa")
            String nomeAtributo = analiseService.getAtributoMetrica(nomeAmigavel);

            if (nomeAtributo != null) {
                TableColumn<DadosIbge, BigDecimal> novaColuna = new TableColumn<>(nomeAmigavel);
                // Linka a coluna ao atributo (ex: "bandaLargaFixa") do objeto DadosIbge
                novaColuna.setCellValueFactory(new PropertyValueFactory<>(nomeAtributo));
                novaColuna.setMinWidth(100);
                tabelaPrincipal.getColumns().add(novaColuna);
            }
        }
    }

    // --- ABA 2: ANÁLISE GRÁFICA ---

    /**
     * Chamado ao clicar no botão "Gerar Gráficos" (Aba 2).
     * Coleta os filtros da Aba 2 e chama as funções auxiliares para gerar os gráficos.
     */
    @FXML
    private void onBtnGerarGraficosClick() {
        try {
            // 1. Coleta os filtros da Aba 2
            String tabelaNome = comboGraficoTabela.getValue();
            String metricaNome = comboGraficoMetrica.getValue();
            String regiaoGraficoBarras = comboGraficoRegiao.getValue();
            Integer anoGraficoPizza = comboGraficoAno.getValue();

            // 2. Validação
            if (tabelaNome == null || metricaNome == null || regiaoGraficoBarras == null || anoGraficoPizza == null) {
                exibirAlertaErro("Filtros Incompletos", "Por favor, selecione todos os filtros para gerar os gráficos.");
                return;
            }

            // 3. Prepara os "extratores" de dados
            String atributo = analiseService.getAtributoMetrica(metricaNome);
            // Cria uma função Lambda para extrair o dado do objeto (ex: d.getBandaLargaFixa())
            Function<DadosIbge, BigDecimal> extratorMetrica = (DadosIbge d) -> (BigDecimal) d.get(atributo);
            GenericDao<? extends DadosIbge> dao = analiseService.getDaoPeloNome(tabelaNome);

            // 4. Gera os gráficos
            gerarGraficoDeCrescimento(dao, extratorMetrica, metricaNome, regiaoGraficoBarras, tabelaNome);
            gerarGraficoDeComparacao(dao, extratorMetrica, metricaNome, anoGraficoPizza, tabelaNome);

        } catch (Exception e) {
            exibirAlertaErro("Erro ao gerar gráficos", "Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper que gera o Gráfico de Barras (Crescimento Anual).
     *
     * @param dao             O DAO da tabela selecionada (ex: DomicilioInternetPorConexaoDao).
     * @param extratorMetrica A função Lambda que sabe como extrair o valor da métrica.
     * @param metricaNome     O nome amigável da métrica (para o título).
     * @param regiao          A região a ser analisada (para o título e filtro).
     * @param tabelaNome      O nome amigável da tabela (para o título).
     */
    private void gerarGraficoDeCrescimento(GenericDao<? extends DadosIbge> dao, Function<DadosIbge, BigDecimal> extratorMetrica, String metricaNome, String regiao, String tabelaNome) {

        // 1. Busca os dados de crescimento no serviço
        List<CrescimentoAnualDtoModel> dadosCrescimento = analiseService.calcularCrescimentoAnualMetrica(
                regiao,
                extratorMetrica,
                dao
        );

        // 2. Cria a "Série" de dados para o gráfico
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Crescimento % de " + metricaNome);

        for (CrescimentoAnualDtoModel dto : dadosCrescimento) {
            // Adiciona o par (Ano, Valor)
            series.getData().add(new XYChart.Data<>(String.valueOf(dto.getAno()), dto.getPercentualCrescimento()));
        }

        // 3. Popula o gráfico
        graficoBarrasCrescimento.getData().clear();
        graficoBarrasCrescimento.getData().add(series);

        // 4. Define o título descritivo
        String titulo = String.format("Crescimento Anual de '%s' (%s)\nFonte: %s",
                metricaNome, regiao, tabelaNome);
        graficoBarrasCrescimento.setTitle(titulo);
    }

    /**
     * Helper que gera o Gráfico de Pizza (Comparação Regional).
     *
     * @param dao             O DAO da tabela selecionada.
     * @param extratorMetrica A função Lambda que sabe como extrair o valor da métrica.
     * @param metricaNome     O nome amigável da métrica (para o título).
     * @param ano             O ano a ser analisado (para o título e filtro).
     * @param tabelaNome      O nome amigável da tabela (para o título).
     */
    private void gerarGraficoDeComparacao(GenericDao<? extends DadosIbge> dao, Function<DadosIbge, BigDecimal> extratorMetrica, String metricaNome, int ano, String tabelaNome) {

        // 1. Busca os dados de comparação no serviço
        List<ComparacaoRegionalDtoModel> dadosComparacao = analiseService.compararMetricaEntreRegioes(
                ano,
                extratorMetrica,
                dao,
                true // Ordem descendente
        );

        // 2. Converte os DTOs em dados de Pizza
        List<PieChart.Data> dadosPizza = new ArrayList<>();
        for (ComparacaoRegionalDtoModel dto : dadosComparacao) {
            // Ignora valores nulos ou zero para não poluir o gráfico
            if (dto.getValorMetrica() != null && dto.getValorMetrica().compareTo(BigDecimal.ZERO) > 0) {
                // Formata a legenda para incluir o valor
                String legenda = String.format("%s (%.1f)", dto.getRegiao(), dto.getValorMetrica().doubleValue());
                dadosPizza.add(new PieChart.Data(legenda, dto.getValorMetrica().doubleValue()));
            }
        }

        // 3. Popula o gráfico
        graficoPizzaComparacao.getData().clear();
        graficoPizzaComparacao.setData(FXCollections.observableArrayList(dadosPizza));

        // 4. Define o título descritivo
        String titulo = String.format("Distribuição de '%s' por Região (%d)\nFonte: %s",
                metricaNome, ano, tabelaNome);
        graficoPizzaComparacao.setTitle(titulo);

        // 5. Configurações visuais (correção de bug de renderização)
        graficoPizzaComparacao.setLabelsVisible(false); // Desliga rótulos "spiderweb"
        graficoPizzaComparacao.setLegendVisible(true);  // Liga a legenda lateral
    }


    // --- ABA 3: CRESCIMENTO MÚLTIPLO ---

    /**
     * Chamado ao clicar no botão "Gerar Tabela de Crescimento" (Aba 3).
     * Busca os dados de crescimento de *todas* as métricas e popula a tabela.
     */
    @FXML
    private void onBtnGerarCrescimentoClick() {
        try {
            // 1. Coleta os filtros da Aba 3
            String tabelaNome = comboCrescimentoTabela.getValue();
            String regiao = comboCrescimentoRegiao.getValue();

            // 2. Validação
            if (tabelaNome == null || regiao == null) {
                exibirAlertaErro("Filtros Incompletos", "Por favor, selecione a Fonte de Dados e a Região.");
                return;
            }

            GenericDao<? extends DadosIbge> dao = analiseService.getDaoPeloNome(tabelaNome);

            // 3. Busca os dados no serviço (usando a função de Múltiplo)
            List<CrescimentoMultiploDtoModel> resultados = analiseService.calcularCrescimentoAnualMultiplo(regiao, dao);

            // 4. Popula a tabela
            tabelaCrescimento.setItems(FXCollections.observableArrayList(resultados));

        } catch (Exception e) {
            exibirAlertaErro("Erro ao gerar tabela", "Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // --- MÉTODOS UTILITÁRIOS ---

    /**
     * Cria uma fábrica de células (CellFactory) reutilizável para a Tabela 3.
     * Esta fábrica é responsável por pegar um valor {@code Double} e formatá-lo
     * como uma {@code String} com exatamente duas casas decimais (ex: "1.61999" -> "1.62").
     *
     * @return Um Callback (fábrica) que gera TableCells formatadas.
     */
    private Callback<TableColumn<CrescimentoMultiploDtoModel, Double>, TableCell<CrescimentoMultiploDtoModel, Double>> getDoubleCellFactory() {
        return column -> {
            // Retorna uma nova instância de TableCell
            return new TableCell<CrescimentoMultiploDtoModel, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null); // Mostra a célula vazia se não houver dados
                    } else {
                        // A formatação acontece aqui: "%.2f" significa "float/double com 2 casas decimais"
                        setText(String.format("%.2f", item));
                    }
                }
            };
        };
    }

    /**
     * Exibe um pop-up de Alerta de Erro para o usuário.
     *
     * @param titulo   O título da janela de alerta.
     * @param mensagem A mensagem de erro detalhada.
     */
    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Sem cabeçalho
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}