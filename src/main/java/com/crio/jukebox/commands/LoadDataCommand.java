package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.CSVFileLoadingException;
import com.crio.jukebox.services.SongService;

public class LoadDataCommand implements ICommand {
    
    private final SongService songService;

    public LoadDataCommand(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        String fileName = tokens.get(1);
        try {

            songService.loadSongsIntoRepository(fileName);
            //songService.printAllSongsAfterLoading(fileName);
            System.out.println("Songs Loaded successfully");

        } catch (CSVFileLoadingException e) {

            System.out.println(e.getMessage());
            
        }
        
    }
    
    
}
