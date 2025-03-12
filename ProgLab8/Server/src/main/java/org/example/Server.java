package org.example;

import org.example.commands.*;
import org.example.daba.DataBaseManager;
import org.example.utility.ServerCollectionManager;
import org.example.utility.ServerCommandManager;
import org.example.utility.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    static ForkJoinPool fjp = new ForkJoinPool();
    private static int ACCEPTING_PORT;
    public static void main(String[] args) {
        Logger LOGGER = Logger.getLogger(Server.class.getName());
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        LOGGER.addHandler(consoleHandler);
        LOGGER.setUseParentHandlers(false);

        LOGGER.log(Level.INFO, "Creating server command objects\n");
        new ServerCommandManager() {{
            registerServerCommands("insert", new Insert());
            registerServerCommands("update", new Update());
            registerServerCommands("remove_key", new RemoveKey());
            registerServerCommands("clear", new Clear());
            registerServerCommands("save", new Save());
            registerServerCommands("show", new Show());
            registerServerCommands("remove_greater", new RemoveGreater());
            registerServerCommands("remove_lower", new RemoveLower());
            registerServerCommands("remove_greater_key", new RemoveGreaterKey());
            registerServerCommands("max_by_coordinates", new MaxByCoordinates());
            registerServerCommands("filter_contains_name", new FilterContainsName());
            registerServerCommands("log_in", new LogIn());
            registerServerCommands("register", new SignUp());
            registerServerCommands("log_out", new LogOut());
            registerServerCommands("load_collection", new LoadCollection());
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

        try {
            Class.forName("org.postgresql.Driver");
            String url = System.getenv("URL_DB");
            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get(System.getenv("PROP"))));
            DataBaseManager dataBaseManager = new DataBaseManager();
            dataBaseManager.connectDataBase(url, properties);
            dataBaseManager.initDataBase();
            ServerCollectionManager.group = dataBaseManager.readFromDataBase();
            System.out.println(ServerCollectionManager.group);
//            new LoadCollection().executionForResponse(null, new User("admin", "sdkfbd[fbsbg"));
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            LOGGER.log(Level.INFO, "Create a server channel\n");
            Scanner scanner = new Scanner(System.in);
            LOGGER.log(Level.INFO, "Enter accepting port:");
            ACCEPTING_PORT = scanner.nextInt();
            ServerSocket serverSocket = new ServerSocket(ACCEPTING_PORT);
            ReentrantLock locker = new ReentrantLock();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.log(Level.INFO, String.format("Connection established with client port - %s\n", clientSocket.getPort()));

                fjp.execute(new ServerHandler(clientSocket, LOGGER, locker));
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, String.format("IOException %s",e.getMessage()));
        }
    }
}
