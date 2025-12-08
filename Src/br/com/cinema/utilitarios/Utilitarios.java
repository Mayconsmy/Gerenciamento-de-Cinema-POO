package br.com.cinema.utilitarios;

import java.util.Scanner;

public class Utilitarios {
    public static Scanner scanner = new Scanner(System.in);

    public static void exibirMenuPrincipal() {
        System.out.println("\n--- SISTEMA DE GERENCIAMENTO DE CINEMA ---");
        System.out.println("1. Pessoas");
        System.out.println("2. Filmes");
        System.out.println("3. Produtos");
        System.out.println("4. Salas");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
    }

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}