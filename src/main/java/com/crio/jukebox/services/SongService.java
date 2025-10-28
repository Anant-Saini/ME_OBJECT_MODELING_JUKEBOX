package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.CSVFileLoadingException;
import com.crio.jukebox.repositories.SongRepository;

public class SongService {
    
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void loadSongsIntoRepository(String filename) {

        try( BufferedReader br = new BufferedReader( new FileReader(filename) ) ) {

            String line;

            while( (line = br.readLine()) != null) {

                if(line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                if(data.length < 5) {
                    //System.err.println("Skipping malformed line: "+ line);
                    continue;
                }
                String name = data[0];
                String genre = data[1];
                String albumName = data[2];
                String albumArtist = data[3];
                List<String> featuredArtists = Arrays.asList(data[4].split("#"));
                songRepository.save(new Song(name, genre, albumName, albumArtist, featuredArtists));
            }

        } catch(IOException e) {
             throw new CSVFileLoadingException("Input Output Exception while loading songs:", e);
        } catch(Exception e) {
             throw new CSVFileLoadingException("Something went wrong while loading songs:", e);
        }
    }

    public void printAllSongsAfterLoading(String filename) {
        loadSongsIntoRepository(filename);
     
        for(Song sng: songRepository.findAll()) {
            System.out.println("Song ID - "+sng.getId()+", Song Name - "+sng.getName());
        }
        
    }
}
