/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import backend.connect;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import fertalizer.frontend.*;
/**
 *
 * @author shubham
 */
public class customer {

    connect c1=new connect();
    public void createcustomer(String text, String text0, String text1, String text2) throws SQLException {
         c1.ps=c1.con.prepareStatement("insert into customer values(?,?,?,?,?,?,?)");
         c1.rs=c1.stmt.executeQuery("select * from customer");
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
         c1.ps.setString(5, text2);
         c1.ps.setInt(6, 0);
         c1.ps.setInt(7,0);
         c1.ps.executeUpdate();
         JOptionPane.showMessageDialog(null,"customer created...");
    }
    
    
    
}
