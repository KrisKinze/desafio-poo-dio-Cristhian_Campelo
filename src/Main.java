import br.com.dio.desafio.dominio.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final List<Bootcamp> todosOsBootcamps = new ArrayList<>();
    private static final List<Dev> todosOsDevs = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Random random = new Random();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1: gerenciarBootcamps(); break;
                    case 2: gerenciarDevs(); break;
                    case 0: System.out.println("Encerrando o programa..."); break;
                    default: System.out.println("Opção inválida!"); break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Por favor, digite um número válido.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n========= MENU PRINCIPAL =========");
        System.out.println("1. Gerenciar Bootcamps");
        System.out.println("2. Gerenciar Devs");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void gerenciarBootcamps() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Bootcamps ---");
            System.out.println("1. Criar Bootcamp Manualmente");
            System.out.println("2. Criar Bootcamp Aleatoriamente");
            System.out.println("3. Adicionar Conteúdo a um Bootcamp");
            System.out.println("4. Ver Ranking de um Bootcamp");
            System.out.println("5. Listar todos os Bootcamps");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarBootcampManual();
                    break;
                case 2:
                    gerarBootcampAleatorio();
                    break;
                case 3:
                    adicionarConteudoAoBootcamp();
                    break;
                case 4:
                    Bootcamp bootcampRanking = selecionarBootcamp();
                    if (bootcampRanking == null) break;
                    System.out.println("\n--- RANKING DO BOOTCAMP: " + bootcampRanking.getNome() + " ---");
                    List<Dev> ranking = bootcampRanking.getRanking();
                    if (ranking.isEmpty()) {
                        System.out.println("Nenhum dev inscrito ou ninguém progrediu ainda.");
                    } else {
                        for (int i = 0; i < ranking.size(); i++) {
                            Dev dev = ranking.get(i);
                            System.out.printf("%dº Lugar: %s - %.2f XP%n", (i + 1), dev.getNome(), dev.calcularTotalXp());
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n--- Bootcamps Criados ---");
                    if (todosOsBootcamps.isEmpty()) {
                        System.out.println("Nenhum bootcamp foi criado.");
                    } else {
                        for (Bootcamp b : todosOsBootcamps) {
                            System.out.println("- " + b.getNome() + " (Conteúdos: " + b.getConteudos().size() + ", Devs: " + b.getDevsInscritos().size() + ")");
                        }
                    }
                    break;
                case 0: break;
                default: System.out.println("Opção inválida."); break;
            }
        }
    }

    private static void criarBootcampManual() {
        System.out.print("Nome do Bootcamp: ");
        String nomeBootcamp = scanner.nextLine();
        System.out.print("Descrição do Bootcamp: ");
        String descBootcamp = scanner.nextLine();
        Bootcamp novoBootcamp = new Bootcamp();
        novoBootcamp.setNome(nomeBootcamp);
        novoBootcamp.setDescricao(descBootcamp);
        todosOsBootcamps.add(novoBootcamp);
        System.out.println("Bootcamp '" + nomeBootcamp + "' criado com sucesso!");

        System.out.print("Deseja adicionar conteúdos a este bootcamp agora? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            adicionarConteudoAoBootcamp(novoBootcamp);
        }
    }

    private static void adicionarConteudoAoBootcamp() {
        Bootcamp bootcampEscolhido = selecionarBootcamp();
        if (bootcampEscolhido != null) {
            adicionarConteudoAoBootcamp(bootcampEscolhido);
        }
    }

    private static void adicionarConteudoAoBootcamp(Bootcamp bootcamp) {
        String continuar;
        do {
            System.out.println("\n--- Adicionar Conteúdo ao Bootcamp: " + bootcamp.getNome() + " ---");
            System.out.println("1. Criar e adicionar novo Curso");
            System.out.println("2. Criar e adicionar nova Mentoria");
            System.out.print("Escolha o tipo de conteúdo: ");
            int tipoConteudo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            Conteudo novoConteudo = null;
            if (tipoConteudo == 1) {
                System.out.print("Carga Horária: ");
                int cargaHoraria = scanner.nextInt();
                scanner.nextLine();
                Curso novoCurso = new Curso();
                novoCurso.setTitulo(titulo);
                novoCurso.setDescricao(descricao);
                novoCurso.setCargaHoraria(cargaHoraria);
                novoConteudo = novoCurso;
            } else if (tipoConteudo == 2) {
                try {
                    System.out.print("Data da Mentoria (dd/MM/yyyy): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine(), formatter);
                    Mentoria novaMentoria = new Mentoria();
                    novaMentoria.setTitulo(titulo);
                    novaMentoria.setDescricao(descricao);
                    novaMentoria.setData(data);
                    novoConteudo = novaMentoria;
                } catch (DateTimeParseException e) {
                    System.err.println("Formato de data inválido. Use dd/MM/yyyy.");
                }
            } else {
                System.out.println("Opção inválida.");
            }

            if (novoConteudo != null) {
                bootcamp.getConteudos().add(novoConteudo);
                System.out.println("Conteúdo '" + novoConteudo.getTitulo() + "' adicionado com sucesso!");
            }
            System.out.print("Deseja adicionar outro conteúdo a este Bootcamp? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }

    private static void gerenciarDevs() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Devs ---");
            System.out.println("1. Criar Dev Manualmente");
            System.out.println("2. Criar Dev Aleatoriamente");
            System.out.println("3. Progredir Dev");
            System.out.println("4. Ver Status de um Dev");
            System.out.println("5. Listar todos os Devs");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarDevManual();
                    break;
                case 2:
                    gerarDevAleatorio();
                    break;
                case 3:
                    Dev devParaProgredir = selecionarDev();
                    if (devParaProgredir != null) devParaProgredir.progredir();
                    break;
                case 4:
                    Dev devStatus = selecionarDev();
                    if (devStatus != null) {
                        System.out.println("\n--- STATUS: " + devStatus.getNome() + " ---");
                        System.out.println("XP Total: " + devStatus.calcularTotalXp());
                        System.out.println("Conteúdos Concluídos: " + devStatus.getConteudosConcluidos());
                        System.out.println("Conteúdos Pendentes: " + devStatus.getConteudosInscritos());
                    }
                    break;
                case 5:
                    System.out.println("\n--- Devs Criados ---");
                    if (todosOsDevs.isEmpty()) {
                        System.out.println("Nenhum dev foi criado.");
                    } else {
                        for (Dev d : todosOsDevs) {
                            System.out.println("- " + d.getNome() + " (XP: " + d.calcularTotalXp() + ")");
                        }
                    }
                    break;
                case 0: break;
                default: System.out.println("Opção inválida."); break;
            }
        }
    }

    private static void criarDevManual() {
        System.out.print("Nome do Dev: ");
        String nomeDev = scanner.nextLine();
        Dev novoDev = new Dev();
        novoDev.setNome(nomeDev);
        todosOsDevs.add(novoDev);
        System.out.println("Dev '" + nomeDev + "' criado com sucesso!");

        if (!todosOsBootcamps.isEmpty()) {
            System.out.print("Deseja inscrever este Dev em um Bootcamp agora? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                Bootcamp bootcampEscolhido = selecionarBootcamp();
                if (bootcampEscolhido != null) {
                    novoDev.inscreverBootcamp(bootcampEscolhido);
                }
            }
        }
    }

    private static void gerarBootcampAleatorio() {
        String[] areas = {"Java", "Python", "Full-Stack", "Data Science", "Cloud AWS", "DevOps", "Frontend"};
        String nomeBootcamp = "Bootcamp " + areas[random.nextInt(areas.length)];

        Bootcamp novoBootcamp = new Bootcamp();
        novoBootcamp.setNome(nomeBootcamp);
        novoBootcamp.setDescricao("Descrição completa do " + nomeBootcamp);

        for (int i = 0; i < 4; i++) {
            if (random.nextBoolean()) {
                novoBootcamp.getConteudos().add(gerarCursoAleatorio());
            } else {
                novoBootcamp.getConteudos().add(gerarMentoriaAleatoria());
            }
        }
        todosOsBootcamps.add(novoBootcamp);
        System.out.println("Bootcamp aleatório '" + nomeBootcamp + "' com 4 conteúdos foi criado!");
    }

    private static Curso gerarCursoAleatorio() {
        String[] temas = {"Algoritmos", "Spring Boot", "React", "SQL", "NoSQL", "Microsserviços"};
        Curso curso = new Curso();
        curso.setTitulo("Curso de " + temas[random.nextInt(temas.length)]);
        curso.setDescricao("Descrição do curso.");
        curso.setCargaHoraria(random.nextInt(10) + 2);
        return curso;
    }

    private static Mentoria gerarMentoriaAleatoria() {
        String[] temas = {"Carreira", "Projeto Prático", "Clean Code", "Arquitetura"};
        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("Mentoria sobre " + temas[random.nextInt(temas.length)]);
        mentoria.setDescricao("Descrição da mentoria.");
        mentoria.setData(LocalDate.now().plusDays(random.nextInt(30)));
        return mentoria;
    }

    private static void gerarDevAleatorio() {
        if (todosOsBootcamps.isEmpty()) {
            System.err.println("ERRO: Crie pelo menos um Bootcamp antes de gerar um Dev aleatório.");
            return;
        }
        String[] nomes = {"Ana", "Bruno", "Carla", "Daniel", "Elisa", "Fábio", "Gisele", "Hugo"};
        String nomeDev = nomes[random.nextInt(nomes.length)] + " " + (random.nextInt(90) + 10);

        Dev novoDev = new Dev();
        novoDev.setNome(nomeDev);
        todosOsDevs.add(novoDev);

        Bootcamp bootcampAleatorio = todosOsBootcamps.get(random.nextInt(todosOsBootcamps.size()));
        novoDev.inscreverBootcamp(bootcampAleatorio);

        int progresso = random.nextInt(bootcampAleatorio.getConteudos().size() + 1);
        for (int i = 0; i < progresso; i++) {
            novoDev.progredir();
        }
        System.out.println("Dev aleatório '" + nomeDev + "' criado, inscrito no '" + bootcampAleatorio.getNome() + "' e progrediu " + progresso + " vez(es).");
    }

    private static Bootcamp selecionarBootcamp() {
        if (todosOsBootcamps.isEmpty()) {
            System.out.println("Nenhum bootcamp foi criado ainda.");
            return null;
        }
        System.out.println("Selecione o Bootcamp:");
        for (int i = 0; i < todosOsBootcamps.size(); i++) {
            System.out.println(i + " - " + todosOsBootcamps.get(i).getNome());
        }
        System.out.println(todosOsBootcamps.size() + " - Nenhum/Cancelar"); 
        System.out.print("Escolha uma opção: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < todosOsBootcamps.size()) {
            return todosOsBootcamps.get(index);
        }
        return null; 
    }

    private static Dev selecionarDev() {
        if (todosOsDevs.isEmpty()) {
            System.out.println("Nenhum dev foi criado ainda.");
            return null;
        }
        System.out.println("Selecione o Dev:");
        for (int i = 0; i < todosOsDevs.size(); i++) {
            System.out.println(i + " - " + todosOsDevs.get(i).getNome());
        }
        System.out.print("Escolha uma opção: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index >= 0 && index < todosOsDevs.size()) {
            return todosOsDevs.get(index);
        }
        System.out.println("Índice inválido.");
        return null;
    }
}