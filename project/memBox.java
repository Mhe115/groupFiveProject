
//classes for buttons to call methods to display dialog boxes 

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