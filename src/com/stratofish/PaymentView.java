package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PaymentView
{
  protected JPanel mainPanel;
  protected JPanel innerPanel;
  protected JScrollPane scrollPane;

  PaymentView()
  {
    mainPanel = new JPanel();
    scrollPane = new JScrollPane();
    innerPanel = new JPanel(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();   
       
    // Loop per day
    for (int i = 0; i < 11; i++)
    {     
      gbc.gridx = 0;
      gbc.gridy = i;

      JLabel label = new JLabel("Payment " + (i + 1));
      label.setSize( 300, 300);
      innerPanel.add(label, gbc);
      
      gbc.gridx = 1;
      gbc.gridy = i;
      
      label = new JLabel("Amount " + (i + 1));
      innerPanel.add(label, gbc);
      
      gbc.gridx = 2;
      gbc.gridy = i;
      
      label = new JLabel("Desription " + (i + 1));
      innerPanel.add(label, gbc);
      
      gbc.gridx = 3;
      gbc.gridy = i;
      
      label = new JLabel("PaymentType " + (i + 1));
      innerPanel.add(label, gbc);
      
      gbc.gridx = 4;
      gbc.gridy = i;
      
      label = new JLabel("DateStamp " + (i + 1));
      innerPanel.add(label, gbc);
    }
    
    scrollPane.getViewport().add(innerPanel);
    scrollPane.setPreferredSize(new Dimension(800, 700));
    mainPanel.add(scrollPane);
  }
  
  JPanel GetPanel()
  {
    return mainPanel;
  }
}
