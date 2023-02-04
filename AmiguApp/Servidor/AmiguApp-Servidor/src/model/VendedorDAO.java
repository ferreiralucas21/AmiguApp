/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import factory.Conector;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.Produto;
import modelDominio.Vendedor;

/**
 *
 * @author ADMIN
 */
public class VendedorDAO {
    
    private Connection con;
    
    public VendedorDAO() {
        con = Conector.getConnection();
    }    
    
    public Vendedor efetuarLogin(Vendedor vend) {
        PreparedStatement stmt = null; //usado para rodar SQL
        Vendedor vendedorSelecionado = null;
        
        try {
            //passando a string SQL que faz o SELECT
            String sql = " select * from vendedor " + " where email = ? and senha = ?";
            stmt = con.prepareStatement(sql);                     
            //substituir os ? do script SQL
            stmt.setString(1, vend.getEmail());
            stmt.setString(2, vend.getSenha());
            
            //Executando o select
            ResultSet res = stmt.executeQuery();
            
            //Percorrendo o resultado - res
            while (res.next()) {
                vendedorSelecionado = new Vendedor(res.getInt("idvendedor"),
                                  res.getString("nome"),
                                  res.getString("email"),
                                  res.getString("telefone"),
                                  res.getString("senha"));
            }
            res.close();//fechando o resultado
            stmt.close();//fechando o statement
            con.close();//fechando a conexão com o banco
            return vendedorSelecionado;//retornando a lista de
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
       public Vendedor perfilVendedor(int codigo) {
        PreparedStatement stmt = null; //usado para rodar SQL
        Vendedor vendedorSelecionado = null;
        
        try {
            //passando a string SQL que faz o SELECT
            String sql = " select * from vendedor where idVendedor = ? ";
            stmt = con.prepareStatement(sql);                     
            //substituir os ? do script SQL
            stmt.setInt(1, codigo);
         
            
            //Executando o select
            ResultSet res = stmt.executeQuery();
            
            //Percorrendo o resultado - res
            while (res.next()) {
                vendedorSelecionado = new Vendedor(res.getInt("idvendedor"),
                                  res.getString("nome"),
                                  res.getString("email"),
                                  res.getString("telefone"),
                                  res.getString("senha"));
            }
            res.close();//fechando o resultado
            stmt.close();//fechando o statement
            con.close();//fechando a conexão com o banco
            return vendedorSelecionado;//retornando a lista de
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Vendedor> getLista() {
        Statement stmt = null;
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from vendedor");
            
            while (res.next()){
                Vendedor vendedor = new Vendedor(res.getInt("idVendedor"),res.getString("nome"),res.getString("email"),res.getString("telefone"),res.getString("senha"));
                
                listaVendedores.add(vendedor);
            }
            
            return listaVendedores;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
    public int inserirVendedor(Vendedor vend) {
        PreparedStatement stmt = null; //usado para rodar SQL
        try {
            try {
                con.setAutoCommit(false);
                String sql = "insert into Vendedor (nome,email,telefone,senha) values(?,?,?,?)";
                stmt = con.prepareStatement(sql);
                
                /*try {

                    MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256

                    BigInteger senhaHashCadastrada = new BigInteger(1, md.digest(vend.getSenha().getBytes()));

                    System.out.println(senhaHashCadastrada);

                    Vendedor vendedor = new Vendedor(vend.getEmail(), senhaHashCadastrada.toString());

                    } catch (NoSuchAlgorithmException e) {
                        System.out.println("Erro ao carregar o MessageDigest");
                    }*/
                
                stmt.setString(1, vend.getNome());
                stmt.setString(2, vend.getEmail());
                stmt.setString(3, vend.getTelefone());
                stmt.setString(4, vend.getSenha());
                stmt.execute();
                con.commit();
                return -1;
                
            } catch (SQLException e) {
                return e.getErrorCode();
            }
            
            } finally {
        }
    }
    
    public int alterarVendedor (Vendedor vendedor) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "update vendedor set nome = ?, email = ?, telefone = ?, senha = ? where idvendedor = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, vendedor.getNome());
                stmt.setString(2, vendedor.getEmail());
                stmt.setString(3, vendedor.getTelefone());
                stmt.setString(4, vendedor.getSenha());
                stmt.setInt(5, vendedor.getIdVendedor());
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
