// package cs4421;

public class template 
{
     public static void main(String[] args)
     {
          String nameOS = "os.name";  
          String versionOS = "os.version";  
          String architectureOS = "os.arch";
          double memory = Runtime.getRuntime().totalMemory() / 1000000;







          System.loadLibrary("sysinfo");
          sysInfo info = new sysInfo();

          cpuInfo cpu = new cpuInfo();
          cpu.read();

          System.out.println("CPU " + cpu.getModel() + " has "+
          cpu.socketCount() + " sockets each with "+
          cpu.coresPerSocket() + " cores");
          System.out.println("l1d="+cpu.l1dCacheSize()+
          ", l1i="+cpu.l1iCacheSize()+
          ", l2="+cpu.l2CacheSize()+
          ", l3="+cpu.l3CacheSize());


          /*while(true)
          {
            try {
                Thread.sleep (1000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            cpu.read();
            System.out.println("core 3 idle="+cpu.getIdleTime(3)+"%");
          }*/







          
          

          
          System.out.println("\nTotal memory available to JVM (Megabytes): " + memory );
          System.out.println("\nOS: \t\t\t" + System.getProperty(nameOS));
          System.out.println("OS Version: \t\t" + System.getProperty(versionOS));
          System.out.println("OS Architecture: \t" + System.getProperty(architectureOS));
          System.out.println();
          

          
     }
}
