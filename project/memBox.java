import java.awt.*;
import javax.swing.*;
//classes for buttons to call methods to display dialog boxes 

public class memBox{

    JDialog memDialog = new JDialog();

    public memBox(){
        initiliaseMem();
        }
        
        public void initiliaseMem(){
                memDialog.setBackground(Color.WHITE); 
                memDialog.setTitle("Memory"); // Set window title
                memDialog.setSize(400, 200); // Set window size
                memDialog.setLocationRelativeTo(null); // Center the window on the screen

                memDialog.setVisible(true);
                
    }
    }