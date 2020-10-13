/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kft2;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Windows 7
 */
public class frmlistaaluno extends javax.swing.JFrame {
    FrmAluno enviarpagina;
    FrmProfessor enviarprofessor;
    frmProduto enviarproduto;
    frmVenda enviarvenda;
    /**
     * Creates new form frmlistarCliente
     */
    public frmlistaaluno() {
        setExtendedState(MAXIMIZED_BOTH);
       
        initComponents();
        inicio();
        pnlistar.setVisible(false);
    }
    
    
    
    Conexao bd = new Conexao();
    
    
    public void inicio(){
        txtID_aluno.setDocument(new numerospermetidos());
        txtcodigodavenda.setDocument(new numerospermetidos());
        txtidproduto.setDocument(new numerospermetidos());
        txtidprofessor.setDocument(new numerospermetidos());
        txtnomeProfes.setDocument(new teclaspermitidas());
        txtnomealuno.setDocument(new teclaspermitidas());
        txtnomeproduto.setDocument(new teclaspermitidas());
        
        txtnomealuno.setEditable(false);
        txtID_aluno.setEditable(false);
        txtcodigodavenda.setEditable(false);
        txtdatavenda.setEditable(false);
        txtidproduto.setEditable(false);
        txtidprofessor.setEditable(false);
        txtnomeProfes.setEditable(false);
        txtnomeproduto.setEditable(false);
   }
    
    
    public class ListarModalidade extends AbstractTableModel {
    
