import java.awt.*;
import javax.swing.*;
//classes for buttons to call methods to display dialog boxes 


public class cpuBox{

    JDialog cpuDialog = new JDialog();

    public cpuBox(){
    initiliaseCpu();
    }
    
    public void initiliaseCpu(){
            cpuDialog.setBackground(Color.WHITE); 
            cpuDialog.setTitle("System Information"); // Set window title
            cpuDialog.setSize(400, 36); // Set window size
            cpuDialog.setLocationRelativeTo(null); // Center the window on the screen
            cpuDialog.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed
    
            setVisible(true);
            
    }
    }
    