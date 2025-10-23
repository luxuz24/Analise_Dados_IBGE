package org.example;

import org.example.dao.CoeficienteVariacaoInternetDao;

import org.example.model.CoeficienteVariacaoInternetModel;

import java.util.List;


public class TesteDao {

    public static void main(String[] args) {

        CoeficienteVariacaoInternetDao dao = new CoeficienteVariacaoInternetDao();

        System.out.println("=========================================================");
        System.out.println("INICIANDO TESTES PARA: " + dao.getTableName());
        System.out.println("=========================================================");


        System.out.println("\n--- Teste 1: buscarTodos() ---");
        try {
            List<CoeficienteVariacaoInternetModel> listaTodos = dao.buscarTodos();
            if (listaTodos.isEmpty()) {
                System.out.println("Resultado: Nenhum dado encontrado.");
            } else {
                System.out.printf("Resultado: Sucesso! %d registros encontrados.\n", listaTodos.size());
                System.out.println("Primeiro registro: " + listaTodos.get(0));
            }
        } catch (Exception e) {
            System.out.println("ERRO no buscarTodos(): " + e.getMessage());
        }

        int anoTeste = 2022;
        System.out.printf("\n--- Teste 2: buscarPorAno(%d) ---\n", anoTeste);
        try {
            List<CoeficienteVariacaoInternetModel> listaPorAno = dao.buscarPorAno(anoTeste);
            if (listaPorAno.isEmpty()) {
                System.out.printf("Resultado: Nenhum dado encontrado para %d.\n", anoTeste);
            } else {
                System.out.printf("Resultado: Sucesso! %d registros encontrados para %d.\n", listaPorAno.size(), anoTeste);
                System.out.printf("Primeiro registro de %d: %s\n", anoTeste, listaPorAno.get(0));
            }
        } catch (Exception e) {
            System.out.println("ERRO no buscarPorAno(): " + e.getMessage());
        }

        System.out.println("\n--- Teste 3: buscarAnosDistintos() ---");
        try {
            List<Integer> anos = dao.buscarAnosDistintos();
            if (anos.isEmpty()) {
                System.out.println("Resultado: Nenhum ano distinto encontrado.");
            } else {
                System.out.println("Resultado: Anos distintos encontrados: " + anos);
            }
        } catch (Exception e) {
            System.out.println("ERRO no buscarAnosDistintos(): " + e.getMessage());
        }

        String regiaoTeste = "Brasil";
        System.out.printf("\n--- Teste 4: buscarPorRegiao('%s') ---\n", regiaoTeste);
        try {
            List<CoeficienteVariacaoInternetModel> listaPorRegiao = dao.buscarPorRegiao(regiaoTeste);
            if (listaPorRegiao.isEmpty()) {
                System.out.printf("Resultado: Nenhum dado encontrado para '%s'.\n", regiaoTeste);
            } else {
                System.out.printf("Resultado: Sucesso! %d registros (série histórica) encontrados para '%s'.\n", listaPorRegiao.size(), regiaoTeste);
                System.out.println("Registro mais antigo: " + listaPorRegiao.get(0)); // A query ordena por ano ASC
            }
        } catch (Exception e) {
            System.out.println("ERRO no buscarPorRegiao(): " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("FIM DOS TESTES.");
        System.out.println("=========================================================");
    }
}