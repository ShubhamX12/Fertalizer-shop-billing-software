/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.*;
import java.io.*;
import backend.connect;
import javax.swing.*;
 
public class login {
    
    public boolean logincheck(String username,String password)
    {
        boolean value=false;
        try{
           connect c1=new connect();
           c1.rs=c1.stmt.executeQuery("select * from login"); 
           
           while(c1.rs.next())
          {
              String us=c1.rs.getString(1);
              
              String ps=c1.rs.getString(2); 
             if(username.equals(us) && password.equals(ps))
             {
                 value=true;
             }
             else
             {
                return  false;
             }
          }
        }catch(Exception e){}
        return value;
    }
    
}
