import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors




// Group Five Project - Conor Healy, Emmett Macken, Caitlin Amelia Moloney, Holly Best

//Imports for button functionality
import java.awt.event.ActionEvent; //for the event of a button click
import java.awt.event.ActionListener; //for waiting for a button click

//Imports for GUI
import javax.swing.JButton; //button for the GUI
import javax.swing.JFrame; //frame for the GUI
import javax.swing.JLabel; //label component for text display
import javax.swing.JPanel; //panel container for grouping components 
import javax.swing.SwingUtilities; //manages GUI updates

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.awt.Color;//for color of JFrame panel

//JFree Chart Stuff


//import javax.swing.UIManager;

//JFrame GUI
public class template extends JFrame{ //creates a JFrame window

    private JFrame frame;
    private JPanel panel;

//Making the main screen content on the GUI


    //Seting up the GUI
   public template() {
        // Set up the JFrame window
        setTitle("System Information"); // Set window title
        setSize(400, 200); // Set window size
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits application when window is closed
        
        // Create a new JFrame and JPanel
        frame = new JFrame("Refresh JFrame Example"); // Creates a new frame object
        panel = new JPanel(); // Creates a new panel for GUI components
        
        // Set a background color for the JPanel (e.g., light blue)
        panel.setBackground(Color.CYAN); // You can change Color.CYAN to any other color
        
        // Add the panel to the content pane of the frame
        getContentPane().add(panel);
        
        // Calls createScreenCont()
        createScreenCont();
        
        // Make sure the frame is visible
        setVisible(true);
    }


    //method to add components to the screen
    public void createScreenCont() { 


        
        panel.add(new JLabel("Select Data Type")); //user prompt to "Select Data Type"

        

        

        JButton cpubut = new JButton("CPU"); //new button for CPU
        panel.add(cpubut); //adds this CPU button to the panel

        cpubut.addActionListener(new cpuPick()); //checks for CPU button being pressed

        JButton pcibut = new JButton("PCI"); //new button for PCI
        panel.add(pcibut); //adds this PCI button to the panel

        pcibut.addActionListener(new pciPick()); //checks for PCI button being pressed

        JButton usbbut = new JButton("USB"); //new button for USB
        panel.add(usbbut); //adds this USB button to the panel

        usbbut.addActionListener(new usbPick()); //checks for USB button being pressed

        JButton membut = new JButton("MEM"); //new button for MEM
        panel.add(membut); //adds this MEM button to the panel

        membut.addActionListener(new memPick()); //checks for MEM button being pressed


    }

    //a class for handling the CPU button click
    private class cpuPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("\nCPU Selected"); //print "CPU selected" when CPU is selected
            showCPU(); //call showCPU method
        }
    }

    //a class for handling the PCI button click
    private class pciPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("\nPCI Selected"); //print "PCI selected" when PCI is selected
            showPCI(); //call showPCI method
        }
    }

    //a class for handling the USB button click
    private class usbPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("\nUSB Selected"); //print "USB selected" when USB is selected
            showUSB(); //call showUSB method
        }
    }

    //a class for handling the MEM button click
    private class memPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("\nMEM Selected"); //print "MEM selected" when MEM is selected
            showMEM(); //call showMEM method
        }
    }





