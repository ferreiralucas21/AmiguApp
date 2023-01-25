package br.com.amiguapp.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.amiguapp.InformacoesApp;
import br.com.amiguapp.modelDominio.Cliente;

public class ConexaoSocketController {
    InformacoesApp informacoesApp;

    public ConexaoSocketController(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }

    public boolean criaConexao(){
        boolean resultado;
        try{
            informacoesApp.socket = new Socket("10.0.2.2", 12345);
            informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
            informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());

            resultado = true;

        }catch(IOException ioe){
            ioe.printStackTrace();
            resultado = false;
        }
        return resultado;
    }

    public Cliente autenticaCliente(Cliente cliente){
        Cliente clienteLogado = null;
        try{
            informacoesApp.out.writeObject("autenticaCliente");
            String msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("ok")){
                informacoesApp.out.writeObject(cliente);
                clienteLogado = (Cliente) informacoesApp.in.readObject();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (ClassNotFoundException classe){
            classe.printStackTrace();
        }
        return clienteLogado;
    }

    public String enviaRecebeString(String msgEnviar){
        String msgRecebida = "";
        try{
            informacoesApp.out.writeObject(msgEnviar);
            msgRecebida = (String) informacoesApp.in.readObject();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (ClassNotFoundException classe){
            classe.printStackTrace();
        }
        return msgRecebida;
    }
}