import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GerenciadorDePessoas {
    private List<Pessoa> pessoas;

    public GerenciadorDePessoas() {
        this.pessoas = new ArrayList<>();
    }

    public void adicionarPessoa(Pessoa p) {
        this.pessoas.add(p);
    }

    public void listarTodasAsPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }
        System.out.println("\n Lista de Pessoas Cadastradas ");
        for (Pessoa p : pessoas) {
            System.out.println(p.getDetalhesIdentificacao() + " | Cargo: " + p.getCargo());
        }
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorDePessoas gerenciadorPessoas = new GerenciadorDePessoas();
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static List<SalaExibicao> salas = new ArrayList<>();

    public static void main(String[] args) {
        inicializarDados();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        menuPessoas();
                        break;
                    case 2:
                        menuFilmesEventos();
                        break;
                    case 3:
                        menuProdutos();
                        break;
                    case 4:
                        menuSalas();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }
    private static void inicializarDados() {
        gerenciadorPessoas.adicionarPessoa(new Gerente("João Pessoa", "123.456.789-00", "senha123", "Administração"));
        gerenciadorPessoas.adicionarPessoa(new Funcionario("Bruno Borges", "987.654.321-00", "Atendente de Bilheteria", 1500.00, "senha456"));

        filmes.add(new Filme("O Grande Lebowski", 117, "14 anos"));
        filmes.add(new Filme("Harakiri", 133, "16 anos"));

        produtos.add(new Lanche("Pipoca Grande", 25.00, "Grande", 50));
        produtos.add(new Lanche("Refrigerante", 10.00, "Médio", 100));

        salas.add(new SalaExibicao(1, 150, true));
        salas.add(new SalaExibicao(2, 100, false));
    }
        private static void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("  SISTEMA DE GERENCIAMENTO DE CINEMA");
        System.out.println();
        System.out.println("1. Gerenciar Pessoas (Funcionários/Gerentes)");
        System.out.println("2. Gerenciar Filmes e Eventos");
        System.out.println("3. Gerenciar Produtos (Lanches)");
        System.out.println("4. Gerenciar Salas de Exibição");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
}