import commands.*;
import utility.DataAndRequestsProcessor;
import utility.FileManager;
import utility.ServerCommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) throws FileNotFoundException {
        Logger LOGGER = Logger.getLogger(Server.class.getName());
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        LOGGER.addHandler(consoleHandler);
        LOGGER.setUseParentHandlers(false);

        LOGGER.log(Level.INFO, "Creating server command objects\n");
        new ServerCommandManager() {{
            registerServerCommands("help", new Help());
            registerServerCommands("info", new Info());
            registerServerCommands("show", new Show());
            registerServerCommands("insert", new Insert());
            registerServerCommands("update", new Update());
            registerServerCommands("remove_key", new RemoveKey());
            registerServerCommands("clear", new Clear());
            registerServerCommands("save", new Save());
            registerServerCommands("remove_greater", new RemoveGreater());
            registerServerCommands("remove_lower", new RemoveLower());
            registerServerCommands("remove_greater_key", new RemoveGreaterKey());
            registerServerCommands("max_by_coordinates", new MaxByCoordinates());
            registerServerCommands("filter_contains_name", new FilterContainsName());
            registerServerCommands("print_field_descending_expelled_students", new PrintFieldDescendingExpelledStudents());
        }};

        LOGGER.log(Level.INFO, "We create command objects that require an object for input\n");
        new ServerCommandManager() {{
            registerServerCommandsContainsObject("insert");
            registerServerCommandsContainsObject("remove_greater");
            registerServerCommandsContainsObject("remove_lower");
        }};

        LOGGER.log(Level.INFO, "We create command objects that require an argument for input\n");
        new ServerCommandManager() {{
            registerServerCommandsContainsValue("filter_contains_name");
            registerServerCommandsContainsValue("remove_key");
            registerServerCommandsContainsValue("execute_script");
            registerServerCommandsContainsValue("remove_greater_key");

        }};

        LOGGER.log(Level.INFO, "We create command objects that require an argument  and object for input\n");
        new ServerCommandManager() {{
            registerServerCommandsContainsValueAndObject("update");
        }};

        LOGGER.log(Level.INFO, "Parsing the collection when the server starts\n");
        FileManager fileManager = new FileManager();
        Scanner scanner = new Scanner(new File(System.getenv("MYFILE")));
        if (scanner.hasNext()) {
            fileManager.parseCSVToCollection();
        }

        try (ServerSocket serverSocket = new ServerSocket(45016)){
            LOGGER.log(Level.INFO, "Create a server channel\n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.log(Level.INFO, String.format("New client connected - %s\n", clientSocket));

                LOGGER.log(Level.INFO, String.format("Create a new thread using a new connection - %s\n", clientSocket));
                Thread clientThread = new Thread(new DataAndRequestsProcessor(clientSocket, LOGGER));
                clientThread.start();
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, String.format("IOException %s",e.getMessage()));
        }
    }
}
