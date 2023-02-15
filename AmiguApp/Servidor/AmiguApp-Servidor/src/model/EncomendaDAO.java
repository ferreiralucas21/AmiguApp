/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.Cliente;
import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;

/**
 *
 * @author ADMIN
 */
public class EncomendaDAO {
    
    private Connection con;
    
    public EncomendaDAO() {
        con = Conector.getConnection();
    }
    
    public int inserirEncomenda(Encomenda encomenda) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "insert into Encomenda (idEncomenda,quantidade,status,fkIdProduto,fkIdCliente) values(?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, encomenda.getIdEncomenda());
                stmt.setInt(2, encomenda.getQuantidade());
                stmt.setString(3, "Em processamento");
                stmt.setInt(4, encomenda.getProduto().getIdProduto());
                stmt.setInt(5, encomenda.getCliente().getIdCliente());
                stmt.execute();
                con.commit();
                return -1;
                
            } catch (SQLException e ) {
                return e.getErrorCode();
            }
            
        } finally {
            
        }
    }
    
    public ArrayList<Encomenda> getListaEncomendas(Vendedor vendedor) {
        Statement stmt = null; // usado para rodar SQL
        ArrayList<Encomenda> listaEncomendas = new ArrayList<>();

        try {

            stmt = con.createStatement();
            // passando a string SQL que faz o SELECT
            ResultSet res = stmt.executeQuery(" select encomenda.idEncomenda,encomenda.quantidade,produto.*,cliente.nome,cliente.email,cliente.telefone,cliente.cpf from produto"+
                                              " inner join encomenda on (produto.idProduto = encomenda.fkIdProduto) "+
                                              " inner join cliente on (cliente.idCliente = encomenda.fkIdCliente)" +
                                              " where produto.fkIdVendedor = " + vendedor.getIdVendedor());

            // Pebkorrendo o resultado - res
            while (res.next()) {
                // criando o objeto de gastomensal pegando dados do res.
                Encomenda encomenda = new Encomenda(res.getInt("idEncomenda"),new Produto(res.getInt("idProduto"), res.getString("produto.nome"), res.getFloat("preco"), res.getFloat("tamanho"), res.getString("descricao"), res.getBytes("imagem"), res.getInt("fkIdVendedor")),
                                      new Cliente(res.getString("cliente.nome"),res.getString("email"),res.getString("telefone"),res.getString("cpf")),res.getInt("quantidade"));              
                listaEncomendas.add(encomenda);
                System.out.println(encomenda);                
            }
            res.close();// fechando o resultado
            stmt.close();// fechando statment
            con.close(); // fechando conexão com o banco
            return listaEncomendas; // retornando a lista de gastomensals

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
  
}
