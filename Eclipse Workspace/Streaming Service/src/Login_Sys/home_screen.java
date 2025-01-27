package Login_Sys;

import static Login_Sys.Login_Systems.pswd;
import static Login_Sys.Login_Systems.username;
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
import java.net.SocketException;
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
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class home_screen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	String search;
	public ArrayList<ArrayList> arrList = new ArrayList<ArrayList>();
        
        
        public ArrayList<String> testlib = new ArrayList<String>();
        public ArrayList<String> search_results = new ArrayList<String>();
        // array lists to be contained in the array list
        
        public String selected_song;
        public static String logged_in = Login_Systems.username;
        public static boolean deleteOrKeep = false;
        public JList playlists;
        public static String chosen_playlist;
        public proxy prox = new proxy();
        JSONParser ret = new JSONParser();
        public EchoClient client = new EchoClient();
        public static boolean addToPlaylist = false;
        public String playlist_to_add;
        public String song_to_add;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                                        
					home_screen frame = new home_screen();
                                        
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
	public home_screen() throws SocketException {
            
                //client = new EchoClient(5000);
                // two lists to contain the contents to be displayed in the Jlists
                ArrayList<JSONObject> searchList = new ArrayList<JSONObject>();
                ArrayList<JSONObject> playlistList = new ArrayList<JSONObject>();
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setBounds(179, 6, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("My Playlists");
		lblNewLabel_1.setBounds(17, 56, 115, 26);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(257, 56, 187, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
                
                
                
                // search result list
                DefaultListModel listModel = new DefaultListModel();
                DefaultListModel listModel2 = new DefaultListModel();
                JList res = new JList(listModel); //or setListModel(listModel)
		res.setBounds(257, 109, 181, 165);
		contentPane.add(res);
                
		JButton searchButton = new JButton("Search");
                searchButton.setBounds(325, 81, 117, 29);
		contentPane.add(searchButton);
                
                
                
                // takes in the input from the selected item and plays the song
                res.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
                                                String selected = res.getSelectedValue().toString();
                                                System.out.println(selected);
                                                
                                                if (addToPlaylist == true)
                                                {
                                                    System.out.println("playlist name to be added: " + playlist_to_add);
                                                    System.out.println("playlist name to be added: " + selected);
                                                    System.out.println("username name to be added: " + logged_in);
                                                    JSONObject proxyObj = prox.synchExecution("addSongToPlaylist", logged_in, playlist_to_add, selected);
                                                    String proxyString = proxyObj.toJSONString();
                                                    try {
                                                        String retStr = client.sendPacket(proxyString);
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    
                                                    addToPlaylist = false;
                                                }
                                                
                                                else{
                                                
                                                Thread thread = new Thread(){
                                                    public void run(){
                                                        prox.synchExecution("playSong", selected);
                                                    }
                                                };
                                                thread.start();
                                                }
					}
				});
                
                // searches the JSON library for the desired song
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//                                
				  search = textField.getText();
                                  //prox.synchExecution("searchSong", search);
//				
//                          
                            searchList.clear();
                            listModel.clear();

                            JSONObject proxyObj =  prox.synchExecution("searchSong",search);
                            String proxyString = proxyObj.toJSONString();
                            String retStr;
                            try {
                                retStr = client.sendPacket(proxyString);
                                JSONArray jarr = (JSONArray) ret.parse(retStr);
                                Iterator<JSONObject> it = jarr.iterator();                                      
                                                while(it.hasNext())
                                                {
                                                    JSONObject temp2 = it.next();
                                                    listModel.addElement(temp2.get("title"));
                                                    
                                                }
                            } catch (IOException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            }      
                          }                                                          
                         });
                            
                            
                            
                            
                            
                            
                            
                
                
		
		JLabel lblNewLabel_2 = new JLabel("Search Artist or Song Title");
		lblNewLabel_2.setBounds(257, 29, 187, 26);
		contentPane.add(lblNewLabel_2);
		
                // list of playlists
                playlists = new JList(listModel2); 
		playlists.setBounds(6, 86, 132, 146);
		contentPane.add(playlists);
		
		
		

		// eiher deletes the selected playlist or takes you to the new playlist screen
		playlists.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
                                           
                                            if (addToPlaylist == false){
                                                chosen_playlist = playlists.getSelectedValue().toString();
                                                try {
                                                    playlist_screen ps = new playlist_screen();
                                                    ps.setVisible(true);
                                                } catch (IOException ex) {
                                                    Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                                                }
						
                                            }
                                            
                                            else if (addToPlaylist == true){
                                                playlist_to_add = playlists.getSelectedValue().toString();
                                            }
                                            
					}
				});
		
                
                JButton createButton = new JButton("Create Playlist");
		createButton.setBounds(0, 235, 117, 29);
		contentPane.add(createButton);
                
                JButton refreshbtn = new JButton("Refresh");
		refreshbtn.setBounds(140, 235, 117, 29);
		contentPane.add(refreshbtn);
                
                JButton deletebtn = new JButton("Delete");
		deletebtn.setBounds(140, 200, 117, 29);
		contentPane.add(deletebtn);
                
                JButton addBtn = new JButton("Add");
		addBtn.setBounds(140, 160, 117, 29);
		contentPane.add(addBtn);
                
                addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                         
                            addToPlaylist = true;             
                        }
		});
                
                
                
                
                
                deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("delete pressed");
