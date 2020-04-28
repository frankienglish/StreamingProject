/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.util.ArrayList;

public class User
{
    private String username;
    private String password;
    private ArrayList<Playlist> playlists;
    
    User() {
        this.playlists = new ArrayList<Playlist>();
    }
    
    User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.playlists = new ArrayList<Playlist>();
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
}