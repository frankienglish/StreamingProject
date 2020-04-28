/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EchoServer implements Runnable {
    private final int port;
    private byte[] sendBuffer = new byte[65507];
    private byte[] receiveBuffer = new byte[65507];
    private Dispatcher dispatcher = new Dispatcher();
    private SongDispatcher songDispatcher = new SongDispatcher();
    private DatagramSocket serverSocket;
    
    public EchoServer(int port) throws SocketException, IOException {
        this.serverSocket = new DatagramSocket(50000);
        this.port = port;
        dispatcher.registerObject(songDispatcher, "SongDispatcher");  
    }
    
    @Override
    public void run() {
        while(true)
        {
            System.out.println("server receiving packet");
            DatagramPacket dp = new DatagramPacket(receiveBuffer,0,receiveBuffer.length); //parameters to receive packet
        try {
            serverSocket.receive(dp);
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("received packet");
            String jsonRequest = new String(dp.getData(), 0, dp.getLength());
            String ret = dispatcher.dispatch(jsonRequest);
            System.out.println("after dispatch");
            JSONParser parser = new JSONParser();
            JSONObject json;
        try {
            json = (JSONObject) parser.parse(ret);
            String ret2 = json.get("ret").toString();
            System.out.println("ret:"+ret2);
            sendBuffer = ret2.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length,InetAddress.getLocalHost(), dp.getPort()); //parameters to send packet
            System.out.println("before server send packet");
            try {
            serverSocket.send(datagramPacket);
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (ParseException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (UnknownHostException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
            System.out.println("after server send packet");
        }
            
    }

    
//    public static void main(String a[]) throws Exception
//    {
//        DatagramSocket ds = new DatagramSocket(9999); //need port number to receive packet
//        
//        byte[] b1 = new byte[1024];
//        
//        DatagramPacket dp = new DatagramPacket(b1, b1.length);
//        ds.receive(dp); //receive packet from client
//        String str = new String(dp.getData(),0,dp.getLength()); //fetch data into string
//        int num = Integer.parseInt(str.trim()); //converts string into number
//        int result = num*num; //square number
//        byte[] b2 = String.valueOf(result).getBytes();
//        InetAddress ia = InetAddress.getLocalHost();
//        DatagramPacket dp1 = new DatagramPacket(b2, b2.length, ia, dp.getPort()); //send packet to client (need port number)
//        ds.send(dp1); //send packet to client
//    }
}