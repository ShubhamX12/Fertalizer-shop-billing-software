/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
 

import fertalizer.frontend.ADD_CUSTOMER;
import fertalizer.frontend.Add_product;
import fertalizer.frontend.company;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author shubham
 */

public class Company_billing {

    connect c1=new connect();
     public int getBillno() { 
    try
        {
            int r;
            c1.rs=c1.stmt.executeQuery("select * from deailorbill");
            int i=0;
            while(c1.rs.next())
            {
                i++;
            }
            return i+1;
        }
        catch(Exception e){}
     
        return 0;



//To change body of generated methods, choose Tools | Templates.
    }

    public String[] Defaultvalue() {
          List l=new ArrayList();
        
        try
        {
            c1.rs=c1.stmt.executeQuery("select * from company");
            while(c1.rs.next())
            {
                l.add(c1.rs.getString(2));
            }
            
        }catch(Exception e){}
        String s[]=new String[l.size()];
        for(int i=0;i<s.length;i++)
        {
            s[i]=(String)l.get(i);
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }

    public String[] Defaultproduct() {
          
        
          List l=new ArrayList();
        
        try
        {
            c1.rs=c1.stmt.executeQuery("select * from product");
            while(c1.rs.next())
            {
                l.add(c1.rs.getString(2));
            }
            
        }catch(Exception e){}
        String s[]=new String[l.size()];
        for(int i=0;i<s.length;i++)
        {
            s[i]=(String)l.get(i);
        }
        return s;
        
        //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getSearchResult(String toString) { 
        
     String patten="[a-zA-Z ]*"+toString+"[a-zA-Z ]*";
        Pattern p=Pattern.compile(patten);
        List l=new ArrayList();
        try{
        c1.rs=c1.stmt.executeQuery("select * from company");
        while(c1.rs.next()){
            String r=c1.rs.getString(2);
            Matcher m=p.matcher(r);
            if(m.matches())
            {
                l.add(r);
            }
        }
        }catch(Exception e){}
        String s[]=new String[l.size()];
        for(int i=0;i<s.length;i++)
        {
            s[i]=(String)l.get(i);
        }
        if(s.length==0){ 
            new company().setVisible(true);
        }
        return s;
    }

    public String[] getCustomerDetails(String jitem) {
 String s[]=new String[5];
        try{
        c1.rs=c1.stmt.executeQuery("select * from company");
        while(c1.rs.next())
        {
            if(c1.rs.getString(2).equals(jitem))
            {
            s[0]=(String)c1.rs.getString(2);
            s[1]=(String)c1.rs.getString(3);
            s[2]=(String)c1.rs.getString(4);
            s[3]=(String)c1.rs.getString(5);
            s[4]=(String)c1.rs.getString(7);
            }
        }
        }catch(Exception e){}
        
       return s;           
        
//To change body of generated methods, choose Tools | Templates.
    }

    public String[] getSearchResults(String text) {
         String patten="[a-zA-Z0-9: ]*"+text+"[a-zA-Z0-9: ]*";
        Pattern p=Pattern.compile(patten);
        List l=new ArrayList();
        try{
        c1.rs=c1.stmt.executeQuery("select * from product");
        while(c1.rs.next()){
            String r=c1.rs.getString(2);
            Matcher m=p.matcher(r);
            if(m.matches())
            {
                l.add(r);
            }
        }
        }catch(Exception e){}
        String s[]=new String[l.size()];
        for(int i=0;i<s.length;i++)
        {
            s[i]=(String)l.get(i);
        }
        if(s.length==0){
         new Add_product().setVisible(true);   
        }
        return s;         


//To change body of generated methods, choose Tools | Templates.
    }

    public String[] getProductDetails(String jitem) {
      String s[]=new String[3];
        try{
        c1.rs=c1.stmt.executeQuery("select * from product");
        while(c1.rs.next())
        {
            if(c1.rs.getString(2).equals(jitem))
            {
            s[0]=(String)c1.rs.getString(2);
            s[1]=(String)c1.rs.getString(3);
            s[2]=(String)c1.rs.getString(4); 
            }
        }
        }catch(Exception e){}
        
       return s;        


//To change body of generated methods, choose Tools | Templates.
    }

    public void insertRecord(String text, String text0, String text1, String text2, String text3, String text4, String text5) {
     try{
          
     c1.rs=c1.stmt.executeQuery("select product_id from product where product_name='"+text+"'");
     int prdouct_id=0;
     while(c1.rs.next())
     {
         prdouct_id=c1.rs.getInt(1);
     }
 
      c1.rs=c1.stmt.executeQuery("select   company_id from company where name='"+text0+"'");
     int deailor_id=0;
     while(c1.rs.next())
     {
         deailor_id=c1.rs.getInt(1);
     }
             
     c1.ps=c1.con.prepareStatement("insert into deailor_product_details values(?,?,?,?,?,?)");
     c1.ps.setInt(1, prdouct_id);
     c1.ps.setInt(2, deailor_id);
     c1.ps.setInt(3, Integer.parseInt(text1));
     c1.ps.setInt(4, Integer.parseInt(text2));
     c1.ps.setInt(5, Integer.parseInt(text3));
     
     c1.ps.setString(6,text5);
     c1.ps.executeUpdate();
     
     JOptionPane.showMessageDialog(null,"Hi");
     
     int a=Integer.parseInt(text4)+Integer.parseInt(text1);
     c1.ps=c1.con.prepareStatement("update product set Qty='"+a+"' where product_id="+prdouct_id);
     c1.ps.executeUpdate();
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }
        
        


//To change body of generated methods, choose Tools | Templates.
    }

    public boolean isinsertBill(String text, String text0, String text1, String text2, String text3) {
     try{
        int a=Integer.parseInt(text);
        c1.rs=c1.stmt.executeQuery("select company_id from company where name='"+text0+"'");
        int company_id=-1;
        while(c1.rs.next())
        {
            company_id=c1.rs.getInt(1);
        }
        c1.ps=c1.con.prepareStatement("insert into deailorbill values(?,?,?,?,?)");
        c1.ps.setInt(1,a);
        c1.ps.setInt(2,company_id);
        c1.ps.setInt(3,Integer.parseInt(text1));
        c1.ps.setInt(4,Integer.parseInt(text2));
        c1.ps.setInt(5,Integer.parseInt(text3));
        c1.ps.executeUpdate();
         
        c1.ps=c1.con.prepareStatement("update company set  paid_price='"+text2+"',reaming_price='"+text3+"' where name='"+text0+"'");
        c1.ps.executeUpdate();
        return true;
       }catch(Exception e){}
         return false;         
        
//To change body of generated methods, choose Tools | Templates.
    }

 
    
    
}
