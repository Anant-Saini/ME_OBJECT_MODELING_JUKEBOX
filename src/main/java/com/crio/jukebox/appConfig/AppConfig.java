package com.crio.jukebox.appConfig;

import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlayListCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlayListCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;

public class AppConfig {

    //repositories
    private final UserRepository userRepository = new UserRepository();
    private final SongRepository songRepository = new SongRepository();
    private final PlayListRepository playListRepository = new PlayListRepository();

    //services
    private final UserService userService = new UserService(userRepository);
    private final SongService songService = new SongService(songRepository);
    private final PlayListService playListService = new PlayListService(playListRepository, userRepository, songRepository);

    //commands
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final CreatePlayListCommand createPlayListCommand = new CreatePlayListCommand(playListService);
    private final DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(playListService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    private final ModifyPlayListCommand modifyPlayListCommand = new ModifyPlayListCommand(playListService);
    private final PlayPlayListCommand playPlayListCommand = new PlayPlayListCommand(playListService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playListService);

    //Command Invoker
    private final CommandInvoker commandInvoker = CommandInvoker.getCommandInvoker();


    public AppConfig() {

        commandInvoker.registerCommand("LOAD-DATA", loadDataCommand);
        commandInvoker.registerCommand("CREATE-USER", createUserCommand);
        commandInvoker.registerCommand("CREATE-PLAYLIST", createPlayListCommand);
        commandInvoker.registerCommand("DELETE-PLAYLIST", deletePlayListCommand);
        commandInvoker.registerCommand("PLAY-PLAYLIST", playPlayListCommand);
        commandInvoker.registerCommand("MODIFY-PLAYLIST", modifyPlayListCommand);
        commandInvoker.registerCommand("PLAY-SONG", playSongCommand);

    }

    public CommandInvoker getCommandInvoker() {
        return commandInvoker;
    }

    
    
}
