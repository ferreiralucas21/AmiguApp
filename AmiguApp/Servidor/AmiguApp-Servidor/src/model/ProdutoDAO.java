/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Connection;
import factory.Conector;
import java.util.ArrayList;
import modelDominio.Produto;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author ADMIN
 */
public class ProdutoDAO {
    
    private Connection con;
    
    public ProdutoDAO() {
        con = Conector.getConnection();
    }
    
    public ArrayList<Produto> getLista() {
        Statement stmt = null;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from produto");
            
            while (res.next()){
                Produto produto = new Produto(res.getInt("idProduto"), res.getString("nome"), res.getFloat("preco"), res.getFloat("tamanho"), res.getString("descricao"), res.getInt("fkIdVendedor"));
                
                listaProdutos.add(produto);
            }
            
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
    public int inserirProduto(Produto produto) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "insert into Produto (nome,preco,tamanho,descricao,fkIdVendedor) values(?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                stmt.setFloat(2, produto.getPreco());
                stmt.setFloat(3, produto.getTamanho());
                stmt.setString(4, produto.getDescricao());
                stmt.setInt(5, produto.getFkIdVendedor());
                stmt.execute();
                con.commit();
                return -1;
                
            } catch (SQLException e ) {
                return e.getErrorCode();
            }
            
        } finally {
            
        }
    }
        
    public int alterar(Produto produto) {
        PreparedStatement stmt = null;
        try {
            try {
                //Desliga o autocommit
                con.setAutoCommit(false);
                //O ? será substituído pelo valor
                String sql = "update produto set nome = ?, preco = ?, tamanho = ?, descricao = ? where idproduto = ?";
                stmt = con.prepareStatement(sql);
                //Substituir os ? do script SQL
                stmt.setString(1, produto.getNome());
                stmt.setFloat(2, produto.getPreco());
                stmt.setFloat(3, produto.getTamanho());
                stmt.setString(4, produto.getDescricao());
                stmt.setInt(5, produto.getIdProduto());
                stmt.execute();
                con.commit();
                return -1; // <- indica que deu tudo certo                
            } catch (SQLException e) {
                try {
                    con.rollback();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    return ex.getErrorCode();
                }
            }
        } finally {// Isto será executado dando erro ou não
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                return e.getErrorCode();
            }
        }
    }
    
    public int excluir(Produto produto) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "delete from produto where idProduto = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, produto.getIdProduto());
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