//Getting the PCI,CPU,USB and MEM Info - Mark Burkley's
    //method to display PCI Info
    public static void showPCI(){ try{
        pciInfo pci = new pciInfo(); //creates a new instance of pciInfor
        pci.read(); //reads computer's PCI info

        FileWriter myWriter = new FileWriter("pci.txt");
        System.out.println("\nThis machine has "+
            pci.busCount()+" PCI buses "); //message to display PCI bus info

        // iterate through each PCI bus to print how many devices are connected
        for (int i = 0; i < pci.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                pci.deviceCount(i)+" devices");

            // Iterate for up to 32 devices.  Not every device slot may be populated
            // so ensure at least one function before printing device information

            //iterate through each device to print how many functions there are
            for (int j = 0; j < 64; j++) {
                if (pci.functionCount (i, j) > 0) {
                    System.out.println("Bus "+i+" device "+j+" has "+
                        pci.functionCount(i, j)+" functions");

                    // Iterate through each function for the device (up to 8 functions per device) to print vendor and product IDs
                    for (int k = 0; k < 8; k++) {
                        if (pci.functionPresent (i, j, k) > 0) {
                            //System.out.println("Bus "+i+" device "+j+" function "+k+
                            //    " has vendor "+String.format("0x%04X", pci.vendorID(i,j,k))+
                            //    " and product "+String.format("0x%04X", pci.productID(i,j,k)));
                            //    String busId = String.valueOf(i); //bus id

                            String busId = String.valueOf(i); //bus id

                            String deviceId = String.valueOf(j); //device id

                            String vendorId = String.format("0x%04X", pci.vendorID(i, j, k)); //vendor id

                            String productId = String.format("0x%04X", pci.productID(i, j, k)); //product id

                            String functionNumber = String.valueOf(k); //func no.

                            //updates panel
                        panel.add(new JLabel("\nBus Id = " + busId + "\nDevice Id = " + deviceId + "\nVendor Id = " + vendorId + "\nProduct Id = " + productId + "\nFunction Number = " + functionNumber));
                        panel.add(new JLabel("<html><br/>"));

                        panel.revalidate(); // Refresh the layout
                        panel.repaint(); // Refresh the drawing

                            //put PCI info variables into the string array

                            String[] pciDevices = {busId, deviceId, functionNumber, productId, vendorId};

                            System.out.println(pciDevices[0] + pciDevices[1] + pciDevices[2] + pciDevices[3] + pciDevices[4]);

                            

                            myWriter.write(i + "\n" + j + "\n" + k + "\n" + String.format("0x%04X", pci.vendorID(i,j,k)) + "\n" + String.format("0x%04X", pci.productID(i,j,k)) + "\n");
                        }
                    }
                }
            }
        }
        myWriter.close();
        System.out.println("Successfully wrote to pci.txt.");
                try {
            // Prepare the Maven command
            ProcessBuilder processBuilder = new ProcessBuilder(
                "mvn", "exec:java", "-Dexec.mainClass=com.example.pciGraph");

            // Redirect error stream to the same output stream
            processBuilder.redirectErrorStream(true);
            
            // Start the process
            Process process = processBuilder.start();

            // Capture the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print the Maven output
            }

            // Wait for the process to complete and get the exit code
            int exitCode = process.waitFor();
            System.out.println("Maven command exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    } catch (Exception e){
        System.err.println("Failed to get PCI info"); //handles any exceptions in fetching PCI info
    }
    
    }

    //method to display USB Info
    public static void showUSB(){
        usbInfo usb = new usbInfo(); //creates a new instance of usbInfo
        usb.read(); //reads computer's USB infp
        System.out.println("\nThis machine has "+
            usb.busCount()+" USB buses "); //message to display USB bus info

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                usb.deviceCount(i)+" devices"); //print how many USB devices are attatched to the USB bus

            //new device removed or added
            int count = 0;

            if (usb.deviceCount() > 0){
                System.out.println("Device has been added"); 
            }else if (count == 0){ 
                System.out.println("No device attached or device has been removed"); 
            }

                // Iterate through all of the USB devices on the USB bus
                for (int j = 1; j <= usb.deviceCount(i); j++) {
                    System.out.println("Bus "+i+" device "+j+
                        " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+
                        " and product "+String.format("0x%04X", usb.productID(i,j))); //print vendor and product IDs for each USB device
            }
        }

            try {
            // Prepare the Maven command
            ProcessBuilder processBuilder = new ProcessBuilder(
                "mvn", "exec:java", "-Dexec.mainClass=com.example.cpuGraph");

            // CHATGPT WROTE THIS TO FIX AN ERROR WITH ERROR STREAM
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Capture the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print the Maven output
            }

            // Wait for the process to complete and get the exit code
            int exitCode = process.waitFor();
            System.out.println("Maven command exited with code: " + exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

    }

    //method to display CPU Info
    public static void showCPU(){
        cpuInfo cpu = new cpuInfo(); //creates a new instance of cpuInfo
        cpu.read(0); //reads cpmuter's CPU info

        // Show CPU model, CPU sockets and cores per socket
        System.out.println("CPU " + cpu.getModel() + " has "+
            cpu.socketCount() + " sockets each with "+
            cpu.coresPerSocket() + " cores");

        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d="+cpu.l1dCacheSize()+
            ", l1i="+cpu.l1iCacheSize()+
            ", l2="+cpu.l2CacheSize()+
            ", l3="+cpu.l3CacheSize());

        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);
        System.out.println("Core 1 idle time ="+cpu.getIdleTime(1)+"%");
        
        System.out.println("Core 1 busy time ="+cpu.getUserTime(1)+"%");
        
        double idleTime = cpu.getIdleTime(1);
        double busyTime = cpu.getUserTime(1);
        double ratio = busyTime/idleTime;

        System.out.println("Core 1 load =  "+ ratio +"%");

        //to get overall system load, get average load across all cores, so add all core loads then divide by number of core loads
        //1. equal core load x = cpu.getBusy/cpu.getIdleTime repeat for number of cores
        //2. add all cores = to cpuLoad
        //3. divide CPUload by number of core loads

        cpu.read(0);
        double cpuLoad = cpu.getUserTime()/cpu.getIdleTime();
        double systemLoad = cpuLoad / 8;

        System.out.println("CPU load = " + cpuLoad);
        System.out.println("System load = " + systemLoad);

        try {
            FileWriter myWriter = new FileWriter("CPU.txt");
            myWriter.write(cpu.getModel() + cpu.socketCount() + "\n" + cpu.coresPerSocket() + "\n" +cpu.l1dCacheSize()+ "\n" + cpu.l1iCacheSize()+ "\n" + cpu.l2CacheSize() + "\n" + cpu.l3CacheSize());
            myWriter.close();
            System.out.println("Successfully wrote to CPU.txt.");

 
            try {
                // Prepare the Maven command
                ProcessBuilder processBuilder = new ProcessBuilder(
                    "mvn", "exec:java", "-Dexec.mainClass=com.example.cpuGraph");

                // CHATGPT WROTE THIS TO FIX AN ERROR WITH ERROR STREAM
                processBuilder.redirectErrorStream(true);

                // Start the process
                Process process = processBuilder.start();

                // Capture the output
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Print the Maven output
                }

                // Wait for the process to complete and get the exit code
                int exitCode = process.waitFor();
                System.out.println("Maven command exited with code: " + exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred in writing to CPU.txt.");
            e.printStackTrace();
        }
    }

    //method to display Memory Info
    public void showMEM(){
        cpuInfo cpu = new cpuInfo(); //creats a new instance of cpuInfor
        cpu.read(0); //reads computer's CPU info

        //display system properties
        String nameOS = "os.name";  
        String versionOS = "os.version";  
        String architectureOS = "os.arch";

        //calculates memory available
        double memory = Runtime.getRuntime().totalMemory() / 1000000;

        System.out.println("\nTotal memory available to JVM (Megabytes): " + memory ); //prints available memory
        System.out.println("\nOS: \t\t\t" + System.getProperty(nameOS)); //prints OS name
        System.out.println("OS Version: \t\t" + System.getProperty(versionOS)); //prints OS version
        System.out.println("OS Architecture: \t" + System.getProperty(architectureOS)); //prints OS architecture
        System.out.println();
        System.out.println("CPU Memory info"); //prints header for CPU memory info      
        System.out.println("l1d="+cpu.l1dCacheSize()+ ", l1i="+cpu.l1iCacheSize()+ ", l2="+cpu.l2CacheSize()+ ", l3="+cpu.l3CacheSize()); //prints CPU cache info

        //updates panel
        panel.add(new JLabel("\nTotal memory available to JVM (Megabytes): " + memory));
        panel.add(new JLabel("<html><br/>"));
        panel.add(new JLabel("OS:" + System.getProperty(nameOS)));

        panel.revalidate(); // Refresh the layout
        panel.repaint(); // Refresh the drawing
    }

//main method to run the GUI
    public static void main(String[] args){
        System.loadLibrary("sysinfo"); //loads system library
        sysInfo info = new sysInfo(); //creates a new instance of sysInfo

        
        

        //showMEM();
        //showCPU();
        //showPCI();
        //showUSB();




        //Calling the GUI to run
        SwingUtilities.invokeLater(() -> {
            template ex = new template();
            ex.setVisible(true);
        });
    }
}
