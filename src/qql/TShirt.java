package src.qql;

import java.util.Objects;
import java.io.Serializable;

public class TShirt extends Artigo implements Serializable {
    public enum Tamanho {
        S,
        M,
        L,
        XL
    }

    public enum Padrao {
        LISO,
        RISCAS,
        PALMEIRAS
    }

    private Tamanho tamanho;
    private Padrao padrao;
    private int desconto;

    public TShirt() {
        super();
        this.tamanho = null;
        this.padrao = null;
        this.desconto = 0;
    }

    public TShirt(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, String currentUser, String tamanho, String padrao, int desconto) {
        super(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, currentUser);
        setTamanho(tamanho);
        setPadrao(padrao);
        this.desconto = desconto;
    }

    public TShirt(TShirt tshirt) {
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
        this.desconto = tshirt.getDesconto();
    }

    // getters
    public Tamanho getTamanho() {
        return tamanho;
    }

    public Padrao getPadrao() {
        return padrao;
    }

    public int getDesconto() {
        return desconto;
    }

    // setters
    public void setTamanho(String tamanho) {
        switch (tamanho) {
            case "S":
                this.tamanho = Tamanho.S;
                break;
            case "M":
                this.tamanho = Tamanho.M;
                break;
            case "L":
                this.tamanho = Tamanho.L;
                break;
            case "XL":
                this.tamanho = Tamanho.XL;
                break;
            default:
                throw new IllegalArgumentException("Invalid tamanho: " + tamanho);
        }
    }

    public void setPadrao(String padrao) {
        switch (padrao) {
            case "LISO":
                this.padrao = Padrao.LISO;
                break;
            case "RISCAS":
                this.padrao = Padrao.RISCAS;
                break;
            case "PALMEIRAS":
                this.padrao = Padrao.PALMEIRAS;
                break;
            default:
                throw new IllegalArgumentException("Invalid padrao: " + padrao);
        }
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public TShirt clone() {
        return new TShirt(this);
    }

    public float calcularPrecoFinal() {
        float precoFinal = getPrecoBase();
        if (getPadrao() != Padrao.LISO && !getNovo()) {
            precoFinal *= 0.5f;
        }
        setPrecoFinal(precoFinal);
        return precoFinal;
    }

    public String toString() {
        return super.toString() + ", tipo: t-shirt, " +
            "tamanho: " + tamanho + ", " +
            "padrão: " + padrao + ", " +
            "desconto: " + desconto + "%";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TShirt)) {
            return false;
        }
        TShirt other = (TShirt) obj;
        return super.equals(obj) &&
           this.tamanho == other.tamanho &&
           this.padrao == other.padrao &&
           this.desconto == other.desconto;
    }

}

