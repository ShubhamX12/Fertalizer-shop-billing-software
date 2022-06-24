/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import backend.deailor;
import java.util.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fertalizer.frontend.*;

import javax.swing.JOptionPane;

/**
 *
 * @author shubham
 */
public class deailor {
    connect c1=new connect();
    public void createdeailor(String text, String text0, String text1, String text2) throws SQLException {
         c1.ps=c1.con.prepareStatement("insert into company values(?,?,?,?,?,?,?)");
         c1.rs=c1.stmt.executeQuery("select company_id from company");
         int i=0;
         while(c1.rs.next())
         {
              i=c1.rs.getInt(1);
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
         JOptionPane.showMessageDialog(null,"deailor created...");
    }

    

    public String[] getdeailorDetails(String text) {
         String s[]=new String[7];
        try{
            
            c1.rs=c1.stmt.executeQuery("select * from company where  company_id='"+Integer.parseInt(text)+"'");
            
            while(c1.rs.next())
            {
                s[0]=String.valueOf(c1.rs.getInt(1));
                s[1]=c1.rs.getString(2);
                s[2]=c1.rs.getString(3);
                s[3]=c1.rs.getString(4);
                s[4]=c1.rs.getString(5);
                s[5]=String.valueOf(c1.rs.getInt(6));
                
                s[6]=String.valueOf(c1.rs.getInt(7));
                
                
                
                
            }
             
            
        }catch(Exception e){}        
        

       return s;


//To change body of generated methods, choose Tools | Templates.
    }

    public boolean isdeleted(String text){
        try{
    c1.ps=c1.con.prepareStatement("delete  from company where company_id='"+Integer.parseInt(text)+"'");
    c1.ps.executeUpdate();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    return true;
  
    }

    public void UpdatedeailorDetails(String text, String text0, String text1, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void UpdatedeailorDetails(String text, String text0, String text1, String text2, String text3) throws SQLException {
      c1.ps=c1.con.prepareStatement("update company set name='"+text+"' ,address='"+text0+"',phone='"+text1+"' ,email='"+text2+"'  where company_id='"+Integer.parseInt(text3)+"'");     
      c1.ps.executeUpdate();
    }

    public String[] getdeailorDetails1(String text) throws SQLException {
        List l=new ArrayList(); 
         String pattern="[a-zA-Z0-9: ]*"+text+"[a-zA-Z0-9: ]*";
        Pattern p=Pattern.compile(pattern);
        Matcher m;
        c1.rs=c1.stmt.executeQuery("select * from company");
        while(c1.rs.next())
        {
            String r=c1.rs.getString(2);
            m=p.matcher(r);
            if(m.matches())
            {
                l.add(r);
            }
        }
        String s[]=new String[l.size()];
        for(int i=0;i<l.size();i++)
        {
            s[i]=(String)l.get(i);
        }
           
          return s;
    
    }

    public int getcountDeailor() throws SQLException {
    int i=0;
    c1.rs=c1.stmt.executeQuery("select * from company");
    while(c1.rs.next())
    {
        i++;
    }
    return ++i;
    
    }

    public String[] getDetails(int i) {
        String s[]=new String[6];
        try{
        c1.rs=c1.stmt.executeQuery("select * from company where company_id='"+i+"'");
        while(c1.rs.next())
        {
            s[0]=String.valueOf(c1.rs.getInt(1));
            s[1]=c1.rs.getString(2);
            s[2]=c1.rs.getString(3);
            s[3]=c1.rs.getString(4);
            s[4]=c1.rs.getString(5);
            s[5]=String.valueOf(c1.rs.getInt(7));
              
        }
      
        }catch(Exception e){}
        return s;

    }

    public int getID(String r) {
     int id=0;
     try{ 
     c1.rs=c1.stmt.executeQuery("select company_id from company where name='"+r+"'");
     while(c1.rs.next())
     {
         id=c1.rs.getInt(1);
     }
     }catch(Exception e){}
     return id;
    }

    public String[] getDetails_paid_price(String text) {
     String s[]=new String[2];
        try{
            
             
            c1.rs=c1.stmt.executeQuery("select * from company where  name='"+text+"'");
            
            while(c1.rs.next())
            {
                s[0]=String.valueOf(c1.rs.getInt(6));
                s[1]=String.valueOf(c1.rs.getInt(7));
                 
            }
             
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }        
        

       return s;    
    
    
    }

   
     

    public boolean isupdatePayment(String text, String text0, String text1) {
       
         
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isupdatePayment(String text, String text0, String text1, String text2) {
        try{
        int a=Integer.parseInt(text)+Integer.parseInt(text1);
        c1.ps=c1.con.prepareStatement("update company set paid_price='"+a+"',reaming_price='"+(Integer.parseInt(text0)-Integer.parseInt(text1))+"' where name='"+text2+"' ");
        c1.ps.executeUpdate();
        return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return false;
        } 
    }

    public ArrayList<ArrayList<String>> getDetailss() {
      ArrayList<ArrayList<String>>l=new ArrayList();
      
        try{
        c1.rs=c1.stmt.executeQuery("select * from company");
        while(c1.rs.next())
        {
            ArrayList l2=new ArrayList();
            l2.add(String.valueOf(c1.rs.getInt(1)));
            l2.add(c1.rs.getString(2));
            l2.add(c1.rs.getString(3));
            l2.add(c1.rs.getString(4));
            l2.add(c1.rs.getString(5));
            l2.add(String.valueOf(c1.rs.getInt(7)));
            l.add(l2);
              
        }
      
        }catch(Exception e){}
        return l;

    }
    
}
