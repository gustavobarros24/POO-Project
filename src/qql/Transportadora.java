package src.qql;

import src.qql.Encomenda.TamanhoEmbalagem;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class Transportadora implements Serializable {
    private String nome;
    private float valorExpEncPeq;
    private float valorExpEncMed;
    private float valorExpEncGra;
    private float imposto; 
    private float margemLucro;
    private float lucroEfetivo;
    private String formula;

    public Transportadora() {
        this.nome = "";
        this.valorExpEncPeq = 0.0f;
        this.valorExpEncMed = 0.0f;
        this.valorExpEncGra = 0.0f;
        this.imposto = 0.0f;
        this.margemLucro = 0.0f;
        this.lucroEfetivo = 0.0f;
        this.formula = "(VB * MLT * (1 + I)) * 0.9";
    }

    public Transportadora(String nome, float imposto, float valorPeq, float valorMed, float valorGra, float margemLucro) {
        this.nome = nome;
        this.imposto = imposto;
        this.valorExpEncPeq = valorPeq;
        this.valorExpEncMed = valorMed;
        this.valorExpEncGra = valorGra;
        this.margemLucro = margemLucro;
        this.lucroEfetivo = 0.0f;
        this.formula = "(VB * MLT * (1 + I)) * 0.9";
    }

    public Transportadora(Transportadora transportadora) {
        this.nome = transportadora.getNome();
        this.valorExpEncPeq = transportadora.getValorExpEncPeq();
        this.valorExpEncMed = transportadora.getValorExpEncMed();
        this.valorExpEncGra = transportadora.getValorExpEncGra();
        this.imposto = transportadora.getImposto();
        this.margemLucro = transportadora.getMargemLucro();
        this.lucroEfetivo = transportadora.getLucroEfetivo();
        this.formula = transportadora.getFormula();
    }

    public String getNome() {
        return nome;
    }

    public float getValorExpEncPeq() {
        return valorExpEncPeq;
    }

    public float getValorExpEncMed() {
        return valorExpEncMed;
    }

    public float getValorExpEncGra() {
        return valorExpEncGra;
    }

    public float getImposto() {
        return imposto;
    }

    public float getMargemLucro() {
        return margemLucro;
    }

    public float getLucroEfetivo() {
        return lucroEfetivo;
    }

    public String getFormula() {
        return formula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorExpEncPeq(float valorExpEncPeq) {
        this.valorExpEncPeq = valorExpEncPeq;
    }

    public void setValorExpEncMed(float valorExpEncMed) {
        this.valorExpEncMed = valorExpEncMed;
    }

    public void setValorExpEncGra(float valorExpEncGra) {
        this.valorExpEncGra = valorExpEncGra;
    }

    public void setImposto(float imposto) {
        this.imposto = imposto;
    }

    public void setMargemLucro(float margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setLucroEfetivo(float lucroEfetivo) {
        this.lucroEfetivo = lucroEfetivo;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

    public float calculaPrecoExpedicao(TamanhoEmbalagem tamanho) {
        if (tamanho == TamanhoEmbalagem.PEQUENO) {
            Expression exp = new ExpressionBuilder(this.formula)
                    .variables("VB", "MLT", "I")
                    .build()
                    .setVariable("VB", this.valorExpEncPeq)
                    .setVariable("MLT", this.margemLucro)
                    .setVariable("I", this.imposto);

             return (float) exp.evaluate();
        }

        else if (tamanho == TamanhoEmbalagem.MEDIO) {
            Expression exp = new ExpressionBuilder(this.formula)
                    .variables("VB", "MLT", "I")
                    .build()
                    .setVariable("VB", this.valorExpEncMed)
                    .setVariable("MLT", this.margemLucro)
                    .setVariable("I", this.imposto);

             return (float) exp.evaluate();
        }

        else {
            Expression exp = new ExpressionBuilder(this.formula)
                    .variables("VB", "MLT", "I")
                    .build()
                    .setVariable("VB", this.valorExpEncGra)
                    .setVariable("MLT", this.margemLucro)
                    .setVariable("I", this.imposto);

             return (float) exp.evaluate();
        }
    }
}
