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


public class playlist_creator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
        public String srch;
        public proxy prox = new proxy();
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
	public playlist_creator() throws SocketException {
                //client = new EchoClient(5000);
                // not used anymore
                ArrayList<JSONObject> searchList = new ArrayList<JSONObject>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name your playlist");
		lblNewLabel.setBounds(171, 6, 115, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(146, 34, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
//		JLabel lblNewLabel_1 = new JLabel("Search a song");
//		lblNewLabel_1.setBounds(171, 73, 61, 16);
//		contentPane.add(lblNewLabel_1);
		
//		textField_1 = new JTextField();
//		textField_1.setBounds(146, 101, 130, 26);
//		contentPane.add(textField_1);
//		textField_1.setColumns(10);
		
//		DefaultListModel listModel = new DefaultListModel();
//                JList list = new JList(listModel);
//		list.setBounds(116, 139, 187, 133);
//		contentPane.add(list);
		
//		JButton searchbtn = new JButton("Search");
//		searchbtn.setBounds(335, 191, 117, 29);
//		contentPane.add(searchbtn);
                
                // searches and displays songs
//                searchbtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
////                                for (int i = 0; i < search_results.size(); i++){
////                                    listModel.removeElement(i);
////                                }
////                                search_results.clear();
//				  srch = textField_1.getText();
////                                System.out.println(testlib.size());
////                                // search if any of our songs contain the search criteria
////				for (int i = 0; i < testlib.size(); i++){
////                                    System.out.println(i);
////                                    if (testlib.get(i).contains(search)){
////                                        search_results.add(testlib.get(i));
////                                    }
////				
////                                }
//                            System.out.println(searchList);
//                            searchList.clear();
//                            listModel.clear();
//                            JSONParser jsonParser = new JSONParser();
//                            try (FileReader reader = new FileReader("music.json"))
//                            {
//                                //Scanner input = new Scanner(System.in);
//                                //System.out.println("Enter artist name or song title:");
//                                //String search = input.next();
//                            //Read JSON file
//                                Object obj = jsonParser.parse(reader);
//
//                                JSONArray songList = (JSONArray) obj;
//
//                            //Iterate over playlist array
//                                songList.forEach( song -> parseSearchedSongObject((ArrayList<JSONObject>) searchList, (JSONObject) song, srch ) );
//
//                            } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                            } catch (IOException e) {
//                            e.printStackTrace();
//                             } 
//                            catch (ParseException e) {
//                            e.printStackTrace();
//                            }
//                            System.out.println(searchList);
//                                //parseSearchedSongObject(searchList, song, search);
//                                for (int i = 0; i < searchList.size(); i++){
//                                    listModel.addElement(searchList.get(i).get("title"));
//                                }
//                        }
//		});
                
                
                
		
		JButton createbtn = new JButton("Create");
		createbtn.setBounds(335, 232, 117, 29);
		contentPane.add(createbtn);
                
                // creates the playlist for the user with the entered name
                createbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                                System.out.println("logged in: "+ logged_in);
                                System.out.println("textfield.gettext():" + textField.getText());
                                JSONObject proxyObj =  prox.synchExecution("CreatePlaylist",logged_in, textField.getText());
                                String proxyString = proxyObj.toJSONString();
                                System.out.println("proxyString: " + proxyString);
                            try {
                                System.out.println("made it into try block");
                                String retStr = client.sendPacket(proxyString);
                                System.out.println("finished process");
                                home_screen hs;
                                hs = new home_screen();
                                hs.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(playlist_creator.class.getName()).log(Level.SEVERE, null, ex);
                            }
				//CreatePlaylist(home_screen.logged_in, textField.getText());
                                
                            
				
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
                    while(it2.hasNext())
                    {
                        JSONObject temp2 = it2.next();
                        if (temp2.get("playlist name").equals(playlistName))
                        {
                            json.remove(temp2);
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



