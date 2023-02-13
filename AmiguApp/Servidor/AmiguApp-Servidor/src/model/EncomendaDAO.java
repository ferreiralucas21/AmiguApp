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
import modelDominio.Encomenda;
import modelDominio.Produto;

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
    
    /*public ArrayList<Encomenda> getListaEncomendas() {
        Statement stmt = null; // usado para rodar SQL
        ArrayList<Encomenda> listaEncomendas = new ArrayList<Encomenda>();

        try {
            // cria o objeto para rodar o SQL
            stmt = con.createStatement();
            // passando a string SQL que faz o SELECT
            ResultSet res = stmt.executeQuery(" select encomenda.*, produto.idProduto from encomenda "+
                                              " inner join produto on (produto.idProduto = encomenda.fkIdProduto) "+
                                              " order by bike.codbike ");

            // Pebkorrendo o resultado - res
            while (res.next()) {
                // criando o objeto de gastomensal pegando dados do res.
                Bike bk = new Bike(res.getInt("codbike"),
                        res.getString("modelo"),
                        new Marca(res.getInt("codmarca"),res.getString("nomemarca")),
                        res.getFloat("preco"),
                        res.getBytes("imagem"),
                        res.getDate("datalancamento"));
                // adicionando na lista auxiliar
                listaBikes.add(bk);
            }
            res.close();// fechando o resultado
            stmt.close();// fechando statment
            con.close(); // fechando conexão com o banco
            return listaBikes; // retornando a lista de gastomensals
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
