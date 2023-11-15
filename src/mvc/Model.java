package src.mvc;

import src.qql.*;
import src.qql.Encomenda.EstadoEncomenda;
import src.qql.Encomenda.TamanhoEmbalagem;
import src.qql.Fatura.Tipo;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import static java.time.temporal.ChronoUnit.HOURS;
import java.time.temporal.ChronoUnit;
import java.lang.Math;

public class Model implements Serializable {
    public final float TAXA_DE_GARANTIA = 5.0f;

    private LocalDateTime currentDateTime;
    private Map<Integer, Artigo> catalogoArtigos;
    private Map<Integer, Artigo> catalogoArtigosEncomenda;
    private Map<Integer, Artigo> artigosVendidos;
    private Map<String, Utilizador> catalogoUtilizadores;
    private Map<String, Transportadora> catalogoTransportadoras;
    private float lucro;

    public Model() {
        this.catalogoArtigos = new HashMap<>();
        this.catalogoArtigosEncomenda = new HashMap<>();
        this.artigosVendidos = new HashMap<>();
        this.catalogoUtilizadores = new HashMap<>();
        this.catalogoTransportadoras = new HashMap<>();
        this.lucro = 0.0f;
        this.currentDateTime = LocalDateTime.now();
    }

    public Model(LocalDateTime currentDateTime, Map<Integer, Artigo> catalogoArtigos, Map<Integer, Artigo> catalogoArtigosEncomenda, Map<Integer, Artigo> artigosVendidos, Map<String, Utilizador> catalogoUtilizadores, Map<String, Transportadora> catalogoTransportadoras, float lucro) {
        this.currentDateTime = currentDateTime;

        this.catalogoArtigos = new HashMap<>();
        for (Integer codigo : catalogoArtigos.keySet()) {
            Object obj = catalogoArtigos.get(codigo);
            if (obj instanceof Mala) {
                this.catalogoArtigos.put(codigo, ((Mala) obj).clone());
            //this.catalogoArtigos.put(codigo, catalogoArtigos.get(codigo).clone());
            }
            else if (obj instanceof Sapatilha) {
                this.catalogoArtigos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                this.catalogoArtigos.put(codigo, ((TShirt) obj).clone());
            }
        }

        this.catalogoArtigosEncomenda = new HashMap<>();
        for (Integer codigo : catalogoArtigosEncomenda.keySet()) {
            //this.catalogoArtigosEncomenda.put(codigo, catalogoArtigosEncomenda.get(codigo).clone());
            Object obj = catalogoArtigosEncomenda.get(codigo);
            if (obj instanceof Mala) {
                this.catalogoArtigosEncomenda.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                this.catalogoArtigosEncomenda.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                this.catalogoArtigosEncomenda.put(codigo, ((TShirt) obj).clone());
            }
        }

        this.artigosVendidos = new HashMap<>();
        for (Integer codigo : artigosVendidos.keySet()) {
            //this.catalogoArtigosEncomenda.put(codigo, catalogoArtigosEncomenda.get(codigo).clone());
            Object obj = artigosVendidos.get(codigo);
            if (obj instanceof Mala) {
                this.artigosVendidos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                this.artigosVendidos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                this.artigosVendidos.put(codigo, ((TShirt) obj).clone());
            }
        }

        this.catalogoUtilizadores = new HashMap<>();
        for (String email : catalogoUtilizadores.keySet()) {
            this.catalogoUtilizadores.put(email, catalogoUtilizadores.get(email).clone());
        }

        this.catalogoTransportadoras = new HashMap<>();
        for (String nome : catalogoTransportadoras.keySet()) {
            this.catalogoTransportadoras.put(nome, catalogoTransportadoras.get(nome).clone());
        }

        this.lucro = lucro;
    }

