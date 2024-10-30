
//classes for buttons to call methods to display dialog boxes 


public class usbBox(){

    public usbBox(){
        JDialog usbDialog = new JDialog();
    
    initiliaseUsb();
    
    
    public void initiliaseUsb(){
            usbDialog.setBackground(Color.WHITE); 
            usbDialog.setTitle("System Information"); // Set window title
            usbDialog.setSize(400, 36); // Set window size
            usbDialog.setLocationRelativeTo(null); // Center the window on the screen
            usbDialog.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed
    
            setVisible(true);
            
    }
    }
    }