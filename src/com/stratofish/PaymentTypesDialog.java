package com.stratofish;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PaymentTypesDialog extends JDialog implements ActionListener
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PaymentTypesDialog(JFrame parent, BudgetController bCon)
  {
    super(parent, "Payment types", true);

    if (parent != null) {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
    }
    
    //setLocationRelativeTo(null);
    setSize(400, 500);
    
    JPanel dialogPane = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
    JScrollPane sp = new JScrollPane();
    //JList list = new JList();
    JList<String> list = new JList<String>();
    list.setModel(new PaymentTypeListModel(bCon.GetPaymentTypes()));
    
    JLabel label = new JLabel("test");
    list.add(label);
    
    getContentPane().add(sp);
    sp.getViewport().add(list);
    
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("OK"); 
    buttonPane.add(button); 
    button.addActionListener(this);
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    //pack(); 
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    setVisible(false); 
    dispose(); 
  }
}