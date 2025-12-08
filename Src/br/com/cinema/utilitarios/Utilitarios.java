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
        System.out.println("5. Avaliações");
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
            String input = scanner.nextLine().replaceAll(",", ".");
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Entrada inválida. Usando 0.0.");
            return 0.0;
        }
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Entrada inválida. Usando -1.");
            return -1;
        }
    }

    public static boolean lerBoolean(String mensagem) {
        System.out.print(mensagem);
        
        String input = scanner.nextLine().trim().toLowerCase();
        
        return input.equals("true") || input.equals("t") || input.equals("sim") || input.equals("s");
    }
}