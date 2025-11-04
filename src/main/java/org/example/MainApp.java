package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Classe principal da aplicação JavaFX.
 *
 * Esta classe estende {@link Application} e é o ponto de entrada real
 * para o toolkit do JavaFX. Sua responsabilidade é carregar a view FXML,
 * configurar a cena (Scene) e exibir a janela principal (Stage).
 */
public class MainApp extends Application {

    /**
     * O ponto de entrada principal para todas as aplicações JavaFX.
     * Este método é chamado automaticamente pelo JavaFX runtime após o {@code launch()}
     * ser invocado.
     *
     * @param primaryStage O "palco" principal (a janela da aplicação)
     * fornecido pelo JavaFX runtime.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Localiza o arquivo FXML.
            // O caminho "/org/example/view/AnalyseView.fxml" é relativo à
            // raiz da pasta "resources".
            URL fxmlUrl = getClass().getResource("/org/example/view/AnalyseView.fxml");

            // 2. Validação de segurança: Verifica se o FXML foi encontrado.
            if (fxmlUrl == null) {
                System.err.println("Erro Crítico: Não foi possível encontrar o arquivo AnalyseView.fxml.");
                System.err.println("Verifique se ele está na pasta 'resources/org/example/view/'");
                return; // Encerra a aplicação se a view principal não for encontrada.
            }

            // 3. Carrega o FXML.
            // O FXMLLoader infla o XML, cria as instâncias dos componentes
            // e instancia o AnaliseController, injetando os @FXML.
            Parent root = FXMLLoader.load(fxmlUrl);

            // 4. Cria a Cena (Scene)
            // A Cena contém o nó raiz (root) do FXML e define o tamanho inicial.
            Scene scene = new Scene(root, 900, 600);

            // 5. Configura e exibe o "Palco" (Stage)
            primaryStage.setTitle("Análise de Exclusão Digital - IBGE (Tabela 7313)");
            primaryStage.setScene(scene); // Define a cena na janela
            primaryStage.show(); // Torna a janela visível

        } catch (IOException e) {
            // Captura erros específicos de I/O (ex: FXML mal formatado ou não encontrado)
            System.err.println("Erro fatal ao carregar o FXML (MainApp):");
            e.printStackTrace();
        } catch (Exception e) {
            // Captura qualquer outro erro inesperado durante a inicialização
            System.err.println("Ocorreu um erro inesperado ao iniciar a aplicação:");
            e.printStackTrace();
        }
    }

    /**
     * O ponto de entrada Java padrão (usado pelo Launcher).
     * Este método chama o {@code launch(args)} da classe Application,
     * que inicia o toolkit JavaFX e eventualmente chama o método {@code start()}.
     *
     * @param args Argumentos de linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
        launch(args);
    }
}