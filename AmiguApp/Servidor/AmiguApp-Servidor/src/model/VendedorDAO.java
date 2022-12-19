/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public ArrayList<Vendedor> getLista() {
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
    }
    
    public Vendedor efetuarLogin(Vendedor vend) {
        PreparedStatement stmt = null; //usado para rodar SQL
        Vendedor vendselecionado = null;
        
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
                
            }
        }
    }
    
}
