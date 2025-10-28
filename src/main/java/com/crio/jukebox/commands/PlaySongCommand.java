package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.ActivePlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundInPlayListException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlayListService;

public class PlaySongCommand implements ICommand {

    private PlayListService playListService;

    public PlaySongCommand(PlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {

        String userId = tokens.get(1);
        String instructionOrSongId = tokens.get(2);

        try {

            Song song;
          
            switch(instructionOrSongId) {

                case "BACK": 
                
                song = playListService.playBackOrNextSong(userId, instructionOrSongId);
                break;

                case "NEXT": 
                
                song = playListService.playBackOrNextSong(userId, instructionOrSongId);
                break;

                default:
                
                song = playListService.playSong(userId, instructionOrSongId);

            }
            
            System.out.println("Current Song Playing");
            System.out.println(song.toString());
            
            
        } catch (UserNotFoundException e) {

            System.out.println(e.getMessage());
            
        } catch (ActivePlayListNotFoundException e) {

            System.out.println(e.getMessage());
        
        
        } catch (SongNotFoundException e) {
            
            System.out.println(e.getMessage());

        } catch (SongNotFoundInPlayListException e) {

            System.out.println("Given song id is not a part of the active playlist");

        }
        
    }
    
}
