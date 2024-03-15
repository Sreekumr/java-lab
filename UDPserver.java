import java.net.*;
import java.io.*;
import java.util.*;

public class UDPserver {

    public static void main(String args[]) {
        DatagramSocket ds = null;
        DatagramPacket dp = null, reply;

        try {

            System.out.println("Server is Ready..");
            ds = new DatagramSocket(1234); 

            while(true){
            byte[] b = new byte[1000];
            dp = new DatagramPacket(b, b.length);
            //received
            ds.receive(dp);
            System.out.println("From client:" + (new String(dp.getData())).trim());
            System.out.println("Client Port:" + dp.getPort());
            System.out.println("Host name:"+dp.getAddress());
            String str = new String(dp.getData());
            if(str.equals("quit")){
                System.out.println("Client is closing...");
                break;
            }
            System.out.println("Client says:" + str);
            Scanner s = new Scanner(System.in);


            
            String st;
            System.out.print("Server:");
             st = s.nextLine();
         
              byte [] b1= st.getBytes();
              InetAddress sHost = dp.getAddress();
              int port= dp.getPort();
              reply= new DatagramPacket(b,b.length,sHost,port);
              ds.send(reply);   
               if(st.equals("quit")){
                System.out.println("Sever is closing...");
                break;
            }
            }


        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (ds != null)
                ds.close();
        }
    }
}
