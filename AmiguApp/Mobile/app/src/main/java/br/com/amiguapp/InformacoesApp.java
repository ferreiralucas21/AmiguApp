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

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }
}
