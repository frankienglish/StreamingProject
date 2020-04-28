/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.util.*;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFrame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main
{
    //private JFrame frame;
    
    void mp3play(final String file) {
        try {
            final InputStream is = (InputStream)new CECS327InputStream(file);
            final Player mp3player = new Player(is);
            mp3player.play();
        }
        catch (JavaLayerException exception) {
            exception.printStackTrace();
        }
        catch (IOException exception2) {
            exception2.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        
        //ArrayList of User objects for sign up and login
        ArrayList<User> userList = new ArrayList<User>();
        
        //Login_Systems window = new Login_Systems();
        //window.frame.setVisible(true);
        //DeletePlayList(frankie,)
        //window.frame.setVisible(true);
        //String testUsername = window.username;
        //String testPassword = window.pswd;
        //CreateUser(userList, testUsername,testPassword);
        //System.out.println(UserExists(userList, testUsername, testPassword));
        
        //ArrayList of Song JSONObjects that shows up on the search list
        ArrayList<JSONObject> searchList = new ArrayList<JSONObject>();
        //ArrayList of Playlist objects
        ArrayList<Playlist> profileList = new ArrayList<Playlist>();
        //create songs
        JSONObject song1Details = new JSONObject();
        song1Details.put("id", "1");
        JSONObject song1Object = new JSONObject();
        song1Object.put("song", song1Details);
        JSONObject song2Details = new JSONObject();
        song2Details.put("id", "2");
        JSONObject song2Object = new JSONObject();
        song2Object.put("song", song2Details);
        JSONObject song3Details = new JSONObject();
        song3Details.put("id", "3");
        JSONObject song3Object = new JSONObject();
        song3Object.put("song", song3Details);
        JSONObject song4Details = new JSONObject();
        song4Details.put("id", "4");
        JSONObject song4Object = new JSONObject();
        song4Object.put("song", song4Details);
        //add playlists to list
        JSONArray playlist = new JSONArray();
        playlist.add(song1Object);
        playlist.add(song2Object);
        playlist.add(song3Object);
        playlist.add(song4Object);
        //write JSON file
        try(FileWriter file = new FileWriter("list.json"))
        {
            file.write(playlist.toJSONString());
            file.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("music.json"))
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter artist name or song title:");
            String search = input.next();
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray songList = (JSONArray) obj;
             
            //Iterate over playlist array
            songList.forEach( song -> parseSearchedSongObject((ArrayList<JSONObject>) searchList, (JSONObject) song, search ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        displaySearchList(searchList);
    }
    //creates a new user when signing up and adds it to ArrayList of users
    public static void CreateUser(ArrayList<User> userList,String username, String password)
    {
        User tempUser = new User(username, password);
        userList.add(tempUser);
    }
    //checks if a user exists
    public static boolean UserExists(ArrayList<User> userList, String username, String password)
    {
        for (User u : userList)
        {
            if (u.getUsername() == username && u.getPassword() == password)
            {
                return true;
            }
        }
        return false;
    }
    //searches for songs (CALL searchList.clear() AND THIS FUNCTION WHEN SEARCH BUTTON IS CLICKED)
    private static void parseSearchedSongObject(ArrayList<JSONObject> searchList, JSONObject song, String search)
    {
        //Get song object within list
        JSONObject songObj = (JSONObject) song.get("song");
        //Get artist object within list
        JSONObject artistObj = (JSONObject) song.get("artist");
        //check song name and artist name for searched word
        if (search.equals(songObj.get("title")) || search.equals(artistObj.get("name")))
        {
            searchList.add(songObj);
        }        
    }
    //iterates through searchList and displays song names
    private static void displaySearchList(ArrayList<JSONObject> searchList)
    {
        for (JSONObject tempSong : searchList)
        {
            System.out.println(tempSong.get("title"));
        }
    }
    //plays a song from searchList when clicked on
    private static void playSongFromSearchList(ArrayList<JSONObject> searchList, String songName)
    {
        for (JSONObject tempSong : searchList)
        {
            if (songName.equals(tempSong.get("title")))
            {
                final Main player = new Main();
                player.mp3play(tempSong.get("id")+".mp3");
            }
        }
    }
    
    
    
    
    // IN PROGRESS ---------------------------------------------------------------------------------------------------------------------------------------------
    // ArrayList<Playlist> OR ArrayList<JSONObject>
    // UNCLEAR OF INSTRUCTIONS
    
    
    
    
    //create new playlist object
    private void CreatePlaylist(ArrayList<Playlist> profileList, String name)
    {
        Playlist tempPlaylist = new Playlist(name);
        profileList.add(tempPlaylist);
    }
    //deletes a playlist object
//    private void DeletePlaylist(ArrayList<Playlist> profileList, String name)
//    {
//        int count = 0;
//        for (Playlist temp : profileList)
//        {
//            if (name.equals(temp.getName()))
//            {
//                profileList.remove(count);
//            }
//            count++;
//        }
//    }
    //adds a song to a specific playlist
    private void addSongToPlaylist(ArrayList<JSONObject> searchList, ArrayList<Playlist> profileList, String songName, String playlistName)
    {
        for (JSONObject tempSong : searchList)
        {
            if (songName.equals(tempSong.get("title")))
            {
               Song addedSong = new Song(songName);
               for (Playlist temp : profileList)
               {
                    if (playlistName.equals(temp.getName()))
                    {
                        temp.addSong(addedSong);
                    }
               }
            }
        }
    }
    //displays all playlist names from profileList
    private static void displayProfileList(ArrayList<Playlist> profileList)
    {
        for (Playlist temp : profileList)
        {
            System.out.println(temp.getName());
        }
    }
    //plays a song from a playlist
    private static void playSongFromPlaylist(String songName)
    {
        
    }
    
    
    private static void DeletePlaylist(String username, String playlistName)
    {
       
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;
           
            Iterator<JSONObject> it = userList.iterator();
            while (it.hasNext())
            {
                JSONObject temp = it.next();
                if (temp.get("username").equals(username))
                {
                    JSONParser parser = new JSONParser();
                    JSONArray json = (JSONArray) parser.parse(temp.get("playlists").toString());
                    Iterator<JSONObject> it2 = json.iterator();
                    while(it2.hasNext())
                    {
                        JSONObject temp2 = it2.next();
                        if (temp2.get("playlist name").equals(playlistName))
                        {
                            
                            json.remove(temp2);
                            System.out.println("playlist found and removed");                      
                        }
                       System.out.println("going");
                    }
                    System.out.println("still going");
                    temp.replace("playlists",json);
                    
                }
            }
            try (FileWriter file = new FileWriter("Users.json"))
            {
 
                file.write(userList.toJSONString());
                file.flush();
                file.close();
 
            } catch (IOException e) {
                e.printStackTrace();
            }
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

//LIST -> PLAYLISTS -> SONGS -> SONG DETAILS

