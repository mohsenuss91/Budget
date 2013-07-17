package com.stratofish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener, ItemListener
{
  protected static final long serialVersionUID = 1L;
  
  protected JMenuBar menuBar;
  protected JMenuItem newMenuItem;
  protected JMenuItem openMenuItem;
  protected JMenuItem saveMenuItem;
  protected JMenuItem exitMenuItem;
  protected JMenuItem paymentTypesMenuItem;

  protected BudgetFile budgetFile = null;
  protected Connection conn = null;
  
  protected BudgetController bCon = null;
  protected CalendarView calendarView = null;

  MainWindow()
	{
    bCon = new BudgetController();
    
  	CreateWindowComponents();
  	
  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
  
  private void CreateWindowComponents()
  {
    AddMenus();
    
    JSplitPane sp = new JSplitPane();
    sp.setDividerLocation(425);
    add(sp);
    
    try
    {
      calendarView = new CalendarView(bCon);
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    JPanel panel1 = calendarView.GetPanel();
    sp.setLeftComponent(panel1);
    
    PaymentView paymentView = new PaymentView();
    JPanel panel2 = paymentView.GetPanel();
    sp.setRightComponent(panel2);
    
    setSize(1024, 768);
    setLocationRelativeTo(null);

    setVisible(true);
  }

  private void AddMenus()
  {
    // Main bar
    menuBar = new JMenuBar();
    
    // Menus
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(fileMenu);
    
    JMenu paymentsMenu = new JMenu("Payments");
    paymentsMenu.setMnemonic(KeyEvent.VK_P);
    menuBar.add(paymentsMenu);

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
    
    paymentTypesMenuItem = new JMenuItem("Payment types", KeyEvent.VK_P);
    paymentTypesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
    paymentTypesMenuItem.addActionListener(this);
    paymentsMenu.add(paymentTypesMenuItem);
    
    setJMenuBar(menuBar);
  }

  public void Run()
  {
    
  }

  protected void New()
  {
    System.out.println("New");
    
    budgetFile = new BudgetFile();
    
    budgetFile.New("newbudget");
    
    conn = BudgetFile.Instance();
  }
  
  protected void Open()
  {
    bCon.Open("newbudget");
    
    calendarView.AddContent();
    
    System.out.println("Open");
    
    repaint();
  }
  
  protected void Save()
  {
    System.out.println("Save");
  }
  
  protected void Exit()
  {
    System.out.println("Exit");
  }
  
  protected void PaymentTypes()
  {
    System.out.println("PaymentTypes");
    
    if (bCon == null)
    {
      System.out.println("No budget is loaded");
      return;
    }
    
    PaymentTypesDialog dlg = new PaymentTypesDialog(new JFrame(), bCon);
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
    if (source == paymentTypesMenuItem)
    {
      PaymentTypes();
    }
  }
}
