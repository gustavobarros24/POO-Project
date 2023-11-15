package src.qql;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Fatura implements Serializable {

    public enum Tipo {
        COMPRA,
        VENDA
    }

    private float custo;
    private Tipo tipo;
    private Encomenda encomenda;
    private LocalDateTime dataFinalizacao;
    private List<Integer> artigos;

    public Fatura(float custo, Tipo tipo, Encomenda encomenda, LocalDateTime dataFinalizacao, List<Integer> artigos) {
        this.custo = custo;
        this.tipo = tipo;
        this.encomenda = encomenda;
        this.dataFinalizacao = dataFinalizacao;
        this.artigos = artigos;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public List<Integer> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Integer> artigos) {
        this.artigos = artigos;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    @Override
    public String toString() {
        return "tipo: " + tipo +
            ", custo: " + custo +
            ", data de finalização: " + dataFinalizacao +
            ", artigos: " + artigos.toString();
    }
}
