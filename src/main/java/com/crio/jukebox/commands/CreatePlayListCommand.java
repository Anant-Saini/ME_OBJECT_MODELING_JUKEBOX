package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlayListService;

public class CreatePlayListCommand implements ICommand {

    private PlayListService playListService;

    public CreatePlayListCommand(PlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {

        String userId = tokens.get(1);
        String playlistName = tokens.get(2);
        List<String> songIDs = tokens.subList(3, tokens.size());
        //System.out.println(songIDs);

        try {

            PlayList pl = playListService.create(userId, playlistName, songIDs);
            System.out.println("Playlist ID - "+pl.getId());
            
        } catch (UserNotFoundException e) {

            System.out.println(e.getMessage());
            
        } catch (SongNotFoundException e) {
            
            System.out.println("Some Requested Songs Not Available. Please try again.");
        }
        
        
    }
    
}
