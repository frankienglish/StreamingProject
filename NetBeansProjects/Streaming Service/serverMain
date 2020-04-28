/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author frankienglish
 */
public class serverMain {
    
    public static void main (String[] args) throws SocketException, IOException
    {
        EchoServer server = new EchoServer(50000);
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(server);
        
    }
    
}
