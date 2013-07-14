package com.stratofish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

class PaymentTypeListModel extends AbstractListModel{

  private List<PaymentType> PaymentType_list = new ArrayList();

  public PaymentTypeListModel(List<PaymentType> paymentTypes)
  {
    for (int i = 0 ; i < paymentTypes.size(); i++)
    {
      PaymentType_list.add(paymentTypes.get(i));
    }
  }

  @Override
  public int getSize() {
  return PaymentType_list.size();
  }

  @Override
  public Object getElementAt(int index) {
  return PaymentType_list.get(index).name;
  }

}