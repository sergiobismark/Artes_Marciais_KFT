/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kft2;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows 7
 */
public class frmVenda extends javax.swing.JFrame {

    /**
     * Creates new form frmVenda
     */
        TabelaProdutoVenda tblvendaproduto = new TabelaProdutoVenda();

    public frmVenda() {
        initComponents();
        setLocationRelativeTo( null );

        dtinicio();
        teclasusdas();
      tblitens.setModel(tblvendaproduto);
        
    }
    Conexao bd = new Conexao();
    String sql;
    int remover_item;
    double remove_total;
      int remover_qtd;
    
             int onumero;
             int numeroaluno;
      public void dadospagina (int recebe){
         onumero = recebe;
         seila();
     }
      public void seila(){
          btfinalizar.setVisible(false);
          btnCod_Cliente.setVisible(false);
          btnVoltar.setVisible(false);
          btnsai.setVisible(true);
          pnproduto.setVisible(false);
          pnprodutointes.setVisible(false);
          txtvalor_pago.setEnabled(false);
         String s = "select aluno.ID as idaluno,aluno.nome as nomealuno,aluno.CPF as cpfaluno,venda.ID as idvenda,venda.Dtvenda,venda.valor_pago,venda.valor_total from venda inner join aluno on venda.CPF_aluno = aluno.CPF where venda.ID =" + onumero;
        
         try{
             
            ResultSet rs = bd.executarconsulta(s);
            while(rs.next())
            {   
                numeroaluno = rs.getInt("idaluno");
                txtcod_aluno.setText(""+numeroaluno);
                txtcpf_aluno.setText(""+rs.getString("cpfaluno"));
                txtnome_aluno.setText(""+rs.getString("nomealuno"));
                txtcod_venda.setText("" + rs.getInt("idvenda"));
                Date d0 = rs.getDate("Dtvenda");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                txtDt_Venda.setText(""+ format.format(d0));
                NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();
                double so = rs.getDouble("valor_pago");
                txtvalor_pago.setText(""+formatomoeda.format(so));
                double sp = rs.getDouble("valor_total");
                txtValor_Total.setText(""+ formatomoeda.format(sp));
            }
            
            itens();
         }
      
        catch (SQLException ex) {
            System.out.println("Problema na busca"); 
            }
      
         }
      public void itens(){
          System.out.println("caionointes");
            pnprod.setVisible(true);
            
         String   c = "select produto.ID as ID,produto.nome as nome, itens_venda.quantidade as quantidade,produto.pr_und as pr_und from itens_venda inner join produto on itens_venda.id_pro = produto.ID where Cod_vend ="+onumero;
        
             try{
             
            ResultSet rs = bd.executarconsulta(c);
                         double valor_totals,total = 0,si=0;
                         int qtd =0;

             while(rs.next())
            {   
                         
               NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();

                produto VP = new produto();
                VP.setIdproduto(rs.getInt("ID"));
                VP.setNomeproduto(rs.getString("nome"));
                qtd = rs.getInt("quantidade");
                VP.setQtdproduto(qtd);
                si = rs.getDouble("pr_und");
                VP.setPrunproduto(formatomoeda.format(si));
                System.out.println(rs.getString("nome"));
                tblvendaproduto.addRow(VP);



                total += (si * qtd);
                }
                             String valortotal = txtValor_Total.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
                 System.out.println(total+"valor total");
                valor_totals = Double.parseDouble(valortotal);
                                 System.out.println(valor_totals+"valoroooooooo");

                double result = valor_totals - total;
                System.out.println(result+"seilá cara");
            if(result != 0){
            pnmdl.setVisible(true);
                    String mod;
        boolean s = false;
            mod = "SELECT mdl as Modalidade FROM modalidade INNER JOIN moda_aluno ON moda_aluno.id_modalidade = modalidade.ID INNER JOIN aluno ON aluno.ID = moda_aluno.id_aluno where moda_aluno.id_aluno =" + numeroaluno;
             try{
            rs = bd.executarconsulta(mod);
            while(rs.next()){
            tblCliente.setVisible(true);
            preencher_tabelamodalidade(mod);
               s = true;
            }
            
        }
        catch(SQLException ex){
            System.out.println("Problema na busca");
        }                
            }     
            
        }
                 catch (SQLException ex) {
            System.out.println("Problema na busca2222222"); 
            }
      
      }
      
      
    public void dtinicio(){
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
                
        Date data = new Date();
        
        txtDt_Venda.setText("" + dt.format(data));
        
           }
    public void cod_venda(){
    
   sql = "select max(id) as ID from venda";
        int codigo_venda = 0;
        
        try{
             
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next())
            {   
                codigo_venda =  rs.getInt("ID");
            }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
            }
         
