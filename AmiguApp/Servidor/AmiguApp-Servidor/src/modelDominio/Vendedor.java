/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Vendedor implements Serializable {
    
    private static final long serialVersionID = 123456789L;
    
    private int idVendedor;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    public Vendedor(int idVendedor, String nome, String email, String telefone, String senha) {
        this.idVendedor = idVendedor;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Vendedor(String nome, String email, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Vendedor(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Vendedor(int idVendedor, String nome, String email, String telefone) {
        this.idVendedor = idVendedor;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
       
    public Vendedor(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Vendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Vendedor(int idVendedor, String nome) {
        this.idVendedor = idVendedor;
        this.nome = nome;
    }    
   
    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "idVendedor=" + idVendedor + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + '}';
    }
    
    

    
}
