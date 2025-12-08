package br.com.cinema.entidades;

public class Avaliacao {
    private static int proximoId = 1;
    private int id;
    private String nomeUsuario;
    private Filme filme;
    private String estrelas;
    private String comentario;

    public Avaliacao(String nomeUsuario, Filme filme, String estrelas, String comentario) {
        this.id = proximoId++;
        this.nomeUsuario = nomeUsuario;
        this.filme = filme;
        this.estrelas = estrelas;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public String getEstrelas() {
        return estrelas;
    }

    public String getComentario() {
        return comentario;
    }

    public String exibirDetalhes() {
        return String.format("ID: %d | Usuário: %s | Filme: %s | Estrelas: %s | Comentário: %s",
                id, nomeUsuario, filme.getTitulo(), estrelas, comentario);
    }

    public static boolean validarEstrelas(String estrelas) {
        return estrelas.matches("\\*+") && estrelas.length() >= 1 && estrelas.length() <= 5;
    }
}
