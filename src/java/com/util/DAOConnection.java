package com.util;

import java.sql.*;

public  class DAOConnection {
static Connection con;
static String username = "root";
static String password = null;

 
   public static Connection sqlconnection(){
       try{
           String dbUrl = "jdbc:mysql://localhost:3306/virtualstore_db";
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           con=DriverManager.getConnection(dbUrl,"root","");              
           System.out.println("Conexão OK");
       }catch(Exception excessao){
           System.out.println("Erro=" + excessao);
       }
       return con;    
    
}    
    
}
