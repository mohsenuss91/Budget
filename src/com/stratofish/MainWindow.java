package com.stratofish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener, ItemListener
{
  private static final long serialVersionUID = 1L;
  private JMenuBar menuBar;

  MainWindow()
	{
  	JLabel jlbHelloWorld = new JLabel("Hello World");
  	add(jlbHelloWorld);
  	setSize(1024, 768);
  	
  	AddMenu();
  	
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
    JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
    newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    newMenuItem.addActionListener(this);
    fileMenu.add(newMenuItem);
    
    JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
    openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    openMenuItem.addActionListener(this);
    fileMenu.add(openMenuItem);
    
    JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
    saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    saveMenuItem.addActionListener(this);
    fileMenu.add(saveMenuItem);
    
    fileMenu.addSeparator();
    
    JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
    exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    exitMenuItem.addActionListener(this);
    fileMenu.add(exitMenuItem);
    
    setJMenuBar(menuBar);
  }

  public void Run()
  {
    
  }

  @Override
  public void itemStateChanged(ItemEvent arg0)
  {
    
  }

  @Override
  public void actionPerformed(ActionEvent arg0)
  {
    System.out.println("New clicked");
  }
}