//                                deleteOrKeep = true;
                                    Delete_playlist dp;
                            try {
                                dp = new Delete_playlist();
                                dp.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            }
				    
                        
                                
                        }
		});
                
                // refreshes the playlist list, needs to be used on start up
                refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JSON parser object to parse read file
                                playlistList.clear();
                                listModel2.clear();
                                JSONObject proxyObj =  prox.synchExecution("displayProfile",logged_in);
                                String proxyString = proxyObj.toJSONString();
                                String retStr;
                            try {
                                retStr = client.sendPacket(proxyString);
                                JSONArray jarr = (JSONArray) ret.parse(retStr);
                                Iterator<JSONObject> it = jarr.iterator();                                      
                                                while(it.hasNext())
                                                {
                                                    JSONObject temp2 = it.next();
                                                    listModel2.addElement(temp2.get("playlist name"));
                                                    //System.out.println(temp2.get("playlist name"));
                                                }
                            } catch (IOException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               
                                            }                                                          
                         });
                
                
                createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                                playlist_creator pc;
                            try {
                                pc = new playlist_creator();
                                pc.setVisible(true);
                            } catch (SocketException ex) {
                                Logger.getLogger(home_screen.class.getName()).log(Level.SEVERE, null, ex);
                            }
						
                                
                        }
		});
                
		
		
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
    
     private static void searchSong(JSONObject song, String songName)
    {
        //Get song object within list
        JSONObject songObj = (JSONObject) song.get("song");
        //check song name and artist name for searched word
        String temp = songObj.get("title").toString();
        temp = temp.replaceAll("\\s","").toLowerCase();
        String temp2 = songName;
        temp2 = temp2.replaceAll("\\s","").toLowerCase();
        if (temp2.equals(temp))
        {
            System.out.println("test");
            Main player = new Main();
            player.mp3play(songObj.get("id")+".mp3");
        }        
    }
    
     private static void playSong(String songName)
    {
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("music.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray songList = (JSONArray) obj;
             
            //Iterate over playlist array
            songList.forEach( song -> searchSong((JSONObject) song, songName ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
    }
     
    //displays all playlist names from profileList
    private static void displayProfile(String username)
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
                        System.out.println(temp2.get("playlist name"));
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
                    boolean more = true;
                    while(more == true)
                    {
                        JSONObject temp2 = it2.next();
                        if (temp2.get("playlist name").equals(playlistName))
                        { 
                            json.remove(temp2);
                            more = false;
                            System.out.println("playlist found and removed");                      
                        }
                        
                      // System.out.println("going");
                    }
                    //System.out.println("still going");
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
