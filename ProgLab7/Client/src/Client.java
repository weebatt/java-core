import commands.*;
import exceptions.ServerUnavailableException;
import models.User;
import utility.ClientCommandManager;
import utility.ClientHandler;
import utility.ClientRequester;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.*;

import static utility.ClientInvoker.clientInvoker;

public class Client {
    private static String SERVER_ADDRESS;
    private static int SERVER_PORT;
    private static final int CONNECTION_TIMEOUT = 5000;
    static ClientRequester clientRequester = new ClientRequester();
    static ClientHandler clientHandler = new ClientHandler();

    public static void main(String[] args) {
        ClientCommandManager commandManager = new ClientCommandManager();
        commandManager.register("insertObject", new InsertObject());
        commandManager.register("exit", new Exit());
        commandManager.register("execute_script", new ExecuteScript());
        commandManager.register("log_in", new ClientAuthorization());
        commandManager.register("register", new ClientRegister());
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
            SERVER_ADDRESS = scanner.nextLine();
            System.out.println("Print server port: ");
            SERVER_PORT = scanner.nextInt();

            SocketChannel socketChannel = SocketChannel.open();
            try {
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
            } catch (UnresolvedAddressException e) {
                System.out.println("You enter incorrect server address. Try again!");
                connecting();
            }

            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            clientInvoker.setBuffer(byteBuffer);
            clientInvoker.setSocketChannel(socketChannel);

            if (checkFirstConnection()){
                System.out.println("Connection GOOD!");
                while (true) {
                    System.out.println("\nWhat do you wanna do with massage:\n1 - send\n2 - receive");
                    String actionWithMassage = scanner.next();
                    switch (actionWithMassage){
                        case("1"):
                        case("send"):
                            System.out.println("What command do you wanna send the server?");
                            String request = clientRequester.makeRequest();
                            clientRequester.sendRequest(request);
                            break;
                        case("2"):
                        case("receive"):
                            System.out.println(clientHandler.receiveResponse());
                            break;
                    }
                }
            }
            else {
                System.out.println("Error in first connection with server. Check did you enter the port on the server!");
                clientRequester.setUser(new User("testUser", "1234"));
                connecting();
            }
        } catch (IOException | ServerUnavailableException | ClassNotFoundException e) {
            System.out.println("Error with connection with server. " + e.getMessage());
            clientRequester.setUser(new User("testUser", "1234"));
            connecting();
        }
    }

    public static boolean checkFirstConnection() throws ServerUnavailableException, ClassNotFoundException {
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
