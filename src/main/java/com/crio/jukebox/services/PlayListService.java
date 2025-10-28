package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.exceptions.ActivePlayListNotFoundException;
import com.crio.jukebox.exceptions.PlayListIsEmptyException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundInPlayListException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class PlayListService {

    private final PlayListRepository playListRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public PlayListService(PlayListRepository playListRepository, UserRepository userRepository, SongRepository songRepository) {
        this.playListRepository = playListRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public PlayList create(String userId, String playlistName, List<String> songIDs) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        List<Song> songs = new ArrayList<>();
        for(String songId: songIDs) {
            Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song with given songId: "+ songId +" doesn't exist!"));
            songs.add(song);
        }
        //System.out.println(songs);
        PlayList pList = playListRepository.save(new PlayList(playlistName, user.getId(), songs));
        user.addPlaylist(pList);
        userRepository.save(user);

        return pList;
    }

    public void delete(String userId, String playListId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        PlayList playlist = playListRepository.findById(playListId).orElseThrow(() -> new PlayListNotFoundException("Playlist with given PlaylistID: "+ playListId + " doesn't exist!"));

        if(!user.getPlaylists().contains(playlist)) throw new PlayListNotFoundException("Playlist with id: "+playListId+" doesn't belong to user: "+user.getName()+" hence cannot be deleted!");

        user.removePlaylist(playlist);
        userRepository.save(user);
        playListRepository.delete(playlist);
    }

    public PlayList modifyPlayListAddSongs(String userId, String playListId, List<String> songIDs) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        PlayList playlist = playListRepository.findById(playListId).orElseThrow(() -> new PlayListNotFoundException("Playlist with given PlaylistID: "+ playListId + " doesn't exist!"));

        if(!playlist.getUserId().equals(user.getId())) throw new PlayListNotFoundException("Playlist with id: "+playListId+" doesn't belong to user: "+user.getName()+" hence cannot be modified!");
        for(String songId: songIDs) {
            Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song with given songId: "+ songId +" doesn't exist!"));
            playlist.addSong(song);
        }
        playListRepository.save(playlist);
        return playlist;
    }

    public PlayList modifyPlayListDeleteSongs(String userId, String playListId, List<String> songIDs) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        PlayList playlist = playListRepository.findById(playListId).orElseThrow(() -> new PlayListNotFoundException("Playlist with given PlaylistID: "+ playListId + " doesn't exist!"));

        if(!playlist.getUserId().equals(user.getId())) throw new PlayListNotFoundException("Playlist with id: "+playListId+" doesn't belong to user: "+user.getName()+" hence cannot be modified!");
        for(String songId: songIDs) {
            Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song with given songId: "+ songId +" doesn't exist!"));
            if(!playlist.removeSong(song)) throw new SongNotFoundInPlayListException("Song with given songId: "+ songId +" doesn't exist in Playlist!");
            
        }
        playListRepository.save(playlist);
        return playlist;
    }

    public Song playPlaylist(String userId, String playListId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        PlayList playlist = playListRepository.findById(playListId).orElseThrow(() -> new PlayListNotFoundException("Playlist with given PlaylistID: "+ playListId + " doesn't exist!"));
        
        if(!playlist.getUserId().equals(user.getId())) throw new PlayListNotFoundException("Playlist with id: "+playListId+" doesn't belong to user: "+user.getName()+" hence cannot be played!");
        if(playlist.isEmpty()) throw new PlayListIsEmptyException("Playlist is empty!");

        PlayList activatedPlaylist = user.setActivatedPlaylist(playlist);
        playListRepository.save(activatedPlaylist);
        userRepository.save(user);

        return activatedPlaylist.getCurrentSong();

    }

    public Song playBackOrNextSong(String userId, String command) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        PlayList activatedPlaylist = user.getActivatedPlayList().orElseThrow(() -> new ActivePlayListNotFoundException("No active playlist found!"));

        activatedPlaylist.updateCurrSongIndex(command);
        playListRepository.save(activatedPlaylist);

        return activatedPlaylist.getCurrentSong();
    }

    public Song playSong(String userId, String songId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with given UserId: "+ userId +" doesn't exist!"));
        PlayList activatedPlaylist = user.getActivatedPlayList().orElseThrow(() -> new ActivePlayListNotFoundException("No active playlist found!"));
        Song sng = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song with given songId: "+ songId +" doesn't exist in pool!"));

        if(!activatedPlaylist.updateCurrSongIndexBySong(sng)) throw new SongNotFoundInPlayListException("Song with given songId: "+ songId +" doesn't exist in Playlist!");
        playListRepository.save(activatedPlaylist);

        return activatedPlaylist.getCurrentSong();
    }
    
}
