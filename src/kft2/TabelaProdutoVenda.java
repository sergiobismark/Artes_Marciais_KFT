package kft2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TabelaProdutoVenda extends AbstractTableModel {
    
     private List<produto> dados = new ArrayList<>();
     private String [] colunas = {"ID","Nome do Produto","Quantidade","Preço Und"};

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
            return dados.get(linha).getIdproduto();
            case 1:
        return dados.get(linha).getNomeproduto();
            case 2:
        return dados.get(linha).getQtdproduto();
             case 3:
        return dados.get(linha).getPrunproduto();
                    
    }
                return null;

    }
    
    public void addRow (produto VP){
        this.dados.add(VP);
        this.fireTableDataChanged();
        
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}
