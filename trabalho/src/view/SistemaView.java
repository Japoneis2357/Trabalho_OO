package view;

import models.Atendente;
import models.admin;
import models.Usuario;
import services.UsuarioService;

import java.util.Scanner;

public class SistemaView {
    public static void iniciar() {
        UsuarioService servico = new UsuarioService();
        Usuario usuarioLogado;

        boolean continuar;

        do {
            usuarioLogado = servico.getUsuarioLogado();

            if (usuarioLogado == null) {
                UsuarioView.login();
            }

            usuarioLogado = servico.getUsuarioLogado();

            if (usuarioLogado != null) {
                continuar = apresentarMenu(usuarioLogado);
            } else {
                continuar = true;
            }
        } while (continuar);
    }

    private static boolean apresentarMenu(Usuario usuarioLogado) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Olá %s!\n", usuarioLogado.getNome());
        System.out.println("Menu (escolha a opção desejada):");
        System.out.println(" 0 - Sair");
        System.out.println(" 1 - Logout");

        if (usuarioLogado instanceof admin) {
            System.out.println(" 2 - livro");
            System.out.println(" 9 - Usuários");
        }
        if (usuarioLogado instanceof Atendente) {
            System.out.println(" 2 - livro");
        }

        System.out.print(" Opção: ");
        int opcao = sc.nextInt();

        while (true) {
            switch (opcao) {
                case 0:
                    return false;
                case 1:
                    UsuarioService servico = new UsuarioService();
                    servico.logout();
                    return true;
                case 2:
                    livroView.apresentarMenu();
                    return true;
                case 3:
                    UsuarioView.apresentarMenu();
                    return true;
                default:
                    System.out.println(" Opção inválida!");
                    return true;
            }
        }
    }
}
