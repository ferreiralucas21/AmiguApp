/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelDominio.Cliente;
import modelDominio.Vendedor;

/**
 *
 * @author julia
 */
public class ClienteDAO {
    private Connection con;
    
    public ClienteDAO() {
        con = Conector.getConnection();
    }
       
    public Cliente efetuarLogin(Cliente cliente) {
        PreparedStatement stmt = null; //usado para rodar SQL
        Cliente clienteSelecionado = null;
        String senhaHash = null;
        
        try {
        
            MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256
        
            BigInteger senhaHashDigitada = new BigInteger(1, md.digest(cliente.getSenha().getBytes()));
            senhaHash = senhaHashDigitada.toString();          
            
        } catch (NoSuchAlgorithmException e) {
             System.out.println("Erro ao carregar o MessageDigest");
        }
        
        try {
            //passando a string SQL que faz o SELECT
            String sql = " select * from cliente " + " where email = ? and senha = ?";
            stmt = con.prepareStatement(sql);
            //substituir os ? do script SQL
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, senhaHash);
            
            //Executando o select
            ResultSet res = stmt.executeQuery();
            
            //Percorrendo o resultado - res
            while (res.next()) {
                clienteSelecionado = new Cliente(res.getInt("idcliente"),
                                  res.getString("nome"),
                                  res.getString("email"),
                                  res.getString("telefone"),
                                  res.getString("senha"),
                                  res.getString("cpf"),
                                  res.getString("rua"),
                                  res.getString("bairro"),
                                  res.getString("complemento"),
                                  res.getString("cep"));
            }
            res.close();//fechando o resultado
            stmt.close();//fechando o statement
            con.close();//fechando a conexão com o banco
            return clienteSelecionado;//retornando a lista de
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
    public int inserirCliente(Cliente cliente) {
        PreparedStatement stmt = null; //usado para rodar SQL
        try {
            try {
                con.setAutoCommit(false);
                String sql = "insert into Cliente (nome,email,telefone,senha,cpf) values(?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                String senhaHash = null;
                try {

                    MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256

                    BigInteger senhaHashCadastrada = new BigInteger(1, md.digest(cliente.getSenha().getBytes()));
                    senhaHash = senhaHashCadastrada.toString();
                    System.out.println(senhaHashCadastrada);

                    Cliente client = new Cliente(cliente.getEmail(), senhaHash);

                    } catch (NoSuchAlgorithmException e) {
                        System.out.println("Erro ao carregar o MessageDigest");
                    }
                
                
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, senhaHash);
                stmt.setString(5, cliente.getCpf());
                stmt.execute();
                con.commit();
                return -1;
                
            } catch (SQLException e) {
                return e.getErrorCode();
            }
            
            } finally {
        }
    }
    
    public int alterarCliente (Cliente cliente) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "update cliente set nome = ?, email = ?, telefone = ?, cpf = ?, rua = ?, bairro = ?, complemento = ?, cep = ? where idcliente = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getCpf());
                stmt.setString(5, cliente.getRua());
                stmt.setString(6, cliente.getBairro());
                stmt.setString(7, cliente.getComplemento());
                stmt.setString(8, cliente.getCep());
                stmt.setInt(9, cliente.getIdCliente());
                stmt.execute();
                System.out.println(cliente.getIdCliente());
                con.commit();
                return -1;
            } catch (SQLException e) {
                try {
                    con.rollback();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    return ex.getErrorCode();
                }
            }
        } finally {
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                return e.getErrorCode();
            }
        }
    }
}
