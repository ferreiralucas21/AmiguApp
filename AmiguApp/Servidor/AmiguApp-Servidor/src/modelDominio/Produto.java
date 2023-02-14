package modelDominio;

import java.io.Serializable;


public class Produto implements Serializable {
    
    private static final long serialVersionID = 123456789L;
    
    private int idProduto;
    private String nome;
    private float preco;
    private float tamanho;
    private String descricao;
    private byte[] imagem;
    private int fkIdVendedor;

    public Produto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public Produto(int idProduto, String nome, float preco, float tamanho, String descricao, byte[] imagem, int fkIdVendedor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = descricao;
        this.imagem = imagem;
        this.fkIdVendedor = fkIdVendedor;
    }

    public Produto(String nome, float preco, float tamanho, String descricao, byte[] imagem, int fkIdVendedor) {
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = descricao;
        this.imagem = imagem;
        this.fkIdVendedor = fkIdVendedor;
    }
    
    public Produto(int idProduto, String nome) {
        this.idProduto = idProduto;
        this.nome = nome;
    }

    public Produto(String nome, float preco, float tamanho, String descricao, int fkIdVendedor) {
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = descricao;
        this.fkIdVendedor = fkIdVendedor;
    }

    public Produto(String nome, float preco, float tamanho, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = descricao;
    }

    public Produto(int idProduto, String nome, float preco, float tamanho, String descricao, int fkIdVendedor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = descricao;
        this.fkIdVendedor = fkIdVendedor;
    }

    public Produto(int idProduto, String nome, float preco, float tamanho, String descricao) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.descricao = descricao;
    }    
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public int getFkIdVendedor() {
        return fkIdVendedor;
    }

    public void setFkIdVendedor(int fkIdVendedor) {
        this.fkIdVendedor = fkIdVendedor;
    }
    
    @Override
    public String toString() {
        return "Produto{" + "serialVersionID=" + serialVersionID + ", idProduto=" + idProduto + ", nome=" + nome + ", preco=" + preco + ", tamanho=" + tamanho + ", descricao=" + descricao + ", imagem=" + imagem + '}';
    }
       
}
