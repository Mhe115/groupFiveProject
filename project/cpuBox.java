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
            cpuDialog.setTitle("CPU"); // Set window title
            cpuDialog.setSize(400, 200); // Set window size
            cpuDialog.setLocationRelativeTo(null); // Center the window on the screen
          
            cpuDialog.setVisible(true);
            
    }
    }
    