/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kft2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
     
public class TabelaMenu extends AbstractTableModel{
     
    private List<produto> dados = new ArrayList<>();
    private String [] colunas = {"Nome","CPF","Data do Ultimo Pagamento"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
    return colunas.length;  
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
    switch(coluna){
        case 0:
            return dados.get(linha).getNomemenu();
        case 1:
            return dados.get(linha).getCpfmenu();
        case 2:
            return dados.get(linha).getModalidademenu();
    }
    
            return null;
    
    }
    
    public void addRow (produto p){
        this.dados.add(p);
        this.fireTableDataChanged();
        
        
    }
    
}
