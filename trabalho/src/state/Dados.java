package state;

import models.*;

import java.util.ArrayList;

public class Dados {
    public static ArrayList<Usuario> getUsuarios() {
        if (usuarios.isEmpty()) {
            admin adm = new admin();
            adm.setNome("Admin");
            adm.setEmail("admin@email.com");
            adm.setSenha("@dmin123");
            usuarios.add(adm);
            padrao();
        }

        return usuarios;
    }

    public static void padrao() {
        admin adm = new admin();
        adm.setNome("Admin");
        adm.setEmail("a");
        adm.setSenha("a");
        adm.setPrimeiroLogin(false);
        usuarios.add(adm);
    }

    public static ArrayList<Livro> getLivros() {
        return livro;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Dados.usuarioLogado = usuarioLogado;
    }

    private static Usuario usuarioLogado = null;

    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static ArrayList<Livro> livro = new ArrayList<Livro>();
}
