package com.crio.jukebox.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.InvalidCommandException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundInPlayListException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlayListService;

public class ModifyPlayListCommand implements ICommand {

    private PlayListService playListService;

    public ModifyPlayListCommand(PlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {

        String secondaryCommand = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIDs = tokens.stream().filter(id -> tokens.indexOf(id) > 3).collect(Collectors.toList());

        try {
            PlayList pl;
            switch(secondaryCommand) {

                case "ADD-SONG": 
                
                pl = playListService.modifyPlayListAddSongs(userId, playlistId, songIDs);
                printPlayList(pl);
                break;

                case "DELETE-SONG": 
                
                pl = playListService.modifyPlayListDeleteSongs(userId, playlistId, songIDs);
                printPlayList(pl);
                break;

                default: throw new InvalidCommandException("Commmand Format is invalid! Please check and try again.");

            }
            
            
            
        } catch (UserNotFoundException e) {

            System.out.println(e.getMessage());
            
        } catch (PlayListNotFoundException e) {

            System.out.println(e.getMessage());
        
        
        } catch (SongNotFoundException e) {
            
            System.out.println("Some Requested Songs Not Available. Please try again.");

        } catch (SongNotFoundInPlayListException e) {

            System.out.println("Some Requested Songs for Deletion are not present in the playlist. Please try again.");

        }
        
    }

    private void printPlayList(PlayList pl) {
        System.out.println("Playlist ID - "+pl.getId());
        System.out.println("Playlist Name - "+pl.getName());
        System.out.println("Song IDs - "+String.join(" ", pl.getSongIds()) );
    }
    
}
