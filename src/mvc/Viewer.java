package src.mvc;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.util.regex.Pattern;

import src.qql.Utilizador;

public class Viewer {
    private Controller controller;
    private Scanner sc;
    private String currentUser;

    public Viewer(Controller c, Scanner sc) {
        this.controller = c;
        this.sc = sc;
    }

    public void run() {
        int option = showMenu();

        boolean success = this.loadState(option);
        if (!success) return;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

        LocalDateTime initialDate = LocalDateTime.now();

        System.out.println("\nIndique a data inicial da simulação");
        System.out.println("1. Data actual\n2. Data à escolha");
        System.out.print("> ");

        option = sc.nextInt();
        sc.nextLine();

        if (option == 2) { 
            System.out.println("Indique a data inicial pretendida (yyyy-MM-dd HH)");
            System.out.print("> ");
            String dataInicial = sc.nextLine();

            try {
                initialDate = LocalDateTime.parse(dataInicial, formatter);
                this.controller.changeDate(initialDate);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido!");
                return;
            }
        }

        this.controller.startSimulation();

        loadState(2);
        //showLoggedInMenu();
    }

    private int showMenu() {
        int option = 0;

        System.out.println("Como pretende carregar a informação?");
        System.out.println("\n1. Carregar de um ficheiro binário\n2. Menu");
        System.out.print("> ");

        try {
            option = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nIndique um número inteiro!\n");
            sc.nextLine(); // Consume the invalid input
            option = showMenu(); // Call the method recursively to get a valid input
        }
    
        return option;
    }

