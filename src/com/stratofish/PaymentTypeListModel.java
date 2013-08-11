package com.stratofish;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

class PaymentTypeListModel extends AbstractListModel<String>
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<PaymentType> PaymentType_list = new ArrayList<PaymentType>();

  public PaymentTypeListModel(List<PaymentType> paymentTypes)
  {
    for (int i = 0 ; i < paymentTypes.size(); i++)
    {
      PaymentType_list.add(paymentTypes.get(i));
    }
  }

  @Override
  public int getSize()
  {
    return PaymentType_list.size();
  }

  @Override
  public String getElementAt(int index)
  {
    return PaymentType_list.get(index).name;
  }

}
