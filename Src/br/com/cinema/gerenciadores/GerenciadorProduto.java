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
            System.out.println("\n--- GESTÃO DE PRODUTOS (LANCHES) ---");
            System.out.println("1. Listar Todos os Produtos");
            System.out.println("2. Adicionar Novo Lanche");
            System.out.println("3. Vender Lanche (Baixa no Estoque)");
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
        if (cinema.getProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n Lista de Produtos");
        for (Produto p : cinema.getProdutos()) {
            System.out.println(p.exibirDetalhes());
        }
    }

    private void adicionarLanche() {
        String nome = Utilitarios.lerTexto("Nome do Lanche: ");
        double preco = Utilitarios.lerDouble("Preço: ");
        String tamanho = Utilitarios.lerTexto("Tamanho: ");
        int estoque = Utilitarios.lerInt("Estoque Inicial: ");

        Lanche l = new Lanche(nome, preco, tamanho, estoque);
        cinema.getProdutos().add(l);
        System.out.println("Lanche " + nome + " adicionado com sucesso!");
    }

    private void venderLanche() {
        if (cinema.getProdutos().isEmpty()) {
            System.out.println("Nenhum produto para vender.");
            return;
        }
        listar();
        String nomeLanche = Utilitarios.lerTexto("Digite o NOME do lanche para vender: ");
        int quantidade = Utilitarios.lerInt("Quantidade: ");

        for (Produto p : cinema.getProdutos()) {
            if (p instanceof Lanche && p.getNome().equalsIgnoreCase(nomeLanche)) {
                Lanche lanche = (Lanche) p;
                if (lanche.darBaixaEstoque(quantidade)) {
                    System.out.println("Venda de " + quantidade + "x " + nomeLanche + " realizada com sucesso!");
                    System.out.println("Novo estoque: " + lanche.getEmEstoque());
                } else {
                    System.out.println("ERRO: Não foi possível realizar a venda. Estoque insuficiente.");
                }
                return;
            }
        }
        System.out.println("Lanche não encontrado.");
    }
}
