import java.net.*;
import java.io.*;
import java.util.*;
public class UDPclient {
    public static void main(String arg[]){
        DatagramSocket ds = null;
        DatagramPacket dp ;
        Scanner s = new Scanner(System.in);
      try {
          ds = new DatagramSocket();
        String str;

        while(true){
            //send 
        System.out.print("Client:");
         str = s.nextLine();
          
          byte [] b= str.getBytes();
          InetAddress sHost = InetAddress.getByName("localhost");
          dp= new DatagramPacket(b,b.length,sHost,1234);
          ds.send(dp);
          if(str.equals("quit")){
                System.out.println("Client is closing...");
                break;
            }

            //received 
            byte[] b1 = new byte[1000];
            dp = new DatagramPacket(b, b.length);
            ds.receive(dp);
           
           System.out.println("From server:" + (new String(dp.getData())).trim());
            System.out.println("server Port:" + dp.getPort());
            String st = new String(dp.getData()); 
            if(st.equals("quit")){
                System.out.println("Sever is closing...");
                break;
            }
            System.out.println("Server says:" + st);

         }
        }



          catch (SocketException e) 
          {
              System.out.println("Socket: " + e.getMessage());
          }
          catch (IOException e) 
          {
              System.out.println("IO: " + e.getMessage());
          }
          finally 
          {
              if (ds != null)
                  ds.close();
          }
      
    }
}