    private boolean loadState(int option) {
        String filename;
        switch (option) {
            case 1:
                System.out.println("Nome do ficheiro");
                System.out.print("> ");
                filename = "./input/" + this.sc.next();
                try {
                    this.controller.setUpReadFromBinaryFile(filename);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    return false;
                }

                break;

            case 2:
                do {
                    System.out.println("\n0. Terminar\n1. Adicionar utilizador\n2. Entrar com utilizador\n3. Adicionar transportadora\n4. Alterar valor de cálculo de transportadora");
                    System.out.print("> ");

                    try {
                        option = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nIndique um número inteiro!");
                        sc.nextLine();
                        loadState(option);
                    }

                    switch (option) {
                        case 0:
                            System.out.println("\nTerminado!");

                            System.out.println("Pretende guardar o estado actual em ficheiro? (S/N)");
                            System.out.print("> ");
                            String option_g = sc.nextLine();
                            while (!option_g.equals("S") && !option_g.equals("N")) {
                                System.out.println("(S/N)");
                                System.out.print("> ");
                                option_g = sc.nextLine();
                            }

                            if (option_g.equals("S")) {
                                try {
                                    this.controller.storeState(showStoreStateMenu());
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            break;
                        case 1:
                            showUtilizadorMenu();
                            break;
                        case 2:
                            showUtilizador2Menu();
                            break;
                        case 3:
                            showTransportadoraMenu();
                            break;
                        case 4:
                            showAlterarValorTransportadoraMenu();
                            break;
                    }

                } while (option != 0);
                break;
        }

    return true;
    }

    private void showUtilizadorMenu() {
        int optionU;
        do {
            System.out.println("\nIndique o email pretendido");
            System.out.print("> ");
            String email = sc.nextLine();

            System.out.println("Indique o nome pretendido");
            System.out.print("> ");
            String nome = sc.nextLine();

            System.out.println("Indique a morada pretendida");
            System.out.print("> ");
            String morada = sc.nextLine();

            System.out.println("Indique o nif");
            System.out.print("> ");
            String nif = sc.nextLine();

            String em;

            try {
                em = this.controller.addUtilizador(nome, email, morada, nif);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

            System.out.println("\nO utilizador " + em + " foi adicionado com sucesso.");
            currentUser = em;
            System.out.println("\n1. Adicionar mais um utilizador\n2. Entrar");
            System.out.print("> ");
            optionU = sc.nextInt();
            sc.nextLine();

        } while (optionU != 2);
        showLoggedInMenu();

            // showVendedorCompradorMenu();
    }

    private void showUtilizador2Menu() {
        System.out.println("Indique o email do utilizador");
        System.out.print("> ");
        String email = sc.nextLine();

        if (this.controller.checkUtilizadorExiste(email)) {
            currentUser = email;
            System.out.println("Sessão iniciada com utilizador " + email);
            showLoggedInMenu();
        }

        else {
            System.out.println("\nUtilizador não existe!");
        }
    }

    private void showTransportadoraMenu() {
        System.out.println("\nIndique o nome pretendido");
        System.out.print("> ");
        String nome = sc.nextLine();

        float imposto = 0.0f;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Indique o imposto pretendido");
            System.out.print("> ");
            
            try {
                imposto = sc.nextFloat();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Indique um valor numérico!\n");
                sc.nextLine(); 
            }
        }
        sc.nextLine(); 

        float valorPeq = 0.0f;
        validInput = false;
        while (!validInput) {
            System.out.println("Indique o valor de expedição de uma encomenda pequena pretendido");
            System.out.print("> ");
           
            try {
                valorPeq = sc.nextFloat();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Indique um valor numérico!\n");
                sc.nextLine(); 
            }
        }
        sc.nextLine(); 

        float valorMed = 0.0f;
        validInput = false;
        while (!validInput) {
            System.out.println("Indique o valor de expedição de uma encomenda média pretendido");
            System.out.print("> ");
            
            try {
                valorMed = sc.nextFloat();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Indique um valor numérico!\n");
                sc.nextLine(); 
            }
        }
        sc.nextLine(); 

        float valorGra = 0.0f;
        validInput = false;
        while (!validInput) {
            System.out.println("Indique o valor de expedição de uma encomenda grande pretendido");
            System.out.print("> ");
            try {
                valorGra = sc.nextFloat();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Indique um valor numérico!\n");
                sc.nextLine(); 
            }
        }
        sc.nextLine(); 

        float margemLucro = 0.0f;
        validInput = false;
        while (!validInput) {
            System.out.println("Indique a margem de lucro pretendida");
            System.out.print("> ");
            
            try {
                margemLucro = sc.nextFloat();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Indique um valor numérico!\n");
                sc.nextLine(); 
            }
        }
        sc.nextLine();

        String n;

        try {
            n = this.controller.addTransportadora(nome, imposto, valorPeq, valorMed, valorGra, margemLucro);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nA transportadora " + n + " foi adicionada com sucesso.");

    }

    private void showAlterarValorTransportadoraMenu() {
        if (this.controller.getNumTransportadoras() > 0) {
            System.out.println("Indique a transportadora que pretende alterar");

            this.controller.showTransportadoras();
            System.out.print("> ");
            String transportadora = sc.nextLine();
            while (!this.controller.checkTransportadoraExiste(transportadora)) {
                System.out.println("selecione uma das apresentadas!");
                this.controller.showTransportadoras();
                System.out.print("> ");
                transportadora = sc.nextLine();
            }

            System.out.println("0. Voltar\n1. Alterar valor base encomendas pequenas\n2. Alterar valor base encomendas médias\n3. Alterar valor base encomendas grandes\n4. Alterar margem de lucro\n5. Alterar imposto\n6. Alterar fórmula");
            
            System.out.print("> ");
            int option = sc.nextInt();
            sc.nextLine();
            float valor = 0.0f;

            switch (option) {
                case 0: 
                    break;
                case 1:
                    System.out.println("Indique o novo valor base para encomendas pequenas pretendido. Atual: " + this.controller.showValorTransportadora(transportadora, option));
                    System.out.print("> ");
                    valor = sc.nextFloat();
                    this.controller.alterarValorTransportadora(transportadora, option, valor);
                    System.out.println("\nValor atualizado com sucesso!");
                    break;
                case 2:
                    System.out.println("Indique o novo valor base para encomendas médias pretendido. Atual: " + this.controller.showValorTransportadora(transportadora, option));
                    System.out.print("> ");
                    valor = sc.nextFloat();
                    this.controller.alterarValorTransportadora(transportadora, option, valor);
                    System.out.println("\nValor atualizado com sucesso!");
                    break;
                case 3:
                    System.out.println("Indique o novo valor base para encomendas grandes pretendido. Atual: " + this.controller.showValorTransportadora(transportadora, option));
                    System.out.print("> ");
                    valor = sc.nextFloat();
                    this.controller.alterarValorTransportadora(transportadora, option, valor);
                    System.out.println("\nValor atualizado com sucesso!");
                    break;
                case 4:
                    System.out.println("Indique a nova margem de lucro pretendida. Atual: " + this.controller.showValorTransportadora(transportadora, option));
                    System.out.print("> ");
                    valor = sc.nextFloat();
                    this.controller.alterarValorTransportadora(transportadora, option, valor);
                    System.out.println("\nValor atualizado com sucesso!");
                    break;
                case 5:
                    System.out.println("Indique o novo imposto pretendido. Atual: " + this.controller.showValorTransportadora(transportadora, option));
                    System.out.print("> ");
                    valor = sc.nextFloat();
                    this.controller.alterarValorTransportadora(transportadora, option, valor);
                    System.out.println("\nValor atualizado com sucesso!");
                    break;
                case 6:
                    System.out.println("Indique a nova fórmula pretendida. Atual: " + this.controller.showFormulaTransportadora(transportadora));
                    System.out.print("> ");
                    String formula = sc.nextLine();

                    String pattern = "^[0-9+\\-*/().\\sVBMLTI]*$";
                    while (!Pattern.matches(pattern, formula)) {
                        System.out.println("Siga as regras das fórmulas!");
                        System.out.print("> ");
                        formula = sc.nextLine();
                    }

                    this.controller.alterarFormulaTransportadora(transportadora, formula);

                    System.out.println("\nFórmula alterada com sucesso!");
            }

        }

        else {
            System.out.println("\nDado não existirem transportadoras no sistema, não é possível alterar valores de cálculo! Tente mais tarde.");
        }
    }

    private void showLoggedInMenu() {
        int optionL;
        do {
            System.out.println("\n0. Voltar\n1. Ver e/ou comprar artigos\n2. Colocar artigo à venda\n3. Consultar e/ou finalizar encomenda\n4. Devolver encomenda\n5. Estatísticas\n6. Ver faturas\n7. Avançar no tempo");
            System.out.print("> ");

            optionL = sc.nextInt();
            sc.nextLine();

            switch (optionL) {
                case 0:
                    break;
                case 1:
                    showArtigosMenu();
                    break;
                case 2:
                    showVenderArtigoMenu();
                    break;
                case 3:
                    showEncomenda();
                    break;
                case 4:
                    showDevolverEncomenda();
                    break;
                case 5:
                    showEstatisticasMenu();
                    break;
                case 6:
                    showFaturasMenu();
                    break;
                case 7:
                    showAvancarMenu();
                    break;
            }
        } while (optionL != 0);
    }

    private void showFaturasMenu() {
        boolean hasFaturas = this.controller.checkUtilizadorTemFaturas(currentUser);

        if (!hasFaturas) {
            System.out.println("\nDe momento, o utilizador não tem faturas!");
        }

        else {
            String answer = this.controller.showFaturas(currentUser);
            System.out.println(answer);
        }
    }

    private void showAvancarMenu() {
        System.out.println("Quantas horas pretende avançar?");
        System.out.print("> ");
        int hours = sc.nextInt();

        this.controller.avancar(hours);
        this.controller.startSimulation();
    
        System.out.println("\nO tempo foi adiantado em " + hours + " horas!");
    }

    private void showVenderArtigoMenu() {
        if (this.controller.getNumTransportadoras() > 0) {
            System.out.println("\nIndique o nome do artigo");
            System.out.print("> ");
            String nome = sc.nextLine();

            System.out.println("Indique a descrição do artigo");
            System.out.print("> ");
            String descricao = sc.nextLine();

            System.out.println("Indique a marca do artigo");
            System.out.print("> ");
            String marca = sc.nextLine();

            System.out.println("Indique o código do artigo");
            System.out.print("> ");
            Integer codigo = sc.nextInt();
        
            System.out.println("Indique o preço base do artigo");
            System.out.print("> ");
            float precoBase = sc.nextFloat();

            System.out.println("Indique a correção de preço do artigo");
            System.out.print("> ");
            float correcaoPreco = sc.nextFloat();
     
            System.out.println("O artigo é novo ou usado? (N/U)");
            System.out.print("> ");
            String novo_tmp = sc.nextLine();
            System.out.print(novo_tmp);
            boolean novo;
            int numDonos;
            while (!novo_tmp.equals("U") && !novo_tmp.equals("N")) {
                System.out.println("(N/U)");
                System.out.print("> ");
                novo_tmp = sc.nextLine();
            }
            if (novo_tmp.equals("U")) {
                System.out.println("Indique o número de donos prévios");
                System.out.print("> ");
                numDonos = sc.nextInt();
                sc.nextLine();
                novo = false;
            }
            else {
                numDonos = 0;
                novo = true;
            }

            System.out.println("Indique a condição do artigo");
            System.out.print("> ");
            String condicao = sc.nextLine();

            System.out.println("Indique a transportadora que pretende utilizar (selecionar uma das apresentadas)");
            this.controller.showTransportadoras();
            System.out.print("> ");
            String transportadora = sc.nextLine();
            while (!this.controller.checkTransportadoraExiste(transportadora)) {
                System.out.println("selecione uma das apresentadas!");
                this.controller.showTransportadoras();
                System.out.print("> ");
                transportadora = sc.nextLine();
            }

            System.out.println("Indique o stock do artigo");
            System.out.print("> ");
            int stock = sc.nextInt();
            sc.nextLine();
            while (stock <= 0) {
                System.out.println("Indique um valor superior a 0!");
                System.out.print("> ");
                stock = sc.nextInt();
                sc.nextLine();
            }

            int optionA;
            do {
                System.out.println("Qual é o tipo do artigo?");
                System.out.println("\n1. Mala\n2. Sapatilha\n3. T-Shirt\n");
                System.out.print("> ");

                optionA = sc.nextInt();
                sc.nextLine();

                switch (optionA) {
                    case 1:
                        showAddMalaMenu(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock);
                        break;
                    case 2:
                        showAddSapatilhaMenu(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock);
                        break;
                    case 3:
                        showAddTShirtMenu(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock);
                        break;
                }
            } while (optionA != 1 && optionA != 2 && optionA != 3);
        }
        else {
            System.out.println("\nDado não existirem transportadoras no sistema, não é possível adicionar artigos! Tente mais tarde.");
        }
    }

    private void showAddMalaMenu(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock) {
        System.out.println("Indique a dimensão da mala");
        System.out.print("> ");
        float dimensao = sc.nextFloat();
        sc.nextLine(); 
        
        System.out.println("Indique o material da mala");
        System.out.print("> ");
        String material = sc.nextLine();

        System.out.println("Indique o ano de colecção da mala");
        System.out.print("> ");
        int anoColecao = sc.nextInt();

        System.out.println("A mala é premium? (S/N)");
        System.out.print("> ");
        String option_c = sc.nextLine();
        while (!option_c.equals("S") && !option_c.equals("N")) {
            System.out.println("(S/N)");
            System.out.print("> ");
            option_c = sc.nextLine();
        }
        boolean premium;
        if (option_c.equals("S")) {
            premium = true;
        }
        else {
            premium = false;
        }

        System.out.println("Indique a valorização da mala");
        System.out.print("> ");
        float valorizacao = sc.nextFloat();

        String n;

        try {
            n = this.controller.addMala(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, dimensao, material, anoColecao, premium, valorizacao, currentUser);
            if (n.equals("null")) {
                System.out.println("Já existe um artigo com o código " + codigo + "!\n");
            }
            else {
                System.out.println("\nA mala " + n + " foi adicionada com sucesso.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    private void showAddSapatilhaMenu(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock) {
        System.out.println("Indique o tamanho das sapatilhas");
        System.out.print("> ");
        int tamanho = sc.nextInt();
        sc.nextLine(); 
        
        System.out.println("As sapatilhas possuem atacadores? (S/N)");
        System.out.print("> ");
        String option_c = sc.nextLine();
        while (!option_c.equals("S") && !option_c.equals("N")) {
            System.out.println("(S/N)");
            System.out.print("> ");
            option_c = sc.nextLine();
        }
        boolean atacadores;
        if (option_c.equals("S")) {
            atacadores = true;
        }
        else {
            atacadores = false;
        }

        System.out.println("Indique a cor das sapatilhas");
        System.out.print("> ");
        String cor = sc.nextLine();

        System.out.println("Indique o ano de lançamento das sapatilhas");
        System.out.print("> ");
        int anoLancamento = sc.nextInt();
        sc.nextLine(); 
        
        System.out.println("As sapatilhas são premium? (S/N)");
        System.out.print("> ");
        String option_c2 = sc.nextLine();
        while (!option_c2.equals("S") && !option_c2.equals("N")) {
            System.out.println("(S/N)");
            System.out.print("> ");
            option_c2 = sc.nextLine();
        }
        boolean premium;
        if (option_c2.equals("S")) {
            premium = true;
        }
        else {
            premium = false;
        }

        String n;

        try {
            n = this.controller.addSapatilha(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, tamanho, atacadores, cor, anoLancamento, premium, currentUser);
            if (n.equals("null")) {
                System.out.println("Já existe um artigo com o código " + codigo + "!\n");
            }
            else {
                System.out.println("\nAs sapatilhas " + n + " foram adicionadas com sucesso.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void showAddTShirtMenu(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock) {
        System.out.println("Indique o tamanho da t-shirt (S/M/L/XL)");
        System.out.print("> ");
        String tamanho = sc.nextLine();
        while (!tamanho.equals("S") && !tamanho.equals("M") && !tamanho.equals("L") && !tamanho.equals("XL")) {
            System.out.println("(S/M/L/XL)");
            System.out.print("> ");
            tamanho = sc.nextLine();
        }


        System.out.println("Indique o padrão da t-shirt (LISO/RISCAS/PALMEIRAS)");
        System.out.print("> ");
        String padrao = sc.nextLine();
        while (!padrao.equals("LISO") && !padrao.equals("RISCAS") && !padrao.equals("PALMEIRAS")) {
            System.out.println("(LISO/RISCAS/PALMEIRAS))");
            System.out.print("> ");
            padrao = sc.nextLine();
        }

        System.out.println("Indique o desconto da t-shirt");
        System.out.print("> ");
        int desconto = sc.nextInt();
        sc.nextLine();


        String n;

        try {
            n = this.controller.addTShirt(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, tamanho, padrao, desconto, currentUser);
            if (n.equals("null")) {
                System.out.println("Já existe um artigo com o código " + codigo + "!\n");
            }
            else {
                System.out.println("\nA T-Shirt " + n + " foi adicionada com sucesso.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void showArtigosMenu() {
        if (this.controller.getNumArtigos() > 0) {
            System.out.print(this.controller.showArtigos());

            System.out.println("\nDeseja comprar algum dos artigos? (S/N)");
            System.out.print("> ");
            String option_c = sc.nextLine();
            while (!option_c.equals("S") && !option_c.equals("N")) {
                System.out.println("(S/N)");
                System.out.print("> ");
                option_c = sc.nextLine();
            }

            if (option_c.equals("S")) {
                System.out.println("Qual o código do artigo desejado?");
                System.out.print("> ");
                Integer option_codigo = sc.nextInt();

                System.out.println("Qual o número de stock pretendido?");
                System.out.print("> ");
                Integer option_stock = sc.nextInt();

                if (this.controller.checkEncomendaVazia(currentUser)) {
                    this.controller.initEncomendaUser(currentUser);
                }

                Integer r = this.controller.addArtigoEncomenda(option_codigo, option_stock, currentUser);
                if (r == 0) {
                    System.out.println("\nArtigo não existe!");
                }

                else if (r == -1) {
                    System.out.println("\nStock insuficiente!");
                }

                else {
                    System.out.println("\nArtigo com o código " + option_codigo  + " adicionado com sucesso!");
                }
            }
        }

        else {
            System.out.println("\nNenhum artigo disponível de momento!");
        }
    }

    private void showDevolverEncomenda() {
        boolean hasEncomendas = this.controller.checkUtilizadorEncADevolver(currentUser);
        
        if (!hasEncomendas) {
            System.out.println("\nDe momento, não há encomendas disponíveis para serem devolvidas!");
        }
        else {
            String answer = this.controller.showEncomendasFeitas(currentUser);
            System.out.println(answer);

            System.out.println("\nDeseja devolver alguma das encomendas? (S/N)");
            System.out.print("> ");
            String option_d = sc.nextLine();
            while (!option_d.equals("S") && !option_d.equals("N")) {
                System.out.println("(S/N)");
                System.out.print("> ");
                option_d = sc.nextLine();
            }

            if (option_d.equals("S")) {
                System.out.println("Qual o código da encomenda que deseja devolver?");
                System.out.print("> ");
                Integer option_codigo = sc.nextInt();

                if (!this.controller.checkUtilizadorTemEncADevolver(option_codigo, currentUser)) {
                    System.out.println("\nNenhuma encomenda com esse código!");
                }

                else {
                    this.controller.devolverEncomenda(currentUser, option_codigo);
                    System.out.println("A encomenda foi devolvida com sucesso!") ;                  
                }
            }
        }
    }

    private void showEncomenda() {
        String answer = this.controller.showEncomenda(currentUser);
        System.out.println(answer);

        if (!answer.equals("\nSem artigos na encomenda!")) {
            System.out.println("0. Voltar\n1. Finalizar encomenda\n2. Remover artigo da encomenda");
            System.out.print("> ");

            int option_e = sc.nextInt();
            sc.nextLine();

            switch (option_e) {
                case 0:
                    showLoggedInMenu();
                    break;
                case 1:
                    float preco = this.controller.calcularPrecoFinalEncomenda(currentUser);
                    System.out.println("O preço final é " + preco);
                    System.out.println("Deseja prosseguir com a compra? (S/N)");
                    System.out.print("> ");
                    String option_cc = sc.nextLine();
                    while (!option_cc.equals("S") && !option_cc.equals("N")) {
                        System.out.println("(S/N)");
                        System.out.print("> ");
                        option_cc = sc.nextLine();
                    }

                    if (option_cc.equals("S")) {
                        // a acabar
                        this.controller.finalizarCompra(currentUser);
                        System.out.println("\nCompra concluída!");
                    }

                    else {
                        break;
                    }

                    break;
                case 2:
                    System.out.println("Qual o código do artigo que pretende remover?");
                    System.out.print("> ");
                    Integer option_codigo = sc.nextInt();

                    Integer r = this.controller.removeArtigoEncomenda(option_codigo, currentUser);
                    if (r == null) {
                        System.out.println("\nArtigo com o código " + option_codigo + " não está na encomenda!");
                    }
                    else {
                        this.controller.addArtigoEncomenda2(option_codigo);
                        System.out.println("\nArtigo com o código " + option_codigo + " removido da encomenda!");
                    }
                    break;
            }
        }
    }

    private void showEstatisticasMenu() {
        System.out.println("\n0. Voltar\n1. O vendedor que mais facturou num período ou desde sempre\n2. A transportadora com o maior volume de facturação\n3. Lista de encomendas emitidas por um vendedor\n4. Lista ordenada dos maiores compradores/vendedores do sistema durante um período de tempo\n5. O dinheiro ganho pela Vintage");
        System.out.print("> ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 0:
                showLoggedInMenu();
                break;
            case 1:
                System.out.println("\n1. Desde sempre\n2. Num período de tempo");
                System.out.print("> ");
                int option44 = sc.nextInt();
                sc.nextLine();

                if (option44 == 1) {
                        String nome = this.controller.getMaiorVendedor();
                        float valor = this.controller.getMaiorVendedorValor(nome);

                        if (valor == 0.0f) {
                        System.out.println("\nAinda não houve vendas na Vintage, pelo que não é possível calcular o vendedor que mais facturou!");
                        }
                        else {
                            System.out.println("\nO vendedor que mais facturou foi o " + nome + " e facturou um total de " + valor + "€!");
                        }
                }
                
                else if (option44 == 2) {
                        System.out.println("Indique a data inicial pretendida (yyyy-MM-dd HH)");
                        System.out.print("> ");
                        String date = sc.nextLine();
                        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
                        LocalDateTime date1 = LocalDateTime.parse(date, dateformatter);
                        System.out.println("Indique a data final pretendida (yyyy-MM-dd HH)");
                        System.out.print("> ");
                        date = sc.nextLine();
                        LocalDateTime date2 = LocalDateTime.parse(date, dateformatter);
                        Boolean booldate = date2.isAfter(date1);
                
                        if (booldate == false) {
                            System.out.println("\nAs datas dadas são inválidas");
                        }
                
                        else {
                            String nome3 = this.controller.getMaiorVendedorPeriodo(date1, date2);
                            float valor3 = this.controller.getMaiorVendedorValorPeriodo(nome3, date1, date2);
                        
                            if (valor3 == 0.0f) {
                            System.out.println("\nNão houve vendas na Vintage nesse período de tempo, pelo que não é possível calcular o vendedor que mais facturou!");
                            }
                            else {
                                System.out.println("\nO vendedor que mais facturou foi o " + nome3 + " e facturou um total de " + valor3 + "€!");
                            }
                        }
                    }

                break;
            case 2:
                String nome2 = this.controller.getMaiorTransportadora();
                float valor2 = this.controller.getMaiorTransportadoraValor(nome2);

                if (valor2 == 0.0f) {
                    System.out.println("\nAinda não houve vendas na Vintage, pelo que não é possível calcular a transportadora que mais facturou!");
                }
                else {
                    System.out.println("\nA transportadora que mais facturou foi a " + nome2 + " e facturou um total de " + valor2 + "€!");
                }

                break;
            case 3:
                System.out.println("Indique o nome do vendedor pretendido");
                System.out.print("> ");
                String user = sc.nextLine();

                boolean check = this.controller.checkUtilizadorExiste(user);
                if (!check) {
                    System.out.println("\nNão existe um vendedor com o nome indicado!");
                }
                else {
                    String resultado = this.controller.getVendasUtilizador(user);
                    
                    if (!resultado.isEmpty()) {
                        System.out.print("\n");
                        System.out.println(resultado);
                    }
                    else {
                        System.out.println("\nO vendedor indicado ainda não tem nenhuma venda!");
                    }
                }
                break;
            case 4:
                System.out.println("Indique a data inicial pretendida (yyyy-MM-dd HH)");
                System.out.print("> ");
                String date = sc.nextLine();
                DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
                LocalDateTime date1 = LocalDateTime.parse(date, dateformatter);
                System.out.println("Indique a data final pretendida (yyyy-MM-dd HH)");
                System.out.print("> ");
                date = sc.nextLine();
                LocalDateTime date2 = LocalDateTime.parse(date, dateformatter);
                Boolean booldate = date2.isAfter(date1);
                
                if (booldate == false) {
                    System.out.println("\nAs datas dadas são inválidas");
                }
                
                else {
                    Map<String, Integer> result = this.controller.getListaMaioresVendedores(date1, date2);

                    for (Map.Entry<String, Integer> entry : result.entrySet()) {
                        String vendedor = entry.getKey();
                        Integer num = entry.getValue();

                        if (num > 0) {
                            System.out.println("\n" + vendedor + ": " + num);
                        }
                    }
                }
                break;
            case 5:
                float lucro = this.controller.getLucroVintage();
                System.out.println("\nA Vintage ganhou, até agora, " + lucro + "€!");
                break;
        }
    }

    private String showStoreStateMenu() {
        System.out.println("Qual o nome do ficheiro onde pretende guardar o estado?");
        System.out.print("> ");
        String filename = "input/" + sc.nextLine();

        return filename;
    }
}
