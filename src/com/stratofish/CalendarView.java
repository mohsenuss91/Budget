package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class CalendarView
{
  protected JPanel mainPanel;
  protected JPanel innerPanel = null;
  protected JScrollPane scrollPane;
  
  protected int month;
  protected int year;
  protected int day;
  protected Calendar cal;
  
  CalendarView() throws SQLException
  {
    year = 2013;
    month = Calendar.JULY;
    day = 14;
    
    mainPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
    scrollPane = new JScrollPane();
    innerPanel = new JPanel(new GridBagLayout());
    
    
    
    AddContent();
    
    mainPanel.add(scrollPane);
  }
  
  public void AddContent()
  {
    if (innerPanel != null)
      scrollPane.remove(innerPanel);
    
    innerPanel = new JPanel(new GridBagLayout());
    
    cal = new GregorianCalendar(year, month, day);
    int days_in_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    
    GregorianCalendar date = new GregorianCalendar(year, month, 1);
    Locale locale = new Locale("ENGLISH", "United Kingdom");
    
    GridBagConstraints gbc = new GridBagConstraints();
    
    float cash = -1600;
    
    // Loop per day
    for (int i = 0; i < days_in_month; i++)
    {
      gbc.gridy = i;
      date.set(year, month, i+1);

      JLabel label = new JLabel((1+i)+getDayOfMonthSuffix(i+1)+" ("+date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale)+")", SwingConstants.LEFT);
      gbc.gridx = 0;
      
      Color bgColour = new Color(1.0f, 1.0f, 1.0f);
      int day_of_week = date.get(Calendar.DAY_OF_WEEK);
      if ((day_of_week == Calendar.SATURDAY) ||
          (day_of_week == Calendar.SUNDAY))
      {
        bgColour = new Color(0.8f, 0.8f, 0.8f);
      }
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(75, 20));

      innerPanel.add(label, gbc);
      
      // Columns
      Connection conn = BudgetFile.Instance();
      Statement statement = null;
      
      if (conn != null)
      {
        try
        {
          statement = conn.createStatement();
        } catch (SQLException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
        int column = 2;
        ResultSet rs = null;
        try
        {
          rs = statement.executeQuery("select * from paymentTypes");
        } catch (SQLException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        try
        {
          while(rs.next())
          {
            label = new JLabel(rs.getString(2));
            label.setOpaque(true);
            label.setBackground(bgColour);
            label.setPreferredSize(new Dimension(75, 20));
            gbc.gridx = column;
            innerPanel.add(label, gbc);
            column++;
          }
        } catch (SQLException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
      
      label = new JLabel(Float.toString(cash));
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(75, 20));
      gbc.gridx = 10000;
      innerPanel.add(label, gbc);
    }
    
    scrollPane.getViewport().add(innerPanel);

    scrollPane.setPreferredSize(new Dimension(700, 700));
    innerPanel.setPreferredSize(new Dimension(650, 650));
    mainPanel.add(scrollPane);
  }
  
  String getDayOfMonthSuffix(final int n) {
    if (n >= 11 && n <= 13) {
        return "th";
    }
    switch (n % 10) {
        case 1:  return "st";
        case 2:  return "nd";
        case 3:  return "rd";
        default: return "th";
    }
}
  
  JPanel GetPanel()
  {
    return mainPanel;
  }
}
