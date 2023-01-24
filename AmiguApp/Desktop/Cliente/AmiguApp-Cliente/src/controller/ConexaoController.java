/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelDominio.Produto;
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
    public Produto produto;
    
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
            Vendedor vendedorSelecionado = (Vendedor) in.readObject();
            return vendedorSelecionado;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String inserirVendedor(Vendedor vendedor) {
        String msg;
        try {
            out.writeObject("VendedorInserir");
            msg = (String) in.readObject();
            if (msg.equals("ok")) {
                out.writeObject(vendedor);
                return (String) in.readObject();
            } else {
                return "nok";
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public String inserirProduto(Produto produto) {
        String msg;
        try {
            out.writeObject("ProdutoInserir");
            msg = (String) in.readObject();
            if (msg.equals("ok")) {
                out.writeObject(produto);
                return (String) in.readObject();
            } else {
                return "nok";
            }
        } catch (Exception e) {
            return null;           
        }
    }
    
    public ArrayList<Produto> produtoLista() {
        String msg;
        try {
            out.writeObject("ProdutoLista");
            msg = (String) in.readObject();
            if (msg.equals("ok")) {
                return (ArrayList<Produto>) in.readObject();
            } else {
                return null;
            }          
        } catch (Exception e) {
            return null;
        }
    }
    
    public String alterarProduto(Produto produto){
        String msg = "";
        try {
            out.writeObject("ProdutoAlterar");
            msg = (String) in.readObject();
            out.writeObject(produto);
            msg = (String) in.readObject();
            return msg;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String excluirProduto(Produto produto) {
        String msg = "";
        try {
            out.writeObject("ProdutoExcluir");
            msg = (String) in.readObject();
            out.writeObject(produto);
            msg = (String) in.readObject();
            return msg;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Produto> produtoListaNome(String nome) {
        String msg;
        try {
            out.writeObject("ProdutoListaNome");
            msg = (String) in.readObject();
            out.writeObject(nome);
            ArrayList<Produto> listaProdutos = (ArrayList<Produto>) in.readObject();
            return listaProdutos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String alterarVendedor (Vendedor vendedor) {
        String msg = "";
        try {
            out.writeObject("VendedorAlterar");
            msg = (String) in.readObject();
            out.writeObject(vendedor);
            msg = (String) in.readObject();
            return msg;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
