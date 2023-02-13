/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Encomenda;

/**
 *
 * @author ADMIN
 */
public class EncomendaTableModel extends AbstractTableModel{
    
    private ArrayList<Encomenda> listaEncomendas;
    
    //Método construtor que recebe como parâmetro a lista de Marcas
    public EncomendaTableModel(ArrayList<Encomenda> listaEncomendas) {
        this.listaEncomendas = listaEncomendas;
    }
    
    //Sobrescrita do método que diz quantas LINHAS a tabela tem
    @Override 
    public int getRowCount() {
        return listaEncomendas.size();
    }
    
    @Override
    //Sobrescrita do método que diz quantas COLUNAS a tabela tem
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Encomenda encomenda = listaEncomendas.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return encomenda.getProduto().getNome();
            case 1:
                return encomenda.getProduto().getPreco();
            case 2:
                return encomenda.getProduto().getTamanho();
            case 3:
                return encomenda.getProduto().getDescricao();
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
    public Encomenda getProduto(int row) {
        return listaEncomendas.get(row);
    }
}
