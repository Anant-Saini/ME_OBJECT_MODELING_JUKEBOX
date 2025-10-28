package com.crio.jukebox.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayListTest {
    
    private PlayList playlist;
    private Song mockSong1;
    private Song mockSong2;
    private List<Song> songs;
    
    @BeforeEach
    void setUp() {
        // Create mock songs
        mockSong1 = mock(Song.class);
        when(mockSong1.getId()).thenReturn("S1");
        mockSong2 = mock(Song.class);
        when(mockSong2.getId()).thenReturn("S2");
        
        songs = new ArrayList<>();
        songs.add(mockSong1);
        
        // Initialize playlist
        playlist = new PlayList("PL1", "MyPlaylist", "U1", songs);
    }
    
    @Test
    @DisplayName("Constructor should initialize playlist with all properties")
    void constructor_ShouldInitializeWithAllProperties() {
        assertEquals("MyPlaylist", playlist.getName());
        assertEquals("U1", playlist.getUserId());
        assertEquals(1, playlist.getSongs().size());
        assertEquals(0, playlist.getCurrentSongIdx());
        assertEquals(PlayListStatus.NOT_SELECTED, playlist.getStatus());
    }
    
    @Test
    @DisplayName("addSong should return true and add song when it doesn't exist")
    void addSong_ShouldAddNewSong() {
        boolean result = playlist.addSong(mockSong2);
        assertTrue(result);
        assertEquals(2, playlist.getSongs().size());
        assertTrue(playlist.getSongs().contains(mockSong2));
    }
    
    @Test
    @DisplayName("addSong should return false when song already exists")
    void addSong_ShouldReturnFalseForExistingSong() {
        boolean result = playlist.addSong(mockSong1);
        assertFalse(result);
        assertEquals(1, playlist.getSongs().size());
    }
    
    @Test
    @DisplayName("removeSong should return true and remove song when it exists")
    void removeSong_ShouldRemoveExistingSong() {
        boolean result = playlist.removeSong(mockSong1);
        assertTrue(result);
        assertTrue(playlist.getSongs().isEmpty());
    }
    
    @Test
    @DisplayName("selectPlaylist should update status to SELECTED")
    void selectPlaylist_ShouldUpdateStatus() {
        playlist.selectPlaylist();
        assertEquals(PlayListStatus.SELECTED, playlist.getStatus());
    }
    
    @Test
    @DisplayName("deselectPlaylist should update status to NOT_SELECTED")
    void deselectPlaylist_ShouldUpdateStatus() {
        playlist.selectPlaylist();
        playlist.deselectPlaylist();
        assertEquals(PlayListStatus.NOT_SELECTED, playlist.getStatus());
    }
    
    @Test
    @DisplayName("getSongById should return song when it exists")
    void getSongById_ShouldReturnSongWhenExists() {
        Optional<Song> result = playlist.getSongById("S1");
        assertTrue(result.isPresent());
        assertEquals(mockSong1, result.get());
    }
    
    @Test
    @DisplayName("updateCurrSongIndex should move to next song correctly")
    void updateCurrSongIndex_ShouldMoveToNextSong() {
        playlist.addSong(mockSong2);
        playlist.updateCurrSongIndex("next");
        assertEquals(1, playlist.getCurrentSongIdx());
    }
    
    @Test
    @DisplayName("updateCurrSongIndex should move to previous song correctly")
    void updateCurrSongIndex_ShouldMoveToPreviousSong() {
        playlist.addSong(mockSong2);
        playlist.setCurrentSongIdx(1);
        playlist.updateCurrSongIndex("back");
        assertEquals(0, playlist.getCurrentSongIdx());
    }
    
    @Test
    @DisplayName("updateCurrSongIndexBySong should update index when song exists")
    void updateCurrSongIndexBySong_ShouldUpdateForExistingSong() {
        playlist.addSong(mockSong2);
        boolean result = playlist.updateCurrSongIndexBySong(mockSong2);
        assertTrue(result);
        assertEquals(1, playlist.getCurrentSongIdx());
    }
    
    @Test
    @DisplayName("getCurrentSong should return current song")
    void getCurrentSong_ShouldReturnCurrentSong() {
        //current song is by default the first song
        Song currentSong = playlist.getCurrentSong();
        assertEquals(mockSong1, currentSong);
    }
    
    @Test
    @DisplayName("isEmpty should return true when playlist has no songs")
    void isEmpty_ShouldReturnTrueForEmptyPlaylist() {
        playlist.removeSong(mockSong1);
        assertTrue(playlist.isEmpty());
    }
}