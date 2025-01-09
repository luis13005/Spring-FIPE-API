package com.fipe.principal;

import com.fipe.models.Carro;
import com.fipe.models.Dados;
import com.fipe.models.Modelo;
import com.fipe.service.ConsumoApi;
import com.fipe.service.ConverteDado;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private String api = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDado converte = new ConverteDado();

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
        if (opcao.equals("1")){
            opcao = "carros";
        } else if (opcao.equals("2")) {
            opcao = "motos";
        } else if (opcao.equals("3")) {
            opcao = "caminhoes";
        }
        api = api+opcao+"/marcas";

        System.out.println(api);
        String json = consumoApi.consumo(api);

        List<Dados> dado = converte.converteDadoLista(json, Dados.class);

        dado.stream()
                .sorted(Comparator.comparing(Dados::codigo).reversed())
                .forEach(System.out::println);

        System.out.println("\nDigite o Código da Marca: ");
        opcao = scanner.nextLine();

        api = api+"/"+opcao+"/modelos";

        json = consumoApi.consumo(api);

        Modelo modelo = converte.converteDado(json, Modelo.class);

        System.out.println(modelo);

        modelo.modelo().stream().forEach(System.out::println);

        System.out.println("\nDigite o nome do modelo");
        String nomeModelo = scanner.nextLine();
        modelo.modelo().stream().filter(n -> n.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("\nDigite o Código do Modelo: ");
        opcao = scanner.nextLine();

        api = api+"/"+opcao+"/anos";
        System.out.println(api);

        json = consumoApi.consumo(api);
        System.out.println(json);

        List<Dados> anoList = converte.converteDadoLista(json, Dados.class);

        List<Carro> carros = new ArrayList<>();

        for (int i = 0; i < anoList.size(); i++) {

            String newAPi = api+"/"+anoList.get(i).codigo();

            json = consumoApi.consumo(newAPi);
            Carro carro = converte.converteDado(json,Carro.class);

            carros.add(carro);
        }

        carros.stream().forEach(System.out::println);

    }
}
