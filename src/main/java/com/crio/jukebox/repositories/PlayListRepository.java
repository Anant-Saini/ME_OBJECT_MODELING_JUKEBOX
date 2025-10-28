package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;

public class PlayListRepository implements CRUDRepository<PlayList, String> {

    private final Map<String, PlayList> playlistMap;
    private Integer autoincrement;

    public PlayListRepository() {
        this.playlistMap = new HashMap<>();
        this.autoincrement = 0;
    }

    public PlayListRepository(Map<String, PlayList> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoincrement = playlistMap.size();
    }

    @Override
    public PlayList save(PlayList entity) {
        if(entity.getId() == null) {
            this.autoincrement++;
            PlayList playlist = new PlayList(Integer.toString(this.autoincrement), entity.getName(), entity.getUserId(), entity.getSongs());
            this.playlistMap.put(playlist.getId(), playlist);
            return playlist;
        } else {
            this.playlistMap.put(entity.getId(), entity);
            return entity;
        }

    }

    @Override
    public Optional<PlayList> findById(String id) {
        return Optional.ofNullable(this.playlistMap.get(id));
    }

    @Override
    public List<PlayList> findAll() {
        
        return this.playlistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<PlayList> findByName(String name) {
        return this.playlistMap.values().stream().filter(pl -> pl.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<PlayList> delete(PlayList entity) {
        
        return Optional.ofNullable(this.playlistMap.remove(entity.getId()));
    }
    
}
