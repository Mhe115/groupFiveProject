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
            pciDialog.setTitle("System Information"); // Set window title
            pciDialog.setSize(400, 36); // Set window size
            pciDialog.setLocationRelativeTo(null); // Center the window on the screen
            pciDialog.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed
    
            setVisible(true);
            
    }
    }
    
    