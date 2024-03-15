import java.net.*;
import java.io.*;
import java.util.*;

public class UDPclient1 {
    public static void main(String arg[]) {
        DatagramSocket ds = null;
        DatagramPacket dp,reply;
        Scanner s = new Scanner(System.in);
        try {
            ds = new DatagramSocket();

            while (true) {
                String str;
                System.out.print("Client:");
                str = s.nextLine();
                byte[] b = str.getBytes();
                InetAddress sHost = InetAddress.getByName("localhost");
                dp = new DatagramPacket(b, b.length, sHost, 1234);
                ds.send(dp);

                byte[] b1 = new byte[1000];
                reply = new DatagramPacket(b1, b1.length);
                ds.receive(reply);
                System.out.println("From server:" + (new String(reply.getData())).trim());
                System.out.println("Server Port:" + reply.getPort());

                String st = new String(reply.getData());
                System.out.println("Server says:" + st);
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
