package modelDominio;

public class ProdutoLoja {
    private String pNomeLoja;
    private String pNomeProduto;
    private float pPreco;
    private String lNomeLoja;

    public ProdutoLoja(String pNomeLoja, String pNomeProduto, float pPreco, String lNomeLoja) {
        this.pNomeLoja = pNomeLoja;
        this.pNomeProduto = pNomeProduto;
        this.pPreco = pPreco;
        this.lNomeLoja = lNomeLoja;
    }

    public ProdutoLoja(String pNomeLoja, String pNomeProduto, float pPreco) {
        this.pNomeLoja = pNomeLoja;
        this.pNomeProduto = pNomeProduto;
        this.pPreco = pPreco;
    }

    public ProdutoLoja(String lNomeLoja) {
        this.lNomeLoja = lNomeLoja;
    }

    public String getpNomeLoja() {
        return pNomeLoja;
    }

    public void setpNomeLoja(String pNomeLoja) {
        this.pNomeLoja = pNomeLoja;
    }

    public String getpNomeProduto() {
        return pNomeProduto;
    }

    public void setpNomeProduto(String pNomeProduto) {
        this.pNomeProduto = pNomeProduto;
    }

    public float getpPreco() {
        return pPreco;
    }

    public void setpPreco(float pPreco) {
        this.pPreco = pPreco;
    }

    public String getlNomeLoja() {
        return lNomeLoja;
    }

    public void setlNomeLoja(String lNomeLoja) {
        this.lNomeLoja = lNomeLoja;
    }
}
