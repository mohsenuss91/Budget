package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CalendarView
{
  protected JPanel mainPanel;
  protected JPanel innerPanel;
  protected JScrollPane scrollPane;
  
  CalendarView()
  {
    mainPanel = new JPanel();
    scrollPane = new JScrollPane();
    innerPanel = new JPanel(new GridLayout(2, 3));
    
    JLabel l = new JLabel("JLabel 1");
    l.setSize(new Dimension(50, 20));
    innerPanel.add(l);
    innerPanel.add(new JLabel("JLabel 2"));
    innerPanel.add(new JLabel("JLabel 3"));
    innerPanel.add(new JLabel("JLabel 4"));
    innerPanel.add(new JLabel("JLabel 5"));
    innerPanel.add(new JLabel("JLabel 6"));
    
    scrollPane.getViewport().add(innerPanel);
    scrollPane.setPreferredSize(new Dimension(500, 200));
    mainPanel.add(scrollPane);
  }
  
  JPanel GetPanel()
  {
    return mainPanel;
  }
}
