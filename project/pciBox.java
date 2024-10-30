import java.awt.*;
import javax.swing.*;
//classes for buttons to call methods to display dialog boxes 


public class pciBox{


    private final JDialog pciDialog = new JDialog();
    
    public pciBox(){
    initiliasePci();
    }
    
    public void initiliasePci(){
            pciDialog.setBackground(Color.WHITE); 
            pciDialog.setTitle("PCI"); // Set window title
            pciDialog.setSize(400, 200); // Set window size
            pciDialog.setLocationRelativeTo(null); // Center the window on the screen
          
            pciDialog.setVisible(true);
            
    }
    }
    
    