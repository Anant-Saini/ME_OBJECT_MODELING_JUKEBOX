package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.appConfig.AppConfig;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.exceptions.CommandNotFoundException;

public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        //System.out.println(expectedSequence.equals(actualSequence));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.
        AppConfig appConfig = new AppConfig();
        CommandInvoker commandInvoker = appConfig.getCommandInvoker();
        // List<String> tokens = new ArrayList<>();
        // tokens.add("LOAD-DATA");
        // tokens.add("songs.csv");
        // commandInvoker.executeCommand("LOAD-DATA", tokens);

        String fileName = commandLineArgs.get(0).split("=")[1];
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = br.readLine()) != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);

            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(CommandNotFoundException e) {
            e.printStackTrace();
            System.out.println("No Such Command exists!");
        }
    }
}
