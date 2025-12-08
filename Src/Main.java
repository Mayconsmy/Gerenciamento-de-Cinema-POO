import br.com.cinema.entidades.Filme;
import br.com.cinema.entidades.Funcionario;
import br.com.cinema.entidades.Gerente;
import br.com.cinema.entidades.Lanche;
import br.com.cinema.entidades.SalaExibicao;
import br.com.cinema.gerenciadores.GerenciadorFilme;
import br.com.cinema.gerenciadores.GerenciadorPessoa;
import br.com.cinema.gerenciadores.GerenciadorProduto;
import br.com.cinema.gerenciadores.GerenciadorSala;
import br.com.cinema.gerenciadores.GerenciadorAvaliacoes;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

public class Main {
    private static Cinema cinema;
    private static GerenciadorPessoa gerenciadorPessoa;
    private static GerenciadorFilme gerenciadorFilme;
    private static GerenciadorProduto gerenciadorProduto;
    private static GerenciadorSala gerenciadorSala;
    private static GerenciadorAvaliacoes gerenciadorAvaliacoes;

    public static void main(String[] args) {
        cinema = new Cinema();
        gerenciadorPessoa = new GerenciadorPessoa(cinema);
        gerenciadorFilme = new GerenciadorFilme(cinema);
        gerenciadorProduto = new GerenciadorProduto(cinema);
        gerenciadorSala = new GerenciadorSala(cinema);
        gerenciadorAvaliacoes = new GerenciadorAvaliacoes(cinema);

        inicializarDados();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = Utilitarios.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> gerenciadorPessoa.menu();
                case 2 -> gerenciadorFilme.menu();
                case 3 -> gerenciadorProduto.menu();
                case 4 -> gerenciadorSala.menu();
                case 5 -> gerenciadorAvaliacoes.menu(); 
                case 0 -> System.out.println("Saindo do sistema. Até mais!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void inicializarDados() {
        cinema.getPessoas().add(new Gerente("João Pessoa", "123.456.789-00", "senha123", "Administração"));
        cinema.getPessoas().add(new Funcionario("Bruno Borges", "987.654.321-00", "Atendente de Bilheteria", 1500.00, "senha456"));

        cinema.getFilmes().add(new Filme("O Grande Lebowski", 117, "14 anos"));
        cinema.getFilmes().add(new Filme("Harakiri", 133, "16 anos"));

        cinema.getProdutos().add(new Lanche("Pipoca Grande", 25.00, "Grande", 50));
        cinema.getProdutos().add(new Lanche("Refrigerante", 10.00, "Médio", 100));

        cinema.getSalas().add(new SalaExibicao(1, 100, true));
        cinema.getSalas().add(new SalaExibicao(2, 100, false));
    }

    private static void exibirMenuPrincipal() {
        System.out.println();
        System.out.println(" SISTEMA DE GERENCIAMENTO DE CINEMA");
        System.out.println();
        System.out.println("1. Gerenciar Pessoas (Funcionários/Gerentes)");
        System.out.println("2. Gerenciar Filmes e Eventos");
        System.out.println("3. Gerenciar Produtos (Lanches)");
        System.out.println("4. Gerenciar Salas de Exibição");
        System.out.println("5. Gerenciar Avaliações");
        System.out.println("0. Sair");
    }
}