package src.qql;

import java.io.Serializable;
import java.time.LocalDate;

public class Mala extends Artigo implements Serializable {
    private float dimensao;
    private String material;
    private int anoColecao;
    private boolean premium;
    private float valorizacao;

    public Mala() {
        super();
        this.dimensao = 0.0f;
        this.material = "";
        this.anoColecao = 0;
        this.premium = false;
        this.valorizacao = 0.0f;
    }

    public Mala(String nome, String descricao, String marca, Integer codigo, float precoBase, float correcaoPreco, boolean novo, int numDonos, String condicao, String transportadora, int stock, String currentUser, float dimensao, String material, int anoColecao, boolean premium, float valorizacao) {
        super(nome, descricao, marca, codigo, precoBase, correcaoPreco, novo, numDonos, condicao, transportadora, stock, currentUser);
        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
        this.premium = premium;
        this.valorizacao = valorizacao;
    }

    public Mala(Mala mala) {
        super(mala);
        this.dimensao = mala.getDimensao();
        this.material = mala.getMaterial();
        this.anoColecao = mala.getAnoColecao();
        this.premium = mala.isPremium();
        this.valorizacao = mala.getValorizacao();
    }

    // getters
    public float getDimensao() {
        return dimensao;
    }

    public String getMaterial() {
        return material;
    }

    public int getAnoColecao() {
        return anoColecao;
    }

    public boolean isPremium() {
        return premium;
    }

    public float getValorizacao() {
        return valorizacao;
    }

    // setters
    public void setDimensao(float dimensao) {
        this.dimensao = dimensao;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setAnoColecao(int anoColecao) {
        this.anoColecao = anoColecao;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void setValorizacao(float valorizacao) {
        this.valorizacao = valorizacao;
    }

    public Mala clone() {
        return new Mala(this);
    }

    public float calcularPrecoFinal() {
        float k = 101100.0f;
        float precoFinal;

        if (isPremium()) {
            precoFinal = getPrecoBase() + (k / (getDimensao() * getAnoColecao()));
        }
        else {
            int anoAtual = LocalDate.now().getYear();
            int anosDiferenca = anoAtual - getAnoColecao();
            precoFinal = getPrecoBase() + (0.2f * (float) anosDiferenca) + (k / (getDimensao() * getAnoColecao()));
        }
    
        setPrecoFinal(precoFinal);
        return precoFinal;
    }

    public String toString() {
        return super.toString() + ", tipo: mala, " +
            "dimensão: " + dimensao + ", " +
            "material: " + material + ", " +
            "ano da Coleção: " + anoColecao + ", " +
            "premium: " + premium + ", " + 
            "valorização: " + valorizacao + "%";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Mala)) {
            return false;
        }
        Mala other = (Mala) obj;
        return super.equals(obj) &&
           Float.compare(dimensao, other.dimensao) == 0 &&
           (material == null ? other.material == null : material.equals(other.material)) &&
           anoColecao == other.anoColecao &&
           premium == other.premium &&
           Float.compare(valorizacao, other.valorizacao) == 0;
}

}
