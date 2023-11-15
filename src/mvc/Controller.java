package src.mvc;

import src.qql.*;

import java.io.*;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;

public class Controller {
    private Model model;

    public Controller(Model m) {
        this.model = m;
    }

    public Controller(Controller c) {
        this.model = c.getModel();
    }

    public Model getModel() {
        return this.model;
    }

    public void setUpReadFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.model = (Model) ois.readObject();
        ois.close();
    }

    public String addUtilizador(String nome, String email, String morada, String nif) {
        Utilizador u = new Utilizador(nome, email, morada, nif);
        String utilizadorEmail = this.model.addUtilizador(u);
        return utilizadorEmail;
    }

    public String addTransportadora(String nome, float imposto, float valorPeq, float valorMed, float valorGra, float lucro) {
        Transportadora t = new Transportadora(nome, imposto, valorPeq, valorMed, valorGra, lucro);
        String transportadoraNome = this.model.addTransportadora(t);
        return transportadoraNome;
    }

   /* public String addArtigo(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora) {
        Artigo a = new Artigo(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora);
        String artigoNome = this.model.addArtigo(a);
        return artigoNome;
    }*/

    public String addMala(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, float dimensao, String material, int anoColecao, boolean premium, float valorizacao, String currentUser) {
        Mala a = new Mala(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, currentUser, dimensao, material, anoColecao, premium, valorizacao);
        String malaNome = this.model.addMala(a, currentUser);
        return malaNome;
    }

    public String addSapatilha(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, int tamanho, boolean atacadores, String cor, int anoLancamento, boolean premium, String currentUser) {
        Sapatilha a = new Sapatilha(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, currentUser, tamanho, atacadores, cor, anoLancamento, premium);
        String sapatilhaNome = this.model.addSapatilha(a, currentUser);
        return sapatilhaNome;
    }

    public String addTShirt(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, String tamanho, String padrao, int desconto, String currentUser) {
        TShirt a = new TShirt(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, currentUser, tamanho, padrao, desconto);
        String TShirtNome = this.model.addTShirt(a, currentUser);
        return TShirtNome;
    }

    public void changeDate(LocalDateTime initialDate) {
        this.model.setCurrentDate(initialDate);
    }

    public LocalDateTime getCurrentDateTime() {
        return this.model.getCurrentDateTime();
    }

    public String showArtigos() {
        return this.model.showArtigos();
    }

    public String showTransportadoras() {
        return this.model.showTransportadoras();
    }

    public int getNumArtigos() {
        return this.model.getNumArtigos();
    }

    public int getNumTransportadoras() {
        return this.model.getNumTransportadoras();
    }

    public Integer addArtigoEncomenda(Integer option_codigo, Integer option_stock, String currentUser) {
        return this.model.addArtigoEncomenda(option_codigo, option_stock, currentUser);
    }

    public void addArtigoEncomenda2(Integer option_codigo) {
        this.model.addArtigoEncomenda2(option_codigo);
    }

    public Integer removeArtigoEncomenda(Integer option_codigo, String currentUser) {
        return this.model.removeArtigoEncomenda(option_codigo, currentUser);
    }

    public String showEncomenda(String currentUser) {
        return this.model.showEncomenda(currentUser);
    }

    public String showEncomendasFeitas(String currentUser) {
        return this.model.showEncomendasFeitas(currentUser);
    }

    public boolean checkUtilizadorExiste(String email) {
        return this.model.checkUtilizadorExiste(email);
    }

    public boolean checkTransportadoraExiste(String nome) {
        return this.model.checkTransportadoraExiste(nome);
    }

    public boolean checkEncomendaVazia(String nome) {
        return this.model.checkEncomendaVazia(nome);
    }

    public void initEncomendaUser(String nome) {
        this.model.initEncomendaUser(nome);
    }

    public float calcularPrecoFinalEncomenda(String currentUser) {
        return this.model.calcularPrecoFinalEncomenda(currentUser);
    }

    public void finalizarCompra(String currentUser) {
        this.model.finalizarCompra(currentUser);
    }

    public void devolverEncomenda(String currentUser, Integer codigo) {
        this.model.devolverEncomenda(currentUser, codigo);
    }

    public float getLucroVintage() {
        return this.model.getLucro();
    }

    public String getVendasUtilizador(String nome) {
        return this.model.getVendasUtilizador(nome);
    }

    public String getMaiorVendedor() {
        return this.model.getMaiorVendedor();
    }

    public String getMaiorVendedorPeriodo(LocalDateTime date1, LocalDateTime date2) {
        return this.model.getMaiorVendedorPeriodo(date1, date2);
    }

    public float getMaiorVendedorValor(String nome) {
        return this.model.getMaiorVendedorValor(nome);
    }

    public float getMaiorVendedorValorPeriodo(String nome, LocalDateTime date1, LocalDateTime date2) {
        return this.model.getMaiorVendedorValorPeriodo(nome, date1, date2);
    }

    public String getMaiorTransportadora() {
        return this.model.getMaiorTransportadora();
    }

    public float getMaiorTransportadoraValor(String nome) {
        return this.model.getMaiorTransportadoraValor(nome);
    }

    public boolean checkUtilizadorEncADevolver(String nome) {
        return this.model.checkUtilizadorEncADevolver(nome);
    }

    public boolean checkUtilizadorTemFaturas(String nome) {
        return this.model.checkUtilizadorTemFaturas(nome);
    }

    public boolean checkUtilizadorTemEncADevolver(Integer codigo, String nome) {
        return this.model.checkUtilizadorTemEncADevolver(codigo, nome);
    }

    public void storeState(String filename) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.model);
        oos.flush();
        oos.close();
    }

    public void avancar(int hours) {
        this.model.avancar(hours);
    }

    public void startSimulation() {
        this.model.startSimulation();
    }

    public String showFaturas(String nome) {
        return this.model.showFaturas(nome);
    }

    public void alterarValorTransportadora(String transportadora, int option, float valor) {
        this.model.alterarValorTransportadora(transportadora, option, valor);
    }

    public float showValorTransportadora(String transportadora, int option) {
        return this.model.showValorTransportadora(transportadora, option);
    }

    public String showFormulaTransportadora(String transportadora) {
        return this.model.showFormulaTransportadora(transportadora);
    }

    public void alterarFormulaTransportadora(String transportadora, String formula) {
        this.model.alterarFormulaTransportadora(transportadora, formula);
    }

    public Map<String, Integer> getListaMaioresVendedores(LocalDateTime date1, LocalDateTime date2) {
        return this.model.getListaMaioresVendedores(date1, date2);
    }

}
