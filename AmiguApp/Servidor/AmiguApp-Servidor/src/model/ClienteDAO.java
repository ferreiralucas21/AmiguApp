/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelDominio.Cliente;

/**
 *
 * @author julia
 */
public class ClienteDAO {
    private Connection con;
    
    public ClienteDAO() {
        con = Conector.getConnection();
    }
    
    /*public ArrayList<Vendedor> getLista() {
        Statement stmt = null;
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from vendedor");
            
            while (res.next()){
                Vendedor vendedor = new Vendedor(res.getInt("idvendedor"), res.getString("nome"), res.getString("email"), res.getInt("telefone"), res.getString("senha"), res.getBytes("imagem"));
                
                listaVendedores.add(vendedor);
            }
            
            return listaVendedores;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }*/
    
    public Cliente efetuarLogin(Cliente cliente) {
        PreparedStatement stmt = null; //usado para rodar SQL
        Cliente clienteSelecionado = null;
        
        try {
            //passando a string SQL que faz o SELECT
            String sql = " select * from cliente " + " where email = ? and senha = ?";
            stmt = con.prepareStatement(sql);
            //substituir os ? do script SQL
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getSenha());
            
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
                String sql = "insert into Cliente (nome,email,telefone,senha) values(?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(4, cliente.getCpf());
                stmt.setString(4, cliente.getRua());
                stmt.setString(4, cliente.getBairro());
                stmt.setString(4, cliente.getComplemento());
                stmt.setString(4, cliente.getCep());
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
                String sql = "update cliente set nome = ?, email = ?, telefone = ?, imagem = ? where idvendedor = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(4, cliente.getCpf());
                stmt.setString(4, cliente.getRua());
                stmt.setString(4, cliente.getBairro());
                stmt.setString(4, cliente.getComplemento());
                stmt.setString(4, cliente.getCep());
                stmt.setInt(5, cliente.getIdCliente());
                stmt.execute();
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
