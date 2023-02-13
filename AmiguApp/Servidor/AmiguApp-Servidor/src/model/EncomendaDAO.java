/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
