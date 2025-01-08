package com.fipe.principal;

import com.fipe.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private String api = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumoApi = new ConsumoApi();

    public void exibeMenu() {
        System.out.println("""
                Escolha uma opção
                    1 - Carros
                    2 - Motos
                    3 - Caminhões
                """);
        var opcao = scanner.nextLine();

        //Verifica se a pessoa escolheu um numero
            //se não deixa oque ela escreveu
        if (opcao.equals(1)){
            opcao = "carros";
        } else if (opcao.equals(2)) {
            opcao = "motos";
        } else if (opcao.equals(3)) {
            opcao = "caminhoes";
        }
        api = opcao+"/marcas";
        System.out.println(api);
        String json = consumoApi.consumo(api);
        System.out.println(json);
    }
}
