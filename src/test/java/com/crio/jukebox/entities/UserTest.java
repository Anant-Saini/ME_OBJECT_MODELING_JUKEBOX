package com.crio.jukebox.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    
    private User user;
    private PlayList mockPlaylist1;
    private PlayList mockPlaylist2;
    private List<Song> mockSongs;

    @BeforeEach
    void setUp() {
        // Initialize mock songs
        mockSongs = new ArrayList<>();
        Song mockSong = mock(Song.class);
        mockSongs.add(mockSong);

        // Initialize mock playlists
        mockPlaylist1 = mock(PlayList.class);
        when(mockPlaylist1.getId()).thenReturn("PL1");
        mockPlaylist2 = mock(PlayList.class);
        when(mockPlaylist2.getId()).thenReturn("PL2");

        // Initialize user
        user = new User("U1", "TestUser", new ArrayList<>());
    }

    @Test
    @DisplayName("Constructor should initialize user with empty playlist when no playlists provided")
    void constructor_ShouldInitializeWithEmptyPlaylist() {
        User newUser = new User("TestUser");
        assertEquals("TestUser", newUser.getName());
        assertTrue(newUser.getPlaylists().isEmpty());
    }

    @Test
    @DisplayName("Constructor should initialize user with given playlists")
    void constructor_ShouldInitializeWithGivenPlaylists() {
        List<PlayList> playlists = Arrays.asList(mockPlaylist1);
        User newUser = new User("U1", "TestUser", playlists);
        assertEquals("TestUser", newUser.getName());
        assertEquals(1, newUser.getPlaylists().size());
        assertEquals(mockPlaylist1, newUser.getPlaylists().get(0));
    }

    @Test
    @DisplayName("addPlaylist should successfully add a new playlist")
    void addPlaylist_ShouldAddNewPlaylist() {
        user.addPlaylist(mockPlaylist1);
        assertEquals(1, user.getPlaylists().size());
        assertEquals(mockPlaylist1, user.getPlaylists().get(0));
    }

    @Test
    @DisplayName("removePlaylist should return true and remove playlist when it exists")
    void removePlaylist_ShouldRemoveExistingPlaylist() {
        // Add playlist first
        user.addPlaylist(mockPlaylist1);
        assertEquals(1, user.getPlaylists().size());
        
        // Remove playlist
        boolean result = user.removePlaylist(mockPlaylist1);
        assertTrue(result);
        assertTrue(user.getPlaylists().isEmpty());
    }

    @Test
    @DisplayName("removePlaylist should return false when playlist doesn't exist")
    void removePlaylist_ShouldReturnFalseForNonExistentPlaylist() {
        boolean result = user.removePlaylist(mockPlaylist1);
        assertFalse(result);
    }

    @Test
    @DisplayName("getPlaylistById should return playlist when it exists")
    void getPlaylistById_ShouldReturnPlaylistWhenExists() {
        user.addPlaylist(mockPlaylist1);
        Optional<PlayList> result = user.getPlaylistById("PL1");
        assertTrue(result.isPresent());
        assertEquals(mockPlaylist1, result.get());
    }

    @Test
    @DisplayName("getPlaylistById should return empty Optional when playlist doesn't exist")
    void getPlaylistById_ShouldReturnEmptyOptionalWhenNotExists() {
        Optional<PlayList> result = user.getPlaylistById("PL1");
        assertFalse(result.isPresent());
    }
}