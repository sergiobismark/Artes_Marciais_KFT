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
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author KFT
 */
public class FrmAluno extends javax.swing.JFrame {

    /**
     * Creates new form FrmAluno
     */
    public FrmAluno() {
        setExtendedState(MAXIMIZED_BOTH);
        initComponents();
        dtinicio();
        teclasletras();
        teclasnumeros();

     }  Conexao bd = new Conexao();
       
    Scanner ler = new Scanner(System.in);
     boolean valido = false;     
     String pfc;
     String resp;
     int v = 0, ko = 0;
     
     int onumero;
     public void dadospagina (int recebe){
         onumero = recebe;
         seila();
     }
     
     public void seila(){
            String sql = "select * from aluno where ID ="+ onumero+";"; 
            System.out.println(sql);
         try{
             
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next())
            {   
                
            txtid_aluno.setText(""+ rs.getInt("ID"));    
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
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            txtdtinicio.setText(""+ format.format(dt));
            String resp = rs.getString("nomeresponsalve");
                System.out.println("caio aqui dentro");
            if (resp.equals(txtnomemae.getText())){
                chkresponsa2.setSelected(true);
                chkresponsa1.setSelected(false);
            }
            
            if (resp.equals(txtnomepai.getText())){
                chkresponsa1.setSelected(true);
                chkresponsa2.setSelected(false);
            }
            }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
        } 
        int s7 = 1;
        String s = "SELECT * FROM moda_aluno where id_aluno =" + onumero+";"; 
              System.out.println(s);
                
