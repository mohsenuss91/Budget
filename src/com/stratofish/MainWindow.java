package com.stratofish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener, ItemListener
{
  protected static final long serialVersionUID = 1L;
  
  protected JMenuBar menuBar;
  protected JMenuItem newMenuItem;
  protected JMenuItem openMenuItem;
  protected JMenuItem saveMenuItem;
  protected JMenuItem exitMenuItem;

  protected BudgetFile budgetFile = null;
  protected Connection conn = null;

  MainWindow()
	{
  	CreateWindowComponents();
  	
  	//CalendarView calendarView = new CalendarView();
  	//add(calendarView.GetPanel());
	}
  
  private void CreateWindowComponents()
  {
    AddMenu();
    
    JSplitPane sp = new JSplitPane();
    
    add(sp);
    
    CalendarView calendarView = new CalendarView();
    JPanel panel1 = calendarView.GetPanel();
    sp.setLeftComponent(panel1);
    JPanel panel2 = new JPanel();
    sp.setRightComponent(panel2);
    
    setSize(1024, 768);
    
    setVisible(true);
  }

  private void AddMenu()
  {
    // Main bar
    menuBar = new JMenuBar();
    
    // Menus
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(fileMenu);

    // Items
    newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
    newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    newMenuItem.addActionListener(this);
    fileMenu.add(newMenuItem);
    
    openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
    openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    openMenuItem.addActionListener(this);
    fileMenu.add(openMenuItem);
    
    saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
    saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    saveMenuItem.addActionListener(this);
    fileMenu.add(saveMenuItem);
    
    fileMenu.addSeparator();
    
    exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
    exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    exitMenuItem.addActionListener(this);
    fileMenu.add(exitMenuItem);
    
    setJMenuBar(menuBar);
  }

  public void Run()
  {
    
  }

  protected void New()
  {
    System.out.println("New");
    
    try
    {
      budgetFile = new BudgetFile();
    } catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    
    budgetFile.New("newbudget");
    
    conn = BudgetFile.Instance();
  }
  
  protected void Open()
  {
    System.out.println("Open");
  }
  
  protected void Save()
  {
    System.out.println("Save");
  }
  
  protected void Exit()
  {
    System.out.println("Exit");
  }
  
  @Override
  public void itemStateChanged(ItemEvent arg0)
  {
    
  }

  @Override
  public void actionPerformed(ActionEvent arg0)
  {
    JMenuItem source = (JMenuItem) arg0.getSource();
    
    if (source == newMenuItem)
    {
      New();
    }
    if (source == openMenuItem)
    {
      Open();
    }
    if (source == saveMenuItem)
    {
      Save();
    }
    if (source == exitMenuItem)
    {
      Exit();
    }
  }
}
