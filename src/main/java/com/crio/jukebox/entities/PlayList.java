package com.crio.jukebox.entities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayList extends BaseEntity {

    private String name;
    private List<Song> songs;
    private Integer currentSongIdx;
    private String userId;
    private PlayListStatus status;

    public PlayList(String name, String userId, List<Song> songs) {
        this.name = name;
        this.songs = songs;
        this.userId = userId;
        this.currentSongIdx = 0;
        this.status = PlayListStatus.NOT_SELECTED;
        this.id = null;
    }

    public PlayList(String id, String name, String userId, List<Song> songs) {
        this(name, userId, songs);
        this.id = id;
    }

    public boolean addSong(Song song) {
        boolean isAdded = !this.songs.contains(song);

        if(isAdded)
         this.songs.add(song);

        return isAdded;
    }

    public boolean removeSong(Song song) {
        boolean isRemoved = this.songs.removeIf((sng) -> sng.getId().equals(song.getId()) );
        return isRemoved;
    }

    public void selectPlaylist() {
        this.status = PlayListStatus.SELECTED;
    }

    public void deselectPlaylist() {
        this.setCurrentSongIdx(0);
        this.status = PlayListStatus.NOT_SELECTED;
    }

    public Optional<Song> getSongById(String id) {

        return this.songs.stream().filter((sng) -> sng.getId().equals(id)).findFirst();
    }

    public void updateCurrSongIndex(String command) {
        if(command.equalsIgnoreCase("next"))
            this.currentSongIdx = (this.currentSongIdx + 1) % this.songs.size();
        if(command.equalsIgnoreCase("back"))
            this.currentSongIdx = (this.currentSongIdx - 1 + this.songs.size()) % this.songs.size();
    }

    public boolean updateCurrSongIndexBySong(Song song) {
        int indexOfGivenSong = this.songs.indexOf(song);
        if(indexOfGivenSong == -1) {
            return false;
        } else {
            this.currentSongIdx = indexOfGivenSong;
            return true;
        }
    }

    public Song getCurrentSong() {
        if( !(this.currentSongIdx >= 0 && this.currentSongIdx < this.songs.size() ) ) this.currentSongIdx = 0;
        return this.songs.get(this.currentSongIdx);
    }

    public boolean isEmpty() {
        return this.songs.isEmpty(); 
    } 
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Song> getSongs() {
        return songs;
    }

    public List<String> getSongIds() {
        return songs.stream().map(sng -> sng.getId()).collect(Collectors.toList());
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Integer getCurrentSongIdx() {
        return currentSongIdx;
    }
    public void setCurrentSongIdx(Integer currentSongIdx) {
        this.currentSongIdx = currentSongIdx;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public PlayListStatus getStatus() {
        return status;
    }
    public void setStatus(PlayListStatus status) {
        this.status = status;
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
        PlayList other = (PlayList) obj;
        if (name == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
    
}
