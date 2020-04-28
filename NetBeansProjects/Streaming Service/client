/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClient {
    //private final int port;
    private byte[] sendBuffer = new byte[65507];
    private byte[] receiveBuffer = new byte[65507];
    private DatagramSocket clientSocket;
    
    public EchoClient() throws SocketException {
        //this.port = port;
        this.clientSocket = new DatagramSocket();
    }
    
//    @Override
//    public void run() {
//            sendBuffer = new byte[65507]; //max length
//            while(true) {
//                DatagramPacket datagramPacket = new DatagramPacket(sendBuffer,0,sendBuffer.length); //parameters to receive packet
//                clientSocket.receive(datagramPacket);
//                
//                String receivedMessage = new String(datagramPacket.getData());
//                System.out.println(receivedMessage);
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Timeout. Client is closing.");
//        }
//    }

    
    public String sendPacket(String packet) throws UnknownHostException, IOException {
        sendBuffer = packet.getBytes();
        System.out.println("client sending packet");
        DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length,InetAddress.getLocalHost(), 50000); //parameters to send packet
        clientSocket.send(datagramPacket);
        System.out.println("client receiving packet");
        DatagramPacket dp = new DatagramPacket(receiveBuffer,0,receiveBuffer.length); //parameters to receive packet
        clientSocket.receive(dp);
        
        System.out.println("client received packet");
        String receivedMessage = new String(dp.getData(), 0, dp.getLength());
        System.out.println("recieved message: " + receivedMessage);
        return receivedMessage;
        
    }
    
    
//    public static void main(String[] args) throws Exception
//    {
//        DatagramSocket ds = new DatagramSocket(); //accepts packets
//        
//        int i = 8;
//        InetAddress ia = InetAddress.getLocalHost();
//        byte[] b = String.valueOf(i).getBytes(); //turn int to string into bytes to pass
//        
//        DatagramPacket dp = new DatagramPacket(b,b.length,ia,9999); //packet to send (need port number to send)
//        ds.send(dp); //send packet to socket
//        
//        byte[] b1 = new byte[1024];
//        DatagramPacket dp1 = new DatagramPacket(b1, b1.length); //dont need port number to receive
//        ds.receive(dp1);
//        
//        String str = new String(dp1.getData(),0,dp1.getLength()); //get data from user
//        System.out.println("result is " + str);
//    }
}
