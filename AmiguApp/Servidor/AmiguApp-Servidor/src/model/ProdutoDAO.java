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
                Produto produto = new Produto(res.getInt("idProduto"), res.getString("nomeProduto"));
                
                listaProdutos.add(produto);
            }
            
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return null;
        }
    }
    
}
