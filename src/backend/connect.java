/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;


import java.sql.*;
import java.io.*;
import javax.swing.*;
public class connect {
    
    public Connection con; 
    public Statement stmt,stmt1; 
    public PreparedStatement ps;
    public ResultSet rs,r1,r2;
    public connect()  
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
        stmt=con.createStatement();
        stmt1=con.createStatement();
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
}
