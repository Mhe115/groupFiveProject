// package cs4421;

public class template 
{
     public static void main(String[] args)
     {
          System.loadLibrary("sysinfo");
          sysInfo info = new sysInfo();
          //int     square = info.intExample(5);
          //System.out.println("intExample: " + square);
          //String     str = info.stringExample("test");
          //System.out.println("stringExample: " + str);

          cpuInfo cpu = new cpuInfo();
          String nameOS = "os.name";  
          String versionOS = "os.version";  
          String architectureOS = "os.arch";
          double memory = Runtime.getRuntime().totalMemory() / 1000000;

          //System.out.println("CPU " + cpu.getModel() + " has "+ cpu.coreCount() + " cores");
          System.out.println("\nTotal memory available to JVM (Megabytes): " + memory );
          System.out.println("\nOS: \t" + System.getProperty(nameOS));
          System.out.println("OS Version: \t" + System.getProperty(versionOS));
          System.out.println("OS Architecture: \t" + System.getProperty(architectureOS));
          //System.out.println(info);

          
     }
}
