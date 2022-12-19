/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


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
