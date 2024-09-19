package br.com.DAO;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author Kassio Dias Monteiro
 */
public class ConexaoDao {
    public static Connection conector(){
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://localhost:3306/bdprojetologin";
        String user = "root";
        String password = "root";
        
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
            
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Conexao" +e);
            return null;
            
        }
    } 
}