     private List<classelistarmodalidade> dados = new ArrayList<>();
        private String [] colunas = {"Nome do Professor","CPF do Professor","CPF do Aluno","Nome do Aluno"};

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
            return dados.get(linha).getNomealuno();
            case 1:
        return dados.get(linha).getCpfaluno();
            case 2:
        return dados.get(linha).getCpfprofessor();
             case 3:
        return dados.get(linha).getNomeprofessor();
                         
       
    }
                return null;

    }
    
    public void addRow (classelistarmodalidade LM){
        this.dados.add(LM);
        this.fireTableDataChanged();
        
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}

    
    public class ListarVenda extends AbstractTableModel {
    
     private List<listarvendas> dados = new ArrayList<>();
        private String [] colunas = {"ID","Nome do Aluno","CPF do Aluno","Valor Total","Valor Pago","Data da Venda"};

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
            return dados.get(linha).getIdvenda();
            case 1:
        return dados.get(linha).getNomevenda();
            case 2:
        return dados.get(linha).getCpfvenda();
             case 3:
        return dados.get(linha).getValortotalvenda();
             case 4:
        return dados.get(linha).getValorpagovenda();
                 case 5:
        return dados.get(linha).getDtvenda();
                         
       
    }
                return null;

    }
    
    public void addRow (listarvendas LC){
        this.dados.add(LC);
        this.fireTableDataChanged();
        
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}

    
    
    
    
    
    
    public class ListarProfessor extends AbstractTableModel {
    
     private List<listarprofessor> dados = new ArrayList<>();
     private String [] colunas = {"ID","Nome do Professor","CPF","Telefone Celular","Email","Data de Nascimento","Data de Inicio","Login"};

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
            return dados.get(linha).getIDprofessor();
            case 1:
        return dados.get(linha).getNomeprofessor();
            case 2:
        return dados.get(linha).getCpfprofessor();
             case 3:
        return dados.get(linha).getTelefoneprofessor();
             case 4:
        return dados.get(linha).getEmailprofessor();
                 case 5:
        return dados.get(linha).getDatanacprofessor();
                     case 6:
        return dados.get(linha).getDatadeincprofes();
                         case 7:
        return dados.get(linha).getLoginprofessor();
       
    }
                return null;

    }
    
    public void addRow (listarprofessor LK){
        this.dados.add(LK);
        this.fireTableDataChanged();
        
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}


    
        String pagina = null;
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        btnVoltar = new javax.swing.JButton();
        txtnomealuno = new javax.swing.JTextField();
        rdbnomealuno = new javax.swing.JRadioButton();
        rdbtodos = new javax.swing.JRadioButton();
        rdbcodigodoaluno = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtID_aluno = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        rdbnomeprofessor = new javax.swing.JRadioButton();
        rdbcodigoprofessor = new javax.swing.JRadioButton();
        txtnomeProfes = new javax.swing.JTextField();
        txtidprofessor = new javax.swing.JTextField();
        rdbtodosproduto = new javax.swing.JRadioButton();
        rdbnomeproduto = new javax.swing.JRadioButton();
        txtnomeproduto = new javax.swing.JTextField();
        rdbcodigoproduto = new javax.swing.JRadioButton();
        txtidproduto = new javax.swing.JTextField();
        rdbtodasvendas = new javax.swing.JRadioButton();
        rdbdata = new javax.swing.JRadioButton();
        rdbcodigodavenda = new javax.swing.JRadioButton();
        txtcodigodavenda = new javax.swing.JTextField();
        txtdatavenda = new javax.swing.JFormattedTextField();
        pnlistar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcliente = new javax.swing.JTable();
        btnlistar = new javax.swing.JButton();
        jcbmodalidade = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        txtnomealuno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomealunoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbnomealuno);
        rdbnomealuno.setText("Nome do Aluno");
        rdbnomealuno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbnomealunoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbtodos);
        rdbtodos.setSelected(true);
        rdbtodos.setText("Todos");
        rdbtodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtodosActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbcodigodoaluno);
        rdbcodigodoaluno.setText("Codigo do Aluno");
        rdbcodigodoaluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbcodigodoalunoActionPerformed(evt);
            }
        });

        jButton1.setText("Listar Professor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Listar Modalidade");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Listar Estoque");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Listar Vendas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Todos");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdbnomeprofessor);
        rdbnomeprofessor.setText("Nome do Professor");
        rdbnomeprofessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbnomeprofessorActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdbcodigoprofessor);
        rdbcodigoprofessor.setText("Codigo do Professor");
        rdbcodigoprofessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbcodigoprofessorActionPerformed(evt);
            }
        });

        txtnomeProfes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomeProfesActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdbtodosproduto);
        rdbtodosproduto.setSelected(true);
        rdbtodosproduto.setText("Todos");
        rdbtodosproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtodosprodutoActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdbnomeproduto);
        rdbnomeproduto.setText("Nome do Produto");
        rdbnomeproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbnomeprodutoActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdbcodigoproduto);
        rdbcodigoproduto.setText("Codigo do Produto");
        rdbcodigoproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbcodigoprodutoActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdbtodasvendas);
        rdbtodasvendas.setSelected(true);
        rdbtodasvendas.setText("Todos");
        rdbtodasvendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtodasvendasActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdbdata);
        rdbdata.setText("Data da venda");
        rdbdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbdataActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdbcodigodavenda);
        rdbcodigodavenda.setText("Codigo da Venda");
        rdbcodigodavenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbcodigodavendaActionPerformed(evt);
            }
        });

        txtcodigodavenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigodavendaActionPerformed(evt);
            }
        });

        try {
            txtdatavenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        tblcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblclienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblcliente);

        javax.swing.GroupLayout pnlistarLayout = new javax.swing.GroupLayout(pnlistar);
        pnlistar.setLayout(pnlistarLayout);
        pnlistarLayout.setHorizontalGroup(
            pnlistarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlistarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlistarLayout.setVerticalGroup(
            pnlistarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlistarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        btnlistar.setText("Listar Aluno");
        btnlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlistarActionPerformed(evt);
            }
        });

        jcbmodalidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Selecione uma Modalidade --", "Muay thai Manha", "Muay thai", "Taekwondo", "Jiu Jitsu", "Capoeira", "Boxe", "MMA", "Judô" }));
        jcbmodalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmodalidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlistar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbtodos)
                            .addComponent(rdbnomealuno)
                            .addComponent(txtnomealuno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbcodigodoaluno)
                            .addComponent(txtID_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidprofessor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnomeProfes, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbnomeprofessor)
                            .addComponent(rdbcodigoprofessor)))
                    .addComponent(btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbtodosproduto)
                    .addComponent(rdbnomeproduto)
                    .addComponent(txtnomeproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbcodigoproduto)
                    .addComponent(txtidproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcodigodavenda, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbtodasvendas)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbdata)
                    .addComponent(txtdatavenda, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbcodigodavenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jcbmodalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbtodasvendas)
                            .addComponent(rdbtodosproduto)
                            .addComponent(jRadioButton1)
                            .addComponent(rdbtodos))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jcbmodalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbdata)
                    .addComponent(rdbnomeproduto)
                    .addComponent(rdbnomeprofessor)
                    .addComponent(rdbnomealuno))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdatavenda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomeproduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomeProfes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomealuno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdbcodigoproduto)
                                    .addComponent(rdbcodigoprofessor)
                                    .addComponent(rdbcodigodoaluno))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtidprofessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtID_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(rdbcodigodavenda)
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtcodigodavenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtidproduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlistarActionPerformed
        TabelaListar bllistar = new TabelaListar();
            
        tblcliente.setModel(bllistar);

        String sql = "select ID, nome, CPF,email,tel2,Dtnascimento,Dtinicio, obs from aluno";
        System.out.println(sql);
        if(rdbnomealuno.isSelected())
        {
            sql = "select * from aluno where nome like '%" + txtnomealuno.getText() + "%'";
        }
        else if(rdbcodigodoaluno.isSelected())
        {
            sql = "select * from aluno where ID = " + txtID_aluno.getText();
        }
        
           String nome, cpf,modalidade;
       int c = 0;
       int m = 0;
                       boolean v = true;
    Date dm = null;
       try{

           ResultSet rs = bd.executarconsulta(sql);
            while(rs.next()){
                   pnlistar.setVisible(true);

                listardados LD = new listardados();
                LD.setIDlistar(rs.getInt("ID"));
                LD.setNomelistar(rs.getString("nome"));
                LD.setCPFlistar(rs.getString("CPF"));
                LD.setEmaillistar(rs.getString("email"));
                LD.setTelefonelistar(rs.getString("tel2"));
                LD.setDtnascimentolistar(rs.getString("Dtnascimento"));
               dm  = rs.getDate("Dtinicio");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                format.format(dm);
                LD.setDtiniciolistar(""+format.format(dm));
    
                LD.setObslistar(rs.getString("obs"));
                bllistar.addRow(LD);
                v = false;
                        
            } 
            if(v){
                       JOptionPane.showMessageDialog(null,"Aluno não cadastrado"); 
                        pnlistar.setVisible(false);
            } 
       }
    
        catch(SQLException ex){
            System.out.println("Problema na busca");
            
        }
    
       pagina = "aluno";
        
        
    }//GEN-LAST:event_btnlistarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new frmMenu().setVisible(true);
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ListarProfessor bllistar = new ListarProfessor();
            
        tblcliente.setModel(bllistar);

        String sql = "select * from professor";
        
        if(rdbnomeprofessor.isSelected())
        {
            sql = "select * from professor where nome like '%" + txtnomeProfes.getText() + "%'";
        }
        else if(rdbcodigoprofessor.isSelected())
        {
            sql = "select * from professor where ID = " + txtidprofessor.getText() ;
        }
        
                       boolean v = true;
        Date dm = null;
       try{

           ResultSet rs = bd.executarconsulta(sql);
            while(rs.next()){
                   pnlistar.setVisible(true);

                listarprofessor LK = new listarprofessor();
                LK.setIDprofessor(rs.getInt("ID"));
                LK.setNomeprofessor(rs.getString("nome"));
                LK.setCpfprofessor(rs.getString("CPF"));
                LK.setEmailprofessor(rs.getString("email"));
                LK.setTelefoneprofessor(rs.getString("tel2"));
                LK.setDatanacprofessor(rs.getString("Dtnascimento"));
               dm  = rs.getDate("Dtinicio");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                format.format(dm);
                LK.setDatadeincprofes(""+format.format(dm));
    
                LK.setLoginprofessor(rs.getString("loguin"));
                bllistar.addRow(LK);
                v = false;
                        
            } 
            if(v){
                       JOptionPane.showMessageDialog(null,"Professor não cadastrado"); 
                       pnlistar.setVisible(false);
            } 
       }
    
        catch(SQLException ex){
            System.out.println("Problema na busca");
            
        }
    
       pagina = "professor";
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtcodigodavendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigodavendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigodavendaActionPerformed

    private void rdbnomeprofessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbnomeprofessorActionPerformed
        txtidprofessor.setText(null);
        
        inicio();
        txtnomeProfes.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rdbnomeprofessorActionPerformed

    private void txtnomeProfesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomeProfesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomeProfesActionPerformed

    private void rdbnomealunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbnomealunoActionPerformed
        txtID_aluno.setText(null);
        inicio();
        txtnomealuno.setEditable(true);
         
        
      
    }//GEN-LAST:event_rdbnomealunoActionPerformed

    private void txtnomealunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomealunoActionPerformed
     
          
    }//GEN-LAST:event_txtnomealunoActionPerformed

    private void rdbcodigodoalunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbcodigodoalunoActionPerformed
        txtnomealuno.setText(null);
        inicio();
        txtID_aluno.setEditable(true);
    }//GEN-LAST:event_rdbcodigodoalunoActionPerformed

    private void rdbcodigoprofessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbcodigoprofessorActionPerformed
        txtnomeProfes.setText(null);
        inicio();
        txtidprofessor.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rdbcodigoprofessorActionPerformed

    private void rdbnomeprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbnomeprodutoActionPerformed
        txtidproduto.setText(null);
        inicio();
        txtnomeproduto.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rdbnomeprodutoActionPerformed

    private void rdbcodigoprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbcodigoprodutoActionPerformed
        txtnomeproduto.setText(null);
        inicio();
        txtidproduto.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rdbcodigoprodutoActionPerformed

    private void rdbdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbdataActionPerformed
        txtcodigodavenda.setText(null);
        inicio();
        txtdatavenda.setEditable(true);// TODO add your handling code here:
    }//GEN-LAST:event_rdbdataActionPerformed

    private void rdbcodigodavendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbcodigodavendaActionPerformed
        txtdatavenda.setText(null);
        inicio();
        txtcodigodavenda.setEditable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rdbcodigodavendaActionPerformed

    private void rdbtodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtodosActionPerformed
    txtID_aluno.setText(null);
    txtnomealuno.setText(null);
        inicio();

    }//GEN-LAST:event_rdbtodosActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
      txtidprofessor.setText(null);
      txtnomeProfes.setText(null);
        inicio();        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void rdbtodosprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtodosprodutoActionPerformed
        txtidproduto.setText(null);
        txtnomeproduto.setText(null);
        inicio();        // TODO add your handling code here:
    }//GEN-LAST:event_rdbtodosprodutoActionPerformed

    private void rdbtodasvendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtodasvendasActionPerformed
     txtdatavenda.setText(null);
     txtcodigodavenda.setText(null);
        inicio();        // TODO add your handling code here:
    }//GEN-LAST:event_rdbtodasvendasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TabelaProdutoVenda bllistar = new TabelaProdutoVenda();
        
        tblcliente.setModel(bllistar);
        String sql = "select * from produto";
        
        if(rdbnomeproduto.isSelected())
        {
            sql = "select * from produto where nome like '%" + txtnomeproduto.getText() + "%'";
        }
        else if(rdbcodigoproduto.isSelected())
        {
            sql = "select * from produto where ID = " + txtidproduto.getText() ;
        }
                       boolean v = true;
        Date dm = null;
       try{

           ResultSet rs = bd.executarconsulta(sql);
            while(rs.next()){
                   pnlistar.setVisible(true);
                 NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();

                produto PK = new produto();
                PK.setIdproduto(rs.getInt("ID"));
                PK.setNomeproduto(rs.getString("nome"));
                PK.setQtdproduto(rs.getInt("qtd"));
                double si = rs.getDouble("pr_und");
                PK.setPrunproduto(formatomoeda.format(si));
                bllistar.addRow(PK);
                v = false;
                        
            } 
            if(v){
                       JOptionPane.showMessageDialog(null,"Produto não cadastrado"); 
                       pnlistar.setVisible(false);
            } 
       }
    
        catch(SQLException ex){
            System.out.println("Problema na busca");
            
        }
    
       pagina = "produto";
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ListarVenda bllistar = new ListarVenda();
        
        tblcliente.setModel(bllistar);
        String sql = "select venda.*, aluno.nome from venda inner join aluno on venda.CPF_aluno = aluno.CPF;";
        if(rdbdata.isSelected())
        {
           
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String j9 = txtdatavenda.getText();
                String Dia = txtdatavenda.getText().substring(0,2);
                String mes = txtdatavenda.getText().substring(3,5);
                String ano = txtdatavenda.getText().substring(6);
                String dtmysql = ano+"-"+mes+"-"+Dia; 
                        
               System.out.println(dtmysql);
                sql = "select venda.*, aluno.nome from venda inner join aluno on venda.CPF_aluno = aluno.CPF where venda.Dtvenda = '" + dtmysql +"'";
           
        }
        else if(rdbcodigodavenda.isSelected())
        {
            sql = "select venda.*, aluno.nome from venda inner join aluno on venda.CPF_aluno = aluno.CPF where venda.ID = " + txtcodigodavenda.getText();
        }
                       boolean v = true;
        Date dm = null;
       try{

           ResultSet rs = bd.executarconsulta(sql);
            while(rs.next()){
                   pnlistar.setVisible(true);
                listarvendas LC = new listarvendas();

                LC.setIdvenda(rs.getInt("ID"));
                LC.setNomevenda(rs.getString("nome"));
                LC.setCpfvenda(rs.getString("CPF_aluno"));
                NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();
                double sl = rs.getDouble("valor_total");
                double so = rs.getDouble("valor_pago");
                LC.setValortotalvenda(formatomoeda.format(sl));
                LC.setValorpagovenda(formatomoeda.format(so));
                Date s = rs.getDate("Dtvenda");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                LC.setDtvenda(""+format.format(s));
                v = false;
                bllistar.addRow(LC);
              
                
            } 
            
              if(v){
                       JOptionPane.showMessageDialog(null,"Venda não cadastrada"); 
                       pnlistar.setVisible(false);
            }
       }
    
        catch(SQLException ex){
            System.out.println("Problema na busca");
            
        }
    
       pagina = "venda";
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblclienteMouseClicked
        if(evt.getClickCount() == 2){
            int linha;         
            linha = tblcliente.getSelectedRow();             
            int remover_item = Integer.parseInt(tblcliente.getValueAt(linha, 0).toString());
            if(pagina.equals("aluno")){    
         
            enviarpagina = new FrmAluno();
            enviarpagina.setVisible(true);
            enviarpagina.dadospagina(remover_item);
             this.setVisible(false);
                
                
        }
            else if (pagina.equals("professor")){
            enviarprofessor = new FrmProfessor();
            enviarprofessor.setVisible(true);
            enviarprofessor.dadospagina(remover_item);
             this.setVisible(false);
  
            }
            else if (pagina.equals("produto")){
            enviarproduto = new frmProduto();
            enviarproduto.setVisible(true);
            enviarproduto.dadospagina(remover_item);
             this.setVisible(false);
  
            }
            else if (pagina.equals("venda")){
            enviarvenda = new frmVenda();
            enviarvenda.setVisible(true);
            enviarvenda.dadospagina(remover_item);
             this.setVisible(false);
  
            }
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_tblclienteMouseClicked

    private void jcbmodalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmodalidadeActionPerformed
        
        


// TODO add your handling code here:
    }//GEN-LAST:event_jcbmodalidadeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ListarModalidade bllistar = new ListarModalidade();
        boolean v = false;
        tblcliente.setModel(bllistar);
        
        String sql = null,s = null;
        if (jcbmodalidade.getSelectedItem().equals("Muay thai Manha")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Muay thai Manha';";

        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Muay thai Manha';";
            v = true;
        }
            else if (jcbmodalidade.getSelectedItem().equals("-- Selecione uma Modalidade --")){
                       JOptionPane.showMessageDialog(null,"Selecione uma Modalidade"); 
                       pnlistar.setVisible(false);
                
                } 

        else if (jcbmodalidade.getSelectedItem().equals("Muay thai")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Muay thai';";

        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Muay thai';";
        v = true;
        }
        else if (jcbmodalidade.getSelectedItem().equals("Taekwondo")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Taekwondo';";
v = true;
        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Taekwondo';";
 
        }
        else if (jcbmodalidade.getSelectedItem().equals("Jiu Jitsu")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Jiu Jitsu';";
v = true;
        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Jiu Jitsu';";
 
        }
        else if (jcbmodalidade.getSelectedItem().equals("Capoeira")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Capoeira';";
v = true;
        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Capoeira';";
 
        }
        
        else if (jcbmodalidade.getSelectedItem().equals("Boxe")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Boxe';";
v = true;
        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Boxe';";
 
        }
       
        else if (jcbmodalidade.getSelectedItem().equals("MMA")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'MMA';";
v = true;
        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'MMA';";
 
        }
        
        else if (jcbmodalidade.getSelectedItem().equals("Judô")){
            sql = "select nome,CPF from aluno inner join moda_aluno on aluno.ID = moda_aluno.id_aluno inner join modalidade on moda_aluno.id_modalidade = modalidade.ID where Mdl = 'Judô';";
v = true;
        s = "select nome,CPF from professor inner join moda_prof on professor.CPF = moda_prof.id_professor inner join modalidade on moda_prof.id_modalidade = modalidade.ID where Mdl = 'Judô';";
 
        }
        if(v == true){        
        try{

           ResultSet rs = bd.executarconsulta(s);
            while(rs.next()){
                classelistarmodalidade LM = new classelistarmodalidade();
                   pnlistar.setVisible(true);

                LM.setNomealuno(rs.getString("nome"));
                LM.setCpfaluno(rs.getString("CPF"));
                
                
                bllistar.addRow(LM);
              
              
                
            } 
            
             try{
             
                rs = bd.executarconsulta(sql);
            while(rs.next()){
                                   pnlistar.setVisible(true);

                classelistarmodalidade LM = new classelistarmodalidade();

                LM.setCpfprofessor(rs.getString("CPF"));
                LM.setNomeprofessor(rs.getString("nome"));
            
                bllistar.addRow(LM);
              
                
                            } 
                
       }
    
        catch(SQLException ex){
            System.out.println("Problema na busca");
            
        }
       }
    
        catch(SQLException ex){
            System.out.println("Problema na busca");
            
        }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmlistaaluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmlistaaluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmlistaaluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmlistaaluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmlistaaluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnlistar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcbmodalidade;
    private javax.swing.JPanel pnlistar;
    private javax.swing.JRadioButton rdbcodigodavenda;
    private javax.swing.JRadioButton rdbcodigodoaluno;
    private javax.swing.JRadioButton rdbcodigoproduto;
    private javax.swing.JRadioButton rdbcodigoprofessor;
    private javax.swing.JRadioButton rdbdata;
    private javax.swing.JRadioButton rdbnomealuno;
    private javax.swing.JRadioButton rdbnomeproduto;
    private javax.swing.JRadioButton rdbnomeprofessor;
    private javax.swing.JRadioButton rdbtodasvendas;
    private javax.swing.JRadioButton rdbtodos;
    private javax.swing.JRadioButton rdbtodosproduto;
    private javax.swing.JTable tblcliente;
    private javax.swing.JTextField txtID_aluno;
    private javax.swing.JTextField txtcodigodavenda;
    private javax.swing.JFormattedTextField txtdatavenda;
    private javax.swing.JTextField txtidproduto;
    private javax.swing.JTextField txtidprofessor;
    private javax.swing.JTextField txtnomeProfes;
    private javax.swing.JTextField txtnomealuno;
    private javax.swing.JTextField txtnomeproduto;
    // End of variables declaration//GEN-END:variables
}
