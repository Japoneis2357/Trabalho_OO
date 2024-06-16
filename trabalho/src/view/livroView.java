package view;

import models.*;
import services.LivroService;

import java.util.Scanner;

public class livroView {
    public static void apresentarMenu() {
        System.out.println("Menu de livros (escolha uma opção):");
        System.out.println(" 1 - Listar");
        System.out.println(" 2 - Inserir");
        System.out.println(" 3 - Alterar");
        System.out.println(" 4 - Excluir");
        System.out.println(" 5 - Emprestar");

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
                    emprestar();
                    break;
                default:
                    continuar = true;
            }
        } while (continuar);
    }

    private static void listar() {
        LivroService servico = new LivroService();

        System.out.println("\nLista de Livros do Sistema:");

        for (Livro usr: servico.getLivros()) {
            System.out.printf("%s - %s\n", usr.getNome(), usr.getStatus());
        }

        System.out.println("-----------------------------\n");
    }

    private static void inserir() {
        System.out.println("\nInserindo Livro:");
        Livro livro = new Livro();
        lerLivro(livro);
        LivroService servico = new LivroService();
        servico.inserir(livro);
    }

    private static void lerLivro(Livro livro) {
        Scanner sc = new Scanner(System.in);

        System.out.print("  Nome: ");
        livro.setNome(sc.nextLine());
        livro.setStatus("Disponível");
    }

    private static void alterar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do livro a ser alterado(a): ");
        String nome = sc.nextLine();
        LivroService servico = new LivroService();

        Livro livro = servico.getLivroporNome(nome);

        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        System.out.print("  Nome: ");
        livro.setNome(sc.nextLine());
        System.out.print("  Status: ");
        livro.setStatus(sc.nextLine());

        try {
            servico.alterar(livro);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void excluir() {
        Scanner sc = new Scanner(System.in);

        System.out.print("nome do Livro a ser excluído: ");
        String nome = sc.nextLine();
        LivroService servico = new LivroService();

        Livro livro = servico.getLivroporNome(nome);

        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        try {
            servico.excluir(livro.getNome());
            System.out.println("Livro excluído(a) com sucesso!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static void emprestar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("nome do Livro a ser emprestado: ");
        String nome = sc.nextLine();
        LivroService servico = new LivroService();

        Livro livro = servico.getLivroporNome(nome);

        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;

        }
        if (livro.getStatus().equals("emprestado"))
        {
            System.out.println("Livro indisponível");
            return;
        }
        try {
            servico.emprestar(livro.getNome());
            System.out.println("Livro emprestado com sucesso!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
