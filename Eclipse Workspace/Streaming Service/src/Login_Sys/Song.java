/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

public class Song
{
    private double key;
    private double mode_confidence;
    private double artist_mbtags_count;
    private double key_confidence;
    private double tatums_start;
    private int year;
    private double duration;
    private double hotttnesss;
    private double beats_start;
    private double time_signature_confidence;
    private String title;
    private double bars_confidence;
    private String id;
    private double bars_start;
    private String artist_mbtags;
    private double start_of_fade_out;
    private double tempo;
    private double end_of_fade_in;
    private double beats_confidence;
    private double tatums_confidence;
    private int mode;
    private double time_signature;
    private double loudness;
    
    public Song() {
    }
    
    public Song(String title)
    {
        this.title = title;
    }
    
    public Song(final Song s) {
        this.key = s.key;
        this.mode_confidence = s.mode_confidence;
        this.artist_mbtags_count = s.artist_mbtags_count;
        this.key_confidence = s.key_confidence;
        this.tatums_start = s.tatums_start;
        this.year = s.year;
        this.duration = s.duration;
        this.hotttnesss = s.hotttnesss;
        this.beats_start = s.beats_start;
        this.time_signature_confidence = s.time_signature_confidence;
        this.title = s.title;
        this.bars_confidence = s.bars_confidence;
        this.id = s.id;
        this.bars_start = s.bars_start;
        this.artist_mbtags = s.artist_mbtags;
        this.start_of_fade_out = s.start_of_fade_out;
        this.tempo = s.tempo;
        this.end_of_fade_in = s.end_of_fade_in;
        this.beats_confidence = s.beats_confidence;
        this.tatums_confidence = s.tatums_confidence;
        this.mode = s.mode;
        this.time_signature = s.time_signature;
        this.loudness = s.loudness;
    }
    
    public void setKey(final double key) {
        this.key = key;
    }
    
    public double getKey() {
        return this.key;
    }
    
    public void setMode_Confidence(final double mode_confidence) {
        this.mode_confidence = mode_confidence;
    }
    
    public double getMode_Confidence() {
        return this.mode_confidence;
    }
    
    public void setArtist_Mbtags_Count(final double artist_mbtags_count) {
        this.artist_mbtags_count = artist_mbtags_count;
    }
    
    public double getArtist_Mbtags_Count() {
        return this.artist_mbtags_count;
    }
    
    public void setKey_Confidence(final double key_confidence) {
        this.key_confidence = key_confidence;
    }
    
    public double getKey_Confidence() {
        return this.key_confidence;
    }
    
    public void setTatums_Start(final double tatums_start) {
        this.tatums_start = tatums_start;
    }
    
    public double getTatums_Start() {
        return this.tatums_start;
    }
    
    public void setYear(final int year) {
        this.year = year;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public void setDuration(final double duration) {
        this.duration = duration;
    }
    
    public double getDuration() {
        return this.duration;
    }
    
    public void setHotttnesss(final double hotttnesss) {
        this.hotttnesss = hotttnesss;
    }
    
    public double getHotttnesss() {
        return this.hotttnesss;
    }
    
    public void setBeats_Start(final double beats_start) {
        this.beats_start = beats_start;
    }
    
    public double getBeats_Start() {
        return this.beats_start;
    }
    
    public void setTime_Signature_Confidence(final double time_signature_confidence) {
        this.time_signature_confidence = time_signature_confidence;
    }
    
    public double getTime_Signature_Confidence() {
        return this.time_signature_confidence;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setBars_Confidence(final double bars_confidence) {
        this.bars_confidence = bars_confidence;
    }
    
    public double getBars_Confidence() {
        return this.bars_confidence;
    }
    
    public void setID(final String id) {
        this.id = id;
    }
    
    public String getID() {
        return this.id;
    }
    
    public void setBars_Start(final double bars_start) {
        this.bars_start = bars_start;
    }
    
    public double getBars_Start() {
        return this.bars_start;
    }
    
    public void setArtist_Mbtags(final String artist_mbtags) {
        this.artist_mbtags = artist_mbtags;
    }
    
    public String getArtist_Mbtags() {
        return this.artist_mbtags;
    }
    
    public void setStart_Of_Fade_Out(final double start_of_fade_out) {
        this.start_of_fade_out = start_of_fade_out;
    }
    
    public double getStart_Of_Fade_Out() {
        return this.start_of_fade_out;
    }
    
    public void setTempo(final double tempo) {
        this.tempo = tempo;
    }
    
    public double getTempo() {
        return this.tempo;
    }
    
    public void setEnd_Of_Fade_In(final double end_of_fade_in) {
        this.end_of_fade_in = end_of_fade_in;
    }
    
    public double getEnd_Of_Fade_In() {
        return this.end_of_fade_in;
    }
    
    public void setBeats_Confidence(final double beats_confidence) {
        this.beats_confidence = beats_confidence;
    }
    
    public double getBeats_Confidence() {
        return this.beats_confidence;
    }
    
    public void setTatums_Confidence(final double tatums_confidence) {
        this.tatums_confidence = tatums_confidence;
    }
    
    public double getTatums_Confidence() {
        return this.tatums_confidence;
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public void setTime_Signature(final double time_signature) {
        this.time_signature = time_signature;
    }
    
    public double getTime_Signature() {
        return this.time_signature;
    }
    
    public void setLoudness(final double loudness) {
        this.loudness = loudness;
    }
    
    public double getLoudness() {
        return this.loudness;
    }
}
