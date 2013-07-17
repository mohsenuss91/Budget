package com.stratofish;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class SinglePaymentModel
{
  public SinglePaymentModel()
  {
    
  }
  
  @SuppressWarnings("deprecation")
  public List<SinglePayment> GetCurrentSinglePayments(Connection conn)
  {
    if (conn == null)
    {
      return null;
    }

    List<SinglePayment> list = new ArrayList<SinglePayment>();
    
    try
    {
      Statement statement = conn.createStatement();
      ResultSet rs;
      rs = statement.executeQuery("SELECT * FROM singlePayments");
      
      while (rs.next())
      {
        SinglePayment sp = new SinglePayment();
        sp.id = rs.getInt("id");
        sp.amount = rs.getFloat("amount");
        sp.description = rs.getString("description");
        sp.paymentTypeId = rs.getInt("paymentTypeId");
        sp.verified = rs.getInt("verified");
        
        int dateStamp = rs.getInt("dateStamp");               
        int year = (int)Math.floor((double)dateStamp / 10000.0f);
        dateStamp -= (year * 10000);
        
        int month = (int)Math.floor((double)(dateStamp - (year * 10000)) / 100.0f);
        dateStamp -= (month * 100);

        int day = dateStamp;

        sp.dateStamp = new Date(0);
        sp.dateStamp.setYear(year);
        sp.dateStamp.setMonth(month);
        sp.dateStamp.setDate(day);
        
        list.add(sp);
      }
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    return list;
  }
}