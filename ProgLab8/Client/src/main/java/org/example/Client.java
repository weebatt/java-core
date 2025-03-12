package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.commands.ExecuteScript;
import org.example.commands.Exit;
import org.example.commands.InsertObject;
import org.example.models.User;
import org.example.utility.ClientCommandManager;
import org.example.utility.ClientHandler;
import org.example.utility.ClientRequester;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Scanner;
import java.util.Set;

import static org.example.utility.ClientInvoker.clientInvoker;

public class Client extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Starting application...");
            Parent root = loadFXML("intro");
            if (root == null) {
                throw new IOException("Failed to load FXML for intro");
            }
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            System.out.println("Application started successfully.");
        } catch (Exception e) {
            System.err.println("Exception in Application start method!!: " + e.getMessage());
        }
    }

    public static void setRoot(String fxml) {
        try {
            Parent root = loadFXML(fxml);
            if (root != null) {
                scene.setRoot(root);
                Stage stage = (Stage) scene.getWindow();
                stage.sizeToScene();
                stage.setResizable(false);
            }
        } catch (Exception e){
            System.err.println("Exception in loadFXML method! More information - " + e.getMessage());
        }
    }

    private static Parent loadFXML(String fxml) {
        try {
            System.out.println("Loading FXML file: " + fxml + ".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Exception in loadFXML method! More information - " + e.getMessage());
            return null;
        }
    }

    private static final int CONNECTION_TIMEOUT = 5000;
    public static final ClientRequester clientRequester = new ClientRequester();
    public static final ClientHandler clientHandler = new ClientHandler();

    public static void main(String[] args) {
        ClientCommandManager commandManager = new ClientCommandManager();
        commandManager.register("insertObject", new InsertObject());
        commandManager.register("exit", new Exit());
        commandManager.register("execute_script", new ExecuteScript());
        commandManager.registerClientCommandsContainsObject("insert");
        commandManager.registerClientCommandsContainsObject("remove_greater");
        commandManager.registerClientCommandsContainsObject("remove_lower");
        commandManager.registerClientCommandsContainsObject("log_in");
        commandManager.registerClientCommandsContainsObject("register");
        commandManager.registerClientCommandsContainsValueAndObject("update");
        commandManager.registerClientCommandsContainsValueAndObject("update");
        connecting();
    }

    public static void connecting() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Print server address: ");
            String SERVER_ADDRESS = scanner.nextLine();
            System.out.println("Print server port: ");
            int SERVER_PORT = scanner.nextInt();

            SocketChannel socketChannel = SocketChannel.open();
            try {
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
            } catch (UnresolvedAddressException e) {
                System.out.println("You enter incorrect server address. Try again!");
                connecting();
            }

            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            clientInvoker.setBuffer(byteBuffer);
            clientInvoker.setSocketChannel(socketChannel);

            if (checkFirstConnection()){
                System.out.println("Connection GOOD!");
                try {
                    launch();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    connecting();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error with connection with server. " + e.getMessage());
            clientRequester.setUser(new User("new User", "1234"));
            connecting();
        }
    }

    public static boolean checkFirstConnection() throws ClassNotFoundException {
        try (Selector selector = Selector.open()){
            clientInvoker.getSocketChannel().register(selector, SelectionKey.OP_CONNECT);
            selector.select(CONNECTION_TIMEOUT);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            if (selectedKeys.isEmpty()) {
                System.out.println("Connection timeout");
            } else {
                clientInvoker.getSocketChannel().finishConnect();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
