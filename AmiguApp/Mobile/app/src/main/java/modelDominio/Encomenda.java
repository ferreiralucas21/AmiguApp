package modelDominio;

import java.io.Serializable;


public class Encomenda implements Serializable {

    private static final long serialVersionUID = 123456789L;

    private int idEncomenda;
    private int quantidade;
    private String status;
    private int fkIdProduto;
    private int fkIdCliente;
    private Produto produto;
    private Cliente cliente;

    public Encomenda(int idEncomenda, int quantidade, String status, int fkIdProduto, int fkIdCliente, Produto produto, Cliente cliente) {
        this.idEncomenda = idEncomenda;
        this.quantidade = quantidade;
        this.status = status;
        this.fkIdProduto = fkIdProduto;
        this.fkIdCliente = fkIdCliente;
        this.produto = produto;
        this.cliente = cliente;
    }

    public Encomenda(Produto produto, Cliente cliente, int quantidade) {
        this.produto = produto;
        this.cliente = cliente;
        this.quantidade = quantidade;
    }

    public Encomenda(int idEncomenda, Produto produto, Cliente cliente, int quantidade, String status) { //mudar manuelamente no mobile
        this.idEncomenda = idEncomenda;
        this.produto = produto;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.status = status;
    }

    public Encomenda(int idEncomenda, Produto produto) { //construtor prcisa ser inserido no mobile
        this.idEncomenda = idEncomenda;
        this.produto = produto;
    }

    public Encomenda(Produto produto) {
        this.produto = produto;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFkIdProduto() {
        return fkIdProduto;
    }

    public void setFkIdProduto(int fkIdProduto) {
        this.fkIdProduto = fkIdProduto;
    }

    public int getFkIdCliente() {
        return fkIdCliente;
    }

    public void setFkIdCliente(int fkIdCliente) {
        this.fkIdCliente = fkIdCliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Encomenda{" + "idEncomenda=" + idEncomenda + ", quantidade=" + quantidade + ", status=" + status + ", fkIdProduto=" + fkIdProduto + ", fkIdCliente=" + fkIdCliente + ", produto=" + produto + ", cliente=" + cliente + '}';
    }

}
