/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Produto;

/**
 *
 * @author ADMIN
 */
public class ProdutoTableModel extends AbstractTableModel {
    
    private ArrayList<Produto> listaProdutos;
    
    //Método construtor que recebe como parâmetro a lista de Marcas
    public ProdutoTableModel(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
    
    //Sobrescrita do método que diz quantas LINHAS a tabela tem
    @Override
    public int getRowCount() {
        return listaProdutos.size();
    }

    //Sobrescrita do método que diz quantas COLUNAS a tabela tem
    @Override
    public int getColumnCount() {
        return 4;
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = listaProdutos.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return produto.getNome();
            case 1:
                return produto.getPreco();
            case 2:
                return produto.getTamanho();
            case 3:
                return produto.getDescricao();
            default:
                return "";
        }
    }
    
    //Sobrescrita do método que retorna o nome de cada colunua
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "Preço";
            case 2:
                return "Tamanho";
            case 3:
                return "Descrição";
            default:
                return "";
        }
    }
    
    //Método que retorna um objeto do produto a partir da linha que o usuário selecionou
    public Produto getProduto(int row) {
        return listaProdutos.get(row);
    }
    
    /*public Object meusProdutos(Produto produto) {
        int codVendedor = AmiguAppCliente.ccont.vendedor.getIdVendedor();
        int codProduto = AmiguAppCliente.ccont.produto.getFkIdVendedor();
        
        while (codVendedor == codProduto){
            
        } return listaProdutos;
    
        }*/
    
}
