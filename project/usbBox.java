import java.awt.*;
import javax.swing.*;
//classes for buttons to call methods to display dialog boxes 


public class usbBox{

    JDialog usbDialog = new JDialog();

    public usbBox(){
    initiliaseUsb();
    }
    
    public void initiliaseUsb(){
            usbDialog.setBackground(Color.WHITE); 
            usbDialog.setTitle("USB"); // Set window title
            usbDialog.setSize(400, 200); // Set window size
            usbDialog.setLocationRelativeTo(null); // Center the window on the screen
        
            usbDialog.setVisible(true);
            
    }
    }
    