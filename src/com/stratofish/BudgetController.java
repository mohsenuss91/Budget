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
  
  public List<PaymentType> GetPaymentTypes()
  {
    Connection conn = GetConnection();
    
    if (conn == null)
    {
      return null;
    }
    
    PaymentTypeModel ptm = new PaymentTypeModel();
    
    List<PaymentType> list = ptm.GetCurrentPaymentTypes(conn);
    
    return list;
  }
  
  public List<SinglePayment> GetSinglePayments()
  {
    Connection conn = GetConnection();
    
    if (conn == null)
    {
      return null;
    }
    
    SinglePaymentModel spm = new SinglePaymentModel();
    
    List<SinglePayment> list = spm.GetCurrentSinglePayments(conn);
    
    return list;
  }  
}
