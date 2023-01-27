package br.com.amiguapp.modelDominio;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionID = 12345678L;
    private String nome;
    private String email;
    private int telefone;
    private String senha;
    private String rua;
    private String bairro;
    private String complemento;
    private int cep;


    public Cliente(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Cliente(String nome, int telefone, String rua, String bairro, String complemento, int cep) {
        this.nome = nome;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
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
}
