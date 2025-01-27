/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.Base64;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SongDispatcher
{
    static final int FRAGMENT_SIZE = 8192; 
    public SongDispatcher()
    {
        
    }
    
    /* 
    * getSongChunk: Gets a chunk of a given song
    * @param key: Song ID. Each song has a unique ID 
    * @param fragment: The chunk corresponds to 
    * [fragment * FRAGMENT_SIZE, FRAGMENT_SIZE]
    */
    public String getSongChunk(Long key, Long fragment) throws FileNotFoundException, IOException
    {
        byte buf[] = new byte[FRAGMENT_SIZE];

        File file = new File("./" + key);
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.skip(fragment * FRAGMENT_SIZE);
        inputStream.read(buf);  
        inputStream.close(); 
        
        // Encode in base64 so it can be transmitted 
         return Base64.getEncoder().encodeToString(buf);
    }
    
    public String playSong(Long key) throws FileNotFoundException, IOException
    {
        byte buf[] = new byte[FRAGMENT_SIZE];
        File file = new File("./playSong.json");
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(buf);
        inputStream.close();
        // Encode in base64 so it can be transmitted 
         return Base64.getEncoder().encodeToString(buf);
    }
    
    //creates a new user when signing up and adds it to ArrayList of users
    public String CreateUser(String username, String password)
    {
        JSONObject userObj = new JSONObject(); //user object to be added
        userObj.put("username",username); //inputs username into object
        userObj.put("password",password); //inputs password into object
        JSONArray playlists = new JSONArray(); //list of playlists
        userObj.put("playlists", playlists);
        
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray tempList = (JSONArray) obj;
            tempList.add(userObj);
            
            System.out.println(tempList);
            try (FileWriter file = new FileWriter("Users.json"))
            {
 
                file.write(tempList.toJSONString());
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
        return userObj.toJSONString();
    }
    //checks if a user exists
    public String Login(String username, String password)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray usersList = (JSONArray) obj;
            
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            
            Iterator<JSONObject> it = usersList.iterator();
            while (it.hasNext())
            {
                JSONObject temp = it.next();
                System.out.println(temp.get("username"));
                System.out.println(temp.get("password"));
                
                if (temp.get("username").equals(username) && temp.get("password").equals(password))
                {
                    System.out.println(temp.get("username"));
                    System.out.println(temp.get("password"));
                    return "true";
                }
            }
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return "false"; //user doesn't exist and calls CreateUser()
    }
    
    
      private static void parseSearchedSong(JSONArray songList, JSONObject song, String songName)
    {
        JSONObject songObj = (JSONObject) song.get("song");
        //Get artist object within list
        JSONObject artistObj = (JSONObject) song.get("artist");
        String temp = songObj.get("title").toString();
        temp = temp.replaceAll("\\s","").toLowerCase();
        String temp2 = artistObj.get("name").toString();
        temp2 = temp2.replaceAll("\\s","").toLowerCase();
        String temp3 = songName;
        temp3 = temp3.replaceAll("\\s","").toLowerCase();
        //check song name and artist name for searched word
        if (temp3.equals(temp) || temp3.equals(temp2))
        {
            System.out.println("song found");
            songList.add(songObj);
        }        
       
    } 
    
    
     public String searchSong(String songName)
    {
        JSONArray songList = new JSONArray();
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("music.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray tempSongList = (JSONArray) obj;
             
            //Iterate over playlist array
            tempSongList.forEach( song -> parseSearchedSong((JSONArray)songList,(JSONObject) song, songName ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return songList.toJSONString();
    }
    //iterates through searchList and displays song names
    private static void displaySearchList(ArrayList<JSONObject> searchList)
    {
        for (JSONObject tempSong : searchList)
        {
            System.out.println(tempSong.get("title"));
        }
    }
      
    
    //create new playlist object
    public String CreatePlaylist(String username, String playlistName)
    {
        System.out.println("made it into CreatePlaylist");
        JSONObject playlistObj = new JSONObject();
        playlistObj.put("playlist name", playlistName);
        JSONArray songList = new JSONArray();
        playlistObj.put("songs", songList);
        
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
                System.out.println("temp username: " + temp.get("username"));
                System.out.println("local username: " + username);
                if (temp.get("username").equals(username))
                {
                    System.out.println("found user");
                    JSONParser parser = new JSONParser();
                    JSONArray json = (JSONArray) parser.parse(temp.get("playlists").toString());
                    json.add(playlistObj);
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
        return "true";
    }
    //deletes a playlist object
    public String DeletePlaylist(String username, String playlistName)
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
                    boolean more = true;
                    
                    while(more == true)
                    {
                        JSONObject temp2 = it2.next();
                        if (temp2.get("playlist name").equals(playlistName))
                        {
                            json.remove(temp2);
                            more = false;
                                    
                        }
                    }
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
        return "true";
    }
    //adds a song to a specific playlist
    public String addSongToPlaylist(String songName, String playlistName, String username)
    {
        
        System.out.println("username: " + username);
        System.out.println("playlistName: " + playlistName);
        System.out.println("songName: " + songName);
        
        JSONObject songObj = new JSONObject();
        songObj.put("song name", songName);
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;
            System.out.println("1.");
            Iterator<JSONObject> it = userList.iterator();
            while (it.hasNext())
            {
                System.out.println("2.");
                JSONObject temp = it.next();
                if (temp.get("username").equals(username))
                {
                    System.out.println("3.");
                    JSONParser parser = new JSONParser();
                    JSONArray json = (JSONArray) parser.parse(temp.get("playlists").toString());
                    Iterator<JSONObject> it2 = json.iterator();
                    while(it2.hasNext())
                    {
                        System.out.println("4.");
                        JSONObject temp2 = it2.next();
                        JSONArray songList = (JSONArray) parser.parse(temp2.get("songs").toString());
                        if (temp2.get("playlist name").equals(playlistName))
                        {
                            System.out.println("song added to playlist");
                            songList.add(songObj);
                            System.out.println(songList);
                        }
                        temp2.replace("songs", songList);
                    }
                    temp.replace("playlists",json);
                }
            }
            System.out.println(userList);
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
        
        return "true";
    }
    //displays all playlist names from profileList
    public String displayProfile(String username)
    {
        JSONArray profileList = new JSONArray();
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
                        System.out.println(temp2.get("playlist name"));
                        profileList.add(temp2);
                    }
                }
            }             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return profileList.toJSONString();
    }
    
    //displays the songs in a playlist
    public String displayPlaylist(String username, String playlistName)
    {
        
        JSONArray playlist = new JSONArray();
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
                        JSONArray songList = (JSONArray) parser.parse(temp2.get("songs").toString());
                        if (temp2.get("playlist name").equals(playlistName))
                        {
                            it = songList.iterator();
                            while(it.hasNext())
                            {
                                JSONObject temp3 = it.next();
                                playlist.add(temp3);                            
                            }
                        }
                    }
                }
            }
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return playlist.toJSONString();
    }
    
    /* 
    * getFileSize: Gets a size of the file
    * @param key: Song ID. Each song has a unique ID 
     */
    public Integer getFileSize(Long key) throws FileNotFoundException, IOException
    {
        File file = new File("./" + key);        
        Integer total =  (int)file.length();
        
        return total;
    }
    
    
}
