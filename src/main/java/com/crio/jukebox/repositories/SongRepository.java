package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements CRUDRepository<Song, String> {

    private final Map<String, Song> songMap;
    private Integer autoincrement;

    public SongRepository() {
        this.songMap = new HashMap<>();
        this.autoincrement = 0;
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoincrement = songMap.size();
    }

    @Override
    public Song save(Song entity) {
        if(entity.getId() == null) {
            this.autoincrement++;
            Song song = new Song(Integer.toString(this.autoincrement), entity.getName(), entity.getGenre(), entity.getAlbumName(), entity.getAlbumArtist(), entity.getFeaturedArtists());
            this.songMap.put(song.getId(), song);
            return song;
        } else {
            this.songMap.put(entity.getId(), entity);
            return entity;
        }

    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(this.songMap.get(id));
    }

    @Override
    public List<Song> findAll() {
        
        return this.songMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findByName(String name) {
        return this.songMap.values().stream().filter(sng -> sng.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<Song> delete(Song entity) {
        
        return Optional.ofNullable(this.songMap.remove(entity.getId()));
    }
    
}
