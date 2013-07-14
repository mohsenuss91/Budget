package com.stratofish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BudgetController
{
  protected BudgetFile budgetFile = null;
  
  public BudgetController()
  {
  }
  
  public void Open(String name)
  {
    if (budgetFile == null)
    {
      budgetFile = new BudgetFile();
    }
    
    budgetFile.Open(name);
  }
  
  public Connection GetConnection()
  {
    if (budgetFile != null)
      return BudgetFile.Instance();
    
    return null;
  }
  
  public ArrayList<PaymentType> GetPaymentTypes()
  {
    Connection conn = GetConnection();
    
    if (conn == null)
    {
      return null;
    }
    
    ArrayList<PaymentType> list = new ArrayList<PaymentType>();
    
    try
    {
      Statement statement = conn.createStatement();
      ResultSet rs;
      rs = statement.executeQuery("SELECT * FROM paymentTypes");
      
      while (rs.next())
      {
        PaymentType pt = new PaymentType();
        pt.id = rs.getInt(1);
        pt.name = rs.getString(2);
        list.add(pt);
      }
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    return list;
  }
}
