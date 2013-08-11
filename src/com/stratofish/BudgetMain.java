package com.stratofish;

public class BudgetMain
{
  protected MainWindow window;
 
  public static void main(String[] args) throws ClassNotFoundException
  {
    /*ResultSet rs = statement.executeQuery("select * from person");
    while(rs.next())
    {
      // read the result set
      System.out.println("name = " + rs.getString("name"));
      System.out.println("id = " + rs.getInt("id"));
    }*/
	  
    new BudgetMain();
  }
  
  BudgetMain()
  {
    window = new MainWindow();
    window.Run();
  }
}