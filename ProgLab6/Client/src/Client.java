import commands.ExecuteScript;
import commands.Exit;
import commands.InsertObject;
import exceptions.ServerUnavailableException;
import utility.ClientCommandManager;
import utility.ClientHandler;
import utility.ClientRequester;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;

import static utility.ClientInvoker.clientInvoker;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 45016;
    private static final int CONNECTION_TIMEOUT = 5000;
    static ClientRequester clientRequester = new ClientRequester();
    static ClientHandler clientHandler = new ClientHandler();

    public static void main(String[] args) throws ServerUnavailableException {
        new ClientCommandManager() {{
            register("insertObject", new InsertObject());
            register("exit", new Exit());
            register("execute_script", new ExecuteScript());
        }};

        new ClientCommandManager() {{
            registerClientCommandsContainsObject("insert");
            registerClientCommandsContainsObject("remove_greater");
            registerClientCommandsContainsObject("remove_lower");

        }};

        new ClientCommandManager() {{
            registerClientCommandsContainsValueAndObject("update");
        }};
        connecting();
    }

    public static void connecting() {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            clientInvoker.setBuffer(byteBuffer);
            clientInvoker.setSocketChannel(socketChannel);
            checkFirstConnection();
            while (true) {
                if (tcpPing(SERVER_ADDRESS, SERVER_PORT, CONNECTION_TIMEOUT)){
                    System.out.println("What do you wanna do with massage:\n1 - send\n2 - receive");
                    Scanner scanner = new Scanner(System.in);
                    String actionWithMassage = scanner.nextLine();
                    try{
                        if (actionWithMassage.equals("1") || actionWithMassage.equals("send") && socketChannel.isConnected()) {
                            System.out.println("What command do you wanna send the server?");
                            String request = clientRequester.makeRequest();
                            clientRequester.sendRequest(request);
                        }
                        if (actionWithMassage.equals("2") || actionWithMassage.equals("receive")) {
                            clientHandler.receiveResponse();
                        }
                    } catch (Exception e){
                        throw new ServerUnavailableException();
                    }
                } else {
                    throw new ServerUnavailableException();
                }
            }
        } catch (IOException | ServerUnavailableException e) {
            System.out.println("IOException - " + e.getMessage());
            connecting();
        }
    }

    public static boolean tcpPing(String host, int port, int timeout) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().setSoTimeout(timeout);
            socketChannel.connect(new InetSocketAddress(host, port));

            ByteBuffer buffer = ByteBuffer.allocate(5);
            buffer.put("OKAY?".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

            int bytesRead = socketChannel.read(buffer);
            socketChannel.close();

            return bytesRead > 0;
        } catch (IOException e) {
            return false;
        }
    }
    public static void checkFirstConnection(){
        try {
            Selector selector = Selector.open();
            clientInvoker.getSocketChannel().register(selector, SelectionKey.OP_CONNECT);
            selector.select(CONNECTION_TIMEOUT);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            if (selectedKeys.isEmpty()) {
                System.out.println("Connection timeout");
                return;
            } else {
                System.out.println("Client successfully connected!");
                clientInvoker.getSocketChannel().finishConnect();
            }
            selector.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }
}
