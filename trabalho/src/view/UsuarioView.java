package view;
import models.Atendente;
import models.admin;
import models.Usuario;
import services.UsuarioService;
import java.util.Scanner;

public class UsuarioView {
    public static void login() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Login:");
            System.out.print("   E-mail: ");
            String email = sc.nextLine();
            System.out.print("   Senha: ");
            String senha = sc.nextLine();

            UsuarioService servico = new UsuarioService();

            try {
                servico.login(email, senha);
                if (servico.getUsuarioLogado().isPrimeiroLogin()) {
                    trocarSenha(servico.getUsuarioLogado());
                    logout();
                }
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void logout() {
        UsuarioService servico = new UsuarioService();
        servico.logout();
        System.out.println("Usuário desconectado com sucesso!");
    }

    public static void trocarSenha(Usuario usuario) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.printf("É necessário trocar a senha do usuário %s:\n", usuario.getNome());

            System.out.print("Senha atual: ");
            String senhaAtual = sc.nextLine();

            System.out.print("Nova senha: ");
            String novaSenha = sc.nextLine();

            System.out.print("Nova senha - Confirmação: ");
            String novaSenhaConfirmacao = sc.nextLine();

            try {
                usuario.trocarSenha(senhaAtual, novaSenha, novaSenhaConfirmacao);
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void apresentarMenu() {
        System.out.println("Menu de usuários (escolha uma opção):");
        System.out.println(" 1 - Listar");
        System.out.println(" 2 - Inserir");
        System.out.println(" 3 - Alterar");
        System.out.println(" 4 - Excluir");
        System.out.println(" 5 - Resetar a Senha");
        System.out.print("Opção: ");

        boolean continuar;

        do {
            continuar = false;

            Scanner sc = new Scanner(System.in);
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    inserir();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    excluir();
                    break;
                case 5:
                    resetarSenha();
                    break;
                default:
                    continuar = true;
            }
        } while (continuar);
    }

    private static void resetarSenha() {
        Scanner sc = new Scanner(System.in);

        System.out.print("E-mail do(a) usuário(a) a ser alterado(a): ");
        String email = sc.nextLine();
        UsuarioService servico = new UsuarioService();

        Usuario usuario = servico.getUsuarioPorEmail(email);

        if (usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        usuario.setSenha("usu@rio123");
        usuario.setPrimeiroLogin(true);

        System.out.println("A senha foi alterada para 'usu@rio123' e deve ser trocada no próximo login.");
    }

    private static void listar() {
        UsuarioService servico = new UsuarioService();

        System.out.println("\nLista de Usuários do Sistema:");

        for (Usuario usr: servico.getUsuarios()) {
            System.out.printf("%s - %s - %s\n", usr.getNome(), usr.getEmail(), usr.getTelefone());
        }

        System.out.println("-----------------------------\n");
    }

    private static void inserir() {
        System.out.println("\nInserindo Usuário:");
        Usuario usuario = lerComBaseEmTipo();
        UsuarioService servico = new UsuarioService();
        servico.inserir(usuario);
    }

    private static Usuario lerComBaseEmTipo() {
        int tipo;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Tipo de usuário (1 - Atendente, 2 - Administrador(a): ");
            tipo = sc.nextInt();

            switch (tipo) {
                case 1:
                    Atendente atendente = new Atendente();
                    lerUsuario(atendente);
                    return atendente;
                case 2:
                    admin administrador = new admin();
                    lerUsuario(administrador);
                    return administrador;
                default:
                    System.out.println("Tipo de usuário inválido!");
            }
        }
    }

    private static void lerUsuario(Usuario usuario) {
        Scanner sc = new Scanner(System.in);

        System.out.print("  Nome: ");
        usuario.setNome(sc.nextLine());
        System.out.print("  E-mail: ");
        usuario.setEmail(sc.nextLine());
        System.out.print("  Telefone: ");
        usuario.setTelefone(sc.nextLine());
    }

    private static void alterar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("E-mail do(a) usuário(a) a ser alterado(a): ");
        String email = sc.nextLine();
        UsuarioService servico = new UsuarioService();

        Usuario usuario = servico.getUsuarioPorEmail(email);

        if (usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        System.out.print("  Nome: ");
        usuario.setNome(sc.nextLine());
        System.out.print("  Telefone: ");
        usuario.setTelefone(sc.nextLine());

        try {
            servico.alterar(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void excluir() {
        Scanner sc = new Scanner(System.in);

        System.out.print("E-mail do(a) usuário(a) a ser excluído(a): ");
        String email = sc.nextLine();
        UsuarioService servico = new UsuarioService();

        Usuario usuario = servico.getUsuarioPorEmail(email);

        if (usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        try {
            servico.excluir(usuario.getEmail());
            System.out.println("Usuário(a) excluído(a) com sucesso!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
