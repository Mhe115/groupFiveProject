// Group Five Project
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class template extends JFrame{
    public static void showPCI(){
        pciInfo pci = new pciInfo();
        pci.read();

        System.out.println("\nThis machine has "+
            pci.busCount()+" PCI buses ");

        // Iterate through each bus
        for (int i = 0; i < pci.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                pci.deviceCount(i)+" devices");

            // Iterate for up to 32 devices.  Not every device slot may be populated
            // so ensure at least one function before printing device information
            for (int j = 0; j < 32; j++) {
                if (pci.functionCount (i, j) > 0) {
                    System.out.println("Bus "+i+" device "+j+" has "+
                        pci.functionCount(i, j)+" functions");

                    // Iterate through up to 8 functions per device.
                    for (int k = 0; k < 8; k++) {
                        if (pci.functionPresent (i, j, k) > 0) {
                            System.out.println("Bus "+i+" device "+j+" function "+k+
                                " has vendor "+String.format("0x%04X", pci.vendorID(i,j,k))+
                                " and product "+String.format("0x%04X", pci.productID(i,j,k)));
                        }
                    }
                }
            }
        }
    }

    public static void showUSB(){
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis machine has "+
            usb.busCount()+" USB buses ");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                usb.deviceCount(i)+" devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+
                    " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+
                    " and product "+String.format("0x%04X", usb.productID(i,j)));
            }
        }
    }

    public static void showCPU(){
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

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
        System.out.println("core 1 idle="+cpu.getIdleTime(1)+"%");
    }

    /*public static void memoryInfo(){
          System.out.println("\nTotal memory available to JVM (Megabytes): " + memory );
          System.out.println("\nOS: \t\t\t" + System.getProperty(nameOS));
          System.out.println("OS Version: \t\t" + System.getProperty(versionOS));
          System.out.println("OS Architecture: \t" + System.getProperty(architectureOS));
          System.out.println();
          System.out.println("CPU Memory info");          
          System.out.println("l1d="+cpu.l1dCacheSize()+ ", l1i="+cpu.l1iCacheSize()+ ", l2="+cpu.l2CacheSize()+ ", l3="+cpu.l3CacheSize());
    }*/





   public template() {
        // Set up the JFrame
            setTitle("Group Five Project");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            createScreenCont();
    }

    private void createScreenCont() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        
        panel.add(new JLabel("Select Data Type"));

        

        

        JButton cpubut = new JButton("CPU");
        panel.add(cpubut);

        cpubut.addActionListener(new cpuPick());

        JButton pcibut = new JButton("PCI");
        panel.add(pcibut);

        pcibut.addActionListener(new pciPick());

        JButton usbbut = new JButton("USB");
        panel.add(usbbut);

        usbbut.addActionListener(new usbPick());
    }

    private class cpuPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("CPU Selected");
            showCPU();
        }
    }

    private class pciPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("PCI Selected");
            showPCI();
        }
    }

    private class usbPick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("USB Selected");
            showUSB();
        }
    }















    public static void main(String[] args){
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        //showCPU();
        //showPCI();
        //showUSB();

        SwingUtilities.invokeLater(() -> {
            template ex = new template();
            ex.setVisible(true);
        });
    }
}