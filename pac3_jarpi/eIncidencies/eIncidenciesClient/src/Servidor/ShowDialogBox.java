package Servidor;

import javax.swing.*;

public class ShowDialogBox{
  JFrame frame;
  
	  public ShowDialogBox(String message){
		  JOptionPane.showMessageDialog(frame,message.toString());
	  }
}