package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListIsEmptyException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PlayListServiceTest {

    @Mock
    private PlayListRepository playListRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private PlayListService playListService;

    @Captor
    private ArgumentCaptor<PlayList> playListCaptor;

    private final String userId = "U1";
    private final String songId1 = "S1";
    private final String songId2 = "S2";
    private final String playlistId = "PL1";

    @BeforeEach
    void setUp() {
        // playListService is injected by Mockito with mocks above
    }

    @Test
    @DisplayName("create should build playlist, save it and attach to user when user and songs exist")
    void create_shouldSavePlaylistAndAttachToUser() {
        // Arrange
        User user = new User(userId, "Alice", new ArrayList<>());
        Song s1 = new Song("SongOne", "Rock", "Album1", "Artist1", Arrays.asList("Feat1"));
        Song s2 = new Song("SongTwo", "Pop", "Album2", "Artist2", Arrays.asList("Feat2"));
        List<String> songIds = Arrays.asList(songId1, songId2);
        PlayList saved = new PlayList(playlistId, "MyPlaylist", userId, Arrays.asList(s1, s2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(songRepository.findById(songId1)).thenReturn(Optional.of(s1));
        when(songRepository.findById(songId2)).thenReturn(Optional.of(s2));
        when(playListRepository.save(any(PlayList.class))).thenReturn(saved);

       

        // Act
        PlayList result = playListService.create(userId, "MyPlaylist", songIds);

        // Assert
        assertEquals("MyPlaylist", result.getName());
        assertEquals(user.getId(), result.getUserId());
        // user should have received the playlist via addPlaylist + userRepository.save
        verify(playListRepository).save(playListCaptor.capture());
        PlayList captured = playListCaptor.getValue();
        assertEquals("SongOne", captured.getSongs().get(0).getName());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("create should throw UserNotFoundException when user is absent")
    void create_shouldThrowWhenUserNotFound() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> playListService.create(userId, "P", Arrays.asList(songId1)));
    }

    @Test
    @DisplayName("modifyPlayListAddSongs should add songs to playlist and save")
    void modifyPlayListAddSongs_shouldAddSongs() {
        // Arrange
        User user = new User(userId, "Bob", new ArrayList<>());
        Song existing = new Song("SongOne", "Rock", "Album1", "Artist1", Arrays.asList("Feat1"));
        existing.setId(songId1); // set id on BaseEntity field
        PlayList playlist = new PlayList(playlistId, "PlName", user.getId(), new ArrayList<>(Arrays.asList(existing)));

        Song toAdd = new Song("SongTwo", "Jazz", "AlbumX", "ArtistX", Arrays.asList("FeatX"));
        toAdd.setId(songId2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(playListRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(songId2)).thenReturn(Optional.of(toAdd));

        // Act
        PlayList modified = playListService.modifyPlayListAddSongs(userId, playlistId, Arrays.asList(songId2));

        // Assert
        // playlist should now contain the added song
        assertEquals(2, modified.getSongs().size());
        verify(playListRepository).save(playlist);
    }

    @Test
    @DisplayName("playPlaylist should throw when playlist is empty")
    void playPlaylist_shouldThrowWhenEmpty() {
        // Arrange
        User mockUser = mock(User.class);
        PlayList empty = new PlayList(playlistId, "Empty", userId, new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(playListRepository.findById(playlistId)).thenReturn(Optional.of(empty));
        when(mockUser.getId()).thenReturn(userId);

        // Act & Assert
        assertThrows(PlayListIsEmptyException.class, () -> playListService.playPlaylist(userId, playlistId));
    }

    @Test
    @DisplayName("playBackOrNextSong should update playlist index and return next song")
    void playBackOrNextSong_shouldReturnNextSong() {
        // Arrange
        User mockUser = mock(User.class);
        Song s1 = new Song("A", "G", "Alb", "Ar", Arrays.asList());
        Song s2 = new Song("B", "G", "Alb", "Ar", Arrays.asList());
        s1.setId("s1");
        s2.setId("s2");
        PlayList playlist = new PlayList(playlistId, "Pl", userId, new ArrayList<>(Arrays.asList(s1, s2)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(mockUser.getActivatedPlayList()).thenReturn(Optional.of(playlist));

        // Act
        Song now = playListService.playBackOrNextSong(userId, "next");

        // Assert
        assertEquals(s2, now);
        verify(playListRepository).save(playlist);
    }

    @Test
    @DisplayName("playBackOrNextSong should update playlist index and return previous song")
    void playBackOrNextSong_shouldReturnPreviousSong() {
        // Arrange
        User mockUser = mock(User.class);
        Song s1 = new Song("A", "G", "Alb", "Ar", Arrays.asList());
        Song s2 = new Song("B", "G", "Alb", "Ar", Arrays.asList());
        s1.setId("s1");
        s2.setId("s2");
        PlayList playlist = new PlayList(playlistId, "Pl", userId, new ArrayList<>(Arrays.asList(s1, s2)));
        playlist.setCurrentSongIdx(1); // set to second song

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(mockUser.getActivatedPlayList()).thenReturn(Optional.of(playlist));

        // Act
        Song now = playListService.playBackOrNextSong(userId, "back");

        // Assert
        assertEquals(s1, now);
        verify(playListRepository).save(playlist);
    }

    @Test
    @DisplayName("playSong should throw when asked song not present in global pool")
    void playSong_shouldThrowWhenSongNotInPool() {
        // Arrange
        User mockUser = mock(User.class);
        PlayList playlist = new PlayList(playlistId, "Pl", userId, new ArrayList<>());
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(mockUser.getActivatedPlayList()).thenReturn(Optional.of(playlist));
        when(songRepository.findById("non")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SongNotFoundException.class, () -> playListService.playSong(userId, "non"));
    }

    @Test
    @DisplayName("playSong should successfully play requested song when it exists in playlist")
    void playSong_shouldPlayRequestedSong() {
        // Arrange
        User mockUser = mock(User.class);
        Song s1 = new Song("A", "G", "Alb", "Ar", Arrays.asList());
        Song s2 = new Song("B", "G", "Alb", "Ar", Arrays.asList());
        s1.setId(songId1);
        s2.setId(songId2);
        PlayList playlist = new PlayList(playlistId, "Pl", userId, new ArrayList<>(Arrays.asList(s1, s2)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(mockUser.getActivatedPlayList()).thenReturn(Optional.of(playlist));
        when(songRepository.findById(songId2)).thenReturn(Optional.of(s2));

        // Act
        Song result = playListService.playSong(userId, songId2);

        // Assert
        assertEquals(s2, result);
        assertEquals(1, playlist.getCurrentSongIdx()); // Should be at index 1 (second song)
        verify(playListRepository).save(playlist);
    }

    @Test
    @DisplayName("delete should successfully remove playlist from user and repository")
    void delete_shouldRemovePlaylist() {
        // Arrange
        User user = new User(userId, "TestUser", new ArrayList<>());
        PlayList playlist = new PlayList(playlistId, "TestPlaylist", userId, new ArrayList<>());
        user.addPlaylist(playlist); // Add playlist to user's list

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(playListRepository.findById(playlistId)).thenReturn(Optional.of(playlist));

        // Act
        playListService.delete(userId, playlistId);

        // Assert
        verify(userRepository).save(user);
        verify(playListRepository).delete(playlist);
        assertEquals(0, user.getPlaylists().size()); // User should have no playlists
    }

    @Test
    @DisplayName("modifyPlayListDeleteSongs should successfully remove songs from playlist")
    void modifyPlayListDeleteSongs_shouldRemoveSongs() {
        // Arrange
        User user = new User(userId, "TestUser", new ArrayList<>());
        Song s1 = new Song("SongOne", "Rock", "Album1", "Artist1", Arrays.asList("Feat1"));
        Song s2 = new Song("SongTwo", "Pop", "Album2", "Artist2", Arrays.asList("Feat2"));
        s1.setId(songId1);
        s2.setId(songId2);
        PlayList playlist = new PlayList(playlistId, "TestPlaylist", userId, new ArrayList<>(Arrays.asList(s1, s2)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(playListRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(songId1)).thenReturn(Optional.of(s1));

        // Act
        PlayList result = playListService.modifyPlayListDeleteSongs(userId, playlistId, Arrays.asList(songId1));

        // Assert
        assertEquals(1, result.getSongs().size()); // Should have one song left
        assertEquals(s2, result.getSongs().get(0)); // Should be the second song
        verify(playListRepository).save(playlist);
    }

    @Test
    @DisplayName("playPlaylist should successfully return first song of playlist")
    void playPlaylist_shouldPlayFirstSong() {
        // Arrange
        User mockUser = mock(User.class);
        Song s1 = new Song("First", "Rock", "Album1", "Artist1", Arrays.asList("Feat1"));
        Song s2 = new Song("Second", "Pop", "Album2", "Artist2", Arrays.asList("Feat2"));
        s1.setId(songId1);
        s2.setId(songId2);
        PlayList playlist = new PlayList(playlistId, "TestPlaylist", userId, new ArrayList<>(Arrays.asList(s1, s2)));
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(playListRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(mockUser.getId()).thenReturn(userId);
        when(mockUser.setActivatedPlaylist(playlist)).thenReturn(playlist);

        // Act
        Song result = playListService.playPlaylist(userId, playlistId);

        // Assert
        assertEquals(s1, result); // Should return first song
        assertEquals(0, playlist.getCurrentSongIdx()); // Should be at first index
        verify(playListRepository).save(playlist);
        verify(userRepository).save(mockUser);
    }
}
