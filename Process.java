
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Process {

public static String output1;
        public static void main(String[] args){
             String output;
             Process porcess;
             String list = "<Give your code.> ";
                try {
                    porcess = Runtime.getRuntime().exec(list);
                    BufferedReader br = new BufferedReader(new InputStreamReader(porcess.getInputStream()));
                    while ((output = br.readLine()) != null)
//                      System.out.println("The process is up: " + output);
                        output1=output;
                    porcess.waitFor();
//                  System.out.println ("exit: " + porcess.exitValue());
                   switch(porcess.exitValue()) {
                   case 0: System.out.println("Process is up and" + output1 + "is the ID");
                           break;
                   case 1: System.out.println("Either process is Down or Killed.");
                           break;
                   case 2: System.out.println("Usage should be {start|stop|status|Kill|..etc}");
                           break;
                   default : System.out.println("Process has entred Zombie state");
                           break;

                   }

                    porcess.destroy();
                } catch (Exception e) {}
            }
}
