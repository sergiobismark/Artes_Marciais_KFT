package kft2;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaListar extends AbstractTableModel {
    
     private List<listardados> dados = new ArrayList<>();
     private String [] colunas = {"ID","Nome do Aluno","CPF","Email","Telefone Celular","Data de Nascimento","Data de Inicio","Obs sobre o Aluno"};

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
            return dados.get(linha).getIDlistar();
            case 1:
        return dados.get(linha).getNomelistar();
            case 2:
        return dados.get(linha).getCPFlistar();
             case 3:
        return dados.get(linha).getEmaillistar();
             case 4:
        return dados.get(linha).getTelefonelistar();
                 case 5:
        return dados.get(linha).getDtnascimentolistar();
                     case 6:
        return dados.get(linha).getDtiniciolistar();
                         case 7:
        return dados.get(linha).getObslistar();
       
    }
                return null;

    }
    
    public void addRow (listardados LD){
        this.dados.add(LD);
        this.fireTableDataChanged();
        
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}

