package modelDominio;

import java.io.Serializable;


public class Encomenda implements Serializable {

    private static final long serialVersionUID = 123456789L;

    private int idEncomenda;
    private int quantidade;
    private String status;
    private Produto produto;
    private Cliente cliente;
    private Vendedor vendedor;

    public Encomenda(int idEncomenda, int quantidade, String status, Produto produto, Cliente cliente, Vendedor vendedor) {
        this.idEncomenda = idEncomenda;
        this.quantidade = quantidade;
        this.status = status;
        this.produto = produto;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public Encomenda(int idEncomenda, int quantidade, String status, Produto produto, Vendedor vendedor) {
        this.idEncomenda = idEncomenda;
        this.quantidade = quantidade;
        this.status = status;
        this.produto = produto;
        this.vendedor = vendedor;
    }

    public Encomenda(Produto produto, Cliente cliente, int quantidade) {
        this.produto = produto;
        this.cliente = cliente;
        this.quantidade = quantidade;
    }

    public Encomenda(int idEncomenda, Produto produto, Vendedor vendedor, Cliente cliente, int quantidade, String status) { //mudar manuelamente no mobile
        this.idEncomenda = idEncomenda;
        this.produto = produto;
        this.vendedor = vendedor;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Encomenda{" + "idEncomenda=" + idEncomenda + ", quantidade=" + quantidade + ", status=" + status + ", produto=" + produto + ", cliente=" + cliente + ", vendedor=" + vendedor + '}';
    }

}
