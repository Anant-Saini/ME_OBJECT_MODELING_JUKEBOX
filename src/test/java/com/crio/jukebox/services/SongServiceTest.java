package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.CSVFileLoadingException;
import com.crio.jukebox.repositories.SongRepository;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {
    
    @Mock
    private SongRepository songRepository;
    
    @Captor
    private ArgumentCaptor<Song> songCaptor;
    
    private SongService songService;
    private String testFilePath;
    
    @BeforeEach
    void setUp() {
        // Arrange
        songService = new SongService(songRepository);
        testFilePath = "test_songs.csv";
    }
    
    @AfterEach
    void tearDown() {
        // Clean up test file after each test
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    @DisplayName("loadSongsIntoRepository should successfully load valid songs from CSV")
    void loadSongsIntoRepository_ValidCSV_ShouldLoadSuccessfully() throws IOException {
        // Arrange
        String csvContent = "Song1,Rock,Album1,Artist1,FeatArtist1#FeatArtist2\n" +
                          "Song2,Pop,Album2,Artist2,FeatArtist3#FeatArtist4";
        createTestFile(csvContent);
        when(songRepository.save(any(Song.class))).thenReturn(new Song("", "", "", "", Arrays.asList()));
        
        // Act & Assert
        assertDoesNotThrow(() -> songService.loadSongsIntoRepository(testFilePath));
        verify(songRepository, times(2)).save(any(Song.class));
    }
    
    @Test
    @DisplayName("loadSongsIntoRepository should skip empty lines in CSV")
    void loadSongsIntoRepository_WithEmptyLines_ShouldSkipEmptyLines() throws IOException {
        // Arrange
        String csvContent = "Song1,Rock,Album1,Artist1,FeatArtist1#FeatArtist2\n\n" +
                          "Song2,Pop,Album2,Artist2,FeatArtist3#FeatArtist4\n\n";
        createTestFile(csvContent);
        when(songRepository.save(any(Song.class))).thenReturn(new Song("", "", "", "", Arrays.asList()));
        
        // Act & Assert
        assertDoesNotThrow(() -> songService.loadSongsIntoRepository(testFilePath));
        verify(songRepository, times(2)).save(any(Song.class));
    }
    
    @Test
    @DisplayName("loadSongsIntoRepository should skip malformed lines in CSV")
    void loadSongsIntoRepository_WithMalformedLines_ShouldSkipMalformedLines() throws IOException {
        // Arrange
        String csvContent = "Song1,Rock,Album1,Artist1,FeatArtist1#FeatArtist2\n" +
                          "InvalidLine,Rock,Album1\n" + // Malformed line
                          "Song2,Pop,Album2,Artist2,FeatArtist3#FeatArtist4";
        createTestFile(csvContent);
        when(songRepository.save(any(Song.class))).thenReturn(new Song("", "", "", "", Arrays.asList()));
        
        // Act & Assert
        assertDoesNotThrow(() -> songService.loadSongsIntoRepository(testFilePath));
        verify(songRepository, times(2)).save(any(Song.class));
    }
    
    @Test
    @DisplayName("loadSongsIntoRepository should throw CSVFileLoadingException for non-existent file")
    void loadSongsIntoRepository_NonExistentFile_ShouldThrowException() {
        // Arrange
        String nonExistentFile = "non_existent.csv";
        
        // Act & Assert
        assertThrows(CSVFileLoadingException.class, 
            () -> songService.loadSongsIntoRepository(nonExistentFile)
        );
    }
    
    @Test
    @DisplayName("loadSongsIntoRepository should create correct Song object from CSV data")
    void loadSongsIntoRepository_ShouldCreateCorrectSongObject() throws IOException {
        // Arrange
        String csvContent = "MySong,Rock,MyAlbum,MainArtist,Artist1#Artist2#Artist3";
        createTestFile(csvContent);
        when(songRepository.save(any(Song.class))).thenReturn(new Song("", "", "", "", Arrays.asList()));
        
        // Act
        songService.loadSongsIntoRepository(testFilePath);
        
        // Assert
        verify(songRepository).save(songCaptor.capture());
        Song capturedSong = songCaptor.getValue();
        assertEquals("MySong", capturedSong.getName());
        assertEquals("Rock", capturedSong.getGenre());
        assertEquals("MyAlbum", capturedSong.getAlbumName());
        assertEquals("MainArtist", capturedSong.getAlbumArtist());
        assertEquals(Arrays.asList("Artist1", "Artist2", "Artist3"), capturedSong.getFeaturedArtists());
    }

    // Helper method to create test CSV file
    private void createTestFile(String content) throws IOException {
        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write(content);
        }
    }
}