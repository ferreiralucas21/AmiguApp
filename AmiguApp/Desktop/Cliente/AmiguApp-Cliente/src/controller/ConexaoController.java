/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelDominio.Vendedor;

/**
 *
 * @author ADMIN
 */
public class ConexaoController {
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int idUnico;
    
    public Vendedor vendedor;
    
    public ConexaoController(ObjectInputStream in, ObjectOutputStream out, int idUnico) {
        this.in = in;
        this.out = out;
        this.idUnico = idUnico;
    }
    
    public Vendedor efetuarLogin(Vendedor vendedor) {
        String msg;
        try {
            out.writeObject("VendedorEfetuarLogin");
            msg = (String) in.readObject();
            out.writeObject(vendedor);
            Vendedor vendSelecionado = (Vendedor) in.readObject();
            return vendSelecionado;
        } catch (Exception e) {
            return null;
        }
    }
    
}
