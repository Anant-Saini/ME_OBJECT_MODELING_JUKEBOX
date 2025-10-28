package com.crio.jukebox.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SongTest {
    
    private Song song;
    private List<String> featuredArtists;
    
    @BeforeEach
    void setUp() {
        featuredArtists = Arrays.asList("Artist1", "Artist2");
        song = new Song("TestSong", "Rock", "TestAlbum", "MainArtist", featuredArtists);
    }
    
    @Test
    @DisplayName("Constructor should initialize song with all properties")
    void constructor_ShouldInitializeWithAllProperties() {
        assertEquals("TestSong", song.getName());
        assertEquals("Rock", song.getGenre());
        assertEquals("TestAlbum", song.getAlbumName());
        assertEquals("MainArtist", song.getAlbumArtist());
        assertEquals(featuredArtists, song.getFeaturedArtists());
    }
    
    @Test
    @DisplayName("toString should return formatted string with song details")
    void toString_ShouldReturnFormattedString() {
        String expected = "Song - TestSong\nAlbum - TestAlbum\nArtists - Artist1,Artist2";
        assertEquals(expected, song.toString());
    }
}