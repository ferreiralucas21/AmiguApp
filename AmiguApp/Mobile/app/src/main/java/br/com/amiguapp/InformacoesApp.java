package br.com.amiguapp;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDominio.Cliente;

public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Cliente clienteLogado;
    private Cliente clienteInserido;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public Cliente getClienteInserido() {
        return clienteInserido;
    }

    public void setClienteInserido(Cliente clienteInserido) {
        this.clienteInserido = clienteInserido;
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }
}
