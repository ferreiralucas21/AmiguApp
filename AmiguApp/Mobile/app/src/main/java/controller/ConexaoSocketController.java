package controller;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import br.com.amiguapp.InformacoesApp;
import modelDominio.Cliente;
import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;

public class ConexaoSocketController {
    InformacoesApp informacoesApp;

    public ConexaoSocketController(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }

    public boolean criaConexao() {
        boolean resultado;
        try {
            informacoesApp.socket = new Socket("10.0.2.2", 12345);
            informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
            informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());

            resultado = true;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            resultado = false;
        }
        return resultado;
    }

    public Cliente efetuarLogin(Cliente cliente) {
        Cliente clienteLogado = null;
        try {
            informacoesApp.out.writeObject("ClienteEfetuarLogin");
            String msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("ok")) {
                informacoesApp.out.writeObject(cliente);
                clienteLogado = (Cliente) informacoesApp.in.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
        }
        return clienteLogado;
    }

    public String inserirCliente(Cliente cliente) {
        String msgRecebida = "";
        try {
            informacoesApp.out.writeObject("ClienteInserir");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("ok")) {
                informacoesApp.out.writeObject(cliente);
                msgRecebida = (String) informacoesApp.in.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
        }
        return msgRecebida;
    }

    public String enviaRecebeString(String msgEnviar) {
        String msgRecebida = "";
        try {
            informacoesApp.out.writeObject(msgEnviar);
            msgRecebida = (String) informacoesApp.in.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
        }
        return msgRecebida;
    }

    public ArrayList<Produto> listaProdutos() {
        ArrayList<Produto> listaProdutos = null;
        try {
            informacoesApp.out.writeObject("ListaProdutos");
            String msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("ok")) {
                listaProdutos = (ArrayList<Produto>) informacoesApp.in.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaProdutos = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaProdutos = null;
        }
        return listaProdutos;
    }

    public ArrayList<Vendedor> listaVendedores() {
        ArrayList<Vendedor> listaVendedores = null;
        try {
            informacoesApp.out.writeObject("ListaVendedores");
            String msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("ok")) {
                listaVendedores = (ArrayList<Vendedor>) informacoesApp.in.readObject();
            }else{
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaVendedores = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaVendedores = null;
        }
        return listaVendedores;
    }

    public String inserirEncomenda(Encomenda encomenda) {
        String msgRecebida = "";
        try {
            informacoesApp.out.writeObject("EncomendaInserir");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("ok")) {
                informacoesApp.out.writeObject(encomenda);
                msgRecebida = (String) informacoesApp.in.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
        }
        return msgRecebida;
    }
}
