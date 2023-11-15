package src.qql;

import java.time.LocalDate;
import java.io.Serializable;

public class Sapatilha extends Artigo implements Serializable {
    private int tamanho;
    private boolean atacadores;
    private String cor;
    private int anoLancamento;
    private boolean premium;

    public Sapatilha() {
       super();
       this.tamanho = 0;
       this.atacadores = false;
       this.cor = "";
       this.anoLancamento = 0;
       this.premium = false;
    }

    public Sapatilha(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, String currentUser, int tamanho, boolean atacadores, String cor, int anoLancamento, boolean premium) {
        super(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, currentUser);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.anoLancamento = anoLancamento;
        this.premium = premium;
    }

    public Sapatilha(int tamanho, boolean atacadores, String cor, int anoLancamento, boolean premium) {
        super();
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.anoLancamento = anoLancamento;
        this.premium = premium;
    }

    public Sapatilha(Sapatilha sapatilha) {
        super(sapatilha);
        this.tamanho = sapatilha.getTamanho();
        this.atacadores = sapatilha.getAtacadores();
        this.cor = sapatilha.getCor();
        this.anoLancamento = sapatilha.getAnoLancamento();
        this.premium = sapatilha.isPremium();
    }

    // getters
    public int getTamanho() {
        return tamanho;
    }

    public boolean getAtacadores() {
        return atacadores;
    }

    public String getCor() {
        return cor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public boolean isPremium() {
        return premium;
    }

    // setters
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    // clone
    public Sapatilha clone() {
        return new Sapatilha(this);
    }

    public float calcularPrecoFinal() {
        float precoFinal;

        if (!isPremium()) {
            if (getNumDonos() > 0) {
                precoFinal = getPrecoBase() - (getPrecoBase() / getNumDonos());
            }
            else {
                precoFinal = getPrecoBase();
            }
        }
        else {
            int anoAtual = LocalDate.now().getYear();
            int anosDiferenca = anoAtual - getAnoLancamento();
            precoFinal = getPrecoBase() + (float) anosDiferenca * 2.0f;
        }

        setPrecoFinal(precoFinal);
        return precoFinal;
    }

    // toString
    public String toString() {
    return super.toString() + ", tipo: sapatilha, " +
            "tamanho: " + tamanho + ", " +
            "possui atacadores: " + atacadores + ", " +
            "cor: " + cor + ", " +
            "ano de lançamento: " + anoLancamento + ", " +
            "premium: " + premium;
    }


    // equals
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Sapatilha)) {
            return false;
        }
        Sapatilha other = (Sapatilha) obj;
        return super.equals(obj) &&
        tamanho == other.tamanho &&
        atacadores == other.atacadores &&
        (cor == null ? other.cor == null : cor.equals(other.cor)) &&
        anoLancamento == other.anoLancamento &&
        premium == other.premium;
    }


}
