/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kft2;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author KFT
 */
public class FrmProfessor extends javax.swing.JFrame {

    /**
     * Creates new form FrmProfessor
     */
    public FrmProfessor() {
        setExtendedState(MAXIMIZED_BOTH);
        initComponents();
        dtinicio();
        teclasletras();
        
    }
    Conexao bd = new Conexao();
    
         int onumero;
     public void dadospagina (int recebe){
         onumero = recebe;
         seila();
     }
     
     public void seila(){
        String sql = "select * from professor where ID = "+ onumero; 
        String CPFprofessor = null;
         try{
             
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next())
            {   
            ID_professor = rs.getInt("ID");
            txtnome.setText("" + rs.getString("nome"));
            txtnomepai.setText("" + rs.getString("nomepai"));
            txtnomemae.setText("" + rs.getString("nomemae"));
            CPFprofessor = rs.getString("CPF");
            txtcpf.setText(""+ CPFprofessor); 
            txtrg.setText("" + rs.getString("RG"));
            txttel1.setText("" + rs.getString("tel1"));
            txttel2.setText("" + rs.getString("tel2"));
            txtemail.setText("" + rs.getString("email"));
            txtrua.setText("" + rs.getString("rua"));
            txtnumerorua.setText("" + rs.getString("numero"));
            txtcomplemento.setText("" + rs.getString("complemento"));
            txtbairro.setText("" + rs.getString("bairro"));
            txtcidade.setText("" + rs.getString("cidade"));
            txtcep.setText("" + rs.getString("cep"));
            txtdtnascimento.setText("" + rs.getString("Dtnascimento"));
            Date dt = rs.getDate("Dtinicio");
            txtobs.setText("" + rs.getString("obs"));
            txtloguin.setText("" + rs.getString("loguin"));
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            txtdtinicio.setText(""+ format.format(dt));
            
            }
            
         int nmdl = 0;
        
        String s = "SELECT * FROM moda_prof INNER JOIN professor ON moda_prof.id_professor = professor.CPF where CPF ='" + CPFprofessor + "'";         
        try{
            ResultSet sr = bd.executarconsulta(s);
            while(sr.next()){
                System.out.println("sala");
                
                nmdl = Integer.parseInt("" + sr.getInt("id_modalidade"));
             
            if(nmdl == 1){
                jcmtmanha.setSelected(true);
            }
            if(nmdl == 2){
                jcmt.setSelected(true);
            }
            if(nmdl == 3){
                jctwd.setSelected(true);
            }
            if(nmdl == 4){
                jcjj.setSelected(true);
            }
            if(nmdl == 5){
                jccpr.setSelected(true);
            }
            if(nmdl == 6){
                jcboxe.setSelected(true);
            }
            if(nmdl == 7){
                jcmma.setSelected(true);
            }
            if(nmdl == 8){
                jcjudo.setSelected(true);
            }
            System.out.println(nmdl);
              
            }

        }
               catch(SQLException ex){
            System.out.println("Problema na busca");
        }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
        } 
          
          
               
           }          
       
         
     
    
    
    
    public void teclasletras(){
        txtnome.setDocument(new Documentos(69, "[^a-z|^A-Z|^ ]"));
        txtnomemae.setDocument(new Documentos(69, "[^a-z|^A-Z|^ ]"));
        txtnomepai.setDocument(new Documentos(69, "[^a-z|^A-Z|^ ]"));
        txtbairro.setDocument(new Documentos(40, "[^a-z|^A-Z|^ ]"));
        txtcidade.setDocument(new Documentos(40, "[^a-z|^A-Z|^ ]"));
        txtrua.setDocument(new Documentos(57, "[^a-z|^A-Z|^ ]"));
        txtcep.setDocument(new Documentos(13, "[^0-9]"));
        txtrg.setDocument(new Documentos(10, "[^0-9]"));
        txtnumerorua.setDocument(new Documentos(10, "[^0-9]"));
    }
    
    
     class Documentos extends PlainDocument{
         int tam = 0;
         String regex = "";
         public Documentos (int a,String r){
             this.tam = a;
             this.regex = r;
         }
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            int tamanho = this.getLength() + str.length();
            if (tamanho <=tam){
            super.insertString(offs, str.replaceAll(regex,""), a); //To change body of generated methods, choose Tools | Templates.
            }
            else{              
            super.insertString(offs, str.replaceAll("[aA0-zZ9|^ |^!|^@|^#|^$|^%|^&|^*|^(|^)|^+|^_|^-|^;|^.|^,|^¨|^-|^~|^/|^´|^'|^^|^-|^}]", ""), a);
            }
            
            }
         
     }
    
    
    
    
    public void dtinicio(){
        DateFormat dt = new SimpleDateFormat("dd-MM-yyyy"); 
                
        Date data = new Date();
        
        txtdtinicio.setText("" + dt.format(data));
        
           }
     boolean valido = false;     
     String pfc;
     String resp;
     int v = 0, ko = 0;
             
             
             
     public void verifica_CPF(){
              
     pfc = "select * from professor where CPF ='" + txtcpf.getText() + "'"; 
       
       try{
             
            ResultSet rs = bd.executarconsulta(pfc);
            while(rs.next())
            {   
             ko = rs.getInt("ID");
            rs.getString("CPF");
             v++;
            valido = true;
            }
            
        }
        catch (SQLException ex) {
             
            valido = false;         
            } 
     }
     
    public void verifi_modalidade(){
        String modalidade;
         if(jcmtmanha.isSelected()){
           modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',1)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
         if(jcmt.isSelected()){
           modalidade = "insert into moda_prof values (0,'"+ txtcpf.getText() + "',2)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
         if(jctwd.isSelected()){
               modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',3)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
           if(jcjj.isSelected()){
           modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',4)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
           if(jccpr.isSelected()){
            modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',5)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
           if(jcboxe.isSelected()){
            modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',6)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
        if(jcmma.isSelected()){
         modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',7)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
         if(jcjudo.isSelected()){
        modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',8)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }     
    }

    
    public void excl_modalidade(){
        String modalidade;
         if(!jcmtmanha.isSelected()){

           modalidade = "delete from moda_prof where id_modalidade = 1 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
         if(!jcmt.isSelected()){
           modalidade = "delete from moda_prof where id_modalidade = 2 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
         if(!jctwd.isSelected()){
               modalidade = "delete from moda_prof where id_modalidade = 3 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
           if(!jcjj.isSelected()){
           modalidade = "delete from moda_prof where id_modalidade = 4 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
           if(!jccpr.isSelected()){
            modalidade = "delete from moda_prof where id_modalidade = 5 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
           if(!jcboxe.isSelected()){
            modalidade = "delete from moda_prof where id_modalidade = 6 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
        if(!jcmma.isSelected()){
         modalidade = "delete from moda_prof where id_modalidade = 7 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
         if(!jcjudo.isSelected()){
        modalidade = "delete from moda_prof where id_modalidade = 8 and id_professor = '" + txtcpf.getText() + "'";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }     
    }
    int ID_professor = 0;
    
    public void limpar(){
    txtnome.setText(null);
    txtnomepai.setText(null);
    txtnomemae.setText(null);
    txtcpf.setText(null);
    txtrg.setText(null);
    txttel1.setText(null);
    txttel2.setText(null);
    txtrua.setText(null);
    txtnumerorua.setText(null);
    txtbairro.setText(null);
    txtcidade.setText(null);
    txtcep.setText(null);
    txtdtnascimento.setText(null);
    txtcomplemento.setText(null);
    txtemail.setText(null);
    txtloguin.setText(null);
    txtsenha1.setText(null);
    txtsenha2.setText(null);
    txtobs.setText(null);
    jcboxe.setSelected(false);
    jccpr.setSelected(false);
    jcjj.setSelected(false);
    jcjudo.setSelected(false);
    jcmma.setSelected(false);
    jcmt.setSelected(false);
    jcmtmanha.setSelected(false);
    jctwd.setSelected(false);
    }
    public void verif_login(){
                 
     pfc = "select * from professor where loguin ='" + txtloguin.getText() + "'"; 
       
       try{
             
            ResultSet rs = bd.executarconsulta(pfc);
            while(rs.next())
            {   
             ko = rs.getInt("ID");
            rs.getString("loguin");
             v++;
            valido = true;
            }
            
        }
        catch (SQLException ex) {
             
            valido = false;         
            } 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnomepai = new javax.swing.JTextField();
        txtnomemae = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtrg = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtdtinicio = new javax.swing.JTextField();
        txtdtnascimento = new javax.swing.JFormattedTextField();
        txtcpf = new javax.swing.JFormattedTextField();
        txttel1 = new javax.swing.JFormattedTextField();
        txttel2 = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtrua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtcomplemento = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtcidade = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtbairro = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtnumerorua = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtcep = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtloguin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtsenha1 = new javax.swing.JPasswordField();
        txtsenha2 = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jcmt = new javax.swing.JCheckBox();
        jctwd = new javax.swing.JCheckBox();
        jccpr = new javax.swing.JCheckBox();
        jcjj = new javax.swing.JCheckBox();
        jcmtmanha = new javax.swing.JCheckBox();
        jcboxe = new javax.swing.JCheckBox();
        jcmma = new javax.swing.JCheckBox();
        jcjudo = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnovo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("* Nome:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("* Data de Nascimento:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("* CPF: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("* Telefone de Contato: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("* Nome do Pai:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("* Nome da Mãe:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("* RG:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("* Obrigatório dois (02) telefones para contato.");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Email:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DD/MM/AAAA");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("* Data de Início:");

        txtdtinicio.setEditable(false);
        txtdtinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdtinicioActionPerformed(evt);
            }
        });

        try {
            txtdtnascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtcpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txttel1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txttel2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel26.setText("Celular");

        jLabel27.setText("Fixo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel21))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtdtinicio)
                                    .addComponent(txtdtnascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttel1))
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnomemae, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnomepai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcpf, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(txtrg))))
                        .addContainerGap(337, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdtnascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtnomepai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtdtinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnomemae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("* Rua:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Complemento:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("* Cidade:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("* Bairro:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("* Número:");

        txtnumerorua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumeroruaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("* CEP:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcidade, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcomplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtrua, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtnumerorua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtnumerorua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcomplemento))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20)
                            .addComponent(txtcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("* Login:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("* Senha:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("* Confirma senha:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtloguin, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)
                        .addComponent(jLabel11))
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtsenha1)
                    .addComponent(txtsenha2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtloguin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtsenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtsenha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel22.setBackground(new java.awt.Color(204, 255, 204));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Modalidades");

        jcmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmt.setText("Muay Thai");
        jcmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmtActionPerformed(evt);
            }
        });

        jctwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jctwd.setText("Taekwondo");

        jccpr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jccpr.setText("Capoeira");

        jcjj.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcjj.setText("Jiu Jitsu ");

        jcmtmanha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmtmanha.setText("Muay Thai (Manhã)");

        jcboxe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcboxe.setText("Boxe");

        jcmma.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmma.setText("MMA");

        jcjudo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcjudo.setText("Judô");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcmtmanha)
                .addGap(24, 24, 24)
                .addComponent(jcmt)
                .addGap(30, 30, 30)
                .addComponent(jctwd)
                .addGap(18, 18, 18)
                .addComponent(jcjj)
                .addGap(35, 35, 35)
                .addComponent(jccpr)
                .addGap(49, 49, 49)
                .addComponent(jcboxe)
                .addGap(44, 44, 44)
                .addComponent(jcmma)
                .addGap(27, 27, 27)
                .addComponent(jcjudo)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcjj)
                    .addComponent(jctwd)
                    .addComponent(jcmtmanha)
                    .addComponent(jccpr)
                    .addComponent(jcmt)
                    .addComponent(jcboxe)
                    .addComponent(jcmma)
                    .addComponent(jcjudo))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Observação:");

        txtobs.setColumns(20);
        txtobs.setRows(5);
        jScrollPane1.setViewportView(txtobs);

        jButton4.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jButton4.setText("VOLTAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("ALTERAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnovo.setText("NOVO");
        btnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnovoActionPerformed(evt);
            }
        });

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(204, 255, 204));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Dados do Professor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(539, 539, 539)
                .addComponent(jLabel25)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(32, 32, 32)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnovo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(531, 531, 531)
                        .addComponent(jLabel22)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnumeroruaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumeroruaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumeroruaActionPerformed

    private void txtdtinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdtinicioActionPerformed

    }//GEN-LAST:event_txtdtinicioActionPerformed

    private void jcmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmtActionPerformed

    private void btnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnovoActionPerformed
        String sql,dtin,modalidade;
        
        if(!txtnome.getText().equals("")&& !txtnomepai.getText().equals("") &&!txtnomemae.getText().equals("")&&!txtcpf.getText().equals("   .   .   -  ")&&!txtrg.getText().equals("")&&!txttel1.getText().equals("(  )    -    ")&&
            !txttel2.getText().equals("(  )     -    ") &&!txtrua.getText().equals("") &&!txtnumerorua.getText().equals("") && !txtbairro.getText().equals("") &&
            !txtcidade.getText().equals("") &&!txtcep.getText().equals("")&& !txtdtnascimento.getText().equals("  /  /    ")&& !txtloguin.getText().equals("")&& !txtsenha1.getText().equals("")){
             if(txtsenha1.getText().equals(txtsenha2.getText())){
              verif_login();
              verifica_CPF();
                 if(valido==false){  
     
                   
        Date data = new Date();    
         
          SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
       
     sql = "insert into professor values(0,'" + txtnome.getText() + "','" + txtnomepai.getText() + "','" + txtnomemae.getText()
            + "','" + txtcpf.getText() + "','" + txtrg.getText() + "','" + txttel1.getText()+ "','" + txttel2.getText() +
          "','" + txtemail.getText() + "','" + txtrua.getText() + "','" + txtnumerorua.getText() + "','" +
         txtcomplemento.getText() + "','" + txtbairro.getText() + "','" + txtcidade.getText() + "','" +
          txtcep.getText() + "','" + txtdtnascimento.getText() +  "','" + format.format(data) +  "','" + txtobs.getText() + "','" + txtloguin.getText() + "','" + txtsenha1.getText() + "')";

      System.out.println(sql);

         if(jcmtmanha.isSelected()){
           Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',1)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
         if(jcmt.isSelected()){
           Integer M = 0;
          modalidade = "insert into moda_prof values (0,'"+ txtcpf.getText() + "',2)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
         if(jctwd.isSelected()){
           Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',3)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
           if(jcjj.isSelected()){
           Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',4)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
      }  
           if(jccpr.isSelected()){
           Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',5)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
           if(jcboxe.isSelected()){
           Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',6)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
        if(jcmma.isSelected()){
        Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',7)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }
         if(jcjudo.isSelected()){
        Integer M = 0;
          modalidade = "insert into moda_prof values (0,'" + txtcpf.getText() + "',8)";
                System.out.println(modalidade);
            bd.executarcomandos(modalidade); 
           }     
         
                if( bd.executarcomandos(sql)>0){
                JOptionPane.showMessageDialog(null,"Cadastro efetuado com sucesso");
                 limpar();

            }
               

               }
               else{
                  JOptionPane.showMessageDialog(null,"Login ou CPF ja Cadastrado"); 
       valido=false;
               }
             }
             else {
                  JOptionPane.showMessageDialog(null,"Senhas Diferentes");
             }           
        }
        else {
            JOptionPane.showMessageDialog(null,"Campos * obrigatorios");
        }
    
    }//GEN-LAST:event_btnovoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(!txtcpf.getText().equals("") && !txtsenha2.getText().equals("")){
        if(txtsenha1.getText().equals(txtsenha2.getText())){
         verifica_CPF();
         if(ID_professor == ko){
       
        String sql = " update professor set nome = '" + txtnome.getText()+"', nomepai = '"
        + txtnomepai.getText() + "', nomemae = '" + txtnomemae.getText() + "', RG = '" + txtrg.getText() + "', tel1 = '" + txttel1.getText() +
        "',tel2 = '" + txttel2.getText() + "',email = '" + txtemail.getText()+ "', rua = '"
        + txtrua.getText() + "', numero = '" + txtnumerorua.getText() +"', complemento = '"
        + txtcomplemento.getText() + "', bairro = '" + txtbairro.getText() + "', cidade = '" + txtcidade.getText() + "', cep = '"
        + txtcep.getText() + "', Dtnascimento = '" + txtdtnascimento.getText() + "', obs = '"+ txtobs.getText() +"', senha = '" + txtsenha1.getText() + "' where CPF = '"+ txtcpf.getText() +"'";
        verifi_modalidade();
        excl_modalidade();
            if( bd.executarcomandos(sql)>0){
                JOptionPane.showMessageDialog(null,"Alteração efetuada com sucesso !! ");
                 limpar();
        }
         }
         else{
             JOptionPane.showMessageDialog(null,"CPF ja cadastrado");
         }
        }
        else {
            JOptionPane.showMessageDialog(null,"Campo CPF Vazio");
        }
        }
        else{
            JOptionPane.showMessageDialog(null,"Senhas diferentes ");
        }
        
                                        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new frmMenu().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean s3 = false;
        if(!txtloguin.getText().equals("") || !txtcpf.getText().equals("")){
        
            String sql = "select * from professor where (nome ='" + txtnome.getText() 
                +"' or loguin ='" + txtloguin.getText() + "' or CPF ='" + txtcpf.getText()+ "')" ; 
        
         try{
             
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next())
            {   
            ID_professor = rs.getInt("ID");
            txtnome.setText("" + rs.getString("nome"));
            txtnomepai.setText("" + rs.getString("nomepai"));
            txtnomemae.setText("" + rs.getString("nomemae"));
            txtcpf.setText("" + rs.getString("CPF")); 
            txtrg.setText("" + rs.getString("RG"));
            txttel1.setText("" + rs.getString("tel1"));
            txttel2.setText("" + rs.getString("tel2"));
            txtemail.setText("" + rs.getString("email"));
            txtrua.setText("" + rs.getString("rua"));
            txtnumerorua.setText("" + rs.getString("numero"));
            txtcomplemento.setText("" + rs.getString("complemento"));
            txtbairro.setText("" + rs.getString("bairro"));
            txtcidade.setText("" + rs.getString("cidade"));
            txtcep.setText("" + rs.getString("cep"));
            txtdtnascimento.setText("" + rs.getString("Dtnascimento"));
            Date dt = rs.getDate("Dtinicio");
            txtobs.setText("" + rs.getString("obs"));
            txtloguin.setText("" + rs.getString("loguin"));
             s3 = true;
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            txtdtinicio.setText(""+ format.format(dt));
            
            }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
            s3 = false;
        } 
          if(s3==false){
         JOptionPane.showMessageDialog(null, "Professor não cadastrado"); 
         s3 = true;
     }
         int nmdl = 0;
        
        String s = "SELECT * FROM moda_prof INNER JOIN professor ON moda_prof.id_professor = professor.CPF where CPF ='" + txtcpf.getText()+ "'";         
        try{
            ResultSet sr = bd.executarconsulta(s);
            while(sr.next()){
                System.out.println("sala");
                
                nmdl = Integer.parseInt("" + sr.getInt("id_modalidade"));
             
            if(nmdl == 1){
                jcmtmanha.setSelected(true);
            }
            if(nmdl == 2){
                jcmt.setSelected(true);
            }
            if(nmdl == 3){
                jctwd.setSelected(true);
            }
            if(nmdl == 4){
                jcjj.setSelected(true);
            }
            if(nmdl == 5){
                jccpr.setSelected(true);
            }
            if(nmdl == 6){
                jcboxe.setSelected(true);
            }
            if(nmdl == 7){
                jcmma.setSelected(true);
            }
            if(nmdl == 8){
                jcjudo.setSelected(true);
            }
            System.out.println(nmdl);
              
            }

        }
               catch(SQLException ex){
            System.out.println("Problema na busca");
        }
          
               
           }          
       else {
        JOptionPane.showMessageDialog(null," Nome do Aluno ou CPF obrigatorio");       
                      
       } 
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProfessor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnovo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcboxe;
    private javax.swing.JCheckBox jccpr;
    private javax.swing.JCheckBox jcjj;
    private javax.swing.JCheckBox jcjudo;
    private javax.swing.JCheckBox jcmma;
    private javax.swing.JCheckBox jcmt;
    private javax.swing.JCheckBox jcmtmanha;
    private javax.swing.JCheckBox jctwd;
    private javax.swing.JTextField txtbairro;
    private javax.swing.JTextField txtcep;
    private javax.swing.JTextField txtcidade;
    private javax.swing.JTextField txtcomplemento;
    private javax.swing.JFormattedTextField txtcpf;
    private javax.swing.JTextField txtdtinicio;
    private javax.swing.JFormattedTextField txtdtnascimento;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtloguin;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtnomemae;
    private javax.swing.JTextField txtnomepai;
    private javax.swing.JTextField txtnumerorua;
    private javax.swing.JTextArea txtobs;
    private javax.swing.JTextField txtrg;
    private javax.swing.JTextField txtrua;
    private javax.swing.JPasswordField txtsenha1;
    private javax.swing.JPasswordField txtsenha2;
    private javax.swing.JFormattedTextField txttel1;
    private javax.swing.JFormattedTextField txttel2;
    // End of variables declaration//GEN-END:variables
}
