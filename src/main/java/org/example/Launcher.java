package org.example;

/**
 * Classe "Lançadora" (Launcher/Wrapper) para a aplicação JavaFX.
 *
 * Esta classe serve como o ponto de entrada principal (Main-Class)
 * para a aplicação, contornando um problema comum de "classpath vs. module-path"
 * do JavaFX em IDEs modernas (Erro: "JavaFX runtime components are missing").
 *
 * O Java pode ter problemas ao lançar uma classe que estende diretamente
 * {@link javafx.application.Application} a partir do classpath.
 * Ao executar esta classe (que é um POJO simples), o Java inicia normalmente
 * e *então* chama o main da {@link MainApp}, que por sua vez inicia o JavaFX
 * com sucesso.
 */
public class Launcher {
    /**
     * O ponto de entrada principal (main) da aplicação.
     *
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        // Simplesmente repassa a chamada para o método main da MainApp,
        // que iniciará o toolkit JavaFX.
        MainApp.main(args);
    }
}