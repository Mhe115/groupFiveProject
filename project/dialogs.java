//classes for buttons to call methods to display dialog boxes 
public class pciBox(){


JDialog pciDialog = new JDialog();

public pciBox(){
initiliasePci();


public void initiliasePci(){
        pciDialog.setBackground(Color.WHITE); 
        pciDialog.setTitle("System Information"); // Set window title
        pciDialog.setSize(400, 36); // Set window size
        pciDialog.setLocationRelativeTo(null); // Center the window on the screen
        pciDialog.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed

        setVisible(true);
        
}
}
}


public class cpuBox(){


public cpuBox(){
    JDialog cpuDialog = new JDialog();

initiliaseCpu();


public void initiliaseCpu(){
        cpuDialog.setBackground(Color.WHITE); 
        cpuDialog.setTitle("System Information"); // Set window title
        cpuDialog.setSize(400, 36); // Set window size
        cpuDialog.setLocationRelativeTo(null); // Center the window on the screen
        cpuDialog.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed

        setVisible(true);
        
}
}
}

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

public class memBox(){

public memBox(){
    JDialog memDialog = new JDialog();

    initiliaseMem();
    
    
    public void initiliaseMem(){
            memDialog.setBackground(Color.WHITE); 
            memDialog.setTitle("System Information"); // Set window title
            memDialog.setSize(400, 36); // Set window size
            memDialog.setLocationRelativeTo(null); // Center the window on the screen
            memDialog.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed

            setVisible(true);
            
}
}
}

