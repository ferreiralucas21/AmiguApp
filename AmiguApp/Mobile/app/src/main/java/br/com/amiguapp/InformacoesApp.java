package br.com.amiguapp;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDominio.Cliente;
import modelDominio.Produto;

public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Cliente clienteLogado;
    private Produto produtoSelecionado;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void setClienteInserido() {
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}
