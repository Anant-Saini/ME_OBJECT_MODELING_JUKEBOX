package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlayListService;

public class DeletePlayListCommand implements ICommand {

    private PlayListService playListService;

    public DeletePlayListCommand(PlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {

        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        try {

            playListService.delete(userId, playlistId);
            System.out.println("Delete Successful");
            
        } catch (UserNotFoundException e) {

            System.out.println(e.getMessage());
            
        } catch (PlayListNotFoundException e) {
            
            System.out.println("Playlist Not Found");
        }
        
        
    }
    
}