        codigo_venda ++;
        txtcod_venda.setText("" + codigo_venda);
           
}
    public void teclasusdas(){
        pnmdl.setVisible(false);     
        pnprod.setVisible(false);
        txtcod_venda.setDocument(new numerospermetidos());
        txtcod_aluno.setDocument(new numerospermetidos());
        txtcodprod.setDocument(new numerospermetidos());
        txtdescprod.setDocument(new teclaspermitidas());
        txtquanti_pedi.setDocument(new numerospermetidos());
        txtvalor_pago.setDocument(new teclasmoedas());
        btnsai.setVisible(false);
    }
    int c_dias = 2;
    int m_dias = 1;
    
    
    public void buscar_ult_pagamento(){
        String sql = "select nome, DATEDIFF(now(),Dtpagamento) as quantidade_dias from aluno where ID =" +txtcod_aluno.getText();
                      String nome;
       
       try{
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next()){
                nome = rs.getString("nome");
                    c_dias = rs.getInt("quantidade_dias");
                 m_dias =10;
                 
                System.out.println(c_dias);
                 
         
            }               
        }
        catch(SQLException ex){
            System.out.println("Problema na busca");
            System.out.println("caiu aqui2");
            m_dias=1;
        }
    }
    
    double valor_mensalidade = 0;
    public void buscar_modalidade(){
        String mod;
        boolean s = false;
        int somalinhas = 0;
        mod = "SELECT mdl as Modalidade FROM modalidade INNER JOIN moda_aluno ON moda_aluno.id_modalidade = modalidade.ID INNER JOIN aluno ON aluno.ID = moda_aluno.id_aluno where moda_aluno.id_aluno =" + txtcod_aluno.getText();         
        try{
            ResultSet rs = bd.executarconsulta(mod);
            while(rs.next()){
            tblCliente.setVisible(true);
            preencher_tabelamodalidade(mod);
            somalinhas = tblCliente.getModel().getRowCount();
               s = true;
            }
            if(somalinhas==1){
                valor_mensalidade = 89.90;
                
            }
            if (somalinhas>1){
                valor_mensalidade = 74.90;
                valor_mensalidade *= somalinhas; 

            }
            if (!s){
            JOptionPane.showMessageDialog(null,"Aluno não cadastrado em modalidade");
            tblCliente.setVisible(false);
                            valor_mensalidade = 0.00;

            }
        }
        catch(SQLException ex){
            System.out.println("Problema na busca");
            System.out.println("caio aqui 3");
        }
    }
           boolean achou = false, achou4 = true; 

    public void buscar_aluno(){
       sql = "select * from aluno where ID =" + txtcod_aluno.getText();
       Date m2 = null;
        try{
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next()){
                txtcpf_aluno.setText(rs.getString("CPF"));
                txtnome_aluno.setText(rs.getString("nome"));
                m2 = rs.getDate("Dtpagamento");
                achou = true;
                achou4 = true;
            pnprodutointes.setVisible(true);
            pnproduto.setVisible(true);
    
            }
            if (!achou){
            JOptionPane.showMessageDialog(null,"Aluno não Cadastrado");
            achou4 = false;
            pnproduto.setVisible(false);
            pnprodutointes.setVisible(false);
            achou = false;
            limpar();
            }
        }
        catch(SQLException ex){
            System.out.println("Problema na busca");
            System.out.println("caio aqui 6");
        }
        
    }
    
    public void preencher_tabelamodalidade(String sql)
    {        
        try{
            
        ResultSet rs = bd.executarconsulta(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int num_colunas = rsmd.getColumnCount();
        DefaultTableModel IncluirItem = new DefaultTableModel();        
        
        this.tblCliente.setModel(IncluirItem);        
        
        for(int x = 1; x <= num_colunas; x++)
        {
            IncluirItem.addColumn(rsmd.getColumnLabel(x));
        }        
        while(rs.next())
        {
            Object [] fila = new Object[num_colunas];            
            for(int y = 0; y < num_colunas;y++)
            {
                fila[y] = rs.getObject(y+1);
            }
            IncluirItem.addRow(fila);
        }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
            }      
    }

    
         public void preencher_tabela(String sql)
    {        
        try{
            System.out.println("passou na cozinha");
        ResultSet rs = bd.executarconsulta(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int num_colunas = rsmd.getColumnCount();
        DefaultTableModel IncluirItem = new DefaultTableModel();        
            System.out.println(IncluirItem);
        this.tblitens.setModel(IncluirItem);        
        
        for(int x = 1; x <= num_colunas; x++)
        {
            IncluirItem.addColumn(rsmd.getColumnLabel(x));
        }        
        while(rs.next())
        {
            Object [] fila = new Object[num_colunas];            
            for(int y = 0; y < num_colunas;y++)
            {
                fila[y] = rs.getObject(y+1);
            }
            IncluirItem.addRow(fila);
        }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
            }      
    }

    
    public void limpar(){
    txtDt_Venda.setText(null);
    txtValor_Total.setText(null);
    txtcod_aluno.setText(null);
    txtcod_venda.setText(null);
    txtcodprod.setText(null);
    txtcpf_aluno.setText(null);
    txtdescprod.setText(null);
    txtnome_aluno.setText(null);
    txtqtd.setText(null);
    txtquanti_pedi.setText(null);
    txtsubtotal.setText(null);
    txtvalor_pago.setText(null);
    txtvalor_unit.setText(null);
       
    }
    
    
    public void atualizar_data_aluno(){
             Date data = new Date();    
         
          SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        sql = " update aluno set Dtpagamento ='" + format.format(data) + "'  where id = " + txtcod_aluno.getText();
                System.out.println(sql);
            bd.executarcomandos(sql);
        
    }
    
    
    public void buscar_produto(){
        boolean f = false;
        sql = "select * from produto where (id = '" + txtcodprod.getText()
        +"' or nome = '" + txtdescprod.getText() + "')";

        try{

            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next())
            {
                txtcodprod.setText("" + rs.getInt("id"));
                txtdescprod.setText(rs.getString("nome"));
                txtqtd.setText("" + rs.getInt("qtd"));
                 NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();
                 double si = rs.getDouble("pr_und");
                txtvalor_unit.setText("" + formatomoeda.format(si) );
                f = true;
            }
           if (!f){
            JOptionPane.showMessageDialog(null,"Produto não cadastrado");
            
            }
        
        }
        
        catch (SQLException ex) {
            System.out.println("Problema na busca");
        }
        
    }
    
    boolean vendafinalizada = false;
    
   public void limparProd(){
    txtcodprod.setText(null);
    txtdescprod.setText(null);
    txtvalor_unit.setText(null);
    txtqtd.setText(null);
    txtquanti_pedi.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtnome_aluno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcpf_aluno = new javax.swing.JTextField();
        btnCod_Cliente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtcod_aluno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDt_Venda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtvalor_pago = new javax.swing.JTextField();
        btfinalizar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txttroco = new javax.swing.JTextField();
        pnproduto = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtcodprod = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdescprod = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtvalor_unit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtqtd = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtquanti_pedi = new javax.swing.JTextField();
        pnprodutointes = new javax.swing.JPanel();
        btnbuscaprod = new javax.swing.JButton();
        btnadprod = new javax.swing.JButton();
        btnremoveprod = new javax.swing.JButton();
        txtcod_venda = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pnmdl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        pnprod = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitens = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtValor_Total = new javax.swing.JTextField();
        btnVoltar = new javax.swing.JButton();
        btnsai = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("* Nome do Aluno:");

        txtnome_aluno.setEditable(false);
        txtnome_aluno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("* CPF do Aluno:");

        txtcpf_aluno.setEditable(false);
        txtcpf_aluno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnCod_Cliente.setText("Buscar Aluno");
        btnCod_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCod_ClienteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("* ID Aluno:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnome_aluno))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCod_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcpf_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcod_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtcod_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnome_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtcpf_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCod_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Data da Venda:");

        txtDt_Venda.setEditable(false);
        txtDt_Venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDt_VendaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Valor Pago:");

        txtvalor_pago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtvalor_pagoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtvalor_pagoFocusLost(evt);
            }
        });
        txtvalor_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalor_pagoActionPerformed(evt);
            }
        });

        btfinalizar.setText("OK");
        btfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btfinalizarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Troco:");

        txttroco.setEditable(false);
        txttroco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttrocoActionPerformed(evt);
            }
        });

        jLabel8.setText("Cod. Produto:");

        jLabel9.setText("Descrição:");

        txtdescprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescprodActionPerformed(evt);
            }
        });

        jLabel10.setText("Valor Unitario:");

        txtvalor_unit.setEditable(false);

        jLabel11.setText("Quantidade Estoque:");

        txtqtd.setEditable(false);

        jLabel12.setText("Sub-Total");

        txtsubtotal.setEditable(false);
        txtsubtotal.setText("0");

        jLabel14.setText("Quantidade:");

        javax.swing.GroupLayout pnprodutoLayout = new javax.swing.GroupLayout(pnproduto);
        pnproduto.setLayout(pnprodutoLayout);
        pnprodutoLayout.setHorizontalGroup(
            pnprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnprodutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtquanti_pedi)
                    .addComponent(txtcodprod, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtdescprod, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtvalor_unit, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtqtd, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnprodutoLayout.createSequentialGroup()
                        .addGroup(pnprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnprodutoLayout.setVerticalGroup(
            pnprodutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnprodutoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtcodprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdescprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtvalor_unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtqtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(12, 12, 12)
                .addComponent(txtquanti_pedi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btnbuscaprod.setText("Buscar Produto");
        btnbuscaprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscaprodActionPerformed(evt);
            }
        });

        btnadprod.setText("Add Produto");
        btnadprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadprodActionPerformed(evt);
            }
        });

        btnremoveprod.setText("Remover Produto");
        btnremoveprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveprodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnprodutointesLayout = new javax.swing.GroupLayout(pnprodutointes);
        pnprodutointes.setLayout(pnprodutointesLayout);
        pnprodutointesLayout.setHorizontalGroup(
            pnprodutointesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnadprod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnbuscaprod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnremoveprod, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        pnprodutointesLayout.setVerticalGroup(
            pnprodutointesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnprodutointesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnbuscaprod)
                .addGap(18, 18, 18)
                .addComponent(btnadprod)
                .addGap(18, 18, 18)
                .addComponent(btnremoveprod)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        txtcod_venda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcod_venda.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Cod. Venda:");

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCliente.setEnabled(false);
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        javax.swing.GroupLayout pnmdlLayout = new javax.swing.GroupLayout(pnmdl);
        pnmdl.setLayout(pnmdlLayout);
        pnmdlLayout.setHorizontalGroup(
            pnmdlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnmdlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnmdlLayout.setVerticalGroup(
            pnmdlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnmdlLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblitens.setModel(new javax.swing.table.DefaultTableModel(
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
        tblitens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblitensMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblitens);

        javax.swing.GroupLayout pnprodLayout = new javax.swing.GroupLayout(pnprod);
        pnprod.setLayout(pnprodLayout);
        pnprodLayout.setHorizontalGroup(
            pnprodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnprodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnprodLayout.setVerticalGroup(
            pnprodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnprodLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Valor Total:");

        txtValor_Total.setEditable(false);

        btnVoltar.setText("voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnsai.setText("Sair");
        btnsai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtvalor_pago, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                    .addComponent(txtValor_Total, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                    .addComponent(txttroco))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                                .addComponent(pnprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(pnprodutointes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnmdl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btfinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnproduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDt_Venda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcod_venda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnmdl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtValor_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtvalor_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txttroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(125, 125, 125)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btfinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnsai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(pnprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDt_Venda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtcod_venda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnproduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(pnprodutointes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        if(!txtcod_venda.getText().equals("")){
    int resposta = JOptionPane.showConfirmDialog(null, "Venda ja Inicializada. Deseja Cancelar ?",(txtcod_venda.getText()), JOptionPane.YES_NO_OPTION);

         if (resposta == JOptionPane.YES_OPTION) {
       sql = "delete from venda where ID = " +txtcod_venda.getText();

                     bd.executarcomandos(sql);

        this.setVisible(false);
        new frmMenu().setVisible(true);
         }
        }
        else{
            this.setVisible(false);
        new frmMenu().setVisible(true);
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnCod_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCod_ClienteActionPerformed
        // TODO add your handling code here:
               
         if (!txtcod_aluno.getText().equals("")){
        
                      buscar_aluno();
                      
                      if(achou4){
                cod_venda();
    
                          buscar_ult_pagamento();
                        if (c_dias > 30 || c_dias < 0){
                
                            pnmdl.setVisible(true);
                buscar_modalidade();
     
                        }
                
            else{
       JOptionPane.showMessageDialog(null,"Modalidade em Debito");
       achou4 = false;
       achou = false;
       pnmdl.setVisible(false);
       valor_mensalidade = 0;
       }
             }
             
         
             NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
              txtValor_Total.setText(""+ nf.format(valor_mensalidade));
            
            }
             
       else{
           JOptionPane.showMessageDialog(null,"Digite o ID do Aluno");
       }
    }//GEN-LAST:event_btnCod_ClienteActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        

 
    }//GEN-LAST:event_tblClienteMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formWindowOpened

    private void txtDt_VendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDt_VendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDt_VendaActionPerformed

    private void txtvalor_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalor_pagoActionPerformed
        
        
        //CRIA AQUI O CALCULO E MANDA PARA CAMPO TROCO
    }//GEN-LAST:event_txtvalor_pagoActionPerformed

    private void txttrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttrocoActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txttrocoActionPerformed

    private void btnbuscaprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscaprodActionPerformed
        if(!txtcodprod.getText().equals("")){
                buscar_produto();
        }
        else{
                      JOptionPane.showMessageDialog(null,"ID Produto Obrigadorio");

        }
    }//GEN-LAST:event_btnbuscaprodActionPerformed

    private void btnadprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadprodActionPerformed
            
        if(!txtcod_venda.getText().equals("")){
             produto VP = new produto();

        if(!txtquanti_pedi.getText().equals("")){
        txtcod_aluno.setEnabled(false);
            
        double valor_unit,s1,qtd,total,qtdestoque = 0;
        
        sql = "select qtd from produto where ID = "+ txtcodprod.getText() ;
        try{
        ResultSet rs = bd.executarconsulta(sql);  
            while (rs.next()){
            qtdestoque = Integer.parseInt("" + rs.getInt("qtd"));
            }
        }
        catch (SQLException ex){
          JOptionPane.showMessageDialog(null,"Problema na Consulta");
        }
        
        if(qtdestoque >= Integer.parseInt(txtquanti_pedi.getText())){
            if (txtsubtotal.getText().equals("")){
                txtsubtotal.setText("0");
            }
            pnprod.setVisible(true);
            
        NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();

        String subtotal2 = txtsubtotal.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");

        String valortotal = txtValor_Total.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        
        String valorund = txtvalor_unit.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        

        total = Double.parseDouble(subtotal2);
        valor_unit = Double.parseDouble(valorund);
        qtd = Integer.parseInt(txtquanti_pedi.getText());
        total += (valor_unit * qtd);
        s1 = valor_mensalidade;
        s1 = s1 + total;

        txtsubtotal.setText("" + formatomoeda.format(total));
        txtValor_Total.setText("" + formatomoeda.format(s1));
            
        sql = "insert into itens_venda values(0,"+
                txtcod_venda.getText()+","+
                txtcodprod.getText()+","+
                Integer.parseInt(txtquanti_pedi.getText())+","+ valorund +")";
            bd.executarcomandos(sql);
       
        double calculo = qtdestoque - Integer.parseInt(txtquanti_pedi.getText());                          
       
        sql = " update produto set qtd = " + calculo + "  where id = " + txtcodprod.getText();
                System.out.println(sql);
            bd.executarcomandos(sql);
                System.out.println("não chegou na tabela");
            VP.setIdproduto(Integer.parseInt(txtcodprod.getText()));
            VP.setNomeproduto(txtdescprod.getText());
            VP.setQtdproduto(Integer.parseInt(txtquanti_pedi.getText()));
            VP.setPrunproduto((valorund));
            tblvendaproduto.addRow(VP);
            System.out.println("chegou na tabela" + VP);
            limparProd();
                
            btnVoltar.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Quantidade indisponivel");
        }
        }
        else{
            JOptionPane.showMessageDialog(null,"Quantidade não encontrada");
        }
        }
        
        else{
            JOptionPane.showMessageDialog(null,"Campo ID aluno Vazio");
        }
        
        
    }//GEN-LAST:event_btnadprodActionPerformed

    private void btnremoveprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveprodActionPerformed
        boolean validaçãocampo = false;
        if(tblitens.getSelectedRow() != -1){
         int linha;         
            linha = tblitens.getSelectedRow();             
            remover_item = Integer.parseInt(tblitens.getValueAt(linha, 0).toString());
            remover_qtd = Integer.parseInt(tblitens.getValueAt(linha, 2).toString());
            remove_total = Double.parseDouble(tblitens.getValueAt(linha, 2).toString()) * 
            Double.parseDouble(tblitens.getValueAt(linha, 3).toString());
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?",(tblitens.getValueAt(linha, 1).toString()), JOptionPane.YES_NO_OPTION);

         if (resposta == JOptionPane.YES_OPTION) {
             System.out.println("caio no sim");
                     String sql;
                             
        NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();

        String subtotal2 = txtsubtotal.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");

        String valortotal = txtValor_Total.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        
        String valorund = txtvalor_unit.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        
        int q = 0; 
        double sub = Double.parseDouble(subtotal2);
        double calculo;
        double n1 = Double.parseDouble(valortotal);;
        sub -= remove_total;
        n1 -= remove_total; 
        txtsubtotal.setText("" + formatomoeda.format(sub));
        txtValor_Total.setText("" + formatomoeda.format(n1));
        sql = "delete from itens_venda where id_pro = " + remover_item + " and Cod_vend = " + txtcod_venda.getText() + ";";
        bd.executarcomandos(sql);
        
        sql = " select qtd from produto where ID = " + remover_item + ";";                
        try{
            ResultSet rs = bd.executarconsulta(sql);
            while (rs.next()){
                 q = Integer.parseInt("" + rs.getInt("qtd"));
            }
        }
        catch(SQLException ex){
            System.out.println("Problema na Busca");
        }
         
         calculo = q + remover_qtd;
          sql = "update produto set qtd = " + calculo + " where ID = " + remover_item + ";";
          bd.executarcomandos(sql);
                     
            tblvendaproduto.removeRow(tblitens.getSelectedRow());
                limparProd();  
        } else if (resposta == JOptionPane.NO_OPTION) {
           //Usuário clicou em não. Executar o código correspondente.
             System.out.println("caio no não");
        
        }
         validaçãocampo = true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecione o Produto que Deseja Remove");
            validaçãocampo=false;
        }
         if(validaçãocampo==false){
             btnVoltar.setVisible(true);
             pnprod.setVisible(false);
             
         }
        

    }//GEN-LAST:event_btnremoveprodActionPerformed

    private void txtdescprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescprodActionPerformed

    private void btfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btfinalizarActionPerformed
        if(!txtcod_aluno.getText().equals("")){
            if(!txtvalor_pago.getText().equals("")){
             
            Date data = new Date();    
         
          SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
          
                      
        NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();

        String valorpago = txtvalor_pago.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");

        String valortotal = txtValor_Total.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        
          
          
            String sql = "insert into venda  values (" + txtcod_venda.getText() + ",'" + txtcpf_aluno.getText() + "','" + format.format(data) + "'," + Double.parseDouble(valorpago) + "," + Double.parseDouble(valortotal)+ ")";
                
         if(bd.executarcomandos(sql)>0){
             atualizar_data_aluno();
            JOptionPane.showMessageDialog(null, "Venda finalizada ! Troco: "+ txttroco.getText());
            limpar();
         this.setVisible(false);
        new frmVenda().setVisible(true);
         }
            }
            
        else{
            JOptionPane.showMessageDialog(null, "Valor Pago Vazio");
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "Campo ID Aluno vazio");
        }
       

    }//GEN-LAST:event_btfinalizarActionPerformed

    private void tblitensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitensMouseClicked
        // TODO add your handling code here:
        
                             
    }//GEN-LAST:event_tblitensMouseClicked

    private void txtvalor_pagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalor_pagoFocusLost
        if(!txtvalor_pago.getText().equals("") || !txtValor_Total.getText().equals("R$ 0,00")){
        String valortotal = txtValor_Total.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        String valorpago = txtvalor_pago.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");

         double si = Double.parseDouble(valortotal);
         double sj = Double.parseDouble(valorpago);
         
         NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();
          txtvalor_pago.setText(""+formatomoeda.format(sj));
       
         if(si>0){
         double troco = sj-si;
         txttroco.setText(""+ formatomoeda.format(troco));
        
         }
         else{
            double troco = 0;
         txttroco.setText(""+ formatomoeda.format(troco));
         }
        }
        else{
            double troco = 0;
            NumberFormat formatomoeda = NumberFormat.getCurrencyInstance();
         txttroco.setText(""+ formatomoeda.format(troco));
         
        }
                        
    }//GEN-LAST:event_txtvalor_pagoFocusLost

    private void txtvalor_pagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalor_pagoFocusGained
    }//GEN-LAST:event_txtvalor_pagoFocusGained

    private void btnsaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaiActionPerformed
        this.setVisible(false);
        new frmMenu().setVisible(true);



    }//GEN-LAST:event_btnsaiActionPerformed

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
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btfinalizar;
    private javax.swing.JButton btnCod_Cliente;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnadprod;
    private javax.swing.JButton btnbuscaprod;
    private javax.swing.JButton btnremoveprod;
    private javax.swing.JToggleButton btnsai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnmdl;
    private javax.swing.JPanel pnprod;
    private javax.swing.JPanel pnproduto;
    private javax.swing.JPanel pnprodutointes;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblitens;
    private javax.swing.JTextField txtDt_Venda;
    private javax.swing.JTextField txtValor_Total;
    private javax.swing.JTextField txtcod_aluno;
    private javax.swing.JTextField txtcod_venda;
    private javax.swing.JTextField txtcodprod;
    private javax.swing.JTextField txtcpf_aluno;
    private javax.swing.JTextField txtdescprod;
    private javax.swing.JTextField txtnome_aluno;
    private javax.swing.JTextField txtqtd;
    private javax.swing.JTextField txtquanti_pedi;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttroco;
    private javax.swing.JTextField txtvalor_pago;
    private javax.swing.JTextField txtvalor_unit;
    // End of variables declaration//GEN-END:variables
}
