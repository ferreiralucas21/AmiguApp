/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TrataClienteController;
import factory.Conector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import modelDominio.Vendedor;

/**
 *
 * @author ADMIN
 */
public class Principal {
    
    public static void main(String[] args) {
        Connection con;
        con = Conector.getConnection();
        
        try {
            // iniciando servidor na porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Sevidor inicializado. Aguardando conexão...");
            ConectaServidor s1 = new ConectaServidor(servidor, con);
            
            //Vendedor vendedor = new Vendedor(2, "Julia", "julia@gmail.com", 998984557, "123456");
            //System.out.println(vendedor);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
}

class ConectaServidor extends Thread {
    
    private ServerSocket servidor;
    private int idUnico;
    private Connection con;
    
    public ConectaServidor(ServerSocket servidor, Connection con) {
        this.servidor = servidor;
        this.con = con;
        this.start();
    }
    
    public void run() {
        try {
            while (true) {
                Socket cliente = this.servidor.accept();
                System.out.println("Um novo cliente conectou :" + cliente);
                
                //Crinado um objeto de streaming para receber e enviar os dados
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                idUnico++;
                System.out.println("Iniciando uma nova Thread para o Cliente " + idUnico);
                TrataClienteController tratacliente = new TrataClienteController(cliente, in, out, idUnico);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
