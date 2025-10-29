package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class MainApp extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {

            URL fxmlUrl = getClass().getResource("/org/example/view/AnalyseView.fxml");


            if (fxmlUrl == null) {
                System.err.println("Erro Crítico: Não foi possível encontrar o arquivo AnalyseView.fxml.");
                System.err.println("Verifique se ele está na pasta 'resources/org/example/view/'");

                return;
            }


            Parent root = FXMLLoader.load(fxmlUrl);



            Scene scene = new Scene(root, 900, 600);


            primaryStage.setTitle("Análise de Exclusão Digital - IBGE (Tabela 7313)");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Erro fatal ao carregar o FXML (MainApp):");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado ao iniciar a aplicação:");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}