package org.example.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox; // MUDANÇA AQUI
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.DadosIbge;
import org.example.service.AnaliseService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AnaliseController {

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

    private AnaliseService analiseService;

    @FXML
    public void initialize() {
        this.analiseService = new AnaliseService();
        carregarFiltros();
    }

    private void carregarFiltros() {
        try {
            comboTabela.setItems(FXCollections.observableArrayList(analiseService.getTabelasNomes()));
            comboRegiao.setItems(FXCollections.observableArrayList(analiseService.getRegioesDisponiveis()));
            comboAno.setItems(FXCollections.observableArrayList(analiseService.getAnosDisponiveis()));
            comboAno.getItems().add(0, null);




            listTiposConexao.getItems().clear();


            List<String> metricas = analiseService.getNomesMetricas();


            List<CheckBox> checkBoxes = new ArrayList<>();
            for (String metrica : metricas) {
                checkBoxes.add(new CheckBox(metrica));
            }


            listTiposConexao.setItems(FXCollections.observableArrayList(checkBoxes));


            listTiposConexao.setFocusTraversable(false);


            comboTabela.getSelectionModel().selectFirst();
            comboRegiao.getSelectionModel().select("Brasil");


        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar filtros", "Não foi possível conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void onCheckSelecionarTodosClick() {

        boolean isSelecionado = checkSelecionarTodos.isSelected();


        for (CheckBox cb : listTiposConexao.getItems()) {

            cb.setSelected(isSelecionado);
        }
    }

    @FXML
    private void onBtnBuscarClick() {
        try {
            String tabelaSelecionada = comboTabela.getValue();
            String regiaoSelecionada = comboRegiao.getValue();
            Integer anoSelecionado = comboAno.getValue();


            List<String> colunasSelecionadas = new ArrayList<>();
            for (CheckBox cb : listTiposConexao.getItems()) {
                if (cb.isSelected()) {
                    colunasSelecionadas.add(cb.getText());
                }
            }

            if (tabelaSelecionada == null || regiaoSelecionada == null || colunasSelecionadas.isEmpty()) {
                exibirAlertaErro("Filtros Incompletos", "Por favor, selecione Tabela, Região e ao menos um Tipo de Conexão.");
                return;
            }

            List<? extends DadosIbge> resultados = analiseService.buscarDadosFiltrados(
                    tabelaSelecionada,
                    regiaoSelecionada,
                    anoSelecionado
            );

            atualizarColunasTabela(colunasSelecionadas);
            tabelaPrincipal.setItems(FXCollections.observableArrayList(resultados));

        } catch (Exception e) {
            exibirAlertaErro("Erro ao buscar dados", "Ocorreu um erro na busca: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void atualizarColunasTabela(List<String> colunasSelecionadas) {

        tabelaPrincipal.getColumns().clear();


        TableColumn<DadosIbge, String> colRegiao = new TableColumn<>("Região");
        colRegiao.setCellValueFactory(new PropertyValueFactory<>("regioes"));
        colRegiao.setMinWidth(120);

        TableColumn<DadosIbge, Integer> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colAno.setMinWidth(60);

        tabelaPrincipal.getColumns().addAll(colRegiao, colAno);

        for (String nomeAmigavel : colunasSelecionadas) {
            String nomeAtributo = analiseService.getAtributoMetrica(nomeAmigavel);

            if (nomeAtributo != null) {
                TableColumn<DadosIbge, BigDecimal> novaColuna = new TableColumn<>(nomeAmigavel);
                novaColuna.setCellValueFactory(new PropertyValueFactory<>(nomeAtributo));
                novaColuna.setMinWidth(100);
                tabelaPrincipal.getColumns().add(novaColuna);
            }
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}