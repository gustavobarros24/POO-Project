package src.qql;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import src.qql.Fatura.Tipo;

public class Utilizador implements Serializable {

    private String email;
    private String nome;
    private String morada;
    private String nif;
    private Map<Integer, Artigo> produtosAVenda;
    private Map<Integer, Artigo> produtosVendidos;
    private Map<Integer, Encomenda> encomendasFeitas;
    private List<Fatura> faturas;
    private Encomenda encomenda;
    private float totalVendas;

    public Utilizador(){
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.nif = "";
        this.produtosAVenda = new HashMap<>();
        this.produtosVendidos = new HashMap<>();
        this.encomendasFeitas = new HashMap<>();
        this.faturas = new ArrayList<Fatura>();
        this.encomenda = null;
        this.totalVendas = 0.0f;
    }

    public Utilizador(String nome, String email, String morada, String nif) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.produtosAVenda = new HashMap<>();
        this.produtosVendidos = new HashMap<>();
        this.encomendasFeitas = new HashMap<>();
        this.faturas = new ArrayList<Fatura>();
        this.totalVendas = 0.0f;
        this.encomenda = null;
    }
    
    public Utilizador(Utilizador utilizador){
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nif = utilizador.getNIF();
        this.produtosAVenda = utilizador.getProdutosAVenda();
        this.produtosVendidos = utilizador.getProdutosVendidos();
        this.encomendasFeitas = utilizador.getEncomendasFeitas();
        this.faturas = utilizador.getFaturas();
        this.totalVendas = utilizador.getTotalVendas();
        this.encomenda = utilizador.getEncomenda();
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getMorada(){
        return this.morada;
    }

    public String getNIF(){
        return this.nif;
    }

    public Map<Integer, Artigo> getProdutosAVenda() {
        Map<Integer, Artigo> produtosAVenda = new HashMap<>();
        for (Integer codigo : this.produtosAVenda.keySet()) {
            Object obj = this.produtosAVenda.get(codigo);
            if (obj instanceof Mala) {
                produtosAVenda.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                produtosAVenda.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                produtosAVenda.put(codigo, ((TShirt) obj).clone());
            }
        }
        return produtosAVenda;
    }

    public Map<Integer, Artigo> getProdutosVendidos() {
        Map<Integer, Artigo> produtosVendidos = new HashMap<>();
        for (Integer codigo : this.produtosVendidos.keySet()) {
            Object obj = this.produtosVendidos.get(codigo);
            if (obj instanceof Mala) {
                produtosVendidos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                produtosVendidos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                produtosVendidos.put(codigo, ((TShirt) obj).clone());
            }
        }
        return produtosVendidos;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public Map<Integer, Encomenda> getEncomendasFeitas() {
        return this.encomendasFeitas;
    }

    public float getTotalVendas() {
        return this.totalVendas;
    }
    
    public Encomenda getEncomenda() {
        return this.encomenda;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setNIF(String nif){
        this.nif = nif;
    }

    public void setProdutosAVenda(Map<Integer, Artigo> produtosAVenda) {
        Map<Integer, Artigo> novoProdutosAVenda = new HashMap<Integer, Artigo>();
        for (Integer codigo : produtosAVenda.keySet()) {
            Object obj = produtosAVenda.get(codigo);
            if (obj instanceof Mala) {
                novoProdutosAVenda.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                novoProdutosAVenda.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                novoProdutosAVenda.put(codigo, ((TShirt) obj).clone());
            }
        }
        this.produtosAVenda = novoProdutosAVenda;
    }

    public void setProdutosVendidos(Map<Integer, Artigo> produtosVendidos) {
        Map<Integer, Artigo> novoProdutosVendidos = new HashMap<Integer, Artigo>();
        for (Integer codigo : produtosVendidos.keySet()) {
            Object obj = produtosVendidos.get(codigo);
            if (obj instanceof Mala) {
                novoProdutosVendidos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                novoProdutosVendidos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                novoProdutosVendidos.put(codigo, ((TShirt) obj).clone());
            }
        }
        this.produtosVendidos = novoProdutosVendidos;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public void setTotalVendas(float totalVendas) {
        this.totalVendas = totalVendas;
    }

    public void setEncomenda() {
        this.encomenda = new Encomenda();
    }

    public void setEncomendaNull() {
        this.encomenda = null;
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public Integer addArtigoEncomenda(Integer codigo, Integer stock) {
        return this.encomenda.adicionarArtigo(codigo, stock);
    }

    public Integer removeArtigoEncomenda(Integer codigo) {
        return this.encomenda.removerArtigo(codigo);
    }

    public void addArtigoProdutosAVenda(Integer codigo, Artigo artigo) {
        produtosAVenda.put(codigo, artigo);
    }

    public void removeArtigoProdutosAVenda(Integer codigo) {
        produtosAVenda.remove(codigo);
    }
    
    public void addArtigoProdutosVendidos(Integer codigo, Artigo artigo, Integer stock) {
        produtosVendidos.put(codigo, artigo);

        totalVendas += artigo.getPrecoFinal() * stock;
    }

    public void removeArtigoProdutosVendidos(Integer codigo) {
        produtosVendidos.remove(codigo);
    }

    public void addEncomendaEncomendasFeitas(Integer codigo, Encomenda encomenda) {
        encomendasFeitas.put(codigo, encomenda);
    }

    public void removeEncomendaEncomendasFeitas(Integer codigo) {
        encomendasFeitas.remove(codigo);
    }

    public void addFatura(Fatura fatura) {
        faturas.add(fatura);
    }

    public int numeroVendasEfetuadasDuranteUmPeriodoTempo(LocalDateTime date1, LocalDateTime date2) {
        int count = 0;
        List<Fatura> faturas = this.getFaturas();

        for (Fatura fatura: faturas) {
            if ((fatura.getTipo() == Tipo.VENDA) && ((fatura.getDataFinalizacao().isEqual(date1) == true) || (fatura.getDataFinalizacao().isBefore(date2) == true) && (fatura.getDataFinalizacao().isAfter(date1) == true ))){
                count++;
            }
        }
        return count;
    }

    public float getTotalVendasPeriodo(LocalDateTime date1, LocalDateTime date2) {
        float valor = 0;
        List<Fatura> faturas = this.getFaturas();

        for (Fatura fatura : faturas) {
            if ((fatura.getTipo() == Tipo.VENDA) && ((fatura.getDataFinalizacao().isEqual(date1) == true) || (fatura.getDataFinalizacao().isBefore(date2) == true) && (fatura.getDataFinalizacao().isAfter(date1) == true))) {
                valor += fatura.getCusto();
            }
        }

        return valor;
    }
}

