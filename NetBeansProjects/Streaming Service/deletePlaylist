/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;



import static Login_Sys.home_screen.logged_in;
import java.util.*;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Delete_playlist extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
        public String srch;
        public static String l_in = home_screen.logged_in;
        public boolean delete_orKeep = false;
        public proxy prox = new proxy();
        public JSONParser ret = new JSONParser();
        public EchoClient client = new EchoClient();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playlist_creator frame = new playlist_creator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Delete_playlist() throws SocketException, IOException, ParseException {
            
            
                //client = new EchoClient(5000);
                // not used anymore
                ArrayList<JSONObject> searchList = new ArrayList<JSONObject>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
                ArrayList<JSONObject> plistList = new ArrayList<JSONObject>();
		
		
//		JLabel lblNewLabel_1 = new JLabel("Search a song");
//		lblNewLabel_1.setBounds(171, 73, 61, 16);
//		contentPane.add(lblNewLabel_1);
		
//		textField_1 = new JTextField();
//		textField_1.setBounds(146, 101, 130, 26);
//		contentPane.add(textField_1);
//		textField_1.setColumns(10);



//                JButton refreshbtn = new JButton("Refresh");
//		refreshbtn.setBounds(0, 235, 117, 29);
//		contentPane.add(refreshbtn);
                
                JButton deletebtn = new JButton("Delete");
		deletebtn.setBounds(0, 175, 117, 29);
		contentPane.add(deletebtn);
                
                JButton back = new JButton("Back");
		back.setBounds(0, 250, 117, 29);
		contentPane.add(back);
                
                back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                            try {
                                home_screen hs = new home_screen();
                                hs.setVisible(true); 
                            } catch (SocketException ex) {
                                Logger.getLogger(Delete_playlist.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               
                                
                        }
		});
		
		DefaultListModel listModel = new DefaultListModel();
                JList playlists = new JList(listModel);
		playlists.setBounds(116, 139, 187, 133);
		contentPane.add(playlists);
                
                
                
                                plistList.clear();
                                listModel.clear();
                                System.out.println("logged_in delete screen: " + logged_in);
                                JSONObject proxyObj =  prox.synchExecution("displayProfile",logged_in);
                                String proxyString = proxyObj.toJSONString();
                                String retStr = client.sendPacket(proxyString);
                                JSONArray jarr = (JSONArray) ret.parse(retStr);
                                Iterator<JSONObject> it = jarr.iterator();                                      
                                                while(it.hasNext())
                                                {
                                                    JSONObject temp2 = it.next();
                                                    listModel.addElement(temp2.get("playlist name"));
                                                    //System.out.println(temp2.get("playlist name"));
                                                }

                                             
                    deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                                String toDelete = playlists.getSelectedValue().toString();
                                JSONObject proxyObj =  prox.synchExecution("DeletePlaylist",logged_in, toDelete);
                                String proxyString = proxyObj.toJSONString();
                                String retStr = "";
                            try {
                                retStr = client.sendPacket(proxyString);	  
                            } catch (IOException ex) {
                                Logger.getLogger(Delete_playlist.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
		    });
                
         
	
        }
        
        
        private static void CreatePlaylist(String username, String playlistName)
    {
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
                if (temp.get("username").equals(username))
                {
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
    }
    //deletes a playlist object
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
                    boolean more = true;
                    while(more == true)
                    {
                        JSONObject temp2 = it2.next();
                        if (temp2.get("playlist name").equals(playlistName))
                        { 
                            json.remove(temp2);
                            more = false;
                           // System.out.println("playlist found and removed");                      
                        }
                        
                       //System.out.println("going");
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
    }
    
    //adds a song to a specific playlist
    private static void addSongToPlaylist(String username, String playlistName, String songName)
    {
       
        JSONObject songObj = new JSONObject();
        songObj.put("song name", songName);
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
    }
    
    
     private static void parseSearchedSongObject(ArrayList<JSONObject> searchList, JSONObject song, String search)
    {
        //Get song object within list
        JSONObject songObj = (JSONObject) song.get("song");
        //Get artist object within list
        JSONObject artistObj = (JSONObject) song.get("artist");
        String temp = songObj.get("title").toString();
        temp = temp.replaceAll("\\s","").toLowerCase();
        String temp2 = artistObj.get("name").toString();
        temp2 = temp2.replaceAll("\\s","").toLowerCase();
        String temp3 = search;
        temp3 = temp3.replaceAll("\\s","").toLowerCase();
        //check song name and artist name for searched word
        if (temp3.equals(temp) || temp3.equals(temp2))
        {
            searchList.add(songObj);
        }        
    }
     
     

    
}



