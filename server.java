import java.net.*;
import java.io.*;
import java.util.*;

class server{
    public static void main(String[] args) {
           ServerSocket ss;
           Socket as;
           DataInputStream sin;
           DataOutputStream sout;

    try {
         System.out.println("waiting for client...");
         ss= new ServerSocket(1234);
         as = ss.accept();
         System.out.println("Connection Established...");
         sout = new DataOutputStream(as.getOutputStream());
         sin = new DataInputStream(as.getInputStream());
         Scanner scan= new Scanner(System.in);
         String toSend,received;
         while (true) {
            received= sin.readUTF();
            System.out.println("Client says: " + received);
            if(received.equals("quit")){
                System.out.println("Client is closing");
                break;
            }
            System.out.print("Server:");
            toSend= scan.nextLine();
            sout.writeUTF(toSend);
            if(toSend.equals("quit")){
                System.out.println("Server is closing");
                break;
            }
            
         }
    } catch (Exception e) {
        System.out.println(e);
    }
    }
}