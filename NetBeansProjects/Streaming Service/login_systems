package Login_Sys;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.SocketException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Login_Systems {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public static String username;
	public static String pswd;
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
                                        
					Login_Systems window = new Login_Systems();
                                        
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_Systems() throws SocketException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SocketException {
            
            
                // array list used to check users
                ArrayList<User> userList = new ArrayList<User>();
                
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome ! Please log in");
		lblNewLabel.setBounds(168, 6, 156, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(39, 82, 77, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(39, 151, 77, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(174, 72, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 141, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(120, 207, 117, 29);
		frame.getContentPane().add(btnNewButton);
                
                // checks if the user has an existing account, if they dont, prompts them to sign up
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				username = textField.getText();
				pswd = passwordField.getText();
				
                                System.out.println(username);
                                System.out.println(pswd);
                                
                                JSONObject proxyObj =  prox.synchExecution("Login",username, pswd);
                                String proxyString = proxyObj.toJSONString();
                                String retStr = "";
                            try {
                                retStr = client.sendPacket(proxyString);
                                System.out.println("packet sent");
                            } catch (IOException ex) {
                                Logger.getLogger(Login_Systems.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                System.out.println(retStr);
                                System.out.println("process done");
                                if (retStr.contains("true")){
                                    JOptionPane.showMessageDialog(frame, "welcome back" + " " + username);
                                    home_screen hs;
                                    try {
                                        hs = new home_screen();
                                        hs.setVisible(true);
                                    } catch (SocketException ex) {
                                        Logger.getLogger(Login_Systems.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    frame.setVisible(false);
                                }
                                
                                else{
                                    JOptionPane.showMessageDialog(frame, "you do not have an existing account, please click Sign up");
                                }
                                
				
                                
                                username = "";
				pswd = "";
                                
			}
		});
                
            JButton signUp = new JButton("Sign Up");
            signUp.setBounds(250, 207, 117, 29);
            frame.getContentPane().add(signUp);
            
            // creates a new account for the user
            signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				username = textField.getText();
				pswd = passwordField.getText();
				
                                JSONObject proxyObj =  prox.synchExecution("CreateUser",username, pswd);
                                String proxyString = proxyObj.toJSONString();
                                String retStr = "";
                                System.out.println("retStr type: " + retStr.getClass());
                            try {
                                retStr = client.sendPacket(proxyString);
                            } catch (IOException ex) {
                                Logger.getLogger(Login_Systems.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                //JSONObject retObj;
                            try {
                                System.out.println("local username: " + username);
                                System.out.println("local password: " + pswd);
                                JSONObject retObj = (JSONObject) ret.parse(retStr);
                                System.out.println("server username: " + retObj.get("username"));
                                System.out.println("server password: " + retObj.get("password"));
                                if (username.equals(retObj.get("username")) && pswd.equals(retObj.get("password")))
                                {
                                    JOptionPane.showMessageDialog(frame, "Account created succesfully, Welcome ");
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(Login_Systems.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                               
                                
                                //CreateUser(username,pswd);;
                                //JOptionPane.showMessageDialog(frame, "Account created succesfully, Welcome ");
				home_screen hs;
                            try {
                                hs = new home_screen();
                                hs.setVisible(true);
                            } catch (SocketException ex) {
                                Logger.getLogger(Login_Systems.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                                //frame.setVisible(false);
                                
                                username = "";
				pswd = "";
                                
			}
		});
            
	}
        
        
    //creates a new user when signing up and adds it to ArrayList of users
    private static void CreateUser(String username, String password)
    {
        JSONObject userObj = new JSONObject();
        userObj.put("username",username);
        userObj.put("password",password);
        JSONArray playlists = new JSONArray();
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
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //checks if a user exists
    private static boolean Login(String username, String password)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray usersList = (JSONArray) obj;
           
            Iterator<JSONObject> it = usersList.iterator();
            while (it.hasNext())
            {
                JSONObject temp = it.next();
                if (temp.get("username").equals(username) && temp.get("password").equals(password))
                {
                    return true;
                }
            }
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        return false; //user doesn't exist and calls CreateUser()
    }
	
}
