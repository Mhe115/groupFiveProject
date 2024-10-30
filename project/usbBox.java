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
            usbDialog.setTitle("System Information"); // Set window title
            usbDialog.setSize(400, 36); // Set window size
            usbDialog.setLocationRelativeTo(null); // Center the window on the screen
            usbDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Exits application when window is closed
    
            usbDialog.setVisible(true);
            
    }
    }
    