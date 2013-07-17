package com.stratofish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class PaymentTypeModel
{
  public PaymentTypeModel()
  {
    
  }
  
  public List<PaymentType> GetCurrentPaymentTypes(Connection conn)
  {
    if (conn == null)
    {
      return null;
    }

    List<PaymentType> list = new ArrayList<PaymentType>();
    
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