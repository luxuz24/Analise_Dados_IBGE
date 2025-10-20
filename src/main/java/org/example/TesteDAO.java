package org.example;

import org.example.dao.DadosIbgeDAO;
import org.example.model.DadosIbge;

import java.util.List;

public class TesteDAO {

    public static void main(String[] args) {
        DadosIbgeDAO dao = new DadosIbgeDAO();

        System.out.println("Buscando dados no banco...");
        List<DadosIbge> listaDeDados = dao.buscarTodos();

        if (listaDeDados.isEmpty()) {
            System.out.println("Nenhum dado foi encontrado no banco de dados.");
        } else {
            System.out.println(listaDeDados.size() + " registros encontrados.");
            System.out.println("---------------------------------------------");

            for (DadosIbge dado : listaDeDados) {
                System.out.println(dado);
            }
        }
    }
}