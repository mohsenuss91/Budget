package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Calendar;

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

  PaymentView()
  {
    mainPanel = new JPanel();
    scrollPane = new JScrollPane();
    innerPanel = new JPanel(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();   
       
    gbc.gridx = 0;
    gbc.gridy = 0;

    Color bgColour = new Color(0.8f, 0.8f, 0.8f);
    
    Border paddingBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color( 0.9f, 0.9f, 0.9f), 1), BorderFactory.createEmptyBorder(10,10,10,10));
    //Border paddingBorder = BorderFactory.createDashedBorder(null);
    Border bevelBorder = BorderFactory.createBevelBorder(0);
   
    JLabel label = new JLabel("Amount");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(90, 20));
    label.setHorizontalAlignment(SwingConstants.CENTER);    
    label.setBorder(bevelBorder);    
    innerPanel.add(label, gbc);
    
    gbc.gridx = 1;      
    
    label = new JLabel("Desription");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(250, 20));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setBorder(bevelBorder);
    innerPanel.add(label, gbc);
    
    gbc.gridx = 2;      
    
    label = new JLabel("PaymentType");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(120, 20));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setBorder(bevelBorder);
    innerPanel.add(label, gbc);
    
    gbc.gridx = 3;      
    
    label = new JLabel("DateStamp");
    label.setOpaque(true);
    label.setBackground(bgColour);
    label.setPreferredSize(new Dimension(90, 20));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setBorder(bevelBorder);
    innerPanel.add(label, gbc);    
    
    bgColour = new Color(1.0f, 1.0f, 1.0f);
    
    // Loop per payment    
    
    for (int i = 1; i < 11; i++)
    {          
      gbc.gridx = 0;
      gbc.gridy = i;                 
      
      label = new JLabel(String.format("%,10.2f", (-1600.02f * (i + 1))));
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(90, 20));
      label.setHorizontalAlignment(SwingConstants.RIGHT);
      label.setBorder(paddingBorder);  
      innerPanel.add(label, gbc);
      
      gbc.gridx = 1;      
      
      label = new JLabel("Desription " + (i + 1));
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(250, 20));
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);
      
      gbc.gridx = 2;      
      
      label = new JLabel("PaymentType " + (i + 1));
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(120, 20));
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);
      
      gbc.gridx = 3;      
      
      label = new JLabel("01/02/2013");
      label.setOpaque(true);
      label.setBackground(bgColour);
      label.setPreferredSize(new Dimension(90, 20));
      label.setBorder(paddingBorder);
      innerPanel.add(label, gbc);
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
