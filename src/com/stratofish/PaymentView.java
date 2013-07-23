package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class PaymentView
{
  protected JPanel mainPanel;
  protected JPanel innerPanel;
  protected JScrollPane scrollPane;  
  protected BudgetController bCon;

  PaymentView(BudgetController p_bCon) throws SQLException
  {
    bCon = p_bCon;
    
    mainPanel = new JPanel();
    scrollPane = new JScrollPane();
    innerPanel = new JPanel(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();   

    List<SinglePayment> columns = bCon.GetSinglePayments();
    
    gbc.gridx = 0;
    gbc.gridy = 0;

    Color bgColour = new Color(0.8f, 0.8f, 0.8f);
    
    Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
    Border bevelBorder = BorderFactory.createBevelBorder(0);
    
    bgColour = new Color(1.0f, 1.0f, 1.0f);
    
    // Loop per payment
    if (columns != null)
    {           
      for (SinglePayment singlePayment : columns)
      {
        JLabel label = new JLabel(Integer.toString(singlePayment.dateStamp.get( Calendar.YEAR)));
        label.setOpaque(true);
        label.setBackground(bgColour);
        label.setPreferredSize(new Dimension(90, 20));
        label.setBorder(paddingBorder);
        innerPanel.add(label, gbc);
        gbc.gridy++;      
      }
    }
      
    for (int i = 10; i < 11; i++)
    {          
      gbc.gridy = i;      
      
      gbc.gridx = 0;
      JLabel label = new JLabel("01/02/2013");
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(90, 20));
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);
      
      gbc.gridx = 1;            
      label = new JLabel("Desription " + (i + 1));
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(125, 20));
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);

      gbc.gridx = 2;
      label = new JLabel("£");
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(30, 20));
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);
      
      gbc.gridx = 3;
      label = new JLabel(String.format("%,10.2f", (-1600.02f * (i + 1))));
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(90, 20));
      label.setHorizontalAlignment(SwingConstants.RIGHT);
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);
      
      gbc.gridx = 4;
      JCheckBox checkBox = new JCheckBox();
      innerPanel.add(checkBox, gbc);
    }
    
    scrollPane.getViewport().add(innerPanel);
    mainPanel.setPreferredSize(new Dimension(400, 700));
    mainPanel.add(scrollPane);
  }
  
  JPanel GetPanel()
  {
    return mainPanel;
  }
}
