package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
    
    Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
    Border bevelBorder = BorderFactory.createBevelBorder(0);
    
    Color bgColour = new Color(1.0f, 1.0f, 1.0f);          
    
    JLabel label = new JLabel("Date");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(90, 20));
    label.setBorder(paddingBorder);
    innerPanel.add(label, gbc);
    
    Font font = label.getFont();
    Font headerFont = font.deriveFont(15.0f);    
    label.setFont(headerFont);
    
    gbc.gridx++;
    
    label = new JLabel("Description");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(125, 20));
    label.setBorder(paddingBorder);
    innerPanel.add(label, gbc);
    
    font = label.getFont();
    headerFont = font.deriveFont(15.0f);    
    label.setFont(headerFont);

    gbc.gridx++;
    
    label = new JLabel("Amount");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(100, 20));
    label.setBorder(paddingBorder);
    innerPanel.add(label, gbc);
    
    font = label.getFont();
    headerFont = font.deriveFont(15.0f);    
    label.setFont(headerFont);        
    
    gbc.gridx++;
    
    label = new JLabel("Verified");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(100, 20));
    label.setBorder(paddingBorder);
    innerPanel.add(label, gbc);
    
    font = label.getFont();
    headerFont = font.deriveFont(15.0f);    
    label.setFont(headerFont);    
    
    gbc.gridy++;
    gbc.gridx = 0;
    
    // Loop per payment
    if (columns != null)
    {           
      for (SinglePayment singlePayment : columns)
      {
        String dateStamp = String.format("%02d", singlePayment.dateStamp.get( Calendar.DAY_OF_MONTH)) 
                         + "/"
                         + String.format("%02d", singlePayment.dateStamp.get( Calendar.MONTH))
                         + "/"
                         + String.format("%04d", singlePayment.dateStamp.get( Calendar.YEAR));
        
        label = new JLabel(dateStamp);
        label.setOpaque(true);
        label.setBackground(bgColour);
        label.setPreferredSize(new Dimension(90, 20));
        label.setBorder(paddingBorder);
        innerPanel.add(label, gbc);        
        
        gbc.gridx++;
        
        label = new JLabel(singlePayment.description);
        label.setOpaque(true);
        label.setBackground(bgColour);        
        label.setPreferredSize(new Dimension(125, 20));
        label.setBorder(paddingBorder);
        innerPanel.add(label, gbc);        

        gbc.gridx++;
        
        label = new JLabel(String.format("£%,.2f", singlePayment.amount));
        label.setOpaque(true);
        label.setBackground(bgColour);
        label.setPreferredSize(new Dimension(100, 20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBorder(paddingBorder);
        innerPanel.add(label, gbc);
        
        gbc.gridx++;
        
        JCheckBox checkBox = new JCheckBox();        
        checkBox.setHorizontalAlignment(SwingConstants.LEFT);
        innerPanel.add(checkBox, gbc);        
        
        gbc.gridy++;
        gbc.gridx = 0;
      }
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
