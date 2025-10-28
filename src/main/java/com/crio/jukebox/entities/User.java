package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User extends BaseEntity {

    private String name;
    private List<PlayList> playlists;

    public User(String name) {
        this.name = name;
        this.playlists = new ArrayList<>();
        this.id = null;
    }

    public User(String name, List<PlayList> playlists) {
        this(name);
        this.playlists = playlists;
    }
    public User(String id, String name) {
        this(name);
        this.id = id;
    }

    public User(String id, String name, List<PlayList> playlists) {
        this(name, playlists);
        this.id = id;
    }

    public void addPlaylist(PlayList playList) {
        this.playlists.add(playList);
    }

    public boolean removePlaylist(PlayList playlist) {
        boolean isRemoved = this.playlists.removeIf((pl) -> playlist.getId().equals(pl.getId()) );
        return isRemoved;
    }

    public Optional<PlayList> getPlaylistById(String id) {

        return this.playlists.stream().filter((pl) -> id.equals(pl.getId())).findFirst();
    }

    public Optional<PlayList> getActivatedPlayList() {

        return this.playlists.stream().filter(pl -> pl.getStatus().equals(PlayListStatus.SELECTED)).findFirst();

    }

    public PlayList setActivatedPlaylist(PlayList playList) {

        PlayList activatedPlaylist = null;

        for(PlayList pList: this.playlists) {
            if(pList.getId().equals(playList.getId())) {
                pList.selectPlaylist();
                activatedPlaylist = pList;
                
            } else {
                pList.deselectPlaylist();
            }
        }
        return activatedPlaylist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlayList> playlists) {
        this.playlists = playlists;
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", playlists=" + playlists + "]";
    }

    
}
