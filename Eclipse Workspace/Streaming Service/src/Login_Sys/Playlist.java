/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.util.ArrayList;

public class Playlist
{
    private String name;
    private ArrayList<Song> songList;
    
    Playlist() {
        this.name = "";
        this.songList = new ArrayList<Song>();
    }
    
    Playlist(String name)
    {
        this.name = name;
        this.songList = new ArrayList<Song>();
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void addSong(final Song song) {
        this.songList.add(song);
    }
    
    public void deleteSong(final Song song) {
        this.songList.remove(new Song(song));
    }
    
    public ArrayList<Song> getSong() {
        return this.songList;
    }
}