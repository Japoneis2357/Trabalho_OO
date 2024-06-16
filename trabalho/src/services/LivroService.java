package services;

import models.Livro;
import state.Dados;

import java.util.ArrayList;

public class LivroService {
    public ArrayList<Livro> getLivros() {
        return Dados.getLivros();
    }

    public void inserir(Livro livro) {
        Dados.getLivros().add(livro);
    }

    public Livro getLivroporNome(String nome) {
        for (Livro livro : Dados.getLivros()) {
            if (livro.getNome().equals(nome)) {
                return livro;
            }
        }

        return null;
    }

    public void alterar(Livro livroAlterado) throws Exception {
        Livro livro = getLivroporNome(livroAlterado.getNome());

        if (livro == null)
            throw new Exception("Não é possível alterar. Livro não encontrado(a).");

        livro.setNome(livroAlterado.getNome());
        livro.setStatus(livroAlterado.getStatus());
    }

    public void excluir(String nome) throws Exception {
        Livro livro = getLivroporNome(nome);

        if (livro == null)
            throw new Exception("Não é possível excluir. Livro não encontrado(a).");

        Dados.getLivros().remove(livro);
    }

    public void emprestar(String nome) throws Exception {
        Livro livro = getLivroporNome(nome);

        if (livro == null)
            throw new Exception("Não é possível emprestar. Livro não encontrado(a).");
        if (livro.getStatus().equals("emprestado"))
            throw new Exception("Não é possível, já está emprestado.");
        livro.setStatus("emprestado");
    }
}