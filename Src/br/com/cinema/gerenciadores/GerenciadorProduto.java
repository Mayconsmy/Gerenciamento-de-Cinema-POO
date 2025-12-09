package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.Lanche;
import br.com.cinema.entidades.Produto;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;
import br.com.cinema.exceptions.EstoqueException;

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
            System.out.println("4. Remover Produto");
            System.out.println("5. Buscar Produto");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionarLanche();
                case 3 -> venderLanche();
                case 4 -> removerProduto();
                case 5 -> buscarProduto();
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
                
                // TRATAMENTO DE EXCEÇÃO APLICADO
                try {
                    lanche.darBaixaEstoque(quantidade);
                    System.out.println("Venda de " + quantidade + "x " + nomeLanche + " realizada com sucesso!");
                    System.out.println("Novo estoque: " + lanche.getEmEstoque());
                } catch (EstoqueException e) {
                    System.out.println("ERRO NA VENDA: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Lanche não encontrado.");
    }

    private Produto buscarProduto(String nome) {
        return cinema.getProdutos().stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    private void removerProduto() {
        String nome = Utilitarios.lerTexto("Nome do Produto para remover: ");
        Produto produto = buscarProduto(nome);

        if (produto == null) {
            System.out.println("ERRO: Produto não encontrado.");
            return;
        }

        cinema.getProdutos().remove(produto);
        System.out.println("Produto '" + nome + "' removido com sucesso!");
    }

    private void buscarProduto() {
        String nome = Utilitarios.lerTexto("Nome do Produto para buscar: ");
        Produto produto = buscarProduto(nome);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
        } else {
            System.out.println("Produto encontrado:");
            System.out.println(produto.exibirDetalhes());
        }
    }
}