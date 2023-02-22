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
import modelDominio.Vendedor;

/**
 *
 * @author ADMIN
 */
public class ProdutoDAO {
    
    private Connection con;
    
    public ProdutoDAO() {
        con = Conector.getConnection();
    }
    
    public ArrayList<Produto> getLista(Vendedor vendedor) { //Listagem para o dekstop
        Statement stmt = null;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(" select produto.*,vendedor.* from produto "+
                                                " inner join vendedor on produto.fkIdVendedor = " + vendedor.getIdVendedor() + " and produto.fkIdVendedor = vendedor.idVendedor");
            
            while (res.next()){
                Produto produto = new Produto(res.getInt("idProduto"), res.getString("nome"), res.getFloat("preco"), res.getFloat("tamanho"), res.getString("descricao"), res.getBytes("imagem"), new Vendedor(res.getInt("idVendedor")));
                
                listaProdutos.add(produto);
            }
            
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Produto> getLista() { //Listagem para o mobile
        Statement stmt = null;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(" select produto.*, vendedor.* from produto"  + 
                                              " inner join vendedor on (vendedor.idVendedor = produto.fkIdVendedor)");
            
            while (res.next()){
                Produto produto = new Produto(res.getInt("idProduto"), res.getString("nome"), res.getFloat("preco"), res.getFloat("tamanho"), res.getString("descricao"), res.getBytes("imagem"), new Vendedor(res.getInt("idVendedor"), res.getString("vendedor.nome"), res.getString("telefone")));
                
                listaProdutos.add(produto);
            }
            
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
    // Método para consultar produtos pelo nome
    public ArrayList<Produto> getListaProdutosNome(String nome, Vendedor vendedor) {
        Statement stmt = null; // usado para rodar SQL
        ArrayList<Produto> listprodutos = new ArrayList<>();

        try {
            // cria o objeto para rodar o SQL
            stmt = con.createStatement();
            // passando a string SQL que faz o SELECT
            ResultSet res = stmt.executeQuery(" select produto.*, vendedor.idVendedor from produto "+
                                              " inner join vendedor on produto.fkIdVendedor = " + vendedor.getIdVendedor() + " and produto.nome "+
                                              " like '%" + nome + "%' and produto.fkIdVendedor = vendedor.idVendedor");

            // Percorrendo o resultado - res
            while (res.next()) {
                // criando o objeto de marca pegando dados do res.
                Produto rc = new Produto(res.getInt("idProduto"), res.getString("nome"), res.getFloat("preco"), res.getFloat("tamanho"), res.getString("descricao"), res.getBytes("imagem"), new Vendedor(res.getInt("idVendedor")));
                // adicionando na lista auxiliar
                listprodutos.add(rc);
            }
            res.close();// fechando o resultado
            stmt.close();// fechando statment
            con.close(); // fechando conexão com o banco
            return listprodutos; // retornando a lista de marcas
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return null;
        }

    }
    
    public int inserirProduto(Produto produto) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "insert into Produto (nome,preco,tamanho,descricao,imagem,fkIdVendedor) values(?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                stmt.setFloat(2, produto.getPreco());
                stmt.setFloat(3, produto.getTamanho());
                stmt.setString(4, produto.getDescricao());
                stmt.setBytes(5, produto.getImagem());
                stmt.setInt(6, produto.getVendedor().getIdVendedor());
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
                String sql = "update produto set nome = ?, preco = ?, tamanho = ?, descricao = ?, imagem = ? where idproduto = ?";
                stmt = con.prepareStatement(sql);
                //Substituir os ? do script SQL
                stmt.setString(1, produto.getNome());
                stmt.setFloat(2, produto.getPreco());
                stmt.setFloat(3, produto.getTamanho());
                stmt.setString(4, produto.getDescricao());
                stmt.setBytes(5, produto.getImagem());
                stmt.setInt(6, produto.getIdProduto());
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
