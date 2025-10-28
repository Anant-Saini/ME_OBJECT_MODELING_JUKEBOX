package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlayListIsEmptyException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlayListService;

public class PlayPlayListCommand implements ICommand {

    private PlayListService playListService;

    public PlayPlayListCommand(PlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {

        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        try {
          
            Song sng = playListService.playPlaylist(userId, playlistId);
            System.out.println("Current Song Playing");
            System.out.println(sng.toString());         
            
            
        } catch (UserNotFoundException e) {

            System.out.println(e.getMessage());
            
        } catch (PlayListNotFoundException e) {

            System.out.println(e.getMessage());
        
        
        } catch (PlayListIsEmptyException e) {
            
            System.out.println("Playlist is empty.");

        }
        
    }
    
}
