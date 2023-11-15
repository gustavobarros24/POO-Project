package src.qql;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;
import java.io.Serializable;

public class Encomenda implements Serializable {

    public enum TamanhoEmbalagem {
        PEQUENO,
        MEDIO,
        GRANDE
    }

    public enum EstadoEncomenda {
        PENDENTE, 
        FINALIZADA, 
        EXPEDIDA, 
        DEVOLVIDA
    }

    public final float TAXA_SATISFACAO_ARTIGO_NOVO = 0.5f;
    public final float TAXA_SATISFACAO_ARTIGO_USADO = 0.25f;

    private static int ultimoCodigo = 0;

    private int codigo;
    // private List<Integer> artigos;
    private Map<Integer, Integer> artigos;
    private TamanhoEmbalagem embalagem;
    private float precoFinal;
    private EstadoEncomenda estado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;
    private LocalDateTime dataPrevistaEntrega;

    public Encomenda() {
        this.codigo = ++ultimoCodigo;
        // this.artigos = new ArrayList<Integer>();
        this.artigos = new HashMap<Integer, Integer>();
        this.embalagem = null;
        this.precoFinal = 0.0f;
        this.estado = EstadoEncomenda.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
        this.dataFinalizacao = null;
        this.dataPrevistaEntrega = null;
    }

    public Encomenda(TamanhoEmbalagem embalagem, float taxaSatisfacao) {
        this.codigo = ++ultimoCodigo;
        // this.artigos = new ArrayList<>();
        this.artigos = new HashMap<>();
        this.embalagem = embalagem;
        this.precoFinal = 0.0f;
        this.estado = EstadoEncomenda.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
        this.dataFinalizacao = null;
        this.dataPrevistaEntrega = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public Map<Integer, Integer> getArtigos() {
        return artigos;
    }

    public EstadoEncomenda getEstado() {
        return this.estado;
    }

    public LocalDateTime getDataFinalizacao() {
        return this.dataFinalizacao;
    }

    public LocalDateTime getDataPrevistaEntrega() {
        return this.dataPrevistaEntrega;
    }

    public float getPrecoFinal() {
        return this.precoFinal;
    }

    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void setEstado(EstadoEncomenda estado) {
        this.estado = estado;
    }

    public void setDataFinalizacao(LocalDateTime data) {
        this.dataFinalizacao = data;
    }

    public void setDataPrevistaEntrega(LocalDateTime data) {
        this.dataPrevistaEntrega = data;
    }

    public TamanhoEmbalagem getTamanhoEmbalagem() {
        return embalagem;
    }

    public void setTamanhoEmbalagem() {
        if (artigos.size() == 1) {
            this.embalagem = TamanhoEmbalagem.PEQUENO;
        }

        else if (artigos.size() > 1 && artigos.size() < 6) {
            this.embalagem = TamanhoEmbalagem.MEDIO;
        }

        else {
            this.embalagem = TamanhoEmbalagem.GRANDE;
        }
    }

    public Integer adicionarArtigo(Integer cod_artigo, Integer stock_artigo) {
        if (!artigos.containsKey(cod_artigo)) {
            artigos.put(cod_artigo, stock_artigo);
            return cod_artigo;
        }
        else { 
            //return 0;
            Integer stock_existente = artigos.get(cod_artigo);
            artigos.put(cod_artigo, stock_artigo + stock_existente);
            return cod_artigo;
        }
    }

    public Integer removerArtigo(Integer cod_artigo) {
        return artigos.remove(cod_artigo);
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void devolverEncomenda() {
        if (estado == EstadoEncomenda.EXPEDIDA) {
            LocalDateTime dataAtual = LocalDateTime.now();
            LocalDateTime datacriacao = getDataCriacao();
            long daysbetween = DAYS.between(dataAtual,datacriacao);
            if (daysbetween <= 14) {
                estado = EstadoEncomenda.DEVOLVIDA;
            }
        }
    }

    @Override
    public String toString() {
        return "código: " + codigo +
            ", artigos: " + artigos.size() +
            ", embalagem: " + embalagem +
            ", preço final: " + precoFinal +
            ", estado: " + estado +
            ", data de criação: " + dataCriacao +
            ", data de finalização: " + dataFinalizacao +
            ", data prevista de entrega: " + dataPrevistaEntrega;
    }
}


