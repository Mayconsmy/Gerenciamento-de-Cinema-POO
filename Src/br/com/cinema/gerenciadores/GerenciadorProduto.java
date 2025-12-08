package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.Lanche;
import br.com.cinema.entidades.Produto;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

public class GerenciadorProduto {
    private Cinema cinema;

    public GerenciadorProduto(Cinema cinema) {
        this.cinema = cinema;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE PRODUTOS ---");
            System.out.println("1. Listar Produtos");
            System.out.println("2. Adicionar Lanche");
            System.out.println("3. Vender Lanche");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionarLanche();
                case 3 -> venderLanche();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void listar() {
        if (cinema.getProdutos().isEmpty()) System.out.println("Estoque vazio.");
        for (Produto p : cinema.getProdutos()) System.out.println(p.exibirDetalhes());
    }

    private void adicionarLanche() {
        String nome = Utilitarios.lerTexto("Nome: ");
        double preco = Utilitarios.lerDouble("Preço: ");
        String tam = Utilitarios.lerTexto("Tamanho: ");
        int qtd = Utilitarios.lerInt("Quantidade Inicial: ");
        cinema.getProdutos().add(new Lanche(nome, preco, tam, qtd));
        System.out.println("Lanche cadastrado!");
    }

    private void venderLanche() {
        listar();
        String nome = Utilitarios.lerTexto("Digite o nome do lanche para vender: ");
        int qtd = Utilitarios.lerInt("Quantidade: ");

        for (Produto p : cinema.getProdutos()) {
            if (p instanceof Lanche && p.getNome().equalsIgnoreCase(nome)) {
                Lanche l = (Lanche) p;
                if (l.darBaixaEstoque(qtd)) {
                    System.out.println("Venda realizada! Novo estoque: " + l.getEmEstoque());
                } else {
                    System.out.println("Erro: Estoque insuficiente.");
                }
                return;
            }
        }
        System.out.println("Lanche não encontrado.");
    }
}