/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kft2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author KFT
 */
public class Conexao {
 public String driver = "com.mysql.jdbc.Drive";  // Classe do driver JDBC
    public String banco = "kft"; // nome do Banco de dados criado
    public String host = "localhost";//Maquinda onde está o banco de dados
    public String str_conn = "jdbc:mysql://" + host + ":3306/" + banco; //URL de Conexão.
    public String usuario = "root"; // Usuario do Banco
    public String senha = "lab123"; //Senha de conexão
    
        
    Connection con;
    
    //Abri a Conexão com O BD;
    public void ConectarBD()
    {
        try
        {
            con = DriverManager.getConnection(str_conn, usuario, senha);
        }
        catch (SQLException ex)
        {
            System.out.println("Problema com a conexão do BD");
        }
    }
    
    
    // Comano de Insert,Delete, Update
    
    public int executarcomandos (String sql)
    {
        try
        {
            ConectarBD();
              Statement stmt = con.createStatement();
              return stmt.executeUpdate(sql);
        }
        
        
          catch (SQLException ex)
          {
              System.out.println("Problema com o SQL");
              
        }
        return 0;   
    }
        // executar consulta
        
    public ResultSet executarconsulta (String sql)
    {
        try
        {
            ConectarBD();            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            return rs;
        }
        catch (SQLException ex)
        {
            System.out.println("Problema com a Consulta");
        }
    return null;
    }
}
