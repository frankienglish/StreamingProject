package Login_Sys;

import static Login_Sys.home_screen.logged_in;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class playlist_screen extends JFrame {

	private JPanel contentPane;
        public proxy prox = new proxy();
        public JSONParser ret = new JSONParser();
        public String choice = home_screen.chosen_playlist;
        public EchoClient client = new EchoClient();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playlist_screen frame = new playlist_screen();
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
	public playlist_screen() throws SocketException, IOException, ParseException {
                //client = new EchoClient(5000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Playlist");
		lblNewLabel.setBounds(192, 6, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pick a song to play");
		lblNewLabel_1.setBounds(155, 23, 146, 16);
		contentPane.add(lblNewLabel_1);
		
                DefaultListModel listModel = new DefaultListModel();
                
		JList list = new JList(listModel);
		list.setBounds(114, 50, 199, 209);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(6, 230, 94, 29);
		contentPane.add(btnNewButton);
                
                JSONObject proxyObj =  prox.synchExecution("displayPlaylist",logged_in, choice);
                String proxyString = proxyObj.toJSONString();
                String retStr = client.sendPacket(proxyString);
                JSONArray jarr = (JSONArray) ret.parse(retStr);
                Iterator<JSONObject> it = jarr.iterator();                                      
                while(it.hasNext())
                {
                        JSONObject temp2 = it.next();
                        listModel.addElement(temp2.get("song name"));
                        //System.out.println(temp2.get("playlist name"));
                        
                }  
                
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(frame, "welcome");
				home_screen hs;
                            try {
                                hs = new home_screen();
                                hs.setVisible(true);
                            } catch (SocketException ex) {
                                Logger.getLogger(playlist_screen.class.getName()).log(Level.SEVERE, null, ex);
                            }
				
				//frame.setVisible(false);
			}
		});
	}

}
