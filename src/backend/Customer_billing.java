/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import backend.connect;
import fertalizer.frontend.ADD_CUSTOMER;
import java.sql.SQLException;
import java.util.regex.*;  
import java.util.*;
import javax.swing.*;
import fertalizer.frontend.*;
public class Customer_billing {
    
    connect c1;
    HashMap<Integer,String> h1=new HashMap();
    
    HashMap<Integer,String> h3=new HashMap();
    HashMap<String,Integer> h5=new HashMap();
    public Customer_billing()
    {
          c1=new connect();
          h1=getProductInfo();
          h3=getCustomerInfo();
          h5=getDateInfo();
          
    }
    public int getBillno()
    { 
        try
        {
            int r;
            c1.rs=c1.stmt.executeQuery("select * from bill");
            int i=0;
            while(c1.rs.next())
            {
                i++;
            }
            return i+1;
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
     
        return 0;
    }
    public String[] getSearchResult(String matchpattern)
    {
        String patten="[a-zA-Z ]*"+matchpattern+"[a-zA-Z ]*";
        Pattern p=Pattern.compile(patten);
        List l=new ArrayList();
        try{
        c1.rs=c1.stmt.executeQuery("select * from customer");
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
            new ADD_CUSTOMER().setVisible(true);
        }
        return s;
    }

    public String[] getCustomerDetails(String jitem) {
         String s[]=new String[5];
        try{
        c1.rs=c1.stmt.executeQuery("select * from customer");
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
        if(s.length==0)JOptionPane.showMessageDialog(null,"product not exits");
        return s;
        
        
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
    }

    public String[] Defaultvalue() {
        List l=new ArrayList();
        
        try
        {
            c1.rs=c1.stmt.executeQuery("select * from customer");
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

    public void insertRecord(String text, String text0, String text1, String text2, String text3,String text4,String text5) throws SQLException {
     try{
          
     c1.rs=c1.stmt.executeQuery("select product_id from product where product_name='"+text+"'");
     int prdouct_id=0;
     while(c1.rs.next())
     {
         prdouct_id=c1.rs.getInt(1);
     }
      
      c1.rs=c1.stmt.executeQuery("select cust_id from customer where name='"+text0+"'");
     int cust_id=0;
     while(c1.rs.next())
     {
         cust_id=c1.rs.getInt(1);
     }
     
     
     c1.ps=c1.con.prepareStatement("insert into product_details values(?,?,?,?,?,?,?)");
     c1.ps.setInt(1, prdouct_id);
     c1.ps.setInt(2, cust_id);
     c1.ps.setInt(3, Integer.parseInt(text1));
     c1.ps.setInt(4, Integer.parseInt(text2));
     c1.ps.setInt(5, Integer.parseInt(text3));
     
     c1.ps.setString(6,text5);
     int r=getRowCount();
     c1.ps.setInt(7,r);
     
     c1.ps.executeUpdate();
     
     int a=Integer.parseInt(text4)-Integer.parseInt(text1);
     c1.ps=c1.con.prepareStatement("update product set Qty='"+a+"' where product_id="+prdouct_id);
     c1.ps.executeUpdate();
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }

     

    }

     

    public String[] getSearchResultss(String toString) {
     String patten="[ ]*[a-zA-Z ]*"+toString+"+[a-zA-Z ]*[ ]*";
        Pattern p=Pattern.compile(patten);
        List l=new ArrayList();
        try{
        c1.rs=c1.stmt.executeQuery("select * from customer");
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
            JOptionPane.showMessageDialog(null,"Customer not exits.");
        }
        return s;        

    }

    public int getRowCount() {
       int i=0;
      try{
      c1.rs=c1.stmt.executeQuery("select * from product_details");
      
      while(c1.rs.next())
      {
          i++;   
      }
      
      }catch(Exception e){}
      return ++i;
    }

    public String[] getResultproduct(int i) {
     String s[]=new String[6];
     try{
     c1.rs=c1.stmt.executeQuery("select * from  product_details where ID='"+i+"'");
     while(c1.rs.next())
     {
         s[0]=c1.rs.getString(6);
         int a=c1.rs.getInt(2);
         
         s[3]=String.valueOf(c1.rs.getInt(3));
         s[4]=String.valueOf(c1.rs.getInt(4));
         s[5]=String.valueOf(c1.rs.getInt(5));
         s[1]=h3.get(c1.rs.getInt(2));
         s[2]=h1.get(c1.rs.getInt(1));
     }
     }catch(Exception e){ 
     }
    return s;
    }
  

    public ArrayList<ArrayList<String>> getproductdetails(String toString) {
         
        ArrayList<ArrayList<String>> l=new ArrayList();
        try
        {
            int id=0;
            c1.rs =c1.stmt.executeQuery("select * from customer where name='"+toString+"'");
            while(c1.rs.next())
            {
                id=c1.rs.getInt(1);
            } 
             
            
            c1.r1 =c1.stmt1.executeQuery("select * from product_details where cust_id='"+id+"'");
            
            while(c1.r1.next())
            { 
                ArrayList<String>l2=new ArrayList();
                int r=c1.r1.getInt(1);
                String s=h1.get(r);
                l2.add(c1.r1.getString(6));
                l2.add(toString);
                l2.add(s);
                l2.add(String.valueOf(c1.r1.getInt(3)));
                
                l2.add(String.valueOf(c1.r1.getInt(4)));
                
                l2.add(String.valueOf(c1.r1.getInt(5)));
                
                l.add(l2);
            }
            
             
             
            
        }catch(Exception e){
        
        JOptionPane.showMessageDialog(null,e);
        }
        
        return l;
      //To change body of generated methods, choose Tools | Templates.
    }

    private String getProductname(int idd) throws SQLException {
        c1.r1 =c1.stmt1.executeQuery("select * from product where product_id='"+idd+"'");
        String r="";
                while(c1.r1.next())
               {
                 r=c1.r1.getString(2);
               }    
                return r;

    }

    private HashMap<Integer, String> getProductInfo() {
       HashMap<Integer, String> h2=new HashMap();
       try{
       c1.rs=c1.stmt.executeQuery("select * from product");
       while(c1.rs.next())
       {
           h2.put(c1.rs.getInt(1),c1.rs.getString(2));
           
       }}catch(Exception e){}
       return  h2;
    }

    private HashMap<Integer, String> getCustomerInfo() {
        
        HashMap<Integer, String> h2=new HashMap();
       try{
       c1.rs=c1.stmt.executeQuery("select * from customer");
       while(c1.rs.next())
       {
           h2.put(c1.rs.getInt(1),c1.rs.getString(2));
           
       }}catch(Exception e){}
       return  h2;

    }

    public ArrayList<ArrayList<String>> getDateWiseInfo(String s) throws SQLException {
        
        ArrayList<ArrayList<String>> l=new ArrayList();
        try{
        s=s.replace(",", "");
        String p[]=s.split(" ");
        Integer a=h5.get(p[0]);
        String month=String.valueOf(a);
        if(a<10)
        {
            month="0"+month;
        }
        String day=String.valueOf(p[1]);
        if(day.length()<2)
        {
            day="0"+day;
        }
        String date=p[2]+"-"+month+"-"+day;
        System.out.println(date);
         c1.rs=c1.stmt.executeQuery("select * from  product_details");
         while(c1.rs.next())
         { 
             String r=date.replace("-","");
             String j=c1.rs.getString(6).replace("-","");
             j.replace(",",""); 
             if(r.equals(j))
             {
             ArrayList l2=new ArrayList();
             l2.add(date); 
             l2.add(h3.get(c1.rs.getInt(2)));
             l2.add(h1.get(c1.rs.getInt(1)));
             l2.add(c1.rs.getInt(3));
             l2.add(c1.rs.getInt(4));
             l2.add(c1.rs.getInt(5));
             
             l.add(l2);
             }
         }
         
        }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
        }
        if(l.size()==0)JOptionPane.showMessageDialog(null,"Record not present....");
        
        return l;//To change body of generated methods, choose Tools | Templates.
    }

    private HashMap<String, Integer> getDateInfo() {
 
         h5.put("Jan",1);
         h5.put("Feb",2);
         h5.put("Mar",3);
         h5.put("Apr",4);
         h5.put("May",5);
         h5.put("Jun",6);
         h5.put("Jul",7);
         h5.put("Aug",8);
         h5.put("Sep",9);
         h5.put("Oct",10);
         h5.put("Nov",11);
         h5.put("Dec",12);
         
         
        //To change body of generated methods, choose Tools | Templates.
        return h5;
    }

    public ArrayList<ArrayList<String>> getDateWiseInfo1(String a) {
         
          ArrayList<ArrayList<String>> s1=new ArrayList();
          try{
          String  p="[0-9]{4}-"+a+"-[0-9]{2}";
          Pattern r;
        r = Pattern.compile(p);
        
         c1.rs=c1.stmt.executeQuery("select * from  product_details");
         while(c1.rs.next())
         { 
             Matcher m;
              m = r.matcher(c1.rs.getString(6));
              
             if(m.matches())
             {
             ArrayList l2=new ArrayList();
             l2.add(c1.rs.getString(6)); 
             l2.add(h3.get(c1.rs.getInt(2)));
             l2.add(h1.get(c1.rs.getInt(1)));
             l2.add(c1.rs.getInt(3));
             l2.add(c1.rs.getInt(4));
             l2.add(c1.rs.getInt(5));
             
             s1.add(l2);
             }
         }
          }catch(Exception e){}
          if(s1.isEmpty()){ 
              JOptionPane.showMessageDialog(null,"Record not present....");
          }
          
          return s1;
     }

    public ArrayList<ArrayList<String>> getDateWiseInfo2(String b) {
         ArrayList<ArrayList<String>> s1=new ArrayList();
          try{
          String  p=b+"-[0-9]{2}-[0-9]{2}";
          Pattern r;
        r = Pattern.compile(p);
        
         c1.rs=c1.stmt.executeQuery("select * from  product_details");
         while(c1.rs.next())
         { 
             Matcher m;
              m = r.matcher(c1.rs.getString(6));
              
             if(m.matches())
             {
             ArrayList l2=new ArrayList();
             l2.add(c1.rs.getString(6)); 
             l2.add(h3.get(c1.rs.getInt(2)));
             l2.add(h1.get(c1.rs.getInt(1)));
             l2.add(c1.rs.getInt(3));
             l2.add(c1.rs.getInt(4));
             l2.add(c1.rs.getInt(5));
             
             s1.add(l2);
             }
         }
          }catch(Exception e){}
             return s1;
    }

    public boolean isinsertBill(String text, String text0, String text1, String text2, String text3, String text4) {
    try{
        int a=Integer.parseInt(text);
        c1.rs=c1.stmt.executeQuery("select cust_id from customer where name='"+text0+"'");
        int cust_id=-1;
        while(c1.rs.next())
        {
            cust_id=c1.rs.getInt(1);
        }
        c1.ps=c1.con.prepareStatement("insert into bill values(?,?,?,?,?,?)");
        c1.ps.setInt(1,a);
        c1.ps.setInt(2,cust_id);
        c1.ps.setInt(3,Integer.parseInt(text1));
        c1.ps.setInt(4,Integer.parseInt(text2));
        c1.ps.setInt(5,Integer.parseInt(text3));
        c1.ps.setString(6, text4);
         
        c1.ps.executeUpdate();
         
        c1.ps=c1.con.prepareStatement("update customer set  paid_price='"+text2+"',remaing_price='"+text3+"' where name='"+text0+"'");
        c1.ps.executeUpdate();
        return true;
       }catch(Exception e){}
         return false;         



    }

    public ArrayList<ArrayList<String>> getproductdetails2(String r) {
          ArrayList<ArrayList<String>> l=new ArrayList();
        try
        {
            int id=0;
            c1.rs =c1.stmt.executeQuery("select * from customer where name='"+r+"'");
            while(c1.rs.next())
            {
                id=c1.rs.getInt(1);
            } 
             
            
            c1.r1 =c1.stmt1.executeQuery("select date, billno,customer.name,customer.phoneno,Total_price,bill.paid_price,bill.remaing_price from bill inner join customer on bill.cust_id=customer.cust_id  where customer.cust_id='"+id+"'");
            
            while(c1.r1.next())
            { 
                ArrayList<String>l2=new ArrayList();
                l2.add(c1.r1.getString(1));
                l2.add(String.valueOf(c1.r1.getInt(2)));
                l2.add(c1.r1.getString(3));
                l2.add(c1.r1.getString(4));
                l2.add(String.valueOf(c1.r1.getInt(5)));
                l2.add(String.valueOf(c1.r1.getInt(6)));
                l2.add(String.valueOf(c1.r1.getInt(7)));
                l.add(l2);
            }
            
             
             
            
        }catch(Exception e){
        
        JOptionPane.showMessageDialog(null,e);
        }
        
        return l;
    
    
    }

    public ArrayList<ArrayList<String>> getBillDateWiseInfo2(String b) {
    
          ArrayList<ArrayList<String>> s1=new ArrayList();
          try{
          String  p=b+"-[0-9]{2}-[0-9]{2}";
          Pattern r;
           r = Pattern.compile(p);
           c1.r1 =c1.stmt1.executeQuery("select date, billno,customer.name,customer.phoneno,Total_price,bill.paid_price,bill.remaing_price from bill inner join customer on bill.cust_id=customer.cust_id");
         while(c1.r1.next())
         { 
             Matcher m;
              m = r.matcher(c1.r1.getString(1));
              
             if(m.matches())
             {
             ArrayList l2=new ArrayList();
             l2.add(c1.r1.getString(1));
                l2.add(String.valueOf(c1.r1.getInt(2)));
                l2.add(c1.r1.getString(3));
                l2.add(c1.r1.getString(4));
                l2.add(String.valueOf(c1.r1.getInt(5)));
                l2.add(String.valueOf(c1.r1.getInt(6)));
                l2.add(String.valueOf(c1.r1.getInt(7)));
               
             
             s1.add(l2);
             }
         }
          }catch(Exception e){}
             return s1;
    }

   

    public ArrayList<ArrayList<String>> getBillDateinfo(String s) {
    ArrayList<ArrayList<String>> l=new ArrayList();
        try{
        s=s.replace(",", "");
        String p[]=s.split(" ");
        Integer a=h5.get(p[0]);
        String month=String.valueOf(a);
        if(a<10)
        {
            month="0"+month;
        }
        String day=String.valueOf(p[1]);
        if(day.length()<2)
        {
            day="0"+day;
        }
        String date=p[2]+"-"+month+"-"+day;
        System.out.println(date);
         c1.rs=c1.stmt.executeQuery("select date, billno,customer.name,customer.phoneno,Total_price,bill.paid_price,bill.remaing_price from bill inner join customer on bill.cust_id=customer.cust_id");
         while(c1.rs.next())
         { 
             String r=date.replace("-","");
             String j=c1.rs.getString(6).replace("-","");
             j.replace(",",""); 
             if(r.equals(j))
             {
             ArrayList l2=new ArrayList();
             l2.add(c1.r1.getString(1));
                l2.add(String.valueOf(c1.r1.getInt(2)));
                l2.add(c1.r1.getString(3));
                l2.add(c1.r1.getString(4));
                l2.add(String.valueOf(c1.r1.getInt(5)));
                l2.add(String.valueOf(c1.r1.getInt(6)));
                l2.add(String.valueOf(c1.r1.getInt(7)));
               
              
             l.add(l2);
             }
         }
         
        }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
        }
        if(l.size()==0)JOptionPane.showMessageDialog(null,"Record not present....");
        
        return l;//To change body of generated methods, choose Tools | Templates.     
    
    
    }

    public ArrayList<ArrayList<String>> getBill_details1() {
         
    ArrayList<ArrayList<String>> s1=new ArrayList();
          try{
           
           c1.r1 =c1.stmt1.executeQuery("select date, billno,customer.name,customer.phoneno,Total_price,bill.paid_price,bill.remaing_price from bill inner join customer on bill.cust_id=customer.cust_id");
         while(c1.r1.next())
         { 
              
             ArrayList l2=new ArrayList();
             l2.add(c1.r1.getString(1));
                l2.add(String.valueOf(c1.r1.getInt(2)));
                l2.add(c1.r1.getString(3));
                l2.add(c1.r1.getString(4));
                l2.add(String.valueOf(c1.r1.getInt(5)));
                l2.add(String.valueOf(c1.r1.getInt(6)));
                l2.add(String.valueOf(c1.r1.getInt(7)));
               
             
             s1.add(l2);
             
         }
          }catch(Exception e){}
             return s1;
       
    }

    public ArrayList<ArrayList<String>> getBillDateinfo2(String s) { //It is used access bill through specfic day.
     ArrayList<ArrayList<String>> l=new ArrayList();
        try{
        s=s.replace(",", "");
        String p[]=s.split(" ");
        Integer a=h5.get(p[0]);
        String month=String.valueOf(a);
        if(a<10)
        {
            month="0"+month;
        }
        String day=String.valueOf(p[1]);
        if(day.length()<2)
        {
            day="0"+day;
        }
        String date=p[2]+"-"+month+"-"+day;
        System.out.println(date);
         c1.rs=c1.stmt.executeQuery("select date, billno,customer.name,customer.phoneno,Total_price,bill.paid_price,bill.remaing_price from bill inner join customer on bill.cust_id=customer.cust_id");
         while(c1.rs.next())
         { 
             String r=date.replace("-","");
             String j=c1.rs.getString(1).replace("-","");
             j.replace(",",""); 
             if(r.equals(j))
             {
              ArrayList l2=new ArrayList();
             l2.add(c1.rs.getString(1));
                l2.add(String.valueOf(c1.rs.getInt(2)));
                l2.add(c1.rs.getString(3));
                l2.add(c1.rs.getString(4));
                l2.add(String.valueOf(c1.rs.getInt(5)));
                l2.add(String.valueOf(c1.rs.getInt(6)));
                l2.add(String.valueOf(c1.rs.getInt(7)));
               
             
             l.add(l2);
             
             }
         }
         
        }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
        }
        if(l.size()==0)JOptionPane.showMessageDialog(null,"Record not present....");
        
        return l;//To change body of generated methods, choose Tools | Templates.     
    
    
    }

    public int updateProduct_record(String p, String q) {
     int r=0;
        try
    {
        
        c1.rs=c1.stmt.executeQuery("select * from product where product_name='"+p+"'");
        while(c1.rs.next())
        {
            r=c1.rs.getInt(3);
        }
        r=(r+Integer.parseInt(q));
        c1.ps=c1.con.prepareStatement("update product set Qty='"+r+"' where product_name='"+p+"'");
        JOptionPane.showMessageDialog(null,r);
        
    }catch(Exception e){
     JOptionPane.showMessageDialog(null,e);
    }
    return r; 
    }
  

    

     
    
}
