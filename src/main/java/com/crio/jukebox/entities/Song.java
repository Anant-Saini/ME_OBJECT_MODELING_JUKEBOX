package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {

    private String name;
    private String genre;
    private String albumName;
    private String albumArtist;
   
    private List<String> featuredArtists;

    public Song(String id, String name, String genre, String albumName, String albumArtist,
    List<String> featuredArtists) {

        this(name, genre, albumName, albumArtist, featuredArtists);
        this.id = id;
        
    }

    public Song(String name, String genre, String albumName, String albumArtist,
    List<String> featuredArtists) {

        this.name = name;
        this.genre = genre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.featuredArtists = featuredArtists;
        this.id = null;
        
    }

    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public String getAlbumArtist() {
        return albumArtist;
    }
    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }
    public List<String> getFeaturedArtists() {
        return featuredArtists;
    }
    public void setFeaturedArtist(List<String> featuredArtists) {
        this.featuredArtists = featuredArtists;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Song - " + name + "\nAlbum - " + albumName + "\nArtists - "+ String.join(",", featuredArtists);

    }
    
}
