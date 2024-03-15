import java.net.*;
import java.io.*;
import java.util.*;

public class UDPserver1 {

    public static void main(String args[]) {
        DatagramSocket ds = null;
        DatagramPacket dp = null;

        try {
            System.out.println("Server is Ready..");
            ds = new DatagramSocket(1234);

            while (true) {
                byte[] b = new byte[1000];
                dp = new DatagramPacket(b, b.length);
                ds.receive(dp);
                System.out.println("From client:" + (new String(dp.getData())).trim());
                System.out.println("Client Port:" + dp.getPort());
                System.out.println("Host name:" + dp.getAddress());

                String str = new String(dp.getData());
                System.out.println("Client says:" + str);

                Scanner s = new Scanner(System.in);
                String st;
                System.out.print("Server:");
                str = s.nextLine();
                byte[] b1 = str.getBytes();
                InetAddress sHost = dp.getAddress();
                int sPort = dp.getPort();
                DatagramPacket reply = new DatagramPacket(b1, b1.length, sHost, sPort);
                ds.send(reply);
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
