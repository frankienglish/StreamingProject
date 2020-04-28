/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg327;

public class Artist
{
    private double terms_freq;
    private String terms;
    private String name;
    private double familiarity;
    private double longitude;
    private String id;
    private String location;
    private double latitude;
    private String similar;
    private double hotttnesss;
    
    public void setTerms_Freq(final double terms_freq) {
        this.terms_freq = terms_freq;
    }
    
    public double getTerms_Freq() {
        return this.terms_freq;
    }
    
    public void setTerms(final String terms) {
        this.terms = terms;
    }
    
    public String getTerms() {
        return this.terms;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setFamiliarity(final double familiarity) {
        this.familiarity = familiarity;
    }
    
    public double getFamiliarity() {
        return this.familiarity;
    }
    
    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }
    
    public double getLongitude() {
        return this.longitude;
    }
    
    public void setID(final String id) {
        this.id = id;
    }
    
    public String getID() {
        return this.id;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }
    
    public double getLatitude() {
        return this.latitude;
    }
    
    public void setSimilar(final String similar) {
        this.similar = similar;
    }
    
    public String getSimilar() {
        return this.similar;
    }
    
    public void setHotttnesss(final double hotttnesss) {
        this.hotttnesss = hotttnesss;
    }
    
    public double getHotttnesss() {
        return this.hotttnesss;
    }
}