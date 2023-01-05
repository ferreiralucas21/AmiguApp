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
    
    private final long serialVersionID = 123456789L;
    
    private int idVendedor;
    private String nome;
    private String email;
    private int telefone;
    private String senha;
    private byte[] imagem;

    public Vendedor(int idVendedor, String nome, String email, int telefone, String senha, byte[] imagem) {
        this.idVendedor = idVendedor;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.imagem = imagem;
    }

    public Vendedor(int idVendedor, String nome, String email, int telefone, String senha) {
        this.idVendedor = idVendedor;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Vendedor(String nome, String email, int telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
    
    public Vendedor(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "serialVersionID=" + serialVersionID + ", idVendedor=" + idVendedor + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + ", imagem=" + imagem + '}';
    }
    
    
    
}
