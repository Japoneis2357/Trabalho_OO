package services;

import models.Usuario;
import state.Dados;

import java.util.ArrayList;

public class UsuarioService {
    public void login(String email, String senha) throws Exception {
        for (Usuario usr: Dados.getUsuarios()) {
            if (usr.getEmail().equals(email)) {
                if (usr.getSenha().equals(senha)) {
                    Dados.setUsuarioLogado(usr);
                    return;
                } else {
                    throw new Exception("E-mail e/ou senha inválido(s)!");
                }
            }
        }

        throw new Exception("E-mail e/ou senha inválido(s)!");
    }

    public void logout() {
        Dados.setUsuarioLogado(null);
    }

    public Usuario getUsuarioLogado() {
        return Dados.getUsuarioLogado();
    }

    public ArrayList<Usuario> getUsuarios() { return Dados.getUsuarios(); }

    public void inserir(Usuario usuario) {
        usuario.setSenha("usu@rio123");
        usuario.setPrimeiroLogin(true);
        Dados.getUsuarios().add(usuario);
    }

    public Usuario getUsuarioPorEmail(String email) {
        for (Usuario usr: Dados.getUsuarios()) {
            if (usr.getEmail().equals(email)) {
                return usr;
            }
        }

        return null;
    }

    public void alterar(Usuario usuarioAlterado) throws Exception {
        Usuario usuario = getUsuarioPorEmail(usuarioAlterado.getEmail());

        if (usuario == null)
            throw  new Exception("Não é possível alterar. Usuário não encontrado.");

        usuario.setNome(usuarioAlterado.getNome());
        usuario.setTelefone(usuarioAlterado.getTelefone());
    }

    public void excluir(String email) throws Exception {
        Usuario usuario = getUsuarioPorEmail(email);

        if (usuario == null)
            throw  new Exception("Não é possível excluir. Usuário não encontrado.");

        Dados.getUsuarios().remove(usuario);
    }
}