              try{
                ResultSet sr = bd.executarconsulta(s);
                while(sr.next()){
            
                 int nmdl = 0;
                  nmdl = sr.getInt("id_modalidade");
                    
                  System.out.println(nmdl);
            if(nmdl == 1){
                jcmtmanha.setSelected(true);
            }
            else if(nmdl == 2){
                jcmt.setSelected(true);
            }
            else if(nmdl == 3){
                jctwd.setSelected(true);
            }
            else if(nmdl == 4){
                jcjj.setSelected(true);
            }
            else if(nmdl == 5){
                jccpr.setSelected(true);
            }
            else if(nmdl == 6){
                jcboxe.setSelected(true);
            }
            else if(nmdl == 7){
                jcmma.setSelected(true);
            }
            else{
                jcjudo.setSelected(true);
            }
            System.out.println(nmdl);
              
            }
              System.out.println("sala");  
              }
              
              
               catch(SQLException ex){
            System.out.println("Problema na busca");
        }
    
      
     }
     
     public void teclasletras (){
         txtnome.setDocument(new Documento(69, "[^a-z|^A-Z|^ ]"));
         txtnomemae.setDocument(new Documento(69, "[^a-z|^A-Z|^ ]"));
         txtnomepai.setDocument(new Documento(69, "[^a-z|^A-Z|^ ]"));
         txtbairro.setDocument(new Documento(40, "[^a-z|^A-Z|^ ]"));
         txtcidade.setDocument(new Documento(40, "[^a-z|^A-Z|^ ]"));
         txtrua.setDocument(new Documento(57, "[^a-z|^A-Z|^ ]"));
         
     }
     class Documento extends PlainDocument{
         int tam = 0;
         String regex = "";
         public Documento (int a,String r){
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
     public void teclasnumeros(){
         txtcep.setDocument(new Documento(13, "[^0-9]"));
         txtnumerorua.setDocument(new Documento(10, "[^0-9]"));
         txtrg.setDocument(new Documento(10, "[^0-9]"));
     }
     public void verifica_CPF(){
              
     pfc = "select ID,CPF from aluno where CPF ='" + txtcpf.getText() + "'"; 
       
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
     Integer rt = 0;

    
    public void dtinicio(){
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
                
        Date data = new Date();
        
        txtdtinicio.setText("" + dt.format(data));
        
           }
    
    int cd_aluno = 0;

    public void id_aluno(){
       
       
       String t = "select max(ID) as codigo  from aluno "; 
        System.out.println(t);
         try{
             
            ResultSet rs = bd.executarconsulta(t);
            while(rs.next())
            {   
            cd_aluno =  rs.getInt("codigo");
            }
         }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
            }  
    cd_aluno++;
         System.out.println("id aluno" + cd_aluno);
    
    }

    public void limpar(){
    txtnome.setText(null);
    txtnomepai.setText(null);
    txtnomemae.setText(null);
    txtcpf.setValue(null);
    txtrg.setText(null);
    txttel1.setValue(null);
    txttel2.setValue(null);
    txtrua.setText(null);
    txtnumerorua.setText(null);
    txtbairro.setText(null);
    txtcidade.setText(null);
    txtcep.setText(null);
    txtdtnascimento.setValue(null);
    txtcomplemento.setText(null);
    txtemail.setText(null);
    txtid_aluno.setText(null);
    txtobs.setText(null);
    jcboxe.setSelected(false);
    jccpr.setSelected(false);
    jcjj.setSelected(false);
    jcjudo.setSelected(false);
    jcmma.setSelected(false);
    jcmt.setSelected(false);
    jcmtmanha.setSelected(false);
    jctwd.setSelected(false);
    chkresponsa1.setSelected(false);
    chkresponsa2.setSelected(false);
    txtnomemae.setEnabled(true);
    txtnomepai.setEnabled(true);
    dtinicio();
    }
    public void verif_respo(){
         if (chkresponsa1.isSelected()){
             resp = txtnomepai.getText();
                System.out.println(resp);
     }
         if (chkresponsa2.isSelected()){
             resp = txtnomemae.getText();
             System.out.println(resp);
         }
    }
    public void verifi_modalidade(){
            id_aluno();
            System.out.println(cd_aluno+"verificação");
            int s7 = 1;
              boolean o = true;
            cd_aluno -= s7;
            String s = "SELECT * FROM moda_aluno where id_aluno = " + cd_aluno; 
              System.out.println(s+"antes do try");
                
              try{
                ResultSet sr = bd.executarconsulta(s);
                 int nmdl = 0; 
                  int f4 = 0;
                
                while(sr.next()){
                System.out.println(sr.getInt("ID"));
                 
                  nmdl = sr.getInt("ID");
                    f4 = sr.getInt("id_modalidade");
                    System.out.println(nmdl +"insert");
                  System.out.println(f4+"ENTRO NO INSERT");
                                
            String r;
            if(f4!=1||f4==0){
        if(jcmtmanha.isSelected()){
           r = "insert into moda_aluno values (0," + cd_aluno + ",1)";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
            }
            else if(f4!=2|| f4==0){
         if(jcmt.isSelected()){
           r = "insert into moda_aluno values (0,"+ cd_aluno + ",2)";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
            }
            else if (f4!=3|| f4==0){
         if(jctwd.isSelected()){
               r = "insert into moda_aluno values (0," + cd_aluno + ",3)";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
            }
            else if (f4 !=4|| f4==0){
           if(jcjj.isSelected()){
           r = "insert into moda_aluno values (0," + cd_aluno + ",4)";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  }
            else if (f4!=5|| f4==0){
           if(jccpr.isSelected()){
            r = "insert into moda_aluno values (0," + cd_aluno + ",5)";
                System.out.println(r);
            bd.executarcomandos(r); 
           }}
            else if (f4!=6|| f4==0){
           if(jcboxe.isSelected()){
            r = "insert into moda_aluno values (0," + cd_aluno + ",6)";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
            }
            if(f4!=7|| f4==0){
            
        if(jcmma.isSelected()){
         r = "insert into moda_aluno values (0," + cd_aluno + ",7)";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
            }
            if(f4!=8|| f4==0){
         if(jcjudo.isSelected()){
        r = "insert into moda_aluno values (0," + cd_aluno + ",8)";
                System.out.println(r);
            bd.executarcomandos(r); 
           }}
                o = false;
                }
              
                if(o==true){
              String r;
         if(jcmtmanha.isSelected()){
           r = "insert into moda_aluno values (0," + cd_aluno + ",1);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
         if(jcmt.isSelected()){
           r = "insert into moda_aluno values (0,"+ cd_aluno + ",2);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
         if(jctwd.isSelected()){
               r = "insert into moda_aluno values (0," + cd_aluno + ",3);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
           if(jcjj.isSelected()){
           r = "insert into moda_aluno values (0," + cd_aluno + ",4);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
           if(jccpr.isSelected()){
            r = "insert into moda_aluno values (0," + cd_aluno + ",5);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
           if(jcboxe.isSelected()){
            r = "insert into moda_aluno values (0," + cd_aluno + ",6);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
        if(jcmma.isSelected()){
         r = "insert into moda_aluno values (0," + cd_aluno + ",7);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
         if(jcjudo.isSelected()){
        r = "insert into moda_aluno values (0," + cd_aluno + ",8);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }     
      
                }
              }
               catch(SQLException ex){
            System.out.println("Problema na busca");
        }
    }

    public void excl_modalidade(){
        String modalidade;
         id_aluno();
            System.out.println(cd_aluno);
        
        int s7 = 1;
        s7 = cd_aluno - s7;
        String s = "SELECT * FROM moda_aluno where id_aluno =" + s7; 
              System.out.println(s);
                
              try{
                ResultSet sr = bd.executarconsulta(s);
                
                while(sr.next()){
                System.out.println(sr.getInt("ID"));
                 int nmdl = 0; 
                  int f4 = 0;
                 
                  nmdl = sr.getInt("ID");
                    f4 = sr.getInt("id_modalidade");
                    System.out.println(nmdl +"deleteee");
                  System.out.println(f4+"ENTRO NO DELETE");
                  
        
       
                if(f4==1){
               if(!jcmtmanha.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }
                if(f4==2){
             if(!jcmt.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }
            if(f4==3){
             if(!jctwd.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }      if(f4==4){
             if(!jcjj.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }      if(f4==5){
             if(!jccpr.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }      if(f4==6){
             if(!jcboxe.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }      if(f4==7){
             if(!jcmma.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         }      if(f4==8){
             if(!jcjudo.isSelected()){
                modalidade = "delete from moda_aluno where ID = " + nmdl;
                System.out.println(modalidade);
                bd.executarcomandos(modalidade); 
      }  
         } }
              
              }       
               catch(SQLException ex){
            System.out.println("Problema na busca");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
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
        jLabel8 = new javax.swing.JLabel();
        chkresponsa1 = new javax.swing.JCheckBox();
        chkresponsa2 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
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
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtdtinicio = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtcpf = new javax.swing.JFormattedTextField();
        txtdtnascimento = new javax.swing.JFormattedTextField();
        txttel2 = new javax.swing.JFormattedTextField();
        txttel1 = new javax.swing.JFormattedTextField();
        txtrg = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jcmt = new javax.swing.JCheckBox();
        jctwd = new javax.swing.JCheckBox();
        jccpr = new javax.swing.JCheckBox();
        jcjj = new javax.swing.JCheckBox();
        jcmtmanha = new javax.swing.JCheckBox();
        jcboxe = new javax.swing.JCheckBox();
        jcmma = new javax.swing.JCheckBox();
        jcjudo = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        txtid_aluno = new javax.swing.JTextField();
        btnovo = new javax.swing.JButton();

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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("* Aluno menor, marcar campo responsável *");

        chkresponsa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkresponsa1ActionPerformed(evt);
            }
        });

        chkresponsa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkresponsa2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("* Obrigatório dois (02) telefones para contato.");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Celular");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Email:");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrua, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnumerorua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtcomplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcidade, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(195, 195, 195)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(txtnumerorua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtcomplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

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

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Fixo");

        jLabel18.setBackground(new java.awt.Color(204, 255, 204));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Modalidades");

        try {
            txtcpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtdtnascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txttel2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txttel1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel21)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtnomepai, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                            .addComponent(txtnomemae))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkresponsa1)
                                            .addComponent(chkresponsa2)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel8)))))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdtnascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(txtdtinicio)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel9)
                                                    .addGap(436, 436, 436))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txttel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel23)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txttel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel19)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(217, 217, 217)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtcpf, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                            .addComponent(txtrg))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(28, 28, 28))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addComponent(jLabel18)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdtnascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtnomepai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkresponsa1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtnomemae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkresponsa2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txttel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txttel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9))))
                    .addComponent(txtdtinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7)
                    .addComponent(txtrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel18)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(204, 255, 204));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Dados do Aluno");

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
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ALTERAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jButton4.setText("VOLTAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Observação:");

        txtobs.setColumns(20);
        txtobs.setRows(5);
        jScrollPane1.setViewportView(txtobs);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("* ID:");

        txtid_aluno.setEditable(false);

        btnovo.setText("NOVO");
        btnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(343, 343, 343)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(btnovo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel24)
                    .addComponent(txtid_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmtActionPerformed

    private void btnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnovoActionPerformed
   if(!txtnome.getText().equals("") && !txtnomepai.getText().equals("") && !txtnomemae.getText().equals("") && !txtcpf.getText().equals("   .   .   -  ") && !txtrg.getText().equals("") && !txttel1.getText().equals("(  )    -    ") && 
            !txttel2.getText().equals("(  )    -     ") && !txtrua.getText().equals("") && !txtnumerorua.getText().equals("") && !txtbairro.getText().equals("") &&
           !txtcidade.getText().equals("") && !txtcep.getText().equals("") && !txtdtnascimento.getText().equals("  /  /    ")){ 
   verifica_CPF();
    if(valido==false){
   verif_respo();
   String sql, modalidade;  
   Date data = new Date();    
   SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
       
     sql = "insert into aluno values(0,'" + txtnome.getText() + "','" + txtnomepai.getText() + "','" + txtnomemae.getText()
            + "','" + txtcpf.getText() + "','" + txtrg.getText() + "','" + txttel1.getText()+ "','" + txttel2.getText() +
          "','" + txtemail.getText() + "','" + txtrua.getText() + "','" + txtnumerorua.getText() + "','" +
         txtcomplemento.getText() + "','" + txtbairro.getText() + "','" + txtcidade.getText() + "','" +
          txtcep.getText() + "','" + txtdtnascimento.getText() +  "','" + format.format(data) +  "','" + resp +"','" + txtobs.getText() + "','9999-01-01')";

        System.out.println(sql);
      System.out.println(sql);
        
        id_aluno();
          
         String r;
         if(jcmtmanha.isSelected()){
           r = "insert into moda_aluno values (0," + cd_aluno + ",1);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
         if(jcmt.isSelected()){
           r = "insert into moda_aluno values (0,"+ cd_aluno + ",2);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
         if(jctwd.isSelected()){
               r = "insert into moda_aluno values (0," + cd_aluno + ",3);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
           if(jcjj.isSelected()){
           r = "insert into moda_aluno values (0," + cd_aluno + ",4);";
                System.out.println(r);
            bd.executarcomandos(r); 
      }  
           if(jccpr.isSelected()){
            r = "insert into moda_aluno values (0," + cd_aluno + ",5);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
           if(jcboxe.isSelected()){
            r = "insert into moda_aluno values (0," + cd_aluno + ",6);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
        if(jcmma.isSelected()){
         r = "insert into moda_aluno values (0," + cd_aluno + ",7);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }
         if(jcjudo.isSelected()){
        r = "insert into moda_aluno values (0," + cd_aluno + ",8);";
                System.out.println(r);
            bd.executarcomandos(r); 
           }     

         if( bd.executarcomandos(sql)>0){
                JOptionPane.showMessageDialog(null, "Aluno Cadastrado ... Codigo do Aluno: " + cd_aluno);
                 limpar();

            }
               
   }
   else {
       JOptionPane.showMessageDialog(null,"CPF ja Cadastrado"); 
       valido=false;
               
   }
                 
             }
           
        else {
            JOptionPane.showMessageDialog(null,"Campos * obrigatorios");
        }
    }//GEN-LAST:event_btnovoActionPerformed

    private void txtnumeroruaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumeroruaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumeroruaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new frmMenu().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtdtinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdtinicioActionPerformed
      
        
    }//GEN-LAST:event_txtdtinicioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(!txtnome.getText().equals("") || !txtcpf.getText().equals("   .   .   -  ")){
        boolean s3 = false;        
        boolean f = false;
        if(f==false){
         String sql = "select * from aluno where (nome ='" + txtnome.getText() 
                +"' or CPF = '" + txtcpf.getText() + "')"; 
            System.out.println(sql);
            System.out.println(f);
         try{
             
            ResultSet rs = bd.executarconsulta(sql);
            while(rs.next())
            {   
                
            txtid_aluno.setText(""+ rs.getInt("ID"));    
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
             s3 = true;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            txtdtinicio.setText(""+ format.format(dt));
            String resp = rs.getString("nomeresponsalve");
            
            if (resp.equals(txtnomemae.getText())){
                chkresponsa2.setSelected(true);
                chkresponsa1.setSelected(false);
            }
            
            if (resp.equals(txtnomepai.getText())){
                chkresponsa1.setSelected(true);
                chkresponsa2.setSelected(false);
            }
            }
        }
        catch (SQLException ex) {
             System.out.println("Problema na busca");         
            s3 = false;
        } 
          if(s3==false){
         JOptionPane.showMessageDialog(null, "Aluno não cadastrado"); 
         s3 = false;
     }      
          if (s3==true){
         id_aluno();
            System.out.println(cd_aluno);
        
        int s7 = 1;
        s7 = cd_aluno - s7;
        String s = "SELECT * FROM moda_aluno where id_aluno =" + s7; 
              System.out.println(s);
                
              try{
                ResultSet sr = bd.executarconsulta(s);
                while(sr.next()){
        System.out.println(sr.getInt("id_modalidade")+"idmodalidade");

                 int nmdl = 0;
                  nmdl = sr.getInt("id_modalidade");
                    
                  System.out.println(nmdl);
            if(nmdl == 1){
                jcmtmanha.setSelected(true);
            }
            else if(nmdl == 2){
                jcmt.setSelected(true);
            }
            else if(nmdl == 3){
                jctwd.setSelected(true);
            }
            else if(nmdl == 4){
                jcjj.setSelected(true);
            }
            else if(nmdl == 5){
                jccpr.setSelected(true);
            }
            else if(nmdl == 6){
                jcboxe.setSelected(true);
            }
            else if(nmdl == 7){
                jcmma.setSelected(true);
            }
            else{
                jcjudo.setSelected(true);
            }
            System.out.println(nmdl);
              
            }
              System.out.println("sala");  
        }
              
              
               catch(SQLException ex){
            System.out.println("Problema na busca");
        }
    
        
       }
        }
       }
       
       else {
              JOptionPane.showMessageDialog(null,"Campo Nome ou CPF obrigadorio !! ");
                     
       }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:''
        if(!txtid_aluno.getText().equals("")){
         verifica_CPF();
         if(!txtid_aluno.getText().equals(ko)){
            verif_respo();
        String sql = " update aluno set nome = '" + txtnome.getText()+"', nomepai = '"
        + txtnomepai.getText() + "', nomemae = '" + txtnomemae.getText() + "', CPF ='" + txtcpf.getText() + "', RG = '" + txtrg.getText() + "', tel1 = '" + txttel1.getText() + 
        "',tel2 = '" + txttel2.getText() + "',email = '" + txtemail.getText()+ "', rua = '"
        + txtrua.getText() + "', numero = '" + txtnumerorua.getText() +"', complemento = '"
        + txtcomplemento.getText() + "', bairro = '" + txtbairro.getText() + "', cidade = '" + txtcidade.getText() + "', cep = '"
        + txtcep.getText() + "', Dtnascimento = '" + txtdtnascimento.getText() + "', nomeresponsalve = '"+ resp +"', obs = '" + txtobs.getText() + "' where ID = "+ txtid_aluno.getText();
           id_aluno();
        excl_modalidade();
           verifi_modalidade();
        
        if( bd.executarcomandos(sql)>0){
                JOptionPane.showMessageDialog(null,"Alteração efetuada com sucesso !! ");
                 limpar();
        }
        }
         
        else{
            JOptionPane.showMessageDialog(null,"CPF ja Cadastrado");
        }
        }
                 else{
             JOptionPane.showMessageDialog(null,"Campo ID vazio !! ");
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void chkresponsa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkresponsa1ActionPerformed
        // TODO add your handling code here:
        if(chkresponsa1.isSelected()){
             txtnomepai.setEnabled(false);
            chkresponsa2.setSelected(false);
            txtnomemae.setEnabled(true);
         }
        else{
        txtnomepai.setEnabled(true);
        }
        
    }//GEN-LAST:event_chkresponsa1ActionPerformed

    private void chkresponsa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkresponsa2ActionPerformed
        // TODO add your handling code here:
        if(chkresponsa2.isSelected()){
             txtnomemae.setEnabled(false);
             chkresponsa1.setSelected(false);
             txtnomepai.setEnabled(true);
         }
        else{
        txtnomemae.setEnabled(true);
        }
    }//GEN-LAST:event_chkresponsa2ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnovo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JCheckBox chkresponsa1;
    private javax.swing.JCheckBox chkresponsa2;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JTextField txtid_aluno;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtnomemae;
    private javax.swing.JTextField txtnomepai;
    private javax.swing.JTextField txtnumerorua;
    private javax.swing.JTextArea txtobs;
    private javax.swing.JTextField txtrg;
    private javax.swing.JTextField txtrua;
    private javax.swing.JFormattedTextField txttel1;
    private javax.swing.JFormattedTextField txttel2;
    // End of variables declaration//GEN-END:variables
}
