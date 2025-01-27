/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;
import com.google.gson.JsonObject;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.*;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author frankienglish
 */
public class proxy {
    
    public proxy(){}

    /*
    * takes in the name of the method being requested
    * depending on the method, different arguments will be passed in
    * iterate through the arguments passed in, and assign them to the json object
    */
    
    public static void main(String[] args){
        proxy prox = new proxy();
        prox.synchExecution("CreatePlaylist", "frankie", "test playlist");
    }
    
    /*
    * check the remoteMethod.contains() to see which category to put it under
    * 
    */
    
    public JSONObject synchExecution(String remoteMethod,Object...args)
    {
        JSONObject remote_method = new JSONObject();
        JSONObject child_data = new JSONObject();
        remote_method.put("objectName", "SongDispatcher");
        if (remoteMethod.equals("getSongChunk")){
        
        remote_method.put("remoteMethod", remoteMethod);
        
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("objectName", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("song", arg);
                count += 1;
            }
            else if (count == 2)
            {
                child_data.put("fragment", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
        }
        
        }
        
        if (remoteMethod.equals("searchSong")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("songName", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("song", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("playSong")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("songName", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("DeletePlaylist")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("playlistName", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("username", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("CreatePlaylist")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("username", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("playlistName", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("addSongToPlaylist")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("username", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("playListName", arg);
                count += 1;
            }
            else if (count == 2)
            {
                child_data.put("songName", arg);
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("CreateUser")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("password", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("username", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("Login")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("password", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("username", arg);
                count += 1;
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("userExists")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("password", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("username", arg);
                count += 1;
            }
            else if (count == 2)
            {
                child_data.put("userList", arg);
            }
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("displayProfile")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("username", arg);
                count += 1;
            }
            
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        if (remoteMethod.equals("displayPlaylist")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("username", arg);
                count += 1;
            }
            else if (count == 1)
            {
                child_data.put("playlistName", arg);
                count += 1;
            }
            
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        
        if (remoteMethod.equals("searchSong")){
        
        remote_method.put("remoteMethod", remoteMethod);
        int count = 0;
        for (Object arg: args)
        {
            if (count == 0)
            {
                child_data.put("songName", arg);
                count += 1;
            }
            
            
            
            remote_method.put("param", child_data);
            
            }
        
        }
        
        
  
        try(FileWriter file = new FileWriter("methods.json"))
        {
            System.out.println("running..");
            file.write(remote_method.toJSONString());
            file.flush();
            System.out.println("done");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    
        return remote_method;
    }
    
    
}


