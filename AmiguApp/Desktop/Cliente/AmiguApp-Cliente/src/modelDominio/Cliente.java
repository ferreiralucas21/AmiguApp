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
public class Cliente implements Serializable {
    
    private final long serialVersionID = 12345678L;
    
    private int idCliente;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String cpf;
    private String rua;
    private String bairro;
    private String complemento;
    private String cep;

    public Cliente(int idCliente, String nome, String email, String telefone, String senha, String cpf, String rua, String bairro, String complemento, String cep) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf = cpf;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
    }
    
    public Cliente(String nome, String email, String telefone, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf = cpf;
    }

    public Cliente(String nome, String email, String telefone, String senha, String cpf, String rua, String bairro, String complemento, String cep) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf = cpf;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Cliente(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(int idCliente, String nome, String email, String senha) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(String nome, String email, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Cliente(int idCliente, String nome, String email, String telefone, String senha) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Cliente(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }  

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Cliente{" + "serialVersionID=" + serialVersionID + ", idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + ", cpf=" + cpf + ", rua=" + rua + ", bairro=" + bairro + ", complemento=" + complemento + ", cep=" + cep + '}';
    }
    
    
    
}
