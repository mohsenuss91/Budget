package com.stratofish;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
  protected BudgetController bCon;
  
  CalendarView(BudgetController p_bCon) throws SQLException
  {
    year = 2013;
    month = Calendar.JULY;
    day = 14;
    
    mainPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
    scrollPane = new JScrollPane();
    innerPanel = new JPanel(new GridBagLayout());
    
    bCon = p_bCon;
    
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
    
    List<PaymentType> columns = bCon.GetPaymentTypes();
    
    // Headers
    gbc.gridy = 0;
    JLabel label = new JLabel("Date", SwingConstants.LEFT);
    gbc.gridx = 0;
    
    label.setPreferredSize(new Dimension(75, 20));
    Font font = label.getFont();
    Font headerFont = font.deriveFont(15.0f);
    int f = label.getWidth();
    label.setFont(headerFont);

    innerPanel.add(label, gbc);
    
    // Columns
    int column = 1;
    if (columns != null)
    {
      for (PaymentType paymentType : columns)
      {
        label = new JLabel(paymentType.name);
        label.setPreferredSize(new Dimension(75, 20));
        label.setFont(headerFont);
        gbc.gridx = column;
        innerPanel.add(label, gbc);
        column++;
      }
    }
    
    label = new JLabel("Balance");
    label.setPreferredSize(new Dimension(75, 20));
    label.setFont(headerFont);
    gbc.gridx = 10000;
    innerPanel.add(label, gbc);
    
    float cash = -1600;

    // Loop per day
    for (int i = 0; i < days_in_month; i++)
    {
      gbc.gridy = i+1;
      date.set(year, month, i+1);

      label = new JLabel((1+i)+getDayOfMonthSuffix(i+1)+" ("+date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale)+")", SwingConstants.LEFT);
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
      column = 1;
      if (columns != null)
      {
        for (PaymentType paymentType : columns)
        {
          label = new JLabel("");
          label.setOpaque(true);
          label.setBackground(bgColour);
          label.setPreferredSize(new Dimension(75, 20));
          gbc.gridx = column;
          innerPanel.add(label, gbc);
          
          column++;
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

    mainPanel.add(scrollPane);
    mainPanel.validate();
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