    public Model(Model m) {
        this.currentDateTime = m.getCurrentDateTime();
        this.catalogoArtigos = m.getCatalogoArtigos();
        this.catalogoArtigosEncomenda = m.getCatalogoArtigosEncomenda();
        this.artigosVendidos = m.getArtigosVendidos();
        this.catalogoUtilizadores = m.getCatalogoUtilizadores();
        this.catalogoTransportadoras = m.getCatalogoTransportadoras();
        this.lucro = m.getLucro();
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public Map<Integer, Artigo> getCatalogoArtigos() {
        Map<Integer, Artigo> catalogoArtigos = new HashMap<>();
        for (Integer codigo : this.catalogoArtigos.keySet()) {
            //catalogoArtigos.put(codigo, this.catalogoArtigos.get(codigo).clone());
            Object obj = this.catalogoArtigos.get(codigo);
            if (obj instanceof Mala) {
                catalogoArtigos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                catalogoArtigos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                catalogoArtigos.put(codigo, ((TShirt) obj).clone());
            }
        }
        return catalogoArtigos;
    }

    public Map<Integer, Artigo> getCatalogoArtigosEncomenda() {
        Map<Integer, Artigo> catalogoArtigosEncomenda = new HashMap<>();
        for (Integer codigo : this.catalogoArtigosEncomenda.keySet()) {
            //catalogoArtigosEncomenda.put(codigo, this.catalogoArtigosEncomenda.get(codigo).clone());
            Object obj = this.catalogoArtigosEncomenda.get(codigo);
            if (obj instanceof Mala) {
                catalogoArtigosEncomenda.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                catalogoArtigosEncomenda.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                catalogoArtigosEncomenda.put(codigo, ((TShirt) obj).clone());
            }
        }
        return catalogoArtigosEncomenda;
    }

    public Map<Integer, Artigo> getArtigosVendidos() {
        Map<Integer, Artigo> artigosVendidos = new HashMap<>();
        for (Integer codigo : this.artigosVendidos.keySet()) {
            //catalogoArtigosEncomenda.put(codigo, this.catalogoArtigosEncomenda.get(codigo).clone());
            Object obj = this.artigosVendidos.get(codigo);
            if (obj instanceof Mala) {
                artigosVendidos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                artigosVendidos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                artigosVendidos.put(codigo, ((TShirt) obj).clone());
            }
        }
        return artigosVendidos;
    }

    public Map<String, Utilizador> getCatalogoUtilizadores() {
        Map<String, Utilizador> catalogoUtilizadores = new HashMap<>();
        for (String email : this.catalogoUtilizadores.keySet()) {
            catalogoUtilizadores.put(email, this.catalogoUtilizadores.get(email).clone());
        }
        return catalogoUtilizadores;
    }

    public Map<String, Transportadora> getCatalogoTransportadoras() {
        Map<String, Transportadora> catalogoTransportadoras = new HashMap<>();
        for (String nome : this.catalogoTransportadoras.keySet()) {
            catalogoTransportadoras.put(nome, this.catalogoTransportadoras.get(nome).clone());
        }
        return catalogoTransportadoras;
    }

    public float getLucro() {
        return lucro;
    }

    public void setCurrentDate(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public void setCatalogoArtigos(Map<Integer, Artigo> catalogoArtigos) {
        Map<Integer, Artigo> novoCatalogoArtigos = new HashMap<Integer, Artigo>();
        for (Integer codigo : catalogoArtigos.keySet()) {
            //novoCatalogoArtigos.put(codigo, catalogoArtigos.get(codigo).clone());
            Object obj = catalogoArtigos.get(codigo);
            if (obj instanceof Mala) {
                novoCatalogoArtigos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                novoCatalogoArtigos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                novoCatalogoArtigos.put(codigo, ((TShirt) obj).clone());
            }
        }
        this.catalogoArtigos = novoCatalogoArtigos;
    }

    public void setCatalogoArtigosEncomenda(Map<Integer, Artigo> catalogoArtigosEncomenda) {
        Map<Integer, Artigo> novoCatalogoArtigosEncomenda = new HashMap<Integer, Artigo>();
        for (Integer codigo : catalogoArtigosEncomenda.keySet()) {
            //novoCatalogoArtigosEncomenda.put(codigo, catalogoArtigosEncomenda.get(codigo).clone());
            Object obj = catalogoArtigosEncomenda.get(codigo);
            if (obj instanceof Mala) {
                novoCatalogoArtigosEncomenda.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                novoCatalogoArtigosEncomenda.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                novoCatalogoArtigosEncomenda.put(codigo, ((TShirt) obj).clone());
            }
        }
        this.catalogoArtigosEncomenda = novoCatalogoArtigosEncomenda;
    }

    public void setArtigosVendidos(Map<Integer, Artigo> artigosVendidos) {
        Map<Integer, Artigo> novoArtigosVendidos = new HashMap<Integer, Artigo>();
        for (Integer codigo : artigosVendidos.keySet()) {
            //novoCatalogoArtigosEncomenda.put(codigo, catalogoArtigosEncomenda.get(codigo).clone());
            Object obj = artigosVendidos.get(codigo);
            if (obj instanceof Mala) {
                novoArtigosVendidos.put(codigo, ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                novoArtigosVendidos.put(codigo, ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                novoArtigosVendidos.put(codigo, ((TShirt) obj).clone());
            }
        }
        this.artigosVendidos = novoArtigosVendidos;
    }

    public void setCatalogoUtilizadores(Map<String, Utilizador> catalogoUtilizadores) {
        Map<String, Utilizador> novoCatalogoUtilizadores = new HashMap<String, Utilizador>();
        for (String email : catalogoUtilizadores.keySet()) {
            novoCatalogoUtilizadores.put(email, catalogoUtilizadores.get(email).clone());
        }
        this.catalogoUtilizadores = novoCatalogoUtilizadores;
    }

    public void setCatalogoTransportadoras(Map<String, Transportadora> catalogoTransportadoras) {
        Map<String, Transportadora> novoCatalogoTransportadoras = new HashMap<String, Transportadora>();
        for (String nome : catalogoTransportadoras.keySet()) {
            novoCatalogoTransportadoras.put(nome, catalogoTransportadoras.get(nome).clone());
        }
        this.catalogoTransportadoras = novoCatalogoTransportadoras;
    }

    public void setLucro(float lucro) {
        this.lucro = lucro;
    }

    public String addUtilizador(Utilizador u) {
        if (catalogoUtilizadores.containsKey(u.getEmail())) {
            throw new IllegalArgumentException("\nUtilizador já existe! Tente novamente.");
        } 
        else {
            this.catalogoUtilizadores.put(u.getEmail(), u.clone());
            return u.getEmail();
        }
    }

    public String addTransportadora(Transportadora t) {
        if (catalogoTransportadoras.containsKey(t.getNome())) {
            throw new IllegalArgumentException("\nTransportadora já existe! Tente novamente.");
        }
        else {
            this.catalogoTransportadoras.put(t.getNome(), t.clone());
            return t.getNome();
        }
    }

    public String addArtigo(Artigo a) {
        if (!catalogoArtigos.containsKey(a.getCodigo())) {
            //this.catalogoArtigos.put(a.getCodigo(), a.clone());
            Object obj = this.catalogoArtigos.get(a.getCodigo());
            if (obj instanceof Mala) {
                this.catalogoArtigos.put(a.getCodigo(), ((Mala) obj).clone());
            }
            else if (obj instanceof Sapatilha) {
                this.catalogoArtigos.put(a.getCodigo(), ((Sapatilha) obj).clone());
            }
            else if (obj instanceof TShirt) {
                this.catalogoArtigos.put(a.getCodigo(), ((TShirt) obj).clone());
            }

            return a.getNome();
        }
        else {
            return "null";
        }
    }

    public String addMala(Mala a, String currentUser) {
        if (!catalogoArtigos.containsKey(a.getCodigo())) {
            Utilizador tmp_usr = catalogoUtilizadores.get(currentUser);
            tmp_usr.addArtigoProdutosAVenda(a.getCodigo(), a);

            this.catalogoArtigos.put(a.getCodigo(), a.clone());
            return a.getNome();
        }
        else {
            return "null";
        }
    }

    public String addSapatilha(Sapatilha a, String currentUser) {
        if (!catalogoArtigos.containsKey(a.getCodigo())) {
            Utilizador tmp_usr = catalogoUtilizadores.get(currentUser);
            tmp_usr.addArtigoProdutosAVenda(a.getCodigo(), a);
            
            this.catalogoArtigos.put(a.getCodigo(), a.clone());
            return a.getNome();
        }
        else {
            return "null";
        }
    }

    public String addTShirt(TShirt a, String currentUser) {
        if (!catalogoArtigos.containsKey(a.getCodigo())) {
            Utilizador tmp_usr = catalogoUtilizadores.get(currentUser);
            tmp_usr.addArtigoProdutosAVenda(a.getCodigo(), a);
            
            this.catalogoArtigos.put(a.getCodigo(), a.clone());
            return a.getNome();
        }
        else {
            return "null";
        }
    }
    
    public String showArtigos() {
        StringBuilder sb = new StringBuilder();
        Iterator<Artigo> it = this.catalogoArtigos.values().iterator();

        while (it.hasNext()) {
            Artigo artigo = it.next();
            //sb.append(artigo + "\n");
            sb.append(artigo);
            sb.append(", stock: " + artigo.getStock() + "\n");
        }

        return sb.toString();
    }

    public String showTransportadoras() {
        StringBuilder sb = new StringBuilder();
        Iterator<Transportadora> it = this.catalogoTransportadoras.values().iterator();

        while (it.hasNext()) {
            Transportadora transportadora = it.next();
            System.out.println(transportadora.getNome());
            sb.append(" - " + transportadora.getNome() + "\n");
        }

        return sb.toString();
    }

    public int getNumArtigos() {
        return catalogoArtigos.size();
    }

    public int getNumTransportadoras() {
        return catalogoTransportadoras.size();
    }

    public Integer addArtigoEncomenda(Integer option_codigo, Integer option_stock, String currentUser) {
        if (catalogoArtigos.containsKey(option_codigo)) {
            Artigo tmp_art = catalogoArtigos.get(option_codigo);
            int tmp_art_stock = tmp_art.getStock();

            if (tmp_art_stock < option_stock || option_stock <= 0) {
                return -1;
            }
            else {
                Utilizador tmp = catalogoUtilizadores.get(currentUser);
                Integer r = tmp.addArtigoEncomenda(option_codigo, option_stock);

                catalogoArtigosEncomenda.put(option_codigo, tmp_art);

                if (tmp_art_stock == option_stock) {
                    catalogoArtigos.remove(option_codigo);
                }
                else {
                    tmp_art.setStock(tmp_art_stock - option_stock);
                }

                return r;
            }
        }
        else {
            return 0;
        }
    }

    public void addArtigoEncomenda2(Integer option_codigo) {
        Artigo tmp_art = catalogoArtigosEncomenda.get(option_codigo);
        catalogoArtigos.put(option_codigo, tmp_art);
        catalogoArtigosEncomenda.remove(option_codigo);
    }

    public Integer removeArtigoEncomenda(Integer option_codigo, String currentUser) {
        Utilizador tmp = catalogoUtilizadores.get(currentUser);
        return tmp.removeArtigoEncomenda(option_codigo);
    }

    public String showEncomenda(String currentUser) {
        Utilizador tmp_usr = catalogoUtilizadores.get(currentUser);
        Encomenda tmp_enc = tmp_usr.getEncomenda();
        
        if (tmp_enc != null) {
            Map<Integer, Integer> artigosEnc = tmp_enc.getArtigos();
        
            if (artigosEnc.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n");
                for (Integer key : artigosEnc.keySet()) {
                    if (catalogoArtigosEncomenda.containsKey(key)) {
                        Artigo artigo = catalogoArtigosEncomenda.get(key);
                        sb.append(artigo.toString());
                        sb.append(", stock: " + artigosEnc.get(key));
                        sb.append("\n");
                    }
                }
    
                return sb.toString();
            }
            else {
                return "\nSem artigos na encomenda!";
            }
        }
        else {
            return "\nSem artigos na encomenda!";
        }
    }

   public String showEncomendasFeitas(String currentUser) {
       Utilizador tmp_usr = catalogoUtilizadores.get(currentUser);
       Map<Integer, Encomenda> encFeitas = tmp_usr.getEncomendasFeitas();

       StringBuilder sb = new StringBuilder();
       sb.append("\n");

       for (Integer codigo : encFeitas.keySet()) {
           Encomenda encomenda = encFeitas.get(codigo);
           sb.append(encomenda.toString());
           sb.append("\n");
       }

       return sb.toString();
   }

    public boolean checkUtilizadorExiste(String email) {
        return catalogoUtilizadores.containsKey(email);
    }

    public boolean checkTransportadoraExiste(String nome) {
        return catalogoTransportadoras.containsKey(nome);
    }

    public boolean checkEncomendaVazia(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        if (tmp_usr.getEncomenda() == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUtilizadorEncADevolver(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        Map<Integer, Encomenda> encFeitas = tmp_usr.getEncomendasFeitas();

        boolean r;

        if (encFeitas.isEmpty()) {
            r = false;
        }

        else {
            r = false;

            for (Integer codigo : encFeitas.keySet()) {
                Encomenda encomenda = encFeitas.get(codigo);
                LocalDateTime dataEnc = encomenda.getDataFinalizacao();
                LocalDateTime dataCurr = getCurrentDateTime();

                if (dataEnc != null) {

                    long hours = HOURS.between(dataEnc, dataCurr);
                
                    if (hours < 49) {
                        r = true;
                    }
                }
            }

        }

        return r;
    }

    public boolean checkUtilizadorTemFaturas(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        List<Fatura> faturas = tmp_usr.getFaturas();

        if (faturas.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkUtilizadorTemEncADevolver(Integer codigo, String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        Map<Integer, Encomenda> encFeitas = tmp_usr.getEncomendasFeitas();

        if (encFeitas.containsKey(codigo)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void initEncomendaUser(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        tmp_usr.setEncomenda();
    }

    public float calcularPrecoFinalEncomenda(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        Encomenda tmp_enc = tmp_usr.getEncomenda();

        Map<Integer, Integer> artigos = tmp_enc.getArtigos();
        float preco = 0.0f;
        float custoExpedicao = 0.0f;

        int artNovos = 0;
        int artUsados = 0;
        for (Integer codigo : artigos.keySet()) {
            Artigo tmp_art = catalogoArtigosEncomenda.get(codigo);
            Integer stock = artigos.get(codigo);
            preco += tmp_art.calcularPrecoFinal() * stock;

            if (tmp_art.getNovo()) {
                artNovos++;
            }
            else {
                artUsados++;
            }

            Transportadora tmp_trans = catalogoTransportadoras.get(tmp_art.getTransportadora());
            float custoExpedicao_tmp = tmp_trans.calculaPrecoExpedicao(tmp_enc.getTamanhoEmbalagem());
            tmp_trans.setLucroEfetivo(custoExpedicao_tmp);
            tmp_art.setCustosExpedicao(custoExpedicao_tmp);
            custoExpedicao += custoExpedicao_tmp;
        }

        preco += custoExpedicao;

        float taxas = artNovos * tmp_enc.TAXA_SATISFACAO_ARTIGO_NOVO + artUsados * tmp_enc.TAXA_SATISFACAO_ARTIGO_USADO;
        preco += taxas;

        preco += this.TAXA_DE_GARANTIA;
        this.lucro += this.TAXA_DE_GARANTIA;

        tmp_enc.setTamanhoEmbalagem();
        TamanhoEmbalagem tamanho = tmp_enc.getTamanhoEmbalagem();
        //tmp_trans = catalogoTransportadoras.get()
        //preco += tmp_enc.calculaPrecoExpedicao(tamanho);
        //tmp_enc.setEstado(EstadoEncomenda.FINALIZADA);
        tmp_enc.setPrecoFinal(preco);

        return preco;
    }

    public void finalizarCompra(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        Encomenda tmp_enc = tmp_usr.getEncomenda();
        Map<Integer, Integer> artigos = tmp_enc.getArtigos();

        for (Integer codigo : artigos.keySet()) {
            Artigo tmp_art = catalogoArtigosEncomenda.get(codigo);
            Utilizador vendedor = catalogoUtilizadores.get(tmp_art.getVendedor());

            artigosVendidos.put(codigo, tmp_art);
            catalogoArtigosEncomenda.remove(codigo);
            
            Integer stock = artigos.get(codigo);
            vendedor.addArtigoProdutosVendidos(codigo, tmp_art, stock);
            vendedor.removeArtigoProdutosAVenda(codigo);
        }

        tmp_usr.addEncomendaEncomendasFeitas(tmp_enc.getCodigo(), tmp_enc);

        tmp_enc.setEstado(EstadoEncomenda.EXPEDIDA);
        LocalDateTime dataPrevista = currentDateTime.plusDays((long) (Math.random() * 9) + 2);
        tmp_enc.setDataPrevistaEntrega(dataPrevista);

        tmp_usr.setEncomendaNull();
    }

    public void devolverEncomenda(String nome, Integer codigo_enc) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        Map<Integer, Encomenda> encFeitas = tmp_usr.getEncomendasFeitas();
        Encomenda encomenda = encFeitas.get(codigo_enc);

        Map<Integer, Integer> artigos = encomenda.getArtigos();

        for (Integer codigo : artigos.keySet()) {
            Artigo tmp_art = artigosVendidos.get(codigo);
            Utilizador vendedor = catalogoUtilizadores.get(tmp_art.getVendedor());

            vendedor.removeArtigoProdutosVendidos(codigo);
            vendedor.addArtigoProdutosAVenda(codigo, tmp_art);

            if (!catalogoArtigos.containsKey(codigo)) {
                catalogoArtigos.put(codigo, tmp_art);
            }
            else {
                int stock_comprado = artigos.get(codigo);
                int stock_existente = tmp_art.getStock();
                tmp_art.setStock(stock_comprado + stock_existente);
                catalogoArtigos.put(codigo, tmp_art);
            }
        }

        tmp_usr.removeEncomendaEncomendasFeitas(codigo_enc);
        encomenda.setEstado(EstadoEncomenda.DEVOLVIDA);
    }

    public String getVendasUtilizador(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        
        StringBuilder sb = new StringBuilder();
        Iterator<Artigo> it = tmp_usr.getProdutosVendidos().values().iterator();

        while (it.hasNext()) {
            Artigo artigo = it.next();
            sb.append(artigo + "\n");
        }

        return sb.toString();
   }

   public String getMaiorVendedor() {
        Utilizador maiorVendedor = null;
        float maiorValor = -1.0f;

        for (Utilizador vendedor : catalogoUtilizadores.values()) {
            float valor = vendedor.getTotalVendas();

            if (valor > maiorValor) {
                maiorValor = valor;
                maiorVendedor = vendedor;
            }
        }

        return maiorVendedor.getEmail();
   }

    public String getMaiorVendedorPeriodo(LocalDateTime date1, LocalDateTime date2) {
        Utilizador maiorVendedor = null;
        float maiorValor = -1.0f;

        for (Utilizador vendedor : catalogoUtilizadores.values()) {
            float valor = vendedor.getTotalVendasPeriodo(date1, date2);

            if (valor > maiorValor) {
                maiorValor = valor;
                maiorVendedor = vendedor;
            }
        }

        return maiorVendedor.getEmail();
    }

   public float getMaiorVendedorValor(String nome) {
       Utilizador tmp_usr = catalogoUtilizadores.get(nome);
       return tmp_usr.getTotalVendas();
   }

   public float getMaiorVendedorValorPeriodo(String nome, LocalDateTime date1, LocalDateTime date2) {
       Utilizador tmp_usr = catalogoUtilizadores.get(nome);
       return tmp_usr.getTotalVendasPeriodo(date1, date2);
   }

   public String getMaiorTransportadora() {
       Transportadora maiorTrans = null;
       float maiorValor = -1.0f;

       for (Transportadora t : catalogoTransportadoras.values()) {
           float valor = t.getLucroEfetivo();

           if (valor > maiorValor) {
               maiorValor = valor;
               maiorTrans = t;
           }
       }

       return maiorTrans.getNome();
   }

    public float getMaiorTransportadoraValor(String nome) {
        Transportadora tmp_tra = catalogoTransportadoras.get(nome);
        return tmp_tra.getLucroEfetivo();
    }

    public void avancar(int hours) {
        this.currentDateTime = this.currentDateTime.plus((long) hours, ChronoUnit.HOURS);
    }

    public void startSimulation() {
        for (Map.Entry<String, Utilizador> entry : catalogoUtilizadores.entrySet()) {
            Utilizador tmp_usr = entry.getValue();
            Map<Integer, Encomenda> encFeitas = tmp_usr.getEncomendasFeitas();

            for (Encomenda encomenda : encFeitas.values()) {
                EstadoEncomenda estado = encomenda.getEstado();

                if (estado == EstadoEncomenda.EXPEDIDA) {
                    LocalDateTime dataPrevista = encomenda.getDataPrevistaEntrega();
                    if (dataPrevista.isBefore(currentDateTime)) {
                        encomenda.setEstado(EstadoEncomenda.FINALIZADA);
                        encomenda.setDataFinalizacao(dataPrevista);

                        geraFaturaComprador(tmp_usr, encomenda);
                        
                        Map<Integer, Integer> artigos = encomenda.getArtigos();
                        for (Integer codigo : artigos.keySet()) {
                            Artigo artigo = artigosVendidos.get(codigo);
                            String nomeVendedor = artigo.getVendedor();
                            Utilizador tmp_vend = catalogoUtilizadores.get(nomeVendedor);
                            geraFaturaVendedor(tmp_vend, encomenda, artigo);
                        }
                    }
                }
            }
        }
    }

    public void geraFaturaVendedor(Utilizador vendedor, Encomenda encomenda, Artigo artigo) {
        Integer codigo = artigo.getCodigo();
        Integer stock = encomenda.getArtigos().get(codigo);
        float custo = artigo.getPrecoFinal() * stock;
        Tipo tipo = Tipo.VENDA;
        LocalDateTime data = encomenda.getDataFinalizacao();
        List<Integer> artigos = new ArrayList<Integer>();
        artigos.add(artigo.getCodigo());

        Fatura fatura = new Fatura(custo, tipo, encomenda, data, artigos);

        vendedor.addFatura(fatura);
    }

    public void geraFaturaComprador(Utilizador comprador, Encomenda encomenda) {
        float custo = encomenda.getPrecoFinal();
        Tipo tipo = Tipo.COMPRA;
        LocalDateTime data = encomenda.getDataFinalizacao();
        List<Integer> artigos = new ArrayList<>(encomenda.getArtigos().keySet());

        Fatura fatura = new Fatura(custo, tipo, encomenda, data, artigos);

        comprador.addFatura(fatura);
    }

    public String showFaturas(String nome) {
        Utilizador tmp_usr = catalogoUtilizadores.get(nome);
        List<Fatura> faturas = tmp_usr.getFaturas();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Fatura fatura : faturas) {
           sb.append(fatura.toString());
           sb.append("\n");
        }

        return sb.toString();
    }

    public void alterarValorTransportadora(String transportadora, int option, float valor) {
        Transportadora tmp_t = catalogoTransportadoras.get(transportadora);
        
        switch (option) {
            case 1:
                tmp_t.setValorExpEncPeq(valor);
            case 2:
                tmp_t.setValorExpEncMed(valor);
            case 3:
                tmp_t.setValorExpEncGra(valor);
            case 4:
                tmp_t.setMargemLucro(valor);
            case 5:
                tmp_t.setImposto(valor);
        }
    }

    public float showValorTransportadora(String transportadora, int option) {
        Transportadora tmp_t = catalogoTransportadoras.get(transportadora);
        float result = 0.0f;

        switch (option) {
            case 1:
                result = tmp_t.getValorExpEncPeq();
            case 2:
                result = tmp_t.getValorExpEncMed();
            case 3:
                result = tmp_t.getValorExpEncGra();
            case 4:
                result = tmp_t.getMargemLucro();
            case 5:
                result = tmp_t.getImposto();
        }

        return result;
    }

    public String showFormulaTransportadora(String transportadora) {
        Transportadora tmp_t = catalogoTransportadoras.get(transportadora);
        return tmp_t.getFormula();
    }

    public void alterarFormulaTransportadora(String transportadora, String formula) {
        Transportadora tmp_t = catalogoTransportadoras.get(transportadora);
        tmp_t.setFormula(formula);
    }

    public Map<String, Integer> getListaMaioresVendedores(LocalDateTime date1, LocalDateTime date2) {
        Map<String, Integer> topVendedores = new HashMap<>();

        for (Utilizador k : catalogoUtilizadores.values()) {
            topVendedores.put(k.getEmail(), k.numeroVendasEfetuadasDuranteUmPeriodoTempo(date1, date2));
        }

        List<Map.Entry<String, Integer>> topvendedoreslist = new ArrayList<>(topVendedores.entrySet());
        Collections.sort(topvendedoreslist, Map.Entry.comparingByValue());
        Map<String, Integer> topvendedoresOrdMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : topvendedoreslist){
            topvendedoresOrdMap.put(entry.getKey(), entry.getValue());
        }

        return topvendedoresOrdMap;
    }
}
