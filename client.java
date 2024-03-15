import java.net.*;
import java.io.*;
import java.util.*;

public class client {
   
    public static void main(String args[]) {
        Socket as;
        DataInputStream sin;
        DataOutputStream sout;

        try {
            System.err.println("client is started....");
            as = new Socket("localhost", 1234);
            sout = new DataOutputStream(as.getOutputStream());
         sin = new DataInputStream(as.getInputStream());
         Scanner scan= new Scanner(System.in);
         String toSend,received;
         while (true) {  
            System.out.print("Client:");
            toSend= scan.nextLine();
            sout.writeUTF(toSend);
            if(toSend.equals("quit")){
                System.out.println("client is closing");
                break;
            }
            received= sin.readUTF();
            System.out.println("Server says: " + received);
            if(received.equals("quit")){
                System.out.println("Server is closing");
                break;
            }
          
        }
    }

        catch (IOException e) {
            System.out.println(e);
        }

    }
}


   