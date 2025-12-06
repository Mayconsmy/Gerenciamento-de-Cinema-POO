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

    private static void menuPessoas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n Gerenciar Pessoas");
            System.out.println("1. Listar Todas as Pessoas");
            System.out.println("2. Adicionar Novo Funcionário");
            System.out.println("3. Adicionar Novo Gerente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        gerenciadorPessoas.listarTodasAsPessoas();
                        break;
                    case 2:
                        adicionarFuncionario();
                        break;
                    case 3:
                        adicionarGerente();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private static void adicionarFuncionario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = Double.parseDouble(scanner.nextLine());

        Funcionario f = new Funcionario(nome, cpf, cargo, salario, senha);
        gerenciadorPessoas.adicionarPessoa(f);
        System.out.println("Funcionário " + nome + " adicionado com sucesso!");
    }

    private static void adicionarGerente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();

        Gerente g = new Gerente(nome, cpf, senha, departamento);
        gerenciadorPessoas.adicionarPessoa(g);
        System.out.println("Gerente " + nome + " adicionado com sucesso!");
    }
    private static void menuFilmesEventos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n Gerenciar Filmes e Eventos");
            System.out.println("1. Listar Todos");
            System.out.println("2. Adicionar Novo Filme");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        listarFilmesEventos();
                        break;
                    case 2:
                        adicionarFilme();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private static void listarFilmesEventos() {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme ou evento cadastrado.");
            return;
        }
        System.out.println("\n Lista de Filmes e Eventos ");
        for (Filme f : filmes) {
            f.exibirDetalhes();
            System.out.println();
        }
    }

    private static void adicionarFilme() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Duração (minutos): ");
        int duracao = Integer.parseInt(scanner.nextLine());
        System.out.print("Classificação Indicativa: ");
        String classificacao = scanner.nextLine();

        Filme f = new Filme(titulo, duracao, classificacao);
        filmes.add(f);
        System.out.println("Filme " + titulo + " adicionado com sucesso!");
    }

    private static void menuProdutos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n Gerenciar Produtos (Lanches)");
            System.out.println("1. Listar Todos os Produtos");
            System.out.println("2. Adicionar Novo Lanche");
            System.out.println("3. Vender Lanche (Baixa no Estoque)");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        listarProdutos();
                        break;
                    case 2:
                        adicionarLanche();
                        break;
                    case 3:
                        venderLanche();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n Lista de Produtos");
        for (Produto p : produtos) {
            System.out.println(p.exibirDetalhes());
        }
    }

    private static void adicionarLanche() {
        System.out.print("Nome do Lanche: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        System.out.print("Estoque Inicial: ");
        int estoque = Integer.parseInt(scanner.nextLine());

        Lanche l = new Lanche(nome, preco, tamanho, estoque);
        produtos.add(l);
        System.out.println("Lanche " + nome + " adicionado com sucesso!");
    }

    private static void venderLanche() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto para vender.");
            return;
        }
        listarProdutos();
        System.out.print("Digite o NOME do lanche para vender: ");
        String nomeLanche = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        for (Produto p : produtos) {
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

    