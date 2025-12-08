package br.com.cinema.sistema;

import br.com.cinema.entidades.Pessoa;
import br.com.cinema.exceptions.CinemaException;
import java.util.List;

public class Login {
    private List<Pessoa> usuarios;

    public Login(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
    }

    public Pessoa autenticar(String cpf, String senha) throws CinemaException {
        for (Pessoa p : usuarios) {
            if (p.getCpf().equals(cpf) && p.getSenha().equals(senha)) {
                return p;
            }
        }
        throw new CinemaException("CPF ou Senha inválidos.");
    }
}