/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ClienteDAO;
import modelDominio.Cliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.EncomendaDAO;
import model.ProdutoDAO;
import model.VendedorDAO;
import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;


/**
 *
 * @author ADMIN
 */
public class TrataClienteController extends Thread {
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket s;
    private int idUnico;
    
    //Construtor
    public TrataClienteController(Socket s, ObjectInputStream in, ObjectOutputStream out, int idUnico) {
        this.s = s;
        this.in = in;
        this.out = out;
        this.idUnico = idUnico;
        this.start();
    }

    @Override
    public void run() {
        String comando;
        System.out.println("Esperando comandos do cliente");
        try {
            comando = (String) in.readObject();
            //enquando comando não for "fim" permanece no looping
            while (!comando.equalsIgnoreCase("fim")) {
                System.out.println("Cliente " + idUnico + " enviou o comando: " + comando );
                //Qual é o comando que o cliente quer que o servidor execute?
                if (comando.equalsIgnoreCase("VendedorEfetuarLogin")) {
                    out.writeObject("ok");
                    Vendedor vendedor = (Vendedor) in.readObject();
                    
                    VendedorDAO dao = new VendedorDAO();
                    Vendedor vendedorSelecionado = dao.efetuarLogin(vendedor);
                    
                    out.writeObject(vendedorSelecionado);
                    
                } else if (comando.equalsIgnoreCase("ClienteEfetuarLogin")) {
                    out.writeObject("ok");
                    Cliente cliente = (Cliente) in.readObject();
                    
                    ClienteDAO dao = new ClienteDAO();
                    Cliente clienteSelecionado = dao.efetuarLogin(cliente);
                    
                    out.writeObject(clienteSelecionado);
                    
                } else if (comando.equalsIgnoreCase("VendedorInserir")) {
                    out.writeObject("ok");
                    Vendedor vendedor = (Vendedor) in.readObject();
                    
                    VendedorDAO dao = new VendedorDAO();
                    if (dao.inserirVendedor(vendedor) == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                                                     
                } else if (comando.equalsIgnoreCase("ClienteInserir")) {
                    out.writeObject("ok");
                    Cliente cliente = (Cliente) in.readObject();
                    
                    ClienteDAO dao = new ClienteDAO();
                    if (dao.inserirCliente(cliente) == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                    
                } else if (comando.equalsIgnoreCase("ProdutoInserir")) {
                    out.writeObject("ok");
                    Produto produto = (Produto) in.readObject();
                    
                    ProdutoDAO dao = new ProdutoDAO();
                    if(dao.inserirProduto(produto) == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");                       
                    }
                    
                } else if (comando.equalsIgnoreCase("ProdutoLista")) { //Listagem para o desktop
                    out.writeObject("ok");
                    Vendedor vendedor = (Vendedor) in.readObject();
                    ProdutoDAO dao = new ProdutoDAO();
                    out.writeObject(dao.getLista(vendedor));
                    
                } else if (comando.equalsIgnoreCase("ListaVendedores")) { //Listagem para o mobile
                    out.writeObject("ok");
                    VendedorDAO dao = new VendedorDAO();
                    out.writeObject(dao.getLista());
                    
                } else if (comando.equalsIgnoreCase("ListaProdutos")) { //Listagem para o mobile
                    out.writeObject("ok");
                    ProdutoDAO dao = new ProdutoDAO();
                    out.writeObject(dao.getLista());
                    
                } else if (comando.equalsIgnoreCase("ProdutoListaNome")) { //Listagem para o filtro de pesquisa do desktop
                    out.writeObject("ok");
                    // Nessa consulta eu preciso esperar o nome para realizar um filtro
                    String nome = (String) in.readObject();
                    ProdutoDAO dao = new ProdutoDAO();
                    ArrayList<Produto> listaProdutos = dao.getListaProdutosNome(nome);
                    out.writeObject(listaProdutos);
                    
                } else if (comando.equalsIgnoreCase("VendedorPerfil")) { //Atualização dos campos do pefil do vendedor 
                    out.writeObject("ok");
                    int codigo = (int) in.readObject();
                    VendedorDAO dao = new VendedorDAO();
                    out.writeObject(dao.perfilVendedor(codigo));
                
                } else if (comando.equalsIgnoreCase("ProdutoAlterar")) {
                    out.writeObject("ok");                   
                    // Esperando o objeto Produtou vir do cliente
                    Produto produto = (Produto) in.readObject();
                    // Crinado um DAO para armazenar no bano
                    ProdutoDAO dao = new ProdutoDAO();
                    if (dao.alterar(produto) == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                    
                } else if (comando.equalsIgnoreCase("VendedorAlterar")) {
                    out.writeObject("ok");                   
                    // Esperando o objeto Produtou vir do cliente
                    Vendedor vendedor = (Vendedor) in.readObject();
                    // Crinado um DAO para armazenar no bano
                    VendedorDAO dao = new VendedorDAO();
                    if (dao.alterarVendedor(vendedor) == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                    
                } else if (comando.equalsIgnoreCase("ProdutoExcluir")) {
                    out.writeObject("ok");
                    Produto produto = (Produto) in.readObject();
                    ProdutoDAO dao = new ProdutoDAO();
                    if (dao.excluir(produto) == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                    
                } else if (comando.equalsIgnoreCase("EncomendaInserir")) { //Adicionar encomenda para o mobile
                    out.writeObject("ok");
                    Encomenda encomenda = (Encomenda) in.readObject();
                    
                    EncomendaDAO dao = new EncomendaDAO();
                    if(dao.inserirEncomenda(encomenda)== -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");                       
                    }
                                                    
                } else {
                    //Comando inválido e não reconhecido!
                    //Cliente pediu um comando que o servidor não conhece.
                    out.writeObject("nok");
                }
                comando = (String) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            //Fechando recursos ocupados pelo cliente.
            System.out.println("Cliente " + idUnico + "finalizou a conexão");
            this.in.close();
            this.out.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
