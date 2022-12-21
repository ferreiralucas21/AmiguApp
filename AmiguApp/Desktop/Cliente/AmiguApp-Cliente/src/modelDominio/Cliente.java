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
    private int telefone;
    private int senha;
    private int cpf;
    private String rua;
    private String bairro;
    private String complemento;
    private int cep;

    public Cliente(int idCliente, String nome, String email, int telefone, int senha, int cpf, String rua, String bairro, String complemento, int cep) {
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
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

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Cliente{" + "serialVersionID=" + serialVersionID + ", idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + ", cpf=" + cpf + ", rua=" + rua + ", bairro=" + bairro + ", complemento=" + complemento + ", cep=" + cep + '}';
    }
    
    
    
}
