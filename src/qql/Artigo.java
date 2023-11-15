package src.qql;

import java.io.Serializable;

public abstract class Artigo implements Serializable {
    private String nome;
    private String descricao;
    private String marca;
    private Integer codigo;
    private float precoBase;
    private float correcaoPreco;
    private float custosExpedicao;
    private float precoFinal;
    private boolean novo;
    private int numDonos;
    private String condicao;
    private String transportadora;
    private int stock;
    private String vendedor;

    public abstract float calcularPrecoFinal();

    // constructors
    public Artigo() {
        this.nome = "";
        this.descricao = "";
        this.marca = "";
        this.codigo = 0;
        this.precoBase = 0.0f;
        this.correcaoPreco = 0.0f;
        this.custosExpedicao = 0.0f;
        this.precoFinal = 0.0f;
        this.novo = true;
        this.numDonos = 0;
        this.condicao = "";
        this.transportadora = "";
        this.stock = 0;
        this.vendedor = "";
    }

    public Artigo(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, String vendedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.precoBase = precoBase;
        this.correcaoPreco = correcaoPreco;
        this.custosExpedicao = 0.0f;
        this.precoFinal = 0.0f;
        this.novo = novo;
        this.numDonos = numDonos;
        this.condicao = condicao;
        this.transportadora = transportadora;
        this.stock = stock;
        this.vendedor = vendedor;
    }
    
    public Artigo(Artigo artigo) {
        this.nome = artigo.getNome();
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.codigo = artigo.getCodigo();
        this.precoBase = artigo.getPrecoBase();
        this.correcaoPreco = artigo.getCorrecaoPreco();
        this.custosExpedicao = artigo.getCustosExpedicao();
        this.precoFinal = artigo.getPrecoFinal();
        this.novo = artigo.getNovo();
        this.numDonos = artigo.getNumDonos();
        this.condicao = artigo.getCondicao();
        this.transportadora = artigo.getTransportadora();
        this.stock = artigo.getStock();
        this.vendedor = artigo.getVendedor();
    }

    // getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public float getCorrecaoPreco() {
        return correcaoPreco;
    }

    public float getCustosExpedicao() {
        return custosExpedicao;
    }

    public float getPrecoFinal() {
        return precoFinal;
    }

    public boolean getNovo() {
        return novo;
    }

    public int getNumDonos() {
        return numDonos;
    }

    public String getCondicao() {
        return condicao;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public int getStock() {
        return stock;
    }

    public String getVendedor() {
        return vendedor;
    }

    // setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }

    public void setCorrecaoPreco(float correcaoPreco) {
        this.correcaoPreco = correcaoPreco;
    }

    public void setCustosExpedicao(float custosExpedicao) {
        this.custosExpedicao = custosExpedicao;
    }

    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    // clone
    /*
    public Artigo clone() {
        return new Artigo(this);
    }*/

    // toString
    @Override
    public String toString() {
        return "código: " + codigo + ", " +
           "nome: " + nome + ", " +
           "descrição: " + descricao + ", " +
           "marca: " + marca + ", " +
           "preço base: " + precoBase + ", " +
           "correção de preço: " + correcaoPreco + ", " +
           "novo: " + novo + ", " +
           "número de donos: " + numDonos + ", " +
           "condição: " + condicao + ", " +
           "transportadora: " + transportadora + ", " +
           // "stock: " + stock + ", " +
           "vendedor: " + vendedor;
    }

    // equals
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Artigo)) {
           return false;
        }
    
        Artigo other = (Artigo) obj;
        return (nome == null ? other.nome == null : nome.equals(other.nome)) &&
           (descricao == null ? other.descricao == null : descricao.equals(other.descricao)) &&
           (marca == null ? other.marca == null : marca.equals(other.marca)) &&
           codigo == other.codigo &&
           Float.compare(precoBase, other.precoBase) == 0 &&
           Float.compare(correcaoPreco, other.correcaoPreco) == 0 &&
           novo == other.novo &&
           numDonos == other.numDonos &&
           stock == other.stock &&
           vendedor == other.vendedor &&
           (condicao == null ? other.condicao == null : condicao.equals(other.condicao));
    }

}
