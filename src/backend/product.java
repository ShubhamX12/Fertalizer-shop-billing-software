/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import backend.connect;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author shubham
 */
public class product {
    connect c1=new connect();
   

    public void createproduct(String text, String text0, String text1) throws SQLException {
c1.ps=c1.con.prepareStatement("insert into product values(?,?,?,?)");
         c1.rs=c1.stmt.executeQuery("select * from product");
         int i=0;
         while(c1.rs.next())
         {
             i++;
         }
         i++;
         c1.ps.setInt(1,i);
         c1.ps.setString(2, text);
         c1.ps.setString(3, text0);  
         
         c1.ps.setString(4, text1);  
         c1.ps.executeUpdate();
         JOptionPane.showMessageDialog(null,"new product Added");        
        
//To change body of generated methods, choose Tools | Templates.
    }

    public int getQuantity(String text) {
        int a=0;
        try{
         
        c1.rs=c1.stmt.executeQuery("select * from product where product_name='"+text+"'");
        while(c1.rs.next())
        {
            a=c1.rs.getInt(3);
        } 
        }catch(Exception e){}
        
        return a;
    }
    
    
}
