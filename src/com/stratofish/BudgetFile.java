package com.stratofish;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BudgetFile
{
  protected static Connection connection = null;   
  
  public BudgetFile() throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");   
  }
  
  public static Connection Instance()
  {
    return connection;
  }
  
  public static void Close()
  {
    try
    {
      if(connection != null)
         connection.close();
      }
    catch(SQLException e)
    {
      // connection close failed.
      System.err.println(e);
    }
    
    connection = null;
  }
  
  public void New(String name) 
  {     
    try
    {
      Close();
      connection = DriverManager.getConnection("jdbc:sqlite:" + name + ".db");     
      
      Statement statement = connection.createStatement();
      
      System.out.println("Statement => " + statement);
            
      statement.setQueryTimeout(30);  // Set timeout to 30 sec.           
  
      statement.executeUpdate("drop table if exists singlePayments");
      statement.executeUpdate("create table singlePayments(id integer, amount real, description text, paymentTypeId integer, verified integer, dateStamp integer)");

      statement.executeUpdate("drop table if exists paymentTypes");
      statement.executeUpdate("create table paymentTypes(id integer, name text)");
    }
    catch(SQLException e)
    {
      // If the error message is "out of memory", 
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
  }
}
